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

}
