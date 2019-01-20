package com.qiu.view;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.qiu.util.Util;

public class ScorePanel extends JPanel {

	private JButton reset = new JButton("÷ÿ  ÷√");
	private JButton sure = new JButton("»∑  ∂®");
	
	public ScorePanel(){
		this.setSize(348, 190);
		this.setLayout(null);
		reset.setBounds(62, 115, 100, 40);
		sure.setBounds(182, 115, 100, 40);
		this.add(reset);
		this.add(sure);
		this.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		this.getSure().setActionCommand("sure");
		this.getReset().setActionCommand("reset");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(Util.SCORE, 0, 0, 341, 161, 0, 0, 341, 161, null);
		g.drawString(Util.playerOne.getName(), 110, 35);
		g.drawString(Util.playerTwo.getName(), 190, 35);
		g.drawString(Util.playerThree.getName(), 270, 35);
		g.drawString(Util.playerOne.getScore()+"", 110, 88);
		g.drawString(Util.playerTwo.getScore()+"", 190, 88);
		g.drawString(Util.playerThree.getScore()+"", 270, 88);
		this.repaint();
	}

	public JButton getReset() {
		return reset;
	}

	public void setReset(JButton reset) {
		this.reset = reset;
	}

	public JButton getSure() {
		return sure;
	}

	public void setSure(JButton sure) {
		this.sure = sure;
	}
	
}
