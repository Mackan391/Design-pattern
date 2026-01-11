
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class BookingManager {
    private static BookingManager instance;
    private List<Booking> bookings;

    private BookingManager() {
        bookings = new ArrayList<>();
    }

    // Här har vi singleton nedan
    public static BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    public List<Booking> getBookingsForUser(User user) {
        List<Booking> result = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.getUser().getName().equals(user.getName())) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Booking> getAllBookings(User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new SecurityException("Access denied");
        }
        return bookings;
    }

    //Sparar bokningen till fil
    public void saveBookingsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bookings.dat"))) {
            out.writeObject(bookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Används för att slippa få varningar vid körning - vi vet att filen är safe
    @SuppressWarnings("unchecked")
    public void loadBookingsFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("bookings.dat"))) {
            bookings = (List<Booking>) in.readObject();
        } catch (Exception e) {
            bookings = new ArrayList<>();
        }
    }

    public boolean adminUpdateBooking(
            User admin,
            String customerName,
            String oldDate,
            String newDate
    ) {
        if (admin.getRole() != Role.ADMIN) {
            throw new SecurityException("Access denied");
        }
        for (int i = 0; i < bookings.size(); i++) {
            Booking current = bookings.get(i);

            if (current.getUser().getName().equalsIgnoreCase(customerName)
                    && current.getDate().equalsIgnoreCase(oldDate)) {

                String finalDate;
                if (newDate != null && !newDate.isBlank()) {
                    finalDate = newDate;
                } else {
                    finalDate = current.getDate(); //ingen ändring
                }

                Booking updated = new Booking(
                        current.getUser(),
                        current.getSpaService(),
                        finalDate
                );

                bookings.set(i, updated);
                return true;
            }
        }
        return false;
    }
}
