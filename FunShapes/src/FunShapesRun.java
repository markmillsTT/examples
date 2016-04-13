import Controller.ControllerFun;
import Controller.ControllerFunInt;
import Model.ModelFun;
import Model.ModelFunInt;
import View.ViewFun;
import View.ViewFunInt;

/**
 * Runner File
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public final class FunShapesRun {

    /**
     * Main program that sets up main application window and starts user
     * interaction.
     * 
     * @param args
     *            command-line arguments; not used
     */
    public static void main(String[] args) {
     
        ModelFunInt model = new ModelFun();
        ViewFunInt view = new ViewFun();
        ControllerFunInt controller = new ControllerFun(model, view);
        
        view.initilizeController(controller);
    }

}
