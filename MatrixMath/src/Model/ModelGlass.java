package Model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sun.javafx.geom.Matrix3f;

import Helpers.Vector3f;


/**
 * Model for Glass Pane 
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public final class ModelGlass implements ModelGlassInt {
	
	Map<Integer,Map<Vector3f,ViewableObject>> objectLocations = new HashMap<Integer,Map<Vector3f,ViewableObject>>();
    private long startTime;
    float spacer;
    /****
	 * 
					GlassPaneRun.java --- serialVersionUID = 0L;
					ControllerGlass.java --- serialVersionUID = 1L;
					ModelGlass.java --- serialVersionUID = 2L;
					ViewGlass.java --- serialVersionUID = 3L; 
					
	 */
	private static final long serialVersionUID = 2L;

	public ModelGlass() {
		
    }
	
	public void loadMotionSystem(int sceneNum,float omegaT) {
		
        switch(sceneNum) {
        
          case 0:
           motionSystem0(sceneNum,omegaT);
            break;
            
              case 1:
                motionSystem1(sceneNum,omegaT);
                  break;
                  
                  case 2:
                motionSystem2(sceneNum,omegaT);
              break;
                        
            case 3:
           motionSystem3(sceneNum,omegaT);
          break;
			
        }
        
	}
	
    public void motionSystem0(int sceneNum,float omegaT) {
		
	}
	
    public void motionSystem1(int sceneNum,float omegaT) {
		if(!objectLocations.containsKey(sceneNum)){
			return;
		}
		synchronized(this) {
			Map<Vector3f, ViewableObject> sceneMapHold = new HashMap<Vector3f, ViewableObject>();
			Map<Vector3f, ViewableObject> currentSceneMap = objectLocations.remove(sceneNum);
			Iterator<Vector3f> iterator = currentSceneMap.keySet().iterator();
			while(iterator.hasNext()){
//				long t = (long) ((System.currentTimeMillis()-this.startTime)/1000f);
				Vector3f currentLocation = iterator.next();
				ViewableObject vo = currentSceneMap.get(currentLocation);
				
//				float mult = 0.05f;
				Vector3f vel = new Vector3f(.005f,-.005f,-.005f);
						
				if(Math.abs((float)currentLocation.x()) > .5){
					vel.setX( (float) vel.x()*-1.1f);
				}
				if(Math.abs((float)currentLocation.y()) > .5){
					vel.setY( (float) vel.y()*-1.1f);
				}
				if(Math.abs((float)currentLocation.z()) - 20 > 3){
					vel.setZ( (float) vel.y()*-1.1f);
				}
				currentLocation.setX((float) ((float)currentLocation.x()+(float)vel.x()));
				currentLocation.setY((float) ((float)currentLocation.y()+(float)vel.y()));
				currentLocation.setZ((float) ((float)currentLocation.z()+(float)vel.z()));
				
				sceneMapHold.put(currentLocation, vo);
			}
			
			objectLocations.put(sceneNum, sceneMapHold);
		}
	}
	
    public void motionSystem2(int sceneNum,float omegaT) {
		if(!objectLocations.containsKey(sceneNum)){
			return;
		}
		synchronized(this) {
			Map<Vector3f, ViewableObject> sceneMapHold = new HashMap<Vector3f, ViewableObject>();
			Map<Vector3f, ViewableObject> currentSceneMap = objectLocations.remove(sceneNum);
			Iterator<Vector3f> iterator = currentSceneMap.keySet().iterator();
			while(iterator.hasNext()){
//				float t = ((System.currentTimeMillis()-this.startTime)/10000f);
				Vector3f currentLocation = iterator.next();
				ViewableObject vo = currentSceneMap.get(currentLocation);
				
//				float mult = 0.5f;
				Vector3f vel = new Vector3f(.02f,.01f,-.09f);
				
//				if(currentLocation.getZ()<-25){
//					currentLocation.setZ(25);
//				}
				
				currentLocation.setX( (float) currentLocation.x()+ (float)vel.x());
				currentLocation.setY( (float) currentLocation.y()+ (float)vel.y());
				currentLocation.setZ( (float) currentLocation.z()+ (float)vel.z());
				
				sceneMapHold.put(currentLocation, vo);
			}
			
			objectLocations.put(sceneNum, sceneMapHold);
		}
	}

    public void motionSystem3(int sceneNum,float omegaT) {
		/*
		if(!objectLocations.containsKey(sceneNum)){
			return;
		}
		synchronized(this) {
			Map<Vector3f, ViewableObject> sceneMapHold1 = new HashMap<Vector3f, ViewableObject>();
			Map<Vector3f, ViewableObject> sceneMapHold2 = new HashMap<Vector3f, ViewableObject>();
			Map<Vector3f, ViewableObject> currentSceneMap = objectLocations.remove(sceneNum);
			Iterator<Vector3f> iterator1 = currentSceneMap.keySet().iterator();
			
			Vector3f sphereLocation = new Vector3f(););
			
			while(iterator1.hasNext()){
				float t = ((System.currentTimeMillis()-this.startTime)/10000f);
				Vector3f currentLocation = iterator1.next();
				ViewableObject vo = currentSceneMap.get(currentLocation);
				
				float mult = 0.5f;
				Vector3f vel = new Vector3f();
						(float) (.00), 
						(float) (.00), 
						(float) (-.09));
//				if(currentLocation.getZ()<-25){
//					currentLocation.setZ(25);
//				}
				
				currentLocation.setX((float) (currentLocation.getX()+ vel.getX()));
				currentLocation.setY((float) (currentLocation.getY()+ vel.getY()));
				currentLocation.setZ((float) (currentLocation.getZ()+ vel.getZ()));
				
				if(vo instanceof Sphere){
					//first objects in scene 3 are sphere so variable "radius" never null
					sphereLocation = new Vector3f();currentLocation.getX(),currentLocation.getY(),currentLocation.getZ());;
				}
				sceneMapHold1.put(currentLocation, vo);
			}

			Iterator<Vector3f> iterator2 = sceneMapHold1.keySet().iterator();
			while(iterator2.hasNext()){
				float t = ((System.currentTimeMillis()-this.startTime)/10000f);
				Vector3f currentLocation = iterator2.next();
				ViewableObject vo = currentSceneMap.get(currentLocation);
				
				currentLocation.setX(currentLocation.getX()-sphereLocation.getX());
				currentLocation.setY(currentLocation.getY()-sphereLocation.getY());
				currentLocation.setZ(currentLocation.getZ()-sphereLocation.getZ());		
				
				float theta = (float) (Math.PI/50.0);
				float phi = (float) (theta/2.0);
				float x = currentLocation.getX(), y = currentLocation.getY(), z = currentLocation.getZ();
				double cosPhi = Math.cos(phi),cosTheta = Math.cos(theta),sinPhi = Math.sin(phi),sinTheta = Math.sin(theta);
				currentLocation.setX((float) (x*cosPhi*cosTheta-y*sinPhi-z*cosPhi*sinTheta));
				currentLocation.setY((float) (x*cosTheta*sinPhi+y*cosPhi-z*sinPhi*sinTheta));
				currentLocation.setZ((float) (x*sinTheta+z*cosTheta));
				
//				currentLocation.setX((float) (x*cosTheta-y*sinTheta));
//				currentLocation.setY((float) (x*sinTheta+y*cosTheta));
//				
//				currentLocation.setY((float) (y*cosPhi-z*sinPhi));
//				currentLocation.setZ((float) (y*sinPhi+z*cosPhi));

				currentLocation.setX(currentLocation.getX()+sphereLocation.getX());
				currentLocation.setY(currentLocation.getY()+sphereLocation.getY());
				currentLocation.setZ(currentLocation.getZ()+sphereLocation.getZ());
				
				sceneMapHold2.put(currentLocation, vo);
			}
			
			
			objectLocations.put(sceneNum, sceneMapHold2);
		}
		*/
	}

	@Override 
    public void loadModelScene(int sceneNum) {
		if(sceneNum == 1){
			loadModelScene1();
		} else if(sceneNum == 2){
			loadModelScene2();
		} else if(sceneNum == 3){
			loadModelScene3();
		} else if(sceneNum == 4){
			loadModelScene4();
		}
	}

	private void loadModelScene1() {
		
		int count = 0; // goes up by 1 each iteration over i, j, and k;
		for(int i = -5; i < 5 ; i++){
			for(int k = -5; k < 5; k++){
				for(int z = -5; z < 5; z++){
					//spacer = (float) (6.0f + 5.0f*Math.cos((System.currentTimeMillis()-this.startTime)/10000));
					Cube cube1 = new Cube(10.0f);
					//
					Vector3f dist1 = new Vector3f((float)i, (float)k, (float)z);

					loadObjectLocation(1, dist1 , cube1, count);
					count++;
				}
			}
		}
	}

	private void loadModelScene2() {
		
		int count = 0;
//		for(int i = -2; i < 4 ; i++){
			for(int k = -1; k < 1; k++){
				for(int z = -1; z < 1; z++){
					spacer = 100.0f;
					Sphere sphere = new Sphere(5.0f,8,16);
					Vector3f dist1 = new Vector3f((float)8*k,(float)8*z,(float)-40f);

					loadObjectLocation(2, dist1 , sphere, count);
					count++;
				}
			}
	//	}
				
				
	}
	
	private void loadModelScene3() {
		int count = 0;
		float sphereRadius = 5.0f;
		int zStart = -5;
		
		Sphere sphere1 = new Sphere(sphereRadius,8,16);
		Vector3f dist1 = new Vector3f(0f,0,(float)zStart);
		
		loadObjectLocation(3, dist1 , sphere1, count);
		count++;
		
		int numSections = 8;
		for(double theta = 0; theta < 2*Math.PI; theta+=2*Math.PI/numSections){
			for(double phi = 0; phi <= Math.PI; phi+=2*Math.PI/numSections){
				float radius = sphereRadius;
				
				Cube cube1 = new Cube(sphereRadius/8);
				dist1 = new Vector3f( ((float) (radius*Math.sin(phi)*Math.cos(theta)) ),
						(float)(radius*Math.cos(phi)),
						(float)(radius*Math.sin(phi)*Math.sin(theta) + zStart)
						);
				
				loadObjectLocation(3, dist1 , cube1, count);
				count++;
			}
		}
	}
	
	private void loadModelScene4() {
		Cube cube1 = new Cube(1.0f);
		Vector3f dist1 = new Vector3f(0f,0f,-5f);

		loadObjectLocation(4, dist1 , cube1, 0);
	}
	
	@Override 
    public void startModel(){
    	this.startTime = System.currentTimeMillis();
    }
	
	@Override 
    public long getStartTime(){
    	return this.startTime;
    }

    public Matrix3f getIdentityMatrix(){
    	Matrix3f matrix = new Matrix3f();
    	return matrix;
    }
    
	@Override
    public void loadObjectLocation(int sceneNum, Vector3f dist1, ViewableObject vo, int count){
//    	Matrix4x4 matrix = getIdentityMatrix();
//    	matrix.setAt00(Vector3f.getX());
//    	matrix.setAt11(Vector3f.getY());
//    	matrix.setAt22(Vector3f.getZ());
//    	matrix.setAt33(count);
    	if(!objectLocations.containsKey(sceneNum))
    		objectLocations.put(sceneNum, new HashMap<Vector3f,ViewableObject>());
    	objectLocations.get(sceneNum).put(dist1, vo);
    }
    
	@Override
    public Map<Vector3f,ViewableObject> getObjectLocationsForScene(int sceneNum){
    	return objectLocations.get(sceneNum);
    }

	@Override
	public void loadORreloadAllData() {
		loadModelScene(1);
		loadModelScene(2);
		loadModelScene(3);
		loadModelScene(4);
	}
    

}