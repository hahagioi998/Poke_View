package com.qiu.model;

import java.awt.Image;

public class Poke {

	private Image image;//pokeͼƬ
	private int number;//poke����ֵ
	private int pokeX;//poke���ڻ�������ʾ�ĺ�����
	private int pokeY;//poke���ڻ�������ʾ��������
	
	
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
