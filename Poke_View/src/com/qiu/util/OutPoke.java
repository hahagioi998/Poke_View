package com.qiu.util;

import java.util.ArrayList;

import com.qiu.model.Player;
import com.qiu.model.Poke;

//写一个关于出牌的类,主要用于实现出牌和画板上的分数计算方法
public class OutPoke {

	public static int score = 0;// 叫地主的分数,乘上底牌的倍数
	public static boolean isTrusteeship = false;//托管的开关,false表示自己玩,true表示托管开启
	
	// 底牌翻倍的方法
	public static void baseMultiple(ArrayList<Poke> p) {
		/*
		 * 底牌的翻倍:单王两倍/对2两倍/同花和顺子三倍/三张和双王4倍 
		 * 同时出现以高倍为准
		 */
		if ((Util.pokeList.get(0).getNumber() == 100 && Util.pokeList.get(1).getNumber() == 99)
				|| (Util.pokeList.get(0).getNumber() == Util.pokeList.get(1).getNumber()
						&& Util.pokeList.get(0).getNumber() == Util.pokeList.get(2).getNumber())) {
			OutPoke.score *= 4;//4倍
		}else if((p.get(0).getName().equals(p.get(1).getName()) && p.get(0).getName().equals(p.get(2).getName()))
				|| (p.get(0).getNumber() - p.get(1).getNumber() == 1) && (p.get(0).getNumber() - p.get(2).getNumber() == 2)){
			OutPoke.score *= 3;//3倍
		}else if(p.get(0).getNumber() == 100 || p.get(0).getNumber() == 99 
				|| (p.get(0).getNumber() == 22 && p.get(1).getNumber() == 22)){
			OutPoke.score *= 2;//2倍
		}
	}
	
	//电脑智能出牌    参数传进  上家  下家  本家
	public static void autoOutPoke(Player up, Player down, Player self){
		Util.isPoke = -1;// 关闭要不起的按钮
		// 如果上家的出牌集合为空,下家的出牌集合不为空,就说明上家选择不出牌
		if (up.getOutPoke().size() == 0 && down.getOutPoke().size() != 0) {
			self.getOutPoke().clear();
			// 再遍历一下,如果找着比下家大的牌就直接出
			for (int i = self.getPlayerPoke().size() - 1; i >= 0; i--) {
				if (self.getPlayerPoke().get(i).getNumber() > down.getOutPoke().get(0).getNumber()) {
					Poke p = self.getPlayerPoke().get(i);
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(i);
					break;
				}
			}
			if(self.getOutPoke().size() == 0){
				Util.isPoke = 2;//表示本家要不起
			}
		} else if (up.getOutPoke().size() != 0) {//上一家的出牌集合不为空,说明有出牌
			// 先清除本家的出牌集合中的牌
			self.getOutPoke().clear();
			// 再遍历一下,如果找着比上家大的牌就直接出
			for (int i = self.getPlayerPoke().size() - 1; i >= 0; i--) {
				if (self.getPlayerPoke().get(i).getNumber() > up.getOutPoke().get(0).getNumber()) {
					Poke p = self.getPlayerPoke().get(i);
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(i);
					break;
				}
			}
			if(self.getOutPoke().size() == 0){//遍历后依然为空表示要不起
				Util.isPoke = 2;//表示要不起
			}
		} else if (up.getOutPoke().size() == 0 && down.getOutPoke().size() == 0) {// 否则就是前两家的出牌集合都是空的话,就是自己地主出第一张牌
			// 先清除本家的出牌集合中的牌
			self.getOutPoke().clear();
			// 本家拿出最小的牌
			Poke p = self.getPlayerPoke().get(self.getPlayerPoke().size() - 1);
			// 放进出牌的集合中
			self.getOutPoke().add(p);
			self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
		}
	}

}
