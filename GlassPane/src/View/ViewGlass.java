package View;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.plaf.ViewportUI;
import javax.vecmath.Vector3d;

import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

import Controller.ControllerGlassInt;
import Helpers.AllActionListeners;

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
    public static final int GLASSPANE_HEIGHT = 600;
    public static final int GLASSPANE_WIDTH = 800;
    final static boolean RIGHT_TO_LEFT = true;
    public static boolean flashRedraw = false;
    public static Color currentBackgroundColor = Color.BLACK;
    
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
    }

	private void initializeView(Container contentPane) {
		
    	if(RIGHT_TO_LEFT)
    		contentPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    	
    	GridLayout gl = new GridLayout(1,3);
    	
    	contentPane.setLayout(gl);
    	GridBagConstraints c = new GridBagConstraints();
    	
    	this.gp1 = new GlassPanel(2,controller);
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
    				controller.toggleToParrellelView(gp1.getPanelID());
    		}@Override
    		public void mouseClicked(MouseEvent e) {
    			// TODO Auto-generated method stub
    			int mark = 5;
    			mark = 9;
    			super.mouseClicked(e);
    		}
    		
    	});
    	
    	
/*    	this.cp = new ControlPanel();
    	setupControlPanel();
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 1;
    	c.weighty = 1/8;
    	c.gridx = 0;
    	c.gridy = 1;
    	contentPane.add(cp,gl);    	*/
    	
//    	this.gp2 = new GlassPanel(2,controller);
//   	contentPane.add(gp2,gl);
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
