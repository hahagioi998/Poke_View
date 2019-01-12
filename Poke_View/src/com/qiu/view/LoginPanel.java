package com.qiu.view;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.qiu.util.Util;

public class LoginPanel extends JPanel {
	
	private JButton start = new JButton("进入游戏");//开始游戏按钮
	private JButton introduce = new JButton("游戏介绍");//开始游戏按钮
	private JButton exit = new JButton("退出游戏");//开始游戏按钮

	//构建面板
	public LoginPanel(){
		
		this.setSize(Util.LOGINPANEL_W, Util.LOGINPANEL_H);//面板大小
		this.setLayout(null);//绝对布局
		//进入游戏按钮位置
		start.setBounds(Util.LOGINPANELBUTTON_W, Util.LOGINPANELSTARTBUTTON_H, Util.BUTTON_W, Util.BUTTON_H);
		this.add(start);
		//游戏介绍按钮位置
		introduce.setBounds(Util.LOGINPANELBUTTON_W, Util.LOGINPANELINTRODUCEBUTTON_H, Util.BUTTON_W, Util.BUTTON_H);
		this.add(introduce);
		//退出游戏按钮位置
		exit.setBounds(Util.LOGINPANELBUTTON_W, Util.LOGINPANELEXITBUTTON_H, Util.BUTTON_W, Util.BUTTON_H);
		this.add(exit);
		
		//登录按钮的指令设计
		start.setActionCommand("start");
		introduce.setActionCommand("introduce");
		exit.setActionCommand("exit");
	}
	//面板变画板
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//放背景图
		g.drawImage(Util.LOGIN, 0, 0, Util.LOGINPANEL_W, Util.LOGINPANEL_H, 0, 0, Util.LOGINPANEL_W, Util.LOGINPANEL_H, null);
		
		this.repaint();
	}

	public JButton getStart() {
		return start;
	}

	public void setStart(JButton start) {
		this.start = start;
	}

	public JButton getIntroduce() {
		return introduce;
	}

	public void setIntroduce(JButton introduce) {
		this.introduce = introduce;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}	
	
}
