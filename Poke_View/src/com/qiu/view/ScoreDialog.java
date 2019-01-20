package com.qiu.view;

import javax.swing.JDialog;

public class ScoreDialog extends JDialog {

	LoginFrame lf;
	private ScorePanel sp = new ScorePanel();
	public ScoreDialog(LoginFrame lf){
		this.lf = lf;
		this.setTitle("ตรทึ");
		this.setSize(348, 190);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.add(sp);
	}
	public ScorePanel getSp() {
		return sp;
	}
	public void setSp(ScorePanel sp) {
		this.sp = sp;
	}
	
}
