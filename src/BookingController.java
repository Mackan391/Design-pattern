import SpaServices.SpaService;

public class BookingController {
    private BookingManager manager;
    private BookingView view;

    public BookingController(BookingManager manager, BookingView view) {
        this.manager = manager;
        this.view = view;
    }

    public void startApplication() {
        boolean running = true;
        while (running) {
            int choice = view.showMenu();

            switch (choice) {
                case 1 -> handleCustomerFlow();
                case 2 -> handleAdminFlow();
                case 3 -> running = false;
                default -> view.showMessage("Invalid choice");
            }
        }
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
            for (Booking b: manager.getAllBookings(admin)) {
                view.showBooking(b);
            }
        } catch (SecurityException e) {
            view.showMessage("Access denied! Admin only.");
        }
    }

    public void updateBookingAsAdmin(
            User admin,
            String customerName,
            String oldDate,
            String newDate
    ) {
        try {
            boolean updated =
                    manager.adminUpdateBooking(admin, customerName, oldDate, newDate);

            if (updated) {
                view.showMessage("\nBooking date updated successfully!");
            } else {
                view.showMessage("No matching booking found to update.");
            }
        } catch (SecurityException e) {
            view.showMessage("Access denied! Admin only.");
        }
    }

}
