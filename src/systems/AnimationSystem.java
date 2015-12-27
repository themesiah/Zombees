package systems;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

import main.MyLogger;
import components.*;

public class AnimationSystem {
	public void draw(Long key, int delta) {
		PositionComponent pc = null;
		AnimationComponent ac = null;
		try {
			pc = GC.positionComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "PositionComponent", "AnimationSystem", key);
			return;
		}
		try {
			ac = GC.animationComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "AnimationComponent", "AnimationSystem", key);
			return;
		}
		if (ac.animation.isStopped()) {
			ac.animation.start();
		}
		for (int i = 0; i < ac.animation.getFrameCount(); i++) {
			ac.animation.getImage(i).setRotation(pc.facing);
		}
		ac.animation.draw(pc.x, pc.y);
	}
	
	public void getSize(Long key) {
		AnimationComponent ac = null;
		try {
			ac = GC.animationComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "AnimationComponent", "AnimationSystem", key);
			return;
		}
		ac.size[0] = ac.animation.getWidth();
		ac.size[1] = ac.animation.getHeight();
	}
	
	public void getCenter(Long key) {
		AnimationComponent ac = null;
		PositionComponent pc = null;
		try {
			ac = GC.animationComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "AnimationComponent", "AnimationSystem", key);
			return;
		}
		try {
			pc = GC.positionComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "PositionComponent", "AnimationSystem", key);
			return;
		}
		ac.size[0] = ac.animation.getWidth();
		ac.size[1] = ac.animation.getHeight();
		pc.centerx = pc.x + ac.animation.getWidth()/2;
		pc.centery = pc.y + ac.animation.getHeight()/2;
	}
	
	public void scale(Long key, float scale) {
		AnimationComponent ac = null;
		CollisionComponent cc = null;
		try {
			ac = GC.animationComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "AnimationComponent", "AnimationSystem", key);
			return;
		}
		try {
			cc = GC.collisionComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "CollisionComponent", "AnimationSystem", key);
		}
		if (cc != null) {
			cc.scale = scale;
		}
		ArrayList<Image> img = new ArrayList<Image>();
		for (Image i : ac.i) {
			img.add(i.getScaledCopy(scale));
		}
		Image[] newimg = img.toArray(new Image[img.size()]);
		ac.animation = new Animation(newimg, ac.d);
		for (Integer integ : ac.animations.keySet()) {
			img = new ArrayList<Image>();
			for (int index = 0; index < ac.animations.get(integ).getFrameCount(); index++) {
				img.add(ac.animations.get(integ).getImage(index).getScaledCopy(scale));
			}
			newimg = img.toArray(new Image[img.size()]);
			ac.animations.replace(integ, new Animation(newimg, ac.animations.get(integ).getDurations()));
		}
	}
	
	public int getZIndex(Long key) {
		ZIndexComponent zic = null;
		try {
			zic = GC.zIndexComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "ZIndexComponent", "AnimationSystem", key);
			return ZIndexComponent.LAST;
		}
		return zic.zindex;
	}
	
	public void addAnimation(Long key, Animation a, int state) {
		AnimationComponent ac = null;
		try {
			ac = GC.animationComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "AnimationComponent", "AnimationSystem", key);
			return;
		}
		try {
			GC.stateComponent.get(key); // Solo comprueba si existe, no lo necesita!
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "StateComponent", "AnimationSystem", key);
			return;
		}
		ac.animations.put(state, a);
	}
	
	public void changeAnimation(Long key) {
		AnimationComponent ac = null;
		StateComponent sc = null;
		try {
			ac = GC.animationComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "AnimationComponent", "AnimationSystem", key);
			return;
		}
		try {
			sc = GC.stateComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "StateComponent", "AnimationSystem", key);
			return;
		}
		if (sc != null) {
			ac.animation = ac.animations.get(sc.state);
		}
	}
}
