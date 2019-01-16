package com.qiu.util;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import com.qiu.dao.PokeDao;
import com.qiu.dao.PokeDaoImp;
import com.qiu.model.Player;
import com.qiu.model.PlayerOne;
import com.qiu.model.PlayerThree;
import com.qiu.model.PlayerTwo;
import com.qiu.model.Poke;

public class Util {

	// ����ĳߴ����
	public static final int LOGINFRAME_W = 500;// ��¼����Ŀ��
	public static final int LOGINFRAME_H = 400;// ��¼����ĸ߶�
	public static final int MAINFRAME_W = 1000;// ������Ŀ��
	public static final int MAINFRAME_H = 700;// ������ĸ߶�

	public static final int LOGINPANEL_W = 500;// ��¼���Ŀ��
	public static final int LOGINPANEL_H = 400;// ��¼���Ŀ��

	public static final int LOGINPANELBUTTON_W = 200;// ��¼��尴ť�ĺ�����
	public static final int LOGINPANELSTARTBUTTON_H = 100;// ��ʼ��ť��������
	public static final int LOGINPANELINTRODUCEBUTTON_H = 145;// ��ʼ��ť��������
	public static final int LOGINPANELEXITBUTTON_H = 190;// ��ʼ��ť��������

	public static final int BUTTON_W = 90;// ��¼��ť���
	public static final int BUTTON_H = 35;// ��¼��ť����

	public static final int POKE_W = 60;// poke�ƵĿ��
	public static final int POKE_H = 90;// poke�Ƶĸ߶�

	// ͼƬ·��
	public static final Image LOGIN = new ImageIcon("img/login.jpg").getImage();// ��ȡ��¼����ͼ��·����ͼƬ
	public static final Image GAME = new ImageIcon("img/game.jpg").getImage();// ��ȡ��¼����ͼ��·����ͼƬ
	public static final Image P_ONE = new ImageIcon("img/playerOne.jpg").getImage();// ���ͷ��
	public static final Image P_TWO = new ImageIcon("img/playerTwo.jpg").getImage();// ���ͷ��
	public static final Image P_THREE = new ImageIcon("img/playerThree.jpg").getImage();// ���ͷ��
	public static final Image POKE_BACK = new ImageIcon("img/poke/�Ʊ�.png").getImage();// �Ʊ�����ʾ
	public static final Image REMAIN = new ImageIcon("img/reMain.png").getImage();//����������ͼ

	// ÿ��������г�ʼ������
	public static final int PLAYERE_POKE = 17;
	public static final int HIDE_POKE = 3;// ��������
	// ���ƴ���
	public static int player_Poke = 0;

	// ����һ�����迪��,��������������
	public static int key = 0;
	public static boolean flag = true;// �������ƺͰ��Ƶİ�ť

	// ����һ������
	// public static

	// ����poke��
	public static ArrayList<Poke> pokeList = new ArrayList<Poke>();// ��˳��poke�Ƶļ���
	// ����poke�ĳ�ʼ����

