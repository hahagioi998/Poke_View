package com.qiu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.qiu.view.LoginFrame;

public class SelectAction implements ActionListener {

	LoginFrame lf;

	public SelectAction(LoginFrame lf) {
		this.lf = lf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String order = e.getActionCommand();
		if(order.equals("sure")){
			System.out.println("aaaaaaaaa");
		}else if(order.equals("cancel")){
			lf.getSd().setVisible(false);
		}

	}

	public LoginFrame getLf() {
		return lf;
	}

	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}
}
