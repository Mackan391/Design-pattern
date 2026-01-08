public class BookingView {
    public void showBooking(Booking booking) {
        System.out.println(
                booking.getUser().getName() + " | " +
                        booking.getSpaService().getDescription() + " | " +
                        booking.getSpaService().getPrice() + " kr | " +
                        booking.getDate()
        );
    }
    public void showMessage(String message) {
        System.out.println(message);
    }
}
