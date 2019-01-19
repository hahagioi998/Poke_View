package com.qiu.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.qiu.util.Util;

public class ResultPanel extends JPanel {

	private JButton exit = new JButton("关   闭");
//	private JButton restart = new JButton("再 来 一 局");
	
	public ResultPanel(){
		this.setLayout(null);
		
		this.setBounds(0, 0, 630, 270);
		this.getExit().setBounds(340, 192, 70, 40);
//		this.getRestart().setBounds(220, 192, 100, 40);
		this.add(getExit());
//		this.add(getRestart());

		
		this.exit.setActionCommand("exit");
//		this.restart.setActionCommand("restart");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(Util.RESULT, 0, 0, 626, 187,  0, 0, 626, 187, null);
		this.repaint();
	}
	
    
	

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

//	public JButton getRestart() {
//		return restart;
//	}
//
//	public void setRestart(JButton restart) {
//		this.restart = restart;
//	}
//	
}
