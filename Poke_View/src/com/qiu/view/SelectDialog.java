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
	private JCheckBox remain = new JCheckBox("记牌器");
	private JLabel jl_PlayerOne = new JLabel("自己");
	private JLabel jl_PlayerTwo = new JLabel("东家");
	private JLabel jl_PlayerThree = new JLabel("西家");
	private ButtonGroup buttonGroup = new ButtonGroup();
	// 创建4个面板,分别放进网格中
	private JPanel speed = new JPanel();
	private JPanel other = new JPanel();
	private JPanel playerName = new JPanel();
	private JPanel buttonPanel = new JPanel();
	
	private JTextField jtf_PlayerOne = new JTextField();
	private JTextField jtf_PlayerTwo = new JTextField();
	private JTextField jtf_PlayerThree = new JTextField();

	private JButton sure = new JButton("确定");
	private JButton cancel = new JButton("取消");

	public SelectDialog(LoginFrame lf) {
		this.lf = lf;
		// super(lf,"选项");
		this.setTitle("选项");
		this.setSize(300, 300);
		this.setResizable(false);

		this.setLayout(new GridLayout(2, 2, 10, 10));// 将对话框设计为两行两列的网格,两面板距离10像素
		// 给每个面板添加边框和标题,使用BorderFactory工厂类生成带标题的边框对象
		speed.setBorder(BorderFactory.createTitledBorder("动画速度"));
		other.setBorder(BorderFactory.createTitledBorder("其他"));
		playerName.setBorder(BorderFactory.createTitledBorder("玩家姓名"));
		// 添加单选框
		buttonGroup.add(slow);
		buttonGroup.add(normal);
		buttonGroup.add(fast);
		speed.setLayout(null);
		slow.setBounds(30, 30, 100, 25);
		normal.setBounds(30, 60, 100, 25);
		fast.setBounds(30, 90, 100, 25);
		normal.setSelected(true);
		speed.add(slow);
		speed.add(normal);
		speed.add(fast);
		//其他面板添加复选框和设置绝对坐标
		other.setLayout(null);
		remain.setSelected(true);
		remain.setBounds(30, 30, 100, 25);
		music.setSelected(true);
		music.setBounds(30, 60, 100, 25);
		other.add(music);
		other.add(remain);
		// 面板3上设置东西
		playerName.setLayout(null);
		jl_PlayerOne.setBounds(8, 20, 30, 25);
		jl_PlayerTwo.setBounds(8, 80, 30, 25);
		jl_PlayerThree.setBounds(8, 50, 30, 25);
		
		jtf_PlayerOne.setText(lf.getPlayerOne().getName());
		jtf_PlayerTwo.setText(lf.getPlayerTwo().getName());
		jtf_PlayerThree.setText(lf.getPlayerThree().getName());
		jtf_PlayerOne.setBounds(39, 20, 90, 25);
		jtf_PlayerTwo.setBounds(39, 50, 90, 25);
		jtf_PlayerThree.setBounds(39, 80, 90, 25);
		playerName.add(jl_PlayerOne);
		playerName.add(jl_PlayerTwo);
		playerName.add(jl_PlayerThree);
		playerName.add(jtf_PlayerOne);
		playerName.add(jtf_PlayerTwo);
		playerName.add(jtf_PlayerThree);
		// 面板4设置按钮
		buttonPanel.setLayout(null);
		sure.setBounds(40, 20, 60, 30);
		cancel.setBounds(40, 70, 60, 30);
		buttonPanel.add(sure);
		buttonPanel.add(cancel);
		// 面板添加到窗体
		this.add(speed);
		this.add(other);
		this.add(playerName);
		this.add(buttonPanel);

		this.setLocationRelativeTo(null);
		
		this.getSure().setActionCommand("sure");
		this.getCancel().setActionCommand("cancel");
		this.getMusic().setActionCommand("music");
		this.getRemain().setActionCommand("remain");
	}

	public LoginFrame getLf() {
		return lf;
	}

	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}

	public JRadioButton getSlow() {
		return slow;
	}

	public void setSlow(JRadioButton slow) {
		this.slow = slow;
	}

	public JRadioButton getNormal() {
		return normal;
	}

	public void setNormal(JRadioButton normal) {
		this.normal = normal;
	}

	public JRadioButton getFast() {
		return fast;
	}

	public void setFast(JRadioButton fast) {
		this.fast = fast;
	}

	public JCheckBox getMusic() {
		return music;
	}

	public void setMusic(JCheckBox music) {
		this.music = music;
	}

	public JTextField getJtf_PlayerOne() {
		return jtf_PlayerOne;
	}

	public void setJtf_PlayerOne(JTextField jtf_PlayerOne) {
		this.jtf_PlayerOne = jtf_PlayerOne;
	}

	public JTextField getJtf_PlayerTwo() {
		return jtf_PlayerTwo;
	}

	public void setJtf_PlayerTwo(JTextField jtf_PlayerTwo) {
		this.jtf_PlayerTwo = jtf_PlayerTwo;
	}

	public JTextField getJtf_PlayerThree() {
		return jtf_PlayerThree;
	}

	public void setJtf_PlayerThree(JTextField jtf_PlayerThree) {
		this.jtf_PlayerThree = jtf_PlayerThree;
	}

	public JButton getSure() {
		return sure;
	}

	public void setSure(JButton sure) {
		this.sure = sure;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}

	public JCheckBox getRemain() {
		return remain;
	}

	public void setRemain(JCheckBox remain) {
		this.remain = remain;
	}

}
