package entity;

import java.util.ArrayList;

import systems.GS;
import components.GC;

public class GE {
	public static ArrayList<Entity> entities;
	public static ArrayList<Entity> toRemove;
	public static ArrayList<Entity> toAdd;
	
	public static void init() {
		entities = new ArrayList<Entity>();
		toAdd = new ArrayList<Entity>();
		toRemove = new ArrayList<Entity>();
	}
	
	public static void prepareFrame() {
		for (Entity e : toRemove) {
			try {
				entities.remove(e);
			} catch (Exception ex) {
				// Nada. Podría intentar borrarlo dos veces!
			}
		}
		for (Entity e : toAdd) {
			entities.add(e);
		}
		toRemove.clear();
		toAdd.clear();
	}
	
	public static void delete(Entity e) {
		GC.delete(e.getId());
		GS.delete(e.getId());
		toRemove.add(e);
	}
	
	public static void delete(Long id) {
		GC.delete(id);
		GS.delete(id);
		for (Entity e : entities) {
			if (e.getId() == id) {
				toRemove.add(e);
			}
		}
	}
	
	public static Entity getEntity(Long id) {
		for (Entity e : entities) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	public static void add(Entity e) {
		toAdd.add(e);
	}
}
