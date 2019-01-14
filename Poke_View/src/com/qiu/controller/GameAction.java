package com.qiu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.qiu.model.Poke;
import com.qiu.util.Util;
import com.qiu.view.LoginFrame;

public class GameAction implements ActionListener {
	
	private LoginFrame lf;

	public GameAction(LoginFrame lf) {// ���췽������

		this.setLf(lf);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String order = e.getActionCommand();//��ȡ��Ӧ��ָ��
		if(order.equals("deal")){
			Util.upsetPoke(Util.pokeList);
			Util.setKey(1);
			lf.getGf().getStartItem().setText("�ط�(D)");
			lf.getGf().getStartItem().setActionCommand("reDeal");
			
//			Util.show();
		}else if(order.equals("reDeal")){//���·���
			// ���ڼ���,��ʾһ��
			int key = JOptionPane.showConfirmDialog(lf.getGf(), "��Ϸ���ڽ���,�Ƿ�ȷ�����·���?", "��ܰ��ʾ", JOptionPane.OK_CANCEL_OPTION);
			if (key == JOptionPane.OK_OPTION) {
				Util.pokeList.clear();//�Ƴ����ϵ�����Ԫ��
				Util.playerOne.getPlayerPoke().clear();
				Util.playerTwo.getPlayerPoke().clear();
				Util.playerThree.getPlayerPoke().clear();
				Util.playerOne.getOutPoke().clear();
				Util.playerTwo.getOutPoke().clear();
				Util.playerThree.getOutPoke().clear();
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
//				lf.getGf().getGamePanel().removeMouseListener(lf.getGm());
				
				Util.upsetPoke(Util.pokeList);
				Util.setKey(1);
//				Util.reStart = true;
			}
		}else if(order.equals("selete")){
			lf.getSd().setVisible(true);
		}else if(order.equals("score")){
			System.out.println("ccc");
		}else if(order.equals("exit")){
			// ���ڼ���,��ʾһ��
			int key = JOptionPane.showConfirmDialog(lf.getGf(), "���Ҫ�˳���Ϸ��?", "��ܰ��ʾ", JOptionPane.OK_CANCEL_OPTION);
			if (key == JOptionPane.OK_OPTION) {
				lf.getGf().setVisible(false);
				lf.setVisible(true);
			}
		}else if(order.equals("rule")){
			String ruleText = "����:\n"
					+ "һ���� 54 �ţ�һ�� 17 �ţ��� 3 �������ƣ���ȷ������֮ǰ��Ҳ�"
					+ "\n�ܿ����ƣ�����ȷ���󣬵������������Ʒָ�������"
					+ "\n��һ�����Ƶ����"
					+ "\n��һ�ŵ����ƣ������Ʊ�˭�õ�˭�ǵ���������ƽ̨\n"
					+ "��Ϊ��һ�ֽ��Ƶ������ϵͳѡ����\n"
					+ "����"
					+ "\n���ư����Ƶ�˳���������У�ÿ��ֻ�ܽ�һ�Ρ�����ʱ���ԽС�1�֡���"
					+ "\n��2�֡�����3�֡��������С����������ֻ�ܽб�ǰ����Ҹߵķֻ��߲��С�"
					+ "\n���ƽ��������з�ֵ�������Ϊ�������������ҽС�3�֡���������"
					+ "\n�����ƣ������Ϊ��������������У������·��ƣ����½��ơ�"
					+ "\n�ӱ�"
					+ "\n���ƽ�������ҿ�ѡ�񡰼ӱ����͡����ӱ������ӱ��������Ӯ����÷�"
					+ "\n���������ӱ��÷��ޱ��ʱ仯��"
					+ "\n����"
					+ "\n�����ŵ��ƽ��������������������������˶��ܿ������������ȳ��ƣ�Ȼ"
					+ "\n����ʱ��˳�����γ��ƣ��ֵ��û�����ʱ���û�����ѡ�񡰲����������"
					+ "\n��һ����Ҵ���ơ�ĳһ��ҳ�����ʱ�������֡�";
			JOptionPane.showMessageDialog(lf.getGf(), ruleText, "����˵��", JOptionPane.CLOSED_OPTION);
		}else if(order.equals("about")){
			String textAbout = "������ 1.0Bate\n(C)2018-1219  ������Ʒ";
			JOptionPane.showMessageDialog(lf.getGf(), textAbout, "����", JOptionPane.CLOSED_OPTION);
		}else if(order.equals("showPoke")){			
			if(Util.flag){
				Util.flag = false;//���ƿ���
				lf.getGf().getGamePanel().getShowPoke().setText("����");
			}else{
				Util.flag = true;//���ƿ���
				lf.getGf().getGamePanel().getShowPoke().setText("����");
			}
		}else if(order.equals("oneMark")){
			Util.drawMark = 1;
			Util.playerOne.setPoints(1);//��ҽ�1��
		}else if(order.equals("twoMark")){
			Util.drawMark = 2;
			Util.playerOne.setPoints(2);//��ҽ�1��
		}else if(order.equals("threeMark")){
			Util.drawMark = 3;
			Util.playerOne.setPoints(3);//��ҽ�1��
		}else if(order.equals("noMark")){
			Util.drawMark = 4;
			Util.playerOne.setPoints(0);//��ҽ�1��
		}else if(order.equals("outPoke")){
//			System.out.println("�м���������:" + Util.pitchOn);
			if(Util.pitchOn == 1){//ֻ��һ����
				if(Util.playerTwo.getOutPoke().size() != 0){
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {
						if(Util.playerOne.getPlayerPoke().get(i).getPokeY() == 485){//����������������
							if(Util.playerOne.getPlayerPoke().get(i).getNumber() 
									> Util.playerTwo.getOutPoke().get(0).getNumber()){//�Ƚϴ�С
								Util.isPoke = 1;//1�ͱ�ʾҪ����
//								System.out.println("hhhhhhhh");
							}else{
								Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
								Util.isPoke = -1;//��ʾѡ�񲻶�,���³���
							}
							break;
						}
					}
				}else{
					Util.isPoke = 1;//1�ͱ�ʾҪ����
				}
			}else{
				Util.pitchOn = 0;//��0,���������ʹ��
				for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {//����ȫ����λ
					Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
				}
			}
			Util.pitchOn = 0;//��0,���������ʹ��
		}else if(order.equals("noOut")){
//			if(Util.playerOne.getPlayerPoke().size() != 20){//˵�����ǵ���,����ѡ�񲻳�
				Util.isPoke = 0;//Ҫ����
//			}else{//�ǵ���,�����ȳ���
//				JOptionPane.showMessageDialog(lf.getGf(), "���ǵ���,�����ȳ���!");
////				lf.getGf().getGamePanel().getNoOut().setVisible(false);
//			}
		}else if(order.equals("hint")){
			System.out.println("��ʾ");
		}else if(order.equals("trusteeship")){
			System.out.println("�й�");
		}
	}
	public LoginFrame getLf() {
		return lf;
	}

	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}

	
}
