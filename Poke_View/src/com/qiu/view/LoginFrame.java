package com.qiu.view;

import javax.swing.JFrame;

import com.qiu.controller.GameAction;
import com.qiu.controller.GameMouse;
import com.qiu.controller.GameWindow;
import com.qiu.controller.LoginAction;
import com.qiu.controller.LoginWindow;
import com.qiu.controller.SelectAction;
import com.qiu.model.Player;
import com.qiu.model.PlayerOne;
import com.qiu.model.PlayerThree;
import com.qiu.model.PlayerTwo;
import com.qiu.util.MusicServer;
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
	Player playerOne = new PlayerOne();
	Player playerTwo = new PlayerTwo();
	Player playerThree = new PlayerThree();
	SelectDialog sd = new SelectDialog(this);
	ScoreDialog scd = new ScoreDialog(this);
	MusicServer ms = new MusicServer();

	//��¼����Ĺ���
	public LoginFrame(){
		
		this.setSize(Util.LOGINFRAME_W, Util.LOGINFRAME_H);
		this.setTitle("��ӭ������Ϸ��ҳ");//�������
		this.setResizable(false);//���ɸ��Ĵ�С
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//�رշ�ʽ
		this.add(loginPanel);//�Ž����
		this.setLocationRelativeTo(null);//��ʾ��Ļ�м�
		
		this.addWindowListener(lw);//��Ӵ��ڼ���
		this.getGf().addWindowListener(gw);//��Ϸ������Ӵ��ڼ���
		this.getMs().loop();
		//��¼��ť��Ӷ�������
		this.getLoginPanel().getStart().addActionListener(la);
		this.getLoginPanel().getIntroduce().addActionListener(la);
		this.getLoginPanel().getExit().addActionListener(la);
		//��Ϸ���˵�����������
		this.getGf().getStartItem().addActionListener(ga);
		this.getGf().getSeleteItem().addActionListener(ga);
		this.getGf().getScoreItem().addActionListener(ga);
		this.getGf().getExitItem().addActionListener(ga);
		this.getGf().getRuleItem().addActionListener(ga);
		this.getGf().getAboutItem().addActionListener(ga);
		//��Ϸ���зּ���
		this.getGf().getGamePanel().getShowPoke().addActionListener(ga);
		this.getGf().getGamePanel().getOneMark().addActionListener(ga);
		this.getGf().getGamePanel().getTwoMark().addActionListener(ga);
		this.getGf().getGamePanel().getThreeMark().addActionListener(ga);
		this.getGf().getGamePanel().getNoMark().addActionListener(ga);
		//��Ϸ�����ư�ť����
		this.getGf().getGamePanel().getOutPoke().addActionListener(ga);
		this.getGf().getGamePanel().getNoOut().addActionListener(ga);
		this.getGf().getGamePanel().getBack().addActionListener(ga);
		this.getGf().getGamePanel().getTrusteeship().addActionListener(ga);
		//������
		this.getGf().getGamePanel().addMouseListener(gm);
		//ѡ�ť����
		this.getSd().getSure().addActionListener(sa);
		this.getSd().getCancel().addActionListener(sa);
		this.getSd().getMusic().addActionListener(sa);
		this.getSd().getRemain().addActionListener(sa);
		//��������˳����ؿ�����
		this.getGf().getGamePanel().getRd().getRp().getExit().addActionListener(ga);
		this.getGf().getGamePanel().getRd().getRp().getRestart().addActionListener(ga);
		//�÷�������
		this.getScd().getSp().getSure().addActionListener(ga);
		this.getScd().getSp().getReset().addActionListener(ga);
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


	public ScoreDialog getScd() {
		return scd;
	}


	public void setScd(ScoreDialog scd) {
		this.scd = scd;
	}


	public MusicServer getMs() {
		return ms;
	}


	public void setMs(MusicServer ms) {
		this.ms = ms;
	}

}
