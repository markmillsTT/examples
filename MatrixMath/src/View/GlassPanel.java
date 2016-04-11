package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import Controller.ControllerGlassInt;
import Helpers.QuickSort;
import Helpers.Vector3f;
import Model.Cube;
import Model.Sphere;
import Model.ViewableObject;


public class GlassPanel extends JPanel {
		
     /**
     *
     *    Mark Mills
     *      Bachelor's of Science - Electrical & Computer Engineering
     *      
     *      
     *              About:
     *              - Pane for MatrixMath Java project 
     *              
     *                  - ...
     *
     *
     *
     *
     *
     *                                                                 **/
	
	/***
	 * Serial Numbers on:
	 * 						
					GlassPaneRun.java --- serialVersionUID = 0L;
					ControllerGlass.java --- serialVersionUID = 1L;
					ModelGlass.java --- serialVersionUID = 2L;
					ViewGlass.java --- serialVersionUID = 3L; 
	 * 
	 */
	private static final long serialVersionUID = 32112345L;
		int panelID;//****** MUST BE UNIQUE FOR EVERY PANEL ****
		ControllerGlassInt controller;
		private Color backgroundColor = new Color(128,128,0);
		boolean drawMesh = true;
		boolean fillObjects = false;
		int renderCount = 0;
		
