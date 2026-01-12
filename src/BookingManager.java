
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void removeBooking(int index) {
        bookings.remove(index);
    }


    public List<Booking> getAllBookings(User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new SecurityException("Behörighet nekad");
        }
        return bookings;
    }
    public List<String> getAvailableDates() {
        List<String> allDates = new ArrayList<>();
        allDates.add("2026/01/15");
        allDates.add("2026/01/20");
        allDates.add("2026/01/22");
        allDates.add("2026/01/25");
        allDates.add("2026/01/28");
        allDates.add("2026/02/01");


        List<String> bookedDates = new ArrayList<>();// Samla alla datum som redan är bokade
        for (Booking b : bookings) {
            bookedDates.add(b.getDate());
        }

        List<String> availableDates = new ArrayList<>(); // Ta bort bokade datum
        for (String d : allDates) {
            if (!bookedDates.contains(d)) {
                availableDates.add(d);
            }
        }

        return availableDates;
    }

    public boolean authenticateAdmin(String password) {
        return "admin321".equals(password);
    }

    public boolean isDateAvailable(String date) {
        return getAvailableDates().contains(date);
    }

    public void upDateBooking(int index, String newDate) {
        Booking old = bookings.get(index);
        Booking updated = new Booking(
                old.getUser(),
                old.getSpaService(),
                newDate
        );
        bookings.set(index, updated);
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

}
