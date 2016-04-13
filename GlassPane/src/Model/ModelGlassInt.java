package Model;

import java.awt.Dimension;
import java.awt.Shape;
import java.util.List;
import java.util.Map;

import javax.vecmath.Matrix4d;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3d;

import View.ViewGlassInt;

/**
 * Model interface.
 * 
 * Model for FunShapes
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public interface ModelGlassInt {

	void startModel();

	List<CoordinateSystem> getAllCoordSystems();
	
	ViewGlassInt getViewingWindow();

	Map<Vector3d, Shape> getAllCurrentCamera2DScreenProjections( Dimension drawingBoundsForPort, int id);

	Map<Vector3d, Shape> getAllCurrentParallel2DScreenProjections(Dimension viewPortDimensions, int id);

	Map<Vector3d, Shape> getAllCurrentCamera2DScreenProjections(
			Dimension drawingBoundsForPort, int glassPaneId,
			List<Matrix4d> transforms);

	void clearDataMap(int glassPaneId);

   
}
