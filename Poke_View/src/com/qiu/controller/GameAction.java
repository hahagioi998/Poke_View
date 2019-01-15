package com.qiu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.qiu.model.Poke;
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

		String order = e.getActionCommand();//获取相应的指令
		if(order.equals("deal")){
			Util.upsetPoke(Util.pokeList);
			Util.setKey(1);
			lf.getGf().getStartItem().setText("重发(D)");
			lf.getGf().getStartItem().setActionCommand("reDeal");
			
//			Util.show();
		}else if(order.equals("reDeal")){//重新发牌
			// 窗口监听,提示一下
			int key = JOptionPane.showConfirmDialog(lf.getGf(), "游戏正在进行,是否确定重新发牌?", "温馨提示", JOptionPane.OK_CANCEL_OPTION);
			if (key == JOptionPane.OK_OPTION) {
				Util.pokeList.clear();//移除集合的所有元素
				Util.playerOne.getPlayerPoke().clear();
				Util.playerTwo.getPlayerPoke().clear();
				Util.playerThree.getPlayerPoke().clear();
				Util.playerOne.getOutPoke().clear();
				Util.playerTwo.getOutPoke().clear();
				Util.playerThree.getOutPoke().clear();
				Util.landowner = 4;
				//一切数据都要重置
				Util.initPoke();//初始化数据
				Util.player_Poke = 0;//发牌轮数重置为0
				Util.playerOne.setPoints(4);
				Util.playerTwo.setPoints(4);
				Util.playerThree.setPoints(4);
				Util.drawMark = 0;
				Util.flag = true;
				Util.callPlayer = 0;
				Util.mark = 0;
				Util.hidePoints = 0;
//				lf.getGf().getGamePanel().removeMouseListener(lf.getGm());
				
				Util.upsetPoke(Util.pokeList);
				Util.setKey(1);
//				Util.reStart = true;
			}
		}else if(order.equals("selete")){
			lf.getSd().setVisible(true);
		}else if(order.equals("score")){
			System.out.println("ccc");
		}else if(order.equals("exit")){
			// 窗口监听,提示一下
			int key = JOptionPane.showConfirmDialog(lf.getGf(), "真的要退出游戏吗?", "温馨提示", JOptionPane.OK_CANCEL_OPTION);
			if (key == JOptionPane.OK_OPTION) {
				lf.getGf().setVisible(false);
				lf.setVisible(true);
			}
		}else if(order.equals("rule")){
			String ruleText = "发牌:\n"
					+ "一副牌 54 张，一人 17 张，留 3 张做底牌，在确定地主之前玩家不"
					+ "\n能看底牌，地主确定后，底牌亮出，底牌分给地主。"
					+ "\n第一个叫牌的玩家"
					+ "\n翻一张地主牌，地主牌被谁拿到谁是地主。棋牌平台\n"
					+ "上为第一轮叫牌的玩家由系统选定。\n"
					+ "叫牌"
					+ "\n叫牌按出牌的顺序轮流进行，每人只能叫一次。叫牌时可以叫“1分”，"
					+ "\n“2分”，“3分”，“不叫”。后叫牌者只能叫比前面玩家高的分或者不叫。"
					+ "\n叫牌结束后所叫分值最大的玩家为地主；如果有玩家叫“3分”则立即结"
					+ "\n束叫牌，该玩家为地主；如果都不叫，则重新发牌，重新叫牌。"
					+ "\n加倍"
					+ "\n叫牌结束后，玩家可选择“加倍”和“不加倍”。加倍后玩家输赢结算得分"
					+ "\n翻倍，不加倍得分无倍率变化。"
					+ "\n出牌"
					+ "\n将三张底牌交给地主，并亮出底牌让所有人都能看到。地主首先出牌，然"
					+ "\n后按逆时针顺序依次出牌，轮到用户跟牌时，用户可以选择“不出”或出比"
					+ "\n上一个玩家大的牌。某一玩家出完牌时结束本局。";
			JOptionPane.showMessageDialog(lf.getGf(), ruleText, "规则说明", JOptionPane.CLOSED_OPTION);
		}else if(order.equals("about")){
			String textAbout = "斗地主 1.0Bate\n(C)2018-1219  涅槃出品";
			JOptionPane.showMessageDialog(lf.getGf(), textAbout, "关于", JOptionPane.CLOSED_OPTION);
		}else if(order.equals("showPoke")){			
			if(Util.flag){
				Util.flag = false;//暗牌开关
				lf.getGf().getGamePanel().getShowPoke().setText("明牌");
			}else{
				Util.flag = true;//明牌开关
				lf.getGf().getGamePanel().getShowPoke().setText("暗牌");
			}
		}else if(order.equals("oneMark")){
			Util.drawMark = 1;
			Util.playerOne.setPoints(1);//玩家叫1分
		}else if(order.equals("twoMark")){
			Util.drawMark = 2;
			Util.playerOne.setPoints(2);//玩家叫1分
		}else if(order.equals("threeMark")){
			Util.drawMark = 3;
			Util.playerOne.setPoints(3);//玩家叫1分
		}else if(order.equals("noMark")){
			Util.drawMark = 4;
			Util.playerOne.setPoints(0);//玩家叫1分
		}else if(order.equals("outPoke")){
			System.out.println("有几张牌起来:" + Util.pitchOn);
			if(Util.pitchOn == 1){//只有一张牌
				if(Util.playerTwo.getOutPoke().size() != 0){//西家出牌集合不为空,说明有出牌
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {
						if(Util.playerOne.getPlayerPoke().get(i).getPokeY() == 485){//找着那张起来的牌
							if(Util.playerOne.getPlayerPoke().get(i).getNumber() 
									> Util.playerTwo.getOutPoke().get(0).getNumber()){//比较大小
								Util.isPoke = 1;//1就表示要的起
							}else{
								Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
								Util.isPoke = -1;//表示选择不对,重新出牌
							}
							break;
						}
					}
				}else if(Util.playerThree.getOutPoke().size() != 0 
						&& Util.playerTwo.getOutPoke().size() == 0){
					//东家的出牌集合不为空,西家的出牌集合为空,说明西家要不起,这时就要判断自家和东家的牌
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {
						if(Util.playerOne.getPlayerPoke().get(i).getPokeY() == 485){//找着那张起来的牌
							if(Util.playerOne.getPlayerPoke().get(i).getNumber() 
									> Util.playerThree.getOutPoke().get(0).getNumber()){//比较大小
								Util.isPoke = 1;//1就表示要的起
							}else{
								Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
								Util.isPoke = -1;//表示选择不对,重新出牌
							}
							break;
						}
					}
				}else{//不满足上面两个情况,说明现在就该自己出
					Util.isPoke = 1;//1就表示要的起
				}
			}else{
				Util.pitchOn = 0;//置0,待会儿重新使用
				for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {//把牌全部归位
					Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
				}
			}
			Util.pitchOn = 0;//置0,待会儿重新使用
		}else if(order.equals("noOut")){
				Util.isPoke = 0;//要不起
		}else if(order.equals("hint")){
			//如果东西家的出牌集合都为空,说明现在要自己出牌
			if(Util.playerTwo.getOutPoke().size() == 0 
					&& Util.playerThree.getOutPoke().size() == 0){
				ArrayList<Poke> temp = Util.playerOne.getPlayerPoke();//简化代码
				temp.get(temp.size() - 1).setPokeY(485);//将自己手牌最小的一位弹起
				Util.pitchOn ++;
			}else if(Util.playerTwo.getOutPoke().size() != 0){//说明西家出了牌
				//遍历一下,将自己的手牌从最小的地方开始遍历
				for (int i = Util.playerOne.getPlayerPoke().size() - 1; i >= 0; i--) {
					Poke p = Util.playerOne.getPlayerPoke().get(i);
					//这个牌会大于西家出牌集合中最小的一位就弹起来
					if(p.getNumber() > Util.playerTwo.getOutPoke().get(0).getNumber()){
						p.setPokeY(485);
						Util.pitchOn ++;
						break;
					}
				}
				//如果没有找到就说明要不起
				if(Util.pitchOn == 0){
					Util.isPoke = 0;
				}
			}else if(Util.playerTwo.getOutPoke().size() == 0 
					&& Util.playerThree.getOutPoke().size() != 0){//西家出牌集合为空,并且东家出牌集合不为空,说明西家要不起东家的牌
				//遍历一下,将自己的手牌从最小的地方开始遍历
				for (int i = Util.playerOne.getPlayerPoke().size() - 1; i >= 0; i--) {
					Poke p = Util.playerOne.getPlayerPoke().get(i);
					//这个牌会大于东家出牌集合中最小的一位就弹起来
					if(p.getNumber() > Util.playerThree.getOutPoke().get(0).getNumber()){
						p.setPokeY(485);
						Util.pitchOn ++;
						break;//找到牌就弹出
					}
				}
				//如果没有找到就说明要不起
				if(Util.pitchOn == 0){
					Util.isPoke = 0;
				}
			}
		}else if(order.equals("trusteeship")){
			if(OutPoke.isTrusteeship == false){
				lf.getGf().getGamePanel().getTrusteeship().setText("取消托管");
				OutPoke.isTrusteeship = true;
			}else if(OutPoke.isTrusteeship = true){
				lf.getGf().getGamePanel().getTrusteeship().setText("托管");
				OutPoke.isTrusteeship = false;
			}
			
		}
	}
	public LoginFrame getLf() {
		return lf;
	}

	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}

	
}
