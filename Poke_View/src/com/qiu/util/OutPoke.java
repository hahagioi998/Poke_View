package com.qiu.util;

import java.util.ArrayList;

import com.qiu.model.Poke;

//дһ�����ڳ��Ƶ���,��Ҫ����ʵ�ֳ��ƺͻ����ϵķ������㷽��
public class OutPoke {

	public static int score = 0;// �е����ķ���,���ϵ��Ƶı���
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

}
