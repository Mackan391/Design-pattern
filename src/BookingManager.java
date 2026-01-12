
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
            throw new SecurityException("Behörighet nekad");
        }
        return bookings;
    }
    public List<String> getAvailableDates() {
        // Definiera de fasta datum som går att boka
        List<String> allDates = new ArrayList<>();
        allDates.add("2026/01/15");
        allDates.add("2026/01/20");
        allDates.add("2026/01/22");
        allDates.add("2026/01/25");
        allDates.add("2026/01/28");
        allDates.add("2026/02/01");

        // Samla alla datum som redan är bokade
        List<String> bookedDates = new ArrayList<>();
        for (Booking b : bookings) {
            bookedDates.add(b.getDate());
        }

        // Ta bort bokade datum
        List<String> availableDates = new ArrayList<>();
        for (String d : allDates) {
            if (!bookedDates.contains(d)) {
                availableDates.add(d);
            }
        }

        return availableDates;
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
