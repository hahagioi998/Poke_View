package com.qiu.controller;

import com.qiu.util.OutPoke;
import com.qiu.util.Util;
import com.qiu.view.LoginFrame;

public class TimeThread extends Thread {
	
	LoginFrame lf;
	public TimeThread(LoginFrame lf){
		this.lf = lf;
	}

	@Override
	public void run() {
		while(true){
			if(Util.key == 4){
				if(Util.callPlayer == 0 && Util.timeDown > 0){
					Util.timeDown --;//����ʱ��һ
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(Util.timeDown == 0){//��ʱ���Ϊ0��ʱ��Ϳ����йܹ���
						lf.getGf().getGamePanel().getTrusteeship().setText("ȡ���й�");
						OutPoke.isTrusteeship = true;
					}
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