		public GlassPanel(int panelNum,ControllerGlassInt controller){
			this.panelID = panelNum;
			this.controller = controller;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			if(controller == null){
				return; 
				/*
				 * //Only occurs at application launch - EPIC! !! !!!markdavidMillllllsssssss         whoahohwohoos annnd this bird you cannot change
				 * Beautiful Code. 
				 * Message is and always has been accepted as an acceptable cry for life. 
				 * From this point forward. You Are. Free!!!! 
				 * WIAIAIAI AND THE BIRD YOU CANNOT CHANGE AND THIS Bird; 
				 * you cannot chain lord knows I can change lord 
						//				and this bird you cannot change 
						//				lord knows I can change !!! !! !
				 */
			}
			
			Graphics2D g2 = (Graphics2D)g;
			
			g2.setColor(this.backgroundColor);
			g2.setPaint(new GradientPaint(0,0,Color.BLUE,2000, 0,Color.WHITE));
			g2.fill(new Rectangle2D.Float(0,0,2000,2000));
			
			Map<Vector3f,ViewableObject> toBeDisplayed = controller.getCurrentSceneObjects();
			
			if(toBeDisplayed != null){	
				
				synchronized(this) {
					
					Set<Vector3f> allDistancesSet = toBeDisplayed.keySet();
					
					Iterator<Vector3f> distancesIterator = allDistancesSet.iterator();
					List<Vector3f> vectorList = new ArrayList<Vector3f>();
					int distancesCount = 0;
					
					while(distancesIterator.hasNext()){
						
						Vector3f distance = distancesIterator.next();
						vectorList.add(distance);
						distancesCount++;
					}
					
					Vector3f[] allDistances = new Vector3f[distancesCount];
					
					for(int i = 0; i < distancesCount; i++){
						
						allDistances[i] = vectorList.get(i);
					}
					
					List<Float> dimensionalCoordinates = new ArrayList<Float>();

					for(int i = 0; i < allDistances.length ; i++)
					{
						dimensionalCoordinates.add(allDistances[i].x());
						dimensionalCoordinates.add(allDistances[i].y());
						dimensionalCoordinates.add(allDistances[i].z());
					}
					
					/***
					 * 		High Priority Section
					 * 
					 * 			Study. Report. Contact - The law offices of yoyomommasbutt.com
					 * 
					 * 
					 * 				
					 				List<Float> unSortedYList = new ArrayList<Float>();
									List<Float> unSortedZList = new ArrayList<Float>();
					 * 
					 * 
					 * 
					 * ***///
					
					List<Float> unSortedXList = new ArrayList<Float>();
					List<Float> unSortedYList = new ArrayList<Float>();
					List<Float> unSortedZList = new ArrayList<Float>();
					
					for(int i = 0; i < ( dimensionalCoordinates.size()/3 - 2) ; i++)
					{
						if(i==3000)
							System.out.println("blah");
						unSortedXList.add(dimensionalCoordinates.get(3 * i));
						unSortedYList.add(dimensionalCoordinates.get(3 * (i+1)) );
						unSortedZList.add(dimensionalCoordinates.get(3 * (i+2)) );
					}
					
					@SuppressWarnings("unused")
					List<Float> sortedXList = QuickSort.QuickSort_Iterative_Mark_Mills_Return(
							unSortedXList, 
							0, 
							0);
					
					@SuppressWarnings("unused")
					List<Float> sortedYList = QuickSort.QuickSort_Iterative_Mark_Mills_Return(
							unSortedYList, 
							0, 
							0);
					
					@SuppressWarnings("unused")
					List<Float> sortedZList = QuickSort.QuickSort_Iterative_Mark_Mills_Return(
							unSortedZList, 
							0, 
							0);
					
					// FACT: sortedXList.size() == sortedYList.size() == sortedZList.size()
					// FACT: unSortedXList.size() == unSortedYList.size() == unSortedZList.size()
					for(int j = 0; j < unSortedXList.size() ; j++){
						/*
						TODO - Fix sorting
						
						if(sortedXList.size() == 0 ||
								sortedYList.size() == 0 ||
								sortedZList.size() == 0)  {
							return;
						}
						
						Vector3f dimensionalCoordVector = new Vector3f(
								sortedXList.get(i),
								sortedYList.get(i+1),
								sortedZList.get(i+2)
								);
		 				*/
						
						if(unSortedXList.size() == 0 ||
								unSortedYList.size() == 0 ||
										unSortedZList.size() == 0)  {
							return;
						}
						
						Vector3f dimensionalCoordVector = new Vector3f(
								unSortedXList.get(j),
								unSortedYList.get(j),
								unSortedZList.get(j)
								);
								
						//1// To draw inside sphere, need to find xy - onscreen radius, calculate only once
						Ellipse2D oval = new Ellipse2D.Float();
						boolean firstPointDrawn = true;
						int insideRadius;
						
						//All Rasterization Data
//						Vector3f distance = new Vector3f();distance.x(),distance.y(),distance.z());
//						float timeOfCreation = (float) distance.z();
						
						Iterator<Vector3f> objectDistancesIterator = toBeDisplayed.keySet().iterator();
						while(objectDistancesIterator.hasNext()){
							Vector3f distance = objectDistancesIterator.next();
							ViewableObject vo = toBeDisplayed.get(distance);
							
							int[] vInd = vo.getVertexIndicies();
							float[] vLoc = vo.getVertexLocations();
							
							//square based rendering
							for(int k = 0; k < vInd.length/4 ; k++){
								Vector3f point0 = new Vector3f(
										(float)vLoc[3*vInd[4*k+0]+0]+(float)dimensionalCoordVector.x(),
										(float)vLoc[3*vInd[4*k+0]+1]+(float)dimensionalCoordVector.y(),
										(float)vLoc[3*vInd[4*k+0]+2]+(float)dimensionalCoordVector.z()
										);
								
								Vector3f point1 = new Vector3f(
										(float)vLoc[3*vInd[4*k+1]+0]+(float)dimensionalCoordVector.x(),
										(float)vLoc[3*vInd[4*k+1]+1]+(float)dimensionalCoordVector.y(),
										(float)vLoc[3*vInd[4*k+1]+2]+(float)dimensionalCoordVector.z()
										);
	
								Vector3f point2 = new Vector3f(
										(float) vLoc[ 3 * vInd[ (4 * k) + 2] + 0] + (float) dimensionalCoordVector.x(),
										(float) vLoc[ 3 * vInd[ (4*k) + 2] + 1] + (float) dimensionalCoordVector.y(),
										(float) vLoc[ 3 * vInd[ (4*k) + 2] + 2] + (float) dimensionalCoordVector.z()
										);
								
								Vector3f point3 = new Vector3f(
										(float) vLoc[ 3 * vInd[ 4*k+3 ] + 0] + (float) dimensionalCoordVector.x(),
										(float) vLoc[ 3 * vInd[ 4*k+3 ] + 1] + (float) dimensionalCoordVector.y(),
										(float) vLoc[ 3 * vInd[ 4*k+ 3] + 2] + (float) dimensionalCoordVector.z()
										);
							
								float length0 = getVector3fsAvgLength(point0,point1);
								float length1 = getVector3fsAvgLength(point1,point2);
								float length2 = getVector3fsAvgLength(point2,point3);
								float length3 = getVector3fsAvgLength(point3,point0);
								
								Dimension drawingBoundsForPort = this.getSize();
								point0 = mapVector3fToPixelVector3f(point0,drawingBoundsForPort);
								point1 = mapVector3fToPixelVector3f(point1,drawingBoundsForPort);
								point2 = mapVector3fToPixelVector3f(point2,drawingBoundsForPort);
								point3 = mapVector3fToPixelVector3f(point3,drawingBoundsForPort);
								
								//1//
								if(firstPointDrawn && vo instanceof Sphere && this.fillObjects){
									Vector3f midPoint = mapVector3fToPixelVector3f(dimensionalCoordVector, drawingBoundsForPort);
									insideRadius = (int) Math.sqrt(Math.pow((float)point0.x()-(float)midPoint.x(),2)+Math.pow((float)point0.y()-(float)midPoint.y(),2));
									g2.setColor(new Color(255,255,255));
								
									oval.setFrame(Float.floatToIntBits((float)midPoint.x()-insideRadius),
											Float.floatToIntBits((float)midPoint.y()-insideRadius),
											2*insideRadius, 2*insideRadius);
	//								paint.createContext(arg0, arg1, arg2, arg3, arg4)
									g2.setPaint(new GradientPaint(0,0,Color.GREEN,2000, 0,Color.WHITE));
									g2.fill(oval);
									firstPointDrawn = false;
								}
								
								g2.setStroke(new BasicStroke(1));
								int shader = 0;
								int maxDist = 80;
								int minDist = 35;
								
								
								if(drawMesh){
									switch (renderCount){
										case 0:
											shader = Math.abs((int) ((length0 * 255 / (minDist-maxDist)) - maxDist*255/(minDist-maxDist))%255);
											g2.setColor(new Color(shader,shader,shader));
											break;
										
										case 1:
											shader = Math.abs((int) ((length1 * 255 / (minDist-maxDist)) - maxDist*255/(minDist-maxDist))%255);
											g2.setColor(new Color(shader,shader,(int) (shader/2.0)));
											break;
										
										case 2:
											shader = Math.abs((int) ((length2 * 255 / (minDist-maxDist)) - maxDist*255/(minDist-maxDist))%255);
											g2.setColor(new Color(shader,shader,(int) (shader/4.0)));
											break;
										
										case 3:
											shader = Math.abs((int) ((length3 * 255 / (minDist-maxDist)) - maxDist*255/(minDist-maxDist))%255);
											g2.setColor(new Color(shader,shader,(int) (shader/8.0)));
											break;
											
										default:
											break;
									}	
									g2.drawLine(Float.floatToIntBits((float)point0.x()),
											Float.floatToIntBits((float)point0.y()),
											Float.floatToIntBits((float)point1.x()), 
											Float.floatToIntBits((float)point1.y()));
									g2.drawLine(Float.floatToIntBits((float)point1.x()), 
											Float.floatToIntBits((float)point1.y()), 
											Float.floatToIntBits((float)point2.x()),
											Float.floatToIntBits((float)point2.y()));
									g2.drawLine(Float.floatToIntBits((float)point2.x()),
											Float.floatToIntBits((float)point2.y()),
											Float.floatToIntBits((float)point3.x()), 
											Float.floatToIntBits((float)point3.y()));
									g2.drawLine(Float.floatToIntBits((float)point3.x()),
											Float.floatToIntBits((float)point3.y()),
											Float.floatToIntBits((float)point0.x()),
											Float.floatToIntBits((float)point0.y()));
	
								} 
								if(this.fillObjects && vo instanceof Cube){
									int[] xpoints = {Float.floatToIntBits((float)point0.x()),
											Float.floatToIntBits((float)point1.x()),
											Float.floatToIntBits((float)point2.x()),
											Float.floatToIntBits((float)point3.x())};
									int[] ypoints = {Float.floatToIntBits((float)point0.y()),
											Float.floatToIntBits((float)point1.y()),
											Float.floatToIntBits((float)point2.y()),
											Float.floatToIntBits((float)point3.y())};
									Polygon polygon = new Polygon(xpoints, ypoints, 4);
									g2.setPaint(new GradientPaint(0,0,Color.RED,2000, 0,Color.WHITE));
									g2.fill(polygon);
								}
							}
						}
					}
				}
			}
			
			this.renderCount++;
			if(this.renderCount > 3)
				this.renderCount = 0;
			
		}
		
