import Controller.ControllerGlass;
import Controller.ControllerGlassInt;
import Model.ModelGlass;
import Model.ModelGlassInt;
import View.ViewGlass;
import View.ViewGlassInt;

/**
 * Runner File
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public final class GlassPaneRun {

    /**
     * Main program that sets up main application window and starts user
     * interaction.
     * 
     * @param args
     *            command-line arguments; not used
     */
    public static void main(String[] args) {
     
        ModelGlassInt model = new ModelGlass();
        ViewGlassInt view = new ViewGlass();
        ControllerGlassInt controller = new ControllerGlass(model, view);
        view.setController(controller);
        
        controller.begin();
        
    }

}
