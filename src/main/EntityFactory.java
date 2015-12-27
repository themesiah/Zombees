package main;

import entity.Entity;

// TODO: Que las ID den la vuelta para que no se terminen!
public class EntityFactory {
	private static EntityFactory instance = null;
	private static long newId;
	
	private EntityFactory() {
		newId = 0;
	}
	
	public static EntityFactory get() {
		if (instance == null) {
			instance = new EntityFactory();
		}
		return instance;
	}
	
	public Entity newEntity() {
		return new Entity(newId++);
	}
}
