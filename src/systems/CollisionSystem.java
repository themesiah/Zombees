package systems;

import org.newdawn.slick.geom.*;

import components.*;
import entity.*;

public class CollisionSystem {
	public void generateShape(Long key) {
		SpriteComponent sc = null;
		AnimationComponent ac = null;
		PositionComponent pc = null;
		CollisionComponent cc = null;
		try {
			sc = GC.spriteComponent.get(key);
		} catch(Exception ex) {
			return;
		}
		try {
			pc = GC.positionComponent.get(key);
		} catch(Exception ex) {
			return;
		}
		try {
			cc = GC.collisionComponent.get(key);
		} catch(Exception ex) {
			return;
		}
		try {
			ac = GC.animationComponent.get(key);
		} catch(Exception ex) {
			return;
		}
		float[] size = new float[2];
		if (cc.customSize == null) {
			if (ac != null) {
				size = ac.size;
			} else if (sc != null) {
				size = sc.size;
			} else {
				size[0] = 0;
				size[1] = 0;
			}
		} else {
			size = cc.customSize;
		}
		switch(cc.type) {
			case CollisionComponent.CIRCLE:
				cc.s = new Circle(pc.centerx, pc.centery, size[0]*cc.scale);
				break;
			case CollisionComponent.RECTANGLE:
				cc.s = new Rectangle(pc.centerx, pc.centery, size[0]*cc.scale, size[1]*cc.scale);
				cc.s.setCenterX(pc.centerx);
				cc.s.setCenterY(pc.centery);
				cc.s = cc.s.transform(Transform.createRotateTransform(pc.facing+90, pc.centerx, pc.centery));
				break;
			case CollisionComponent.ELLIPSE:
				cc.s = new Ellipse(pc.centerx, pc.centery, size[0]*cc.scale, size[1]*cc.scale);
				break;
		}
	}
	
	public void setCustomSize(Long key, float width, float height) {
		CollisionComponent cc = null;
		try {
			cc = GC.collisionComponent.get(key);
		} catch(Exception ex) {
			return;
		}
		
		cc.customSize = new float[2];
		cc.customSize[0] = width;
		cc.customSize[1] = height;
	}
	
	public boolean collides(Long key) {
		CollisionComponent cc = null;
		CollisionComponent occ = null;
		DamageComponent dc = null;
		HealthComponent hc = null;
		try {
			cc = GC.collisionComponent.get(key);
		} catch(Exception ex) {
			return false;
		}
		for (Entity e : GE.entities) {
			if (GC.collisionComponent.containsKey(e.getId()) && e.getId() != key) {
				occ = null;
				try {
					occ = GC.collisionComponent.get(e.getId());
				} catch(Exception ex) {}
				if (occ != null) {
					generateShape(e.getId());
					generateShape(key);
					if (occ.s != null && cc.s != null) {
						if (occ.s.contains(cc.s) || occ.s.intersects(cc.s) || cc.s.contains(occ.s)) {
							//if (occ.entityType == cc.objective || occ.objective == cc.entityType) {
							if (occ.entityType == cc.objective) {
								System.out.println("COLLISION!");
								try {
									dc = GC.damageComponent.get(key);
								} catch(Exception ex) {}
								try {
									hc = GC.healthComponent.get(e.getId());
								} catch(Exception ex) {}
								
								if (dc != null && hc != null) {
									doDamage(key, e.getId());
								}
								if (cc.entityType == CollisionComponent.BULLET) {
									GE.delete(key);
								}
								/*if (occ.entityType == CollisionComponent.BULLET) {
									GE.delete(e);
								}*/
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public void doDamage(Long source, Long damaged) {
		DamageComponent dc = null;
		HealthComponent hc = null;
		StateComponent sc = null;
		try {
			dc = GC.damageComponent.get(source);
		} catch(Exception ex) {
			return;
		}
		try {
			hc = GC.healthComponent.get(damaged);
		} catch(Exception ex) {
			return;
		}
		try {
			sc = GC.stateComponent.get(damaged);
		} catch(Exception ex) {
			return;
		}
		
		hc.health -= dc.damage;
		if (hc.health <= 0) {
			sc.state = StateComponent.STATE_DEAD;
			//detachAliveComponents(damaged);
		}
	}
	
	public void detachAliveComponents(Long key) {
		StateComponent sc = null;
		try {
			sc = GC.stateComponent.get(key);
		} catch(Exception ex) {
			return;
		}
		if (sc != null) {
			if (sc.state == StateComponent.STATE_DEAD) {
				if (GC.shoterComponent.containsKey(key)) {
					GC.shoterComponent.remove(key);
				}
				if (GC.collisionComponent.containsKey(key)) {
					GC.collisionComponent.remove(key);
				}
				if (GC.followComponent.containsKey(key)) {
					GC.followComponent.remove(key);
				}
				//////////////////////////////////
				if (GS.fixedMovingSystem.containsKey(key)) {
					GS.fixedMovingSystem.remove(key);
				}
				if (GS.shoterSystem.containsKey(key)) {
					GS.shoterSystem.remove(key);
				}
				if (GS.collisionSystem.containsKey(key)) {
					GS.collisionSystem.remove(key);
				}
				if (GS.followSystem.containsKey(key)) {
					GS.followSystem.remove(key);
				}
				if (GS.keyboardInputSystem.containsKey(key)) {
					GS.keyboardInputSystem.remove(key);
				}
				if (GS.mouseDirectionSystem.containsKey(key)) {
					GS.mouseDirectionSystem.remove(key);
				}
			}
		}
	}
}
