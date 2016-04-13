package Controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Helpers.Vector3f;
import Model.CoordinateSystem;
import Model.ModelGlassInt;
import View.ViewGlassInt;

/**
 * Controller class.
 * 
 * @author Mark Mills
 */
public final class ControllerGlass implements ControllerGlassInt {

    private final ModelGlassInt model;
    private final ViewGlassInt view;
    private int currentScene = 1;
    
    @SuppressWarnings("unused") // Positioned here for future purposes.
	private static final long serialVersionUID = 1L;
    

    /**
     * Ties in model and view
     * 
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public ControllerGlass(ModelGlassInt model, ViewGlassInt view) {
        this.model = model;
        this.view = view;
    }

	@Override
	public void begin() {
		model.startModel();
		model.loadORreloadAllData();
		view.startRepaintFlashTimer();
	}

	/***
	 * Returns Copy of Live Model Data
	 */
	@Override
	public Map<Vector3f, CoordinateSystem> getCurrentSceneObjects() {
		
		int switchTimeMS 	= 4000; 	// in milliseconds
		long loopTimeMS 	= 10000;	// in milliseconds
		long totalTimeRanMS = System.currentTimeMillis() - model.getStartTime();
		
		int local_currentScene = this.currentScene;
		
		if(totalTimeRanMS > switchTimeMS && totalTimeRanMS < 2*switchTimeMS){
			this.currentScene = 2;
		} else if(totalTimeRanMS > 2*switchTimeMS){
			this.currentScene = 1;
		} else if(totalTimeRanMS > loopTimeMS){
			model.loadORreloadAllData();
		}
		
		model.loadMotionSystem(this.currentScene, (float) (Math.PI/(1024)));
		
		Map<Vector3f, CoordinateSystem> updatingModelData = model.getObjectLocationsForScene(local_currentScene);
		if(updatingModelData.isEmpty())
			return null;
		
		Map<Vector3f, CoordinateSystem> updatingModelDataCopy = new HashMap<Vector3f, CoordinateSystem>();
		Iterator<Vector3f> keySet = updatingModelData.keySet().iterator();
		
		synchronized(this){
			
			while(keySet.hasNext()){
				Vector3f dist = keySet.next();
				updatingModelDataCopy.put(dist, updatingModelData.get(dist) );
			}
		}
		return updatingModelDataCopy;
	}

}
