public class FacialTreatment extends SpaServiceDecorator {
    public FacialTreatment(SpaService spaService){
        super(spaService);
    }
    @Override
    public String getDescription() {
        return spaService.getDescription() + ", Facial Treatment 60 min";
    }
    @Override
    public double getPrice() {
        return spaService.getPrice() + 550;
    }
}
