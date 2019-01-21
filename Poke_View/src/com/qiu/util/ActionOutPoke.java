package com.qiu.util;

import java.util.ArrayList;

import com.qiu.model.Player;
import com.qiu.model.Poke;

public class ActionOutPoke {

	
	public static void solo(Player self,Player prior){
		for (int i = 0; i < self.getPlayerPoke().size(); i++) {
			if (self.getPlayerPoke().get(i).getPokeY() == 485) {// ����������������
				if (self.getPlayerPoke().get(i).getNumber() > prior.getOutPoke().get(0)
						.getNumber()) {// �Ƚϴ�С
					Util.isPoke = 1;// 1�ͱ�ʾҪ����
					Util.type = 1;// ��������
				} else {
					self.getPlayerPoke().get(i).setPokeY(500);
					Util.isPoke = -1;// ��ʾѡ�񲻶�,���³���
				}
				break;
			}
		}
	}
	
	public static void doublePoke(Player self,Player prior){
		ArrayList<Poke> temp = new ArrayList<Poke>();// ��ʱһ������,�������������������
		for (int i = 0; i < self.getPlayerPoke().size(); i++) {
			Poke p = self.getPlayerPoke().get(i);// ��ʱ�˿�
			if (p.getPokeY() == 485) {// �����������Ƶ���ֵ�ǲ���һ��
				temp.add(p);
			}
		}
		Util.pokeSort(temp);
		if (temp.get(0).getNumber() == temp.get(1).getNumber()
				&& temp.get(0).getNumber() > prior.getOutPoke().get(0).getNumber()) {// ��ʱ�˿��Ƶ���ֵһ��ʱ���Ƕ���
			Util.type = 2;// �����Լ����˶���
			Util.isPoke = 1;// ���Ƴ�
		} else if (temp.get(0).getNumber() == 100 && temp.get(1).getNumber() == 99) {// ��ը������
			OutPoke.score *= 4;
			Util.type = 8;// ��ը������
			Util.isPoke = 1;
		} else {// �����ʾ�������� ,�������ƹ�λ
			for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
				self.getPlayerPoke().get(i).setPokeY(500);
			}
		}
	}
	
	public static void triple(Player self,Player prior){
		ArrayList<Poke> temp = new ArrayList<Poke>();// ��ʱһ������,�������������������
		for (int i = 0; i < self.getPlayerPoke().size(); i++) {
			Poke p = self.getPlayerPoke().get(i);// ��ʱ�˿�
			if (p.getPokeY() == 485) {// �����������Ƶ���ֵ�ǲ���һ��
				temp.add(p);
			}
		}
		if ((temp.get(0).getNumber() == temp.get(1).getNumber())
				&& (temp.get(0).getNumber() == temp.get(2).getNumber())
				&& (temp.get(0).getNumber() > prior.getOutPoke().get(0).getNumber())) {// ��һ�ź͵ڶ�����ͬ.���Һ͵�������ͬ
			Util.type = 3;// �����Լ���������һ����
			Util.isPoke = 1;// ���Ƴ�
		} else {// �����ʾ�������� ,�������ƹ�λ
			for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
				self.getPlayerPoke().get(i).setPokeY(500);
			}
		}
	}
	
	public static void tripleAndOne(Player self,Player prior){
		if(prior.getOutPoke().get(0).getNumber() 
				!= prior.getOutPoke().get(3).getNumber()){
			ArrayList<Poke> temp = new ArrayList<Poke>();// ��ʱ����
			for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����һ���ҳ�����������
				Poke p = self.getPlayerPoke().get(i);
				if (p.getPokeY() == 485) {
					temp.add(p);
				}
			}
			Util.pokeSort(temp);// ����ʱ�����Ÿ���
			if ((temp.get(0).getNumber() == temp.get(1).getNumber())
					&& (temp.get(0).getNumber() == temp.get(2).getNumber())
					&& (temp.get(0).getNumber() != temp.get(3).getNumber())) {// ��һ������һ����
				if (temp.get(1).getNumber() > prior.getOutPoke().get(2).getNumber()) {
					Util.type = 4;
					Util.isPoke = 1;
				} else {
					for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
						self.getPlayerPoke().get(i).setPokeY(500);
					}
				}
			} else if ((temp.get(1).getNumber() == temp.get(2).getNumber())
					&& (temp.get(1).getNumber() == temp.get(3).getNumber())
					&& (temp.get(1).getNumber() != temp.get(0).getNumber())) {// ������һ����
				if (temp.get(1).getNumber() > prior.getOutPoke().get(2).getNumber()) {
					Util.type = 4;
					Util.isPoke = 1;
				} else {
					for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
						self.getPlayerPoke().get(i).setPokeY(500);
					}
				}
			} else if (temp.get(0).getNumber() == temp.get(1).getNumber()
					&& temp.get(0).getNumber() == temp.get(2).getNumber()
					&& temp.get(0).getNumber() == temp.get(3).getNumber()) {// �ĸ�һ����ը��
				OutPoke.score *= 4;
				Util.type = 8;
				Util.isPoke = 1;
			} else {
				for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
					self.getPlayerPoke().get(i).setPokeY(500);
				}
			}
		}
		
	}
	
	public static void fivePoke(Player self,Player prior){
		ArrayList<Poke> temp = new ArrayList<Poke>();// ��ʱ����
		for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����һ���ҳ�����������
			Poke p = self.getPlayerPoke().get(i);
			if (p.getPokeY() == 485) {
				temp.add(p);
			}
		}
		Util.pokeSort(temp);// ����ʱ�����Ÿ���
		if ((temp.get(0).getNumber() == temp.get(1).getNumber())
				&& (temp.get(0).getNumber() == temp.get(2).getNumber())
				&& (temp.get(0).getNumber() != temp.get(3).getNumber())
				&& (temp.get(3).getNumber() == temp.get(4).getNumber())
				&& (temp.get(0).getNumber() > prior.getOutPoke().get(2).getNumber())) {// ��һ������������
			Util.type = 5;
			Util.isPoke = 1;
		} else if ((temp.get(2).getNumber() == temp.get(3).getNumber())
				&& (temp.get(2).getNumber() == temp.get(4).getNumber())
				&& (temp.get(2).getNumber() != temp.get(0).getNumber())
				&& (temp.get(0).getNumber() == temp.get(1).getNumber())
				&& (temp.get(2).getNumber() > prior.getOutPoke().get(2).getNumber())) {// ������һ�Ե���
			Util.type = 5;
			Util.isPoke = 1;
		} else {
			for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
				self.getPlayerPoke().get(i).setPokeY(500);
			}
		}
	}
	
	public static void beyondFivePoke(Player self,Player prior){
		ArrayList<Poke> temp = new ArrayList<Poke>();// ��ʱ����
		for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����һ���ҳ�����������
			Poke p = self.getPlayerPoke().get(i);
			if (p.getPokeY() == 485) {
				temp.add(p);
			}
		}
		Util.pokeSort(temp);// �Ÿ���ȷ��û��
		Util.pokeSort(prior.getOutPoke());// �Ÿ���ȷ��û��
		boolean k = false;// �����ж��ǲ���˳�ӵķ���ֵ 1--˳�� 2--˫˳
		boolean b = false;// �ж��ǲ���˫˳
		// ˳�ӵ��ж�
		for (int i = temp.size() - 1; i > 0; i--) {
			if (temp.get(temp.size() - 1).getNumber() > prior.getOutPoke()
					.get(prior.getOutPoke().size() - 1).getNumber()) {
				if (temp.get(i - 1).getNumber() - temp.get(i).getNumber() == 1) {// ����������,�������
					k = true;
				}else{
					k = false;
				}
			}
		}
		// ˫˳���ж�
		if (temp.size() % 2 == 0) {
			if (temp.get(temp.size() - 1).getNumber() > prior.getOutPoke()
					.get(prior.getOutPoke().size() - 1).getNumber()) {
				for (int i = temp.size() - 1; i > 2; i -= 2) {

					// ������ͬ,�Ӻ��������ͬ�ұ�ǰ���Ŵ�1
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
					for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
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
						// ���ŵ�ը

						if (temp.get(0).getNumber() == temp.get(1).getNumber()
								&& temp.get(0).getNumber() == temp.get(2).getNumber()
								&& temp.get(0).getNumber() == temp.get(3).getNumber()) {
							Util.isPoke = 1;
							OutPoke.score *= 4;
							OutPoke.boomCount++;
							Util.type = 8;

						}
					} else {
						for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
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
					for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
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
						for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
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
						for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
							self.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				}
			}else if(prior.getOutPoke().get(0).getNumber() == 100 && prior.getOutPoke().get(1).getNumber() == 99){
				for (int i = 0; i < self.getPlayerPoke().size(); i++) {// ����ȫ����λ
					self.getPlayerPoke().get(i).setPokeY(500);
				}
			}
		}
	
	}
}
