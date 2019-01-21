package com.qiu.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.qiu.model.Poke;
import com.qiu.util.OutPoke;
import com.qiu.util.Util;

public class RemainPoke extends JPanel {

	int[] count = { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 22 };

	public RemainPoke() {
		this.setBounds(296, 0, 408, 50);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Util.REMAIN, 0, 0, 408, 50, 0, 0, 408, 50, null);
		// 记牌
		g.setColor(Color.BLUE);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
		// 画记牌器
		if (Util.key == 3 || Util.key == 4) {
			for (int i = 0; i < count.length; i++) {
				int c = reMainPoke(count[i]);
				g.drawString(c + "", 12 + (i * 29), 42);
			}
			int c_W = reMainPoke(100) + reMainPoke(99);
			g.drawString(c_W + "", 389, 42);
		}

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.repaint();
	}

	// 王的记牌方法
	public int reMainPoke(int num) {
		int count = 0;
		if(Util.playerOne.getPlayerPoke().size() != 0){
			for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {
				Poke p = Util.playerOne.getPlayerPoke().get(i);
				if (num == p.getNumber()) {
					count++;
				}
			}
		}
		if(Util.playerTwo.getPlayerPoke().size() != 0){
			for (int i = 0; i < Util.playerTwo.getPlayerPoke().size(); i++) {
				Poke p = Util.playerTwo.getPlayerPoke().get(i);
				if (num == p.getNumber()) {
					count++;
				}
			}
		}
		if(Util.playerThree.getPlayerPoke().size() != 0){
			for (int i = 0; i < Util.playerThree.getPlayerPoke().size(); i++) {
				Poke p = Util.playerThree.getPlayerPoke().get(i);
				if (num == p.getNumber()) {
					count++;
				}
			}
		}
		
		return count;
	}
}