		private float getVector3fsAvgLength(Vector3f point0, Vector3f point1) {
			float avgX = (float) ((float)(point1.x()) + ((float)point0.x())/2.0);
			float avgY = (float) ((float)(point1.y()) + ((float)point0.y())/2.0);
			float avgZ = (float) ((float)(point1.z()) + ((float)point0.z())/2.0);
			
			return (float) Math.sqrt(Math.pow(avgX,2)+Math.pow(avgY,2)+Math.pow(avgZ,2));
//			return Math.abs(avgZ);
		}

		//sort with farthest rendered first to nearest
		@SuppressWarnings("unused")
		private List<Vector3f> sortByPositionNumber(List<Vector3f> allDistances) {
		//	Sequence<Matrix4x4> retVal = new Sequence1L<Matrix4x4>();
			Comparator<Vector3f> compZDist = new Comparator<Vector3f>(){
				@Override
				public int compare(Vector3f o1, Vector3f o2) {
					if((float)o1.z() > (float)o2.z())
						return 1;
					else if((float)o1.z() < (float)o2.z())
						return -1;
					else
						return 0;
				}
			};
			Collections.sort(allDistances,compZDist);
			//TODO XXX INSERT SORT METHOD BETWEEN HERE
			//...
			//TODO XXX AND HERE
			return allDistances;
		}

