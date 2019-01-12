package com.qiu.model;

import java.awt.Image;

public class Poke {

	private Image image;//poke图片
	private int number;//poke的面值
	private int pokeX;//poke牌在画板上显示的横坐标
	private int pokeY;//poke牌在画板上显示的纵坐标
	
	
	public Poke(Image image, int number) {
		this.image = image;
		this.number = number;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPokeX() {
		return pokeX;
	}
	public void setPokeX(int pokeX) {
		this.pokeX = pokeX;
	}
	public int getPokeY() {
		return pokeY;
	}
	public void setPokeY(int pokeY) {
		this.pokeY = pokeY;
	}
	
}
