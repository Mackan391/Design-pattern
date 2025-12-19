package SpaServices;

public abstract class SpaServiceDecorator implements SpaService {
    protected SpaService spaService;

    public SpaServiceDecorator(SpaService spaService) {
        this.spaService = spaService;
    }
}
// Abstrakt dekorator som implementerar SpaPackage och innehåller ett
// SpaPackage-objekt. Klassen fungerar som en bas för alla konkreta
// dekoratorer och gör det möjligt att lägga till extra funktionalitet
// (t.ex. behandlingar) till ett befintligt spapaket utan att ändra
// grundklasserna.
