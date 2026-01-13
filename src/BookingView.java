import java.util.List;
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
        System.out.println("Vid önskemål om av- eller ombokning, vänligen kontakta oss");
        System.out.println();
        System.out.println("Var vänlig och välj ett av alternativen nedan:");
        System.out.println("1. Kund");
        System.out.println("2. Admin");
        System.out.println("3. Avsluta");
        System.out.println("Val, ange siffra: ");
        return scanner.nextInt();
    }
    public String askForAdminPassword() {
        scanner.nextLine();
        System.out.print("Admin lösenord: ");
        return scanner.nextLine();
    }

    public int showAdminMenu() {
        System.out.println("------Admin-------");
        System.out.println("---Bokningsöversikt---");
        System.out.println("1. Ta bort bokning");
        System.out.println("2. Ändra bokning");
        System.out.println("Val: ");
        return scanner.nextInt();
    }

    public int askForBookingIndex() {
        return scanner.nextInt();
    }
    public String askForNewDate() {
        scanner.nextLine();
        System.out.println("Ange nytt datum: YYYY/MM/DD: ");
        return scanner.nextLine();
    }


    public String askForName(){
        scanner.nextLine();
        System.out.println("Ange ditt för- och efternamn: ");
        return scanner.nextLine();
    }

    public void showAvailableDates(List<String> dates) {
        if (dates.isEmpty()) {
            System.out.println("Tyvärr, inga dagar lediga de närmaste 30 dagarna.");
        } else {
            System.out.println("Tillgängliga dagar:");
            for (String d : dates) {
                System.out.println(d);
            }
        }
    }

    public String askForDate(){
        System.out.println("Ange datumet du vill boka: ");
        return scanner.nextLine();
    }

    public int askForServiceChoice() {
        System.out.println("Våra spa-paket:");
        System.out.println("---------------------");
        System.out.println("1. Basic Spa-paket: Tillgång till pool, bastu och relax i 3h - 800 SEK");
        System.out.println("2. Basic Spa-paket + Massage 40 min - 1200 SEK");
        System.out.println("3. Basic Spa-paket + ansiktsbehandling 60 min - 1350 SEK");
        System.out.println("4. All inclusive: Basic Spa-paket, massage 40 min + ansiktsbehandling 60 min - 1750 SEK");
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
    public void showBookingsWithIndex(List<Booking> bookings) {
        for (int i = 0; i<bookings.size(); i++) {
            System.out.println((i + 1) + ". " +  bookings.get(i));
        }
    }
}
