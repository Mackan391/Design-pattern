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


        List<String> availableDates = manager.getAvailableDates();
        if (availableDates.isEmpty()) {
            view.showMessage("Tyvärr, inga dagar lediga de närmaste 30 dagarna.");
            return; // avbryt om inga datum finns
        }

        view.showAvailableDates(availableDates); // skriver ut lediga datum
        String date = view.askForDate();


        if (!manager.isDateAvailable(date)) {
            view.showMessage("Ogiltigt. Datumet finns ej i listan");
            return;
        }

        int serviceChoice = view.askForServiceChoice(); // Låt användaren välja spa-tjänst
        SpaService service = createService(serviceChoice);

        Booking booking = new Booking(customer, service, date); // Skapa bokningen
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
        if (!manager.authenticateAdmin(password)) {
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
            view.showAvailableDates(freeTimes);

            String newTime = view.askForNewDate();
            if (!freeTimes.contains(newTime)) {
                view.showMessage("Datumet ej tillgängligt!");
                return;
            }

            manager.upDateBooking(index, newTime);
            manager.saveBookingsToFile();
            view.showMessage("Bokningen är nu uppdaterad!");
        }
    }

}
