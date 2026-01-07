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

    public void showUserBookings(User user) {
        for (Booking b: manager.getBookingsForUser(user)) {
            view.showBooking(b);
        }
    }

    public void showAllBookings(User admin) {
        try {
            for (Booking b: manager.getBookingsForUser(admin)) {
                view.showBooking(b);
            }
        } catch (SecurityException e) {
            view.showMessage("Access denied! Admin only.");
        }
    }
}
