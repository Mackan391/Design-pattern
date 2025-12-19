import SpaServices.BasicSpa;
import SpaServices.FacialTreatment;
import SpaServices.Massage;
import SpaServices.SpaService;

public class Main {
    public static void main(String[] args) {
        SpaService spa = new BasicSpa();
        spa = new Massage(spa);
        spa = new FacialTreatment(spa);

        System.out.println(spa.getDescription());
        System.out.println("Total: " + spa.getPrice() + " kr");

    }
}
//Har bara testat så decorator fungerar, ej lagt in något annat här
