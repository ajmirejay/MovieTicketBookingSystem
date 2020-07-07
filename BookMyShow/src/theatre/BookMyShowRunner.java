package theatre;

import java.util.Scanner;

public class BookMyShowRunner {
    public static void main (String[] args)
    {
        BookMyShowFunctions book = new BookMyShowFunctions();
        book.initialiseTheatre();
        book.showTheatre();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to theatre ");
        int choice;
        do {
            System.out.println("1.Book tickets \n2.Show ticket Status \n3.Cancel ticket\n4.Exit ");
            System.out.println("Enter your choice ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if(book.takeUserDataAndTicketDetails())
                        book.bookMyTicket();
                    break;
                case 2:
                    book.showTheatre();
                    break;
                case 3:
                    book.cancelMyShow();
                    break;
                case 4:
                    break;

            }
        }while(choice != 4);
    }
}
