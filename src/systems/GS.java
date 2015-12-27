package systems;

import java.util.HashMap;

public class GS {
	public static RenderSystem RENDER_SYSTEM;
	public static HashMap<Long, RenderSystem> renderSystem;
	public static KeyboardInputSystem KEYBOARD_INPUT_SYSTEM;
	public static HashMap<Long, KeyboardInputSystem> keyboardInputSystem;
	public static MouseDirectionSystem MOUSE_DIRECTION_SYSTEM;
	public static HashMap<Long, MouseDirectionSystem> mouseDirectionSystem;
	public static FixedMovingSystem FIXED_MOVING_SYSTEM;
	public static HashMap<Long, FixedMovingSystem> fixedMovingSystem;
	public static ShoterSystem SHOTER_SYSTEM;
	public static HashMap<Long, ShoterSystem> shoterSystem;
	public static AnimationSystem ANIMATION_SYSTEM;
	public static HashMap<Long, AnimationSystem> animationSystem;
	public static CollisionSystem COLLISION_SYSTEM;
	public static HashMap<Long, CollisionSystem> collisionSystem;
	public static FollowSystem FOLLOW_SYSTEM;
	public static HashMap<Long, FollowSystem> followSystem;
	
	public static void init() {
		renderSystem = new HashMap<Long, RenderSystem>();
		RENDER_SYSTEM = new RenderSystem();
		keyboardInputSystem = new HashMap<Long, KeyboardInputSystem>();
		KEYBOARD_INPUT_SYSTEM = new KeyboardInputSystem();
		mouseDirectionSystem = new HashMap<Long, MouseDirectionSystem>();
		MOUSE_DIRECTION_SYSTEM = new MouseDirectionSystem();
		fixedMovingSystem = new HashMap<Long, FixedMovingSystem>();
		FIXED_MOVING_SYSTEM = new FixedMovingSystem();
		shoterSystem = new HashMap<Long, ShoterSystem>();
		SHOTER_SYSTEM = new ShoterSystem();
		animationSystem = new HashMap<Long, AnimationSystem>();
		ANIMATION_SYSTEM = new AnimationSystem();
		collisionSystem = new HashMap<Long, CollisionSystem>();
		COLLISION_SYSTEM = new CollisionSystem();
		followSystem = new HashMap<Long, FollowSystem>();
		FOLLOW_SYSTEM = new FollowSystem();
	}
	
	public static void delete(Long key) {
		if (renderSystem.containsKey(key)) {
			renderSystem.remove(key);
		}
		if (keyboardInputSystem.containsKey(key)) {
			keyboardInputSystem.remove(key);
		}
		if (mouseDirectionSystem.containsKey(key)) {
			mouseDirectionSystem.remove(key);
		}
		if (fixedMovingSystem.containsKey(key)) {
			fixedMovingSystem.remove(key);
		}
		if (shoterSystem.containsKey(key)) {
			shoterSystem.remove(key);
		}
		if (animationSystem.containsKey(key)) {
			animationSystem.remove(key);
		}
		if (collisionSystem.containsKey(key)) {
			collisionSystem.remove(key);
		}
		if (followSystem.containsKey(key)) {
			followSystem.remove(key);
		}
	}
	
	private GS() {}
}
