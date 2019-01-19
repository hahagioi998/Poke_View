package com.qiu.view;

import java.awt.Graphics;

import javax.swing.JDialog;

import com.qiu.controller.ResultAction;

public class ResultDialog extends JDialog {
	private LoginFrame lf;
	private ResultPanel rp = new ResultPanel();
	private ResultAction ra = new ResultAction(this);
	public ResultDialog(){
		this.setLf(lf);
		this.setSize(630, 270);
		this.setTitle("Ω·À„’Ωº®");
		this.add(rp);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.getRp().getExit().addActionListener(ra);
//		this.getRp().getRestart().addActionListener(ra);
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

	public ResultAction getRa() {
		return ra;
	}

	public void setRa(ResultAction ra) {
		this.ra = ra;
	}

	
}
