public class BasicSpa implements SpaService{
    @Override
    public String getDescription() {
        return "The Basic Spa Package includes access to the pool, " +
                "sauna, and relaxation area for a calming experience";
    }

    @Override
    public double getPrice() {
        return 500;
    }
}
