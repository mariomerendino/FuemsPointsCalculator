package Backend;

public class Person{
    public String fName;
    public String lName;

    
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Person.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Person other = (Person) obj;
        if ((this.fName == null) ? (other.fName != null) : !this.fName.equals(other.fName)) {
            return false;
        }
        if ((this.lName == null) ? (other.lName != null) : !this.lName.equals(other.lName)) {
            return false;
        }
        return true;
    }
    //Hashing functions, used for Hashmap. 
    public int hashCode(){
        int x = fName.hashCode();
        int y = lName.hashCode();
        return 31 * y + x;
    }
}