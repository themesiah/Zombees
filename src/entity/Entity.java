package entity;

public class Entity {
	private long id;
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Entity(long id) {
		this.id = id;
		this.description = "";
	}
	
	public long getId() {
		return id;
	}
}
