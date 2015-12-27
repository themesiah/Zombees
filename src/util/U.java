package util;

import java.io.BufferedReader;
import java.io.FileReader;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class U {
	public static Animation getAniFromFile(String path) {
		String line = null;
		int aniDelay = 0;
		Animation a = new Animation();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			line = br.readLine();
			aniDelay = Integer.valueOf(line);
			while (line != null) {
				line = br.readLine();
				if (line!= null) {
					a.addFrame(new Image(line), aniDelay);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return a;
	}
}
