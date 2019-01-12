package com.qiu.controller;

import com.qiu.model.Poke;
import com.qiu.util.Util;
import com.qiu.view.LoginFrame;

public class DealThread extends Thread {

//	private GameMouse gm = new GameMouse(lf.getGf().getGamePanel());
	LoginFrame lf;
	public DealThread(LoginFrame lf){
		this.lf = lf;
	}
	
	@Override
	public void run() {

		while (true) {

			if (Util.getKey() == 1) {// 当key值为1的时候就开始发牌
//				Util.reStart = false;//主要用于游戏一半的时候重新开始
				while (Util.player_Poke < Util.PLAYERE_POKE) {// 发牌的轮数
					Util.player_Poke++;// 每发一次就自增1
					// 给每个玩家发牌,并每发一次牌就排序
					for (int j = 0; j < Util.player.length; j++) {
						Util.player[j].getPlayerPoke().add(Util.pokeList.get(0));
						Util.pokeList.remove(0);
						Util.pokeSort(Util.playerOne);
						Util.setCoordinate(Util.playerOne.getPlayerPoke());
						Util.pokeSort(Util.playerTwo);
						Util.pokeSort(Util.playerThree);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
				Util.callPlayers();// 调用随机玩家叫地主的方法
				Util.key = 2;// 把key值改为2,开始在叫地主环节

			}else if (Util.key == 2) {// 叫地主环节
				if (Util.callPlayer == 0) {// 自己叫地主
					if (Util.drawMark == 1) {
						// 休眠两秒
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (Util.playerThree.getPoints() > Util.playerOne.getPoints()) {// 初始化分数为4分,确保可以开始第一轮
							Util.callPlayer = 1;
						} else {
							Util.callPlayer = 2;
						}
					} else if (Util.drawMark == 2) {
						// 休眠两秒
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (Util.playerThree.getPoints() > Util.playerOne.getPoints()) {// 初始化分数为4分,确保可以开始第一轮
							Util.callPlayer = 1;
						} else {
							Util.callPlayer = 2;
						}
					} else if (Util.drawMark == 3) {// 自己直接叫3分需休眠一下
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// 结束叫地主
						Util.landowner = 1;//玩家自己是地主
					} else if (Util.drawMark == 4) {
						// 休眠两秒
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (Util.playerThree.getPoints() > Util.playerOne.getPoints()) {// 初始化分数为4分,确保可以开始第一轮
							Util.callPlayer = 1;
						} else {
							Util.callPlayer = 2;
						}
					}
				} else if (Util.callPlayer == 1) {
					Util.callPoints();
					if (Util.playerThree.getPoints() <= Util.playerOne.getPoints()) {
						if(Util.playerOne.getPoints() != 4){//在自己的叫分不等于4的情况下,说明自己已经叫过分了
							Util.playerThree.setPoints(0);//自己叫过分,东家比自己小的就不叫分
							Util.drawMark = 8;//东家不叫分
						}
					}
					if (Util.drawMark == 5) {// 自己直接叫3分需休眠一下
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// 结束叫地主
						Util.landowner = 3;//玩家自己是地主
					}
					// 休眠两秒
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (Util.playerThree.getPoints() == 1) {
						Util.hidePoints = 1;// 一分按钮不可见
					} else if (Util.playerThree.getPoints() == 2) {
						Util.hidePoints = 2;// 一分按钮和二分按钮都不可见
					}
					if (Util.playerTwo.getPoints() > Util.playerThree.getPoints()) {
						Util.callPlayer = 2;
					} else {
						Util.callPlayer = 0;
					}
					

				} else if (Util.callPlayer == 2) {
					Util.callPoints();
					if (Util.playerTwo.getPoints() <= Util.playerThree.getPoints()) {//获取的分数小于前一位,直接跳过
						if(Util.playerThree.getPoints() != 4){//东家在叫了分的前提下
							Util.playerTwo.setPoints(0);
							Util.drawMark = 12;//西家不叫分
						}
					}
					if (Util.drawMark == 9) {// 自己直接叫3分需休眠一下
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// 结束叫地主
						Util.landowner = 2;//玩家自己是地主
					}
					// 休眠两秒
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (Util.playerTwo.getPoints() == 1) {
						Util.hidePoints = 1;// 一分按钮不可见
					} else if (Util.playerTwo.getPoints() == 2) {
						Util.hidePoints = 2;// 一分按钮和二分按钮都不可见
					}
					if (Util.playerOne.getPoints() > Util.playerTwo.getPoints()) {
						Util.callPlayer = 0;
					} else {
						Util.callPlayer = 1;
					}

				}
				// 当玩家的points的值不为4的时候,说明都叫地主都一轮结束了
				if (Util.playerOne.getPoints() != 4 && Util.playerTwo.getPoints() != 4
						&& Util.playerThree.getPoints() != 4) {
					if (Util.playerOne.getPoints() > Util.playerTwo.getPoints()
							&& Util.playerOne.getPoints() > Util.playerThree.getPoints()) {
						Util.landowner = 1;// 表示玩家一地主
						Util.key = 3;
					} else if (Util.playerTwo.getPoints() > Util.playerOne.getPoints()
							&& Util.playerTwo.getPoints() > Util.playerThree.getPoints()) {
						Util.landowner = 2;// 表示玩家二地主
						Util.key = 3;
					} else if (Util.playerThree.getPoints() > Util.playerOne.getPoints()
							&& Util.playerThree.getPoints() > Util.playerTwo.getPoints()) {
						Util.landowner = 3;// 表示玩家三地主
						Util.key = 3;
						
					}else if(Util.playerOne.getPoints() == 0 && Util.playerTwo.getPoints() == 0 
							&& Util.playerThree.getPoints() == 0){//都不叫地主
						
						Util.pokeList.clear();//移除集合的所有元素
						Util.playerOne.getPlayerPoke().clear();
						Util.playerTwo.getPlayerPoke().clear();
						Util.playerThree.getPlayerPoke().clear();
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
						Util.upsetPoke(Util.pokeList);
						Util.setKey(1);
					}
				}
			}else if(Util.key == 3){
				try {//休眠两秒钟,让底牌有一个显示的时间
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(Util.landowner == 1){//玩家一地主
//					while(Util.pokeList.size() != 0){//把底牌分给地主
//						Util.playerOne.getPlayerPoke().add(Util.pokeList.get(0));
//						Util.pokeList.remove(0);
//					}
					//底牌接着显示
					for(int i = 0; i < Util.pokeList.size(); i++){
						Util.playerOne.getPlayerPoke().add(Util.pokeList.get(i));
					}
					Util.pokeSort(Util.playerOne);//排序
					Util.setCoordinate(Util.playerOne.getPlayerPoke());
				}else if(Util.landowner == 2){
//					while(Util.pokeList.size() != 0){
//						Util.playerTwo.getPlayerPoke().add(Util.pokeList.get(0));
//						Util.pokeList.remove(0);
//					}
					for(int i = 0; i < Util.pokeList.size(); i++){
						Util.playerTwo.getPlayerPoke().add(Util.pokeList.get(i));
					}
					Util.pokeSort(Util.playerTwo);
				}else if(Util.landowner == 3){
//					while(Util.pokeList.size() != 0){
//						Util.playerThree.getPlayerPoke().add(Util.pokeList.get(0));
//						Util.pokeList.remove(0);
//					}
					for(int i = 0; i < Util.pokeList.size(); i++){
						Util.playerThree.getPlayerPoke().add(Util.pokeList.get(i));
					}
					Util.pokeSort(Util.playerThree);
				}
				Util.key = 4;
			}else if(Util.key == 4){
				
				lf.getGf().getGamePanel().addMouseListener(lf.getGm());
				if(Util.landowner == 1){
					Util.callPlayer = 0;
				}else if(Util.landowner == 2){
					Util.callPlayer = 2;
				}else if(Util.landowner == 3){
					Util.callPlayer = 1;
				}
				System.out.println("aaa" + Util.callPlayer);
				while(Util.playerOne.getPlayerPoke().size() != 0 
						&& Util.playerTwo.getPlayerPoke().size() != 0
						&& Util.playerThree.getPlayerPoke().size() != 0){//判断条件就是都不为空说明有牌
					if(Util.callPlayer == 0){
						
					}else if(Util.callPlayer == 1){
						//东家拿出最小的牌
						Poke p = Util.playerThree.getPlayerPoke().get(Util.playerThree.getPlayerPoke().size() - 1);
						//放进出牌的集合中
						Util.playerThree.getOutPoke().add(p);
						Util.playerThree.getPlayerPoke().remove(Util.playerThree.getPlayerPoke().size() - 1);
						Util.callPlayer = 2;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(Util.callPlayer == 2){
						//西家出一张比东家大1的牌
						Poke p;
						if(Util.playerThree.getOutPoke().size() != 0){
							for (int i = Util.playerTwo.getPlayerPoke().size() - 1; i >= 0 ; i--) {
								if(Util.playerTwo.getPlayerPoke().get(i).getNumber() > 
								Util.playerThree.getOutPoke().get(0).getNumber()){//如果西家有一张牌比东家的一张牌大就可以出牌
									p = Util.playerTwo.getPlayerPoke().get(i);
									Util.playerTwo.getOutPoke().add(p);//将找出的那张牌放进西家的出牌集合中
									Util.playerTwo.getPlayerPoke().remove(i);//并移除掉西家手牌中要出的牌
									break;//找到跳出循环
								}
							}
						}
						if(Util.playerTwo.getOutPoke().size() == 0){//西家出牌集合为空,说明要不起
							Util.onPoke = false;
						}
						Util.callPlayer = 0;//跳转到自己出牌
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
//					try {
//						Thread.sleep(1);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
