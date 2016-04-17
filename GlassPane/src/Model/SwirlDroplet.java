package Model;

import java.util.ArrayList;
import java.util.List;

import Helpers.Vector3f;

public class SwirlDroplet implements ViewableModel {

	public final CoordinateSystem objCoordSystem;
	private double[] dropletThetaPhasesOCS;
	private int numberDroplets;
	private double swirlRadius; //in meters
	private double sphereRadius;
	private double swirlPeriod = 500.0;
	
	public SwirlDroplet(CoordinateSystem objCoordSystem){
		this.objCoordSystem = objCoordSystem;
		this.objCoordSystem.addToViewables(this);
		this.numberDroplets = 128;
		this.dropletThetaPhasesOCS = new double[numberDroplets];
		this.swirlRadius = 4.0;
		this.sphereRadius = 0.08f;
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
		double omegaT = (t)*2*Math.PI/(1024*4);
		sp = getSwirlPattern1(omegaT);
//		sp = getSwirlPattern2(omegaT);
//		sp = getSwirlPattern3(omegaT);
//		sp.addAll(getSwirlPattern2(omegaT));
//		sp.addAll(getSwirlPattern3(omegaT));
		return sp;
	}
	
	private List<Vector3f> getSwirlPattern1(double omegaT) {
		
		List<Vector3f> allGreenArrows = new ArrayList<Vector3f>();
		Vector3f distVectInOCS = new Vector3f();
		for(int i = 0 ; i < this.numberDroplets; i++){
			float x,y,z;
			x = (float) (3*swirlRadius*Math.sin(omegaT+dropletThetaPhasesOCS[i]/4.0)*swirlRadius*Math.cos(omegaT/4.0+dropletThetaPhasesOCS[i]));
			y = (float) (4*swirlRadius*Math.cos(omegaT+dropletThetaPhasesOCS[i]));
			z = (float) (40f+5*swirlRadius*Math.sin(omegaT+dropletThetaPhasesOCS[i]/8.0)*swirlRadius*Math.cos(omegaT/8.0+dropletThetaPhasesOCS[i]));
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
			x = (float) (swirlRadius*Math.sin(omegaT+dropletThetaPhasesOCS[i])*swirlRadius*Math.cos(omegaT/2.0+dropletThetaPhasesOCS[i]));
			y = (float) (swirlRadius*Math.tan(omegaT+dropletThetaPhasesOCS[i]));
			z = (float) (swirlRadius*Math.sin(omegaT+dropletThetaPhasesOCS[i])*swirlRadius*Math.cos(omegaT/2.0+dropletThetaPhasesOCS[i]));
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
			double sin = Math.sin(omegaT+dropletThetaPhasesOCS[i]);
			double cos = Math.cos(omegaT+dropletThetaPhasesOCS[i]);
			x = (float) (2*swirlRadius*sin*cos);
			y = (float) (2*swirlRadius*cos);
			z = (float) (swirlRadius*sin*sin);
			distVectInOCS = new Vector3f(x, y, z); //green arrow in Level 1 part B
			allGreenArrows.add(distVectInOCS);
		}
		return allGreenArrows;
	}

	public double getSphereRadius() {
		return this.sphereRadius;		
	}


}
