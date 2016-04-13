package View;

import java.awt.Color;
import java.awt.CompositeContext;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JPanel;
import javax.vecmath.Vector3d;

import Controller.ControllerGlass;
import Controller.ControllerGlassInt;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

public class GlassPanel extends JPanel {
		
		Map<Vector3d,Shape> allShapesToDraw;
		int panelID;//****** MUST BE UNIQUE FOR EVERY PANEL ****
		ControllerGlassInt controller;
		boolean showFarthestFirst = false;
		int[] colorVBO = {0, 20, 0 , 20 , 50, 20}; //color Virtual Buffer Object
		
		public GlassPanel(int panelNum,ControllerGlassInt controller){
			this.panelID = panelNum;
			this.controller = controller;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			
			boolean flashRedraw = ViewGlass.flashRedraw;

			g2.setColor(ViewGlass.currentBackgroundColor);
			g2.fill(new Rectangle2D.Double(0,0,2000,2000));
//			Image img = ImageIO.read(new File("background.jpg");
//			g2.drawImage(img, x, y, observer);
			if(controller == null)
				controller = ViewGlass.controller;
			else if(flashRedraw){
				allShapesToDraw = controller.getVectorsToProjMapToDraw(panelID);		
				Iterator<Vector3d> centerOfDrawnShapes = allShapesToDraw.keySet().iterator();
				SortingMachine<Vector3d> layerOrder = new SortingMachine1L<Vector3d>(new Comparator<Vector3d>() {
							@Override
							public int compare(Vector3d v1, Vector3d v2) {
								int retVal;
								if(showFarthestFirst){
									retVal = -1;
									if(v1.length() > v2.length())
										retVal = 1;
									else if(v1.length() == v2.length())
										retVal = 0;
								} else {
									retVal = 1;
									if(v1.length() > v2.length())
										retVal = -1;
									else if(v1.length() == v2.length())
										retVal = 0;
								}
								return retVal;
							}
				});
				
				while(centerOfDrawnShapes.hasNext()){
					layerOrder.add(centerOfDrawnShapes.next());	
				}
				layerOrder.changeToExtractionMode();
				Iterator<Vector3d> farthestFirst = layerOrder.iterator();
				
				while(farthestFirst.hasNext()){
					Vector3d center = farthestFirst.next();
					
					if(allShapesToDraw.get(center) instanceof Ellipse2D.Double){
						//map <x,y,z> to <r,g,b> ... A(X) = Y 
						Vector3d colorTrnsFrmResult = new Vector3d();
						/* A(x) = 255.0/(5.0)*center.getX()+255.0/(2.0)  		-5 <= x,y <= 5
						 * A(y) = 255.0/(5.0)*center.getY()+255.0/(2.0)			22.5 <= z <= 27.5
						 * A(z) = 255.0/(5.0)*center.getZ()-255.0*22.5/(5.0)		diameter = (5.0)
						 */
						colorTrnsFrmResult.setX(255.0 - Math.abs(center.getX() - colorVBO[0]) * 255.0/colorVBO[1]);
						colorTrnsFrmResult.setY(255.0 - Math.abs(center.getY() - colorVBO[2]) * 255.0/colorVBO[3]);
						colorTrnsFrmResult.setZ(255.0 - Math.abs(center.getZ() - colorVBO[4]) * 255.0/colorVBO[5]);
						if(colorTrnsFrmResult.x >= 0 && colorTrnsFrmResult.y >= 0 && colorTrnsFrmResult.z >= 0 &&
								colorTrnsFrmResult.x < 256 && colorTrnsFrmResult.y < 256 && colorTrnsFrmResult.z < 256){
								g2.setColor(new Color((int)colorTrnsFrmResult.getX(),(int)colorTrnsFrmResult.getY(),(int)colorTrnsFrmResult.getZ()));
						}
						else if(center.getZ() < 0) // virtual image
							g2.setColor(Color.BLACK);
						else //just far into picture
							g2.setColor(Color.WHITE);
						g2.fill(allShapesToDraw.get(center));
					}
					else{
						g2.setColor(Color.RED);
						g2.fill(allShapesToDraw.get(center));
					}
				}
				
				//TODO ....get shape projections to draw

				//
				flashRedraw = false;
			}
		}
		
		public int getPanelID(){
			return panelID;
		}
}