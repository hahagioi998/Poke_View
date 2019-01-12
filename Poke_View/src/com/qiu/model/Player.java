package com.qiu.model;

import java.util.ArrayList;

public abstract class Player {

	private String name;
	private ArrayList<Poke> playerPoke = new ArrayList<Poke>();//���Ƶļ���
	private int points = 4;//��ʼ����
	private ArrayList<Poke> outPoke = new ArrayList<Poke>();//���Ƶļ���

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Poke> getPlayerPoke() {
		return playerPoke;
	}

	public void setPlayerPoke(ArrayList<Poke> playerPoke) {
		this.playerPoke = playerPoke;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public ArrayList<Poke> getOutPoke() {
		return outPoke;
	}

	public void setOutPoke(ArrayList<Poke> outPoke) {
		this.outPoke = outPoke;
	}
	
}
