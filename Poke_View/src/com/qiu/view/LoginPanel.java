package com.qiu.view;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.qiu.util.Util;

public class LoginPanel extends JPanel {
	
	private JButton start = new JButton("������Ϸ");//��ʼ��Ϸ��ť
	private JButton introduce = new JButton("��Ϸ����");//��ʼ��Ϸ��ť
	private JButton exit = new JButton("�˳���Ϸ");//��ʼ��Ϸ��ť

	//�������
	public LoginPanel(){
		
		this.setSize(Util.LOGINPANEL_W, Util.LOGINPANEL_H);//����С
		this.setLayout(null);//���Բ���
		//������Ϸ��ťλ��
		start.setBounds(Util.LOGINPANELBUTTON_W, Util.LOGINPANELSTARTBUTTON_H, Util.BUTTON_W, Util.BUTTON_H);
		this.add(start);
		//��Ϸ���ܰ�ťλ��
		introduce.setBounds(Util.LOGINPANELBUTTON_W, Util.LOGINPANELINTRODUCEBUTTON_H, Util.BUTTON_W, Util.BUTTON_H);
		this.add(introduce);
		//�˳���Ϸ��ťλ��
		exit.setBounds(Util.LOGINPANELBUTTON_W, Util.LOGINPANELEXITBUTTON_H, Util.BUTTON_W, Util.BUTTON_H);
		this.add(exit);
		
		//��¼��ť��ָ�����
		start.setActionCommand("start");
		introduce.setActionCommand("introduce");
		exit.setActionCommand("exit");
	}
	//���仭��
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//�ű���ͼ
		g.drawImage(Util.LOGIN, 0, 0, Util.LOGINPANEL_W, Util.LOGINPANEL_H, 0, 0, Util.LOGINPANEL_W, Util.LOGINPANEL_H, null);
		
		this.repaint();
	}

	public JButton getStart() {
		return start;
	}

	public void setStart(JButton start) {
		this.start = start;
	}

	public JButton getIntroduce() {
		return introduce;
	}

	public void setIntroduce(JButton introduce) {
		this.introduce = introduce;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}	
	
}
