package View;

import java.awt.event.ActionListener;
import java.util.HashMap;

import Controller.ControllerFunInt;

/**
 * View interface.
 * 
 * View for FunShapes
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public interface ViewFunInt extends ActionListener {

	void initilizeController(ControllerFunInt controller);
   
	boolean hasBeenResized();

	HashMap<Integer, Integer> getPressedKeys();
}
