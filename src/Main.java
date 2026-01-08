import SpaServices.BasicSpa;
import SpaServices.FacialTreatment;
import SpaServices.Massage;
import SpaServices.SpaService;

public class Main {
    public static void main(String[] args) {

        BookingManager manager = BookingManager.getInstance();
        manager.loadBookingsFromFile();

        BookingView view = new BookingView();
        BookingController controller = new BookingController(manager, view);

        User customer = new User("Johanna", Role.CUSTOMER);
        User customer2 = new User("John", Role.CUSTOMER);
        User admin = new User("Admin", Role.ADMIN);

        SpaService service = new BasicSpa();
        service = new Massage(service);
        service = new FacialTreatment(service);

        controller.createBooking(customer, service, "2026-03-22");

        SpaService service2 = new BasicSpa();
        service2 = new FacialTreatment(service2);
        controller.createBooking(customer2, service2, "2026-01-22");

        view.showMessage("Customer bookings:");
        controller.showUserBookings(customer);

        view.showMessage("Admin view:");
        controller.showAllBookings(admin);

        manager.saveBookingsToFile();
    }
}

