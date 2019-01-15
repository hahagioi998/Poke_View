package com.qiu.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.qiu.util.Util;

public class RemainPoke extends JPanel {

	public RemainPoke(){
		this.setBounds(296, 0, 408, 50);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Util.REMAIN, 0, 0, 408, 50, 0, 0, 408, 50, null);
		this.repaint();
	}
}
