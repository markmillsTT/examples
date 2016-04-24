package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;

import Controller.ControllerGlassInt;
import Helpers.AllActionListeners;
import Helpers.Vector3f;

public class GlassPanel extends JPanel {
		
		Map<Vector3f,Shape> allShapesToDraw;
		int panelID;//****** MUST BE UNIQUE FOR EVERY PANEL ****
		ControllerGlassInt controller;
		int framesToKeepPaint = 5;
		boolean showFarthestFirst = false;
		
		//Shape Colors
		float[] colorVBO = {0, 255, 200 , 255 , 0, 255}; //color Virtual Buffer Object
		float colorVBOXVel = 1;
		float colorVBOYVel = -1;
		float colorVBOZVel = 1;
		
		//Background Colors
	    float backgroundColorRedVel = 1f;
		float backgroundColorGreenVel = -1f;
		float backgroundColorBlueVel = 0.5f;
		
//		Graphics2D globalGraphics;
				
		public GlassPanel(int panelNum,ControllerGlassInt controller){
			this.panelID = panelNum;
			this.controller = controller;
			AllActionListeners listener = new AllActionListeners(){
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getID() == KeyEvent.VK_P){
						controller.toggleToParrellelView(panelID);
					}
				}
			};
			this.addKeyListener(listener);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			
			Graphics2D g2 = (Graphics2D)g;
			
			updateColorVBO();
			updateBackgroundColor();
//			if(globalGraphics == null){
//				globalGraphics = g2;
//			} else {
//				g2 = globalGraphics;
//			}
			
			boolean flashRedraw = ViewGlass.flashRedraw;

			g2.setColor(ViewGlass.currentBackgroundColor);
			g2.fill(new Rectangle2D.Double(0,0,2000,2000));