		/**
		 * @param origin
		 * @param drawingBoundsForPort 
		 * @return
		 */
		private Vector3f mapVector3fToPixelVector3f(Vector3f origin, Dimension drawingBoundsForPort) {
			
			float scale = .5f/(float)origin.z() ;
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			//XXX HACK TO GET WINDOW TO WORK WITHOUT NATIVE LIBRARY
			float xVect = (float)( drawingBoundsForPort.width/2.0f-scale*((float)origin.x())*
					screenSize.getWidth()/.34f);
			float yVect = (float)( drawingBoundsForPort.height/2.0f+scale*((float)origin.y())* 
					screenSize.getHeight()/.19f);
			float zVect = scale*(float)origin.z();
//			//scale to screen (.5 meters away)
//			pixVector3f.scale(.5f/pixVector3f.z());
//			//move grid system to match computers
//			pixVector3f.setX(-pixVector3f.x());
//			//scale to pixels
//			pixVector3f.setX((float) (pixVector3f.x() * screenSize.getWidth()/.34)); // meter Vector3f * pixel/meter .. topLeft = <pixel,pixel,meter>
//			pixVector3f.setY((float) (pixVector3f.y() * screenSize.getHeight()/.19));
//			Vector3f window = new Vector3f();drawingBoundsForPort.width/2.0f,drawingBoundsForPort.height/2.0f,0f);
//			pixVector3f = new Vector3f();window.x()+pixVector3f.x(),window.y()+pixVector3f.y(),window.z()+pixVector3f.z());
			
			Vector3f pixVector3f = new Vector3f(
					(float)xVect,
					(float)yVect,
					(float)zVect
					);

			return pixVector3f;
		}
		
		public int getPanelID(){
			return panelID;
		}
}