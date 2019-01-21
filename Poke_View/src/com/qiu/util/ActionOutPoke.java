package com.qiu.util;

import java.util.ArrayList;

import com.qiu.model.Player;
import com.qiu.model.Poke;

public class ActionOutPoke {

	
	public static void solo(Player self,Player prior){
		for (int i = 0; i < self.getPlayerPoke().size(); i++) {
			if (self.getPlayerPoke().get(i).getPokeY() == 485) {// 找着那张起来的牌
				if (self.getPlayerPoke().get(i).getNumber() > prior.getOutPoke().get(0)
						.getNumber()) {// 比较大小
					Util.isPoke = 1;// 1就表示要的起
					Util.type = 1;// 单牌类型
				} else {
					self.getPlayerPoke().get(i).setPokeY(500);
					Util.isPoke = -1;// 表示选择不对,重新出牌
				}
				break;
			}
		}
	}
	
	public static void doublePoke(Player self,Player prior){
		ArrayList<Poke> temp = new ArrayList<Poke>();// 临时一个集合,用来存放起来的两张牌
		for (int i = 0; i < self.getPlayerPoke().size(); i++) {
			Poke p = self.getPlayerPoke().get(i);// 临时扑克
			if (p.getPokeY() == 485) {// 起来的两张牌的面值是不是一样
				temp.add(p);
			}
		}
		Util.pokeSort(temp);
		if (temp.get(0).getNumber() == temp.get(1).getNumber()
				&& temp.get(0).getNumber() > prior.getOutPoke().get(0).getNumber()) {// 临时扑克牌的面值一样时就是对子
			Util.type = 2;// 表明自己出了对子
			Util.isPoke = 1;// 有牌出
		} else if (temp.get(0).getNumber() == 100 && temp.get(1).getNumber() == 99) {// 王炸的牌型
			OutPoke.score *= 4;
			Util.type = 8;// 王炸的牌型
			Util.isPoke = 1;
		} else {// 否则表示出牌有误 ,起来的牌归位
			for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
				self.getPlayerPoke().get(i).setPokeY(500);
			}
		}
	}
	
	public static void triple(Player self,Player prior){
		ArrayList<Poke> temp = new ArrayList<Poke>();// 临时一个集合,用来存放起来的两张牌
		for (int i = 0; i < self.getPlayerPoke().size(); i++) {
			Poke p = self.getPlayerPoke().get(i);// 临时扑克
			if (p.getPokeY() == 485) {// 起来的两张牌的面值是不是一样
				temp.add(p);
			}
		}
		if ((temp.get(0).getNumber() == temp.get(1).getNumber())
				&& (temp.get(0).getNumber() == temp.get(2).getNumber())
				&& (temp.get(0).getNumber() > prior.getOutPoke().get(0).getNumber())) {// 第一张和第二张相同.并且和第三张相同
			Util.type = 3;// 表明自己出了三张一样的
			Util.isPoke = 1;// 有牌出
		} else {// 否则表示出牌有误 ,起来的牌归位
			for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
				self.getPlayerPoke().get(i).setPokeY(500);
			}
		}
	}
	
	public static void tripleAndOne(Player self,Player prior){
		if(prior.getOutPoke().get(0).getNumber() 
				!= prior.getOutPoke().get(3).getNumber()){
			ArrayList<Poke> temp = new ArrayList<Poke>();// 临时集合
			for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 遍历一下找出升起来的牌
				Poke p = self.getPlayerPoke().get(i);
				if (p.getPokeY() == 485) {
					temp.add(p);
				}
			}
			Util.pokeSort(temp);// 给临时集合排个序
			if ((temp.get(0).getNumber() == temp.get(1).getNumber())
					&& (temp.get(0).getNumber() == temp.get(2).getNumber())
					&& (temp.get(0).getNumber() != temp.get(3).getNumber())) {// 是一个三带一的牌
				if (temp.get(1).getNumber() > prior.getOutPoke().get(2).getNumber()) {
					Util.type = 4;
					Util.isPoke = 1;
				} else {
					for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
						self.getPlayerPoke().get(i).setPokeY(500);
					}
				}
			} else if ((temp.get(1).getNumber() == temp.get(2).getNumber())
					&& (temp.get(1).getNumber() == temp.get(3).getNumber())
					&& (temp.get(1).getNumber() != temp.get(0).getNumber())) {// 是三代一的牌
				if (temp.get(1).getNumber() > prior.getOutPoke().get(2).getNumber()) {
					Util.type = 4;
					Util.isPoke = 1;
				} else {
					for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
						self.getPlayerPoke().get(i).setPokeY(500);
					}
				}
			} else if (temp.get(0).getNumber() == temp.get(1).getNumber()
					&& temp.get(0).getNumber() == temp.get(2).getNumber()
					&& temp.get(0).getNumber() == temp.get(3).getNumber()) {// 四个一样的炸弹
				OutPoke.score *= 4;
				Util.type = 8;
				Util.isPoke = 1;
			} else {
				for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
					self.getPlayerPoke().get(i).setPokeY(500);
				}
			}
		}
		
	}
	
	public static void fivePoke(Player self,Player prior){
		ArrayList<Poke> temp = new ArrayList<Poke>();// 临时集合
		for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 遍历一下找出升起来的牌
			Poke p = self.getPlayerPoke().get(i);
			if (p.getPokeY() == 485) {
				temp.add(p);
			}
		}
		Util.pokeSort(temp);// 给临时集合排个序
		if ((temp.get(0).getNumber() == temp.get(1).getNumber())
				&& (temp.get(0).getNumber() == temp.get(2).getNumber())
				&& (temp.get(0).getNumber() != temp.get(3).getNumber())
				&& (temp.get(3).getNumber() == temp.get(4).getNumber())
				&& (temp.get(0).getNumber() > prior.getOutPoke().get(2).getNumber())) {// 是一个三带二的牌
			Util.type = 5;
			Util.isPoke = 1;
		} else if ((temp.get(2).getNumber() == temp.get(3).getNumber())
				&& (temp.get(2).getNumber() == temp.get(4).getNumber())
				&& (temp.get(2).getNumber() != temp.get(0).getNumber())
				&& (temp.get(0).getNumber() == temp.get(1).getNumber())
				&& (temp.get(2).getNumber() > prior.getOutPoke().get(2).getNumber())) {// 是三代一对的牌
			Util.type = 5;
			Util.isPoke = 1;
		} else {
			for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
				self.getPlayerPoke().get(i).setPokeY(500);
			}
		}
	}
	
	public static void beyondFivePoke(Player self,Player prior){
		ArrayList<Poke> temp = new ArrayList<Poke>();// 临时集合
		for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 遍历一下找出升起来的牌
			Poke p = self.getPlayerPoke().get(i);
			if (p.getPokeY() == 485) {
				temp.add(p);
			}
		}
		Util.pokeSort(temp);// 排个序确保没错
		Util.pokeSort(prior.getOutPoke());// 排个序确保没错
		boolean k = false;// 用于判断是不是顺子的返回值 1--顺子 2--双顺
		boolean b = false;// 判断是不是双顺
		// 顺子的判断
		for (int i = temp.size() - 1; i > 0; i--) {
			if (temp.get(temp.size() - 1).getNumber() > prior.getOutPoke()
					.get(prior.getOutPoke().size() - 1).getNumber()) {
				if (temp.get(i - 1).getNumber() - temp.get(i).getNumber() == 1) {// 不满足条件,坐标归零
					k = true;
				}else{
					k = false;
				}
			}
		}
		// 双顺的判断
		if (temp.size() % 2 == 0) {
			if (temp.get(temp.size() - 1).getNumber() > prior.getOutPoke()
					.get(prior.getOutPoke().size() - 1).getNumber()) {
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
			Util.setCoordinate(self.getPlayerPoke());
		}
	}
	
	public static void boomPoke(Player self,Player prior){

		ArrayList<Poke> temp = new ArrayList<Poke>();
		for (int i = 0; i < self.getPlayerPoke().size(); i++) {
			Poke p = self.getPlayerPoke().get(i);
			if (p.getPokeY() == 485) {
				temp.add(p);
			}
		}
		Util.pokeSort(temp);
		if (prior.getOutPoke().size() != 2 && prior.getOutPoke().size() != 4) {
			if (temp.size() == 4) {
				if (temp.get(0).getNumber() == temp.get(1).getNumber()
						&& temp.get(0).getNumber() == temp.get(2).getNumber()
						&& temp.get(0).getNumber() == temp.get(3).getNumber()) {
					Util.isPoke = 1;
					OutPoke.score *= 4;
					OutPoke.boomCount++;
					Util.type = 8;
				} else {
					for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
						self.getPlayerPoke().get(i).setPokeY(500);
					}
				}
			} else if (temp.size() == 2) {
				if (temp.get(0).getNumber() == 100 && temp.get(1).getNumber() == 99) {
					Util.isPoke = 1;
					OutPoke.score *= 4;
					OutPoke.doubleBoomCount++;
					Util.type = 8;
				}
			}
		} else if (prior.getOutPoke().size() == 4) {
			if (temp.size() == 4) {
				if (prior.getOutPoke().get(0).getNumber() == prior.getOutPoke().get(1)
						.getNumber()
						&& prior.getOutPoke().get(0).getNumber() == prior.getOutPoke()
								.get(2).getNumber()
						&& prior.getOutPoke().get(0).getNumber() == prior.getOutPoke()
								.get(3).getNumber()) {
					if (temp.get(0).getNumber() > prior.getOutPoke().get(0).getNumber()) {
						// 四张的炸

						if (temp.get(0).getNumber() == temp.get(1).getNumber()
								&& temp.get(0).getNumber() == temp.get(2).getNumber()
								&& temp.get(0).getNumber() == temp.get(3).getNumber()) {
							Util.isPoke = 1;
							OutPoke.score *= 4;
							OutPoke.boomCount++;
							Util.type = 8;

						}
					} else {
						for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
							self.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				} else {
					if (temp.get(0).getNumber() == temp.get(1).getNumber()
							&& temp.get(0).getNumber() == temp.get(2).getNumber()
							&& temp.get(0).getNumber() == temp.get(3).getNumber()) {
						Util.isPoke = 1;
						OutPoke.score *= 4;
						OutPoke.boomCount++;
						Util.type = 8;
					}
				}
			} else if (temp.size() == 2) {
				if (temp.get(0).getNumber() == 100 && temp.get(1).getNumber() == 99) {
					Util.isPoke = 1;
					OutPoke.score *= 4;
					OutPoke.doubleBoomCount++;
					Util.type = 8;
				}else{
					for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
						self.getPlayerPoke().get(i).setPokeY(500);
					}
				}
			}
		} else if (prior.getOutPoke().size() == 2) {
			if (prior.getOutPoke().get(0).getNumber() != 100
					|| prior.getOutPoke().get(1).getNumber() != 100) {
				if (temp.size() == 4) {
					if (temp.get(0).getNumber() == temp.get(1).getNumber()
							&& temp.get(0).getNumber() == temp.get(2).getNumber()
							&& temp.get(0).getNumber() == temp.get(3).getNumber()) {
						Util.isPoke = 1;
						OutPoke.score *= 4;
						OutPoke.boomCount++;
						Util.type = 8;
					} else {
						for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
							self.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				} else if (temp.size() == 2) {
					if (temp.get(0).getNumber() == 100 && temp.get(1).getNumber() == 99) {
						Util.isPoke = 1;
						OutPoke.score *= 4;
						OutPoke.doubleBoomCount++;
						Util.type = 8;
					} else {
						for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
							self.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				}
			}else if(prior.getOutPoke().get(0).getNumber() == 100 && prior.getOutPoke().get(1).getNumber() == 99){
				for (int i = 0; i < self.getPlayerPoke().size(); i++) {// 把牌全部归位
					self.getPlayerPoke().get(i).setPokeY(500);
				}
			}
		}
	
	}
}
