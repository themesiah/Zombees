package main;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import components.*;
import entity.*;
import systems.*;
import util.U;

public class Play extends BasicGameState {
	public static final int MS_PER_SECOND = 1000;
	//private static int lastKey;
	private static int delta2;
	private EntityFactory ef;
	
	private int enemyDelay = 5000;
	private int enemyTimer = 5000;

	public Play(int code) {
	}
	
	public void initPlayer() {
		try {
			ef = EntityFactory.get();
			Entity e = ef.newEntity();
			e.setDescription("El jugador");
			// EMPIEZA CREACION DE COMPONENTES
			PositionComponent pc = new PositionComponent(50, 50, 0);
			SpeedComponent spc = new SpeedComponent(200);
			ZIndexComponent zic = new ZIndexComponent(ZIndexComponent.PLAYER);
			ShoterComponent shc = new ShoterComponent(100);
			AnimationComponent ac = new AnimationComponent(U.getAniFromFile("data/animation/player_stand.ani"));
			CollisionComponent cc = new CollisionComponent(CollisionComponent.RECTANGLE, (long) -1, CollisionComponent.DOESNT_MATTER, CollisionComponent.PLAYER);
			StateComponent sc = new StateComponent(StateComponent.STATE_STAND);
			HealthComponent hc = new HealthComponent(10);
			// FIN DE CREACION DE COMPONENTES
			// EMPIEZA INSERCION DE COMPONENTES
			GC.positionComponent.put(e.getId(), pc);
			GC.animationComponent.put(e.getId(), ac);
			GC.speedComponent.put(e.getId(), spc);
			GC.zIndexComponent.put(e.getId(), zic);
			GC.shoterComponent.put(e.getId(), shc);
			GC.collisionComponent.put(e.getId(), cc);
			GC.stateComponent.put(e.getId(), sc);
			//GC.healthComponent.put(e.getId(), hc);
			// FINALIZA INSERCION DE COMPONENTES
			// EMPIEZA INICIALIZACION DE COMPONENTES
			GS.ANIMATION_SYSTEM.getSize(e.getId());
			GS.ANIMATION_SYSTEM.addAnimation(e.getId(), U.getAniFromFile("data/animation/player_stand.ani"), StateComponent.STATE_STAND);
			GS.ANIMATION_SYSTEM.addAnimation(e.getId(), U.getAniFromFile("data/animation/player_move.ani"), StateComponent.STATE_WALK);
			GS.ANIMATION_SYSTEM.scale(e.getId(), 0.3f);
			GS.ANIMATION_SYSTEM.getCenter(e.getId());
			// FINALIZA INICIALIZACION DE COMPONENTES
			// EMPIEZA INSERCION DE SISTEMAS
			GS.keyboardInputSystem.put(e.getId(), GS.KEYBOARD_INPUT_SYSTEM);
			GS.mouseDirectionSystem.put(e.getId(), GS.MOUSE_DIRECTION_SYSTEM);
			GS.shoterSystem.put(e.getId(), GS.SHOTER_SYSTEM);
			GS.animationSystem.put(e.getId(), GS.ANIMATION_SYSTEM);
			GS.collisionSystem.put(e.getId(), GS.COLLISION_SYSTEM);
			// FINALIZA INSERCION DE SISTEMAS
			GE.add(e);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void initBackground() {
		try {
			ef = EntityFactory.get();
			Entity e = ef.newEntity();
			PositionComponent pc = new PositionComponent();
			SpriteComponent sc = new SpriteComponent(new Image("data/background.jpg"));
			ZIndexComponent zic = new ZIndexComponent(ZIndexComponent.BACKGROUND);
			GC.positionComponent.put(e.getId(), pc);
			GC.spriteComponent.put(e.getId(), sc);
			GC.zIndexComponent.put(e.getId(), zic);
			GS.RENDER_SYSTEM.getSize(e.getId());
			GS.renderSystem.put(e.getId(), GS.RENDER_SYSTEM);
			GE.add(e);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void initEnemy() {
		try {
			float x = 0;
			float y = 0;
			int border = (int) (Math.random() * 3);
			switch(border) {
			case 0: // Arriba
				y = -156;
				x = (float) (Math.random() * Main.GAMEWIDTH);
				break;
			case 1: // Derecha
				x = Main.GAMEWIDTH;
				y = (float) (Math.random() * Main.GAMEHEIGHT);
				break;
			case 2: // Abajo
				y = Main.GAMEHEIGHT;
				x = (float) (Math.random() * Main.GAMEWIDTH);
				break;
			case 3: // Izquierda
				x = -156;
				y = (float) (Math.random() * Main.GAMEHEIGHT);
				break;
			}
			ef = EntityFactory.get();
			Entity e = ef.newEntity();
			e.setDescription("Enemigo");
			// EMPIEZA CREACION DE COMPONENTES
			PositionComponent pc = new PositionComponent(x, y, 0);
			SpeedComponent spc = new SpeedComponent(100);
			ZIndexComponent zic = new ZIndexComponent(ZIndexComponent.ENEMY);
			AnimationComponent ac = new AnimationComponent(U.getAniFromFile("data/animation/enemy_walk.ani"));
			CollisionComponent cc = new CollisionComponent(CollisionComponent.RECTANGLE, (long) -1, CollisionComponent.PLAYER, CollisionComponent.ENEMY);
			FollowComponent fc = new FollowComponent();
			DamageComponent dc = new DamageComponent(1);
			HealthComponent hc = new HealthComponent(25);
			StateComponent sc = new StateComponent(StateComponent.STATE_WALK);
			// FIN DE CREACION DE COMPONENTES
			// EMPIEZA INSERCION DE COMPONENTES
			GC.positionComponent.put(e.getId(), pc);
			GC.animationComponent.put(e.getId(), ac);
			GC.speedComponent.put(e.getId(), spc);
			GC.zIndexComponent.put(e.getId(), zic);
			GC.collisionComponent.put(e.getId(), cc);
			GC.followComponent.put(e.getId(), fc);
			GC.damageComponent.put(e.getId(), dc);
			GC.healthComponent.put(e.getId(), hc);
			GC.stateComponent.put(e.getId(), sc);
			
			GS.ANIMATION_SYSTEM.getSize(e.getId());
			GS.ANIMATION_SYSTEM.addAnimation(e.getId(), U.getAniFromFile("data/animation/enemy_dead.ani"), StateComponent.STATE_DEAD);
			GS.ANIMATION_SYSTEM.addAnimation(e.getId(), U.getAniFromFile("data/animation/enemy_walk.ani"), StateComponent.STATE_WALK);
			GS.ANIMATION_SYSTEM.scale(e.getId(), 1.0f);
			GS.ANIMATION_SYSTEM.getCenter(e.getId());
			GS.FOLLOW_SYSTEM.searchPlayer(e.getId());
			GS.COLLISION_SYSTEM.setCustomSize(e.getId(), 10, 10);
			// FINALIZA INSERCION DE COMPONENTES
			// EMPIEZA INSERCION DE SISTEMAS
			GS.animationSystem.put(e.getId(), GS.ANIMATION_SYSTEM);
			GS.collisionSystem.put(e.getId(), GS.COLLISION_SYSTEM);
			GS.followSystem.put(e.getId(), GS.FOLLOW_SYSTEM);
			// FINALIZA INSERCION DE SISTEMAS
			GE.add(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		GC.init();
		GS.init();
		GE.init();
		initPlayer();
		initBackground();
		delta2 = 0;
	}
	
	public void spawnEnemy(int delta) {
		enemyTimer += delta;
		if (enemyTimer >= enemyDelay) {
			enemyTimer = 0;
			initEnemy();
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		GE.prepareFrame();
		spawnEnemy(delta);
		
		delta2 = delta;
		for (Entity e : GE.entities) {
			if (GS.collisionSystem.containsKey(e.getId())) {
				GS.collisionSystem.get(e.getId()).detachAliveComponents(e.getId());
			}
			if (GS.keyboardInputSystem.containsKey(e.getId())) {
				GS.keyboardInputSystem.get(e.getId()).getInput(e.getId(), gc, delta);
			}
			if (GS.mouseDirectionSystem.containsKey(e.getId())) {
				GS.mouseDirectionSystem.get(e.getId()).getDirection(e.getId(), gc);
			}
			if (GS.fixedMovingSystem.containsKey(e.getId())) {
				GS.fixedMovingSystem.get(e.getId()).update(e.getId(), delta);
			}
			if (GS.shoterSystem.containsKey(e.getId())) {
				GS.shoterSystem.get(e.getId()).shot(e.getId(), gc, delta);
			}
			if (GS.collisionSystem.containsKey(e.getId())) {
				GS.collisionSystem.get(e.getId()).collides(e.getId());
			}
			if (GS.followSystem.containsKey(e.getId())) {
				GS.followSystem.get(e.getId()).update(e.getId(), delta);
			}
			if (GS.animationSystem.containsKey(e.getId())) {
				GS.animationSystem.get(e.getId()).changeAnimation(e.getId());
			}
		}

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		ArrayList<Entity> drawOrder = new ArrayList<Entity>();
		for (int i = 0; i < ZIndexComponent.LAST; i++) {
			for (Entity e : GE.entities) { // Esto para cada sistema
				if (GS.renderSystem.containsKey(e.getId())) {
					int zi = GS.renderSystem.get(e.getId()).getZIndex(e.getId());
					if (zi == i) {
						drawOrder.add(e);
					}
				}
				if (GS.animationSystem.containsKey(e.getId())) {
					int zi = GS.animationSystem.get(e.getId()).getZIndex(e.getId());
					if (zi == i) {
						drawOrder.add(e);
					}
				}
			}
		}
		for (Entity e : drawOrder) { // Esto para cada sistema
			if (GS.renderSystem.containsKey(e.getId())) {
				GS.renderSystem.get(e.getId()).draw(e.getId(), delta2);
			}
			if (GS.animationSystem.containsKey(e.getId())) {
				GS.animationSystem.get(e.getId()).draw(e.getId(), delta2);
			}
		}
	}

	public int getID() {
		return Main.PLAY;
	}
}
