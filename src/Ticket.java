import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    // Private Attributes
    private String row;
    private int seat;
    private double price;
    private Person person_info;

    // Constructor to initialize the ticket object
    public Ticket(String row,int seat,double price,Person person_info){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person_info=person_info;
        save();
    }
    public String getRow(){return row;} // Getter
    public void setRow(String row){this.row=row;} // Setter
    public int getSeat() {return seat;} // Getter
    public void setSeat(int seat){
        this.seat=seat;
    } // Setter
    public double getPrice() {
        return price;
    } // Getter
    public void setPrice(double price){
        this.price=price;
    } // Setter
    public Person getPerson_info(){
        return person_info;
    } // Getter
    public void setPerson_info(Person person_info){
        this.person_info=person_info;
    } // Setter
    public void t_info(){
        System.out.println("\nTicket Information\n");
        System.out.println("Row: "+row);
        System.out.println("Seat: "+seat);
        System.out.println("Price: £"+price);
        System.out.println("\nPassenger Information\n");
        getPerson_info().p_info();
    }
    // Method for create text file
    public void save(){
        String fileName=row+seat+".txt";
        try {FileWriter file = new FileWriter(fileName);
            file.write("Ticket Information:\n\n");
            file.write("Row: "+row+"\n");
            file.write("Seat: "+seat+"\n");
            file.write("Price: "+price+"\n\n");
            file.write("Passenger Information:\n\n");
            file.write("Name: "+person_info.getName()+"\n");
            file.write("Surname: "+person_info.getSurname()+"\n");
            file.write("Email: "+person_info.getEmail()+"\n");
            file.close();
        } catch (IOException e){
            System.out.println("\nAn Error While Writing The File !");
            e.printStackTrace(); // Print stack case for debugging cases
        }
    }
    // Method for delete text file
    public void delete_file(){
        String fileName=row+seat+".txt";
        File file =new File(fileName);
        if (file.delete()){
            System.out.println("\nFile "+fileName+" Deleted Successfully !");
        }else {
            System.out.println("\nFile "+fileName+" Deleted Unsuccessfully !");
        }
    }
}
