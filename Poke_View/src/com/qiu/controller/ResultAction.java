package com.qiu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.qiu.util.OutPoke;
import com.qiu.util.Util;
import com.qiu.view.ResultDialog;

public class ResultAction implements ActionListener {

	ResultDialog rd;
	public ResultAction(ResultDialog rd){
		
		this.rd = rd;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String order = e.getActionCommand();
		
//		if(order.equals("restart")){
//			Util.pokeList.clear();// �Ƴ����ϵ�����Ԫ��
//			Util.playerOne.getPlayerPoke().clear();
//			Util.playerTwo.getPlayerPoke().clear();
//			Util.playerThree.getPlayerPoke().clear();
//			Util.playerOne.getOutPoke().clear();
//			Util.playerTwo.getOutPoke().clear();
//			Util.playerThree.getOutPoke().clear();
//			OutPoke.temp.clear();
//			OutPoke.index.clear();
//			Util.landowner = 4;
//			// һ�����ݶ�Ҫ����
//			Util.initPoke();// ��ʼ������
//			Util.player_Poke = 0;// ������������Ϊ0
//			Util.playerOne.setPoints(4);
//			Util.playerTwo.setPoints(4);
//			Util.playerThree.setPoints(4);
//			Util.drawMark = 0;
//			Util.flag = true;
//			lf.getGf().getGamePanel().getShowPoke().setText("����");
//			Util.callPlayer = 0;
//			Util.mark = 0;
//			Util.hidePoints = 0;
//			Util.isPoke = -1;
//			Util.pitchOn = 0;
//			Util.type = 0;
//			OutPoke.score = 0;
//			lf.getGf().getGamePanel().getTrusteeship().setText("�й�");
//			OutPoke.isTrusteeship = false;
//			Util.upsetPoke(Util.pokeList);
//			Util.setKey(1);
//		}else 
			if(order.equals("exit")){
			rd.setVisible(false);
		}
	}

}
