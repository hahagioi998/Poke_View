package com.qiu.util;

import java.util.ArrayList;

import com.qiu.model.Player;
import com.qiu.model.Poke;

//дһ�����ڳ��Ƶ���,��Ҫ����ʵ�ֳ��ƺͻ����ϵķ������㷽��
public class OutPoke {

	public static ArrayList<Poke> temp = new ArrayList<Poke>();
	public static ArrayList<Integer> index = new ArrayList<Integer>();

	public static int score = 0;// �е����ķ���,���ϵ��Ƶı���
	public static boolean isTrusteeship = false;// �йܵĿ���,false��ʾ�Լ���,true��ʾ�йܿ���

	// ���Ʒ����ķ���
	public static void baseMultiple(ArrayList<Poke> p) {
		/*
		 * ���Ƶķ���:��������/��2����/ͬ����˳������/���ź�˫��4�� ͬʱ�����Ը߱�Ϊ׼
		 */
		if ((Util.pokeList.get(0).getNumber() == 100 && Util.pokeList.get(1).getNumber() == 99)
				|| (Util.pokeList.get(0).getNumber() == Util.pokeList.get(1).getNumber()
						&& Util.pokeList.get(0).getNumber() == Util.pokeList.get(2).getNumber())) {
			OutPoke.score *= 4;// 4��
		} else if ((p.get(0).getName().equals(p.get(1).getName()) && p.get(0).getName().equals(p.get(2).getName()))
				|| (p.get(0).getNumber() - p.get(1).getNumber() == 1)
						&& (p.get(0).getNumber() - p.get(2).getNumber() == 2)) {
			OutPoke.score *= 3;// 3��
		} else if (p.get(0).getNumber() == 100 || p.get(0).getNumber() == 99
				|| (p.get(0).getNumber() == 22 && p.get(1).getNumber() == 22)) {
			OutPoke.score *= 2;// 2��
		}
	}

	// �ֶ����Ʒ���
	public static void handAction() {
		if (Util.isPoke == 1) {
			// ������Լ��ĳ��Ƽ���
			Util.playerOne.getOutPoke().clear();
			// �������������ƷŽ����Ƽ�����
			for (int i = Util.playerOne.getPlayerPoke().size() - 1; i >= 0; i--) {
				Poke p = Util.playerOne.getPlayerPoke().get(i);
				if (p.getPokeY() == 485) {
					Util.playerOne.getOutPoke().add(p);// ���������ƷŽ�
					Util.playerOne.getPlayerPoke().remove(i);// ���������Ƴ�����
					// i = -1;// ���Ƽ����еĳ����б仯,��Ҫ���¿�ʼ
				}
			}
			// ������û�г��ƶ���������
			Util.pokeSort(Util.playerOne.getOutPoke());
			Util.pokeSort(Util.playerOne.getPlayerPoke());
			// ������������
			Util.setCoordinate(Util.playerOne.getPlayerPoke());
			Util.callPlayer = 1;
		} else if (Util.isPoke == 0) {
			Util.playerOne.getOutPoke().clear();
			Util.callPlayer = 1;// ��ֱ����ת��һ����
		}
	}

