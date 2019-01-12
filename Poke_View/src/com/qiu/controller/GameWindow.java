package com.qiu.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import com.qiu.util.Util;
import com.qiu.view.GameFrame;
import com.qiu.view.LoginFrame;

public class GameWindow extends WindowAdapter {

	private LoginFrame lf;

	public GameWindow(LoginFrame lf) {

		this.lf = lf;
	}

	// 重写关闭界面方式
	@Override
	public void windowClosing(WindowEvent e) {

		// 窗口监听,提示一下
		int key = JOptionPane.showConfirmDialog(lf.getGf(), "真的要退出游戏吗?", "温馨提示", JOptionPane.OK_CANCEL_OPTION);
		if (key == JOptionPane.OK_OPTION) {
//			Util.pokeList.clear();//移除集合的所有元素
//			Util.playerOne.getPlayerPoke().clear();
//			Util.playerTwo.getPlayerPoke().clear();
//			Util.playerThree.getPlayerPoke().clear();
//			Util.landowner = 4;
//			//一切数据都要重置
//			Util.initPoke();//初始化数据
//			Util.player_Poke = 0;//发牌轮数重置为0
//			Util.playerOne.setPoints(4);
//			Util.playerTwo.setPoints(4);
//			Util.playerThree.setPoints(4);
//			Util.drawMark = 0;
//			Util.flag = true;
//			Util.callPlayer = 0;
//			Util.mark = 0;
//			Util.hidePoints = 0;
//			lf.getGf().getStartItem().setText("发牌(D)");
//			lf.getGf().getStartItem().setActionCommand("deal");
			lf.getLoginPanel().getStart().setText("继续游戏");
			lf.getGf().setVisible(false);
			lf.setVisible(true);
		}

	}

	public LoginFrame getGf() {
		return lf;
	}

	public void setGf(LoginFrame lf) {
		this.lf = lf;
	}

	public LoginFrame getLf() {
		return lf;
	}

	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}

}
