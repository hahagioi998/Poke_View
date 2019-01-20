package com.qiu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.qiu.model.Poke;
import com.qiu.util.ActionOutPoke;
import com.qiu.util.OutPoke;
import com.qiu.util.Util;
import com.qiu.view.LoginFrame;

public class GameAction implements ActionListener {

	private LoginFrame lf;

	public GameAction(LoginFrame lf) {// ���췽������

		this.setLf(lf);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String order = e.getActionCommand();// ��ȡ��Ӧ��ָ��
		if (order.equals("deal")) {
			Util.upsetPoke(Util.pokeList);
			Util.setKey(1);
			lf.getGf().getStartItem().setText("�ط�(D)");
			lf.getGf().getStartItem().setActionCommand("reDeal");

			// Util.show();
		} else if (order.equals("reDeal")) {// ���·���
			// ���ڼ���,��ʾһ��
			int key = JOptionPane.showConfirmDialog(lf.getGf(), "��Ϸ���ڽ���,�Ƿ�ȷ�����·���?", "��ܰ��ʾ",
					JOptionPane.OK_CANCEL_OPTION);
			if (key == JOptionPane.OK_OPTION) {
				Util.pokeList.clear();// �Ƴ����ϵ�����Ԫ��
				Util.playerOne.getPlayerPoke().clear();
				Util.playerTwo.getPlayerPoke().clear();
				Util.playerThree.getPlayerPoke().clear();
				Util.playerOne.getOutPoke().clear();
				Util.playerTwo.getOutPoke().clear();
				Util.playerThree.getOutPoke().clear();
				OutPoke.temp.clear();
				OutPoke.index.clear();
				Util.landowner = 4;
				// һ�����ݶ�Ҫ����
				Util.initPoke();// ��ʼ������
				Util.player_Poke = 0;// ������������Ϊ0
				Util.playerOne.setPoints(4);
				Util.playerTwo.setPoints(4);
				Util.playerThree.setPoints(4);
				Util.drawMark = 0;
				Util.flag = true;
				lf.getGf().getGamePanel().getShowPoke().setText("����");
				Util.callPlayer = 0;
				Util.mark = 0;
				Util.hidePoints = 0;
				Util.isPoke = -1;
				Util.pitchOn = 0;
				Util.type = 0;
				OutPoke.score = 0;
				lf.getGf().getGamePanel().getTrusteeship().setText("�й�");
				OutPoke.isTrusteeship = false;
				Util.upsetPoke(Util.pokeList);
				Util.setKey(1);
				// Util.reStart = true;
			}
		} else if (order.equals("selete")) {
			lf.getSd().setVisible(true);
		} else if (order.equals("score")) {
			lf.getScd().setVisible(true);
		} else if (order.equals("exit")) {
			// ���ڼ���,��ʾһ��
			int key = JOptionPane.showConfirmDialog(lf.getGf(), "���Ҫ�˳���Ϸ��?", "��ܰ��ʾ", JOptionPane.OK_CANCEL_OPTION);
			if (key == JOptionPane.OK_OPTION) {
				lf.getGf().setVisible(false);
				lf.setVisible(true);
			}
		} else if (order.equals("rule")) {
			String ruleText = "����:\n" + "һ���� 54 �ţ�һ�� 17 �ţ��� 3 �������ƣ���ȷ������֮ǰ��Ҳ�" + "\n�ܿ����ƣ�����ȷ���󣬵������������Ʒָ�������"
					+ "\n��һ�����Ƶ����" + "\n��һ�ŵ����ƣ������Ʊ�˭�õ�˭�ǵ���������ƽ̨\n" + "��Ϊ��һ�ֽ��Ƶ������ϵͳѡ����\n" + "����"
					+ "\n���ư����Ƶ�˳���������У�ÿ��ֻ�ܽ�һ�Ρ�����ʱ���ԽС�1�֡���" + "\n��2�֡�����3�֡��������С����������ֻ�ܽб�ǰ����Ҹߵķֻ��߲��С�"
					+ "\n���ƽ��������з�ֵ�������Ϊ�������������ҽС�3�֡���������" + "\n�����ƣ������Ϊ��������������У������·��ƣ����½��ơ�" + "\n�ӱ�"
					+ "\n���ƽ�������ҿ�ѡ�񡰼ӱ����͡����ӱ������ӱ��������Ӯ����÷�" + "\n���������ӱ��÷��ޱ��ʱ仯��" + "\n����"
					+ "\n�����ŵ��ƽ��������������������������˶��ܿ������������ȳ��ƣ�Ȼ" + "\n����ʱ��˳�����γ��ƣ��ֵ��û�����ʱ���û�����ѡ�񡰲����������"
					+ "\n��һ����Ҵ���ơ�ĳһ��ҳ�����ʱ�������֡�";
			JOptionPane.showMessageDialog(lf.getGf(), ruleText, "����˵��", JOptionPane.CLOSED_OPTION);
		} else if (order.equals("about")) {
			String textAbout = "������ 1.0Bate\n(C)2018-1219  ������Ʒ";
			JOptionPane.showMessageDialog(lf.getGf(), textAbout, "����", JOptionPane.CLOSED_OPTION);
		} else if (order.equals("showPoke")) {
			if (Util.flag) {
				Util.flag = false;// ���ƿ���
				lf.getGf().getGamePanel().getShowPoke().setText("����");
			} else {
				Util.flag = true;// ���ƿ���
				lf.getGf().getGamePanel().getShowPoke().setText("����");
			}
		} else if (order.equals("oneMark")) {
			Util.drawMark = 1;
			Util.playerOne.setPoints(1);// ��ҽ�1��
		} else if (order.equals("twoMark")) {
			Util.drawMark = 2;
			Util.playerOne.setPoints(2);// ��ҽ�1��
		} else if (order.equals("threeMark")) {
			Util.drawMark = 3;
			Util.playerOne.setPoints(3);// ��ҽ�1��
		} else if (order.equals("noMark")) {
			Util.drawMark = 4;
			Util.playerOne.setPoints(0);// ��ҽ�1��
		} else if (order.equals("outPoke")) {
			if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() == 0) {// ��������ҵĳ��Ƽ���Ϊ��,˵���Լ�����,�������¶�������
				Util.type = 0;
			}
			if ((Util.type == 0 && Util.pitchOn == 1) || (Util.type == 1 && Util.pitchOn == 1)) {// ����ǵ���,type����Ϊ0.���Խ���,���ǵ�����ʱ��ø���ǰ��һ���˳��Ƶ����������Լ�����
				if (Util.playerTwo.getOutPoke().size() != 0) {// ���ҳ��Ƽ��ϲ�Ϊ��,˵���г���
					ActionOutPoke.solo(Util.playerOne, Util.playerTwo);
				} else if (Util.playerThree.getOutPoke().size() != 0 && Util.playerTwo.getOutPoke().size() == 0) {
					ActionOutPoke.solo(Util.playerOne, Util.playerTwo);
				} else {// �����������������,˵�����ھ͸��Լ���
					Util.type = 1;// ��1����
					Util.isPoke = 1;// 1�ͱ�ʾҪ����
				}
			} else if ((Util.type == 0 && Util.pitchOn == 2) || (Util.type == 2 && Util.pitchOn == 2)) {// ����ǵ���,type����Ϊ0.���Խ���,���ǵ�����ʱ��ø���ǰ��һ���˳��Ƶ����������Լ�����
				if (Util.playerTwo.getOutPoke().size() != 0) {// ��һ���г���
					ActionOutPoke.doublePoke(Util.playerOne, Util.playerTwo);
				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {// ��һ��û�г���,��һ�ҳ�����
					ActionOutPoke.doublePoke(Util.playerOne, Util.playerThree);
				} else {// һ�����Լ�����
					ArrayList<Poke> temp = new ArrayList<Poke>();// ��ʱһ������,�������������������
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {
						Poke p = Util.playerOne.getPlayerPoke().get(i);// ��ʱ�˿�
						if (p.getPokeY() == 485) {// �����������Ƶ���ֵ�ǲ���һ��
							temp.add(p);
						}
					}
					Util.pokeSort(temp);
					if (temp.get(0).getNumber() == temp.get(1).getNumber()) {// ��ʱ�˿��Ƶ���ֵһ��ʱ���Ƕ���
						Util.type = 2;// �����Լ����˶���
						Util.isPoke = 1;// ���Ƴ�
					} else if (temp.get(0).getNumber() == 100 && temp.get(1).getNumber() == 99) {// ��ը������
						OutPoke.score *= 4;
						OutPoke.doubleBoomCount++;
						Util.type = 8;// ��ը������
						Util.isPoke = 1;
					} else {// �����ʾ�������� ,�������ƹ�λ
						for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// ����ȫ����λ
							Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				}

			} else if ((Util.type == 0 && Util.pitchOn == 3) || (Util.type == 3 && Util.pitchOn == 3)) {
				// ����ǵ���,type����Ϊ0.���Խ���,���ǵ�����ʱ��ø���ǰ��һ���˳��Ƶ����������Լ�����
				if (Util.playerTwo.getOutPoke().size() != 0) {// ��һ�ҳ���
					ActionOutPoke.triple(Util.playerOne, Util.playerTwo);
				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {// ��һ��û�г�����,��һ�ҳ���
					ActionOutPoke.triple(Util.playerOne, Util.playerThree);
				} else {// һ���Լ�����
					ArrayList<Poke> temp = new ArrayList<Poke>();// ��ʱһ������,�������������������
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {
						Poke p = Util.playerOne.getPlayerPoke().get(i);// ��ʱ�˿�
						if (p.getPokeY() == 485) {// �����������Ƶ���ֵ�ǲ���һ��
							temp.add(p);
						}
					}
					if ((temp.get(0).getNumber() == temp.get(1).getNumber())
							&& (temp.get(0).getNumber() == temp.get(2).getNumber())) {// ��һ�ź͵ڶ�����ͬ.���Һ͵�������ͬ
						Util.type = 3;// �����Լ���������һ����
						Util.isPoke = 1;// ���Ƴ�
					} else {// �����ʾ�������� ,�������ƹ�λ
						for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// ����ȫ����λ
							Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				}

			} else if ((Util.type == 0 && Util.pitchOn == 4) || (Util.type == 4 && Util.pitchOn == 4)) {// ����һ������
				if (Util.playerTwo.getOutPoke().size() != 0) {// �ϼ�����
					ActionOutPoke.tripleAndOne(Util.playerOne, Util.playerTwo);

				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {// �ϼ�����,�¼�����
					ActionOutPoke.tripleAndOne(Util.playerOne, Util.playerThree);
				} else {// �����Ҷ�û��
					ArrayList<Poke> temp = new ArrayList<Poke>();// ��ʱ����
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// ����һ���ҳ�����������
						Poke p = Util.playerOne.getPlayerPoke().get(i);
						if (p.getPokeY() == 485) {
							temp.add(p);
						}
					}
					Util.pokeSort(temp);// ����ʱ�����Ÿ���
					if ((temp.get(0).getNumber() == temp.get(1).getNumber())
							&& (temp.get(0).getNumber() == temp.get(2).getNumber())
							&& (temp.get(0).getNumber() != temp.get(3).getNumber())) {// ��һ������һ����
						Util.type = 4;
						Util.isPoke = 1;

					} else if ((temp.get(1).getNumber() == temp.get(2).getNumber())
							&& (temp.get(1).getNumber() == temp.get(3).getNumber())
							&& (temp.get(1).getNumber() != temp.get(0).getNumber())) {// ������һ����
						Util.type = 4;
						Util.isPoke = 1;
					} else if (temp.get(0).getNumber() == temp.get(1).getNumber()
							&& temp.get(0).getNumber() == temp.get(2).getNumber()
							&& temp.get(0).getNumber() == temp.get(3).getNumber()) {// �ĸ�һ����ը��
						OutPoke.score *= 4;
						OutPoke.boomCount++;
						Util.type = 8;
						Util.isPoke = 1;
					} else {
						for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// ����ȫ����λ
							Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				}

			} else if ((Util.type == 0 && Util.pitchOn == 5) || (Util.type == 5 && Util.pitchOn == 5)) {// ����һ��
				if (Util.playerTwo.getOutPoke().size() != 0) {// ǰһ�Ҳ�Ϊ��
					ActionOutPoke.fivePoke(Util.playerOne, Util.playerTwo);

				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {// ǰһ��Ϊ��,��һ�Ҳ�Ϊ��
					ActionOutPoke.fivePoke(Util.playerOne, Util.playerThree);

				} else {
					ArrayList<Poke> temp = new ArrayList<Poke>();// ��ʱ����
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// ����һ���ҳ�����������
						Poke p = Util.playerOne.getPlayerPoke().get(i);
						if (p.getPokeY() == 485) {
							temp.add(p);
						}
					}
					Util.pokeSort(temp);// ����ʱ�����Ÿ���
					if ((temp.get(0).getNumber() == temp.get(1).getNumber())
							&& (temp.get(0).getNumber() == temp.get(2).getNumber())
							&& (temp.get(0).getNumber() != temp.get(3).getNumber())
							&& (temp.get(3).getNumber() == temp.get(4).getNumber())) {// ��һ������������
						Util.type = 5;
						Util.isPoke = 1;
					} else if ((temp.get(2).getNumber() == temp.get(3).getNumber())
							&& (temp.get(2).getNumber() == temp.get(4).getNumber())
							&& (temp.get(2).getNumber() != temp.get(0).getNumber())
							&& (temp.get(0).getNumber() == temp.get(1).getNumber())) {// ������������
						Util.type = 5;
						Util.isPoke = 1;
					} else if ((temp.get(0).getNumber() - temp.get(1).getNumber() == 1)
							&& (temp.get(0).getNumber() - temp.get(2).getNumber() == 2)
							&& (temp.get(0).getNumber() - temp.get(3).getNumber() == 3)
							&& (temp.get(0).getNumber() - temp.get(4).getNumber() == 4)) {// ˳��5�ŵ�ʱ��
						Util.type = 6;
						Util.isPoke = 1;

					} else {
						for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// ����ȫ����λ
							Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
						}
					}
				}

			} else if ((Util.type == 0 && Util.pitchOn > 5) || (Util.type == 6 && Util.pitchOn >= 5)) {// ��������������5
				if (Util.playerTwo.getOutPoke().size() != 0) {
					ActionOutPoke.beyondFivePoke(Util.playerOne, Util.playerTwo);
				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {
					ActionOutPoke.beyondFivePoke(Util.playerOne, Util.playerThree);
				} else {
					ArrayList<Poke> temp = new ArrayList<Poke>();// ��ʱ����
					for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// ����һ���ҳ�����������
						Poke p = Util.playerOne.getPlayerPoke().get(i);
						if (p.getPokeY() == 485) {
							temp.add(p);
						}
					}
					Util.pokeSort(temp);// �Ÿ���ȷ��û��
					boolean k = true;// �����ж��ǲ���˳�ӵķ���ֵ 1--˳�� 2--˫˳
					boolean b = false;// �ж��ǲ���˫˳
					// ˳�ӵ��ж�
					for (int i = temp.size() - 1; i > 0; i--) {
						if (temp.get(i - 1).getNumber() - temp.get(i).getNumber() != 1) {// ����������,�������
							k = false;
						}
					}
					// ˫˳���ж�
					if (temp.size() % 2 == 0) {
						for (int i = temp.size() - 1; i > 2; i -= 2) {
							// ������ͬ,�Ӻ��������ͬ�ұ�ǰ���Ŵ�1
							if (temp.get(i).getNumber() == temp.get(i - 1).getNumber()
									&& temp.get(i - 2).getNumber() - temp.get(i).getNumber() == 1
									&& temp.get(i - 2).getNumber() == temp.get(i - 3).getNumber()) {
								b = true;
							} else {
								b = false;
								break;
							}
						}
					}
					if (k) {
						Util.type = 6;
						Util.isPoke = 1;
					}
					if (b) {
						Util.type = 7;
						Util.isPoke = 1;
					}
					if (k == false && b == false) {
						Util.setCoordinate(Util.playerOne.getPlayerPoke());
					}
				}
			} else if (Util.pitchOn == 4 || Util.pitchOn == 2) {
				if (Util.playerTwo.getOutPoke().size() != 0) {
					ActionOutPoke.boomPoke(Util.playerOne, Util.playerTwo);
				} else if (Util.playerTwo.getOutPoke().size() == 0 && Util.playerThree.getOutPoke().size() != 0) {
					ActionOutPoke.boomPoke(Util.playerOne, Util.playerThree);

				}

			} else {
				for (int i = 0; i < Util.playerOne.getPlayerPoke().size(); i++) {// ����ȫ����λ
					Util.playerOne.getPlayerPoke().get(i).setPokeY(500);
				}
			}
			Util.pitchOn = 0;// ��0,���������ʹ��
		} else if (order.equals("noOut")) {
			Util.isPoke = 0;// Ҫ����
		} else if (order.equals("back")) {
			//��ȥ���ư��ظ�ԭ����λ��
			Util.setCoordinate(Util.playerOne.getPlayerPoke());
		} else if (order.equals("trusteeship")) {
			if (OutPoke.isTrusteeship == false) {
				lf.getGf().getGamePanel().getTrusteeship().setText("ȡ���й�");
				OutPoke.isTrusteeship = true;
			} else if (OutPoke.isTrusteeship = true) {
				lf.getGf().getGamePanel().getTrusteeship().setText("�й�");
				OutPoke.isTrusteeship = false;
			}

		} else if (order.equals("OneceAgain")) {
			lf.getGf().getGamePanel().getRd().setVisible(false);
			Util.pokeList.clear();// �Ƴ����ϵ�����Ԫ��
			Util.playerOne.getPlayerPoke().clear();
			Util.playerTwo.getPlayerPoke().clear();
			Util.playerThree.getPlayerPoke().clear();
			Util.playerOne.getOutPoke().clear();
			Util.playerTwo.getOutPoke().clear();
			Util.playerThree.getOutPoke().clear();
			OutPoke.temp.clear();
			OutPoke.index.clear();
			Util.landowner = 4;
			// һ�����ݶ�Ҫ����
			Util.initPoke();// ��ʼ������
			Util.player_Poke = 0;// ������������Ϊ0
			Util.playerOne.setPoints(4);
			Util.playerTwo.setPoints(4);
			Util.playerThree.setPoints(4);
			Util.drawMark = 0;
			Util.flag = true;
			lf.getGf().getGamePanel().getShowPoke().setText("����");
			Util.callPlayer = 0;
			Util.mark = 0;
			Util.hidePoints = 0;
			Util.isPoke = -1;
			Util.pitchOn = 0;
			Util.type = 0;
			OutPoke.score = 0;
			lf.getGf().getGamePanel().getTrusteeship().setText("�й�");
			OutPoke.isTrusteeship = false;
			Util.upsetPoke(Util.pokeList);
			Util.setKey(1);
		} else if (order.equals("exitResult")) {
			lf.getGf().getGamePanel().getRd().setVisible(false);
		}else if(order.equals("reset")){
			Util.playerOne.setScore(1000);
			Util.playerTwo.setScore(1000);
			Util.playerThree.setScore(1000);
		}else if(order.equals("sure")){
			lf.getScd().setVisible(false);
		}
	}

	public LoginFrame getLf() {
		return lf;
	}

	public void setLf(LoginFrame lf) {
		this.lf = lf;
	}

}
