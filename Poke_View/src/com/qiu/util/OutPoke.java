package com.qiu.util;

import java.util.ArrayList;

import com.qiu.model.Player;
import com.qiu.model.Poke;

//写一个关于出牌的类,主要用于实现出牌和画板上的分数计算方法
public class OutPoke {

	public static int score = 0;// 叫地主的分数,乘上底牌的倍数
	public static boolean isTrusteeship = false;// 托管的开关,false表示自己玩,true表示托管开启

	// 记牌器
	public static int reMainPoke_3;// 3的记牌器
	public static int reMainPoke_4;// 4的记牌器
	public static int reMainPoke_5;// 5的记牌器
	public static int reMainPoke_6;// 6的记牌器
	public static int reMainPoke_7;// 7的记牌器
	public static int reMainPoke_8;// 8的记牌器
	public static int reMainPoke_9;// 9的记牌器
	public static int reMainPoke_10;// 10的记牌器
	public static int reMainPoke_11;// 11的记牌器
	public static int reMainPoke_12;// 12的记牌器
	public static int reMainPoke_13;// 13的记牌器
	public static int reMainPoke_14;// 14的记牌器
	public static int reMainPoke_22;// 22的记牌器
	public static int reMainPoke_W;// 王的记牌器

	// 底牌翻倍的方法
	public static void baseMultiple(ArrayList<Poke> p) {
		/*
		 * 底牌的翻倍:单王两倍/对2两倍/同花和顺子三倍/三张和双王4倍 同时出现以高倍为准
		 */
		if ((Util.pokeList.get(0).getNumber() == 100 && Util.pokeList.get(1).getNumber() == 99)
				|| (Util.pokeList.get(0).getNumber() == Util.pokeList.get(1).getNumber()
						&& Util.pokeList.get(0).getNumber() == Util.pokeList.get(2).getNumber())) {
			OutPoke.score *= 4;// 4倍
		} else if ((p.get(0).getName().equals(p.get(1).getName()) && p.get(0).getName().equals(p.get(2).getName()))
				|| (p.get(0).getNumber() - p.get(1).getNumber() == 1)
						&& (p.get(0).getNumber() - p.get(2).getNumber() == 2)) {
			OutPoke.score *= 3;// 3倍
		} else if (p.get(0).getNumber() == 100 || p.get(0).getNumber() == 99
				|| (p.get(0).getNumber() == 22 && p.get(1).getNumber() == 22)) {
			OutPoke.score *= 2;// 2倍
		}
	}

	// 手动出牌方法
	public static void handAction() {
		if (Util.isPoke == 1) {
			// 先清空自己的出牌集合
			Util.playerOne.getOutPoke().clear();
			// 遍历将起来的牌放进出牌集合中
			for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {
				Poke p = Util.playerOne.getPlayerPoke().get(i);
				if (p.getPokeY() == 485) {
					Util.playerOne.getOutPoke().add(p);// 将起来的牌放进
					Util.playerOne.getPlayerPoke().remove(i);// 从手牌中移除该牌
					i = -1;// 手牌集合中的长度有变化,需要重新开始
				}
			}
			// 不管有没有出牌都重新排序
			Util.pokeSort(Util.playerOne.getPlayerPoke());
			// 重新设置坐标
			Util.setCoordinate(Util.playerOne.getPlayerPoke());
			Util.callPlayer = 1;
		} else if (Util.isPoke == 0) {
			Util.playerOne.getOutPoke().clear();
			Util.callPlayer = 1;// 就直接跳转下一个人
		}
	}

	// 电脑智能出牌 参数传进 上家 下家 本家
	public static void autoSoloOutPoke(Player up, Player down, Player self) {
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
			if (self.getOutPoke().size() == 0) {
				Util.isPoke = 2;// 表示本家要不起
			}
		} else if (up.getOutPoke().size() != 0) {// 上一家的出牌集合不为空,说明有出牌
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
			if (self.getOutPoke().size() == 0) {// 遍历后依然为空表示要不起
				Util.isPoke = 2;// 表示要不起
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

	// 专门出对子的方法
	public static void autoDoubleOutPoke(Player up, Player down, Player self) {
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();
		if (up.getOutPoke().size() != 0) {// 上家的出牌集合不为空,说明上家就是出了对子
			// 判断条件i > 0 是因为防止下标越界,因为有可能是最后一张牌是个对子,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的两张
				if (p1.getNumber() == p2.getNumber()) {// 两张的面值一样,说明是对子
					if (p1.getNumber() > up.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						break;
					}
				}
			}
			if (self.getOutPoke().size() == 0) {// 本家出牌集合为空,就说明要不起
				Util.isPoke = 2;
			}

		} else if (up.getOutPoke().size() == 0 && down.getOutPoke().size() != 0) {// 上家的出牌集合为空,并且下家的出牌集合不为空,说明上家要不起下家
			// 判断条件i > 0 是因为防止下标越界,因为有可能是最后一张牌是个对子,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的两张
				if (p1.getNumber() == p2.getNumber()) {// 两张的面值一样,说明是对子
					if (p1.getNumber() > down.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						break;
					}
				}
			}
			if (self.getOutPoke().size() == 0) {// 本家出牌集合为空,就说明要不起
				Util.isPoke = 2;
			}
		}
	}

