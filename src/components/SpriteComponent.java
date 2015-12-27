package components;

import org.newdawn.slick.Image;

public class SpriteComponent {
	
	public SpriteComponent() {
		img = null;
		size = new float[2];
		size[0] = 0;
		size[1] = 0;
	}
	public SpriteComponent(Image img) {
		this.img = img;
		size = new float[2];
		size[0] = 0;
		size[1] = 0;
	}
	public Image img;
	public float[] size;
}
