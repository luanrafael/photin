package com.photig.utilites;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import com.photig.model.Album;
import com.photig.model.Image;


public class Utils {

	
	public static final String PATH_ALBUNS = "C:\\PHOTIN\\ALBUNS";
	public static final String PATH_VIEWS = "C:\\PHOTIN\\";
	
	public static String getAlbumHash(){
		
		Instant timestamp = Instant.now();
		return String.valueOf(timestamp.getEpochSecond());
	}
	
	
	public static Album renameFiles(String path, String hash){
		
		String full_path = path + "\\" + hash;
		
		File dir = new File(full_path);
		File[] images = dir.listFiles(new FileImageFilter());
		Album album = new Album(hash);
		
		for(int i = 0; i < images.length; i++){
			
			if(!images[i].renameTo(new File(full_path + "\\" + hash + "_" + i + "." + getExtension(images[i].getAbsolutePath()))))
				return null;
			
			
		}
		
		return album;
	
	}
	
	
	public static Album getImages(String album_name){
		File dir = new File(PATH_ALBUNS + "\\" + album_name);
		File[] images = dir.listFiles(new FileImageFilter());
		
		Album album = new Album(album_name);
		for(int i = 0; i < images.length; i++)
			album.add(new Image("ALBUNS/" + album_name + "/" +images[i].getName()));
		
		return album;
	}
	
	private static String getExtension(String fileName){

		String[] ext = fileName.split("\\.");
		return ext.length > 1 ? ext[ext.length-1] : null;
	}
	
	 private static class FileImageFilter implements FileFilter {  
		  
	        public boolean accept(File pathname) {  
	  
	            if (!pathname.isFile()) {  
	                return false;  
	            }  
	  
	            FileImageInputStream input = null;
	            boolean ret;  
	            try {  
	  
	                // tenta ler arquivo como image  
	                input = new FileImageInputStream(pathname);  
	                Iterator i = ImageIO.getImageReaders(input);  
	                ret = i.hasNext();  
	            } catch (FileNotFoundException e) {  
	                throw new RuntimeException(e);  
	            } catch (IOException e) {  
	                throw new RuntimeException(e);  
	            } finally {  
	                if (input != null) {  
	                    try {  
	                        input.close();  
	                    } catch (IOException e) {  
	                        throw new RuntimeException(e);  
	                    }  
	                }  
	            }  
	            return ret;  
	        }  
	  
	    }  
	
}
