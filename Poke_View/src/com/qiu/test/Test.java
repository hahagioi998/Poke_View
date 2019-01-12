package com.qiu.test;

import com.qiu.util.Util;
import com.qiu.view.LoginFrame;

public class Test {

	public static void main(String[] args) {
	
		Util.initPoke();
		LoginFrame login = new LoginFrame();
		login.setVisible(true);
//		Util.show();
//		System.out.println();
	}
	
}
