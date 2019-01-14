package com.qiu.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.qiu.controller.GameMouse;
import com.qiu.model.Player;
import com.qiu.model.PlayerOne;
import com.qiu.model.PlayerThree;
import com.qiu.model.PlayerTwo;
import com.qiu.model.Poke;
import com.qiu.util.OutPoke;
import com.qiu.util.Util;

public class GamePanel extends JPanel {

	private JButton showPoke = new JButton("����");
	private JButton oneMark = new JButton("1  ��");
	private JButton twoMark = new JButton("2  ��");
	private JButton threeMark = new JButton("3  ��");
	private JButton noMark = new JButton("��  ��");
	private JButton outPoke = new JButton("��  ��");
	private JButton noOut = new JButton("��  ��");
	private JButton hint = new JButton("��  ʾ");
	private JButton trusteeship = new JButton("��  ��");

	// private GameMouse gm = new GameMouse(this);

	public GamePanel() {

		this.setSize(Util.MAINFRAME_W, Util.MAINFRAME_H);// ��Ϸ���Ĵ�С
		this.setLayout(null);// ���Բ���
		// ���ư�ť����

		// ��ťָ��
		showPoke.setActionCommand("showPoke");
		oneMark.setActionCommand("oneMark");
		twoMark.setActionCommand("twoMark");
		threeMark.setActionCommand("threeMark");
		noMark.setActionCommand("noMark");
		outPoke.setActionCommand("outPoke");
		noOut.setActionCommand("noOut");
		hint.setActionCommand("hint");
		trusteeship.setActionCommand("trusteeship");

	}

