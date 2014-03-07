

public class Enemy extends Character
{
    private MovableObject weapon;
    
    
	public Enemy(String name, int strength, int health, MovableObject weapon)
	{
	    super(name, strength, health);
	    this.weapon = weapon;
	    
	}
	public MovableObject getWeapon(){
	    return weapon;
	}
	public MovableObject setWeapon(){
	    return weapon;
	}
	
	

}