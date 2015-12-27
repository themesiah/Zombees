package systems;

import main.MyLogger;
import main.Play;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import components.GC;
import components.PositionComponent;
import components.SpeedComponent;
import components.StateComponent;

public class KeyboardInputSystem {
	public static int UP_KEY = Input.KEY_W;
	public static int DOWN_KEY = Input.KEY_S;
	public static int RIGHT_KEY = Input.KEY_D;
	public static int LEFT_KEY = Input.KEY_A;
	public static int SHOT_KEY = Input.KEY_Z;
	public static int FOCUS_KEY = Input.KEY_LSHIFT;
	public static int SPAWN1_KEY = Input.KEY_Q;
	public static int SPAWN2_KEY = Input.KEY_W;
	public static int SPAWN3_KEY = Input.KEY_E;
	public static int SPAWN4_KEY = Input.KEY_R;
	public static int MENU_KEY = Input.KEY_ESCAPE;
	
	public void getInput(Long key, GameContainer gc, int delta) {
		PositionComponent pc = null;
		SpeedComponent sc = null;
		StateComponent stc = null;
		try {
			pc = GC.positionComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.INPUT, MyLogger.NO_KEY, MyLogger.WARNING, "PositionComponent", "KeyboardInputSystem", key);
			return;
		}
		try {
			sc = GC.speedComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.INPUT, MyLogger.NO_KEY, MyLogger.WARNING, "SpeedComponent", "KeyboardInputSystem", key);
			return;
		}
		try {
			stc = GC.stateComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.INPUT, MyLogger.NO_KEY, MyLogger.WARNING, "StateComponent", "KeyboardInputSystem", key);
		}
		float lastx = pc.x;
		float lasty = pc.y;
		Input i = gc.getInput();
		if (i.isKeyDown(UP_KEY)) {
			pc.y -= sc.speed * delta/Play.MS_PER_SECOND;
			pc.centery -= sc.speed * delta/Play.MS_PER_SECOND;
		}
		if (i.isKeyDown(DOWN_KEY)) {
			pc.y += sc.speed * delta/Play.MS_PER_SECOND;
			pc.centery += sc.speed * delta/Play.MS_PER_SECOND;
		}
		if (i.isKeyDown(LEFT_KEY)) {
			pc.x -= sc.speed * delta/Play.MS_PER_SECOND;
			pc.centerx -= sc.speed * delta/Play.MS_PER_SECOND;
		}
		if (i.isKeyDown(RIGHT_KEY)) {
			pc.x += sc.speed * delta/Play.MS_PER_SECOND;
			pc.centerx += sc.speed * delta/Play.MS_PER_SECOND;
		}
		
		if (stc != null && (lastx != pc.x || lasty != pc.y)) {
			stc.state = StateComponent.STATE_WALK;
		} else if (stc != null) {
			stc.state = stc.defaultState;
		}
	}
}