//			Image img = ImageIO.read(new File("background.jpg");
//			g2.drawImage(img, x, y, observer);
			if(controller == null)
				controller = ViewGlass.controller;
			else if(flashRedraw){
				allShapesToDraw = controller.getVectorsToProjMapToDraw(panelID);		
				Iterator<Vector3f> centerOfDrawnShapes = allShapesToDraw.keySet().iterator();
//				SortingMachine<Vector3f> layerOrder = new SortingMachine1L<Vector3f>(new Comparator<Vector3f>() {
//							@Override
//							public int compare(Vector3f v1, Vector3f v2) {
//								int retVal;
//								if(showFarthestFirst){
//									retVal = -1;
//									if(v1.length() > v2.length())
//										retVal = 1;
//									else if(v1.length() == v2.length())
//										retVal = 0;
//								} else {
//									retVal = 1;
//									if(v1.length() > v2.length())
//										retVal = -1;
//									else if(v1.length() == v2.length())
//										retVal = 0;
//								}
//								return retVal;
//							}
//				});
//				
//				while(centerOfDrawnShapes.hasNext()){
//					layerOrder.add(centerOfDrawnShapes.next());	
//				}
//				layerOrder.changeToExtractionMode();
//				Iterator<Vector3f> farthestFirst = layerOrder.iterator();
				
//				while(farthestFirst.hasNext()){
//					Vector3f center = farthestFirst.next();
//					
				while(centerOfDrawnShapes.hasNext()){
					Vector3f center = centerOfDrawnShapes.next();
					
					if(allShapesToDraw.get(center) instanceof Ellipse2D.Double){
						//map <x,y,z> to <r,g,b> ... A(X) = Y 
						Vector3f colorTrnsFrmResult = new Vector3f();
						/* A(x) = 255.0/(5.0)*center.x()+255.0/(2.0)  		-5 <= x,y <= 5
						 * A(y) = 255.0/(5.0)*center.y()+255.0/(2.0)			22.5 <= z <= 27.5
						 * A(z) = 255.0/(5.0)*center.z()-255.0*22.5/(5.0)		diameter = (5.0)
						 */
						colorTrnsFrmResult.setX((float)(255.0 - Math.abs(center.x() - colorVBO[0]) * 255.0/colorVBO[1]));
						colorTrnsFrmResult.setY((float)(255.0 - Math.abs(center.y() - colorVBO[2]) * 255.0/colorVBO[3]));
						colorTrnsFrmResult.setZ((float)(255.0 - Math.abs(center.z() - colorVBO[4]) * 255.0/colorVBO[5]));
						if(colorTrnsFrmResult.x() >= 0 && colorTrnsFrmResult.y() >= 0 && colorTrnsFrmResult.z() >= 0 &&
								colorTrnsFrmResult.x() < 256 && colorTrnsFrmResult.y() < 256 && colorTrnsFrmResult.z() < 256){
								g2.setColor(new Color((int)colorTrnsFrmResult.x(),(int)colorTrnsFrmResult.y(),(int)colorTrnsFrmResult.z()));
						}
						else if(center.z() < 0) // virtual image
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
		
		private void updateBackgroundColor() {
			
			float red = ViewGlass.currentBackgroundColor.getRed();
			float green = ViewGlass.currentBackgroundColor.getGreen();
			float blue = ViewGlass.currentBackgroundColor.getBlue();
			
			if(backgroundColorRedVel > 0 && red >= 255 ){
				backgroundColorRedVel = -1 * backgroundColorRedVel;
				red = 255;
			} else if(backgroundColorRedVel < 0 && red <= 15 ){
				backgroundColorRedVel = -1 * backgroundColorRedVel;
				red = 10;
			}
			
			if(backgroundColorGreenVel > 0 && green >= 255 ){
				backgroundColorGreenVel = -1 * backgroundColorGreenVel;
				green = 255;
			} else if(backgroundColorGreenVel < 0 && green <= 15 ){
				backgroundColorGreenVel = -1 * backgroundColorGreenVel;
				green = 10;
			}
			
			if(backgroundColorBlueVel > 0 && blue >= 255 ){
				backgroundColorBlueVel = -1 * backgroundColorBlueVel;
				blue = 255;
			} else if(backgroundColorBlueVel < 0 && blue <= 15 ){
				backgroundColorBlueVel = -1 * backgroundColorBlueVel;
				blue = 10;
			}
			
			red = red + backgroundColorRedVel;
			green = green + backgroundColorGreenVel;
			blue = blue + backgroundColorBlueVel;
			
			if(red > 255.0f){
				red = 255.0f;
			} else if (red < 0){
				red = 0;
			}
			
			if(green > 255.0f){
				green = 255.0f;
			} else if (green < 0){
				green = 0;
			}
			
			if(blue > 255.0f){
				blue = 255.0f;
			} else if (blue < 0){
				blue = 0;
			}
			
			ViewGlass.currentBackgroundColor = new Color(red * 1.0f/255.0f, green * 1.0f/255.0f, blue * 1.0f/255.0f);
			
		}

		private void updateColorVBO() {
			
			if(colorVBOXVel > 0 && colorVBO[0] >= 255 ){
				colorVBOXVel = -1 * colorVBOXVel;
				colorVBO[0] = 255;
			} else if(colorVBOXVel < 0 && colorVBO[0] <= 15 ){
				colorVBOXVel = -1 * colorVBOXVel;
				colorVBO[0] = 10;
			}
			
			if(colorVBOYVel > 0 && colorVBO[2] >= 255 ){
				colorVBOYVel = -1 * colorVBOYVel;
				colorVBO[2] = 255;
			} else if(colorVBOYVel < 0 && colorVBO[2] <= 15 ){
				colorVBOYVel = -1 * colorVBOYVel;
				colorVBO[2] = 10;
			}
			
			if(colorVBOZVel > 0 && colorVBO[4] >= 255 ){
				colorVBOZVel = -1 * colorVBOZVel;
				colorVBO[4] = 255;
			} else if(colorVBOZVel < 0 && colorVBO[4] <= 15 ){
				colorVBOZVel = -1 * colorVBOZVel;
				colorVBO[4] = 10;
			}
			
			colorVBO[0] = colorVBO[0] + colorVBOXVel;
			colorVBO[2] = colorVBO[2] + colorVBOYVel;
			colorVBO[4] = colorVBO[4] + colorVBOZVel;
		}

		public int getPanelID(){
			return panelID;
		}
}