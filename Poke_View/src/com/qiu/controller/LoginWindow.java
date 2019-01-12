package com.qiu.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import com.qiu.view.LoginFrame;

public class LoginWindow extends WindowAdapter {

	private LoginFrame lf;

	public LoginWindow(LoginFrame lf) {// ���췽������

		this.lf = lf;

	}

	// ��Ϸ������ʱ�򵯿���ʾ
	@Override
	public void windowClosing(WindowEvent e) {

		// ���ڼ���,��ʾһ��
		int key = JOptionPane.showConfirmDialog(lf, "���Ҫ�뿪��?", "��ܰ��ʾ", JOptionPane.OK_CANCEL_OPTION);
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
