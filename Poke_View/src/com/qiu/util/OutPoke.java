package com.qiu.util;

import java.util.ArrayList;

import com.qiu.model.Player;
import com.qiu.model.Poke;

//дһ�����ڳ��Ƶ���,��Ҫ����ʵ�ֳ��ƺͻ����ϵķ������㷽��
public class OutPoke {

	public static int score = 0;// �е����ķ���,���ϵ��Ƶı���
	public static boolean isTrusteeship = false;//�йܵĿ���,false��ʾ�Լ���,true��ʾ�йܿ���
	
	// ���Ʒ����ķ���
	public static void baseMultiple(ArrayList<Poke> p) {
		/*
		 * ���Ƶķ���:��������/��2����/ͬ����˳������/���ź�˫��4�� 
		 * ͬʱ�����Ը߱�Ϊ׼
		 */
		if ((Util.pokeList.get(0).getNumber() == 100 && Util.pokeList.get(1).getNumber() == 99)
				|| (Util.pokeList.get(0).getNumber() == Util.pokeList.get(1).getNumber()
						&& Util.pokeList.get(0).getNumber() == Util.pokeList.get(2).getNumber())) {
			OutPoke.score *= 4;//4��
		}else if((p.get(0).getName().equals(p.get(1).getName()) && p.get(0).getName().equals(p.get(2).getName()))
				|| (p.get(0).getNumber() - p.get(1).getNumber() == 1) && (p.get(0).getNumber() - p.get(2).getNumber() == 2)){
			OutPoke.score *= 3;//3��
		}else if(p.get(0).getNumber() == 100 || p.get(0).getNumber() == 99 
				|| (p.get(0).getNumber() == 22 && p.get(1).getNumber() == 22)){
			OutPoke.score *= 2;//2��
		}
	}
	
	//�������ܳ���    ��������  �ϼ�  �¼�  ����
	public static void autoOutPoke(Player up, Player down, Player self){
		Util.isPoke = -1;// �ر�Ҫ����İ�ť
		// ����ϼҵĳ��Ƽ���Ϊ��,�¼ҵĳ��Ƽ��ϲ�Ϊ��,��˵���ϼ�ѡ�񲻳���
		if (up.getOutPoke().size() == 0 && down.getOutPoke().size() != 0) {
			self.getOutPoke().clear();
			// �ٱ���һ��,������ű��¼Ҵ���ƾ�ֱ�ӳ�
			for (int i = self.getPlayerPoke().size() - 1; i >= 0; i--) {
				if (self.getPlayerPoke().get(i).getNumber() > down.getOutPoke().get(0).getNumber()) {
					Poke p = self.getPlayerPoke().get(i);
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(i);
					break;
				}
			}
			if(self.getOutPoke().size() == 0){
				Util.isPoke = 2;//��ʾ����Ҫ����
			}
		} else if (up.getOutPoke().size() != 0) {//��һ�ҵĳ��Ƽ��ϲ�Ϊ��,˵���г���
			// ��������ҵĳ��Ƽ����е���
			self.getOutPoke().clear();
			// �ٱ���һ��,������ű��ϼҴ���ƾ�ֱ�ӳ�
			for (int i = self.getPlayerPoke().size() - 1; i >= 0; i--) {
				if (self.getPlayerPoke().get(i).getNumber() > up.getOutPoke().get(0).getNumber()) {
					Poke p = self.getPlayerPoke().get(i);
					self.getOutPoke().add(p);
					self.getPlayerPoke().remove(i);
					break;
				}
			}
			if(self.getOutPoke().size() == 0){//��������ȻΪ�ձ�ʾҪ����
				Util.isPoke = 2;//��ʾҪ����
			}
		} else if (up.getOutPoke().size() == 0 && down.getOutPoke().size() == 0) {// �������ǰ���ҵĳ��Ƽ��϶��ǿյĻ�,�����Լ���������һ����
			// ��������ҵĳ��Ƽ����е���
			self.getOutPoke().clear();
			// �����ó���С����
			Poke p = self.getPlayerPoke().get(self.getPlayerPoke().size() - 1);
			// �Ž����Ƶļ�����
			self.getOutPoke().add(p);
			self.getPlayerPoke().remove(self.getPlayerPoke().size() - 1);
		}
	}

}
