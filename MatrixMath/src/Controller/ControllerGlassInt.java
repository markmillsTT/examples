package Controller;

import java.util.Map;

import Helpers.Vector3f;
import Model.ViewableObject;

/**
 * Controller interface.
 * 
 * Controller for FunShapes
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public interface ControllerGlassInt {

	void begin();

	Map<Vector3f, ViewableObject> getCurrentSceneObjects();

   
}
