package com.qiu.view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class SelectDialog extends JDialog {

	private LoginFrame lf;
	private JRadioButton slow = new JRadioButton("慢(S)");
	private JRadioButton normal = new JRadioButton("正常(N)");
	private JRadioButton fast = new JRadioButton("快(F)");
	private JCheckBox music = new JCheckBox("音效");
	private JLabel jl_PlayerOne = new JLabel("自己");
	private JLabel jl_PlayerTwo = new JLabel("东家");
	private JLabel jl_PlayerThree = new JLabel("西家");
	private ButtonGroup buttonGroup = new ButtonGroup();
//	private JTextField jtf_PlayerOne = new JTextField(lf.getPlayerOne().getName());
//	private JTextField jtf_PlayerTwo = new JTextField(lf.getPlayerTwo().getName());
//	private JTextField jtf_PlayerThree = new JTextField(lf.getPlayerThree().getName());
	private JButton sure = new JButton("确定");
	private JButton cancel = new JButton("取消");
	
	public SelectDialog(LoginFrame lf){
		this.lf = lf;
//		super(lf,"选项");
		this.setTitle("选项");
		this.setSize(300, 300);
		
		this.setLayout(new GridLayout(2, 2,10,10));//将对话框设计为两行两列的网格,两面板距离10像素
		//创建4个面板,分别放进网格中
		JPanel speed = new JPanel();
		JPanel sound = new JPanel();
		JPanel playerName = new JPanel();
		JPanel buttonPanel = new JPanel();
		//给每个面板添加边框和标题,使用BorderFactory工厂类生成带标题的边框对象
		speed.setBorder(BorderFactory.createTitledBorder("动画速度"));
		sound.setBorder(BorderFactory.createTitledBorder("音效"));
		playerName.setBorder(BorderFactory.createTitledBorder("玩家姓名"));
		//添加单选框
		buttonGroup.add(slow);
		buttonGroup.add(normal);
		buttonGroup.add(fast);
		speed.add(slow);
		speed.add(normal);
		speed.add(fast);
//		speed.add(buttonGroup);
		//添加复选框
		sound.add(music);
		//面板3上设置东西
		playerName.setLayout(null);
		jl_PlayerOne.setBounds(8, 20, 30, 25);
		jl_PlayerTwo.setBounds(8, 80, 30, 25);
		jl_PlayerThree.setBounds(8, 50, 30, 25);
		JTextField jtf_PlayerOne = new JTextField(lf.getPlayerOne().getName());
		JTextField jtf_PlayerTwo = new JTextField(lf.getPlayerTwo().getName());
		JTextField jtf_PlayerThree = new JTextField(lf.getPlayerThree().getName());
		jtf_PlayerOne.setBounds(39, 20, 90, 25);
		jtf_PlayerTwo.setBounds(39, 50, 90, 25);
		jtf_PlayerThree.setBounds(39, 80, 90, 25);
		playerName.add(jl_PlayerOne);
		playerName.add(jl_PlayerTwo);
		playerName.add(jl_PlayerThree);
		playerName.add(jtf_PlayerOne);
		playerName.add(jtf_PlayerTwo);
		playerName.add(jtf_PlayerThree);
		//面板4设置按钮
		buttonPanel.setLayout(null);
		sure.setBounds(40, 20, 60, 30);
		cancel.setBounds(40, 70, 60, 30);
		buttonPanel.add(sure);
		buttonPanel.add(cancel);
		//面板添加到窗体
		this.add(speed);
		this.add(sound);
		this.add(playerName);
		this.add(buttonPanel);
		
		this.setLocationRelativeTo(null);
	}

	public LoginFrame getLf() {
		return lf;
	}

	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}
	
}
