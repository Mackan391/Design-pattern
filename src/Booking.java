import SpaServices.SpaService;
import java.io.Serializable;

public class Booking implements Serializable {
    private User user;
    private SpaService spaService;
    private String date;

    public Booking(User user, SpaService spaService, String date) {
        this.user = user;
        this.spaService = spaService;
        this.date = date;
    }
    public User getUser() {
        return user;
    }
    public SpaService getSpaService() {
        return spaService;
    }
    public String getDate() {
        return date;
    }
}
