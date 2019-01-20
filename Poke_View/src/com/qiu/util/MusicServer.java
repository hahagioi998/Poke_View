package com.qiu.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class MusicServer {

	private File music = new File("bgm/music.WAV");// �����ļ�·��
	private AudioClip ac;// ��Ƶ����

	public MusicServer() {
		try {
			ac = Applet.newAudioClip(music.toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loop() {//ѭ������
		// TODO Auto-generated method stub
		ac.loop();
	}

	public void stop() {//ѭ������
		// TODO Auto-generated method stub
		ac.stop();
	}
	
}
