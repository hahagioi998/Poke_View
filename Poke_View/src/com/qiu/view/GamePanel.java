package com.qiu.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.qiu.controller.GameMouse;
import com.qiu.model.Player;
import com.qiu.model.PlayerOne;
import com.qiu.model.PlayerThree;
import com.qiu.model.PlayerTwo;
import com.qiu.model.Poke;
import com.qiu.util.OutPoke;
import com.qiu.util.Util;

public class GamePanel extends JPanel {

	private RemainPoke rp = new RemainPoke();// 记牌器
	private JButton showPoke = new JButton("暗牌");
	private JButton oneMark = new JButton("1  分");
	private JButton twoMark = new JButton("2  分");
	private JButton threeMark = new JButton("3  分");
	private JButton noMark = new JButton("不  叫");
	private JButton outPoke = new JButton("出  牌");
	private JButton noOut = new JButton("不  出");
	private JButton back = new JButton("恢  复");
	private JButton trusteeship = new JButton("托  管");
	private ResultDialog rd = new ResultDialog();

	public GamePanel() {

		this.setSize(Util.MAINFRAME_W, Util.MAINFRAME_H);// 游戏面板的大小
		this.setLayout(null);// 绝对布局
		// 明牌按钮设置

		// 按钮指令
		showPoke.setActionCommand("showPoke");
		oneMark.setActionCommand("oneMark");
		twoMark.setActionCommand("twoMark");
		threeMark.setActionCommand("threeMark");
		noMark.setActionCommand("noMark");
		outPoke.setActionCommand("outPoke");
		noOut.setActionCommand("noOut");
		back.setActionCommand("back");
		trusteeship.setActionCommand("trusteeship");

		this.add(rp);// 放记牌器画板
	}

