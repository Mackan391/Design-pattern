import java.util.Scanner;

public class BookingView {
    private Scanner scanner =new Scanner(System.in);

    public int showMenu() {
        System.out.println("--------------------------------------");
        System.out.println("  Välkommen till Relaxa Spa           ");
        System.out.println("--------------------------------------");
        System.out.println("Adress: Spa-gatan 11, 111 22 Stockholm");
        System.out.println("Telefon: 00-000 00 00");
        System.out.println("E-post: info@relaxaspa.se");
        System.out.println("Öppetider: Mån-fre 10:00-18:00");
        System.out.println();
        System.out.println("Var vänlig och välj ett av alternativen nedan:");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        System.out.println("3. Exit");
        System.out.println("Choice: ");
        return scanner.nextInt();
    }

    public String askForName(){
        scanner.nextLine();
        System.out.println("Ange ditt för- och efternamn: ");
        return scanner.nextLine();
    }

    public String askForDate(){
        scanner.nextLine();
        System.out.println("Ange datumet du vill boka: ");
        return scanner.nextLine();
    }

    public int askForServiceChoice() {
        System.out.println("Our Spa Services:");
        System.out.println("---------------------");
        System.out.println("1. Basic Spa-paket: Tillgång till pool, bastu och relax i 3h - 800 SEK");
        System.out.println("2. Basic Spa-paket + Massage 40 min - 1200 SEK");
        System.out.println("3. Basic Spa-paket + ansiktsbehandling 60 min - 1350:-");
        System.out.println("4. All inclusive: Basic Spa-paket, massage 40 min + ansiktsbehandling 60 min - 1750:-");
        System.out.println("Var vänlig ange vilket paket du vill boka:  ");
        return scanner.nextInt();
    }

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
