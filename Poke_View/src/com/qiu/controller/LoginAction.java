package com.qiu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.qiu.view.GameFrame;
import com.qiu.view.LoginFrame;

public class LoginAction implements ActionListener {

	private LoginFrame lf;

	public LoginAction(LoginFrame lf) {// 构造方法传参

		this.setLf(lf);

	}
	//动作监听
	@Override
	public void actionPerformed(ActionEvent e) {

		String order = e.getActionCommand();//按钮指令的获取
		if(order.equals("start")){
			lf.getGf().setVisible(true);//游戏界面可见
			lf.setVisible(false);//登录界面不可见
			new DealThread(lf).start();
		}else if(order.equals("introduce")){
			String text = "“斗地主”是流行于全国的一种扑克游戏,\n玩法简单,娱乐性强,老少皆宜。据传\n在万恶的旧社会，地主横行乡里，无恶\n"
					+ "不作。人们为了发泄对地主的痛恨，常\n常在一天的劳作之后，一家人关起门来\n“斗地主”。该游戏由三人玩，用一副或"
					+ "\n两幅牌，地主为一方，其余两家为另一\n方，双方对战，先出完牌的一方获胜。";
			JOptionPane.showMessageDialog(lf, text,"游戏介绍",JOptionPane.CLOSED_OPTION);
		}else if(order.equals("exit")){
			// 窗口监听,提示一下
			int key = JOptionPane.showConfirmDialog(lf, "真的要离开吗?", "温馨提示", JOptionPane.OK_CANCEL_OPTION);
			if (key == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}
		
	}

	public LoginFrame getLf() {
		return lf;
	}

	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}

}
