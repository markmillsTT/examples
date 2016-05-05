package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Helpers.Vector3f;

public class SwirlDroplet implements ViewableModel {

	public final CoordinateSystem objCoordSystem;
	private double[] dropletThetaPhasesOCS;
	private int numberDroplets;
	private double swirlRadius; //in meters
	private double sphereRadius;
	private double swirlPeriod = 1.0/(2.0*Math.PI/(100.0));
	private float zOffset = 100;
	private boolean zIncreasing;
	
	public SwirlDroplet(CoordinateSystem objCoordSystem){
		this.objCoordSystem = objCoordSystem;
		this.objCoordSystem.addToViewables(this);
		this.numberDroplets = 16;
		this.dropletThetaPhasesOCS = new double[numberDroplets];
		this.swirlRadius = 10;
		this.sphereRadius = .5f;
		for(int i = 0; i < numberDroplets; i++){
			double thetaPhase = ((double)i)*2.0 * Math.PI / ((double)numberDroplets);
		//	this.dropletThetaPhasesOCS[i] = new Vector3f((float)(swirlRadius* Math.sin(theta)), 0, (float) (swirlRadius*Math.cos(theta)));
			this.dropletThetaPhasesOCS[i] = thetaPhase;
		}
	}
	
	public SwirlDroplet(CoordinateSystem objCoordSystem,Vector3f distFromOrigMCS, int numberDroplets,int swirlRadius, float cubeRadius){
		this.sphereRadius = cubeRadius;
		this.objCoordSystem = objCoordSystem;
		this.objCoordSystem.addToViewables(this);
		this.numberDroplets = numberDroplets;
		this.dropletThetaPhasesOCS = new double[numberDroplets];
		this.swirlRadius = swirlRadius;
		for(int i = 0; i < numberDroplets; i++){
			double thetaPhase = ((double)i)*2.0 * Math.PI / ((double)numberDroplets);
			this.dropletThetaPhasesOCS[i] = thetaPhase;
		}
	}
	
	@Override
	public CoordinateSystem getObjectCoordSystem() {
		
		if(zOffset >= 200 && zIncreasing ){
			zOffset -= (float) 25 * Math.abs( Math.sin( (2.0*Math.PI/(256.0)) * System.currentTimeMillis() + 0) );
			zIncreasing = false;
		} else if(zOffset <= 100 && !zIncreasing ){
			zOffset += (float) 25 * Math.abs( Math.sin( (2.0*Math.PI/(256.0)) * System.currentTimeMillis() + 0) );
			zIncreasing = true;
		}
		
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
		
		List<Vector3f> sp;
		double omegaT = (t)*2*Math.PI/(256);
		sp = getSwirlPattern1(omegaT);
//		sp = getSwirlPattern2(omegaT);
//		sp = getSwirlPattern3(omegaT);
		sp.addAll(getSwirlPattern2(omegaT));
		sp.addAll(getSwirlPattern3(omegaT));
		sp.addAll(getSwirlPattern4(omegaT));
		return sp;
	}

	private List<Vector3f> getSwirlPattern1(double omegaT) {
		
		List<Vector3f> allGreenArrows = new ArrayList<Vector3f>();
		Vector3f distVectInOCS = new Vector3f();
		for(int i = 0 ; i < this.numberDroplets; i++){
			float x,y,z;
			x = (float) ( swirlRadius*Math.sin(4*omegaT+dropletThetaPhasesOCS[i]/2.0f)*swirlRadius*Math.cos(omegaT/4.0+dropletThetaPhasesOCS[i]/2.0f) );
			y = (float) ( swirlRadius*Math.cos(2.0*omegaT+dropletThetaPhasesOCS[i]/2.0f) );
			z = (float) ( swirlRadius*Math.cos(8*omegaT+dropletThetaPhasesOCS[i]/4.0f)*swirlRadius*Math.cos(omegaT/4.0+dropletThetaPhasesOCS[i]/2.0f) );
			distVectInOCS = new Vector3f(x, y, z); //green arrow in Level 1 part B
			allGreenArrows.add(distVectInOCS);
		}
		return allGreenArrows;
	}
	
	private List<Vector3f> getSwirlPattern2(double omegaT) {
		List<Vector3f> allGreenArrows = new ArrayList<Vector3f>();
		Vector3f distVectInOCS = new Vector3f();
		for(int i = 0 ; i < this.numberDroplets; i++){
			float x,y,z;
			x = (float) (swirlRadius*Math.sin(2*omegaT+dropletThetaPhasesOCS[i]/4.0f)*swirlRadius*Math.cos(omegaT/2.0+dropletThetaPhasesOCS[i]/4.0f) );
			y = (float) (swirlRadius*Math.cos(4*omegaT+dropletThetaPhasesOCS[i]));
			z = (float) (swirlRadius*Math.sin(2*omegaT+dropletThetaPhasesOCS[i]/4.0f)*swirlRadius*Math.cos(omegaT/2.0+dropletThetaPhasesOCS[i]/4.0f) );
			distVectInOCS = new Vector3f(x, y, z); //green arrow in Level 1 part B
			allGreenArrows.add(distVectInOCS);
		}
		return allGreenArrows;
	}
	
	private List<Vector3f> getSwirlPattern3(double omegaT) {
		List<Vector3f> allGreenArrows = new ArrayList<Vector3f>();
		Vector3f distVectInOCS = new Vector3f();
		for(int i = 0 ; i < this.numberDroplets; i++){
			float x,y,z;
			double sin = Math.sin(8*omegaT+dropletThetaPhasesOCS[i]);
			double cos = Math.cos(4*Math.sqrt(2)*omegaT+dropletThetaPhasesOCS[i]);
			double tan = Math.cos(8*omegaT+dropletThetaPhasesOCS[i]);
			x = (float) (2*swirlRadius*sin*sin*tan);
			y = (float) (2*swirlRadius*cos*sin*tan);
			z = (float) (swirlRadius*sin*tan);
			distVectInOCS = new Vector3f(x, y, z); //green arrow in Level 1 part B
			allGreenArrows.add(distVectInOCS);
		}
		return allGreenArrows;
	}
	
	
	private Collection<? extends Vector3f> getSwirlPattern4(double omegaT) {
		
		List<Vector3f> allGreenArrows = new ArrayList<Vector3f>();
		Vector3f distVectInOCS = new Vector3f();
		for(int i = 0 ; i < this.numberDroplets; i++){
			float x,y,z;
			double sin = Math.sin(omegaT+dropletThetaPhasesOCS[i]/2.0f);
			double cos = Math.cos(omegaT+dropletThetaPhasesOCS[i]/4.0f);
			double tan = Math.cos(omegaT+dropletThetaPhasesOCS[i]/8.0f);
			x = (float) (2*swirlRadius*sin*cos*tan);
			y = (float) (2*swirlRadius*cos*tan);
			z = (float) (swirlRadius*sin*sin*tan);
			distVectInOCS = new Vector3f(x, y, z); //green arrow in Level 1 part B
			allGreenArrows.add(distVectInOCS);
		}
		return allGreenArrows;
	}

	public double getSphereRadius() {
		return this.sphereRadius;		
	}


}
