package com.qiu.view;

import javax.swing.JFrame;

import com.qiu.controller.GameAction;
import com.qiu.controller.GameMouse;
import com.qiu.controller.GameWindow;
import com.qiu.controller.LoginAction;
import com.qiu.controller.LoginWindow;
import com.qiu.controller.ResultAction;
import com.qiu.controller.SelectAction;
import com.qiu.model.Player;
import com.qiu.model.PlayerOne;
import com.qiu.model.PlayerThree;
import com.qiu.model.PlayerTwo;
import com.qiu.util.Util;

public class LoginFrame extends JFrame {
	
	LoginPanel loginPanel = new LoginPanel();
	GameFrame gf = new GameFrame();
	LoginWindow lw = new LoginWindow(this);
	LoginAction la = new LoginAction(this);
	GameWindow gw = new GameWindow(this);
	GameAction ga = new GameAction(this);
	GameMouse gm = new GameMouse(this.getGf().getGamePanel());
	SelectAction sa = new SelectAction(this);
//	ResultDialog rd = new ResultDialog();
//	ResultAction ra = new ResultAction(rd);
	Player playerOne = new PlayerOne();
	Player playerTwo = new PlayerTwo();
	Player playerThree = new PlayerThree();
	SelectDialog sd = new SelectDialog(this);

	//登录界面的构建
	public LoginFrame(){
		
		this.setSize(Util.LOGINFRAME_W, Util.LOGINFRAME_H);
		this.setTitle("欢迎进入游戏首页");//窗体标题
		this.setResizable(false);//不可更改大小
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//关闭方式
		this.add(loginPanel);//放进面板
		this.setLocationRelativeTo(null);//显示屏幕中间
		
		this.addWindowListener(lw);//添加窗口监听
		this.getGf().addWindowListener(gw);//游戏界面添加窗口监听
		//登录按钮添加动作监听
		this.getLoginPanel().getStart().addActionListener(la);
		this.getLoginPanel().getIntroduce().addActionListener(la);
		this.getLoginPanel().getExit().addActionListener(la);
		//游戏面板菜单栏动作监听
		this.getGf().getStartItem().addActionListener(ga);
		this.getGf().getSeleteItem().addActionListener(ga);
		this.getGf().getScoreItem().addActionListener(ga);
		this.getGf().getExitItem().addActionListener(ga);
		this.getGf().getRuleItem().addActionListener(ga);
		this.getGf().getAboutItem().addActionListener(ga);
		this.getGf().getGamePanel().getShowPoke().addActionListener(ga);
		this.getGf().getGamePanel().getOneMark().addActionListener(ga);
		this.getGf().getGamePanel().getTwoMark().addActionListener(ga);
		this.getGf().getGamePanel().getThreeMark().addActionListener(ga);
		this.getGf().getGamePanel().getNoMark().addActionListener(ga);
		this.getGf().getGamePanel().getOutPoke().addActionListener(ga);
		this.getGf().getGamePanel().getNoOut().addActionListener(ga);
		this.getGf().getGamePanel().getHint().addActionListener(ga);
		this.getGf().getGamePanel().getTrusteeship().addActionListener(ga);
		this.getGf().getGamePanel().addMouseListener(gm);
		this.getSd().getSure().addActionListener(sa);
		this.getSd().getCancel().addActionListener(sa);
//		this.getRd().getRp().getExit().addActionListener(ra);
//		this.getRd().getRp().getRestart().addActionListener(ra);
		
	}
	
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}


	public LoginWindow getLw() {
		return lw;
	}


	public void setLw(LoginWindow lw) {
		this.lw = lw;
	}


	public GameFrame getGf() {
		return gf;
	}


	public void setGf(GameFrame gf) {
		this.gf = gf;
	}

	public SelectDialog getSd() {
		return sd;
	}


	public void setSd(SelectDialog sd) {
		this.sd = sd;
	}


	public Player getPlayerOne() {
		return playerOne;
	}


	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}


	public Player getPlayerTwo() {
		return playerTwo;
	}


	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}


	public Player getPlayerThree() {
		return playerThree;
	}


	public void setPlayerThree(Player playerThree) {
		this.playerThree = playerThree;
	}


	public GameMouse getGm() {
		return gm;
	}


	public void setGm(GameMouse gm) {
		this.gm = gm;
	}


//	public ResultDialog getRd() {
//		return rd;
//	}
//
//
//	public void setRd(ResultDialog rd) {
//		this.rd = rd;
//	}

}
