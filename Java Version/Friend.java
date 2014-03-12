
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
         if(hint.equals("")) {
             return this.name + " says: sorry I have got no hints for you.";
         }
         
         return this.name + " says: hello I have got a hint for you:\n" + getHint();
     }
 }
    