	// ������ɻ���
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Util.GAME, 0, 0, Util.MAINFRAME_W, Util.MAINFRAME_H, 0, 0, Util.MAINFRAME_W, Util.MAINFRAME_H,
				null);
		// ���ͷ�����ʾ
		g.drawImage(Util.P_ONE, 160, 510, 240, 590, 0, 0, 80, 80, null);
		g.drawImage(Util.P_TWO, 45, 5, 125, 85, 0, 0, 80, 80, null);
		g.drawImage(Util.P_THREE, 870, 5, 950, 85, 0, 0, 80, 80, null);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		g.setColor(Color.RED);
		g.drawString(new PlayerOne().getName(), 162, 631);
		g.drawString(new PlayerTwo().getName(), 135, 30);
		g.drawString(new PlayerThree().getName(), 800, 30);
		// ���ǳ�����Щ��ť
		outPoke.setBounds(310, 440, 70, 40);
		noOut.setBounds(390, 440, 70, 40);
		hint.setBounds(470, 440, 70, 40);
		trusteeship.setBounds(550, 440, 70, 40);
		this.add(outPoke);
		this.add(noOut);
		this.add(hint);
		this.add(trusteeship);
		outPoke.setVisible(false);
		noOut.setVisible(false);
		hint.setVisible(false);
		trusteeship.setVisible(false);

		if (Util.key == 1) {
			if (Util.landowner == 4) {
				g.drawString("���·���...", 370, 320);
			}
			oneMark.setVisible(false);
			twoMark.setVisible(false);
			threeMark.setVisible(false);
			noMark.setVisible(false);
			showPoke.setBounds(790, 600, 80, 40);// ���ư�ť�Ĵ�С
			this.add(showPoke);
			for (int i = 0; i < Util.pokeList.size() - 3; i++) {// 54���ƿ�ʼ����ʾ������
				g.drawImage(Util.POKE_BACK, 320 + (i * 5), 160, 320 + (i * 5) + Util.POKE_W, 160 + Util.POKE_H, 0, 0,
						108, 148, null);
			}
			for (int k = 0; k < Util.playerOne.getPlayerPoke().size(); k++) {// ���һ���ƿ�ʼ����ʾ������
				Poke p = Util.playerOne.getPlayerPoke().get(k);
				g.drawImage(Util.playerOne.getPlayerPoke().get(k).getImage(), p.getPokeX(), p.getPokeY(),
						p.getPokeX() + Util.POKE_W, p.getPokeY() + Util.POKE_H, 0, 0, 108, 148, null);
			}
			// ���ƺͰ���֮����л�
			if (Util.flag) {
				for (int k = 0; k < Util.playerTwo.getPlayerPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
					g.drawImage(Util.playerTwo.getPlayerPoke().get(k).getImage(), 45, 90 + (k * 20), 45 + Util.POKE_W,
							90 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
				}
				for (int k = 0; k < Util.playerThree.getPlayerPoke().size(); k++) {// ��������ƿ�ʼ����ʾ������
					g.drawImage(Util.playerThree.getPlayerPoke().get(k).getImage(), 877, 90 + (k * 20),
							877 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
				}
			} else {
				for (int k = 0; k < Util.playerTwo.getPlayerPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
					g.drawImage(Util.POKE_BACK, 45, 90 + (k * 20), 45 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0, 0,
							108, 148, null);
				}
				for (int k = 0; k < Util.playerThree.getPlayerPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
					g.drawImage(Util.POKE_BACK, 877, 90 + (k * 20), 877 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0,
							0, 108, 148, null);
				}
			}
			// ����
			if (Util.pokeList.size() == 3) {

				for (int i = 0; i < Util.pokeList.size(); i++) {// 54���ƿ�ʼ����ʾ������
					g.drawImage(Util.POKE_BACK, 400 + (i * 80), 160, 400 + (i * 80) + Util.POKE_W, 160 + Util.POKE_H, 0,
							0, 108, 148, null);
				}
			}
		}
		if (Util.key == 2) {
			for (int i = 0; i < Util.pokeList.size() - 3; i++) {// 54���ƿ�ʼ����ʾ������
				g.drawImage(Util.POKE_BACK, 320 + (i * 5), 160, 320 + (i * 5) + Util.POKE_W, 160 + Util.POKE_H, 0, 0,
						108, 148, null);
			}
			for (int k = 0; k < Util.playerOne.getPlayerPoke().size(); k++) {// ���һ���ƿ�ʼ����ʾ������
				Poke p = Util.playerOne.getPlayerPoke().get(k);
				g.drawImage(Util.playerOne.getPlayerPoke().get(k).getImage(), p.getPokeX(), p.getPokeY(),
						p.getPokeX() + Util.POKE_W, p.getPokeY() + Util.POKE_H, 0, 0, 108, 148, null);
			}
			// ���ƺͰ���֮����л�
			if (Util.flag) {
				for (int k = 0; k < Util.playerTwo.getPlayerPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
					g.drawImage(Util.playerTwo.getPlayerPoke().get(k).getImage(), 45, 90 + (k * 20), 45 + Util.POKE_W,
							90 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
				}
				for (int k = 0; k < Util.playerThree.getPlayerPoke().size(); k++) {// ��������ƿ�ʼ����ʾ������
					g.drawImage(Util.playerThree.getPlayerPoke().get(k).getImage(), 877, 90 + (k * 20),
							877 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
				}
			} else {
				for (int k = 0; k < Util.playerTwo.getPlayerPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
					g.drawImage(Util.POKE_BACK, 45, 90 + (k * 20), 45 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0, 0,
							108, 148, null);
				}
				for (int k = 0; k < Util.playerThree.getPlayerPoke().size(); k++) {// ��������ƿ�ʼ����ʾ������
					g.drawImage(Util.POKE_BACK, 877, 90 + (k * 20), 877 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0,
							0, 108, 148, null);
				}
			}
			// ����
			if (Util.pokeList.size() == 3) {
				for (int i = 0; i < Util.pokeList.size(); i++) {// 54���ƿ�ʼ����ʾ������
					g.drawImage(Util.POKE_BACK, 400 + (i * 80), 160, 400 + (i * 80) + Util.POKE_W, 160 + Util.POKE_H, 0,
							0, 108, 148, null);
				}
			}
			// ���ýзְ�ť������Ͳ��ɼ�
			oneMark.setBounds(310, 440, 70, 40);
			twoMark.setBounds(390, 440, 70, 40);
			threeMark.setBounds(470, 440, 70, 40);
			noMark.setBounds(550, 440, 70, 40);
			this.add(oneMark);
			this.add(twoMark);
			this.add(threeMark);
			this.add(noMark);
			oneMark.setVisible(false);
			twoMark.setVisible(false);
			threeMark.setVisible(false);
			noMark.setVisible(false);
			if (Util.callPlayer == 0) {// ���һ(�Լ�)�е���
				// �Լ��е�����ʱ��,��ť�ÿɼ�
				if (Util.hidePoints == 1) {
					oneMark.setVisible(false);
					twoMark.setVisible(true);
				} else if (Util.hidePoints == 2) {
					oneMark.setVisible(false);
					twoMark.setVisible(false);
				} else {
					oneMark.setVisible(true);
					twoMark.setVisible(true);
				}
				threeMark.setVisible(true);
				noMark.setVisible(true);
				if (Util.drawMark == 1) {
					g.drawString("1  ��", 665, 480);
					// ���갴ť,��ťҪ���ɼ�
					oneMark.setVisible(false);
					twoMark.setVisible(false);
					threeMark.setVisible(false);
					noMark.setVisible(false);
				} else if (Util.drawMark == 2) {
					g.drawString("2  ��", 665, 480);
					oneMark.setVisible(false);
					twoMark.setVisible(false);
					threeMark.setVisible(false);
					noMark.setVisible(false);
				} else if (Util.drawMark == 3) {
					g.drawString("3  ��", 665, 480);
				} else if (Util.drawMark == 4) {
					g.drawString("��  ��", 665, 480);
					oneMark.setVisible(false);
					twoMark.setVisible(false);
					threeMark.setVisible(false);
					noMark.setVisible(false);
				}
			} else if (Util.callPlayer == 1) {// ���ҽе���
				if (Util.drawMark == 5) {
					g.drawString("3 ��", 800, 70);
				} else if (Util.drawMark == 6) {
					g.drawString("2 ��", 800, 70);
				} else if (Util.drawMark == 7) {
					g.drawString("1 ��", 800, 70);
				} else if (Util.drawMark == 8) {
					g.drawString("����", 800, 70);
				}
			} else if (Util.callPlayer == 2) {// ���ҽе���
				if (Util.drawMark == 9) {
					g.drawString("3 ��", 135, 70);
				} else if (Util.drawMark == 10) {
					g.drawString("2 ��", 135, 70);
				} else if (Util.drawMark == 11) {
					g.drawString("1 ��", 135, 70);
				} else if (Util.drawMark == 12) {
					g.drawString("����", 135, 70);
				}
			}
		}
		if (Util.key == 3) {
			oneMark.setVisible(false);
			twoMark.setVisible(false);
			threeMark.setVisible(false);
			noMark.setVisible(false);
			for (int i = 0; i < Util.pokeList.size() - 3; i++) {// 54���ƿ�ʼ����ʾ������
				g.drawImage(Util.POKE_BACK, 320 + (i * 5), 160, 320 + (i * 5) + Util.POKE_W, 160 + Util.POKE_H, 0, 0,
						108, 148, null);
			}
			for (int k = 0; k < Util.playerOne.getPlayerPoke().size(); k++) {// ���һ���ƿ�ʼ����ʾ������
				Poke p = Util.playerOne.getPlayerPoke().get(k);
				g.drawImage(Util.playerOne.getPlayerPoke().get(k).getImage(), p.getPokeX(), p.getPokeY(),
						p.getPokeX() + Util.POKE_W, p.getPokeY() + Util.POKE_H, 0, 0, 108, 148, null);
			}
			// ���ƺͰ���֮����л�
			if (Util.flag) {
				for (int k = 0; k < Util.playerTwo.getPlayerPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
					g.drawImage(Util.playerTwo.getPlayerPoke().get(k).getImage(), 45, 90 + (k * 20), 45 + Util.POKE_W,
							90 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
				}
				for (int k = 0; k < Util.playerThree.getPlayerPoke().size(); k++) {// ��������ƿ�ʼ����ʾ������
					g.drawImage(Util.playerThree.getPlayerPoke().get(k).getImage(), 877, 90 + (k * 20),
							877 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
				}
			} else {
				for (int k = 0; k < Util.playerTwo.getPlayerPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
					g.drawImage(Util.POKE_BACK, 45, 90 + (k * 20), 45 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0, 0,
							108, 148, null);
				}
				for (int k = 0; k < Util.playerThree.getPlayerPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
					g.drawImage(Util.POKE_BACK, 877, 90 + (k * 20), 877 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0,
							0, 108, 148, null);
				}
			}
			// ����
			if (Util.pokeList.size() == 3) {
				// ��������ʾ����
				for (int i = 0; i < Util.pokeList.size(); i++) {// 54���ƿ�ʼ����ʾ������
					g.drawImage(Util.pokeList.get(i).getImage(), 400 + (i * 80), 160, 400 + (i * 80) + Util.POKE_W,
							160 + Util.POKE_H, 0, 0, 108, 148, null);
				}
			}
			// �жϳ�˭�ǵ���,ֱ�ӻ���
			if (Util.landowner == 1) {// �Լ�����
				g.drawString("�� ��", 665, 480);
			} else if (Util.landowner == 2) {// ���ҵ���
				g.drawString("�� ��", 135, 70);
			} else if (Util.landowner == 3) {// ���ҵ���
				g.drawString("�� ��", 800, 70);
			}

		} else if (Util.key == 4) {
			// ����
			if (Util.pokeList.size() == 3) {
				// ��������ʾ����
				for (int i = 0; i < Util.pokeList.size(); i++) {// 54���ƿ�ʼ����ʾ������
					g.drawImage(Util.pokeList.get(i).getImage(), 400 + (i * 80), 130, 400 + (i * 80) + Util.POKE_W,
							130 + Util.POKE_H, 0, 0, 108, 148, null);
				}
			}
			for (int k = 0; k < Util.playerOne.getPlayerPoke().size(); k++) {// ���һ���ƿ�ʼ����ʾ������
				Poke p = Util.playerOne.getPlayerPoke().get(k);
				g.drawImage(Util.playerOne.getPlayerPoke().get(k).getImage(), p.getPokeX(), p.getPokeY(),
						p.getPokeX() + Util.POKE_W, p.getPokeY() + Util.POKE_H, 0, 0, 108, 148, null);
			}
			// ���ƺͰ���֮����л�
			if (Util.flag) {
				for (int k = 0; k < Util.playerTwo.getPlayerPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
					g.drawImage(Util.playerTwo.getPlayerPoke().get(k).getImage(), 45, 90 + (k * 20), 45 + Util.POKE_W,
							90 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
				}
				for (int k = 0; k < Util.playerThree.getPlayerPoke().size(); k++) {// ��������ƿ�ʼ����ʾ������
					g.drawImage(Util.playerThree.getPlayerPoke().get(k).getImage(), 877, 90 + (k * 20),
							877 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
				}
			} else {
				for (int k = 0; k < Util.playerTwo.getPlayerPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
					g.drawImage(Util.POKE_BACK, 45, 90 + (k * 20), 45 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0, 0,
							108, 148, null);
				}
				for (int k = 0; k < Util.playerThree.getPlayerPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
					g.drawImage(Util.POKE_BACK, 877, 90 + (k * 20), 877 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0,
							0, 108, 148, null);
				}
			}
			g.drawString(OutPoke.score + "  ��", 460, 260);
			// �жϳ�˭�ǵ���,ֱ�ӻ���
			if (Util.landowner == 1) {
				g.drawString("�� ��", 665, 480);
			} else if (Util.landowner == 2) {
				g.drawString("�� ��", 135, 70);
			} else if (Util.landowner == 3) {
				g.drawString("�� ��", 800, 70);
			}
			// ��ť���ɼ�
			outPoke.setVisible(false);
			noOut.setVisible(false);
			hint.setVisible(false);
			trusteeship.setVisible(false);
			// �ֵ�˭����
			if (Util.callPlayer == 0) {// �Լ�����
				if(Util.isPoke == 3){
					g.drawString("Ҫ����!", 130, 330);
				}
				outPoke.setVisible(true);
				if (Util.playerTwo.getOutPoke().size() == 0 
						&& Util.playerThree.getOutPoke().size() == 0) {// ���Һ����ҵĳ��ƶ����϶�Ϊ��,���ò����İ�ť���ɼ�
					noOut.setVisible(false);
				} else {
					noOut.setVisible(true);
				}
				hint.setVisible(true);
				trusteeship.setVisible(true);
				// �������ҳ���������Ȼ�ɼ�
				if (Util.playerThree.getOutPoke().size() != 0) {
					for (int i = 0; i < Util.playerThree.getOutPoke().size(); i++) {
						g.drawImage(Util.playerThree.getOutPoke().get(i).getImage(), 800, 150 + (i * 20),
								800 + Util.POKE_W, 150 + (i * 20) + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
				if (Util.playerTwo.getOutPoke().size() != 0) {
					for (int k = 0; k < Util.playerTwo.getOutPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
						g.drawImage(Util.playerTwo.getOutPoke().get(k).getImage(), 122, 150 + (k * 20),
								122 + Util.POKE_W, 150 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
				if (Util.playerOne.getOutPoke().size() != 0) {// ��ʾ���һ���Ƽ���������
					for (int k = 0; k < Util.playerOne.getOutPoke().size(); k++) {// ���һ���ƿ�ʼ����ʾ������
						g.drawImage(Util.playerOne.getOutPoke().get(k).getImage(), 400 + (k * 25), 390,
								400 + (k * 25) + Util.POKE_W, 390 + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
			} else if (Util.callPlayer == 1) {// �����ȳ���
				if(Util.isPoke == 0){
					g.drawString("Ҫ����!", 400, 440);
				}
				outPoke.setVisible(false);
				noOut.setVisible(false);
				hint.setVisible(false);
				trusteeship.setVisible(false);
				if (Util.playerThree.getOutPoke().size() != 0) {
					for (int i = 0; i < Util.playerThree.getOutPoke().size(); i++) {
						g.drawImage(Util.playerThree.getOutPoke().get(i).getImage(), 800, 150 + (i * 20),
								800 + Util.POKE_W, 150 + (i * 20) + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
				if (Util.playerTwo.getOutPoke().size() != 0) {
					for (int k = 0; k < Util.playerTwo.getOutPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
						g.drawImage(Util.playerTwo.getOutPoke().get(k).getImage(), 122, 150 + (k * 20),
								122 + Util.POKE_W, 150 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
				if (Util.playerOne.getOutPoke().size() != 0) {
					for (int k = 0; k < Util.playerOne.getOutPoke().size(); k++) {// ���һ���ƿ�ʼ����ʾ������
						g.drawImage(Util.playerOne.getOutPoke().get(k).getImage(), 400 + (k * 25), 390,
								400 + (k * 25) + Util.POKE_W, 390 + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
			} else if (Util.callPlayer == 2) {// ���ҳ���
				if(Util.isPoke == 2){
					g.drawString("Ҫ����!", 750, 320);
				}
				outPoke.setVisible(false);
				noOut.setVisible(false);
				hint.setVisible(false);
				trusteeship.setVisible(false);
				if (Util.playerTwo.getOutPoke().size() != 0) {
					for (int k = 0; k < Util.playerTwo.getOutPoke().size(); k++) {// ��Ҷ����ƿ�ʼ����ʾ������
						g.drawImage(Util.playerTwo.getOutPoke().get(k).getImage(), 122, 150 + (k * 20),
								122 + Util.POKE_W, 150 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
				if (Util.playerThree.getOutPoke().size() != 0) {
					for (int i = 0; i < Util.playerThree.getOutPoke().size(); i++) {
						g.drawImage(Util.playerThree.getOutPoke().get(i).getImage(), 800, 150 + (i * 20),
								800 + Util.POKE_W, 150 + (i * 20) + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
				if (Util.playerOne.getOutPoke().size() != 0) {
					for (int k = 0; k < Util.playerOne.getOutPoke().size(); k++) {// ���һ���ƿ�ʼ����ʾ������
						g.drawImage(Util.playerOne.getOutPoke().get(k).getImage(), 400 + (k * 25), 390,
								400 + (k * 25) + Util.POKE_W, 390 + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
			}
		}

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.repaint();
	}

	public JButton getShowPoke() {
		return showPoke;
	}

	public void setShowPoke(JButton showPoke) {
		this.showPoke = showPoke;
	}

	public JButton getOneMark() {
		return oneMark;
	}

	public void setOneMark(JButton oneMark) {
		this.oneMark = oneMark;
	}

	public JButton getTwoMark() {
		return twoMark;
	}

	public void setTwoMark(JButton twoMark) {
		this.twoMark = twoMark;
	}

	public JButton getThreeMark() {
		return threeMark;
	}

	public void setThreeMark(JButton threeMark) {
		this.threeMark = threeMark;
	}

	public JButton getNoMark() {
		return noMark;
	}

	public void setNoMark(JButton noMark) {
		this.noMark = noMark;
	}

	public JButton getOutPoke() {
		return outPoke;
	}

	public void setOutPoke(JButton outPoke) {
		this.outPoke = outPoke;
	}

	public JButton getNoOut() {
		return noOut;
	}

	public void setNoOut(JButton noOut) {
		this.noOut = noOut;
	}

	public JButton getHint() {
		return hint;
	}

	public void setHint(JButton hint) {
		this.hint = hint;
	}

	public JButton getTrusteeship() {
		return trusteeship;
	}

	public void setTrusteeship(JButton trusteeship) {
		this.trusteeship = trusteeship;
	}

}
