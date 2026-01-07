package SpaServices;

import java.io.Serializable;
import java.security.Provider;

public interface SpaService extends Serializable {
    String getDescription();
    double getPrice();
}

/*Ett gemensamt interface*/