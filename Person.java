public class Person{
    public String fName;
    public String lName;
    public Boolean equals(Person x){
        return (x.fName == fName && x.lName == lName);
    }
}