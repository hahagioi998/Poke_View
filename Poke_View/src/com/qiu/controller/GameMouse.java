package com.qiu.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.qiu.model.Poke;
import com.qiu.util.Util;
import com.qiu.view.GamePanel;
import com.qiu.view.LoginFrame;

public class GameMouse extends MouseAdapter {

	GamePanel gp;
	public GameMouse(GamePanel gp){
		this.gp = gp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int left = e.getButton();
		if(left == 1){
			int mouseX = e.getX();//获取鼠标的横坐标
			int mouseY = e.getY();//获取鼠标的纵坐标
			for (int i = Util.playerOne.getPlayerPoke().size() -1; i >= 0; i--) {//从最后一张开始
				Poke p = Util.playerOne.getPlayerPoke().get(i);//简便写法
				Rectangle rec = new Rectangle(p.getPokeX(), p.getPokeY(), Util.POKE_W, Util.POKE_H);//确定区域
				if(rec.contains(new Point(mouseX, mouseY))){//鼠标的坐标是不是包含这个区域里面,是就进来
					if(p.getPokeY() == 500){//如果我原来的左边是500,就修改成485
						p.setPokeY(485);
						Util.pitchOn ++;//有起来一张就加1
					}else if(p.getPokeY() == 485){//如果我原来的左边是485,就修改成500
						p.setPokeY(500);
						Util.pitchOn --;//有下去一张就减1
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					getGp().repaint();
					break;//一定要写
				}
				
			}
			
		}
//		int mouseX = e.getX();//获取鼠标的横坐标
//		int mouseY = e.getY();//获取鼠标的纵坐标
//		System.out.println("aaaaa");
//		for (int i = Util.playerOne.getPlayerPoke().size() -1; i >= 0; i--) {
//			Poke p = Util.playerOne.getPlayerPoke().get(i);
//			Rectangle rec = new Rectangle(p.getPokeX(), p.getPokeY(), Util.POKE_W, Util.POKE_H);
//			if(rec.contains(new Point(mouseX, mouseY))){
//				if(p.getPokeY() == 500){
//					p.setPokeY(485);
//				}else if(p.getPokeY() == 485){
//					p.setPokeY(500);
//				}	
//				getGp().repaint();
//				break;
//			}
//			
//		}
		
		
		
	}
	public GamePanel getGp() {
		return gp;
	}
	public void setLf(GamePanel gp) {
		this.gp = gp;
	}
	
}
