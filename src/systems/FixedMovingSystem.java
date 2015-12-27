package systems;

import main.Main;
import main.Play;
import components.*;
import entity.GE;

public class FixedMovingSystem {
	public void update(Long key, int delta) {
		PositionComponent pc = GC.positionComponent.get(key);
		SpeedComponent sc = GC.speedComponent.get(key);
		
		float rad = (float) Math.toRadians(pc.facing);
		
		float movx = (float) (sc.speed * delta/Play.MS_PER_SECOND * Math.sin(rad));
		float movy = (float) (sc.speed * delta/Play.MS_PER_SECOND * Math.cos(rad));
		
		pc.x += movx;
		pc.y -= movy;
		pc.centerx += movx;
		pc.centery -= movy;
		
		if (pc.x < -50 || pc.x > Main.GAMEWIDTH + 50 || pc.y < -50 || pc.y > Main.GAMEHEIGHT + 50) {
			GE.delete(key);
		}
	}
}
