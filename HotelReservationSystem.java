import java.util.*;

class Room {
    int id;
    String type;
    double price;
    boolean isBooked;

    Room(int id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }
}

class Reservation {
    int reservationId;
    String customerName;
    Room room;
    Date date;

    Reservation(int reservationId, String customerName, Room room, Date date) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.room = room;
        this.date = date;
    }
}

public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int reservationCounter = 1;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Preload some rooms
        rooms.add(new Room(1, "Standard", 1500));
        rooms.add(new Room(2, "Deluxe", 2500));
        rooms.add(new Room(3, "Suite", 4000));
        rooms.add(new Room(4, "Standard", 1500));
        rooms.add(new Room(5, "Deluxe", 2500));

        int choice;
        do {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> showAvailableRooms();
                case 2 -> bookRoom();
                case 3 -> cancelBooking();
                case 4 -> viewAllBookings();
                case 5 -> System.out.println("Thank you!");
                default -> System.out.println("Invalid choice");
            }
        } while (choice != 5);
    }

    private static void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            if (!r.isBooked) {
                System.out.println("Room ID: " + r.id + ", Type: " + r.type + ", Price: " + r.price);
            }
        }
    }

    private static void bookRoom() {
        showAvailableRooms();
        System.out.print("Enter Room ID to book: ");
        int roomId = sc.nextInt();
        sc.nextLine();
        Room room = null;
        for (Room r : rooms) {
            if (r.id == roomId && !r.isBooked) {
                room = r;
                break;
            }
        }

        if (room == null) {
            System.out.println("Room not available!");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();
        room.isBooked = true;
        Reservation res = new Reservation(reservationCounter++, name, room, new Date());
        reservations.add(res);
        System.out.println("Booking successful! Reservation ID: " + res.reservationId);
    }

    private static void cancelBooking() {
        System.out.print("Enter Reservation ID to cancel: ");
        int id = sc.nextInt();
        sc.nextLine();

        Reservation found = null;
        for (Reservation r : reservations) {
            if (r.reservationId == id) {
                found = r;
                break;
            }
        }

        if (found != null) {
            found.room.isBooked = false;
            reservations.remove(found);
            System.out.println("Booking cancelled successfully!");
        } else {
            System.out.println("Reservation not found!");
        }
    }

    private static void viewAllBookings() {
        if (reservations.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            System.out.println("\nAll Bookings:");
            for (Reservation r : reservations) {
                System.out.println("Reservation ID: " + r.reservationId +
                        ", Customer: " + r.customerName +
                        ", Room Type: " + r.room.type +
                        ", Price: " + r.room.price +
                        ", Date: " + r.date);
            }
        }
    }
}
