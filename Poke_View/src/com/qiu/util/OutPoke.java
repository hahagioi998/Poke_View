package com.qiu.util;

import java.util.ArrayList;

import com.qiu.model.Player;
import com.qiu.model.Poke;

//дһ�����ڳ��Ƶ���,��Ҫ����ʵ�ֳ��ƺͻ����ϵķ������㷽��
public class OutPoke {

	public static int score = 0;// �е����ķ���,���ϵ��Ƶı���
	public static boolean isTrusteeship = false;//�йܵĿ���,false��ʾ�Լ���,true��ʾ�йܿ���
	
	//������
	public static int reMainPoke_3;//3�ļ�����
	public static int reMainPoke_4;//4�ļ�����
	public static int reMainPoke_5;//5�ļ�����
	public static int reMainPoke_6;//6�ļ�����
	public static int reMainPoke_7;//7�ļ�����
	public static int reMainPoke_8;//8�ļ�����
	public static int reMainPoke_9;//9�ļ�����
	public static int reMainPoke_10;//10�ļ�����
	public static int reMainPoke_11;//11�ļ�����
	public static int reMainPoke_12;//12�ļ�����
	public static int reMainPoke_13;//13�ļ�����
	public static int reMainPoke_14;//14�ļ�����
	public static int reMainPoke_22;//22�ļ�����
	public static int reMainPoke_W;//���ļ�����
	
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
	public static void autoSoloOutPoke(Player up, Player down, Player self){
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
	
	//ר�ų����ӵķ���
	public static void autoDoubleOutPoke(Player up, Player down, Player self){
		Util.isPoke = -1;//�ر�Ҫ����İ�ť
		self.getOutPoke().clear();
		if(up.getOutPoke().size() != 0){//�ϼҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼҾ��ǳ��˶���
			//�ж�����i > 0 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������һ�����Ǹ�����,������-1���±�
			for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
				Poke p1 = self.getPlayerPoke().get(i);//���ұ߿�ʼ
				Poke p2 = self.getPlayerPoke().get(i - 1);//���ڵ�����
				if(p1.getNumber() == p2.getNumber()){//���ŵ���ֵһ��,˵���Ƕ���
					if(p1.getNumber() > up.getOutPoke().get(0).getNumber()){
						self.getOutPoke().add(p1);//��ӵ����Ƽ�����
						self.getOutPoke().add(p2);//��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);//�����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);//�����Ƽ������Ƴ�
						break;
					}
				}
			}
			if(self.getOutPoke().size() == 0){//���ҳ��Ƽ���Ϊ��,��˵��Ҫ����
				Util.isPoke = 2;
			}
			
		}else if(up.getOutPoke().size() == 0 
				&& down.getOutPoke().size() != 0){//�ϼҵĳ��Ƽ���Ϊ��,�����¼ҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼ�Ҫ�����¼�
			//�ж�����i > 0 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������һ�����Ǹ�����,������-1���±�
			for (int i = self.getPlayerPoke().size() - 1; i > 0; i--) {
				Poke p1 = self.getPlayerPoke().get(i);//���ұ߿�ʼ
				Poke p2 = self.getPlayerPoke().get(i - 1);//���ڵ�����
				if(p1.getNumber() == p2.getNumber()){//���ŵ���ֵһ��,˵���Ƕ���
					if(p1.getNumber() > down.getOutPoke().get(0).getNumber()){
						self.getOutPoke().add(p1);//��ӵ����Ƽ�����
						self.getOutPoke().add(p2);//��ӵ����Ƽ�����
						self.getPlayerPoke().remove(i);//�����Ƽ������Ƴ�
						self.getPlayerPoke().remove(i - 1);//�����Ƽ������Ƴ�
						break;
					}
				}
			}
			if(self.getOutPoke().size() == 0){//���ҳ��Ƽ���Ϊ��,��˵��Ҫ����
				Util.isPoke = 2;
			}
		}
	}

	//ר�ų�����һ���ķ���
		public static void autoTripleOutPoke(Player up, Player down, Player self){
			Util.isPoke = -1;//�ر�Ҫ����İ�ť
			self.getOutPoke().clear();
			if(up.getOutPoke().size() != 0){//�ϼҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼҾ��ǳ�������
				//�ж�����i > 1 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������3�����Ǹ�һ����,������-1���±�
				for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
					Poke p1 = self.getPlayerPoke().get(i);//���ұ߿�ʼ
					Poke p2 = self.getPlayerPoke().get(i - 1);//���ڵĵڶ���
					Poke p3 = self.getPlayerPoke().get(i - 2); //���ڵĵ�����
					if(p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()){//���ŵ���ֵһ��
						if(p1.getNumber() > up.getOutPoke().get(0).getNumber()){
							self.getOutPoke().add(p1);//��ӵ����Ƽ�����
							self.getOutPoke().add(p2);//��ӵ����Ƽ�����
							self.getOutPoke().add(p3);//��ӵ����Ƽ�����
							self.getPlayerPoke().remove(i);//�����Ƽ������Ƴ�
							self.getPlayerPoke().remove(i - 1);//�����Ƽ������Ƴ�
							self.getPlayerPoke().remove(i - 2);//�����Ƽ������Ƴ�
							break;
						}
					}
				}
				if(self.getOutPoke().size() == 0){//���ҳ��Ƽ���Ϊ��,��˵��Ҫ����
					Util.isPoke = 2;
				}
				
			}else if(up.getOutPoke().size() == 0 
					&& down.getOutPoke().size() != 0){//�ϼҵĳ��Ƽ���Ϊ��,�����¼ҵĳ��Ƽ��ϲ�Ϊ��,˵���ϼ�Ҫ�����¼�
				//�ж�����i > 1 ����Ϊ��ֹ�±�Խ��,��Ϊ�п��������3�����Ǹ�һ����,������-1���±�
				for (int i = self.getPlayerPoke().size() - 1; i > 1; i--) {
					Poke p1 = self.getPlayerPoke().get(i);//���ұ߿�ʼ
					Poke p2 = self.getPlayerPoke().get(i - 1);//���ڵ�����
					Poke p3 = self.getPlayerPoke().get(i - 2);//���ڵ�����
					if(p1.getNumber() == p2.getNumber() && p1.getNumber() == p3.getNumber()){//���ŵ���ֵһ��,˵���Ƕ���
						if(p1.getNumber() > down.getOutPoke().get(0).getNumber()){
							self.getOutPoke().add(p1);//��ӵ����Ƽ�����
							self.getOutPoke().add(p2);//��ӵ����Ƽ�����
							self.getOutPoke().add(p3);//��ӵ����Ƽ�����
							self.getPlayerPoke().remove(i);//�����Ƽ������Ƴ�
							self.getPlayerPoke().remove(i - 1);//�����Ƽ������Ƴ�
							self.getPlayerPoke().remove(i - 2);//�����Ƽ������Ƴ�
							break;
						}
					}
				}
				if(self.getOutPoke().size() == 0){//���ҳ��Ƽ���Ϊ��,��˵��Ҫ����
					Util.isPoke = 2;
				}
			}
		}
}
