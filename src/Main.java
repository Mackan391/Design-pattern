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
        User admin = new User("Admin", Role.ADMIN);
    }
}
//Har bara testat så decorator fungerar, ej lagt in något annat här