	// 将面板变成画板
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Util.GAME, 0, 0, Util.MAINFRAME_W, Util.MAINFRAME_H, 0, 0, Util.MAINFRAME_W, Util.MAINFRAME_H,
				null);
		// 玩家头像的显示
		g.drawImage(Util.P_ONE, 160, 510, 240, 590, 0, 0, 80, 80, null);
		g.drawImage(Util.P_TWO, 45, 5, 125, 85, 0, 0, 80, 80, null);
		g.drawImage(Util.P_THREE, 870, 5, 950, 85, 0, 0, 80, 80, null);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		g.setColor(Color.RED);
		g.drawString(Util.playerOne.getName(), 162, 631);
		g.drawString(Util.playerTwo.getName(), 135, 30);
		g.drawString(Util.playerThree.getName(), 775, 30);
		// 放那出牌那些按钮
		outPoke.setBounds(310, 440, 70, 40);
		noOut.setBounds(390, 440, 70, 40);
		back.setBounds(470, 440, 70, 40);
		trusteeship.setBounds(660, 600, 90, 40);
		this.add(outPoke);
		this.add(noOut);
		this.add(back);
		this.add(trusteeship);
		outPoke.setVisible(false);
		noOut.setVisible(false);
		back.setVisible(false);
		trusteeship.setVisible(false);

		if (Util.key == 1) {
			if (Util.landowner == 4) {
				g.drawString("重新发牌...", 370, 320);
			}
			oneMark.setVisible(false);
			twoMark.setVisible(false);
			threeMark.setVisible(false);
			noMark.setVisible(false);
			showPoke.setBounds(790, 600, 80, 40);// 明牌按钮的大小
			this.add(showPoke);
			for (int i = 0; i < Util.pokeList.size() - 3; i++) {// 54张牌开始就显示在桌面
				g.drawImage(Util.POKE_BACK, 320 + (i * 5), 160, 320 + (i * 5) + Util.POKE_W, 160 + Util.POKE_H, 0, 0,
						108, 148, null);
			}
			showPlayerOnePoke(g);
			showAndHide(g);
		}
		if (Util.key == 2) {
			showPlayerOnePoke(g);
			showAndHide(g);
			hidePoke(g);

			oneMark.setBounds(310, 440, 70, 40);
			twoMark.setBounds(390, 440, 70, 40);
			threeMark.setBounds(470, 440, 70, 40);
			noMark.setBounds(550, 440, 70, 40);
			this.add(oneMark);
			this.add(twoMark);
			this.add(threeMark);
			this.add(noMark);

			showPointButton();
			if (Util.callPlayer == 0) {// 玩家一(自己)叫地主
				// 自己叫地主的时候,按钮得可见
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
					g.drawString("1  分", 665, 480);
					// 点完按钮,按钮要不可见
					showPointButton();
				} else if (Util.drawMark == 2) {
					g.drawString("2  分", 665, 480);
					showPointButton();
				} else if (Util.drawMark == 3) {
					g.drawString("3  分", 665, 480);
				} else if (Util.drawMark == 4) {
					g.drawString("不  叫", 665, 480);
					showPointButton();
				}
			} else if (Util.callPlayer == 1) {// 东家叫地主
				if (Util.drawMark == 5) {
					g.drawString("3 分", 800, 70);
				} else if (Util.drawMark == 6) {
					g.drawString("2 分", 800, 70);
				} else if (Util.drawMark == 7) {
					g.drawString("1 分", 800, 70);
				} else if (Util.drawMark == 8) {
					g.drawString("不叫", 800, 70);
				}
			} else if (Util.callPlayer == 2) {// 西家叫地主
				if (Util.drawMark == 9) {
					g.drawString("3 分", 135, 70);
				} else if (Util.drawMark == 10) {
					g.drawString("2 分", 135, 70);
				} else if (Util.drawMark == 11) {
					g.drawString("1 分", 135, 70);
				} else if (Util.drawMark == 12) {
					g.drawString("不叫", 135, 70);
				}
			}
		}
		if (Util.key == 3) {
			showPointButton();
			for (int i = 0; i < Util.pokeList.size(); i++) {// 底牌显示在桌面
				g.drawImage(Util.pokeList.get(i).getImage(), 400 + (i * 80), 160, 400 + (i * 80) + Util.POKE_W,
						160 + Util.POKE_H, 0, 0, 108, 148, null);
			}
			showPlayerOnePoke(g);// 自己的牌
			showAndHide(g);// 左右两边的牌
			
			// 判断出谁是地主,直接画上
			if (Util.landowner == 1) {// 自己地主
				g.drawString("地 主", 665, 480);
			} else if (Util.landowner == 2) {// 西家地主
				g.drawString("地 主", 135, 70);
			} else if (Util.landowner == 3) {// 东家地主
				g.drawString("地 主", 800, 70);
			}

		} else if (Util.key == 4) {
			// 托管提示
			if (OutPoke.isTrusteeship) {
				g.drawString("托管", 760, 480);
			}
			// 底牌
			if (Util.pokeList.size() == 3) {
				// 将底牌显示出来
				for (int i = 0; i < Util.pokeList.size(); i++) {// 54张牌开始就显示在桌面
					g.drawImage(Util.pokeList.get(i).getImage(), 400 + (i * 80), 130, 400 + (i * 80) + Util.POKE_W,
							130 + Util.POKE_H, 0, 0, 108, 148, null);
				}
			}
			showPlayerOnePoke(g);
			// 明牌和暗牌之间的切换
			showAndHide(g);
			g.drawString(OutPoke.score + "  倍", 460, 260);
			// 判断出谁是地主,直接画上
			if (Util.landowner == 1) {
//				g.drawString("地 主", 665, 480);
				g.drawImage(Util.LANDOWNER, 665, 420, 725, 480, 0, 0, 60, 60,null);
				g.drawImage(Util.FARMER, 135, 70, 195, 130, 0, 0, 60, 60,null);
				g.drawImage(Util.FARMER, 800, 70, 860, 130, 0, 0, 60, 60,null);
			} else if (Util.landowner == 2) {
//				g.drawString("地 主", 135, 70);
				g.drawImage(Util.FARMER, 665, 420, 725, 480, 0, 0, 60, 60,null);
				g.drawImage(Util.LANDOWNER, 135, 70, 195, 130, 0, 0, 60, 60,null);
				g.drawImage(Util.FARMER, 800, 70, 860, 130, 0, 0, 60, 60,null);
			} else if (Util.landowner == 3) {
//				g.drawString("地 主", 800, 70);
				g.drawImage(Util.FARMER, 665, 420, 725, 480, 0, 0, 60, 60,null);
				g.drawImage(Util.FARMER, 135, 70, 195, 130, 0, 0, 60, 60,null);
				g.drawImage(Util.LANDOWNER, 800, 70, 860, 130, 0, 0, 60, 60,null);
			}
			// 按钮不可见
			showOutPokeButton();
			trusteeship.setVisible(true);// 托管按钮一直可见
			// 轮到谁出牌
			if (Util.callPlayer == 0) {// 自己出牌
				if (OutPoke.isTrusteeship) {// 托管开启后,出牌按钮那些不可见
					showOutPokeButton();					
					if (Util.isPoke == 2) {
						g.drawString("要不起!", 160, 320);// 给上一家画要不起
					}
				} else {
					if (Util.isPoke == 2) {
						g.drawString("要不起!", 160, 320);// 给上一家画要不起
					}
					g.drawImage(Util.TIMEDOWN, 760, 415, 820, 475, 0, 0, 60, 60, null);
					g.drawString(Util.timeDown+"", 773, 455);
					outPoke.setVisible(true);
					if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() == 0) {// 东家和西家的出牌都集合都为空,就让不出的按钮不可见
						noOut.setVisible(false);
					} else {
						noOut.setVisible(true);
					}
					back.setVisible(true);
				}
				// 另外两家出牌区域依然可见
				if (Util.playerThree.getOutPoke().size() != 0) {
					for (int i = 0; i < Util.playerThree.getOutPoke().size(); i++) {
						g.drawImage(Util.playerThree.getOutPoke().get(i).getImage(), 800, 150 + (i * 20),
								800 + Util.POKE_W, 150 + (i * 20) + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
				if (Util.playerTwo.getOutPoke().size() != 0) {
					for (int k = 0; k < Util.playerTwo.getOutPoke().size(); k++) {// 玩家二的牌开始就显示在桌面
						g.drawImage(Util.playerTwo.getOutPoke().get(k).getImage(), 122, 150 + (k * 20),
								122 + Util.POKE_W, 150 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
				if (Util.playerOne.getOutPoke().size() != 0) {// 表示玩家一出牌集合里有牌
					for (int k = 0; k < Util.playerOne.getOutPoke().size(); k++) {// 玩家一的牌开始就显示在桌面
						g.drawImage(Util.playerOne.getOutPoke().get(k).getImage(), 400 + (k * 25), 390,
								400 + (k * 25) + Util.POKE_W, 390 + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
			} else if (Util.callPlayer == 1) {// 东家先出牌
				if (Util.isPoke == 0) {
					g.drawString("要不起!", 400, 440);// 给上一家画要不起
				}
				showOutPokeButton();
				if (Util.playerThree.getOutPoke().size() != 0) {
					for (int i = 0; i < Util.playerThree.getOutPoke().size(); i++) {
						g.drawImage(Util.playerThree.getOutPoke().get(i).getImage(), 800, 150 + (i * 20),
								800 + Util.POKE_W, 150 + (i * 20) + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
				if (Util.playerTwo.getOutPoke().size() != 0) {
					for (int k = 0; k < Util.playerTwo.getOutPoke().size(); k++) {// 玩家二的牌开始就显示在桌面
						g.drawImage(Util.playerTwo.getOutPoke().get(k).getImage(), 122, 150 + (k * 20),
								122 + Util.POKE_W, 150 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
				if (Util.playerOne.getOutPoke().size() != 0) {
					for (int k = 0; k < Util.playerOne.getOutPoke().size(); k++) {// 玩家一的牌开始就显示在桌面
						g.drawImage(Util.playerOne.getOutPoke().get(k).getImage(), 400 + (k * 25), 390,
								400 + (k * 25) + Util.POKE_W, 390 + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
			} else if (Util.callPlayer == 2) {// 西家出牌
				if (Util.isPoke == 2) {
					g.drawString("要不起!", 750, 320);// 给上一家画要不起
				}
				showOutPokeButton();
				if (Util.playerTwo.getOutPoke().size() != 0) {
					for (int k = 0; k < Util.playerTwo.getOutPoke().size(); k++) {// 玩家二的牌开始就显示在桌面
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
					for (int k = 0; k < Util.playerOne.getOutPoke().size(); k++) {// 玩家一的牌开始就显示在桌面
						g.drawImage(Util.playerOne.getOutPoke().get(k).getImage(), 400 + (k * 25), 390,
								400 + (k * 25) + Util.POKE_W, 390 + Util.POKE_H, 0, 0, 108, 148, null);
					}
				}
			}
		}else if(Util.key == 5){
			String s = "游戏结束,正在清理战场...";
			g.drawString(s, 370, 320);
			this.getShowPoke().setVisible(false);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(Util.key == 7){
			rd.setVisible(true);
			Util.key = 0;
		}

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.repaint();
	}

	public void showPlayerOnePoke(Graphics g) {// 显示玩家一的手牌
		for (int k = 0; k < Util.playerOne.getPlayerPoke().size(); k++) {// 玩家一的牌开始就显示在桌面
			Poke p = Util.playerOne.getPlayerPoke().get(k);
			g.drawImage(Util.playerOne.getPlayerPoke().get(k).getImage(), p.getPokeX(), p.getPokeY(),
					p.getPokeX() + Util.POKE_W, p.getPokeY() + Util.POKE_H, 0, 0, 108, 148, null);
		}
	}

	public void showAndHide(Graphics g) {// 明暗牌显示
		// 明牌和暗牌之间的切换
		if (Util.flag) {
			for (int k = 0; k < Util.playerTwo.getPlayerPoke().size(); k++) {// 玩家二的牌开始就显示在桌面
				g.drawImage(Util.playerTwo.getPlayerPoke().get(k).getImage(), 45, 90 + (k * 20), 45 + Util.POKE_W,
						90 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
			}
			for (int k = 0; k < Util.playerThree.getPlayerPoke().size(); k++) {// 玩家三的牌开始就显示在桌面
				g.drawImage(Util.playerThree.getPlayerPoke().get(k).getImage(), 877, 90 + (k * 20), 877 + Util.POKE_W,
						90 + (k * 20) + Util.POKE_H, 0, 0, 108, 148, null);
			}
		} else {
			for (int k = 0; k < Util.playerTwo.getPlayerPoke().size(); k++) {// 玩家二的牌开始就显示在桌面
				g.drawImage(Util.POKE_BACK, 45, 90 + (k * 20), 45 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0, 0, 108,
						148, null);
			}
			for (int k = 0; k < Util.playerThree.getPlayerPoke().size(); k++) {// 玩家二的牌开始就显示在桌面
				g.drawImage(Util.POKE_BACK, 877, 90 + (k * 20), 877 + Util.POKE_W, 90 + (k * 20) + Util.POKE_H, 0, 0,
						108, 148, null);
			}
		}
	}

	// 底牌的显示
	public void hidePoke(Graphics g) {
		if (Util.pokeList.size() == 3) {
			for (int i = 0; i < Util.pokeList.size(); i++) {// 54张牌开始就显示在桌面
				g.drawImage(Util.POKE_BACK, 400 + (i * 80), 160, 400 + (i * 80) + Util.POKE_W, 160 + Util.POKE_H, 0, 0,
						108, 148, null);
			}
		}
	}

	// 叫分按钮不可见设置
	public void showPointButton() {
		oneMark.setVisible(false);
		twoMark.setVisible(false);
		threeMark.setVisible(false);
		noMark.setVisible(false);
	}

	// 出牌按钮的不显示
	public void showOutPokeButton() {
		outPoke.setVisible(false);
		noOut.setVisible(false);
		back.setVisible(false);
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

	public JButton getTrusteeship() {
		return trusteeship;
	}

	public void setTrusteeship(JButton trusteeship) {
		this.trusteeship = trusteeship;
	}

	public RemainPoke getRp() {
		return rp;
	}

	public void setRp(RemainPoke rp) {
		this.rp = rp;
	}

	public ResultDialog getRd() {
		return rd;
	}

	public void setRd(ResultDialog rd) {
		this.rd = rd;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}

}
