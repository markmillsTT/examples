package Model;

import java.util.Map;

import Helpers.Vector3f;

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

	void loadObjectLocation(int sceneNum, Vector3f Vector3f, CoordinateSystem coordSystem,
			int count);
	
	Map<Vector3f, CoordinateSystem> getObjectLocationsForScene(int sceneNum);

	void loadModelScene(int sceneNum);

	long getStartTime();

	void loadORreloadAllData();

	void loadMotionSystem(int sceneNum,float omegaT);
   
}
