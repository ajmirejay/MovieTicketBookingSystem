package theatre;
import java.util.ArrayList;
import java.util.Scanner;

public class BookMyShowFunctions {
    private NodeForSeats[] head = new NodeForSeats[12];
    Scanner scanner = new Scanner(System.in);
    int noOfTickets;
    int rowNumber;
    ArrayList<Integer> bookedSeatNumbers = new ArrayList<>();
    ArrayList<Integer> cancelSeatNumberArray = new ArrayList<>();
    int maxSeatsInRow = 5;
    ArrayList<Integer> totalTicketsBookedInRow =  new ArrayList<>(11);
    protected void initialiseTheatre() {
        for (int i = 1; i < 12; i++) {
            for (int j = 1; j <= 6; j++) {
                NodeForSeats newSeat = new NodeForSeats();
                newSeat.status = "nil";
                newSeat.seatNumber = j;
                newSeat.next = null;
                NodeForSeats temp = head[i];
                if (temp == null) {
                    head[i] = newSeat;
                } else {

                    while (temp.next != null) {
                        temp = temp.next;
                    }
                    temp.next = newSeat;
                }
            }
        }
        for (int i = 0; i < 11; i++)
        {
            totalTicketsBookedInRow.add(0);
        }
    }

    protected void showTheatre()
    {
        for(int i = 1; i <= 11; i++)
        {
            System.out.print("Row->"+i+" ");
            NodeForSeats temp = head[i];
            while(temp.next != null)
            {
                System.out.print(temp.seatNumber+" "+temp.status+"--> ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    protected boolean takeUserDataAndTicketDetails()
    {
        bookedSeatNumbers.clear();
        System.out.println("Enter number of tickets ");
        noOfTickets = scanner.nextInt();
        if(noOfTickets > 5)
        {
            System.out.println("You can book only 5 tickets at a time ");
            return false;
        }
        System.out.println("Enter the row number ");
        rowNumber = scanner.nextInt();
        for(int i = 0; i < noOfTickets; i++)
        {
            System.out.println("Enter the seat number ");
            int addInArray = scanner.nextInt();
            bookedSeatNumbers.add(addInArray);
        }
        return true;
    }
    protected void bookMyTicket()
    {
        int flagForCalculatingMoney = 0;
        NodeForSeats temp = head[rowNumber];
        if(bookedSeatNumbers.size() < totalTicketsBookedInRow.get(rowNumber)){
            System.out.println("Not enough seats are available in this row ");
            flagForCalculatingMoney = 1;
        }else{
            int i = 0;
            while(temp.next != null)
            {
                if(temp.seatNumber != bookedSeatNumbers.get(i))
                {
                    temp = temp.next;
                }else
                {
                    if(temp.flag == 0)
                    {
                        temp.flag = 1;
                        temp.status = "Booked";
                        temp = head[rowNumber];
                        int incrementing = 1 + totalTicketsBookedInRow.get(rowNumber);
                        totalTicketsBookedInRow.add(rowNumber , incrementing);
                        i++;
                        if(i > maxSeatsInRow)
                        {
                            break;
                        }
                        if(i == bookedSeatNumbers.size()){
                            break;
                        }
                    }else {
                        System.out.println("this seat number "+bookedSeatNumbers.get(i)+" is already booked ");
                        System.out.println("Please try again ");
                        flagForCalculatingMoney = 1;
                        break;
                    }
                }
            }
        }
        if(flagForCalculatingMoney == 0)
        {
            calculateTicketCost();
        }
    }

    protected void calculateTicketCost()
    {
        int cost = noOfTickets * 200;
        System.out.println("Please pay rupees "+cost+" for your "+ noOfTickets+" tickets. \n Thanks Visit Again ");
    }

    protected void cancelMyShow()
    {
        cancelSeatNumberArray.clear();
        int flagForRefundAmount = 0;
        System.out.println("Per ticket 50 rupees will be charged for Cancellation ");
        System.out.println("Enter the row number to cancel the ticket ");
        int cancelRowNumber = scanner.nextInt();
        if (cancelRowNumber > 10)
        {
            System.out.println("No such row exist ");
        }
        System.out.println("Enter the number of tickets to cancel ");
        int cancelNoOfTickets = scanner.nextInt();
        for (int j = 0; j < cancelNoOfTickets; j++)
        {
            System.out.println("Enter the seat Numbers to cancel the tickets ");
            cancelSeatNumberArray.add(scanner.nextInt());
        }
        NodeForSeats temp = head[cancelRowNumber];
        int i = 0;
        while (temp != null)
        {
            if(temp.seatNumber != cancelSeatNumberArray.get(i))
            {
                temp = temp.next;
            }else {
                if(temp.flag == 1)
                {
                    temp.flag = 0;
                    temp.status = "nil";
                    i++;
                    int decrementing = totalTicketsBookedInRow.get(cancelRowNumber) - 1;
                    totalTicketsBookedInRow.add(cancelRowNumber , decrementing);
                    temp = head[cancelRowNumber];
                }else {
                    System.out.println("This Seat "+cancelSeatNumberArray.get(i)+" is not booked so cannot be cancelled.");
                    flagForRefundAmount = 1;
                    break;
                }
                if(i == cancelSeatNumberArray.size())
                {
                    break;
                }
            }
        }
        if(flagForRefundAmount == 0)
        {
            int refundAmount = (cancelNoOfTickets * 200) - (cancelNoOfTickets * 50);
            System.out.println(refundAmount + " will be refunded after applying cancellation charges ");
        }
    }
}
