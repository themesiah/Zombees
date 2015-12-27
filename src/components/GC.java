package components;

import java.util.HashMap;

public class GC {
	public static HashMap<Long, PositionComponent> positionComponent;
	public static HashMap<Long, SpriteComponent> spriteComponent;
	public static HashMap<Long, SpeedComponent> speedComponent;
	public static HashMap<Long, ZIndexComponent> zIndexComponent;
	public static HashMap<Long, ShoterComponent> shoterComponent;
	public static HashMap<Long, AnimationComponent> animationComponent;
	public static HashMap<Long, CollisionComponent> collisionComponent;
	public static HashMap<Long, FollowComponent> followComponent;
	public static HashMap<Long, StateComponent> stateComponent;
	public static HashMap<Long, HealthComponent> healthComponent;
	public static HashMap<Long, DamageComponent> damageComponent;
	
	public static void init() {
		positionComponent = new HashMap<Long, PositionComponent>();
		spriteComponent = new HashMap<Long, SpriteComponent>();
		speedComponent = new HashMap<Long, SpeedComponent>();
		zIndexComponent = new HashMap<Long, ZIndexComponent>();
		shoterComponent = new HashMap<Long, ShoterComponent>();
		animationComponent = new HashMap<Long, AnimationComponent>();
		collisionComponent = new HashMap<Long, CollisionComponent>();
		followComponent = new HashMap<Long, FollowComponent>();
		stateComponent = new HashMap<Long, StateComponent>();
		healthComponent = new HashMap<Long, HealthComponent>();
		damageComponent = new HashMap<Long, DamageComponent>();
	}
	
	public static void delete(Long key) {
		if (positionComponent.containsKey(key)) {
			positionComponent.remove(key);
		}
		if (spriteComponent.containsKey(key)) {
			spriteComponent.remove(key);
		}
		if (speedComponent.containsKey(key)) {
			speedComponent.remove(key);
		}
		if (zIndexComponent.containsKey(key)) {
			zIndexComponent.remove(key);
		}
		if (shoterComponent.containsKey(key)) {
			shoterComponent.remove(key);
		}
		if (animationComponent.containsKey(key)) {
			animationComponent.remove(key);
		}
		if (collisionComponent.containsKey(key)) {
			collisionComponent.remove(key);
		}
		if (followComponent.containsKey(key)) {
			followComponent.remove(key);
		}
		if (stateComponent.containsKey(key)) {
			stateComponent.remove(key);
		}
		if (healthComponent.containsKey(key)) {
			healthComponent.remove(key);
		}
		if (damageComponent.containsKey(key)) {
			damageComponent.remove(key);
		}
	}
	
	private GC() {}

}
