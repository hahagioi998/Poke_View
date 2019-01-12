package com.qiu.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.qiu.util.Util;

public class GameFrame extends JFrame {
	
	private GamePanel gamePanel = new GamePanel();
	
	private JMenuBar menuBar = new JMenuBar();//�˵���
	//�˵�
	private JMenu menuGame = new JMenu("��Ϸ(G)");
	private JMenu menuHelp = new JMenu("����(H)");
	//�˵���
	private JMenuItem startItem = new JMenuItem("����(D)");
	private JMenuItem seleteItem = new JMenuItem("ѡ��(O)");
	private JMenuItem scoreItem = new JMenuItem("�÷�(S)");
	private JMenuItem exitItem = new JMenuItem("�˳�(X)");
	private JMenuItem ruleItem = new JMenuItem("����(R)");
	private JMenuItem aboutItem = new JMenuItem("����(A)");

	//����������
	public GameFrame(){
		
		this.setSize(Util.MAINFRAME_W,Util.MAINFRAME_H);//�����С
		this.setTitle("������");//�������
		this.setResizable(false);//���ɸ��Ĵ�С
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//�رշ�ʽ
		this.add(gamePanel);//�������������
		
		//�˵�������װ
		menuGame.add(startItem);
		menuGame.add(seleteItem);
		menuGame.add(scoreItem);
		menuGame.add(exitItem);
		menuHelp.add(ruleItem);
		menuHelp.add(aboutItem);
		
		menuBar.add(menuGame);
		menuBar.add(menuHelp);
		
		this.setJMenuBar(menuBar);
		this.setLocationRelativeTo(null);//����ʾ��Ļ�м�
		
		//��Ʋ˵���ָ��
		startItem.setActionCommand("deal");
//		startItem.setActionCommand("reDeal");
		seleteItem.setActionCommand("selete");
		scoreItem.setActionCommand("score");
		exitItem.setActionCommand("exit");
		ruleItem.setActionCommand("rule");
		aboutItem.setActionCommand("about");		
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public JMenuItem getStartItem() {
		return startItem;
	}

	public void setStartItem(JMenuItem startItem) {
		this.startItem = startItem;
	}

	public JMenuItem getSeleteItem() {
		return seleteItem;
	}

	public void setSeleteItem(JMenuItem seleteItem) {
		this.seleteItem = seleteItem;
	}

	public JMenuItem getScoreItem() {
		return scoreItem;
	}

	public void setScoreItem(JMenuItem scoreItem) {
		this.scoreItem = scoreItem;
	}

	public JMenuItem getExitItem() {
		return exitItem;
	}

	public void setExitItem(JMenuItem exitItem) {
		this.exitItem = exitItem;
	}

	public JMenuItem getRuleItem() {
		return ruleItem;
	}

	public void setRuleItem(JMenuItem ruleItem) {
		this.ruleItem = ruleItem;
	}

	public JMenuItem getAboutItem() {
		return aboutItem;
	}

	public void setAboutItem(JMenuItem aboutItem) {
		this.aboutItem = aboutItem;
	}


	
}
