import java.util.Scanner;

public class Main {

    static String[] roomTypes = {"Standard", "Deluxe", "Suite"};
    static int[] roomPrices = {2000, 4000, 6000};
    static boolean[] roomBooked = {false, false, false};

    static String customerName = "";
    static String bookedRoomType = "";
    static int bookedRoomPrice = 0;
    static boolean hasBooking = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n======================================");
            System.out.println("      HOTEL RESERVATION SYSTEM");
            System.out.println("======================================");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. View Booking");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;

                case 2:
                    bookRoom(sc);
                    break;

                case 3:
                    viewBooking();
                    break;

                case 4:
                    cancelBooking();
                    break;

                case 5:
                    System.out.println("Thank you for using Hotel Reservation System!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }

    public static void viewRooms() {
        System.out.println("\n========== AVAILABLE ROOMS ==========");

        for (int i = 0; i < roomTypes.length; i++) {
            System.out.println((i + 1) + ". " + roomTypes[i] + " Room - Rs." + roomPrices[i]
                    + " - " + (roomBooked[i] ? "Booked" : "Available"));
        }
    }

    public static void bookRoom(Scanner sc) {

        if (hasBooking) {
            System.out.println("\nYou already have a booking.");
            return;
        }

        viewRooms();

        System.out.print("\nEnter your name: ");
        customerName = sc.nextLine();

        System.out.print("Select room number to book (1-3): ");
        int roomChoice = sc.nextInt();
        sc.nextLine();

        if (roomChoice < 1 || roomChoice > 3) {
            System.out.println("Invalid room choice!");
            return;
        }

        if (roomBooked[roomChoice - 1]) {
            System.out.println("Sorry! This room is already booked.");
            return;
        }

        bookedRoomType = roomTypes[roomChoice - 1];
        bookedRoomPrice = roomPrices[roomChoice - 1];

        System.out.println("\nPayment Amount: Rs." + bookedRoomPrice);
        System.out.print("Confirm payment? (yes/no): ");
        String payment = sc.nextLine();

        if (payment.equalsIgnoreCase("yes")) {
            roomBooked[roomChoice - 1] = true;
            hasBooking = true;
            System.out.println("\nRoom booked successfully!");
            System.out.println("Booking confirmed for " + customerName);
        } else {
            System.out.println("Payment cancelled. Booking not completed.");
        }
    }

    public static void viewBooking() {
        System.out.println("\n========== BOOKING DETAILS ==========");

        if (!hasBooking) {
            System.out.println("No booking found.");
            return;
        }

        System.out.println("Customer Name : " + customerName);
        System.out.println("Room Type     : " + bookedRoomType);
        System.out.println("Room Price    : Rs." + bookedRoomPrice);
        System.out.println("Booking Status: Confirmed");
    }

    public static void cancelBooking() {

        if (!hasBooking) {
            System.out.println("\nNo booking available to cancel.");
            return;
        }

        for (int i = 0; i < roomTypes.length; i++) {
            if (roomTypes[i].equals(bookedRoomType)) {
                roomBooked[i] = false;
                break;
            }
        }

        customerName = "";
        bookedRoomType = "";
        bookedRoomPrice = 0;
        hasBooking = false;

        System.out.println("\nBooking cancelled successfully!");
    }
}