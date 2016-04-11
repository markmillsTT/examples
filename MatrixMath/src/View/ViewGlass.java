package View;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import Controller.ControllerGlassInt;

/**
 * 
 * View for FunShapes
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public final class ViewGlass extends JFrame implements ViewGlassInt {

    /****
	 * 
					GlassPaneRun.java --- serialVersionUID = 0L;
					ControllerGlass.java --- serialVersionUID = 1L;
					ModelGlass.java --- serialVersionUID = 2L;
					ViewGlass.java --- serialVersionUID = 3L; 
					
	 */
	
	private static final long serialVersionUID = 3L;
	
	@SuppressWarnings("unused") // Positioned here for future purposes
	private ControllerGlassInt controller;
	
	@SuppressWarnings("unused")
	private boolean flashRedraw; // Utilize this variable to set Analog or "flashRedraw" - start with paint(Graphics g) 
	
	private final Timer timer = new Timer(20, this);
	public static Dimension WINDOW_SIZE = new Dimension(1200,600);
	private GlassPanel viewingPort;
	

    public ViewGlass() {
    	
    	setupView();
    	
    }

    private void setupView() {
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setSize(WINDOW_SIZE);
    	this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		flashRedraw = true;
		this.repaint();
	}

	@Override
	public void setController(ControllerGlassInt controller){
		this.controller = controller;
		viewingPort = new GlassPanel(0, controller);
    	this.add(viewingPort);
    	this.revalidate();
	}

	@Override
	public void startRepaintFlashTimer(){
		this.timer.start();
	}
       
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

}
