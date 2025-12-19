package SpaServices;

public class FacialTreatment extends SpaServiceDecorator {
    public FacialTreatment(SpaService spaService){
        super(spaService);
    }
    @Override
    public String getDescription() {
        return spaService.getDescription() + "\nFacial Treatment 60 min";
    }
    @Override
    public double getPrice() {
        return spaService.getPrice() + 550;
    }
}
