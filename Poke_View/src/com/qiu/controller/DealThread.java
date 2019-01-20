package com.qiu.controller;

import com.qiu.model.Poke;
import com.qiu.util.OutPoke;
import com.qiu.util.Util;
import com.qiu.view.LoginFrame;

public class DealThread extends Thread {

	LoginFrame lf;

	public DealThread(LoginFrame lf) {
		this.lf = lf;
	}

	@Override
	public void run() {

		while (true) {

			if (Util.getKey() == 1) {// 当key值为1的时候就开始发牌
				// Util.reStart = false;//主要用于游戏一半的时候重新开始
				while (Util.player_Poke < Util.PLAYERE_POKE) {// 发牌的轮数
					Util.player_Poke++;// 每发一次就自增1
					// 给每个玩家发牌,并每发一次牌就排序
					for (int j = 0; j < Util.player.length; j++) {
						Util.player[j].getPlayerPoke().add(Util.pokeList.get(0));
						Util.pokeList.remove(0);
						Util.pokeSort(Util.playerOne.getPlayerPoke());
						Util.setCoordinate(Util.playerOne.getPlayerPoke());
						Util.pokeSort(Util.playerTwo.getPlayerPoke());
						Util.pokeSort(Util.playerThree.getPlayerPoke());
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
				Util.callPlayers();// 调用随机玩家叫地主的方法
				Util.key = 2;// 把key值改为2,开始在叫地主环节

			} else if (Util.key == 2) {// 叫地主环节
				if (Util.callPlayer == 0) {// 自己叫地主
					if (Util.drawMark == 1) {
						// 休眠两秒
						try {
							Thread.sleep(Util.time);
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
							Thread.sleep(Util.time);
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
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// 结束叫地主
						Util.landowner = 1;// 玩家自己是地主
						OutPoke.score = Util.playerOne.getPoints();//直接3分
					} else if (Util.drawMark == 4) {
						// 休眠两秒
						try {
							Thread.sleep(Util.time);
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
						if (Util.playerOne.getPoints() != 4) {// 在自己的叫分不等于4的情况下,说明自己已经叫过分了
							Util.playerThree.setPoints(0);// 自己叫过分,东家比自己小的就不叫分
							Util.drawMark = 8;// 东家不叫分
						}
					}
					if (Util.drawMark == 5) {// 自己直接叫3分需休眠一下
						try {
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// 结束叫地主
						Util.landowner = 3;// 玩家自己是地主
						OutPoke.score = Util.playerThree.getPoints();
					}
					// 休眠两秒
					try {
						Thread.sleep(Util.time);
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
					if (Util.playerTwo.getPoints() <= Util.playerThree.getPoints()) {// 获取的分数小于前一位,直接跳过
						if (Util.playerThree.getPoints() != 4) {// 东家在叫了分的前提下
							Util.playerTwo.setPoints(0);
							Util.drawMark = 12;// 西家不叫分
						}
					}
					if (Util.drawMark == 9) {// 自己直接叫3分需休眠一下
						try {
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// 结束叫地主
						Util.landowner = 2;// 玩家自己是地主
						OutPoke.score = Util.playerTwo.getPoints();//直接3分
					}
					// 休眠两秒
					try {
						Thread.sleep(Util.time);
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
				try {
					Thread.sleep(Util.time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 当玩家的points的值不为4的时候,说明都叫地主都一轮结束了
				if (Util.playerOne.getPoints() != 4 && Util.playerTwo.getPoints() != 4
						&& Util.playerThree.getPoints() != 4) {
					if (Util.playerOne.getPoints() > Util.playerTwo.getPoints()
							&& Util.playerOne.getPoints() > Util.playerThree.getPoints()) {
						Util.landowner = 1;// 表示玩家一地主
						Util.key = 3;
						OutPoke.score = Util.playerOne.getPoints();
					} else if (Util.playerTwo.getPoints() > Util.playerOne.getPoints()
							&& Util.playerTwo.getPoints() > Util.playerThree.getPoints()) {
						Util.landowner = 2;// 表示玩家二地主
						Util.key = 3;
						OutPoke.score = Util.playerTwo.getPoints();
					} else if (Util.playerThree.getPoints() > Util.playerOne.getPoints()
							&& Util.playerThree.getPoints() > Util.playerTwo.getPoints()) {
						Util.landowner = 3;// 表示玩家三地主
						Util.key = 3;
						OutPoke.score = Util.playerThree.getPoints();
					} else if (Util.playerOne.getPoints() == 0 && Util.playerTwo.getPoints() == 0
							&& Util.playerThree.getPoints() == 0) {// 都不叫地主

						Util.pokeList.clear();// 移除集合的所有元素
						Util.playerOne.getPlayerPoke().clear();
						Util.playerTwo.getPlayerPoke().clear();
						Util.playerThree.getPlayerPoke().clear();
						Util.landowner = 4;
						// 一切数据都要重置
						Util.initPoke();// 初始化数据
						Util.player_Poke = 0;// 发牌轮数重置为0
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
			} else if (Util.key == 3) {
				try {// 休眠两秒钟,让底牌有一个显示的时间
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (Util.landowner == 1) {// 玩家一地主
					// 底牌接着显示
					for (int i = 0; i < Util.pokeList.size(); i++) {
						Util.playerOne.getPlayerPoke().add(Util.pokeList.get(i));
					}
					//底牌排个序,方便查看是不是顺子
					Util.pokeSort(Util.pokeList);
					OutPoke.baseMultiple(Util.pokeList);//底牌情况对翻倍的控制
					Util.pokeSort(Util.playerOne.getPlayerPoke());// 排序
					Util.setCoordinate(Util.playerOne.getPlayerPoke());
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {
						for (int j = 0; j < Util.pokeList.size(); j++) {
							Poke p1 = Util.playerOne.getPlayerPoke().get(i);
							Poke p2 = Util.pokeList.get(j);
							if(p1.getName().equals(p2.getName()) && p1.getNumber() == p2.getNumber()){
								p1.setPokeY(485);
							}
						}
					}
					try {
						Thread.sleep(Util.time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Util.setCoordinate(Util.playerOne.getPlayerPoke());
				} else if (Util.landowner == 2) {
					for (int i = 0; i < Util.pokeList.size(); i++) {
						Util.playerTwo.getPlayerPoke().add(Util.pokeList.get(i));
					}
					//底牌排个序,方便查看是不是顺子
					Util.pokeSort(Util.pokeList);
					OutPoke.baseMultiple(Util.pokeList);//底牌情况对翻倍的控制
					Util.pokeSort(Util.playerTwo.getPlayerPoke());
				} else if (Util.landowner == 3) {
					for (int i = 0; i < Util.pokeList.size(); i++) {
						Util.playerThree.getPlayerPoke().add(Util.pokeList.get(i));
					}
					//底牌排个序,方便查看是不是顺子
					Util.pokeSort(Util.pokeList);
					OutPoke.baseMultiple(Util.pokeList);//底牌情况对翻倍的控制
					Util.pokeSort(Util.playerThree.getPlayerPoke());
				}
				Util.key = 4;
			} else if (Util.key == 4) {//开始出牌
				Util.pitchOn = 0;//防止还没到发牌的时候,就点击了牌的起来,会出现异常
				if (Util.landowner == 1) {
					Util.callPlayer = 0;
				} else if (Util.landowner == 2) {
					Util.callPlayer = 2;
				} else if (Util.landowner == 3) {
					Util.callPlayer = 1;
				}
				while (Util.playerOne.getPlayerPoke().size() != 0 && Util.playerTwo.getPlayerPoke().size() != 0
						&& Util.playerThree.getPlayerPoke().size() != 0) {// 判断条件就是都不为空说明有牌
					
					if (Util.callPlayer == 0) {// 自己出牌
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//如果托管开启,并且电脑两家都为空,就将出牌类型改为出单牌
						if(OutPoke.isTrusteeship && (Util.playerThree.getOutPoke().size() == 0 
								&& Util.playerTwo.getOutPoke().size() == 0)){
							Util.type = 1;
						}
						if(Util.type == 1 || Util.type == 0){//单牌类型
							if(OutPoke.isTrusteeship){//托管开启
								if(Util.playerThree.getOutPoke().size() == 0 
										&& Util.playerOne.getOutPoke().size() == 0){//当上一家和下一家的出牌集合为空时,说明是本家出牌
									Util.type = 1;//默认出单张
								}
								OutPoke.autoSoloOutPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//上家,下家,本家
								Util.type = 1;
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//如果是2就表示要不起
									Util.isPoke = 0;//改成自家的要不起
								}
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//左边重新设置好
								Util.callPlayer = 1;
							}else{//托管关闭
							
								if (Util.isPoke == 1) {

									// 清空自家出牌集合
									Util.playerOne.getOutPoke().clear();
									for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// 遍历一次把选中的牌放进出牌集合
										if (Util.playerOne.getPlayerPoke().get(i).getPokeY() == 485) {
											Poke p = Util.playerOne.getPlayerPoke().get(i);// 找出这张牌
											Util.playerOne.getOutPoke().add(p);// 放进出牌集合
											Util.playerOne.getPlayerPoke().remove(i);// 移除原集合的牌
											Util.isPoke = -1;
										}
									}
									// 不管有没有出牌都重新排序
									Util.pokeSort(Util.playerOne.getPlayerPoke());
									// 重新设置坐标
									Util.setCoordinate(Util.playerOne.getPlayerPoke());
									Util.callPlayer = 1;
								} else if (Util.isPoke == 0) {// 要不起
									Util.playerOne.getOutPoke().clear();
									Util.callPlayer = 1;// 就直接跳转下一个人
								}
							}
						}else if(Util.type == 2){//对子类型
							if(OutPoke.isTrusteeship){
								OutPoke.autoDoubleOutPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//上家,下家,本家
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//如果是2就表示要不起
									Util.isPoke = 0;//改成自家的要不起
								}
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//左边重新设置好
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 3){
							if(OutPoke.isTrusteeship){
								OutPoke.autoTripleOutPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//上家,下家,本家
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//如果是2就表示要不起
									Util.isPoke = 0;//改成自家的要不起
								}
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//左边重新设置好
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 4){
							if(OutPoke.isTrusteeship){
								OutPoke.autoTripleAndOneOutPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//上家,下家,本家
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//如果是2就表示要不起
									Util.isPoke = 0;//改成自家的要不起
								}
								Util.pokeSort(Util.playerOne.getOutPoke());
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//左边重新设置好
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 5){
							if(OutPoke.isTrusteeship){
								OutPoke.autoTripleAndTwoOutPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//上家,下家,本家
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//如果是2就表示要不起
									Util.isPoke = 0;//改成自家的要不起
								}
								Util.pokeSort(Util.playerOne.getOutPoke());
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//左边重新设置好
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 6){
							if(OutPoke.isTrusteeship){
								OutPoke.continuePoke(Util.playerTwo,Util.playerThree,Util.playerOne);//上家,下家,本家
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//如果是2就表示要不起
									Util.isPoke = 0;//改成自家的要不起
								}
								Util.pokeSort(Util.playerOne.getOutPoke());
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//左边重新设置好
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 7){
							if(OutPoke.isTrusteeship){
								OutPoke.continueDoublePoke(Util.playerTwo,Util.playerThree,Util.playerOne);//上家,下家,本家
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//如果是2就表示要不起
									Util.isPoke = 0;//改成自家的要不起
								}
								Util.pokeSort(Util.playerOne.getOutPoke());
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//左边重新设置好
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 8){
							if(OutPoke.isTrusteeship){
								OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//上家,下家,本家
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//如果是2就表示要不起
									Util.isPoke = 0;//改成自家的要不起
								}
								Util.pokeSort(Util.playerOne.getOutPoke());
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//左边重新设置好
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}
					} else if (Util.callPlayer == 1) {
						try {
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(Util.playerOne.getOutPoke().size() == 0 
								&& Util.playerTwo.getOutPoke().size() == 0){//当上一家和下一家的出牌集合为空时,说明是本家出牌
							Util.type = 1;//默认出单张
						}
						if(Util.type == 1){//单牌类型
							// 在自家的出牌集合中有牌,即不为空的前提下判断东家出什么牌
							OutPoke.autoSoloOutPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 2){//对子类型
							//调用对子类型的方法
							OutPoke.autoDoubleOutPoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 3){
							//调用三张类型的方法
							OutPoke.autoTripleOutPoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 4){//三代一
							OutPoke.autoTripleAndOneOutPoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 5){//三代二
							OutPoke.autoTripleAndTwoOutPoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 6){//顺子
							OutPoke.index.clear();
							OutPoke.temp.clear();
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							OutPoke.continuePoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 7){//顺子
							OutPoke.index.clear();
							OutPoke.temp.clear();
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							OutPoke.continueDoublePoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 8){
							OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							Util.callPlayer = 2;
						}
					} else if (Util.callPlayer == 2) {
						try {
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(Util.playerThree.getOutPoke().size() == 0 
								&& Util.playerOne.getOutPoke().size() == 0){//当上一家和下一家的出牌集合为空时,说明是本家出牌
							Util.type = 1;//默认出单张
						}
						if(Util.type == 1){//单牌类型
							OutPoke.autoSoloOutPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
							
						}else if(Util.type == 2){//对子类型
							//调用对子类型的方法
							OutPoke.autoDoubleOutPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 3){
							//调用三张类型的方法
							OutPoke.autoTripleOutPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 4){//三代一
							OutPoke.autoTripleAndOneOutPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 5){//三代二
							OutPoke.autoTripleAndTwoOutPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 6){//顺子
							OutPoke.index.clear();
							OutPoke.temp.clear();
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							OutPoke.continuePoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 7){//顺子
							OutPoke.index.clear();
							OutPoke.temp.clear();
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							OutPoke.continueDoublePoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 8){//出炸弹的时候
							OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							Util.callPlayer = 0;
						}
						try {
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				try {
					Thread.sleep(Util.time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if((Util.playerOne.getPlayerPoke().size() == 0 && Util.playerTwo.getPlayerPoke().size() != 0 
						&& Util.playerThree.getPlayerPoke().size() != 0) || 
						(Util.playerOne.getPlayerPoke().size() != 0 && Util.playerTwo.getPlayerPoke().size() == 0 
						&& Util.playerThree.getPlayerPoke().size() != 0) ||
						(Util.playerOne.getPlayerPoke().size() != 0 && Util.playerTwo.getPlayerPoke().size() != 0 
						&& Util.playerThree.getPlayerPoke().size() == 0)){
					Util.key = 5;
				}
			}else if(Util.key == 5){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Util.key = 6;
			}else if(Util.key == 6){
				//如果是地主的手牌集合为空就判断地主赢,否则就是农民赢
				if(Util.landowner == 1 && Util.playerOne.getPlayerPoke().size() == 0){
					Util.playerOne.setScore(Util.playerOne.getScore() +  OutPoke.score * 2);
					Util.playerOne.setWin("胜");
					Util.playerTwo.setScore(Util.playerTwo.getScore() - OutPoke.score);
					Util.playerTwo.setWin("败");
					Util.playerThree.setScore(Util.playerThree.getScore() - OutPoke.score);
					Util.playerThree.setWin("败");
					Util.key = 7;//跳转到结算面板
				}else if(Util.landowner == 2 && Util.playerTwo.getPlayerPoke().size() == 0){
					Util.playerTwo.setScore(Util.playerTwo.getScore() +  OutPoke.score * 2);
					Util.playerTwo.setWin("胜");
					Util.playerOne.setScore(Util.playerOne.getScore() - OutPoke.score);
					Util.playerOne.setWin("败");
					Util.playerThree.setScore(Util.playerThree.getScore() - OutPoke.score);
					Util.playerThree.setWin("败");
					Util.key = 7;//跳转到结算面板
				}else if(Util.landowner == 3 && Util.playerThree.getPlayerPoke().size() == 0){
					Util.playerThree.setScore(Util.playerThree.getScore() +  OutPoke.score * 2);
					Util.playerThree.setWin("胜");
					Util.playerTwo.setScore(Util.playerTwo.getScore() - OutPoke.score);
					Util.playerTwo.setWin("败");
					Util.playerOne.setScore(Util.playerOne.getScore() - OutPoke.score);
					Util.playerOne.setWin("败");
					Util.key = 7;//跳转到结算面板
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
