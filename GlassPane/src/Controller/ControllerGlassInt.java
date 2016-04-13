package Controller;

import java.awt.Dimension;
import java.awt.Shape;
import java.util.List;
import java.util.Map;

import javax.vecmath.Vector3d;

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

	Map<Vector3d, Shape> getVectorsToProjMapToDraw(int panelNum);

	List<Vector3d> getAllCoordPositions();

	Dimension viewPortDims();

	void toggleToParrellelView(int panelID);
	
}
