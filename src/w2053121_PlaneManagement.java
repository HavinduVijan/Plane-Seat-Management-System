import java.util.InputMismatchException;
import java.util.Scanner;
// Define the public class
public class w2053121_PlaneManagement {

    //create 2D array for seating plan
    private static int[][] seatPlan = {
            new int[14],
            new int[12],
            new int[12],
            new int[14],

    };
    //counter for track number of sold tickets
    private static int sold_tickets = 0;

    //create ticket array for store tickets
    private static Ticket[] ticket_array = new Ticket[52];

    //create method to convert String row to rowIndex for seatPlan
    private static int rowIndex(String row) {
        switch (row) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            default:
                return -1;
        }
    }
    //Main method
    public static void main(String[] args) {
        // Scanner object for read inputs
        Scanner read = new Scanner(System.in);
        System.out.println("\n Welcome to the Plane Management Application");
        int opt;
        // Using do-while for loop the menu options
        do {
            System.out.println("\n**********************************************");
            System.out.println("*                MENU OPTIONS                *");
            System.out.println("**********************************************");
            System.out.println("\n1) Buy A Seat");
            System.out.println("2) Cancel A Seat");
            System.out.println("3) Find First Available Seat");
            System.out.println("4) Show Seating Plan");
            System.out.println("5) Print Tickets Information And Total Sales");
            System.out.println("6) Search Ticket");
            System.out.println("0) Quit\n");
            System.out.println("**********************************************");
            while (true) {
                try {
                    System.out.print("Please Select An Option:");
                    opt = read.nextInt();
                    read.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("\nPlease Enter Integers Only..\n");
                    read.nextLine();
                    continue;
                }
                // Check if the input in correct range
                if (opt < 0 || opt > 6) {
                    System.out.println("\nEnter Integer In 1-6 Range..\n");
                } else {
                    break;
                }
            }
            // Calling for Methods
            switch (opt) {
                case 1:
                    buy_seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    String available_seat = find_first_available();
                    System.out.println(available_seat);
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    print_tickets_info();
                    break;
                case 6:
                    search_ticket();
                    break;
                case 0:
                    System.exit(0);
            }
        } while (opt != 0); // Loop until opt==0
    }
    //Method for buy seat
    public static void buy_seat() {
        while (true) {
            Scanner read = new Scanner(System.in);
            System.out.print("\nEnter Row Letter (A-D): ");
            String row = read.nextLine().toUpperCase();
            //Convert the String row to rowIndex
            int rowIndex = rowIndex(row);
            //Check the input in given range
            if (rowIndex < 0 || rowIndex > 4) {
                System.out.println("\nPlease Enter a Letter in (A-D)..");
                continue;
            }

            int seat_Number = 0;
            while (true) {
                // Print ticket prices for better understanding
                System.out.println("\n*****************");
                System.out.println("Seat 1-5   = £200\nSeat 6-9   = £150\nSeat 10-14 = £180");
                System.out.println("*****************");
                try {
                    // Display the different massages according to the input RowIndex
                    if (rowIndex == 0 || rowIndex == 3) {
                        System.out.print("\nEnter Seat Number (1-14): ");
                    } else if (rowIndex == 1 || rowIndex == 2) {
                        System.out.print("\nEnter Seat Number (1-12): ");
                    }
                    seat_Number = read.nextInt();
                    read.nextLine();

                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid Input !\nPlease Enter A Valid Number..");
                    read.nextLine();
                    continue;
                }
                // Check the input is valid or not
                if ((rowIndex == 0 || rowIndex == 3) && (seat_Number < 1 || seat_Number > 14)) {
                    System.out.println("\nInvalid Input !\nOut Of Range..");
                    continue;
                } else if ((rowIndex == 1 || rowIndex == 2) && (seat_Number < 1 || seat_Number > 12)) {
                    System.out.println("\nInvalid Input !\nOut Of Range..");
                    continue;
                }
                break;
            }

            String p_name;
            do {
                System.out.println("\nEnter Passenger Name: ");
                p_name = read.nextLine();
            }while (p_name.isEmpty()); //This loop continues for user enter non-empty value for name

            String p_surname;
            do {
                System.out.println("\nEnter Passenger Surname: ");
                p_surname = read.nextLine();
            }while (p_surname.isEmpty()); //This loop continues for user enter non-empty value for surname

            String p_email;
            do {
                System.out.println("\nEnter Passenger Email: ");
                p_email = read.nextLine();
            }while (p_email.isEmpty()); //This loop continues for user enter non-empty value for email

            // create the person object and initialize
            Person person_class = new Person(p_name, p_surname, p_email);

            // Set prices of each seat range
            double ticket_price;
            if (seat_Number >= 1 && seat_Number <= 5) {
                ticket_price = 200;
            } else if (seat_Number >= 6 && seat_Number <= 9) {
                ticket_price = 150;
            } else if (seat_Number >= 10 && seat_Number <= 14) {
                ticket_price = 180;
            } else {
                return;
            }

            // create ticket object and initialize
            Ticket ticket_class = new Ticket(row, seat_Number, ticket_price, person_class);

            // Update the seatPlan and ticket array
            seatPlan[rowIndex][seat_Number - 1] = 1;
            ticket_array[sold_tickets] = ticket_class;
            sold_tickets++;

            System.out.println("\nSeat Booking Successfully !");
            break;
        }
    }

    // Method for cancel seat
    public static void cancel_seat() {
        while (true) {
            Scanner read = new Scanner(System.in);
            System.out.print("\nEnter Row Letter (A-D): ");
            String row = read.nextLine().toUpperCase();
            int rowIndex = rowIndex(row); // Convert String row to rowIndex by method
            if (rowIndex < 0 || rowIndex > 4) {
                System.out.println("\nPlease Enter a Letter in (A-D)..");
                continue;
            }

            int seat_Number = 0;
            while (true) {
                try {
                    if (rowIndex == 0 || rowIndex == 3) {
                        System.out.print("\nEnter Seat Number (1-14): ");
                    } else if (rowIndex == 1 || rowIndex == 2) {
                        System.out.print("\nEnter Seat Number (1-12): ");
                    }
                    seat_Number = read.nextInt();
                // Handle the case for non-numerical input
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid Input !\nPlease Enter A Valid Number..");
                    read.nextLine();// Clear the invalid input from scanner
                    continue;
                }
                // Display the massage for out of range user input
                if ((rowIndex == 0 || rowIndex == 3) && (seat_Number < 1 || seat_Number > 14)) {
                    System.out.println("\nInvalid Input !\nOut Of Range..");
                    continue;
                } else if ((rowIndex == 1 || rowIndex == 2) && (seat_Number < 1 || seat_Number > 12)) {
                    System.out.println("\nInvalid Input !\nOut Of Range..");
                    continue;
                }
                break;
            }

            // Update the seatPlan
            if (seatPlan[rowIndex][seat_Number - 1] == 0) {
                System.out.println("\n The Seat Is Already Available !");
                return;
            }
            seatPlan[rowIndex][seat_Number - 1] = 0;
            System.out.println("\nSeat " + row + seat_Number + " Cancelled Successfully !");

            // Update the ticket array
            for (int i = 0; i < ticket_array.length; i++) {
                if (ticket_array[i] != null && ticket_array[i].getRow().equals(row) && ticket_array[i].getSeat() == seat_Number) {
                    ticket_array[i].delete_file(); // Delete the text file
                    ticket_array[i] = null;
                    break;
                }
            }
            break;
        }
    }
    // Method for find first seat
    public static String find_first_available() {
        for (int i = 0; i < seatPlan.length; i++) {
            for (int j = 0; j < seatPlan[i].length; j++) {
                if (seatPlan[i][j] == 0) {
                    return "\nSeat " + (char) ('A' + i) + (j + 1) + " Available.";
                }
            }
        }
        return "\nNo Available Seats For Booking !";
    }
    // Method for show seating plan
    public static void show_seating_plan() {
        System.out.println();
        String[] rowLetter={"A","B","C","D"};
        System.out.println("     1   2   3   4   5   6   7     8   9  10  11  12  13  14\n");
        for (int i = 0; i < seatPlan.length; i++) {
            System.out.print(rowLetter[i]+"  ");
            for (int j = 0; j < seatPlan[i].length; j++) {
                if (seatPlan[i][j] == 0) {
                    System.out.print("  O "); // Available seats
                } else {
                    System.out.print("  X "); // Booked seats
                }
                if (j==6){
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
    // Method for print tickets info
    public static void print_tickets_info() {
        double total = 0.0;
        // Check the number of sold tickets <1
        if (sold_tickets < 1) {
            System.out.println("\nNo Tickets Sold Yet !");
            return;
        }
        for (int i = 0; i < ticket_array.length; i++) {
            if (ticket_array[i] != null) {
                // Get the ticket object
                Ticket ticket = ticket_array[i];
                System.out.println("\nTicket Information:");
                System.out.println();
                System.out.println("Name: " + ticket.getPerson_info().getName());
                System.out.println("Surname: " + ticket.getPerson_info().getSurname());
                System.out.println("Email: " + ticket.getPerson_info().getEmail());
                System.out.println("Seat: " + ticket.getRow() + " | Row: " + ticket.getSeat());
                System.out.println("Ticket Price: " + ticket.getPrice());
                System.out.println();
                total += ticket.getPrice(); // Calculate the total sales
            }
        }
        System.out.println("Total : £" + total);
    }
    // Method for search tickets
    public static void search_ticket() {
        while (true) {
            Scanner read = new Scanner(System.in);
            System.out.print("\nEnter Row Letter (A-D): ");
            String row = read.nextLine().toUpperCase();
            int rowIndex = rowIndex(row); // Convert String row to rowIndex by method
            if (rowIndex < 0 || rowIndex > 4) {
                System.out.println("\nPlease Enter a Letter in (A-D)..");
                continue;
            }

            int seat_Number = 0;
            while (true) {
                // Check the inputs are valid or not
                try {
                    if (rowIndex == 0 || rowIndex == 3) {
                        System.out.print("\nEnter Seat Number (1-14): ");
                    } else if (rowIndex == 1 || rowIndex == 2) {
                        System.out.print("\nEnter Seat Number (1-12): ");
                    }
                    seat_Number = read.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid Input !\nPlease Enter A Valid Number..");
                    read.nextLine();
                    continue;
                }
                if ((rowIndex == 0 || rowIndex == 3) && (seat_Number < 1 || seat_Number > 14)) {
                    System.out.println("\nInvalid Input !\nOut Of Range..");
                    continue;
                } else if ((rowIndex == 1 || rowIndex == 2) && (seat_Number < 1 || seat_Number > 12)) {
                    System.out.println("\nInvalid Input !\nOut Of Range..");
                    continue;
                }
                break;
            }
            // Check if searched ticket store in ticket array
            for (int i = 0; i < sold_tickets; i++) {
                if (ticket_array[i] != null && ticket_array[i].getRow().equals(row) && ticket_array[i].getSeat() == seat_Number) {
                    ticket_array[i].t_info();
                    return;
                }
            }
            System.out.println("\nThis Seat Is Already Available !");
            break;
        }
    }
}