package com.qiu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.qiu.util.Util;
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
			String oneName = lf.getSd().getJtf_PlayerOne().getText();
			if(oneName.length() == 0 || oneName.length() > 3){
				JOptionPane.showMessageDialog(lf, "请将玩家一的名字长度控制在1-3个字符之间!");
			}else{
				Util.playerOne.setName(oneName);
			}
			String twoName = lf.getSd().getJtf_PlayerTwo().getText();
			if(twoName.length() == 0 || twoName.length() > 3){
				JOptionPane.showMessageDialog(lf, "请将玩家二的名字长度控制在1-3个字符之间!");
			}else{
				Util.playerTwo.setName(twoName);
			}
			String threeName = lf.getSd().getJtf_PlayerThree().getText();
			if(threeName.length() == 0 || threeName.length() > 3){
				JOptionPane.showMessageDialog(lf, "请将玩家三的名字长度控制在1-3个字符之间!");
			}else{
				Util.playerThree.setName(threeName);
			}
//			lf.getSd().setVisible(false);
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
