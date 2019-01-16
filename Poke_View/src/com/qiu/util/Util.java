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

	// 界面的尺寸参数
	public static final int LOGINFRAME_W = 500;// 登录界面的宽度
	public static final int LOGINFRAME_H = 400;// 登录界面的高度
	public static final int MAINFRAME_W = 1000;// 主窗体的宽度
	public static final int MAINFRAME_H = 700;// 主窗体的高度

	public static final int LOGINPANEL_W = 500;// 登录面板的宽度
	public static final int LOGINPANEL_H = 400;// 登录面板的宽度

	public static final int LOGINPANELBUTTON_W = 200;// 登录面板按钮的横坐标
	public static final int LOGINPANELSTARTBUTTON_H = 100;// 开始按钮的纵坐标
	public static final int LOGINPANELINTRODUCEBUTTON_H = 145;// 开始按钮的纵坐标
	public static final int LOGINPANELEXITBUTTON_H = 190;// 开始按钮的纵坐标

	public static final int BUTTON_W = 90;// 登录按钮宽度
	public static final int BUTTON_H = 35;// 登录按钮长度

	public static final int POKE_W = 60;// poke牌的宽度
	public static final int POKE_H = 90;// poke牌的高度

	// 图片路劲
	public static final Image LOGIN = new ImageIcon("img/login.jpg").getImage();// 获取登录背景图的路径及图片
	public static final Image GAME = new ImageIcon("img/game.jpg").getImage();// 获取登录背景图的路径及图片
	public static final Image P_ONE = new ImageIcon("img/playerOne.jpg").getImage();// 玩家头像
	public static final Image P_TWO = new ImageIcon("img/playerTwo.jpg").getImage();// 玩家头像
	public static final Image P_THREE = new ImageIcon("img/playerThree.jpg").getImage();// 玩家头像
	public static final Image POKE_BACK = new ImageIcon("img/poke/牌背.png").getImage();// 牌背的显示
	public static final Image REMAIN = new ImageIcon("img/reMain.png").getImage();//记牌器背景图

	// 每个玩家手中初始的牌数
	public static final int PLAYERE_POKE = 17;
	public static final int HIDE_POKE = 3;// 底牌数量
	// 发牌次数
	public static int player_Poke = 0;

	// 设置一个步骤开关,来开启步骤流程
	public static int key = 0;
	public static boolean flag = true;// 控制明牌和暗牌的按钮

	// 设置一个步骤
	// public static

	// 创建poke牌
	public static ArrayList<Poke> pokeList = new ArrayList<Poke>();// 放顺序poke牌的集合
	// 创建poke的初始方法

	public static void initPoke() {

		int[] number = { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 22 };// 每个花色的面值
		String[] shape = { "方块", "黑桃", "红心", "梅花" };// 花色

		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < number.length; j++) {
				String path = "img/poke/" + shape[i] + number[j] + ".png";
				pokeList.add(new Poke(new ImageIcon(path).getImage(), number[j],shape[i]));
			}
		}
		pokeList.add(new Poke(new ImageIcon("img/poke/王99.png").getImage(), 99, "小王"));
		pokeList.add(new Poke(new ImageIcon("img/poke/王100.png").getImage(), 100, "大王"));
	}

	// 将集合中的牌打乱
	public static void upsetPoke(ArrayList<Poke> list) {
		PokeDao pd = new PokeDaoImp();
		pd.upsetPoke(list);
	}

	public static Player playerOne = new PlayerOne();
	public static Player playerTwo = new PlayerTwo();
	public static Player playerThree = new PlayerThree();
	public static Player[] player = { playerOne, playerThree, playerTwo };

	// 给玩家的手牌排序
	public static void pokeSort(ArrayList<Poke> list) {
		// 采用选择排序法
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
	//给自己的牌设置一个固定的坐标
	public static void setCoordinate(ArrayList<Poke> list){//传进来的参数集合就是要赋值的,其实就是自己的手牌要设置坐标
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setPokeX(250 + (i * 25));//250就是第一张牌的横坐标,每右边一张,就是横坐标加25像素
			list.get(i).setPokeY(500);//500就是自己手牌的纵坐标,都一样
		}
	}

	public static int callPlayer = 0;//随机谁叫地主  0自己,1东家,2西家
	public static int drawMark = 0;//在面板上画几分开关
	public static int mark = 0;//定义一个标记,用来对电脑叫几分控制 0--不叫  1--1分  2--2分  3--3分
	public static int landowner = 0;//判断自己叫分的时候,叫分按钮可不可见,判断谁是地主,1自己,2西家,3东家
	public static int hidePoints = 0;//判断西家叫过的分数,自己叫的分数一定要比上一家大,该变量用来控制比上一家小的按钮不可见
	public static int isPoke = -1;//要不起的开关,默认要的起 0--自己要不起  1--自己要的起  2--东家要不起  3--西家要不起
	public static int pitchOn = 0;//自己选中了几张牌
	public static int type = 0;//出牌的类型  1--是单张  2--是对子   3--是三张  4--三代一  5--三代二
	//叫分的方法
	public static void callPlayers(){
		Random ran = new Random();
		Util.callPlayer = ran.nextInt(3);
	}
	
	public static void callPoints(){
		if(Util.callPlayer == 1){//东家叫分
			boolean boo = Util.lookForBomb(Util.playerThree);
			if((playerThree.getPlayerPoke().get(0).getNumber() == 100 &&//有王炸
					playerThree.getPlayerPoke().get(1).getNumber() == 99)|| boo){//或者是有其他的炸弹
				Util.playerThree.setPoints(3);//叫三分
				Util.drawMark = 5;//东家叫3分
			}else if((playerThree.getPlayerPoke().get(0).getNumber() == 100 &&//有一个大王并且有2个2或者3个2
					playerThree.getPlayerPoke().get(1).getNumber() == 22 && 
					playerThree.getPlayerPoke().get(2).getNumber() == 22) 
					|| (playerTwo.getPlayerPoke().get(0).getNumber() == 22 && 
					playerTwo.getPlayerPoke().get(1).getNumber() == 22 && 
					playerTwo.getPlayerPoke().get(2).getNumber() == 22)){
				Util.playerThree.setPoints(2);//叫二分
				Util.drawMark = 6;//东家叫2分
			}else if(playerThree.getPlayerPoke().get(0).getNumber() == 99 &&//有一个小王并且有2个2
					playerThree.getPlayerPoke().get(1).getNumber() == 22 ){
				Util.playerThree.setPoints(1);//叫一分
				Util.drawMark = 7;//东家叫1分
			}else{
				Util.playerThree.setPoints(0);//不叫分
				Util.drawMark = 8;//东家不叫分
			}
		}else if(Util.callPlayer == 2){
			boolean boo = Util.lookForBomb(Util.playerTwo);
			if((playerTwo.getPlayerPoke().get(0).getNumber() == 100 &&//有王炸
					playerTwo.getPlayerPoke().get(1).getNumber() == 99)|| boo){//或者是有其他的炸弹
				Util.playerTwo.setPoints(3);//叫三分
				Util.drawMark = 9;//西家叫3分
			}else if((playerTwo.getPlayerPoke().get(0).getNumber() == 100 &&//有一个大王并且有2个2或者直接有三个2
					playerTwo.getPlayerPoke().get(1).getNumber() == 22 && 
					playerTwo.getPlayerPoke().get(2).getNumber() == 22) 
					|| (playerTwo.getPlayerPoke().get(0).getNumber() == 22 && 
					playerTwo.getPlayerPoke().get(1).getNumber() == 22 && 
					playerTwo.getPlayerPoke().get(2).getNumber() == 22)){
				Util.playerTwo.setPoints(2);//叫二分
				Util.drawMark = 10;//西家叫2分
			}else if(playerTwo.getPlayerPoke().get(0).getNumber() == 99 &&//有一个小王并且有2个2
					playerTwo.getPlayerPoke().get(1).getNumber() == 22){
				Util.playerTwo.setPoints(1);//叫一分
				Util.drawMark = 11;//西家叫1分
			}else{
				Util.playerTwo.setPoints(0);//不叫分
				Util.drawMark = 12;//西家不叫分
			}
		}
			
	}
		
	
	//寻找炸弹
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
//	//寻找顺子
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
