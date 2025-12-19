package SpaServices;

public class Massage extends SpaServiceDecorator {
    public Massage(SpaService spaService){
        super(spaService);
    }
    @Override
    public String getDescription() {
        return spaService.getDescription() + "\nSpaServices.Massage 40 min";
    }
    @Override
    public double getPrice() {
        return spaService.getPrice() + 400;
    }
}
