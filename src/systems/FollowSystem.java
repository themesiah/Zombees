package systems;

import main.MyLogger;
import main.Play;
import components.*;

public class FollowSystem {
	public void update(Long key, int delta) {
		FollowComponent fc = null;
		PositionComponent pc = null;
		SpeedComponent sc = null;
		PositionComponent opc = null;
		try {
			fc = GC.followComponent.get(key);
		} catch(Exception ex) {
			MyLogger.log(MyLogger.AI, MyLogger.NO_KEY, MyLogger.WARNING, "FollowComponent", "FollowSystem", key);
			return;
		}
		try {
			pc = GC.positionComponent.get(key);
		} catch(Exception ex) {
			MyLogger.log(MyLogger.AI, MyLogger.NO_KEY, MyLogger.WARNING, "PositionComponent", "FollowSystem", key);
			return;
		}
		try {
			sc = GC.speedComponent.get(key);
		} catch(Exception ex) {
			MyLogger.log(MyLogger.AI, MyLogger.NO_KEY, MyLogger.WARNING, "SpeedComponent", "FollowSystem", key);
			return;
		}
		try {
			opc = GC.positionComponent.get(fc.follow);
		} catch(Exception ex) {
			MyLogger.log(MyLogger.AI, MyLogger.NO_KEY, MyLogger.WARNING, "ObjectivePositionComponent", "FollowSystem", key);
			return;
		}
		
		
		float[] np = stepTo(pc.centerx, pc.centery, opc.centerx, opc.centery, sc.speed, delta);
		
		pc.x += np[0];
		pc.y += np[1];
		pc.centerx += np[0];
		pc.centery += np[1];
		pc.facing = np[2];
		
	}
	
	public float[] stepTo(float x, float y, float ox, float oy, float speed, int delta) {
		float difx = ox - x;
		float dify = oy - y;
		float facing = (float) Math.toDegrees(Math.atan2(dify, difx));
		facing += 90.0f;
		
		float rad = (float) Math.toRadians(facing);
		float movx = (float) (speed * delta/Play.MS_PER_SECOND * Math.sin(rad));
		float movy = (float) (-speed * delta/Play.MS_PER_SECOND * Math.cos(rad));
		
		float[] result = {movx, movy, facing};
		return result;
	}
	
	public void searchPlayer(Long key) { // TODO: Random entre players
		FollowComponent fc = null;
		try {
			fc = GC.followComponent.get(key);
		} catch(Exception ex) {
			MyLogger.log(MyLogger.AI, MyLogger.NO_KEY, MyLogger.WARNING, "FollowComponent", "FollowSystem", key);
			return;
		}
		for (Long key2 : GC.collisionComponent.keySet()) {
			if (GC.collisionComponent.get(key2).entityType == CollisionComponent.PLAYER) {
				fc.follow = key2;
				return;
			}
		}
		fc.follow = (long) -1;
	}
}
