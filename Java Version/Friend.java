public class Friend extends Character implements java.io.Serializable
{
    private String hint = null;
 
    public static Friend create(String name)
    {
        return new Friend(name);
    }
    
    private Friend(String name)
    {
        super(name);
    }

    public Friend withHint(String hint)
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