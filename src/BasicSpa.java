public class BasicSpa implements SpaService{
    @Override
    public String getDescription() {
        return "Basic Spa Package:" +
                "\naccess to pool, sauna, and relaxation area in 3 hours";
    }

    @Override
    public double getPrice() {
        return 800;
    }
}

/*Basprodukten för spaet*/
