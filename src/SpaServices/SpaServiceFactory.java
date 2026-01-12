package SpaServices;

public class SpaServiceFactory {

    public static SpaService create(int choice) {
        SpaService service = new BasicSpa();

        switch (choice) {
            case 2 -> service = new Massage(service);
            case 3 -> service = new FacialTreatment(service);
            case 4 -> {
                service = new Massage(service);
                service = new FacialTreatment(service);
            }
        }

        return service;
    }
}