	// �������ܳ��� �������� �ϼ� �¼� ����
	public static void autoSoloOutPoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// �ر�Ҫ����İ�ť
		// ����ϼҵĳ��Ƽ���Ϊ��,�¼ҵĳ��Ƽ��ϲ�Ϊ��,��˵���ϼ�ѡ�񲻳���
		if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {
			self.getOutPoke().clear();
			// �ٱ���һ��,������ű��¼Ҵ���ƾ�ֱ�ӳ�
			for (int i = self.getPlayerPoke().size() - 1; i >= 0; i--) {
				if (self.getPlayerPoke().get(i).getNumber() > next.getOutPoke().get(0).getNumber()) {
					Poke p = self.getPlayerPoke().get(i);
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(i);
					break;
				}
			}
			if (self.getOutPoke().size() == 0) {
				Util.isPoke = 2;// ��ʾ����Ҫ����
			}
		} else if (prior.getOutPoke().size() != 0) {// ��һ�ҵĳ��Ƽ��ϲ�Ϊ��,˵���г���
			// ��������ҵĳ��Ƽ����е���
			self.getOutPoke().clear();
			// �ٱ���һ��,������ű��ϼҴ���ƾ�ֱ�ӳ�
			for (int i = self.getPlayerPoke().size() - 1; i >= 0; i--) {
				if (self.getPlayerPoke().get(i).getNumber() > prior.getOutPoke().get(0).getNumber()) {
					Poke p = self.getPlayerPoke().get(i);
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(i);
					break;
				}
			}
			if (self.getOutPoke().size() == 0) {// ��������ȻΪ�ձ�ʾҪ����
				Util.isPoke = 2;// ��ʾҪ����
			}
		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() == 0) {// �������ǰ���ҵĳ��Ƽ��϶��ǿյĻ�,�����Լ���������һ����
			// ��������ҵĳ��Ƽ����е���
			self.getOutPoke().clear();
			// �����ó���С����
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Poke p = self.getPlayerPoke().get(self.getPlayerPoke().size() - 1);
			// �Ž����Ƶļ�����
			self.getOutPoke().add(p);
			self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
		}
	}

	// ר�ų����ӵķ���
	public static void autoDoubleOutPoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// �ر�Ҫ����İ�ť
		self.getOutPoke().clear();
		if (prior.getOutPoke().size() != 0) {// �ϼҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼҾ��ǳ��˶���
			// �ж�����i > 0 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������һ�����Ǹ�����,������-1���±�
			for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// ���ұ߿�ʼ
				Poke p2 = self.getPlayerPoke().get(i - 1);// ���ڵ�����
				if (p1.getNumber() == p2.getNumber()) {// ���ŵ���ֵһ��,˵���Ƕ���
					if (p1.getNumber() > prior.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						break;
					}
				}
			}
			if (self.getOutPoke().size() == 0) {// ���ҳ��Ƽ���Ϊ��,��˵��Ҫ����
				Util.isPoke = 2;
			}

		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {// �ϼҵĳ��Ƽ���Ϊ��,�����¼ҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼ�Ҫ�����¼�
			// �ж�����i > 0 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������һ�����Ǹ�����,������-1���±�
			for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// ���ұ߿�ʼ
				Poke p2 = self.getPlayerPoke().get(i - 1);// ���ڵ�����
				if (p1.getNumber() == p2.getNumber()) {// ���ŵ���ֵһ��,˵���Ƕ���
					if (p1.getNumber() > next.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						break;
					}
				}
			}
			if (self.getOutPoke().size() == 0) {// ���ҳ��Ƽ���Ϊ��,��˵��Ҫ����
				Util.isPoke = 2;
			}
		}
	}

	// ר�ų�����һ���ķ���
	public static void autoTripleOutPoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// �ر�Ҫ����İ�ť
		self.getOutPoke().clear();
		if (prior.getOutPoke().size() != 0) {// �ϼҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼҾ��ǳ�������
			// �ж�����i > 1 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������3�����Ǹ�һ����,������-1���±�
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// ���ұ߿�ʼ
				Poke p2 = self.getPlayerPoke().get(i - 1);// ���ڵĵڶ���
				Poke p3 = self.getPlayerPoke().get(i - 2); // ���ڵĵ�����
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// ���ŵ���ֵһ��
					if (p1.getNumber() > prior.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
						break;
					}
				}
			}
			if (self.getOutPoke().size() == 0) {// ���ҳ��Ƽ���Ϊ��,��˵��Ҫ����
				Util.isPoke = 2;
			}

		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {// �ϼҵĳ��Ƽ���Ϊ��,�����¼ҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼ�Ҫ�����¼�
			// �ж�����i > 1 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������3�����Ǹ�һ����,������-1���±�
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// ���ұ߿�ʼ
				Poke p2 = self.getPlayerPoke().get(i - 1);// ���ڵ�����
				Poke p3 = self.getPlayerPoke().get(i - 2);// ���ڵ�����
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// ���ŵ���ֵһ��,˵���Ƕ���
					if (p1.getNumber() > next.getOutPoke().get(0).getNumber()) {
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
						break;
					}
				}
			}
			if (self.getOutPoke().size() == 0) {// ���ҳ��Ƽ���Ϊ��,��˵��Ҫ����
				Util.isPoke = 2;
			}
		}
	}

	// ר�ų�����һ�ķ���
	public static void autoTripleAndOneOutPoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// �ر�Ҫ����İ�ť
		self.getOutPoke().clear();
		if (prior.getOutPoke().size() != 0) {// �ϼҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼҾ��ǳ�������һ
			// �ж�����i > 1 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������3�����Ǹ�һ����,������-1���±�
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// ���ұ߿�ʼ
				Poke p2 = self.getPlayerPoke().get(i - 1);// ���ڵĵڶ���
				Poke p3 = self.getPlayerPoke().get(i - 2); // ���ڵĵ�����
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// ���ŵ���ֵһ��
					if (p1.getNumber() > prior.getOutPoke().get(2).getNumber()) {
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
						break;
					}
				}
			}
			// ���ҳ��Ƽ���Ϊ��,��˵��Ҫ����,����һ��������Ǹպ�ʣ������һ����
			if (self.getOutPoke().size() == 0 || self.getPlayerPoke().size() == 0) {
				// �����ҵķŻ�ȥ
				for (int i = 0; i < self.getOutPoke().size(); i++) {
					self.getPlayerPoke().add(self.getOutPoke().get(i));
					self.getOutPoke().remove(i);
				}
				Util.pokeSort(self.getPlayerPoke());
				Util.isPoke = 2;
			} else {// ��Ϊ�ձ�ʾ����
				Poke p = self.getPlayerPoke().get(self.getPlayerPoke().size() - 1);// ��С��һ������Ϊ����1
				if (p.getNumber() != self.getOutPoke().get(0).getNumber()) {// �жϴ���ȥ��һ��,����ǰ�����Ų�һ����������һ,�������ը��
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
				} 
//				else {
//					self.getOutPoke().add(p);
//					self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
//					OutPoke.score *= 4;// ը�����ı�
//				}

			}

		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {// �ϼҵĳ��Ƽ���Ϊ��,�����¼ҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼ�Ҫ�����¼�
			// �ж�����i > 1 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������3�����Ǹ�һ����,������-1���±�
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// ���ұ߿�ʼ
				Poke p2 = self.getPlayerPoke().get(i - 1);// ���ڵ�����
				Poke p3 = self.getPlayerPoke().get(i - 2);// ���ڵ�����
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// ���ŵ���ֵһ��,˵���Ƕ���
					if (p1.getNumber() > next.getOutPoke().get(2).getNumber()) {// ������һ��������һ��������һ��
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
						break;
					}
				}
			}
			// ���ҳ��Ƽ���Ϊ��,��˵��Ҫ����,����һ��������Ǹպ�ʣ������һ����
			if (self.getOutPoke().size() == 0 || self.getPlayerPoke().size() == 0) {
				// �����ҵķŻ�ȥ
				for (int i = 0; i < self.getOutPoke().size(); i++) {
					self.getPlayerPoke().add(self.getOutPoke().get(i));
					self.getOutPoke().remove(i);
				}
				Util.pokeSort(self.getPlayerPoke());
				Util.isPoke = 2;
			} else {// ��Ϊ�ձ�ʾ����
				Poke p = self.getPlayerPoke().get(self.getPlayerPoke().size() - 1);// ��С��һ������Ϊ����1
				if (p.getNumber() != self.getOutPoke().get(0).getNumber()) {// �жϴ���ȥ��һ��,����ǰ�����Ų�һ����������һ,�������ը��
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
				} else {
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
					OutPoke.score *= 4;// ը�����ı�
				}
			}
		}
	}

	// ר�ų��������ķ���
	public static void autoTripleAndTwoOutPoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// �ر�Ҫ����İ�ť
		self.getOutPoke().clear();
		if (prior.getOutPoke().size() != 0) {// �ϼҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼҾ��ǳ���������
			// �ж�����i > 1 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������3�����Ǹ�һ����,������-1���±�
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// ���ұ߿�ʼ
				Poke p2 = self.getPlayerPoke().get(i - 1);// ���ڵĵڶ���
				Poke p3 = self.getPlayerPoke().get(i - 2); // ���ڵĵ�����
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// ���ŵ���ֵһ��
					if (p1.getNumber() > prior.getOutPoke().get(2).getNumber()) {
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
						break;
					}
				}
			}
			// ���ҳ��Ƽ���Ϊ��,��˵��Ҫ����,����һ��������Ǹպ�ʣ������,�����Ͳ�����������
			if (self.getOutPoke().size() == 0 || self.getPlayerPoke().size() < 2) {
				// �����ҵķŻ�ȥ
				for (int i = 0; i < self.getOutPoke().size(); i++) {
					self.getPlayerPoke().add(self.getOutPoke().get(i));
					self.getOutPoke().remove(i);
				}
				Util.pokeSort(self.getPlayerPoke());
				Util.isPoke = 2;
			} else {// ��Ϊ�ձ�ʾ����
				// ��С��������Ϊ���Ķ���
				for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
					Poke p1 = self.getPlayerPoke().get(i);
					Poke p2 = self.getPlayerPoke().get(i - 1);
					if (p1.getNumber() == p2.getNumber()) {
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						break;// �ҵ��˾�����ѭ��
					}
				}
				if (self.getOutPoke().size() != 5) {// ���Ƽ��ϵ�size������5,˵��Ҫ����
					Util.isPoke = 2;
				}
			}

		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {// �ϼҵĳ��Ƽ���Ϊ��,�����¼ҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼ�Ҫ�����¼�
			// �ж�����i > 1 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������3�����Ǹ�һ����,������-1���±�
			for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
				Poke p1 = self.getPlayerPoke().get(i);// ���ұ߿�ʼ
				Poke p2 = self.getPlayerPoke().get(i - 1);// ���ڵ�����
				Poke p3 = self.getPlayerPoke().get(i - 2);// ���ڵ�����
				if (p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()) {// ���ŵ���ֵһ��
					if (p1.getNumber() > next.getOutPoke().get(2).getNumber()) {
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
						break;
					}
				}
			}
			// ���ҳ��Ƽ���Ϊ��,��˵��Ҫ����,����һ��������Ǹպ�ʣ������,�����Ͳ�����������
			if (self.getOutPoke().size() == 0 || self.getPlayerPoke().size() < 2) {
				// �����ҵķŻ�ȥ
				for (int i = 0; i < self.getOutPoke().size(); i++) {
					self.getPlayerPoke().add(self.getOutPoke().get(i));
					self.getOutPoke().remove(i);
				}
				Util.pokeSort(self.getPlayerPoke());
				Util.isPoke = 2;
			} else {// ��Ϊ�ձ�ʾ����
				// ��С��������Ϊ���Ķ���
				for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
					Poke p1 = self.getPlayerPoke().get(i);
					Poke p2 = self.getPlayerPoke().get(i - 1);
					if (p1.getNumber() == p2.getNumber()) {
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						break;// �ҵ��˾�����ѭ��
					}
				}
				if (self.getOutPoke().size() != 5) {// ���Ƽ��ϵ�size������5,˵��Ҫ����
					Util.isPoke = 2;
				}
			}
		}
	}

	// ˳�ӵķ���
	public static void continuePoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// �ر�Ҫ����İ�ť
		self.getOutPoke().clear();
		// ��һ�ҳ��Ƽ�����Ϊ��,˵������
		if (prior.getOutPoke().size() != 0) {
			Util.pokeSort(prior.getOutPoke());// ����һ�ҳ��Ƽ�������,��ȫһ��
			int location = -1;

			// �����ж϶Է�˳�˼�����
			int count = prior.getOutPoke().size();
			int i = self.getPlayerPoke().size() - 1;
			if (self.getPlayerPoke().size() >= count) {// ˵��������ƹ��ٽ���
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
						if (OutPoke.temp.size() == 0) {// ˵����С��һ�Ŷ��Ȳ���
							Util.isPoke = 2;// Ҫ����
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
							// ˵���м�Ͽ���
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
				// ��temp�е�ֵ��countһ����˵������Ҫ����
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
					Util.isPoke = 2;// Ҫ����
				}
			} else {
				Util.isPoke = 2;
			}
		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {
			Util.pokeSort(next.getOutPoke());// ����һ�ҳ��Ƽ�������,��ȫһ��
			int location = -1;

			// �����ж϶Է�˳�˼�����
			int count = next.getOutPoke().size();
			int i = self.getPlayerPoke().size() - 1;
			if (self.getPlayerPoke().size() >= count) {// ˵��������ƹ��ٽ���
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
						if (OutPoke.temp.size() == 0) {// ˵����С��һ�Ŷ��Ȳ���
							Util.isPoke = 2;// Ҫ����
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
							// ˵���м�Ͽ���
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
				// ��temp�е�ֵ��countһ����˵������Ҫ����
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
					Util.isPoke = 2;// Ҫ����
				}
			} else {
				Util.isPoke = 2;
			}
		}
	}

	// ˫˳���Ʒ���
	public static void continueDoublePoke(Player prior, Player next, Player self) {
		Util.isPoke = -1;// �ر�Ҫ����İ�ť
		self.getOutPoke().clear();

		if (prior.getOutPoke().size() != 0) {
			int count = prior.getOutPoke().size();
			Util.pokeSort(prior.getOutPoke());// ����һ�ҳ��Ƽ�������,��ȫһ��
			int location = -1;
			int i = self.getPlayerPoke().size() - 1;
			if (self.getPlayerPoke().size() >= count) {// ˵��������ƹ��ٽ���
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
						if (OutPoke.temp.size() == 0) {// ˵����С��һ�Ŷ��Ȳ���
							Util.isPoke = 2;// Ҫ����
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
				// ��temp�е�ֵ��countһ����˵������Ҫ����
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
					Util.isPoke = 2;// Ҫ����
				}
			} else {
				Util.isPoke = 2;
			}
		} else if (prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0) {
			int count = next.getOutPoke().size();
			Util.pokeSort(next.getOutPoke());// ����һ�ҳ��Ƽ�������,��ȫһ��
			int location = -1;
			int i = self.getPlayerPoke().size() - 1;
			if (self.getPlayerPoke().size() >= count) {// ˵��������ƹ��ٽ���
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
						if (OutPoke.temp.size() == 0) {// ˵����С��һ�Ŷ��Ȳ���
							Util.isPoke = 2;// Ҫ����
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
				// ��temp�е�ֵ��countһ����˵������Ҫ����
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
					Util.isPoke = 2;// Ҫ����
				}
			} else {
				Util.isPoke = 2;
			}
		}
	}
	
	//ը���ĳ���
	public static void boomPoke(Player prior, Player next, Player self){
		Util.isPoke = -1;// �ر�Ҫ����İ�ť
		self.getOutPoke().clear();
		
		if(prior.getOutPoke().size() != 0){
			Util.pokeSort(prior.getOutPoke());
			for (int i = self.getPlayerPoke().size() - 1; i > 2;i--) {//������һ����
				Poke p1 = self.getPlayerPoke().get(i);
				Poke p2 = self.getPlayerPoke().get(i - 1);
				Poke p3 = self.getPlayerPoke().get(i - 2);
				Poke p4 = self.getPlayerPoke().get(i - 3);				
				if(p1.getNumber() == p2.getNumber() 
					&& p1.getNumber() == p3.getNumber() 
					&& p1.getNumber() == p4.getNumber()){//�ҵ�����һ����
					if(prior.getOutPoke().size() == 4 
							&& prior.getOutPoke().get(0).getNumber() != prior.getOutPoke().get(3).getNumber()){//��һ����������,���Ҳ�һ��,˵������ը��
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p4);
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 3);//�Ƴ�
						Util.type = 8;
						Util.isPoke = -1;// �ر�Ҫ����İ�ť
						OutPoke.score *= 4;
						break;
					}else if(prior.getOutPoke().size() == 4
							&& prior.getOutPoke().get(0).getNumber() == prior.getOutPoke().get(1).getNumber()
							&& prior.getOutPoke().get(0).getNumber() == prior.getOutPoke().get(2).getNumber()
							&& prior.getOutPoke().get(0).getNumber() == prior.getOutPoke().get(3).getNumber()){//��һ����������,����һ��,˵����һ�Ҿ���ը��,��Ҫ�����Լ���ը���ǲ��Ǳ���һ�Ҵ�
						if(p1.getNumber() > prior.getOutPoke().get(0).getNumber()){
							self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
							self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
							self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
							self.getOutPoke().add(p4);
							self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
							self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
							self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
							self.getPlayerPoke().remove(i - 3);//�Ƴ�
							Util.type = 8;
							Util.isPoke = -1;// �ر�Ҫ����İ�ť
							OutPoke.score *= 4;
							break;
						}else{
//							Util.isPoke = 2;//Ҫ����
							continue;
						}
						
					}else if(prior.getOutPoke().size() == 2
							&& prior.getOutPoke().get(0).getNumber() == 100
							&& prior.getOutPoke().get(1).getNumber() == 99){//��һ����������,��������ը��ʱ��,ֱ��Ҫ����
						Util.isPoke = 2;//Ҫ����
						break;
					}else{//����������Գ�ը��
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p4);
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 3);//�Ƴ�
						Util.type = 8;
						Util.isPoke = -1;// �ر�Ҫ����İ�ť
						OutPoke.score *= 4;
						break;
					}
					
				}
			}
			if(self.getOutPoke().size() == 0){
				Util.isPoke = 2;//Ҫ����
			}
			
		}else if(prior.getOutPoke().size() == 0 && next.getOutPoke().size() != 0){
			Util.pokeSort(next.getOutPoke());
			for (int i = self.getPlayerPoke().size() - 1; i > 2;i--) {//������һ����
				Poke p1 = self.getPlayerPoke().get(i);
				Poke p2 = self.getPlayerPoke().get(i - 1);
				Poke p3 = self.getPlayerPoke().get(i - 2);
				Poke p4 = self.getPlayerPoke().get(i - 3);				
				if(p1.getNumber() == p2.getNumber() 
					&& p1.getNumber() == p3.getNumber() 
					&& p1.getNumber() == p4.getNumber()){//�ҵ�����һ����
					if(next.getOutPoke().size() == 4 
							&& next.getOutPoke().get(0).getNumber() != next.getOutPoke().get(3).getNumber()){//��һ����������,���Ҳ�һ��,˵������ը��
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p4);
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 3);//�Ƴ�
						Util.type = 8;
						Util.isPoke = -1;// �ر�Ҫ����İ�ť
						OutPoke.score *= 4;
						break;
					}else if(next.getOutPoke().size() == 4
							&& next.getOutPoke().get(0).getNumber() == next.getOutPoke().get(1).getNumber()
							&& next.getOutPoke().get(0).getNumber() == next.getOutPoke().get(2).getNumber()
							&& next.getOutPoke().get(0).getNumber() == next.getOutPoke().get(3).getNumber()){//��һ����������,����һ��,˵����һ�Ҿ���ը��,��Ҫ�����Լ���ը���ǲ��Ǳ���һ�Ҵ�
						if(p1.getNumber() > next.getOutPoke().get(0).getNumber()){
							self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
							self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
							self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
							self.getOutPoke().add(p4);
							self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
							self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
							self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
							self.getPlayerPoke().remove(i - 3);//�Ƴ�
							Util.type = 8;
							Util.isPoke = -1;// �ر�Ҫ����İ�ť
							OutPoke.score *= 4;
							break;
						}else{
							Util.isPoke = 2;//Ҫ����
							break;
						}
						
					}else if(next.getOutPoke().size() == 2
							&& next.getOutPoke().get(0).getNumber() == 100
							&& next.getOutPoke().get(1).getNumber() == 99){//��һ����������,��������ը��ʱ��,ֱ��Ҫ����
						Util.isPoke = 2;//Ҫ����
						break;
					}else{//����������Գ�ը��
						self.getOutPoke().add(p1);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p2);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p3);// ��ӵ����Ƽ�����
						self.getOutPoke().add(p4);
						self.getPlayerPoke().remove(i);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 2);// �����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 3);//�Ƴ�
						Util.type = 8;
						Util.isPoke = -1;// �ر�Ҫ����İ�ť
						OutPoke.score *= 4;
						break;
					}
					
				}
			}
			if(self.getOutPoke().size() == 0){
				Util.isPoke = 2;//Ҫ����
			}
		}
	}
	
	//��ը
	public static void doubleBoom(Player self){
		Util.isPoke = -1;// �ر�Ҫ����İ�ť
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














