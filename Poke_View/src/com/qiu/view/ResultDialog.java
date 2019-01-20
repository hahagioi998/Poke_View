package com.qiu.view;

import java.awt.Graphics;

import javax.swing.JDialog;

public class ResultDialog extends JDialog {
	private LoginFrame lf;
	private ResultPanel rp = new ResultPanel();
	public ResultDialog(){
		this.setLf(lf);
		this.setSize(630, 270);
		this.setTitle("Ω·À„’Ωº®");
		this.add(rp);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	
	public LoginFrame getLf() {
		return lf;
	}
	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}

	public ResultPanel getRp() {
		return rp;
	}

	public void setRp(ResultPanel rp) {
		this.rp = rp;
	}


	
}
