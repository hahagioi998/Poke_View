package com.qiu.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.qiu.util.OutPoke;
import com.qiu.util.Util;

public class ResultPanel extends JPanel {

	private JButton exit = new JButton("关   闭");
	private JButton restart = new JButton("再 来 一 局");

	public ResultPanel() {
		this.setLayout(null);

		this.setBounds(0, 0, 630, 270);
		this.getExit().setBounds(340, 192, 70, 40);
		this.getRestart().setBounds(220, 192, 100, 40);
		this.add(getExit());
		this.add(getRestart());

		this.exit.setActionCommand("exitResult");
		this.restart.setActionCommand("OneceAgain");
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(Util.RESULT, 0, 0, 626, 187, 0, 0, 626, 187, null);
		g.drawString(OutPoke.score + "  分", 180, 85);
		g.drawString(" X " + OutPoke.boomCount, 180, 125);
		g.drawString(" X " + OutPoke.doubleBoomCount, 180, 165);
		g.drawString(Util.playerOne.getName(), 290, 85);
		g.drawString(Util.playerTwo.getName(), 290, 125);
		g.drawString(Util.playerThree.getName(), 290, 165);
		if (Util.landowner == 1) {
			g.drawString(Util.playerOne.getWin(), 430, 85);
			g.drawString(Util.playerOne.getScore() + "(+" + OutPoke.score * 2 + ")", 545, 85);
			g.drawString(Util.playerTwo.getWin(), 430, 125);
			g.drawString(Util.playerTwo.getScore() + "(-" + OutPoke.score + ")", 545, 125);
			g.drawString(Util.playerThree.getWin(), 430, 165);
			g.drawString(Util.playerThree.getScore() + "(-" + OutPoke.score + ")", 545, 165);
		} else if (Util.landowner == 2) {
			g.drawString(Util.playerOne.getWin(), 430, 85);
			g.drawString(Util.playerOne.getScore() + "(-" + OutPoke.score + ")", 545, 85);
			g.drawString(Util.playerTwo.getWin(), 430, 125);
			g.drawString(Util.playerTwo.getScore() + "(+" + OutPoke.score * 2 + ")", 545, 125);
			g.drawString(Util.playerThree.getWin(), 430, 165);
			g.drawString(Util.playerThree.getScore() + "(-" + OutPoke.score + ")", 545, 165);
		} else if (Util.landowner == 3) {
			g.drawString(Util.playerOne.getWin(), 430, 85);
			g.drawString(Util.playerOne.getScore() + "(-" + OutPoke.score + ")", 545, 85);
			g.drawString(Util.playerTwo.getWin(), 430, 125);
			g.drawString(Util.playerTwo.getScore() + "(-" + OutPoke.score + ")", 545, 125);
			g.drawString(Util.playerThree.getWin(), 430, 165);
			g.drawString(Util.playerThree.getScore() + "(+ " + OutPoke.score * 2 + ")", 545, 165);
		}

		this.repaint();
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	public JButton getRestart() {
		return restart;
	}

	public void setRestart(JButton restart) {
		this.restart = restart;
	}

}