	// 专门出三张一样的方法
	public static void autoTripleOutPoke(Player up, Player down, Player self) {
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();
		if (up.getOutPoke().size() != 0) {// 上家的出牌集合不为空,说明上家就是出了三张
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的第二张
				Poke p3 = self.getPlayerPoke().get(i - 2); // 相邻的第三张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 三张的面值一样
					if (p1.getNumber() > up.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getOutPoke().add(p3);// 添加到出牌集合中
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
						break;
					}
				}
			}
			if (self.getOutPoke().size() == 0) {// 本家出牌集合为空,就说明要不起
				Util.isPoke = 2;
			}

		} else if (up.getOutPoke().size() == 0 && down.getOutPoke().size() != 0) {// 上家的出牌集合为空,并且下家的出牌集合不为空,说明上家要不起下家
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的两张
				Poke p3 = self.getPlayerPoke().get(i - 2);// 相邻的两张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 两张的面值一样,说明是对子
					if (p1.getNumber() > down.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getOutPoke().add(p3);// 添加到出牌集合中
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
						break;
					}
				}
			}
			if (self.getOutPoke().size() == 0) {// 本家出牌集合为空,就说明要不起
				Util.isPoke = 2;
			}
		}
	}

	// 专门出三带一的方法
	public static void autoTripleAndOneOutPoke(Player up, Player down, Player self) {
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();
		if (up.getOutPoke().size() != 0) {// 上家的出牌集合不为空,说明上家就是出了三带一
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的第二张
				Poke p3 = self.getPlayerPoke().get(i - 2); // 相邻的第三张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 三张的面值一样
					if (p1.getNumber() > up.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getOutPoke().add(p3);// 添加到出牌集合中
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
						break;
					}
				}
			}
			// 本家出牌集合为空,就说明要不起,还有一种情况就是刚好剩下三张一样的
			if (self.getOutPoke().size() == 0 || self.getPlayerPoke().size() == 0) {
				Util.isPoke = 2;
			} else {// 不为空表示有牌
				Poke p = self.getPlayerPoke().get(self.getPlayerPoke().size() - 1);// 最小的一张牌作为带的1
				if (p.getNumber() != self.getOutPoke().get(0).getNumber()) {// 判断带进去的一张,它和前面三张不一样才是三代一,否则就是炸弹
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
				} else {
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
					OutPoke.score *= 4;// 炸弹翻四倍
				}

			}

		} else if (up.getOutPoke().size() == 0 && down.getOutPoke().size() != 0) {// 上家的出牌集合为空,并且下家的出牌集合不为空,说明上家要不起下家
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的两张
				Poke p3 = self.getPlayerPoke().get(i - 2);// 相邻的两张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 两张的面值一样,说明是对子
					if (p1.getNumber() > down.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getOutPoke().add(p3);// 添加到出牌集合中
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
						break;
					}
				}
			}
			// 本家出牌集合为空,就说明要不起,还有一种情况就是刚好剩下三张一样的
			if (self.getOutPoke().size() == 0 || self.getPlayerPoke().size() == 0) {
				Util.isPoke = 2;
			} else {// 不为空表示有牌
				Poke p = self.getPlayerPoke().get(self.getPlayerPoke().size() - 1);// 最小的一张牌作为带的1
				if (p.getNumber() != self.getOutPoke().get(0).getNumber()) {// 判断带进去的一张,它和前面三张不一样才是三代一,否则就是炸弹
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
				} else {
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
					OutPoke.score *= 4;// 炸弹翻四倍
				}
			}
		}
	}

	// 专门出三带二的方法
	public static void autoTripleAndTwoOutPoke(Player up, Player down, Player self) {
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();
		if (up.getOutPoke().size() != 0) {// 上家的出牌集合不为空,说明上家就是出了三带二
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的第二张
				Poke p3 = self.getPlayerPoke().get(i - 2); // 相邻的第三张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 三张的面值一样
					if (p1.getNumber() > up.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getOutPoke().add(p3);// 添加到出牌集合中
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
						break;
					}
				}
			}
			// 本家出牌集合为空,就说明要不起,还有一种情况就是刚好剩下四张,这样就不满足三代二
			if (self.getOutPoke().size() == 0 || self.getPlayerPoke().size() < 2) {
				Util.isPoke = 2;
			} else {// 不为空表示有牌
				// 最小的两张作为带的对子
				for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
					Poke p1 = self.getPlayerPoke().get(i);
					Poke p2 = self.getPlayerPoke().get(i - 1);
					if (p1.getNumber() == p2.getNumber()) {
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						break;//找到了就跳出循环
					}
				}
				if (self.getOutPoke().size() != 5) {// 出牌集合的size不满足5,说明要不起
					Util.isPoke = 2;
				}
			}

		} else if (up.getOutPoke().size() == 0 && down.getOutPoke().size() != 0) {// 上家的出牌集合为空,并且下家的出牌集合不为空,说明上家要不起下家
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的两张
				Poke p3 = self.getPlayerPoke().get(i - 2);// 相邻的两张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 三张的面值一样
					if (p1.getNumber() > down.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getOutPoke().add(p3);// 添加到出牌集合中
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
						break;
					}
				}
			}
			// 本家出牌集合为空,就说明要不起,还有一种情况就是刚好剩下四张,这样就不满足三代二
			if (self.getOutPoke().size() == 0 || self.getPlayerPoke().size() < 2) {
				Util.isPoke = 2;
			} else {// 不为空表示有牌
				// 最小的两张作为带的对子
				for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
					Poke p1 = self.getPlayerPoke().get(i);
					Poke p2 = self.getPlayerPoke().get(i - 1);
					if (p1.getNumber() == p2.getNumber()) {
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						break;//找到了就跳出循环
					}
				}
				if (self.getOutPoke().size() != 5) {// 出牌集合的size不满足5,说明要不起
					Util.isPoke = 2;
				}
			}
		}
	}
}
