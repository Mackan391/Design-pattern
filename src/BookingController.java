import SpaServices.SpaService;

import java.util.List;

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

    private void handleCustomerFlow() {
        String name = view.askForName();
        String date = view.askForDate();
        User customer = new User(name, Role.CUSTOMER);

        int serviceChoice = view.askForServiceChoice();
        SpaService service = createService(serviceChoice);

        Booking booking = new Booking(customer, service, date);
        manager.addBooking(booking);

        view.showMessage("Tack för din bokning!");
        view.showBooking(booking);
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
            view.showMessage("Nekad behörighet. Endast admin!");
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
