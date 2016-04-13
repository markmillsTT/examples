package Controller;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.vecmath.Vector3d;

import Model.CoordinateSystem;
import Model.ModelGlassInt;
import Model.ViewableModel;
import View.ViewGlassInt;

/**
 * Controller class.
 *  
 * @author Mark Mills
 */
public final class ControllerGlass implements ControllerGlassInt {

    private final ModelGlassInt model;
    private final ViewGlassInt view;
    protected boolean inParallelSpace = false;
    

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
		view.startRepaintFlashTimer();
	}
    
	@Override
	public Map<Vector3d,Shape> getVectorsToProjMapToDraw(int id) {
		if(inParallelSpace)
			return model.getAllCurrentParallel2DScreenProjections(view.getViewPortDimensions(), id);
		//else in camera space by default
		return model.getAllCurrentCamera2DScreenProjections(view.getViewPortDimensions(), id);
	}
	
	@Override
	public List<Vector3d> getAllCoordPositions(){
		List<Vector3d> retVal = new ArrayList<Vector3d>();
		List<CoordinateSystem> allCoordSystems = model.getAllCoordSystems();
		for(int i = 0; i < allCoordSystems.size(); i ++){
			retVal.add(allCoordSystems.get(i).getDistanceVectorFromOrg());
		}
		return retVal;
	}
	
	@Override
	public Dimension viewPortDims(){
		
		return new Dimension(view.getViewPortDimensions());
	}


	@Override
	public void toggleToParrellelView(int panelID) {
		model.clearDataMap(panelID);
		if(inParallelSpace)
			inParallelSpace = false;
		else
			inParallelSpace = true;
	}
	


}
