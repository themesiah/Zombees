package systems;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import main.MyLogger;
import components.GC;
import components.PositionComponent;

public class MouseDirectionSystem {
	
	public void getDirection(Long key, GameContainer gc) {
		PositionComponent pc = null;
		try {
			pc = GC.positionComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.INPUT, MyLogger.NO_KEY, MyLogger.WARNING, "PositionComponent", "MouseDirectionSystem", key);
			return;
		}
		Input i = gc.getInput();
		
		float playerx = pc.centerx ;
		float playery = pc.centery ;
		float mousex = i.getMouseX();
		float mousey = i.getMouseY();
		float adder = 0;
		
		float difx = mousex - playerx;
		float dify = mousey - playery;
		
		float rad = 0;
		float deg = 0;
		
		if (dify > 0) {
			adder = 180;
		}
		
		if (dify == 0 && difx >= 0) {
			pc.facing = 90;
		} else if (dify == 0 && difx < 0) {
			pc.facing = -90;
		} else {
			rad = (float) Math.atan(difx/dify);
			deg = (float) Math.toDegrees(rad);
			pc.facing = -deg + adder;
		}
		
	}
}
