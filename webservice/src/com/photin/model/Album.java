package com.photin.model;

import java.util.ArrayList;
import java.util.List;

public class Album {

	public String name;
	public List<Image> images;
	
	public Album() {
		images = new ArrayList<Image>();
	}
	
	public Album(String  name) {
		images = new ArrayList<Image>();
		this.name = name;
	}
	
	
	public void add(Image image){
		images.add(image);
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public List<Image> getImages(){
		return this.images;
	}
	
	public void setImages(List<Image> images){
		this.images = images;
	}
	
}
