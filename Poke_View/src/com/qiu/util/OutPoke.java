package com.qiu.util;

import java.util.ArrayList;

import com.qiu.model.Poke;

//写一个关于出牌的类,主要用于实现出牌和画板上的分数计算方法
public class OutPoke {

	public static int score = 0;// 叫地主的分数,乘上底牌的倍数
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

}
