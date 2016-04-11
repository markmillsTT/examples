import Controller.ControllerGlass;
import Controller.ControllerGlassInt;
import Model.ModelGlass;
import Model.ModelGlassInt;
import View.ViewGlass;
import View.ViewGlassInt;

/**
 * Runner File
 * 
 * @author Mark David Mills
 * 
 * 			David is my middle name - read my state ID - 1965
 * 
 * </pre>
 */
public final class GlassPaneRun {
	
	/****
	 * 
					GlassPaneRun.java --- serialVersionUID = 0L;
					ControllerGlass.java --- serialVersionUID = 1L;
					ModelGlass.java --- serialVersionUID = 2L;
					ViewGlass.java --- serialVersionUID = 3L; 
					
	 */
	private static final long serialVersionUID = 0L;
	
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
         
         for(int i = 0; i < args.length; i++){
        	 System.out.print( "ARG :" + i + "\n" + args[i] + "     ");
         }
         
    }

}
