
 public class Friend extends Character implements java.io.Serializable
 {
     private String hint;
          
     public Friend (String name, int strength, int health, String hint)
     {
         super(name, strength, health);
         this.hint = hint;
     }
   
     public String getHint()
     {
         return hint;
     } 
     
     public String response()
     {
         if (hint.equals("")) {
             return this.name + "says: Sorry I got no hint for you:";
         }
         return this.name + "says: Hello I got hint for you:\n";
     }
 }
    