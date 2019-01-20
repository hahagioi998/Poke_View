package com.qiu.controller;

import com.qiu.model.Poke;
import com.qiu.util.OutPoke;
import com.qiu.util.Util;
import com.qiu.view.LoginFrame;

public class DealThread extends Thread {

	LoginFrame lf;

	public DealThread(LoginFrame lf) {
		this.lf = lf;
	}

	@Override
	public void run() {

		while (true) {

			if (Util.getKey() == 1) {// ��keyֵΪ1��ʱ��Ϳ�ʼ����
				// Util.reStart = false;//��Ҫ������Ϸһ���ʱ�����¿�ʼ
				while (Util.player_Poke < Util.PLAYERE_POKE) {// ���Ƶ�����
					Util.player_Poke++;// ÿ��һ�ξ�����1
					// ��ÿ����ҷ���,��ÿ��һ���ƾ�����
					for (int j = 0; j < Util.player.length; j++) {
						Util.player[j].getPlayerPoke().add(Util.pokeList.get(0));
						Util.pokeList.remove(0);
						Util.pokeSort(Util.playerOne.getPlayerPoke());
						Util.setCoordinate(Util.playerOne.getPlayerPoke());
						Util.pokeSort(Util.playerTwo.getPlayerPoke());
						Util.pokeSort(Util.playerThree.getPlayerPoke());
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
				Util.callPlayers();// ���������ҽе����ķ���
				Util.key = 2;// ��keyֵ��Ϊ2,��ʼ�ڽе�������

			} else if (Util.key == 2) {// �е�������
				if (Util.callPlayer == 0) {// �Լ��е���
					if (Util.drawMark == 1) {
						// ��������
						try {
							Thread.sleep(Util.time);
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
							Thread.sleep(Util.time);
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
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// �����е���
						Util.landowner = 1;// ����Լ��ǵ���
						OutPoke.score = Util.playerOne.getPoints();//ֱ��3��
					} else if (Util.drawMark == 4) {
						// ��������
						try {
							Thread.sleep(Util.time);
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
						if (Util.playerOne.getPoints() != 4) {// ���Լ��Ľзֲ�����4�������,˵���Լ��Ѿ��й�����
							Util.playerThree.setPoints(0);// �Լ��й���,���ұ��Լ�С�ľͲ��з�
							Util.drawMark = 8;// ���Ҳ��з�
						}
					}
					if (Util.drawMark == 5) {// �Լ�ֱ�ӽ�3��������һ��
						try {
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// �����е���
						Util.landowner = 3;// ����Լ��ǵ���
						OutPoke.score = Util.playerThree.getPoints();
					}
					// ��������
					try {
						Thread.sleep(Util.time);
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
					if (Util.playerTwo.getPoints() <= Util.playerThree.getPoints()) {// ��ȡ�ķ���С��ǰһλ,ֱ������
						if (Util.playerThree.getPoints() != 4) {// �����ڽ��˷ֵ�ǰ����
							Util.playerTwo.setPoints(0);
							Util.drawMark = 12;// ���Ҳ��з�
						}
					}
					if (Util.drawMark == 9) {// �Լ�ֱ�ӽ�3��������һ��
						try {
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Util.key = 3;// �����е���
						Util.landowner = 2;// ����Լ��ǵ���
						OutPoke.score = Util.playerTwo.getPoints();//ֱ��3��
					}
					// ��������
					try {
						Thread.sleep(Util.time);
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
				try {
					Thread.sleep(Util.time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// ����ҵ�points��ֵ��Ϊ4��ʱ��,˵�����е�����һ�ֽ�����
				if (Util.playerOne.getPoints() != 4 && Util.playerTwo.getPoints() != 4
						&& Util.playerThree.getPoints() != 4) {
					if (Util.playerOne.getPoints() > Util.playerTwo.getPoints()
							&& Util.playerOne.getPoints() > Util.playerThree.getPoints()) {
						Util.landowner = 1;// ��ʾ���һ����
						Util.key = 3;
						OutPoke.score = Util.playerOne.getPoints();
					} else if (Util.playerTwo.getPoints() > Util.playerOne.getPoints()
							&& Util.playerTwo.getPoints() > Util.playerThree.getPoints()) {
						Util.landowner = 2;// ��ʾ��Ҷ�����
						Util.key = 3;
						OutPoke.score = Util.playerTwo.getPoints();
					} else if (Util.playerThree.getPoints() > Util.playerOne.getPoints()
							&& Util.playerThree.getPoints() > Util.playerTwo.getPoints()) {
						Util.landowner = 3;// ��ʾ���������
						Util.key = 3;
						OutPoke.score = Util.playerThree.getPoints();
					} else if (Util.playerOne.getPoints() == 0 && Util.playerTwo.getPoints() == 0
							&& Util.playerThree.getPoints() == 0) {// �����е���

						Util.pokeList.clear();// �Ƴ����ϵ�����Ԫ��
						Util.playerOne.getPlayerPoke().clear();
						Util.playerTwo.getPlayerPoke().clear();
						Util.playerThree.getPlayerPoke().clear();
						Util.landowner = 4;
						// һ�����ݶ�Ҫ����
						Util.initPoke();// ��ʼ������
						Util.player_Poke = 0;// ������������Ϊ0
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
			} else if (Util.key == 3) {
				try {// ����������,�õ�����һ����ʾ��ʱ��
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (Util.landowner == 1) {// ���һ����
					// ���ƽ�����ʾ
					for (int i = 0; i < Util.pokeList.size(); i++) {
						Util.playerOne.getPlayerPoke().add(Util.pokeList.get(i));
					}
					//�����Ÿ���,����鿴�ǲ���˳��
					Util.pokeSort(Util.pokeList);
					OutPoke.baseMultiple(Util.pokeList);//��������Է����Ŀ���
					Util.pokeSort(Util.playerOne.getPlayerPoke());// ����
					Util.setCoordinate(Util.playerOne.getPlayerPoke());
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {
						for (int j = 0; j < Util.pokeList.size(); j++) {
							Poke p1 = Util.playerOne.getPlayerPoke().get(i);
							Poke p2 = Util.pokeList.get(j);
							if(p1.getName().equals(p2.getName()) && p1.getNumber() == p2.getNumber()){
								p1.setPokeY(485);
							}
						}
					}
					try {
						Thread.sleep(Util.time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Util.setCoordinate(Util.playerOne.getPlayerPoke());
				} else if (Util.landowner == 2) {
					for (int i = 0; i < Util.pokeList.size(); i++) {
						Util.playerTwo.getPlayerPoke().add(Util.pokeList.get(i));
					}
					//�����Ÿ���,����鿴�ǲ���˳��
					Util.pokeSort(Util.pokeList);
					OutPoke.baseMultiple(Util.pokeList);//��������Է����Ŀ���
					Util.pokeSort(Util.playerTwo.getPlayerPoke());
				} else if (Util.landowner == 3) {
					for (int i = 0; i < Util.pokeList.size(); i++) {
						Util.playerThree.getPlayerPoke().add(Util.pokeList.get(i));
					}
					//�����Ÿ���,����鿴�ǲ���˳��
					Util.pokeSort(Util.pokeList);
					OutPoke.baseMultiple(Util.pokeList);//��������Է����Ŀ���
					Util.pokeSort(Util.playerThree.getPlayerPoke());
				}
				Util.key = 4;
			} else if (Util.key == 4) {//��ʼ����
				Util.pitchOn = 0;//��ֹ��û�����Ƶ�ʱ��,�͵�����Ƶ�����,������쳣
				if (Util.landowner == 1) {
					Util.callPlayer = 0;
				} else if (Util.landowner == 2) {
					Util.callPlayer = 2;
				} else if (Util.landowner == 3) {
					Util.callPlayer = 1;
				}
				while (Util.playerOne.getPlayerPoke().size() != 0 && Util.playerTwo.getPlayerPoke().size() != 0
						&& Util.playerThree.getPlayerPoke().size() != 0) {// �ж��������Ƕ���Ϊ��˵������
					
					if (Util.callPlayer == 0) {// �Լ�����
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//����йܿ���,���ҵ������Ҷ�Ϊ��,�ͽ��������͸�Ϊ������
						if(OutPoke.isTrusteeship && (Util.playerThree.getOutPoke().size() == 0 
								&& Util.playerTwo.getOutPoke().size() == 0)){
							Util.type = 1;
						}
						if(Util.type == 1 || Util.type == 0){//��������
							if(OutPoke.isTrusteeship){//�йܿ���
								if(Util.playerThree.getOutPoke().size() == 0 
										&& Util.playerOne.getOutPoke().size() == 0){//����һ�Һ���һ�ҵĳ��Ƽ���Ϊ��ʱ,˵���Ǳ��ҳ���
									Util.type = 1;//Ĭ�ϳ�����
								}
								OutPoke.autoSoloOutPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//�ϼ�,�¼�,����
								Util.type = 1;
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//�����2�ͱ�ʾҪ����
									Util.isPoke = 0;//�ĳ��Լҵ�Ҫ����
								}
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//����������ú�
								Util.callPlayer = 1;
							}else{//�йܹر�
							
								if (Util.isPoke == 1) {

									// ����Լҳ��Ƽ���
									Util.playerOne.getOutPoke().clear();
									for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// ����һ�ΰ�ѡ�е��ƷŽ����Ƽ���
										if (Util.playerOne.getPlayerPoke().get(i).getPokeY() == 485) {
											Poke p = Util.playerOne.getPlayerPoke().get(i);// �ҳ�������
											Util.playerOne.getOutPoke().add(p);// �Ž����Ƽ���
											Util.playerOne.getPlayerPoke().remove(i);// �Ƴ�ԭ���ϵ���
											Util.isPoke = -1;
										}
									}
									// ������û�г��ƶ���������
									Util.pokeSort(Util.playerOne.getPlayerPoke());
									// ������������
									Util.setCoordinate(Util.playerOne.getPlayerPoke());
									Util.callPlayer = 1;
								} else if (Util.isPoke == 0) {// Ҫ����
									Util.playerOne.getOutPoke().clear();
									Util.callPlayer = 1;// ��ֱ����ת��һ����
								}
							}
						}else if(Util.type == 2){//��������
							if(OutPoke.isTrusteeship){
								OutPoke.autoDoubleOutPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//�ϼ�,�¼�,����
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//�����2�ͱ�ʾҪ����
									Util.isPoke = 0;//�ĳ��Լҵ�Ҫ����
								}
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//����������ú�
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 3){
							if(OutPoke.isTrusteeship){
								OutPoke.autoTripleOutPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//�ϼ�,�¼�,����
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//�����2�ͱ�ʾҪ����
									Util.isPoke = 0;//�ĳ��Լҵ�Ҫ����
								}
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//����������ú�
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 4){
							if(OutPoke.isTrusteeship){
								OutPoke.autoTripleAndOneOutPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//�ϼ�,�¼�,����
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//�����2�ͱ�ʾҪ����
									Util.isPoke = 0;//�ĳ��Լҵ�Ҫ����
								}
								Util.pokeSort(Util.playerOne.getOutPoke());
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//����������ú�
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 5){
							if(OutPoke.isTrusteeship){
								OutPoke.autoTripleAndTwoOutPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//�ϼ�,�¼�,����
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//�����2�ͱ�ʾҪ����
									Util.isPoke = 0;//�ĳ��Լҵ�Ҫ����
								}
								Util.pokeSort(Util.playerOne.getOutPoke());
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//����������ú�
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 6){
							if(OutPoke.isTrusteeship){
								OutPoke.continuePoke(Util.playerTwo,Util.playerThree,Util.playerOne);//�ϼ�,�¼�,����
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//�����2�ͱ�ʾҪ����
									Util.isPoke = 0;//�ĳ��Լҵ�Ҫ����
								}
								Util.pokeSort(Util.playerOne.getOutPoke());
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//����������ú�
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 7){
							if(OutPoke.isTrusteeship){
								OutPoke.continueDoublePoke(Util.playerTwo,Util.playerThree,Util.playerOne);//�ϼ�,�¼�,����
								if(Util.isPoke == 2){
									OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);
								}
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//�����2�ͱ�ʾҪ����
									Util.isPoke = 0;//�ĳ��Լҵ�Ҫ����
								}
								Util.pokeSort(Util.playerOne.getOutPoke());
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//����������ú�
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}else if(Util.type == 8){
							if(OutPoke.isTrusteeship){
								OutPoke.boomPoke(Util.playerTwo,Util.playerThree,Util.playerOne);//�ϼ�,�¼�,����
								if(Util.isPoke == 2){
									OutPoke.doubleBoom(Util.playerOne);
								}
								if(Util.isPoke == 2){//�����2�ͱ�ʾҪ����
									Util.isPoke = 0;//�ĳ��Լҵ�Ҫ����
								}
								Util.pokeSort(Util.playerOne.getOutPoke());
								Util.setCoordinate(Util.playerOne.getPlayerPoke());//����������ú�
								Util.callPlayer = 1;
							}else{
								OutPoke.handAction();
							}
						}
					} else if (Util.callPlayer == 1) {
						try {
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(Util.playerOne.getOutPoke().size() == 0 
								&& Util.playerTwo.getOutPoke().size() == 0){//����һ�Һ���һ�ҵĳ��Ƽ���Ϊ��ʱ,˵���Ǳ��ҳ���
							Util.type = 1;//Ĭ�ϳ�����
						}
						if(Util.type == 1){//��������
							// ���Լҵĳ��Ƽ���������,����Ϊ�յ�ǰ�����ж϶��ҳ�ʲô��
							OutPoke.autoSoloOutPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 2){//��������
							//���ö������͵ķ���
							OutPoke.autoDoubleOutPoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 3){
							//�����������͵ķ���
							OutPoke.autoTripleOutPoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 4){//����һ
							OutPoke.autoTripleAndOneOutPoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 5){//������
							OutPoke.autoTripleAndTwoOutPoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 6){//˳��
							OutPoke.index.clear();
							OutPoke.temp.clear();
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							OutPoke.continuePoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 7){//˳��
							OutPoke.index.clear();
							OutPoke.temp.clear();
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							OutPoke.continueDoublePoke(Util.playerOne, Util.playerTwo, Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 2;
						}else if(Util.type == 8){
							OutPoke.boomPoke(Util.playerOne,Util.playerTwo,Util.playerThree);
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerThree);
							}
							Util.callPlayer = 2;
						}
					} else if (Util.callPlayer == 2) {
						try {
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(Util.playerThree.getOutPoke().size() == 0 
								&& Util.playerOne.getOutPoke().size() == 0){//����һ�Һ���һ�ҵĳ��Ƽ���Ϊ��ʱ,˵���Ǳ��ҳ���
							Util.type = 1;//Ĭ�ϳ�����
						}
						if(Util.type == 1){//��������
							OutPoke.autoSoloOutPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
							
						}else if(Util.type == 2){//��������
							//���ö������͵ķ���
							OutPoke.autoDoubleOutPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 3){
							//�����������͵ķ���
							OutPoke.autoTripleOutPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 4){//����һ
							OutPoke.autoTripleAndOneOutPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 5){//������
							OutPoke.autoTripleAndTwoOutPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 6){//˳��
							OutPoke.index.clear();
							OutPoke.temp.clear();
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							OutPoke.continuePoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 7){//˳��
							OutPoke.index.clear();
							OutPoke.temp.clear();
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							OutPoke.continueDoublePoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							}
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Util.callPlayer = 0;
						}else if(Util.type == 8){//��ը����ʱ��
							OutPoke.boomPoke(Util.playerThree, Util.playerOne, Util.playerTwo);
							if(Util.isPoke == 2){
								OutPoke.doubleBoom(Util.playerTwo);
							}
							Util.callPlayer = 0;
						}
						try {
							Thread.sleep(Util.time);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				try {
					Thread.sleep(Util.time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if((Util.playerOne.getPlayerPoke().size() == 0 && Util.playerTwo.getPlayerPoke().size() != 0 
						&& Util.playerThree.getPlayerPoke().size() != 0) || 
						(Util.playerOne.getPlayerPoke().size() != 0 && Util.playerTwo.getPlayerPoke().size() == 0 
						&& Util.playerThree.getPlayerPoke().size() != 0) ||
						(Util.playerOne.getPlayerPoke().size() != 0 && Util.playerTwo.getPlayerPoke().size() != 0 
						&& Util.playerThree.getPlayerPoke().size() == 0)){
					Util.key = 5;
				}
			}else if(Util.key == 5){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Util.key = 6;
			}else if(Util.key == 6){
				//����ǵ��������Ƽ���Ϊ�վ��жϵ���Ӯ,�������ũ��Ӯ
				if(Util.landowner == 1 && Util.playerOne.getPlayerPoke().size() == 0){
					Util.playerOne.setScore(Util.playerOne.getScore() +  OutPoke.score * 2);
					Util.playerOne.setWin("ʤ");
					Util.playerTwo.setScore(Util.playerTwo.getScore() - OutPoke.score);
					Util.playerTwo.setWin("��");
					Util.playerThree.setScore(Util.playerThree.getScore() - OutPoke.score);
					Util.playerThree.setWin("��");
					Util.key = 7;//��ת���������
				}else if(Util.landowner == 2 && Util.playerTwo.getPlayerPoke().size() == 0){
					Util.playerTwo.setScore(Util.playerTwo.getScore() +  OutPoke.score * 2);
					Util.playerTwo.setWin("ʤ");
					Util.playerOne.setScore(Util.playerOne.getScore() - OutPoke.score);
					Util.playerOne.setWin("��");
					Util.playerThree.setScore(Util.playerThree.getScore() - OutPoke.score);
					Util.playerThree.setWin("��");
					Util.key = 7;//��ת���������
				}else if(Util.landowner == 3 && Util.playerThree.getPlayerPoke().size() == 0){
					Util.playerThree.setScore(Util.playerThree.getScore() +  OutPoke.score * 2);
					Util.playerThree.setWin("ʤ");
					Util.playerTwo.setScore(Util.playerTwo.getScore() - OutPoke.score);
					Util.playerTwo.setWin("��");
					Util.playerOne.setScore(Util.playerOne.getScore() - OutPoke.score);
					Util.playerOne.setWin("��");
					Util.key = 7;//��ת���������
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
