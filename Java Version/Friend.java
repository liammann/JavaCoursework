
 public class Friend extends Character
 {
     private String hint;
     
     public Friend (String name, int strength, int health, String hint)
     {
         super(name, strength, health);
         this.hint = hint;
     }
     
     public String getName()
     {
         return name;
     }
     
     public int strength()
     {
         return strength;
     }
     
     public int health()
     {
         return health;
     }
     
     public String hint()
     {
         return hint;
     }
     
     
     
 }
    