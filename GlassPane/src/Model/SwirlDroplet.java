package Model;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.*;

public class SwirlDroplet implements ViewableModel {

	public final CoordinateSystem objCoordSystem;
	private double[] dropletThetaPhasesOCS;
	private int numberDroplets;
	private double swirlRadius; //in meters
	private double sphereRadius;
	private double swirlPeriod = 2006.0;
	
	public SwirlDroplet(CoordinateSystem objCoordSystem){
		this.objCoordSystem = objCoordSystem;
		this.objCoordSystem.addToViewables(this);
		this.numberDroplets = 32;
		this.dropletThetaPhasesOCS = new double[numberDroplets];
		this.swirlRadius = 2.0;
		this.sphereRadius = 0.05f;
		for(int i = 0; i < numberDroplets; i++){
			double thetaPhase = ((double)i)*2.0 * Math.PI / ((double)numberDroplets);
		//	this.dropletThetaPhasesOCS[i] = new Vector3d((float)(swirlRadius* Math.sin(theta)), 0, (float) (swirlRadius*Math.cos(theta)));
			this.dropletThetaPhasesOCS[i] = thetaPhase;
		}
	}
	
	public SwirlDroplet(CoordinateSystem objCoordSystem,Vector3d distFromOrigMCS, int numberDroplets,int swirlRadius, float cubeRadius){
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
	public List<Vector3d> getAllPositionVectorsInOCS(long t) {
		
		List<Vector3d> sp;
		double omegaT = (t)*2*Math.PI/(1024*3);
//		sp = getSwirlPattern1(omegaT);
//		sp = getSwirlPattern2(omegaT);
		sp = getSwirlPattern3(omegaT);
		return sp;
	}

	private List<Vector3d> getSwirlPattern2(double omegaT) {
		List<Vector3d> allGreenArrows = new ArrayList<Vector3d>();
		Vector3d distVectInOCS = new Vector3d();
		for(int i = 0 ; i < this.numberDroplets; i++){
			float x,y,z;
			x = (float) (swirlRadius*Math.sin(omegaT+dropletThetaPhasesOCS[i])*swirlRadius*Math.cos(omegaT/2.0+dropletThetaPhasesOCS[i]));
			y = (float) (swirlRadius*Math.tan(omegaT+dropletThetaPhasesOCS[i]));
			z = (float) (swirlRadius*Math.sin(omegaT+dropletThetaPhasesOCS[i])*swirlRadius*Math.cos(omegaT/2.0+dropletThetaPhasesOCS[i]));
			distVectInOCS = new Vector3d(x, y, z); //green arrow in Level 1 part B
			allGreenArrows.add(distVectInOCS);
		}
		return allGreenArrows;
	}
	
	private List<Vector3d> getSwirlPattern3(double omegaT) {
		List<Vector3d> allGreenArrows = new ArrayList<Vector3d>();
		Vector3d distVectInOCS = new Vector3d();
		for(int i = 0 ; i < this.numberDroplets; i++){
			float x,y,z;
			double sin = Math.sin(omegaT+dropletThetaPhasesOCS[i]);
			double cos = Math.cos(omegaT+dropletThetaPhasesOCS[i]);
			x = (float) (swirlRadius*sin*cos);
			y = (float) (swirlRadius*cos);
			z = (float) (swirlRadius*sin*sin);
			distVectInOCS = new Vector3d(x, y, z); //green arrow in Level 1 part B
			allGreenArrows.add(distVectInOCS);
		}
		return allGreenArrows;
	}

	private List<Vector3d> getSwirlPattern1(double omegaT) {
		
		List<Vector3d> allGreenArrows = new ArrayList<Vector3d>();
		Vector3d distVectInOCS = new Vector3d();
		for(int i = 0 ; i < this.numberDroplets; i++){
			float x,y,z;
			x = (float) (swirlRadius*Math.sin(omegaT+dropletThetaPhasesOCS[i])*swirlRadius*Math.cos(omegaT/2.0+dropletThetaPhasesOCS[i]));
			y = (float) (swirlRadius*Math.cos(omegaT+dropletThetaPhasesOCS[i]));
			z = (float) (swirlRadius*Math.sin(omegaT+dropletThetaPhasesOCS[i])*swirlRadius*Math.cos(omegaT/2.0+dropletThetaPhasesOCS[i]));
			distVectInOCS = new Vector3d(x, y, z); //green arrow in Level 1 part B
			allGreenArrows.add(distVectInOCS);
		}
		return allGreenArrows;
	}

	public double getSphereRadius() {
		return this.sphereRadius;		
	}


}
