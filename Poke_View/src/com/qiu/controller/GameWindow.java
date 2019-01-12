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

	// ��д�رս��淽ʽ
	@Override
	public void windowClosing(WindowEvent e) {

		// ���ڼ���,��ʾһ��
		int key = JOptionPane.showConfirmDialog(lf.getGf(), "���Ҫ�˳���Ϸ��?", "��ܰ��ʾ", JOptionPane.OK_CANCEL_OPTION);
		if (key == JOptionPane.OK_OPTION) {
//			Util.pokeList.clear();//�Ƴ����ϵ�����Ԫ��
//			Util.playerOne.getPlayerPoke().clear();
//			Util.playerTwo.getPlayerPoke().clear();
//			Util.playerThree.getPlayerPoke().clear();
//			Util.landowner = 4;
//			//һ�����ݶ�Ҫ����
//			Util.initPoke();//��ʼ������
//			Util.player_Poke = 0;//������������Ϊ0
//			Util.playerOne.setPoints(4);
//			Util.playerTwo.setPoints(4);
//			Util.playerThree.setPoints(4);
//			Util.drawMark = 0;
//			Util.flag = true;
//			Util.callPlayer = 0;
//			Util.mark = 0;
//			Util.hidePoints = 0;
//			lf.getGf().getStartItem().setText("����(D)");
//			lf.getGf().getStartItem().setActionCommand("deal");
			lf.getLoginPanel().getStart().setText("������Ϸ");
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
