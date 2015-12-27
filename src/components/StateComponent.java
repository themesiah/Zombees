package components;

public class StateComponent {
	public static int STATE_INACTIVE = 0;
	public static int STATE_STAND = 1;
	public static int STATE_WALK = 2;
	public static int STATE_RUN = 3;
	public static int STATE_DYING = 4;
	public static int STATE_DEAD = 5;
	public static int STATE_ATTACKING = 6;
	
	public int state;
	public int defaultState;
	
	public StateComponent() {
		state = 0;
		defaultState = 0;
	}
	
	public StateComponent(int state) {
		this.state = state;
		this.defaultState = state;
	}

}
