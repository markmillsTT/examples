package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
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
		GlassPanelColorPackage colorPkg = null;
		
		//For drawing images and morphing them
		float count = 0f;
		
//		Graphics2D globalGraphics;
			
		public GlassPanel(int panelNum, ControllerGlassInt controller){
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
		
		public GlassPanel(int panelNum, ControllerGlassInt controller, GlassPanelColorPackage colorPkg){
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
			this.colorPkg = colorPkg;
			this.addKeyListener(listener);
		}
		
		public void setColorPackage(GlassPanelColorPackage colorPkg){
			this.colorPkg = colorPkg;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			
			Graphics2D g2 = (Graphics2D)g;
			
			updateColorVBO();
			updateBackgroundColor();
			
			boolean flashRedraw = ViewGlass.flashRedraw;

//			Image img;
//			try {
//				img = ImageIO.read(new File("res/MarkLauren.jpg"));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return;
//			}
			ImageObserver observer = new ImageObserver() {
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			
//			g2.drawImage(img, -100, 0, observer);
//			g2.drawImage(img,
////				(int) (center.x() - allShapesToDraw.get(center).getBounds2D().getX()/2 ),
////				(int) (center.y() - allShapesToDraw.get(center).getBounds2D().getY()/2 + 150 ),
////				(int) (center.x() + allShapesToDraw.get(center).getBounds2D().getX()/2 ),
////				(int) (center.y() + allShapesToDraw.get(center).getBounds2D().getY()/2 + 150),
//				(int) (-1000 + count),
//				(int) (-100 + count),
//				(int) (img.getWidth(observer) - count),
//				(int) (img.getHeight(observer) - count),
//				0, //sx1
//				0, //sy1
//				img.getWidth(observer), //sx2
//				img.getHeight(observer), //sy2
//				g2.getColor(),
//				observer);
//			
//			g2.drawImage(img,
////					(int) (center.x() - allShapesToDraw.get(center).getBounds2D().getX()/2 ),
////					(int) (center.y() - allShapesToDraw.get(center).getBounds2D().getY()/2 + 150 ),
////					(int) (center.x() + allShapesToDraw.get(center).getBounds2D().getX()/2 ),
////					(int) (center.y() + allShapesToDraw.get(center).getBounds2D().getY()/2 + 150),
//					(int) (-100 + count),
//					(int) (-100 + .5*count),
//					(int) (img.getWidth(observer) - 2*count),
//					(int) (img.getHeight(observer) - 4*count),
//					0, //sx1
//					0, //sy1
//					img.getWidth(observer), //sx2
//					img.getHeight(observer), //sy2
//					g2.getColor(),
//					observer);
			
//			g2.drawImage(img, -100, 0, observer);
			
//			g2.setColor(colorPkg.backgroundColor);
//			
//			int[] x1 = {
//					(int)(-100 + count),
//					(int)(-100 + count),
//					(int)( -2 * count),
//					(int)( -2 * count)
//					};
//			int[] y1 = {
//					(int)(-100 + count),
//					(int)( -4*count),
//					(int)( -4*count),
//					(int)(-100 + count)
//					};
//			g2.fill(new Polygon(x1,y1,4));
//			
//			int[] x2 = {
//					(int)(-100 + count),
//					(int)(-100 + count),
//					(int)(img.getWidth(observer) - count),
//					(int)(img.getWidth(observer) - count)
//					};
//			int[] y2 = {
//					(int)(-100 + count),
//					(int)(img.getHeight(observer) - count),
//					(int)(img.getHeight(observer) - count),
//					(int)(-100 + count)
//					};
//			g2.fill(new Polygon(x2,y2,4));
			
			count += 5f;
			
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
//					if(allShapesToDraw.get(center) instanceof Rectangle2D.Double){
						//map <x,y,z> to <r,g,b> ... A(X) = Y 
						Vector3f colorTrnsFrmResult = new Vector3f();
						/* A(x) = 255.0/(5.0)*center.x()+255.0/(2.0)  		-5 <= x,y <= 5
						 * A(y) = 255.0/(5.0)*center.y()+255.0/(2.0)			22.5 <= z <= 27.5
						 * A(z) = 255.0/(5.0)*center.z()-255.0*22.5/(5.0)		diameter = (5.0)
						 */
						colorTrnsFrmResult.setX((float)(255.0 - Math.abs(center.x() - colorPkg.shapeColorVBO[0]) * 255.0/colorPkg.shapeColorVBO[1]));
						colorTrnsFrmResult.setY((float)(255.0 - Math.abs(center.y() - colorPkg.shapeColorVBO[2]) * 255.0/colorPkg.shapeColorVBO[3]));
						colorTrnsFrmResult.setZ((float)(255.0 - Math.abs(center.z() - colorPkg.shapeColorVBO[4]) * 255.0/colorPkg.shapeColorVBO[5]));
						if(colorTrnsFrmResult.x() >= 0 && colorTrnsFrmResult.y() >= 0 && colorTrnsFrmResult.z() >= 0 &&
								colorTrnsFrmResult.x() < 256 && colorTrnsFrmResult.y() < 256 && colorTrnsFrmResult.z() < 256){
								g2.setColor(new Color((int)colorTrnsFrmResult.x(),(int)colorTrnsFrmResult.y(),(int)colorTrnsFrmResult.z()));
						}
						else if(center.z() < 0) // virtual image
							g2.setColor(Color.BLACK);
						else //just far into picture
							g2.setColor(Color.WHITE);
						g2.fill(allShapesToDraw.get(center));
//						g2.drawImage(img,
////								(int) (center.x() - allShapesToDraw.get(center).getBounds2D().getX()/2 ),
////								(int) (center.y() - allShapesToDraw.get(center).getBounds2D().getY()/2 + 150 ),
////								(int) (center.x() + allShapesToDraw.get(center).getBounds2D().getX()/2 ),
////								(int) (center.y() + allShapesToDraw.get(center).getBounds2D().getY()/2 + 150),
//								(int) (center.x()),
//								(int) (center.y()),
//								(int) (center.x() + allShapesToDraw.get(center).getBounds2D().getX()),
//								(int) (center.y() + allShapesToDraw.get(center).getBounds2D().getY()),
//								0, //sx1
//								0, //sy1
//								img.getWidth(observer), //sx2
//								img.getHeight(observer), //sy2
//								g2.getColor(),
//								observer);
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
			
			float red = colorPkg.backgroundColor.getRed();
			float green = colorPkg.backgroundColor.getGreen();
			float blue = colorPkg.backgroundColor.getBlue();
			
			Vector3f velocity = colorPkg.backgroundColorVelocity; 
			
			if(velocity.x() > 0 && red >= 255 ){
				velocity.setX(-1 * velocity.x());
				red = 255;
			} else if(velocity.x() < 0 && red <= 15 ){
				velocity.setX(-1 * velocity.x());
				red = 10;
			}
			
			if(velocity.y() > 0 && green >= 255 ){
				velocity.setY(-1 * velocity.y());
				green = 255;
			} else if(velocity.y() < 0 && green <= 15 ){
				velocity.setY(-1 * velocity.y());
				green = 10;
			}
			
			if(velocity.z() > 0 && blue >= 255 ){
				velocity.setZ(-1 * velocity.z());
				blue = 255;
			} else if(velocity.z() < 0 && blue <= 15 ){
				velocity.setZ(-1 * velocity.z());
				blue = 10;
			}
			
			red = red + velocity.x();
			green = green + velocity.y();
			blue = blue + velocity.z();
			
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
			
			colorPkg.backgroundColor = new Color(red * 1.0f/255.0f, green * 1.0f/255.0f, blue * 1.0f/255.0f);
			
		}

		private void updateColorVBO() {
			
			float[] colorVBO = colorPkg.shapeColorVBO;
			Vector3f colorVBOVel = colorPkg.shapeColorVelocity;
			
			if(colorVBOVel.x() > 0 && colorVBO[0] >= 255 ){
				colorVBOVel.setX(-1 * colorVBOVel.x());
				colorVBO[0] = 255;
			} else if(colorVBOVel.x() < 0 && colorVBO[0] <= 15 ){
				colorVBOVel.setX(-1 * colorVBOVel.x());
				colorVBO[0] = 10;
			}
			
			if(colorVBOVel.y() > 0 && colorVBO[2] >= 255 ){
				colorVBOVel.setY(-1 * colorVBOVel.y());
				colorVBO[2] = 255;
			} else if(colorVBOVel.y() < 0 && colorVBO[2] <= 15 ){
				colorVBOVel.setY(-1 * colorVBOVel.y());
				colorVBO[2] = 10;
			}
			
			if(colorVBOVel.z() > 0 && colorVBO[4] >= 255 ){
				colorVBOVel.setZ(-1 * colorVBOVel.z());
				colorVBO[4] = 255;
			} else if(colorVBOVel.z() < 0 && colorVBO[4] <= 15 ){
				colorVBOVel.setZ(-1 * colorVBOVel.z());
				colorVBO[4] = 10;
			}
			
			colorVBO[0] = colorVBO[0] + colorVBOVel.x();
			colorVBO[2] = colorVBO[2] + colorVBOVel.y();
			colorVBO[4] = colorVBO[4] + colorVBOVel.z();
			
			colorPkg.shapeColorVBO = colorVBO;
		}

		public int getPanelID(){
			return panelID;
		}

		public static GlassPanelColorPackage getGlassPanelColorPackageInstance(float[] shapeColorVBO, 
				Vector3f shapeColorVelocity, Color backgroundColor, Vector3f backgroundColorVelocity){
			return new GlassPanelColorPackage(shapeColorVBO, shapeColorVelocity, backgroundColor, backgroundColorVelocity);
		}
		
		public static class GlassPanelColorPackage{
			
			//Shape Colors
			public float[] shapeColorVBO = {100, 255, 100 , 255 , 100, 255}; //color Virtual Buffer Object
			public Color backgroundColor = new Color(0f,0f,0f); // BLACK
			public Vector3f shapeColorVelocity = new Vector3f();
			public Vector3f backgroundColorVelocity = new Vector3f();	
			
			public GlassPanelColorPackage (float[] shapeColorVBO, Vector3f shapeColorVelocity, Color backgroundColor, Vector3f backgroundColorVelocity) {
				this.shapeColorVBO = shapeColorVBO;
				this.shapeColorVelocity = shapeColorVelocity;
				this.backgroundColor = backgroundColor;
				this.backgroundColorVelocity = backgroundColorVelocity;
			}
			
		}
		
}