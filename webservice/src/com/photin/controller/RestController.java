package com.photin.controller;


import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.externalStaticFileLocation;

import java.io.File;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.photin.model.Album;
import com.photin.model.AlbumName;
import com.photin.model.Image;
import com.photin.utilites.Utils;



public class RestController {

	public static void main(String[] args) {

		
		externalStaticFileLocation(Utils.PATH_VIEWS);

		get("/", (req,resp) -> {
		
			return "index.html";
		
		});
		
		get("/oi", (req,resp) -> {
			
			Image img = new Image();
			img.setImage("minha imagem");
			
			return new Gson().toJson(img);
		});
		
		
		post("/upload", (request, resonse) -> {
			
			String album_name = Utils.getAlbumHash();
			
			final File files = new File(Utils.PATH_ALBUNS + "\\" + album_name);
            
			if (!files.exists() && !files.mkdirs()) {
                throw new RuntimeException("Failed to create directory " + files.getAbsolutePath());
            }
            try {
				final MultipartRequest req = new MultipartRequest(request.raw(), files.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
            Album album = Utils.renameFiles(Utils.PATH_ALBUNS, album_name);
            return new Gson().toJson(new AlbumName(album_name));
		});
		
		get("/images/:album", (req,resp) -> {
			
			String result = req.params(":album");
			
			return new Gson().toJson(Utils.getImages(result));
			
			}
		);

	}

}
