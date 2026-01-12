import SpaServices.*;


public class Main {
    public static void main(String[] args) {

        BookingManager manager = BookingManager.getInstance();
        manager.loadBookingsFromFile();

        BookingView view = new BookingView();
        BookingController controller = new BookingController(manager, view);

        controller.startApplication();

        manager.saveBookingsToFile();
    }
}

