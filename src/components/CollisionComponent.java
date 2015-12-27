package components;

import org.newdawn.slick.geom.*;

public class CollisionComponent {
	// Shape type
	public static final int CIRCLE = 0;
	public static final int RECTANGLE = 1;
	public static final int ELLIPSE = 2;
	// Objectives and entity type
	public static final int DOESNT_MATTER = 0;
	public static final int PLAYER = 1;
	public static final int ENEMY = 2;
	public static final int BULLET = 3;
	
	public Shape s;
	public int type;
	public Long source;
	public int objective;
	public int entityType;
	public float[] customSize;
	public float scale;
	
	public CollisionComponent() {
		s = null;
		this.type = RECTANGLE;
		source = (long) -1;
		objective = DOESNT_MATTER;
		entityType = DOESNT_MATTER;
		customSize = null;
		scale = 1f;
	}
	
	public CollisionComponent(int type, Long source, int objective, int entityType) {
		s = null;
		this.type = type;
		this.source = source;
		this.objective = objective;
		this.entityType = entityType;
		this.customSize = null;
		scale = 1f;
	}
	
}
