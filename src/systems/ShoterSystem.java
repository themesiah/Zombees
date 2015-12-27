package systems;

import main.EntityFactory;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import components.*;
import entity.Entity;
import entity.GE;

public class ShoterSystem {
	public static final int SHOT_BUTTON = Input.MOUSE_LEFT_BUTTON;
	
	public void shot(Long key, GameContainer gc, int delta) {
		ShoterComponent sc = null;
		try {
			sc = GC.shoterComponent.get(key);
		} catch(Exception ex) {
			return;
		}
		sc = GC.shoterComponent.get(key);
		if (sc.timer < sc.delay) {
			sc.timer += delta;
		} else {
			Input i = gc.getInput();
			if (i.isMouseButtonDown(SHOT_BUTTON)){
				generateBullet(key);
				sc.timer = 0;
			}
		}
	}
	
	private void generateBullet(Long key) {
		try {
			EntityFactory ef = EntityFactory.get();
			Entity e = ef.newEntity();
			e.setDescription("Bala con id: " + e.getId());
			
			PositionComponent sourcepc = GC.positionComponent.get(key);
			ShoterComponent sourceshot = GC.shoterComponent.get(key);
			
			PositionComponent pc = new PositionComponent(sourcepc.centerx, sourcepc.centery, sourcepc.facing);
			SpeedComponent sc = new SpeedComponent(500);
			SpriteComponent spc = new SpriteComponent(new Image(sourceshot.src));
			ZIndexComponent zic = new ZIndexComponent(ZIndexComponent.BULLET);
			CollisionComponent cc = new CollisionComponent(CollisionComponent.RECTANGLE, key, CollisionComponent.ENEMY, CollisionComponent.BULLET);
			DamageComponent dc = new DamageComponent(5);
			
			GC.positionComponent.put(e.getId(), pc);
			GC.speedComponent.put(e.getId(), sc);
			GC.spriteComponent.put(e.getId(), spc);
			GC.zIndexComponent.put(e.getId(), zic);
			GC.collisionComponent.put(e.getId(), cc);
			GC.damageComponent.put(e.getId(), dc);
			
			GS.RENDER_SYSTEM.getCenter(e.getId());
			GS.RENDER_SYSTEM.getSize(e.getId());
			GS.RENDER_SYSTEM.scale(e.getId(), 0.2f);
			
			GS.renderSystem.put(e.getId(), GS.RENDER_SYSTEM);
			GS.fixedMovingSystem.put(e.getId(), GS.FIXED_MOVING_SYSTEM);
			GS.collisionSystem.put(e.getId(), GS.COLLISION_SYSTEM);
			
			GE.add(e);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