	public static void initPoke() {

		int[] number = { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 22 };// ÿ����ɫ����ֵ
		String[] shape = { "����", "����", "����", "÷��" };// ��ɫ

		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < number.length; j++) {
				String path = "img/poke/" + shape[i] + number[j] + ".png";
				pokeList.add(new Poke(new ImageIcon(path).getImage(), number[j],shape[i]));
			}
		}
		pokeList.add(new Poke(new ImageIcon("img/poke/��99.png").getImage(), 99, "С��"));
		pokeList.add(new Poke(new ImageIcon("img/poke/��100.png").getImage(), 100, "����"));
	}

	// �������е��ƴ���
	public static void upsetPoke(ArrayList<Poke> list) {
		PokeDao pd = new PokeDaoImp();
		pd.upsetPoke(list);
	}

	public static Player playerOne = new PlayerOne();
	public static Player playerTwo = new PlayerTwo();
	public static Player playerThree = new PlayerThree();
	public static Player[] player = { playerOne, playerThree, playerTwo };

	// ����ҵ���������
	public static void pokeSort(ArrayList<Poke> list) {
		// ����ѡ������
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getNumber() < list.get(j).getNumber()) {
					Poke temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
	}
	//���Լ���������һ���̶�������
	public static void setCoordinate(ArrayList<Poke> list){//�������Ĳ������Ͼ���Ҫ��ֵ��,��ʵ�����Լ�������Ҫ��������
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setPokeX(250 + (i * 25));//250���ǵ�һ���Ƶĺ�����,ÿ�ұ�һ��,���Ǻ������25����
			list.get(i).setPokeY(500);//500�����Լ����Ƶ�������,��һ��
		}
	}

	public static int callPlayer = 0;//���˭�е���  0�Լ�,1����,2����
	public static int drawMark = 0;//������ϻ����ֿ���
	public static int mark = 0;//����һ�����,�����Ե��Խм��ֿ��� 0--����  1--1��  2--2��  3--3��
	public static int landowner = 0;//�ж��Լ��зֵ�ʱ��,�зְ�ť�ɲ��ɼ�,�ж�˭�ǵ���,1�Լ�,2����,3����
	public static int hidePoints = 0;//�ж����ҽй��ķ���,�Լ��еķ���һ��Ҫ����һ�Ҵ�,�ñ����������Ʊ���һ��С�İ�ť���ɼ�
	public static int isPoke = -1;//Ҫ����Ŀ���,Ĭ��Ҫ���� 0--�Լ�Ҫ����  1--�Լ�Ҫ����  2--����Ҫ����  3--����Ҫ����
	public static int pitchOn = 0;//�Լ�ѡ���˼�����
	public static int type = 0;//���Ƶ�����  1--�ǵ���  2--�Ƕ���   3--������  4--����һ  5--������
	//�зֵķ���
	public static void callPlayers(){
		Random ran = new Random();
		Util.callPlayer = ran.nextInt(3);
	}
	
	public static void callPoints(){
		if(Util.callPlayer == 1){//���ҽз�
			boolean boo = Util.lookForBomb(Util.playerThree);
			if((playerThree.getPlayerPoke().get(0).getNumber() == 100 &&//����ը
					playerThree.getPlayerPoke().get(1).getNumber() == 99)|| boo){//��������������ը��
				Util.playerThree.setPoints(3);//������
				Util.drawMark = 5;//���ҽ�3��
			}else if((playerThree.getPlayerPoke().get(0).getNumber() == 100 &&//��һ������������2��2����3��2
					playerThree.getPlayerPoke().get(1).getNumber() == 22 && 
					playerThree.getPlayerPoke().get(2).getNumber() == 22) 
					|| (playerTwo.getPlayerPoke().get(0).getNumber() == 22 && 
					playerTwo.getPlayerPoke().get(1).getNumber() == 22 && 
					playerTwo.getPlayerPoke().get(2).getNumber() == 22)){
				Util.playerThree.setPoints(2);//�ж���
				Util.drawMark = 6;//���ҽ�2��
			}else if(playerThree.getPlayerPoke().get(0).getNumber() == 99 &&//��һ��С��������2��2
					playerThree.getPlayerPoke().get(1).getNumber() == 22 ){
				Util.playerThree.setPoints(1);//��һ��
				Util.drawMark = 7;//���ҽ�1��
			}else{
				Util.playerThree.setPoints(0);//���з�
				Util.drawMark = 8;//���Ҳ��з�
			}
		}else if(Util.callPlayer == 2){
			boolean boo = Util.lookForBomb(Util.playerTwo);
			if((playerTwo.getPlayerPoke().get(0).getNumber() == 100 &&//����ը
					playerTwo.getPlayerPoke().get(1).getNumber() == 99)|| boo){//��������������ը��
				Util.playerTwo.setPoints(3);//������
				Util.drawMark = 9;//���ҽ�3��
			}else if((playerTwo.getPlayerPoke().get(0).getNumber() == 100 &&//��һ������������2��2����ֱ��������2
					playerTwo.getPlayerPoke().get(1).getNumber() == 22 && 
					playerTwo.getPlayerPoke().get(2).getNumber() == 22) 
					|| (playerTwo.getPlayerPoke().get(0).getNumber() == 22 && 
					playerTwo.getPlayerPoke().get(1).getNumber() == 22 && 
					playerTwo.getPlayerPoke().get(2).getNumber() == 22)){
				Util.playerTwo.setPoints(2);//�ж���
				Util.drawMark = 10;//���ҽ�2��
			}else if(playerTwo.getPlayerPoke().get(0).getNumber() == 99 &&//��һ��С��������2��2
					playerTwo.getPlayerPoke().get(1).getNumber() == 22){
				Util.playerTwo.setPoints(1);//��һ��
				Util.drawMark = 11;//���ҽ�1��
			}else{
				Util.playerTwo.setPoints(0);//���з�
				Util.drawMark = 12;//���Ҳ��з�
			}
		}
			
	}
		
	
	//Ѱ��ը��
	public static boolean lookForBomb(Player p){
		ArrayList<Poke> list = p.getPlayerPoke();
		for (int i = 0; i < list.size() - 3; i++) {
			if(list.get(i).getNumber() == list.get(i + 1).getNumber() 
					&& list.get(i).getNumber() == list.get(i + 2).getNumber() 
					&& list.get(i).getNumber() == list.get(i + 3).getNumber()){
				return true;
			}
		}	
		return false;
		
	} 
//	//Ѱ��˳��
//	public static boolean lookForStraight(ArrayList list){
//		for (int i = 0; i < list.size(); i++) {
//			if(list.get(i) - list.){
//				
//			}
//		}
//		return false;	
//	}
	public static void show() {

		for (int i = 0; i < pokeList.size(); i++) {
			System.out.print(pokeList.get(i).getNumber() + " ");
			if ((i + 1) % 13 == 0) {
				System.out.println();
			}
		}

	}

	public static int getKey() {
		return key;
	}

	public static void setKey(int key) {
		Util.key = key;
	}

}
