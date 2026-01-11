package SpaServices;

public class SpaController {
    private SpaView view;

    public SpaController(SpaView view){
        this.view = view;
    }
    public void showServices(){
        view.showWelcome();
    }

}
