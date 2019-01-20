package com.qiu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.qiu.model.Poke;
import com.qiu.util.ActionOutPoke;
import com.qiu.util.OutPoke;
import com.qiu.util.Util;
import com.qiu.view.LoginFrame;

public class GameAction implements ActionListener {

	private LoginFrame lf;

	public GameAction(LoginFrame lf) {// 构造方法传参

		this.setLf(lf);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String order = e.getActionCommand();// 获取相应的指令
		if (order.equals("deal")) {
			Util.upsetPoke(Util.pokeList);
			Util.setKey(1);
			lf.getGf().getStartItem().setText("重发(D)");
			lf.getGf().getStartItem().setActionCommand("reDeal");

			// Util.show();
		} else if (order.equals("reDeal")) {// 重新发牌
			// 窗口监听,提示一下
			int key = JOptionPane.showConfirmDialog(lf.getGf(), "游戏正在进行,是否确定重新发牌?", "温馨提示",
					JOptionPane.OK_CANCEL_OPTION);
			if (key == JOptionPane.OK_OPTION) {
				Util.pokeList.clear();// 移除集合的所有元素
				Util.playerOne.getPlayerPoke().clear();
				Util.playerTwo.getPlayerPoke().clear();
				Util.playerThree.getPlayerPoke().clear();
				Util.playerOne.getOutPoke().clear();
				Util.playerTwo.getOutPoke().clear();
				Util.playerThree.getOutPoke().clear();
				OutPoke.temp.clear();
				OutPoke.index.clear();
				Util.landowner = 4;
				// 一切数据都要重置
				Util.initPoke();// 初始化数据
				Util.player_Poke = 0;// 发牌轮数重置为0
				Util.playerOne.setPoints(4);
				Util.playerTwo.setPoints(4);
				Util.playerThree.setPoints(4);
				Util.drawMark = 0;
				Util.flag = true;
				lf.getGf().getGamePanel().getShowPoke().setText("暗牌");
				Util.callPlayer = 0;
				Util.mark = 0;
				Util.hidePoints = 0;
				Util.isPoke = -1;
				Util.pitchOn = 0;
				Util.type = 0;
				OutPoke.score = 0;
				lf.getGf().getGamePanel().getTrusteeship().setText("托管");
				OutPoke.isTrusteeship = false;
				Util.upsetPoke(Util.pokeList);
				Util.setKey(1);
				// Util.reStart = true;
			}
		} else if (order.equals("selete")) {
			lf.getSd().setVisible(true);
		} else if (order.equals("score")) {
			lf.getScd().setVisible(true);
		} else if (order.equals("exit")) {
			// 窗口监听,提示一下
			int key = JOptionPane.showConfirmDialog(lf.getGf(), "真的要退出游戏吗?", "温馨提示", JOptionPane.OK_CANCEL_OPTION);
			if (key == JOptionPane.OK_OPTION) {
				lf.getGf().setVisible(false);
				lf.setVisible(true);
			}
		} else if (order.equals("rule")) {
			String ruleText = "发牌:\n" + "一副牌 54 张，一人 17 张，留 3 张做底牌，在确定地主之前玩家不" + "\n能看底牌，地主确定后，底牌亮出，底牌分给地主。"
					+ "\n第一个叫牌的玩家" + "\n翻一张地主牌，地主牌被谁拿到谁是地主。棋牌平台\n" + "上为第一轮叫牌的玩家由系统选定。\n" + "叫牌"
					+ "\n叫牌按出牌的顺序轮流进行，每人只能叫一次。叫牌时可以叫“1分”，" + "\n“2分”，“3分”，“不叫”。后叫牌者只能叫比前面玩家高的分或者不叫。"
					+ "\n叫牌结束后所叫分值最大的玩家为地主；如果有玩家叫“3分”则立即结" + "\n束叫牌，该玩家为地主；如果都不叫，则重新发牌，重新叫牌。" + "\n加倍"
					+ "\n叫牌结束后，玩家可选择“加倍”和“不加倍”。加倍后玩家输赢结算得分" + "\n翻倍，不加倍得分无倍率变化。" + "\n出牌"
					+ "\n将三张底牌交给地主，并亮出底牌让所有人都能看到。地主首先出牌，然" + "\n后按逆时针顺序依次出牌，轮到用户跟牌时，用户可以选择“不出”或出比"
					+ "\n上一个玩家大的牌。某一玩家出完牌时结束本局。";
			JOptionPane.showMessageDialog(lf.getGf(), ruleText, "规则说明", JOptionPane.CLOSED_OPTION);
		} else if (order.equals("about")) {
			String textAbout = "斗地主 1.0Bate\n(C)2018-1219  涅槃出品";
			JOptionPane.showMessageDialog(lf.getGf(), textAbout, "关于", JOptionPane.CLOSED_OPTION);
		} else if (order.equals("showPoke")) {
			if (Util.flag) {
				Util.flag = false;// 暗牌开关
				lf.getGf().getGamePanel().getShowPoke().setText("明牌");
			} else {
				Util.flag = true;// 明牌开关
				lf.getGf().getGamePanel().getShowPoke().setText("暗牌");
			}
		} else if (order.equals("oneMark")) {
			Util.drawMark = 1;
			Util.playerOne.setPoints(1);// 玩家叫1分
		} else if (order.equals("twoMark")) {
			Util.drawMark = 2;
			Util.playerOne.setPoints(2);// 玩家叫1分
		} else if (order.equals("threeMark")) {
			Util.drawMark = 3;
			Util.playerOne.setPoints(3);// 玩家叫1分
		} else if (order.equals("noMark")) {
			Util.drawMark = 4;
			Util.playerOne.setPoints(0);// 玩家叫1分
		} else if (order.equals("outPoke")) {
			if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() == 0) {// 电脑两玩家的出牌集合为空,说明自己出牌,可以重新定义牌型
				Util.type = 0;
			}
			if ((Util.type == 0 && Util.pitchOn == 1) || (Util.type == 1 && Util.pitchOn == 1)) {// 如果是地主,type就是为0.可以进来,不是地主的时候得根据前面一个人出牌的类型来出自己的牌
				if (Util.playerTwo.getOutPoke().size() != 0) {// 西家出牌集合不为空,说明有出牌
					ActionOutPoke.solo(Util.playerOne, Util.playerTwo);
				} else if (Util.playerThree.getOutPoke().size() != 0 && Util.playerTwo.getOutPoke().size() == 0) {
					ActionOutPoke.solo(Util.playerOne, Util.playerTwo);
				} else {// 不满足上面两个情况,说明现在就该自己出
					Util.type = 1;// 走1步骤
					Util.isPoke = 1;// 1就表示要的起
				}
			} else if ((Util.type == 0 && Util.pitchOn == 2) || (Util.type == 2 && Util.pitchOn == 2)) {// 如果是地主,type就是为0.可以进来,不是地主的时候得根据前面一个人出牌的类型来出自己的牌
				if (Util.playerTwo.getOutPoke().size() != 0) {// 上一家有出牌
					ActionOutPoke.doublePoke(Util.playerOne, Util.playerTwo);
				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {// 上一家没有出牌,下一家出了牌
					ActionOutPoke.doublePoke(Util.playerOne, Util.playerThree);
				} else {// 一定是自己出牌
					ArrayList<Poke> temp = new ArrayList<Poke>();// 临时一个集合,用来存放起来的两张牌
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {
						Poke p = Util.playerOne.getPlayerPoke().get(i);// 临时扑克
						if (p.getPokeY() == 485) {// 起来的两张牌的面值是不是一样
							temp.add(p);
						}
					}
					Util.pokeSort(temp);
					if (temp.get(0).getNumber() == temp.get(1).getNumber()) {// 临时扑克牌的面值一样时就是对子
						Util.type = 2;// 表明自己出了对子
						Util.isPoke = 1;// 有牌出
					} else if (temp.get(0).getNumber() == 100 && temp.get(1).getNumber() == 99) {// 王炸的牌型
						OutPoke.score *= 4;
						OutPoke.doubleBoomCount++;
						Util.type = 8;// 王炸的牌型
						Util.isPoke = 1;
					} else {// 否则表示出牌有误 ,起来的牌归位
						for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// 把牌全部归位
							Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				}

			} else if ((Util.type == 0 && Util.pitchOn == 3) || (Util.type == 3 && Util.pitchOn == 3)) {
				// 如果是地主,type就是为0.可以进来,不是地主的时候得根据前面一个人出牌的类型来出自己的牌
				if (Util.playerTwo.getOutPoke().size() != 0) {// 上一家出牌
					ActionOutPoke.triple(Util.playerOne, Util.playerTwo);
				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {// 上一家没有出牌子,下一家出牌
					ActionOutPoke.triple(Util.playerOne, Util.playerThree);
				} else {// 一定自己出牌
					ArrayList<Poke> temp = new ArrayList<Poke>();// 临时一个集合,用来存放起来的两张牌
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {
						Poke p = Util.playerOne.getPlayerPoke().get(i);// 临时扑克
						if (p.getPokeY() == 485) {// 起来的两张牌的面值是不是一样
							temp.add(p);
						}
					}
					if ((temp.get(0).getNumber() == temp.get(1).getNumber())
							&& (temp.get(0).getNumber() == temp.get(2).getNumber())) {// 第一张和第二张相同.并且和第三张相同
						Util.type = 3;// 表明自己出了三张一样的
						Util.isPoke = 1;// 有牌出
					} else {// 否则表示出牌有误 ,起来的牌归位
						for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// 把牌全部归位
							Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				}

			} else if ((Util.type == 0 && Util.pitchOn == 4) || (Util.type == 4 && Util.pitchOn == 4)) {// 三带一的牌型
				if (Util.playerTwo.getOutPoke().size() != 0) {// 上家有牌
					ActionOutPoke.tripleAndOne(Util.playerOne, Util.playerTwo);

				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {// 上家无牌,下家有牌
					ActionOutPoke.tripleAndOne(Util.playerOne, Util.playerThree);
				} else {// 另两家都没牌
					ArrayList<Poke> temp = new ArrayList<Poke>();// 临时集合
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// 遍历一下找出升起来的牌
						Poke p = Util.playerOne.getPlayerPoke().get(i);
						if (p.getPokeY() == 485) {
							temp.add(p);
						}
					}
					Util.pokeSort(temp);// 给临时集合排个序
					if ((temp.get(0).getNumber() == temp.get(1).getNumber())
							&& (temp.get(0).getNumber() == temp.get(2).getNumber())
							&& (temp.get(0).getNumber() != temp.get(3).getNumber())) {// 是一个三带一的牌
						Util.type = 4;
						Util.isPoke = 1;

					} else if ((temp.get(1).getNumber() == temp.get(2).getNumber())
							&& (temp.get(1).getNumber() == temp.get(3).getNumber())
							&& (temp.get(1).getNumber() != temp.get(0).getNumber())) {// 是三代一的牌
						Util.type = 4;
						Util.isPoke = 1;
					} else if (temp.get(0).getNumber() == temp.get(1).getNumber()
							&& temp.get(0).getNumber() == temp.get(2).getNumber()
							&& temp.get(0).getNumber() == temp.get(3).getNumber()) {// 四个一样的炸弹
						OutPoke.score *= 4;
						OutPoke.boomCount++;
						Util.type = 8;
						Util.isPoke = 1;
					} else {
						for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// 把牌全部归位
							Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				}

			} else if ((Util.type == 0 && Util.pitchOn == 5) || (Util.type == 5 && Util.pitchOn == 5)) {// 三带一对
				if (Util.playerTwo.getOutPoke().size() != 0) {// 前一家不为空
					ActionOutPoke.fivePoke(Util.playerOne, Util.playerTwo);

				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {// 前一家为空,后一家不为空
					ActionOutPoke.fivePoke(Util.playerOne, Util.playerThree);

				} else {
					ArrayList<Poke> temp = new ArrayList<Poke>();// 临时集合
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// 遍历一下找出升起来的牌
						Poke p = Util.playerOne.getPlayerPoke().get(i);
						if (p.getPokeY() == 485) {
							temp.add(p);
						}
					}
					Util.pokeSort(temp);// 给临时集合排个序
					if ((temp.get(0).getNumber() == temp.get(1).getNumber())
							&& (temp.get(0).getNumber() == temp.get(2).getNumber())
							&& (temp.get(0).getNumber() != temp.get(3).getNumber())
							&& (temp.get(3).getNumber() == temp.get(4).getNumber())) {// 是一个三带二的牌
						Util.type = 5;
						Util.isPoke = 1;
					} else if ((temp.get(2).getNumber() == temp.get(3).getNumber())
							&& (temp.get(2).getNumber() == temp.get(4).getNumber())
							&& (temp.get(2).getNumber() != temp.get(0).getNumber())
							&& (temp.get(0).getNumber() == temp.get(1).getNumber())) {// 是三代二的牌
						Util.type = 5;
						Util.isPoke = 1;
					} else if ((temp.get(0).getNumber() - temp.get(1).getNumber() == 1)
							&& (temp.get(0).getNumber() - temp.get(2).getNumber() == 2)
							&& (temp.get(0).getNumber() - temp.get(3).getNumber() == 3)
							&& (temp.get(0).getNumber() - temp.get(4).getNumber() == 4)) {// 顺子5张的时候
						Util.type = 6;
						Util.isPoke = 1;

					} else {
						for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// 把牌全部归位
							Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				}

			} else if ((Util.type == 0 && Util.pitchOn > 5) || (Util.type == 6 && Util.pitchOn >= 5)) {// 起来的牌数大于5
				if (Util.playerTwo.getOutPoke().size() != 0) {
					ActionOutPoke.beyondFivePoke(Util.playerOne, Util.playerTwo);
				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {
					ActionOutPoke.beyondFivePoke(Util.playerOne, Util.playerThree);
				} else {
					ArrayList<Poke> temp = new ArrayList<Poke>();// 临时集合
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// 遍历一下找出升起来的牌
						Poke p = Util.playerOne.getPlayerPoke().get(i);
						if (p.getPokeY() == 485) {
							temp.add(p);
						}
					}
					Util.pokeSort(temp);// 排个序确保没错
					boolean k = true;// 用于判断是不是顺子的返回值 1--顺子 2--双顺
					boolean b = false;// 判断是不是双顺
					// 顺子的判断
					for (int i = temp.size() - 1; i > 0; i--) {
						if (temp.get(i - 1).getNumber() - temp.get(i).getNumber() != 1) {// 不满足条件,坐标归零
							k = false;
						}
					}
					// 双顺的判断
					if (temp.size() % 2 == 0) {
						for (int i = temp.size() - 1; i > 2; i -= 2) {
							// 两张相同,接后的两张相同且比前两张大1
							if (temp.get(i).getNumber() == temp.get(i - 1).getNumber()
									&& temp.get(i - 2).getNumber() - temp.get(i).getNumber() == 1
									&& temp.get(i - 2).getNumber() == temp.get(i - 3).getNumber()) {
								b = true;
							} else {
								b = false;
								break;
							}
						}
					}
					if (k) {
						Util.type = 6;
						Util.isPoke = 1;
					}
					if (b) {
						Util.type = 7;
						Util.isPoke = 1;
					}
					if (k == false && b == false) {
						Util.setCoordinate(Util.playerOne.getPlayerPoke());
					}
				}
			} else if (Util.pitchOn == 4 || Util.pitchOn == 2) {
				if (Util.playerTwo.getOutPoke().size() != 0) {
					ActionOutPoke.boomPoke(Util.playerOne, Util.playerTwo);
				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {
					ActionOutPoke.boomPoke(Util.playerOne, Util.playerThree);

				}

			} else {
				for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// 把牌全部归位
					Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
				}
			}
			Util.pitchOn = 0;// 置0,待会儿重新使用
		} else if (order.equals("noOut")) {
			Util.isPoke = 0;// 要不起
		} else if (order.equals("back")) {
			//出去的牌按回复原来的位置
			Util.setCoordinate(Util.playerOne.getPlayerPoke());
		} else if (order.equals("trusteeship")) {
			if (OutPoke.isTrusteeship == false) {
				lf.getGf().getGamePanel().getTrusteeship().setText("取消托管");
				OutPoke.isTrusteeship = true;
			} else if (OutPoke.isTrusteeship = true) {
				lf.getGf().getGamePanel().getTrusteeship().setText("托管");
				OutPoke.isTrusteeship = false;
			}

		} else if (order.equals("OneceAgain")) {
			lf.getGf().getGamePanel().getRd().setVisible(false);
			Util.pokeList.clear();// 移除集合的所有元素
			Util.playerOne.getPlayerPoke().clear();
			Util.playerTwo.getPlayerPoke().clear();
			Util.playerThree.getPlayerPoke().clear();
			Util.playerOne.getOutPoke().clear();
			Util.playerTwo.getOutPoke().clear();
			Util.playerThree.getOutPoke().clear();
			OutPoke.temp.clear();
			OutPoke.index.clear();
			Util.landowner = 4;
			// 一切数据都要重置
			Util.initPoke();// 初始化数据
			Util.player_Poke = 0;// 发牌轮数重置为0
			Util.playerOne.setPoints(4);
			Util.playerTwo.setPoints(4);
			Util.playerThree.setPoints(4);
			Util.drawMark = 0;
			Util.flag = true;
			lf.getGf().getGamePanel().getShowPoke().setText("暗牌");
			Util.callPlayer = 0;
			Util.mark = 0;
			Util.hidePoints = 0;
			Util.isPoke = -1;
			Util.pitchOn = 0;
			Util.type = 0;
			OutPoke.score = 0;
			lf.getGf().getGamePanel().getTrusteeship().setText("托管");
			OutPoke.isTrusteeship = false;
			Util.upsetPoke(Util.pokeList);
			Util.setKey(1);
		} else if (order.equals("exitResult")) {
			lf.getGf().getGamePanel().getRd().setVisible(false);
		}else if(order.equals("reset")){
			Util.playerOne.setScore(1000);
			Util.playerTwo.setScore(1000);
			Util.playerThree.setScore(1000);
		}else if(order.equals("sure")){
			lf.getScd().setVisible(false);
		}
	}

	public LoginFrame getLf() {
		return lf;
	}

	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}

}
