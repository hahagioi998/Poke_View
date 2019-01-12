package com.qiu.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import com.qiu.view.LoginFrame;

public class LoginWindow extends WindowAdapter {

	private LoginFrame lf;

	public LoginWindow(LoginFrame lf) {// 构造方法传参

		this.lf = lf;

	}

	// 游戏结束的时候弹框提示
	@Override
	public void windowClosing(WindowEvent e) {

		// 窗口监听,提示一下
		int key = JOptionPane.showConfirmDialog(lf, "真的要离开吗?", "温馨提示", JOptionPane.OK_CANCEL_OPTION);
		if (key == JOptionPane.OK_OPTION) {
			System.exit(0);
		}

	}

	public LoginFrame getLf() {
		return lf;
	}

	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}

}
