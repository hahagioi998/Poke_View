package com.qiu.dao;

import java.util.ArrayList;
import java.util.Collections;

import com.qiu.model.Poke;

public class PokeDaoImp implements PokeDao {

	@Override
	public void upsetPoke(ArrayList<Poke> list) {//����ʵ�ַ���
		Collections.shuffle(list);//java�Դ����Ҽ��Ϸ���
	}

}
