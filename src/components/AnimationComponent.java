package components;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class AnimationComponent {
	public Animation animation;
	public HashMap<Integer, Animation> animations;
	public float[] size;
	public int[] d = null;
	public Image[] i = null;
	
	public AnimationComponent() {
		animation = new Animation();
		animations = new HashMap<Integer, Animation>();
		size = new float[2];
		size[0] = 0;
		size[1] = 0;
	}
	
	public AnimationComponent(Animation a) {
		animation = a;
		animations = new HashMap<Integer, Animation>();
		this.d = a.getDurations();
		this.i = new Image[d.length];
		for (int i = 0; i < this.i.length; i++) {
			this.i[i] = a.getImage(i);
		}
		size = new float[2];
		size[0] = 0;
		size[1] = 0;		
	}
	
	public AnimationComponent(Image[] i, int[] d) {
		animation = new Animation(i, d);
		animations = new HashMap<Integer, Animation>();
		this.i = i;
		this.d = d;
		size = new float[2];
		size[0] = 0;
		size[1] = 0;
	}
	
}
