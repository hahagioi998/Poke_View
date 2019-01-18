package com.qiu.util;

import java.util.ArrayList;

import com.qiu.model.Player;
import com.qiu.model.Poke;

//写一个关于出牌的类,主要用于实现出牌和画板上的分数计算方法
public class OutPoke {

	public static ArrayList<Poke> temp = new ArrayList<Poke>();
	public static ArrayList<Integer> index = new ArrayList<Integer>();

	public static int score = 0;// 叫地主的分数,乘上底牌的倍数
	public static boolean isTrusteeship = false;// 托管的开关,false表示自己玩,true表示托管开启

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
			for (int i = Util.playerOne.getPlayerPoke().size() - 1; i >= 0; i--) {
				Poke p = Util.playerOne.getPlayerPoke().get(i);
				if (p.getPokeY() == 485) {
					Util.playerOne.getOutPoke().add(p);// 将起来的牌放进
					Util.playerOne.getPlayerPoke().remove(i);// 从手牌中移除该牌
					// i = -1;// 手牌集合中的长度有变化,需要重新开始
				}
			}
			// 不管有没有出牌都重新排序
			Util.pokeSort(Util.playerOne.getOutPoke());
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
	public static void autoSoloOutPoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// 关闭要不起的按钮
		// 如果上家的出牌集合为空,下家的出牌集合不为空,就说明上家选择不出牌
		if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {
			self.getOutPoke().clear();
			// 再遍历一下,如果找着比下家大的牌就直接出
			for (int i = self.getPlayerPoke().size() - 1; i >= 0; i--) {
				if (self.getPlayerPoke().get(i).getNumber() > next.getOutPoke().get(0).getNumber()) {
					Poke p = self.getPlayerPoke().get(i);
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(i);
					break;
				}
			}
			if (self.getOutPoke().size() == 0) {
				Util.isPoke = 2;// 表示本家要不起
			}
		} else if (prior.getOutPoke().size() != 0) {// 上一家的出牌集合不为空,说明有出牌
			// 先清除本家的出牌集合中的牌
			self.getOutPoke().clear();
			// 再遍历一下,如果找着比上家大的牌就直接出
			for (int i = self.getPlayerPoke().size() - 1; i >= 0; i--) {
				if (self.getPlayerPoke().get(i).getNumber() > prior.getOutPoke().get(0).getNumber()) {
					Poke p = self.getPlayerPoke().get(i);
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(i);
					break;
				}
			}
			if (self.getOutPoke().size() == 0) {// 遍历后依然为空表示要不起
				Util.isPoke = 2;// 表示要不起
			}
		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() == 0) {// 否则就是前两家的出牌集合都是空的话,就是自己地主出第一张牌
			// 先清除本家的出牌集合中的牌
			self.getOutPoke().clear();
			// 本家拿出最小的牌
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Poke p = self.getPlayerPoke().get(self.getPlayerPoke().size() - 1);
			// 放进出牌的集合中
			self.getOutPoke().add(p);
			self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
		}
	}

	// 专门出对子的方法
	public static void autoDoubleOutPoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();
		if (prior.getOutPoke().size() != 0) {// 上家的出牌集合不为空,说明上家就是出了对子
			// 判断条件i > 0 是因为防止下标越界,因为有可能是最后一张牌是个对子,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的两张
				if (p1.getNumber() == p2.getNumber()) {// 两张的面值一样,说明是对子
					if (p1.getNumber() > prior.getOutPoke().get(0).getNumber()) {
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

		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {// 上家的出牌集合为空,并且下家的出牌集合不为空,说明上家要不起下家
			// 判断条件i > 0 是因为防止下标越界,因为有可能是最后一张牌是个对子,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的两张
				if (p1.getNumber() == p2.getNumber()) {// 两张的面值一样,说明是对子
					if (p1.getNumber() > next.getOutPoke().get(0).getNumber()) {
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
	public static void autoTripleOutPoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();
		if (prior.getOutPoke().size() != 0) {// 上家的出牌集合不为空,说明上家就是出了三张
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的第二张
				Poke p3 = self.getPlayerPoke().get(i - 2); // 相邻的第三张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 三张的面值一样
					if (p1.getNumber() > prior.getOutPoke().get(0).getNumber()) {
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

		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {// 上家的出牌集合为空,并且下家的出牌集合不为空,说明上家要不起下家
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的两张
				Poke p3 = self.getPlayerPoke().get(i - 2);// 相邻的两张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 两张的面值一样,说明是对子
					if (p1.getNumber() > next.getOutPoke().get(0).getNumber()) {
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
	public static void autoTripleAndOneOutPoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();
		if (prior.getOutPoke().size() != 0) {// 上家的出牌集合不为空,说明上家就是出了三带一
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的第二张
				Poke p3 = self.getPlayerPoke().get(i - 2); // 相邻的第三张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 三张的面值一样
					if (p1.getNumber() > prior.getOutPoke().get(2).getNumber()) {
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
				// 大不起我的放回去
				for (int i = 0; i < self.getOutPoke().size(); i++) {
					self.getPlayerPoke().add(self.getOutPoke().get(i));
					self.getOutPoke().remove(i);
				}
				Util.pokeSort(self.getPlayerPoke());
				Util.isPoke = 2;
			} else {// 不为空表示有牌
				Poke p = self.getPlayerPoke().get(self.getPlayerPoke().size() - 1);// 最小的一张牌作为带的1
				if (p.getNumber() != self.getOutPoke().get(0).getNumber()) {// 判断带进去的一张,它和前面三张不一样才是三代一,否则就是炸弹
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
				} 
//				else {
//					self.getOutPoke().add(p);
//					self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
//					OutPoke.score *= 4;// 炸弹翻四倍
//				}

			}

		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {// 上家的出牌集合为空,并且下家的出牌集合不为空,说明上家要不起下家
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的两张
				Poke p3 = self.getPlayerPoke().get(i - 2);// 相邻的两张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 两张的面值一样,说明是对子
					if (p1.getNumber() > next.getOutPoke().get(2).getNumber()) {// 第三张一定是三张一样的其中一个
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
				// 大不起我的放回去
				for (int i = 0; i < self.getOutPoke().size(); i++) {
					self.getPlayerPoke().add(self.getOutPoke().get(i));
					self.getOutPoke().remove(i);
				}
				Util.pokeSort(self.getPlayerPoke());
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
	public static void autoTripleAndTwoOutPoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();
		if (prior.getOutPoke().size() != 0) {// 上家的出牌集合不为空,说明上家就是出了三带二
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的第二张
				Poke p3 = self.getPlayerPoke().get(i - 2); // 相邻的第三张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 三张的面值一样
					if (p1.getNumber() > prior.getOutPoke().get(2).getNumber()) {
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
				// 大不起我的放回去
				for (int i = 0; i < self.getOutPoke().size(); i++) {
					self.getPlayerPoke().add(self.getOutPoke().get(i));
					self.getOutPoke().remove(i);
				}
				Util.pokeSort(self.getPlayerPoke());
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
						break;// 找到了就跳出循环
					}
				}
				if (self.getOutPoke().size() != 5) {// 出牌集合的size不满足5,说明要不起
					Util.isPoke = 2;
				}
			}

		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {// 上家的出牌集合为空,并且下家的出牌集合不为空,说明上家要不起下家
			// 判断条件i > 1 是因为防止下标越界,因为有可能是最后3张牌是个一样的,不存在-1的下标
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// 最右边开始
				Poke p2 = self.getPlayerPoke().get(i - 1);// 相邻的两张
				Poke p3 = self.getPlayerPoke().get(i - 2);// 相邻的两张
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// 三张的面值一样
					if (p1.getNumber() > next.getOutPoke().get(2).getNumber()) {
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
				// 大不起我的放回去
				for (int i = 0; i < self.getOutPoke().size(); i++) {
					self.getPlayerPoke().add(self.getOutPoke().get(i));
					self.getOutPoke().remove(i);
				}
				Util.pokeSort(self.getPlayerPoke());
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
						break;// 找到了就跳出循环
					}
				}
				if (self.getOutPoke().size() != 5) {// 出牌集合的size不满足5,说明要不起
					Util.isPoke = 2;
				}
			}
		}
	}

	// 顺子的方法
	public static void continuePoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();
		// 上一家出牌几个不为空,说明有牌
		if (prior.getOutPoke().size() != 0) {
			Util.pokeSort(prior.getOutPoke());// 给上一家出牌集合排序,安全一点
			int location = -1;

			// 首先判断对方顺了几张牌
			int count = prior.getOutPoke().size();
			int i = self.getPlayerPoke().size() - 1;
			if (self.getPlayerPoke().size() >= count) {// 说明手里的牌够再进来
				while (location != 0) {
					if (OutPoke.temp.size() == 0) {
						for (; i >= 0; i--) {
							Poke p = self.getPlayerPoke().get(i);
							if (p.getNumber() > prior.getOutPoke().get(prior.getOutPoke().size() - 1).getNumber()) {
								OutPoke.temp.add(p);
								OutPoke.index.add(i);
								break;
							}
						}
						if (OutPoke.temp.size() == 0) {// 说明最小的一张都比不过
							Util.isPoke = 2;// 要不起
							break;
						}
					} else {
						for (int j = i; j >= 0; j--) {
							location = j;
							Poke p = self.getPlayerPoke().get(j);
							if (p.getNumber() - OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber() == 1) {
								OutPoke.temp.add(p);
								OutPoke.index.add(j);
								break;
							}
							// 说明中间断开了
							if (p.getNumber() - OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber() > 1) {
								OutPoke.temp.clear();
								OutPoke.index.clear();
								i = j;
								break;
							}
						}
						if (OutPoke.temp.size() == count) {
							break;
						}
					}

				}
				// 当temp中的值有count一样就说明有牌要的起
				if (OutPoke.temp.size() == count) {
					for (int j = OutPoke.temp.size() - 1; j >= 0; j--) {
						Poke p = OutPoke.temp.get(j);
						self.getOutPoke().add(p);
					}
					for (int j = 0; j < OutPoke.index.size(); j++) {
						int x = OutPoke.index.get(j);
						self.getPlayerPoke().remove(x);
					}
					Util.pokeSort(self.getOutPoke());
					Util.pokeSort(self.getPlayerPoke());
					OutPoke.temp.clear();
					OutPoke.index.clear();
				} else {
					Util.isPoke = 2;// 要不起
				}
			} else {
				Util.isPoke = 2;
			}
		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {
			Util.pokeSort(next.getOutPoke());// 给上一家出牌集合排序,安全一点
			int location = -1;

			// 首先判断对方顺了几张牌
			int count = next.getOutPoke().size();
			int i = self.getPlayerPoke().size() - 1;
			if (self.getPlayerPoke().size() >= count) {// 说明手里的牌够再进来
				while (location != 0) {
					if (OutPoke.temp.size() == 0) {
						for (; i >= 0; i--) {
							Poke p = self.getPlayerPoke().get(i);
							if (p.getNumber() > next.getOutPoke().get(next.getOutPoke().size() - 1).getNumber()) {
								OutPoke.temp.add(p);
								OutPoke.index.add(i);
								break;
							}
						}
						if (OutPoke.temp.size() == 0) {// 说明最小的一张都比不过
							Util.isPoke = 2;// 要不起
							break;
						}
					} else {
						for (int j = i; j >= 0; j--) {
							location = j;
							Poke p = self.getPlayerPoke().get(j);
							if (p.getNumber() - OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber() == 1) {
								OutPoke.temp.add(p);
								OutPoke.index.add(j);
								break;
							}
							// 说明中间断开了
							if (p.getNumber() - OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber() > 1) {
								OutPoke.temp.clear();
								OutPoke.index.clear();
								i = j;
								break;
							}
						}
						if (OutPoke.temp.size() == count) {
							break;
						}
					}

				}
				// 当temp中的值有count一样就说明有牌要的起
				if (OutPoke.temp.size() == count) {
					for (int j = OutPoke.temp.size() - 1; j >= 0; j--) {
						Poke p = OutPoke.temp.get(j);
						self.getOutPoke().add(p);
					}
					for (int j = 0; j < OutPoke.index.size(); j++) {
						int x = OutPoke.index.get(j);
						self.getPlayerPoke().remove(x);
					}
					Util.pokeSort(self.getOutPoke());
					Util.pokeSort(self.getPlayerPoke());
					OutPoke.temp.clear();
					OutPoke.index.clear();
				} else {
					Util.isPoke = 2;// 要不起
				}
			} else {
				Util.isPoke = 2;
			}
		}
	}

	// 双顺出牌方法
	public static void continueDoublePoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();

		if (prior.getOutPoke().size() != 0) {
			int count = prior.getOutPoke().size();
			Util.pokeSort(prior.getOutPoke());// 给上一家出牌集合排序,安全一点
			int location = -1;
			int i = self.getPlayerPoke().size() - 1;
			if (self.getPlayerPoke().size() >= count) {// 说明手里的牌够再进来
				while (location != 0) {
					if (OutPoke.temp.size() == 0) {
						for (; i >= 0; i--) {
							Poke p = self.getPlayerPoke().get(i);
							if (p.getNumber() > prior.getOutPoke().get(prior.getOutPoke().size() - 1).getNumber()) {
								OutPoke.temp.add(p);
								OutPoke.index.add(i);
								break;
							}
						}
						if (OutPoke.temp.size() == 0) {// 说明最小的一张都比不过
							Util.isPoke = 2;// 要不起
							break;
						}
					} else {
						for (int j = i; j >= 0; j--) {
							location = j;
							Poke p = self.getPlayerPoke().get(j);
							if (p.getNumber() == OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber()) {
								if (OutPoke.temp.size() == 1 || OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber()
										- OutPoke.temp.get(OutPoke.temp.size() - 2).getNumber() == 1) {
									OutPoke.temp.add(p);
									OutPoke.index.add(j);
									if (OutPoke.temp.size() == count) {
										break;
									}
								}
							} else if (p.getNumber() - OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber() == 1
									&& OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber() == OutPoke.temp
											.get(OutPoke.temp.size() - 2).getNumber()) {
								OutPoke.temp.add(p);
								OutPoke.index.add(j);
								if (OutPoke.temp.size() == count) {
									break;
								}
							} else if (p.getNumber() - OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber() > 1) {
								OutPoke.temp.clear();
								OutPoke.index.clear();
								i = j;
								break;
							}
						}
						if (OutPoke.temp.size() == count) {
							break;
						}
					}

				}
				// 当temp中的值有count一样就说明有牌要的起
				if (OutPoke.temp.size() == count) {
					for (int j = OutPoke.temp.size() - 1; j >= 0; j--) {
						Poke p = OutPoke.temp.get(j);
						self.getOutPoke().add(p);
					}
					for (int j = 0; j < OutPoke.index.size(); j++) {
						int x = OutPoke.index.get(j);
						self.getPlayerPoke().remove(x);
					}
					Util.pokeSort(self.getOutPoke());
					Util.pokeSort(self.getPlayerPoke());
					OutPoke.temp.clear();
					OutPoke.index.clear();
				} else {
					Util.isPoke = 2;// 要不起
				}
			} else {
				Util.isPoke = 2;
			}
		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {
			int count = next.getOutPoke().size();
			Util.pokeSort(next.getOutPoke());// 给上一家出牌集合排序,安全一点
			int location = -1;
			int i = self.getPlayerPoke().size() - 1;
			if (self.getPlayerPoke().size() >= count) {// 说明手里的牌够再进来
				while (location != 0) {
					if (OutPoke.temp.size() == 0) {
						for (; i >= 0; i--) {
							Poke p = self.getPlayerPoke().get(i);
							if (p.getNumber() > next.getOutPoke().get(next.getOutPoke().size() - 1).getNumber()) {
								OutPoke.temp.add(p);
								OutPoke.index.add(i);
								break;
							}
						}
						if (OutPoke.temp.size() == 0) {// 说明最小的一张都比不过
							Util.isPoke = 2;// 要不起
							break;
						}
					} else {
						for (int j = i; j >= 0; j--) {
							location = j;
							Poke p = self.getPlayerPoke().get(j);
							if (p.getNumber() == OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber()) {
								if (OutPoke.temp.size() == 1 || OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber()
										- OutPoke.temp.get(OutPoke.temp.size() - 2).getNumber() == 1) {
									OutPoke.temp.add(p);
									OutPoke.index.add(j);
									if (OutPoke.temp.size() == count) {
										break;
									}
								}
							} else if (p.getNumber() - OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber() == 1
									&& OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber() == OutPoke.temp
											.get(OutPoke.temp.size() - 2).getNumber()) {
								OutPoke.temp.add(p);
								OutPoke.index.add(j);
								if (OutPoke.temp.size() == count) {
									break;
								}
							} else if (p.getNumber() - OutPoke.temp.get(OutPoke.temp.size() - 1).getNumber() > 1) {
								OutPoke.temp.clear();
								OutPoke.index.clear();
								i = j;
								break;
							}
						}
						if (OutPoke.temp.size() == count) {
							break;
						}
					}

				}
				// 当temp中的值有count一样就说明有牌要的起
				if (OutPoke.temp.size() == count) {
					for (int j = OutPoke.temp.size() - 1; j >= 0; j--) {
						Poke p = OutPoke.temp.get(j);
						self.getOutPoke().add(p);
					}
					for (int j = 0; j < OutPoke.index.size(); j++) {
						int x = OutPoke.index.get(j);
						self.getPlayerPoke().remove(x);
					}
					Util.pokeSort(self.getOutPoke());
					Util.pokeSort(self.getPlayerPoke());
					OutPoke.temp.clear();
					OutPoke.index.clear();
				} else {
					Util.isPoke = 2;// 要不起
				}
			} else {
				Util.isPoke = 2;
			}
		}
	}
	
	//炸弹的出法
	public static void boomPoke(Player prior, Player next, Player self){
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();
		
		if(prior.getOutPoke().size() != 0){
			Util.pokeSort(prior.getOutPoke());
			for (int i = self.getPlayerPoke().size() - 1; i > 2;i--) {//找四张一样的
				Poke p1 = self.getPlayerPoke().get(i);
				Poke p2 = self.getPlayerPoke().get(i - 1);
				Poke p3 = self.getPlayerPoke().get(i - 2);
				Poke p4 = self.getPlayerPoke().get(i - 3);				
				if(p1.getNumber() == p2.getNumber() 
					&& p1.getNumber() == p3.getNumber() 
					&& p1.getNumber() == p4.getNumber()){//找到四张一样的
					if(prior.getOutPoke().size() == 4 
							&& prior.getOutPoke().get(0).getNumber() != prior.getOutPoke().get(3).getNumber()){//上一家有四张牌,并且不一样,说明不是炸弹
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getOutPoke().add(p3);// 添加到出牌集合中
						self.getOutPoke().add(p4);
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 3);//移除
						Util.type = 8;
						Util.isPoke = -1;// 关闭要不起的按钮
						OutPoke.score *= 4;
						break;
					}else if(prior.getOutPoke().size() == 4
							&& prior.getOutPoke().get(0).getNumber() == prior.getOutPoke().get(1).getNumber()
							&& prior.getOutPoke().get(0).getNumber() == prior.getOutPoke().get(2).getNumber()
							&& prior.getOutPoke().get(0).getNumber() == prior.getOutPoke().get(3).getNumber()){//上一家有四张牌,并且一样,说明上一家就是炸弹,就要看下自己的炸弹是不是比上一家大
						if(p1.getNumber() > prior.getOutPoke().get(0).getNumber()){
							self.getOutPoke().add(p1);// 添加到出牌集合中
							self.getOutPoke().add(p2);// 添加到出牌集合中
							self.getOutPoke().add(p3);// 添加到出牌集合中
							self.getOutPoke().add(p4);
							self.getPlayerPoke().remove(i);// 从手牌集合中移除
							self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
							self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
							self.getPlayerPoke().remove(i - 3);//移除
							Util.type = 8;
							Util.isPoke = -1;// 关闭要不起的按钮
							OutPoke.score *= 4;
							break;
						}else{
//							Util.isPoke = 2;//要不起
							continue;
						}
						
					}else if(prior.getOutPoke().size() == 2
							&& prior.getOutPoke().get(0).getNumber() == 100
							&& prior.getOutPoke().get(1).getNumber() == 99){//上一家有两张牌,并且是王炸的时候,直接要不起
						Util.isPoke = 2;//要不起
						break;
					}else{//其他情况可以出炸弹
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getOutPoke().add(p3);// 添加到出牌集合中
						self.getOutPoke().add(p4);
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 3);//移除
						Util.type = 8;
						Util.isPoke = -1;// 关闭要不起的按钮
						OutPoke.score *= 4;
						break;
					}
					
				}
			}
			if(self.getOutPoke().size() == 0){
				Util.isPoke = 2;//要不起
			}
			
		}else if(prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0){
			Util.pokeSort(next.getOutPoke());
			for (int i = self.getPlayerPoke().size() - 1; i > 2;i--) {//找四张一样的
				Poke p1 = self.getPlayerPoke().get(i);
				Poke p2 = self.getPlayerPoke().get(i - 1);
				Poke p3 = self.getPlayerPoke().get(i - 2);
				Poke p4 = self.getPlayerPoke().get(i - 3);				
				if(p1.getNumber() == p2.getNumber() 
					&& p1.getNumber() == p3.getNumber() 
					&& p1.getNumber() == p4.getNumber()){//找到四张一样的
					if(next.getOutPoke().size() == 4 
							&& next.getOutPoke().get(0).getNumber() != next.getOutPoke().get(3).getNumber()){//上一家有四张牌,并且不一样,说明不是炸弹
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getOutPoke().add(p3);// 添加到出牌集合中
						self.getOutPoke().add(p4);
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 3);//移除
						Util.type = 8;
						Util.isPoke = -1;// 关闭要不起的按钮
						OutPoke.score *= 4;
						break;
					}else if(next.getOutPoke().size() == 4
							&& next.getOutPoke().get(0).getNumber() == next.getOutPoke().get(1).getNumber()
							&& next.getOutPoke().get(0).getNumber() == next.getOutPoke().get(2).getNumber()
							&& next.getOutPoke().get(0).getNumber() == next.getOutPoke().get(3).getNumber()){//上一家有四张牌,并且一样,说明上一家就是炸弹,就要看下自己的炸弹是不是比上一家大
						if(p1.getNumber() > next.getOutPoke().get(0).getNumber()){
							self.getOutPoke().add(p1);// 添加到出牌集合中
							self.getOutPoke().add(p2);// 添加到出牌集合中
							self.getOutPoke().add(p3);// 添加到出牌集合中
							self.getOutPoke().add(p4);
							self.getPlayerPoke().remove(i);// 从手牌集合中移除
							self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
							self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
							self.getPlayerPoke().remove(i - 3);//移除
							Util.type = 8;
							Util.isPoke = -1;// 关闭要不起的按钮
							OutPoke.score *= 4;
							break;
						}else{
							Util.isPoke = 2;//要不起
							break;
						}
						
					}else if(next.getOutPoke().size() == 2
							&& next.getOutPoke().get(0).getNumber() == 100
							&& next.getOutPoke().get(1).getNumber() == 99){//上一家有两张牌,并且是王炸的时候,直接要不起
						Util.isPoke = 2;//要不起
						break;
					}else{//其他情况可以出炸弹
						self.getOutPoke().add(p1);// 添加到出牌集合中
						self.getOutPoke().add(p2);// 添加到出牌集合中
						self.getOutPoke().add(p3);// 添加到出牌集合中
						self.getOutPoke().add(p4);
						self.getPlayerPoke().remove(i);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 1);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 2);// 从手牌集合中移除
						self.getPlayerPoke().remove(i - 3);//移除
						Util.type = 8;
						Util.isPoke = -1;// 关闭要不起的按钮
						OutPoke.score *= 4;
						break;
					}
					
				}
			}
			if(self.getOutPoke().size() == 0){
				Util.isPoke = 2;//要不起
			}
		}
	}
	
	//王炸
	public static void doubleBoom(Player self){
		Util.isPoke = -1;// 关闭要不起的按钮
		self.getOutPoke().clear();
		if(self.getPlayerPoke().get(0).getNumber() == 100 && self.getPlayerPoke().get(1).getNumber() == 99){
			self.getOutPoke().add(self.getPlayerPoke().get(0));
			self.getOutPoke().add(self.getPlayerPoke().get(1));
			self.getPlayerPoke().remove(0);
			self.getPlayerPoke().remove(0);
			OutPoke.score *= 4;
		}else{
			Util.isPoke = 2;
		}
	}
}














