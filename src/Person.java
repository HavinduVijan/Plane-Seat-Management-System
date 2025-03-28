public class Person {
    // Private Attributes
    private String name;
    private String surname;
    private String email;

    // Constructor to initialize the object
    public Person(String name,String surname,String email){
        this.name=name;
        this.surname=surname;
        this.email=email;
    }
    public String getName(){return name;} // Getter
    public void setName(String name){
        this.name=name;
    } // Setter
    public String getSurname(){
        return surname;
    } // Getter
    public void setSurname(String surname){
        this.surname=surname;
    } // Setter
    public String getEmail(){
        return email;
    } // Getter
    public void setEmail(String email){this.email=email;} // Setter
    public void p_info(){
        System.out.println("Name: "+name);
        System.out.println("Surname: "+surname);
        System.out.println("Email: "+email);
    }
}
