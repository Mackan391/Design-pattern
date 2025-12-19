import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private static BookingManager instance;
    private List<Booking> bookings;

    private BookingManager(){
        bookings = new ArrayList<>();
    }
    // Här har vi singleton nedan
    public static BookingManager getInstance(){
        if(instance==null){
            instance = new BookingManager();
        }
        return instance;
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }
    public void removeBooking(Booking booking){
        bookings.remove(booking);
    }

    public List<Booking> getBookingsForUser(User user){
        List<Booking> result = new ArrayList<>();
        for (Booking b: bookings){
            if(b.getUser().getName().equals(user.getName())){
                result.add(b);
            }
        }
        return result;
    }

    public List<Booking> getBookingsForRole(User user){
        if (user.getRole() != Role.ADMIN){
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
    public void loadBookingsFromFile() {}
}
