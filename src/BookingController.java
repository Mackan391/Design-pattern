import SpaServices.SpaService;

public class BookingController {
    private BookingManager manager;
    private BookingView view;

    public BookingController(BookingManager manager, BookingView view) {
        this.manager = manager;
        this.view = view;
    }

    public void createBooking(User user, SpaService service, String date){
        Booking booking = new Booking(user, service, date);
        manager.addBooking(booking);
        view.showMessage("Booking created successfully!");
    }
}
