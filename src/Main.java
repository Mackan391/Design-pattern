public class Main {
    public static void main(String[] args) {
        SpaService spa = new BasicSpa();
        spa = new Massage(spa);
        spa = new FacialTreatment(spa);

        System.out.println(spa.getDescription());
        System.out.println("Total: " + spa.getPrice() + " kr");

    }
}
