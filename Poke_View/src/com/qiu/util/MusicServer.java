package com.qiu.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class MusicServer {

	private File music = new File("bgm/music.WAV");// 音乐文件路径
	private AudioClip ac;// 音频对象

	public MusicServer() {
		try {
			ac = Applet.newAudioClip(music.toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loop() {//循环播放
		// TODO Auto-generated method stub
		ac.loop();
	}

	public void stop() {//循环播放
		// TODO Auto-generated method stub
		ac.stop();
	}
	
}
