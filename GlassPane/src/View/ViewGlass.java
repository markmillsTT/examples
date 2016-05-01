package View;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import Controller.ControllerGlassInt;
import Helpers.AllActionListeners;
import Helpers.Vector3f;

/**
 * 
 * View for FunShapes
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public final class ViewGlass extends JFrame implements ViewGlassInt {

    public static ControllerGlassInt controller;
    private final Timer timer = new Timer(20, this);
    public static final int GLASSPANE_HEIGHT = 250;
    public static final int GLASSPANE_WIDTH = 250;
    final static boolean RIGHT_TO_LEFT = true;

    private int flashRedrawCount = 0;
    public static boolean flashRedraw = false;
    
  //  JTextField inX;
//    private JViewport vp;
    private GlassPanel gp1;
    private GlassPanel gp2;
    private GlassPanel gp3;
    private ControlPanel cp;

    public ViewGlass() {
    	this.setMinimumSize(new Dimension(GLASSPANE_WIDTH+100,GLASSPANE_HEIGHT+100));
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	initializeView(this.getContentPane());
    	
    	AllActionListeners listener = new AllActionListeners(){
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getID() == KeyEvent.VK_P){
					controller.toggleToParrellelView(69);
				}
			}
		};
		this.addKeyListener(listener);
		
    }

	private void initializeView(Container contentPane) {
		
    	if(RIGHT_TO_LEFT)
    		contentPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    	
    	GridLayout gl = new GridLayout(1,3);
    	
    	contentPane.setLayout(gl);
    	GridBagConstraints c = new GridBagConstraints();
    	
    	float[] shapeColor1 = {127, 255, 127, 255, 127, 255 };
    	Vector3f shapeColorVel1 = new Vector3f(1f, -1f, 1f);
    	Color bkgColor = new Color(0.5f, 0.5f, 0.5f);
    	Vector3f bkgColorVel1 = new Vector3f(1f, -1f, 1f);
    	
    	GlassPanel.GlassPanelColorPackage colorPkg1 = GlassPanel.getGlassPanelColorPackageInstance(
    			shapeColor1,
    			shapeColorVel1,
    			bkgColor,
    			bkgColorVel1
    			);  	
    	
    	this.gp1 = new GlassPanel(1,controller, colorPkg1);    	
    	c.fill = GridBagConstraints.HORIZONTAL;
    	//c.ipady = GLASSPANE_HEIGHT;
    	c.ipady = GLASSPANE_WIDTH;
  //  	c.ipadx =GLASSPANE_HEIGHT;
    	c.gridwidth = 1;
    	c.gridheight = 2;
    	c.weightx = 1;
    	c.weighty = 1;
    	c.gridx = 0;
    	c.gridy = 0;
    	contentPane.add(gp1,gl);
    	contentPane.addKeyListener(new AllActionListeners() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    			if(e.getKeyChar() == KeyEvent.VK_P)
    				controller.toggleToParrellelView(gp2.getPanelID());
    		}@Override
    		public void mouseClicked(MouseEvent e) {
    			// TODO Auto-generated method stub
    			int mark = 5;
    			mark = 9;
    			super.mouseClicked(e);
    		}
    		
    	});
    	
    	float[] shapeColor2 = {127, 255, 127, 255, 127, 255 };
    	Vector3f shapeColorVel2 = new Vector3f(-1f, 1f, -1f);
    	Vector3f bkgColorVel2 = new Vector3f(1f, -1f, 1f);
    	
    	GlassPanel.GlassPanelColorPackage colorPkg2 = GlassPanel.getGlassPanelColorPackageInstance(
    			shapeColor2,
    			shapeColorVel2,
    			bkgColor,
    			bkgColorVel2
    			); 
    	
    	this.gp2 = new GlassPanel(2,controller, colorPkg2);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	//c.ipady = GLASSPANE_HEIGHT;
    	c.ipady = GLASSPANE_WIDTH;
  //  	c.ipadx =GLASSPANE_HEIGHT;
    	c.gridwidth = 1;
    	c.gridheight = 2;
    	c.weightx = 1;
    	c.weighty = 1;
    	c.gridx = 1;
    	c.gridy = 0;
    	contentPane.add(gp2,gl);
    	
//    	this.gp3 = new GlassPanel(3,controller);
//   	contentPane.add(gp3,gl);
    	
    	this.pack();
    	this.setVisible(true);
		
	}

	private void setupControlPanel() {
		
		GridLayout gl = new GridLayout(3,1);
	//	cp.setLayout(new GridBagLayout());
		JTextField inX;
		cp.setLayout(gl);
		GridBagConstraints c = new GridBagConstraints();
		inX = new JTextField("put X bla bla here");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
    	c.weighty = 1/24;
    	c.gridx = 0;
    	c.gridy = 0;
    	this.cp.add(inX,gl);
    	
    	JTextArea inY = new JTextArea("put Y bla bla here");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
    	c.weighty = 1/24;
    	c.gridx = 0;
    	c.gridy = 1;
    	c.insets = new Insets(10,0,0,0);
    	this.cp.add(inY,gl);
    	
    	JTextArea inZ = new JTextArea("put Z bla bla here");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
    	c.weighty = 1/24;
    	c.gridx = 0;
    	c.gridy = 2;
    	c.insets = new Insets(10,0,0,0);
    	this.cp.add(inZ,gl);
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		flashRedraw = true;
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {		
		super.paint(g);
	}
    
	@Override
	public void setController(ControllerGlassInt controller){
		this.controller = controller;
	}

	@Override
	public Dimension getViewPortDimensions() {
		return gp1.getSize();
	}
	
	@Override
	public void startRepaintFlashTimer(){
		this.timer.start();
	}

	public class ControlPanel extends JPanel {
		
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			
			g2.setColor(Color.CYAN);
			g2.fill(new Rectangle2D.Double(0,0,1000,1000));
		}
	}
	
}
