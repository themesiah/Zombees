package components;

public class PositionComponent {
	public PositionComponent() {
		x = 0;
		y = 0;
		facing = 0;
		centerx = 0;
		centery = 0;
	}
	
	public PositionComponent(float x, float y, float facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
	}
	
	public float x;
	public float y;
	public float centerx;
	public float centery;
	public float facing;
}
