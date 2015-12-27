package systems;

import main.MyLogger;
import components.GC;
import components.PositionComponent;
import components.SpriteComponent;
import components.ZIndexComponent;

public class RenderSystem {
	public void draw(Long key, int delta) {
		PositionComponent pc = null;
		SpriteComponent sc = null;
		try {
			pc = GC.positionComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "PositionComponent", "RenderSystem", key);
			return;
		}
		try {
			sc = GC.spriteComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "SpriteComponent", "RenderSystem", key);
			return;
		}
		sc.img.setRotation(pc.facing);
		sc.img.draw(pc.x, pc.y);
	}
	
	public void getSize(Long key) {
		SpriteComponent sc = null;
		try {
			sc = GC.spriteComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "SpriteComponent", "RenderSystem", key);
			return;
		}
		sc.size[0] = sc.img.getWidth();
		sc.size[1] = sc.img.getHeight();
	}
	
	public void getCenter(Long key) {
		SpriteComponent sc = null;
		PositionComponent pc = null;
		try {
			sc = GC.spriteComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "SpriteComponent", "RenderSystem", key);
			return;
		}
		try {
			pc = GC.positionComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "PositionComponent", "RenderSystem", key);
			return;
		}
		sc.size[0] = sc.img.getWidth();
		sc.size[1] = sc.img.getHeight();
		pc.centerx = pc.x + sc.img.getWidth()/2;
		pc.centery = pc.y + sc.img.getHeight()/2;
	}
	
	public void scale(Long key, float scale) {
		SpriteComponent sc = null;
		try {
			sc = GC.spriteComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "SpriteComponent", "RenderSystem", key);
			return;
		}
		sc.img = sc.img.getScaledCopy(scale);
	}
	
	public int getZIndex(Long key) {
		ZIndexComponent zic = null;
		try {
			zic = GC.zIndexComponent.get(key);
		} catch (Exception e) {
			MyLogger.log(MyLogger.VIEW, MyLogger.NO_KEY, MyLogger.WARNING, "ZIndexComponent", "RenderSystem", key);
			return ZIndexComponent.LAST;
		}
		return zic.zindex;
	}
}
