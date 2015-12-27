package components;

public class HealthComponent {
	public int maxHealth;
	public int health;
	
	public HealthComponent() {
		health = 1;
		maxHealth = 1;
	}
	
	public HealthComponent(int health) {
		this.health = health;
		this.maxHealth = health;
	}
}
