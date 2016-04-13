package View;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.beans.PropertyChangeEvent;
import java.text.AttributedCharacterIterator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import sun.tools.jar.JarImageSource;

import Controller.ControllerFunInt;
import Helpers.ComponentListener1;
import Helpers.KeyListener1;
import Helpers.MouseListener1;
import Helpers.MouseMotionListener1;
import Helpers.PropertyChangeListener1;

/**
 * 
 * View for FunShapes
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public final class ViewFun extends JFrame implements ViewFunInt {

    private ControllerFunInt controller;
    private FunPanel mainPanel = new FunPanel();
    private int widthFrame = 1500, heightFrame =(int) (widthFrame * .5); 
    private boolean moveBackground = false;
	public boolean movePlayer = false;
	int count = 0;
	public boolean resized;
	private HashMap<Integer, Integer> keyInputPressed = new HashMap<Integer, Integer>();

    public ViewFun() {
    	
    	initilizeView();
    	startFun();
    
    }

	private void startFun() {
		
		
		
	}

	private void initilizeView() {
		
		//keep track of which keys are pressed .. initialize all inputs
		keyInputPressed.put(KeyEvent.VK_UP, 0);
		keyInputPressed.put(KeyEvent.VK_DOWN, 0);
		keyInputPressed.put(KeyEvent.VK_LEFT, 0);
		keyInputPressed.put(KeyEvent.VK_RIGHT, 0);
		
		this.setSize(widthFrame, heightFrame);
		mainPanel.setMinimumSize(new Dimension(1500,750));
		this.add(mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(new KeyListener1(){
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyInputPressed.containsKey(keyCode)){
					Integer pressedCount = keyInputPressed.get(keyCode);
					keyInputPressed.put(keyCode,pressedCount+1);
				}
				super.keyPressed(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(keyInputPressed.containsKey(e.getKeyCode()))
					keyInputPressed.put(e.getKeyCode(),0);
				super.keyPressed(e);
			}
			
		});
		this.addComponentListener(new ComponentListener1() {

			@Override
			public void componentResized(ComponentEvent arg0) {
				resized = true;
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initilizeController(ControllerFunInt controller) {
		this.controller = controller;
		Thread thread = new Thread(){
			@Override
			public void run() {
				super.run();
				while(true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					moveBackground = true;
					repaint();	
				}
			}
		};
		thread.run();
	}
       
	// must changed resized to false after call
	@Override
	public boolean hasBeenResized() {
		if(resized){
			resized = false;
			return true;
		}
		return false;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
	}


	public class FunPanel extends JPanel{
		
		public FunPanel(){
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
	
//			if(moveBackground  == true){
//				controller.showFractal(g2,this);
//			}
			
			if(moveBackground  == true){
				controller.drawStationaryPieces(g2,this);
				controller.drawMovingPieces(g2,this);
				moveBackground = false;
			}
			if(movePlayer){
				controller.drawMovingPieces(g2,this);
			}
		}
	}


	@Override
	public HashMap<Integer, Integer> getPressedKeys() {
		return keyInputPressed;
	}
}


