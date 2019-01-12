package com.qiu.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.qiu.util.Util;

public class GameFrame extends JFrame {
	
	private GamePanel gamePanel = new GamePanel();
	
	private JMenuBar menuBar = new JMenuBar();//菜单栏
	//菜单
	private JMenu menuGame = new JMenu("游戏(G)");
	private JMenu menuHelp = new JMenu("帮助(H)");
	//菜单项
	private JMenuItem startItem = new JMenuItem("发牌(D)");
	private JMenuItem seleteItem = new JMenuItem("选项(O)");
	private JMenuItem scoreItem = new JMenuItem("得分(S)");
	private JMenuItem exitItem = new JMenuItem("退出(X)");
	private JMenuItem ruleItem = new JMenuItem("规则(R)");
	private JMenuItem aboutItem = new JMenuItem("关于(A)");

	//构建主窗体
	public GameFrame(){
		
		this.setSize(Util.MAINFRAME_W,Util.MAINFRAME_H);//窗体大小
		this.setTitle("斗地主");//窗体标题
		this.setResizable(false);//不可更改大小
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//关闭方式
		this.add(gamePanel);//添加主窗体的面板
		
		//菜单栏的组装
		menuGame.add(startItem);
		menuGame.add(seleteItem);
		menuGame.add(scoreItem);
		menuGame.add(exitItem);
		menuHelp.add(ruleItem);
		menuHelp.add(aboutItem);
		
		menuBar.add(menuGame);
		menuBar.add(menuHelp);
		
		this.setJMenuBar(menuBar);
		this.setLocationRelativeTo(null);//在显示屏幕中间
		
		//设计菜单栏指令
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
