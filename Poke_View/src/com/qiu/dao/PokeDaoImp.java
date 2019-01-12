package com.qiu.dao;

import java.util.ArrayList;
import java.util.Collections;

import com.qiu.model.Poke;

public class PokeDaoImp implements PokeDao {

	@Override
	public void upsetPoke(ArrayList<Poke> list) {//子类实现方法
		Collections.shuffle(list);//java自带打乱集合方法
	}

}
