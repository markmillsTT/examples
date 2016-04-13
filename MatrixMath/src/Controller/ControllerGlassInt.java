package Controller;

import java.util.Map;

import Helpers.Vector3f;
import Model.CoordinateSystem;

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

	Map<Vector3f, CoordinateSystem> getCurrentSceneObjects();

   
}
