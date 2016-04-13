
package Model;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.vecmath.*;

import View.ViewGlass;
import View.ViewGlassInt;

/**
 * Model for Glass Pane 
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public final class ModelGlass implements ModelGlassInt {
	
	List<CoordinateSystem> allCoords = new ArrayList<CoordinateSystem>();
	Map<Integer,Map<Vector3d,Shape>> allGlassPaneVectData = new HashMap<Integer,Map<Vector3d, Shape>>();
	private long startTime;
	
    public ModelGlass() {
    	
    	CoordinateSystem csHold;
    	for(int i = 0 ; i < 5 ; i++){
    		for(int k = 1 ; k < 2 ; k++){
    			csHold = new CoordinateSystem(i);
    			csHold.setDistanceVectorFromOrg(new Vector3d(-15+7.5*i, -5 +5*k, 40));
    			SwirlDroplet droplet = new SwirlDroplet(csHold);
	    		allCoords.add(csHold);
    		}
    	}
    	
    }
    
    @Override 
    public void startModel(){
    	this.startTime = System.currentTimeMillis();
    }
    
    @Override 
    public List<CoordinateSystem> getAllCoordSystems(){
    	return this.allCoords;
    }
    
	/**
	 * @requires |positionVectorsInOCS| = |distVectorsToViewables|
	 * 
	 * @param vo
	 * @param positionVectorsInOCS
	 * @param distVectorsToViewables
	 * @param transforms 
	 * @return
	 */
	private void get2DShapeProjectionForViewableObject(ViewableModel vo,
			List<Vector3d> positionVectorsInOCS,
			List<Vector3d> distVectorsToViewables,Map<Vector3d, Shape> shapeMap,
			Dimension drawingBoundsForPort, List<Matrix4d> transforms) {
		
		Rectangle2D rect;
		int x = 0,y = 0,width = 0,height = 0;
		
		// Don't add anything to map if it's full
		if(shapeMap.size() > 40000){
			shapeMap.clear();
			return;
		}
	
		//draw dot
		Vector3d origin = new Vector3d(distVectorsToViewables.get(0));
		origin.sub(positionVectorsInOCS.get(0));
		Vector3d pixVector = mapVectorToPixelVector(origin,drawingBoundsForPort);
	//	shapeMap.put(origin,new Ellipse2D.Double((int) pixVector.getX()-10, (int) pixVector.getY()-10, 10, 10));
		/* remember -- screen uses x y --> positive going right then down on computer screen
		 * ... say middle of screen is at point (0,0,.5) (.5 meters back from user) 
		 * 	after getting x,y,z in this system --> translate to computer system*/
		
		for(int i = 0; i < distVectorsToViewables.size(); i ++){
			Vector3d point = distVectorsToViewables.get(i);
			Vector3d topLeft = new Vector3d(point);
			Vector3d topRight = new Vector3d(point);
			
			if(vo instanceof SwirlDroplet){
				SwirlDroplet sd = (SwirlDroplet)vo;
				double cubeRadius = sd.getSphereRadius();
				//top left corner of each cube center for projection... say cube radius = .5 meters
				// LOOKS LIKE POST-IT ON TOP LEFT CORNER OF CUBE... WITH SAME AREA AS FACE... tehe
				topLeft.add(new Vector3d(-cubeRadius,cubeRadius,-cubeRadius));
				topRight.add(new Vector3d(cubeRadius,cubeRadius,-cubeRadius));
				/* Apply transforms if they exist */
				if(transforms != null){
					topLeft = applyTransforms(topLeft,transforms);
					topRight = applyTransforms(topRight,transforms);
				}
				Vector3d pixVectorLeft = mapVectorToPixelVector(topLeft,drawingBoundsForPort);
				Vector3d pixVectorRight = mapVectorToPixelVector(topRight,drawingBoundsForPort);
				
				x = (int) pixVectorLeft.getX();
				y = (int) pixVectorLeft.getY();
				
				pixVectorRight.sub(pixVectorLeft);
				width = (int) pixVectorRight.length();
				height = width;
				
			} else if (vo instanceof FaceShape){
				FaceShape fs = (FaceShape)vo;
				Vector3f posVect = fs.getXYZPositionVector();
				//top front left corner of rectangular shape 
				topLeft.add(new Vector3d(posVect.x,posVect.y,-posVect.z));
				topRight.add(new Vector3d(-posVect.x,posVect.y,-posVect.z));
				Vector3d pixVectorLeft = mapVectorToPixelVector(topLeft,drawingBoundsForPort);
				Vector3d pixVectorRight = mapVectorToPixelVector(topRight,drawingBoundsForPort);
				
				x = (int) pixVectorLeft.getX();
				y = (int) pixVectorLeft.getY();
				
				pixVectorRight.sub(pixVectorLeft);
				width = (int) pixVectorRight.length();
				height = width;
			}
				//FIXME --- point is to middle of a cube, but projection is just front side parallel to viewer
			if(x >= -drawingBoundsForPort.width && x < 2*drawingBoundsForPort.width && y >= -1.5*drawingBoundsForPort.height && y <= 1.5*drawingBoundsForPort.height )
				shapeMap.put(point,new Ellipse2D.Double(x,y,width,height));
		}	
	}
	private Vector3d applyTransforms(Vector3d vector, List<Matrix4d> transforms) {
		for(int k = transforms.size() - 1; k >= 0 ; k--){
			Matrix4d holderMatrix = new Matrix4d();
			holderMatrix.setM00(vector.x);	holderMatrix.setM01(0);			holderMatrix.setM02(0);			holderMatrix.setM03(0);
			holderMatrix.setM11(0);			holderMatrix.setM11(vector.y);	holderMatrix.setM12(0);			holderMatrix.setM13(0);
			holderMatrix.setM20(0);			holderMatrix.setM21(0);			holderMatrix.setM22(vector.z);	holderMatrix.setM23(0);
			holderMatrix.setM30(0);			holderMatrix.setM31(0);			holderMatrix.setM32(0);			holderMatrix.setM33(1);
			holderMatrix.mul(transforms.get(k));
			vector = new Vector3d(holderMatrix.m00, holderMatrix.m11, holderMatrix.m22);
		}
		return vector;
	}

	/**
	 * 	//XXX FIXME Convert to pixels
	 * DOES NOT CLEAR origin
	 * @param origin
	 * @param drawingBoundsForPort 
	 * @return
	 */
	private Vector3d mapVectorToPixelVector(Vector3d origin, Dimension drawingBoundsForPort) {
		Vector3d pixVector = new Vector3d(origin);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		//scale to screen (.5 meters away)
		pixVector.scale(.5f/pixVector.getZ());
		//move grid system to match computers
		pixVector.setY(-1f*pixVector.getY());
		//scale to pixels
		pixVector.setX(pixVector.getX() * screenSize.getWidth()/.34); // meter vector * pixel/meter .. topLeft = <pixel,pixel,meter>
		pixVector.setY(pixVector.getY() * screenSize.getHeight()/.19);
		pixVector.add(new Vector3d(drawingBoundsForPort.width/2.0,drawingBoundsForPort.height/2.0,0));
		return pixVector;
	}

	@Override
	public Map<Vector3d, Shape> getAllCurrentParallel2DScreenProjections(Dimension viewPortDimensions, int id) {
		return getAllCurrentCamera2DScreenProjections(viewPortDimensions,id,null);
	}
	
	@Override
	public Map<Vector3d,Shape> getAllCurrentCamera2DScreenProjections(Dimension drawingBoundsForPort, int glassPaneId, List<Matrix4d> transforms) {
		if(!allGlassPaneVectData.containsKey(glassPaneId)){
			allGlassPaneVectData.put(glassPaneId,new HashMap<Vector3d, Shape>());
		}
		Map<Vector3d, Shape> shapeMap = allGlassPaneVectData.get(glassPaneId);
		List<Vector3d> distVectorsToViewables = new ArrayList<Vector3d>();
		List<ViewableModel> viewables;
		Vector3d distanceVectorFromOrg,hold;
		ViewableModel vo;
		List<Vector3d> positionVectorsInOCS;
		
		/* get all things that need to be drawn for every coordinate system in model -- get their distance vectors*/
		for(int i = 0; i < allCoords.size(); i++){
			viewables = allCoords.get(i).getViewables();
			distanceVectorFromOrg = allCoords.get(i).getDistanceVectorFromOrg(System.currentTimeMillis() - this.startTime);
			/* each coordninate can have multiple viewable objects -- ie: 2 swirls patterns and a cube pattern */
			for(int k = 0; k < viewables.size(); k++){
				vo = viewables.get(k);
				//list of vectors ordered in case of 2D projection depending on location of specific vertices
				positionVectorsInOCS = vo.getAllPositionVectorsInOCS(System.currentTimeMillis() - this.startTime);
				/* each patten can have multiple things that need to be drawn -- ie: a cube could have 8 vertices*/
				for(int z = 0; z < positionVectorsInOCS.size(); z++){
					/* green arrow plus blue and red arrow in documentation*/
					hold = new Vector3d();
					hold.add(positionVectorsInOCS.get(z), distanceVectorFromOrg);
					distVectorsToViewables.add(hold);
				}
				/* this method decides how to assign screen shapes based on the viewable object's position data*/
				get2DShapeProjectionForViewableObject(vo,positionVectorsInOCS, distVectorsToViewables,shapeMap,drawingBoundsForPort,transforms);
				
				//there is no surface in 1 or 3
				if(glassPaneId != 2)
					clearDataMap(glassPaneId);
				/* DONE---Kept for documentation
				TODO Get distance vectors to viewables ... then use information to create
					2D projection shapes on computer screen
					
					(SEE MVC Overview part b - Level 1 abstraction)
					
					-getAllPositionVectorsInOCS(System.currentTimeMillis() - this.startTime) returns green vectors at given time
					-loop through green arrows to draw Black arrows are the contained in distVectorsToViewables, find
					 	by adding green vectors to red/blue -- OR (distanceVectorFromOrg + greenVector_i)
					- designate shape to black arrows found -- project to computer screen
					- translate projection in real coordinates to computer screen coordinates (top-left corner origin system)
					- add created shape to list, return to view to draw and assemble with other components
				*/
			}
		}
		
		return shapeMap;
	}

	@Override
	public ViewGlassInt getViewingWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Map<Vector3d, Shape> getAllCurrentCamera2DScreenProjections(
			Dimension drawingBoundsForPort, int id) {
		// TODO Calculate Rotation THEN offset Matricies to left multiply then Pass in list named transforms
		List<Matrix4d> transforms = new ArrayList<Matrix4d>();
		transforms.add(0,getRotationMatrixAroundZAxis(3.0*Math.PI/4.0));
		transforms.add(0,getOffsetMatrix(new Vector3d(0,0,20)));
		return getAllCurrentCamera2DScreenProjections(drawingBoundsForPort,id,transforms);
	}

	private Matrix4d getOffsetMatrix(Vector3d xyzOffset) {
		Matrix4d offsetMatrix = new Matrix4d();
		offsetMatrix.setM00(1);offsetMatrix.setM01(0);offsetMatrix.setM02(0);offsetMatrix.setM03(xyzOffset.x);
		offsetMatrix.setM11(0);offsetMatrix.setM11(1);offsetMatrix.setM12(0);offsetMatrix.setM13(xyzOffset.y);
		offsetMatrix.setM20(0);offsetMatrix.setM21(0);offsetMatrix.setM22(1);offsetMatrix.setM23(xyzOffset.z);
		offsetMatrix.setM30(0);offsetMatrix.setM31(0);offsetMatrix.setM32(0);offsetMatrix.setM33(1);
		return offsetMatrix;
	}

	private Matrix4d getRotationMatrixAroundZAxis(double theta) {
		Matrix4d rotationMatrix = new Matrix4d();
		rotationMatrix.setM00(Math.cos(theta));	rotationMatrix.setM01(Math.sin(theta));		rotationMatrix.setM02(0);			rotationMatrix.setM03(0);
		rotationMatrix.setM11(-Math.sin(theta));rotationMatrix.setM11(Math.cos(theta));		rotationMatrix.setM12(0);			rotationMatrix.setM13(0);
		rotationMatrix.setM20(0);				rotationMatrix.setM21(0);					rotationMatrix.setM22(1);	rotationMatrix.setM23(0);
		rotationMatrix.setM30(0);				rotationMatrix.setM31(0);					rotationMatrix.setM32(0);			rotationMatrix.setM33(1);
		return rotationMatrix;
	}

	@Override
	public void clearDataMap(int glassPaneId) {
		allGlassPaneVectData.put(glassPaneId,new HashMap<Vector3d, Shape>());
	}

}
