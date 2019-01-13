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
			int mouseX = e.getX();//��ȡ���ĺ�����
			int mouseY = e.getY();//��ȡ����������
			for (int i = Util.playerOne.getPlayerPoke().size() -1; i >= 0; i--) {//�����һ�ſ�ʼ
				Poke p = Util.playerOne.getPlayerPoke().get(i);//���д��
				Rectangle rec = new Rectangle(p.getPokeX(), p.getPokeY(), Util.POKE_W, Util.POKE_H);//ȷ������
				if(rec.contains(new Point(mouseX, mouseY))){//���������ǲ��ǰ��������������,�Ǿͽ���
					if(p.getPokeY() == 500){//�����ԭ���������500,���޸ĳ�485
						p.setPokeY(485);
						Util.pitchOn ++;//������һ�žͼ�1
					}else if(p.getPokeY() == 485){//�����ԭ���������485,���޸ĳ�500
						p.setPokeY(500);
						Util.pitchOn --;//����ȥһ�žͼ�1
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					getGp().repaint();
					break;//һ��Ҫд
				}
				
			}
			
		}
//		int mouseX = e.getX();//��ȡ���ĺ�����
//		int mouseY = e.getY();//��ȡ����������
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
