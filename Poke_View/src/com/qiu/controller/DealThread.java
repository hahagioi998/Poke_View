package com.qiu.controller;

import com.qiu.model.Poke;
import com.qiu.util.Util;
import com.qiu.view.LoginFrame;

public class DealThread extends Thread {

//	private GameMouse gm = new GameMouse(lf.getGf().getGamePanel());
	LoginFrame lf;
	public DealThread(LoginFrame lf){
		this.lf = lf;
	}
	
	@Override
	public void run() {

		while (true) {

			if (Util.getKey() == 1) {// ��keyֵΪ1��ʱ��Ϳ�ʼ����
//				Util.reStart = false;//��Ҫ������Ϸһ���ʱ�����¿�ʼ
				while (Util.player_Poke < Util.PLAYERE_POKE) {// ���Ƶ�����
					Util.player_Poke++;// ÿ��һ�ξ�����1
					// ��ÿ����ҷ���,��ÿ��һ���ƾ�����
					for (int j = 0; j < Util.player.length; j++) {
						Util.player[j].getPlayerPoke().add(Util.pokeList.get(0));
						Util.pokeList.remove(0);
						Util.pokeSort(Util.playerOne);
						Util.setCoordinate(Util.playerOne.getPlayerPoke());
						Util.pokeSort(Util.playerTwo);
						Util.pokeSort(Util.playerThree);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
				Util.callPlayers();// ���������ҽе����ķ���
				Util.key = 2;// ��keyֵ��Ϊ2,��ʼ�ڽе�������

			}else if (Util.key == 2) {// �е�������
				if (Util.callPlayer == 0) {// �Լ��е���
					if (Util.drawMark == 1) {
						// ��������
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (Util.playerThree.getPoints() > Util.playerOne.getPoints()) {// ��ʼ������Ϊ4��,ȷ�����Կ�ʼ��һ��
							Util.callPlayer = 1;
						} else {
							Util.callPlayer = 2;
						}
					} else if (Util.drawMark == 2) {
						// ��������
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (Util.playerThree.getPoints() > Util.playerOne.getPoints()) {// ��ʼ������Ϊ4��,ȷ�����Կ�ʼ��һ��
							Util.callPlayer = 1;
						} else {
							Util.callPlayer = 2;
						}
					} else if (Util.drawMark == 3) {// �Լ�ֱ�ӽ�3��������һ��
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// �����е���
						Util.landowner = 1;//����Լ��ǵ���
					} else if (Util.drawMark == 4) {
						// ��������
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (Util.playerThree.getPoints() > Util.playerOne.getPoints()) {// ��ʼ������Ϊ4��,ȷ�����Կ�ʼ��һ��
							Util.callPlayer = 1;
						} else {
							Util.callPlayer = 2;
						}
					}
				} else if (Util.callPlayer == 1) {
					Util.callPoints();
					if (Util.playerThree.getPoints() <= Util.playerOne.getPoints()) {
						if(Util.playerOne.getPoints() != 4){//���Լ��Ľзֲ�����4�������,˵���Լ��Ѿ��й�����
							Util.playerThree.setPoints(0);//�Լ��й���,���ұ��Լ�С�ľͲ��з�
							Util.drawMark = 8;//���Ҳ��з�
						}
					}
					if (Util.drawMark == 5) {// �Լ�ֱ�ӽ�3��������һ��
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// �����е���
						Util.landowner = 3;//����Լ��ǵ���
					}
					// ��������
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (Util.playerThree.getPoints() == 1) {
						Util.hidePoints = 1;// һ�ְ�ť���ɼ�
					} else if (Util.playerThree.getPoints() == 2) {
						Util.hidePoints = 2;// һ�ְ�ť�Ͷ��ְ�ť�����ɼ�
					}
					if (Util.playerTwo.getPoints() > Util.playerThree.getPoints()) {
						Util.callPlayer = 2;
					} else {
						Util.callPlayer = 0;
					}
					

				} else if (Util.callPlayer == 2) {
					Util.callPoints();
					if (Util.playerTwo.getPoints() <= Util.playerThree.getPoints()) {//��ȡ�ķ���С��ǰһλ,ֱ������
						if(Util.playerThree.getPoints() != 4){//�����ڽ��˷ֵ�ǰ����
							Util.playerTwo.setPoints(0);
							Util.drawMark = 12;//���Ҳ��з�
						}
					}
					if (Util.drawMark == 9) {// �Լ�ֱ�ӽ�3��������һ��
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// �����е���
						Util.landowner = 2;//����Լ��ǵ���
					}
					// ��������
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (Util.playerTwo.getPoints() == 1) {
						Util.hidePoints = 1;// һ�ְ�ť���ɼ�
					} else if (Util.playerTwo.getPoints() == 2) {
						Util.hidePoints = 2;// һ�ְ�ť�Ͷ��ְ�ť�����ɼ�
					}
					if (Util.playerOne.getPoints() > Util.playerTwo.getPoints()) {
						Util.callPlayer = 0;
					} else {
						Util.callPlayer = 1;
					}

				}
				// ����ҵ�points��ֵ��Ϊ4��ʱ��,˵�����е�����һ�ֽ�����
				if (Util.playerOne.getPoints() != 4 && Util.playerTwo.getPoints() != 4
						&& Util.playerThree.getPoints() != 4) {
					if (Util.playerOne.getPoints() > Util.playerTwo.getPoints()
							&& Util.playerOne.getPoints() > Util.playerThree.getPoints()) {
						Util.landowner = 1;// ��ʾ���һ����
						Util.key = 3;
					} else if (Util.playerTwo.getPoints() > Util.playerOne.getPoints()
							&& Util.playerTwo.getPoints() > Util.playerThree.getPoints()) {
						Util.landowner = 2;// ��ʾ��Ҷ�����
						Util.key = 3;
					} else if (Util.playerThree.getPoints() > Util.playerOne.getPoints()
							&& Util.playerThree.getPoints() > Util.playerTwo.getPoints()) {
						Util.landowner = 3;// ��ʾ���������
						Util.key = 3;
						
					}else if(Util.playerOne.getPoints() == 0 && Util.playerTwo.getPoints() == 0 
							&& Util.playerThree.getPoints() == 0){//�����е���
						
						Util.pokeList.clear();//�Ƴ����ϵ�����Ԫ��
						Util.playerOne.getPlayerPoke().clear();
						Util.playerTwo.getPlayerPoke().clear();
						Util.playerThree.getPlayerPoke().clear();
						Util.landowner = 4;
						//һ�����ݶ�Ҫ����
						Util.initPoke();//��ʼ������
						Util.player_Poke = 0;//������������Ϊ0
						Util.playerOne.setPoints(4);
						Util.playerTwo.setPoints(4);
						Util.playerThree.setPoints(4);
						Util.drawMark = 0;
						Util.flag = true;
						Util.callPlayer = 0;
						Util.mark = 0;
						Util.hidePoints = 0;
						Util.upsetPoke(Util.pokeList);
						Util.setKey(1);
					}
				}
			}else if(Util.key == 3){
				try {//����������,�õ�����һ����ʾ��ʱ��
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(Util.landowner == 1){//���һ����
//					while(Util.pokeList.size() != 0){//�ѵ��Ʒָ�����
//						Util.playerOne.getPlayerPoke().add(Util.pokeList.get(0));
//						Util.pokeList.remove(0);
//					}
					//���ƽ�����ʾ
					for(int i = 0; i < Util.pokeList.size(); i++){
						Util.playerOne.getPlayerPoke().add(Util.pokeList.get(i));
					}
					Util.pokeSort(Util.playerOne);//����
					Util.setCoordinate(Util.playerOne.getPlayerPoke());
				}else if(Util.landowner == 2){
//					while(Util.pokeList.size() != 0){
//						Util.playerTwo.getPlayerPoke().add(Util.pokeList.get(0));
//						Util.pokeList.remove(0);
//					}
					for(int i = 0; i < Util.pokeList.size(); i++){
						Util.playerTwo.getPlayerPoke().add(Util.pokeList.get(i));
					}
					Util.pokeSort(Util.playerTwo);
				}else if(Util.landowner == 3){
//					while(Util.pokeList.size() != 0){
//						Util.playerThree.getPlayerPoke().add(Util.pokeList.get(0));
//						Util.pokeList.remove(0);
//					}
					for(int i = 0; i < Util.pokeList.size(); i++){
						Util.playerThree.getPlayerPoke().add(Util.pokeList.get(i));
					}
					Util.pokeSort(Util.playerThree);
				}
				Util.key = 4;
			}else if(Util.key == 4){
				
				lf.getGf().getGamePanel().addMouseListener(lf.getGm());
				if(Util.landowner == 1){
					Util.callPlayer = 0;
				}else if(Util.landowner == 2){
					Util.callPlayer = 2;
				}else if(Util.landowner == 3){
					Util.callPlayer = 1;
				}
				System.out.println("aaa" + Util.callPlayer);
				while(Util.playerOne.getPlayerPoke().size() != 0 
						&& Util.playerTwo.getPlayerPoke().size() != 0
						&& Util.playerThree.getPlayerPoke().size() != 0){//�ж��������Ƕ���Ϊ��˵������
					if(Util.callPlayer == 0){
						
					}else if(Util.callPlayer == 1){
						//�����ó���С����
						Poke p = Util.playerThree.getPlayerPoke().get(Util.playerThree.getPlayerPoke().size() - 1);
						//�Ž����Ƶļ�����
						Util.playerThree.getOutPoke().add(p);
						Util.playerThree.getPlayerPoke().remove(Util.playerThree.getPlayerPoke().size() - 1);
						Util.callPlayer = 2;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(Util.callPlayer == 2){
						//���ҳ�һ�űȶ��Ҵ�1����
						Poke p;
						if(Util.playerThree.getOutPoke().size() != 0){
							for (int i = Util.playerTwo.getPlayerPoke().size() - 1; i >= 0 ; i--) {
								if(Util.playerTwo.getPlayerPoke().get(i).getNumber() > 
								Util.playerThree.getOutPoke().get(0).getNumber()){//���������һ���Ʊȶ��ҵ�һ���ƴ�Ϳ��Գ���
									p = Util.playerTwo.getPlayerPoke().get(i);
									Util.playerTwo.getOutPoke().add(p);//���ҳ��������ƷŽ����ҵĳ��Ƽ�����
									Util.playerTwo.getPlayerPoke().remove(i);//���Ƴ�������������Ҫ������
									break;//�ҵ�����ѭ��
								}
							}
						}
						if(Util.playerTwo.getOutPoke().size() == 0){//���ҳ��Ƽ���Ϊ��,˵��Ҫ����
							Util.onPoke = false;
						}
						Util.callPlayer = 0;//��ת���Լ�����
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
//					try {
//						Thread.sleep(1);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public LoginFrame getLf() {
		return lf;
	}

	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}
	
}
