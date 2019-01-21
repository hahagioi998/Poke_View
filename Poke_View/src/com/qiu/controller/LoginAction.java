package com.qiu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.qiu.view.GameFrame;
import com.qiu.view.LoginFrame;

public class LoginAction implements ActionListener {

	private LoginFrame lf;

	public LoginAction(LoginFrame lf) {// ���췽������

		this.setLf(lf);

	}
	//��������
	@Override
	public void actionPerformed(ActionEvent e) {

		String order = e.getActionCommand();//��ťָ��Ļ�ȡ
		if(order.equals("start")){
			lf.getGf().setVisible(true);//��Ϸ����ɼ�
			lf.setVisible(false);//��¼���治�ɼ�
			new DealThread(lf).start();
		}else if(order.equals("introduce")){
			String text = "������������������ȫ����һ���˿���Ϸ,\n�淨��,������ǿ,���ٽ��ˡ��ݴ�\n�����ľ���ᣬ������������޶�\n"
					+ "����������Ϊ�˷�й�Ե�����ʹ�ޣ���\n����һ�������֮��һ���˹�������\n����������������Ϸ�������棬��һ����"
					+ "\n�����ƣ�����Ϊһ������������Ϊ��һ\n����˫����ս���ȳ����Ƶ�һ����ʤ��";
			JOptionPane.showMessageDialog(lf, text,"��Ϸ����",JOptionPane.CLOSED_OPTION);
		}else if(order.equals("exit")){
			// ���ڼ���,��ʾһ��
			int key = JOptionPane.showConfirmDialog(lf, "���Ҫ�뿪��?", "��ܰ��ʾ", JOptionPane.OK_CANCEL_OPTION);
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
