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
			// ���ڼ���,��ʾһ��
			int key = JOptionPane.showConfirmDialog(lf, "�Ƿ�ȷ���޸�?", "��ܰ��ʾ", JOptionPane.OK_CANCEL_OPTION);
			if (key == JOptionPane.OK_OPTION) {
				String oneName = lf.getSd().getJtf_PlayerOne().getText();
				if(oneName.length() == 0 || oneName.length() > 3){
					JOptionPane.showMessageDialog(lf, "�뽫���һ�����ֳ��ȿ�����1-3���ַ�֮��!");
				}else{
					Util.playerOne.setName(oneName);
				}
				String twoName = lf.getSd().getJtf_PlayerTwo().getText();
				if(twoName.length() == 0 || twoName.length() > 3){
					JOptionPane.showMessageDialog(lf, "�뽫��Ҷ������ֳ��ȿ�����1-3���ַ�֮��!");
				}else{
					Util.playerTwo.setName(twoName);
				}
				String threeName = lf.getSd().getJtf_PlayerThree().getText();
				if(threeName.length() == 0 || threeName.length() > 3){
					JOptionPane.showMessageDialog(lf, "�뽫����������ֳ��ȿ�����1-3���ַ�֮��!");
				}else{
					Util.playerThree.setName(threeName);
				}
				//��Ч����
				if(lf.getSd().getMusic().isSelected()){
					lf.getMs().loop();//���ֿ���
				}else{
					lf.getMs().stop();//����ֹͣ
				}
				//����ʱ������
				if(lf.getSd().getSlow().isSelected()){
					Util.time = 2000;
				}else if(lf.getSd().getNormal().isSelected()){
					Util.time = 1000;
				}else if(lf.getSd().getFast().isSelected()){
					Util.time = 500;
				}
				lf.getSd().setVisible(false);
			}	
		}else if(order.equals("cancel")){
			lf.getSd().getJtf_PlayerOne().setText(Util.playerOne.getName());
			lf.getSd().getJtf_PlayerTwo().setText(Util.playerTwo.getName());
			lf.getSd().getJtf_PlayerThree().setText(Util.playerThree.getName());
			if(Util.time == 2000){
				lf.getSd().getSlow().setSelected(true);
			}else if(Util.time == 1000){
				lf.getSd().getNormal().setSelected(true);
			}else if(Util.time == 500){
				lf.getSd().getFast().setSelected(true);
			}
			lf.getSd().setVisible(false);
		}else if(order.equals("music")){
			if(lf.getSd().getMusic().isSelected()){
				lf.getMs().loop();//���ֿ���
			}else{
				lf.getMs().stop();//����ֹͣ
			}
		}else if(order.equals("remain")){
			if(lf.getSd().getRemain().isSelected()){
				lf.getGf().getGamePanel().getRp().setVisible(true);
			}else{
				lf.getGf().getGamePanel().getRp().setVisible(false);
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
