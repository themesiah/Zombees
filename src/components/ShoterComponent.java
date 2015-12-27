package components;

public class ShoterComponent {
	public int delay;
	public int timer;
	public String src = "data/bullet.png";
	
	public ShoterComponent() {
		delay = 100;
		timer = 0;
	}
	
	public ShoterComponent(int delay) {
		this.delay = delay;
		timer = 0;
	}
}
