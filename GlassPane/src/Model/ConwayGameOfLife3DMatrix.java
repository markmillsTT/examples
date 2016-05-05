package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Helpers.Vector3f;

/***
 * This class models a 3D Presentation of Conway's Game of Life.
 * 
 * 
 ** "Life" follows Conway's 4 Rules for each orthogonal dimension in Z^3 (3D space in the Z - or integer - Domain)
 **** The following are the 2D orthogonal planes that will be used to run "Life"
 ****** XY
 ****** XZ
 ****** YZ
 *
 ** With each iteration of the model:
 **** New seeds are planted at specified XYZ locations
 **** "Life" is spread to each each cell by following the 4 rules of Conway's Game for each Orthogonal Plane
 **** "Life" is first spread in the XY plane, followed by the XZ plane, followed by the YZ plane
 **** Running total of consecutive iterations that a cell is "Alive" are recorded in the dimensional arrays
 **** Dead Cells are set to 0
 **** Color of the cell is displayed based on the stored integer in that cell
 * 
 *
 * @author mmills
 *
 */
public class ConwayGameOfLife3DMatrix implements ViewableModel {

	public final CoordinateSystem objCoordSystem;
	public List<Vector3f> newSeedLocations;
	public Map<Vector3f,Integer> currentLiveCellLocations;
	
	public ConwayGameOfLife3DMatrix(CoordinateSystem objCoordSystem) {
		
		this.objCoordSystem = objCoordSystem;
		this.objCoordSystem.addToViewables(this);
		this.newSeedLocations = new ArrayList<Vector3f>();
		this.currentLiveCellLocations = new HashMap<Vector3f,Integer>();
		
	}
	
	@Override
	public CoordinateSystem getObjectCoordSystem() {
		return this.objCoordSystem;
	}
 
	/**
	 * returns list of distance vectors from observer object origin of things to be drawn.
	 * 
	 * 		SEE Level 1 abstraction part b) -- Returns a list of green arrows for a particular 
	 * 											SwirlDroplet (viewable object)
	 * 
	 * OCS = Object Coordinate System
	 * 
	 */
	@Override
	public List<Vector3f> getAllPositionVectorsInOCS(long t) {
		iterateLifeAfterPlantingNewSeeds();
		return getVectorsToAllLiveCells();
	}

	private void iterateLifeAfterPlantingNewSeeds() {
		plantNewSeeds();
		iterateLife();
		clearNewSeedList();
	}

	private void plantNewSeeds() {
		
		for(Vector3f newSeedLoc : this.newSeedLocations) {
			for(Vector3f liveCellLoc : this.currentLiveCellLocations.keySet()) {

				if(newSeedLoc.distanceFrom(liveCellLoc) == 0f) {
					Integer currentWeight = this.currentLiveCellLocations.get(liveCellLoc);
					this.currentLiveCellLocations.put(liveCellLoc, (currentWeight + 1));
				} else {
					this.currentLiveCellLocations.put(newSeedLoc, 1);
				}
				
			}

		}
		
	}
	
	/**
	 * "Life" follows Conway's 4 Rules for each orthogonal dimension in Z^3 (3D space in the Z - or integer - Domain)
	 ** The following are the 2D orthogonal planes that will be used to run "Life"
	 **** XY
	 **** XZ
	 **** YZ
	 *
	 * "Life" is first spread in the XY plane, followed by the XZ plane, followed by the YZ plane
	 * 
	 */
	public void iterateLife() {

		for( Vector3f liveCellLocation : this.currentLiveCellLocations.keySet() ) {
			// Spread life in following order: XY, XZ, YZ
			spreadLifeAround(liveCellLocation, "xy");
			spreadLifeAround(liveCellLocation, "xz");
			spreadLifeAround(liveCellLocation, "yz");
		}
		
	}
	
	// TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO
	// need to figure out quick way to determain number of neighbors to a liveCellLocation
	private void spreadLifeAround(Vector3f liveCellLocation, String plane) {
		switch(plane) {
			case "xy":

				break;
			case "xz":
				break;
			case "yz":
				break;
		}
	}

	private void clearNewSeedList() {
		this.newSeedLocations.clear();
	}

	private List<Vector3f> getVectorsToAllLiveCells() {
		iterateLifeAfterPlantingNewSeeds();
		// TODO make vectors pointing to live cells
		return null;
	}	
	
	/**
	 * Returns weights with weightList.get(n) corresponding to this.currentLiveCellLocations.get(n)
	 * @return
	 */
	public Map<Vector3f,Integer> getAllPositionVectorsInOCSWithWeights () {		
		return this.currentLiveCellLocations;
	}
	
	public void addNewSeed(Vector3f seedLocation) {
		newSeedLocations.add(seedLocation);
	}
	
	private void addNewListOfSeeds(List<Vector3f> seedList) {
		newSeedLocations.addAll(seedList);
	}

//	private List<Vector3f> getSwirlPattern1(double omegaT) {
//		
//		List<Vector3f> allGreenArrows = new ArrayList<Vector3f>();
//		Vector3f distVectInOCS = new Vector3f();
//		for(int i = 0 ; i < this.numberDroplets; i++){
//			float x,y,z;
//			x = (float) ( swirlRadius*Math.sin(4*omegaT+dropletThetaPhasesOCS[i]/2.0f)*swirlRadius*Math.cos(omegaT/4.0+dropletThetaPhasesOCS[i]/2.0f) );
//			y = (float) ( swirlRadius*Math.cos(2.0*omegaT+dropletThetaPhasesOCS[i]/2.0f) );
//			z = (float) ( swirlRadius*Math.cos(8*omegaT+dropletThetaPhasesOCS[i]/4.0f)*swirlRadius*Math.cos(omegaT/4.0+dropletThetaPhasesOCS[i]/2.0f) );
//			distVectInOCS = new Vector3f(x, y, z); //green arrow in Level 1 part B
//			allGreenArrows.add(distVectInOCS);
//		}
//		return allGreenArrows;
//	}

}
