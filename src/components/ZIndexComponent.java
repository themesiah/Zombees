package components;

public class ZIndexComponent {
	public static int BACKGROUND = 0;
	public static int PLAYER = 2;
	public static int ENEMY = 1;
	public static int BULLET = 3;
	public static int LAST = BULLET+1;
	
	public int zindex;
	
	public ZIndexComponent() {
		zindex = LAST;
	}
	public ZIndexComponent(int zindex) {
		this.zindex = zindex;
	}
}
