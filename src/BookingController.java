import SpaServices.BasicSpa;
import SpaServices.FacialTreatment;
import SpaServices.Massage;
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
                default -> view.showMessage("Ogiltligt val!");
            }
        }
    }

    private void handleCustomerFlow() {
        String name = view.askForName();
        User customer = new User(name, Role.CUSTOMER);

        // Visa lediga datum först
        List<String> availableDates = manager.getAvailableDates();
        if (availableDates.isEmpty()) {
            view.showMessage("Tyvärr, inga dagar lediga de närmaste 30 dagarna.");
            return; // avbryt om inga datum finns
        }

        view.showAvailableDates(); // skriver ut lediga datum
        String date = view.askForDate();

        // Låt användaren välja ett datum
        if (!availableDates.contains(date)) {
            view.showMessage("Ogiltigt. Datumet finns ej i listan");
            return;
        }

        // Låt användaren välja spa-tjänst
        int serviceChoice = view.askForServiceChoice();
        SpaService service = createService(serviceChoice);

        // Skapa bokningen
        Booking booking = new Booking(customer, service, date);
        manager.addBooking(booking);

        view.showMessage("Tack för din bokning!");
        view.showBooking(booking);
    }

    private SpaService createService(int choice) {
        SpaService service = new BasicSpa();

        if (choice == 2) {
            service = new Massage(service);
        } else if (choice == 3) {
            service = new FacialTreatment(service);
        } else if (choice == 4) {
            service = new Massage(service);
            service = new FacialTreatment(service);
        }
        return service;
    }

    private void handleAdminFlow() {
        String password = view.askForAdminPassword();
        if (!password.equals("admin321")) {
            view.showMessage("Behörighet nekad.");
            return;
        }
        User admin = new User("Admin", Role.ADMIN);
        List<Booking> bookings = manager.getAllBookings(admin);

        if (bookings.isEmpty()) {
            view.showMessage("Hittade inga bokningar");
            return;
        }
        view.showBookingsWithIndex(bookings);

        int adminChoice = view.showAdminMenu();
        if (adminChoice == 0) {
            view.showMenu();
        }
        System.out.println("Ange bokningsnummer: ");
        int index = view.askForBookingIndex() - 1;

        if (index < 0 || index >= bookings.size()) {
            view.showMessage("Ogiltligt bokningsnummer");
            return;
        }

        if (adminChoice == 1) {
            manager.removeBooking(index);
            view.showMessage("Bokningen är nu borttagen");
        }
        if (adminChoice == 2) {
            List<String> freeTimes = manager.getAvailableDates();
            view.showAvailableDates();
            String newDate = view.askForDate();

            String newTime = view.askForNewDate();
            if (freeTimes.contains(newTime)) {
                view.showMessage("Datumet ej tillgängligt!");
                return;
            }

            manager.upDateBooking(index, newDate);
            view.showMessage("Booking updated successfully");
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

}
