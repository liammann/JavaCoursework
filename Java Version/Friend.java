public class Friend extends Character implements java.io.Serializable
{
    private String hint = null;
 
    public Friend(String name)
    {
        super(name);
    }
    
    @Override
    public Friend hasStrength(int strength)
    {
        this.strength = strength;

        return this;
    }
    
    @Override
    public Friend withHealth(int health)
    {
        this.health = health;
        
        return this;
    }
    
    public Friend andHasHint(String hint)
    {
        this.hint = hint;
        
        return this;
    }
  
    public String getHint()
    {
        return hint;
    } 
    
    public String response()
    {
        if(hint == null) {
            return this.name + " says: sorry I have got no hints for you.";
        }
        
        return this.name + " says: hello I have got a hint for you:\n" + getHint();
    }
 }