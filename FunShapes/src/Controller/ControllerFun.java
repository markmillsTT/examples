package Controller;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Float;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Set;

import Model.BackgroundPiece;
import Model.Fractal;
import Model.ModelFun.HexBlock;
import Model.ModelFun.HexToTrianglePattern;
import Model.ModelFunInt;
import Model.Player;
import Model.ShapePattern;
import OracleExamples.Complex;
import View.ViewFun.FunPanel;
import View.ViewFunInt;

/**
 * Controller class.
 * 
 * @author Mark Mills
 */
public final class ControllerFun implements ControllerFunInt {

    private final ModelFunInt model;
    private final ViewFunInt view;
    int playerX, playerY;
	private int blockRadius;
	private int backgroundRadius;
	// for rendering
	private int currForeBlockLayer = 1;
	private int maxForeBlockLayers = 10;
	private int currBackBlockLayer = 1;
	private int maxBackBlockLayers = 6;
	private int level = 1;
	private Player player;
	private FunPanel mainPanel;
	private int playerAccel = 20;
	private Fractal fractal;
	private Complex[][] evenPopulatedSeed;
	private double [][] fracMattAbsLast; 
	
	@Override
	public Player getPlayer(){
		return this.player;
	}
    /**
     * Ties in model and view
     * 
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public ControllerFun(ModelFunInt model, ViewFunInt view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void makeLevel(){
    	if(level == 1)
    		makeLevel1();
    }
    
	private void makeLevel1() {
		//make foreground model
		makeForegoundBlocksL1();
		
		// FIXME background model should be made here, but is actually made in drawBackgound
		
		placePlayerL1();
	}
	
	private void placePlayerL1() {
		
		this.player = new Player(blockRadius, 3 * blockRadius, (int) (.5 * blockRadius) );
	}

	public void makeForegoundBlocksL1(){
		
		int stX = mainPanel.getWidth()/2;
		int stY = mainPanel.getHeight()/2;
		
		
		model.newHexBlockForeground(stX,stY );

		for(double i = 0; i < 2 * Math.PI ; i+= Math.PI/3.0){
			model.newHexBlockForeground((int)(stX + 2.5* blockRadius * Math.cos(i)),(int) (stY + 2.5 * blockRadius * Math.sin(i)));
		}
		
		
		//apply color scheme to blocks
		ArrayList<BackgroundPiece> fBs = model.getForegroundBlocks();
		for(BackgroundPiece fB: fBs){
			if(fB instanceof HexBlock){
				HexBlock hB = (HexBlock)fB;
				hB.blueMult = .9f;
				hB.greenMult = .80f;
				hB.redMult = .75f;
			}
		}
	}

	@Override
	public void drawMovingPieces(Graphics2D g2, FunPanel funPanel) {
		updatePlayerCoords();
		//depends on moving pieces already placed
		drawPlayer(g2, funPanel);
	}
	
	private void updatePlayerCoords() {
		Set<Integer> keySet = view.getPressedKeys().keySet();
		for(Integer key : keySet){
			Integer pressedCount = view.getPressedKeys().get(key);
			if(pressedCount > 0){
				if(pressedCount > 50);
					pressedCount = 50;
				int delta = 5;
				switch(key){
				case(KeyEvent.VK_UP) : 
					player.setCurrY(player.getCurrY() - playerAccel);
					break;
				case(KeyEvent.VK_DOWN) : 
					player.setCurrY(player.getCurrY() + playerAccel);
					break;
				case(KeyEvent.VK_LEFT) : 
					player.setCurrX(player.getCurrX() - playerAccel);
					break;
				case(KeyEvent.VK_RIGHT) : 
					player.setCurrX(player.getCurrX() + playerAccel);
					break;
				}
			}
		}
	}
	private void drawPlayer(Graphics2D g2, FunPanel funPanel) {
		int currX = player.getCurrX();
		int currY = player.getCurrY();
		int radius = player.getRadius();
		
		Float p1 = new Ellipse2D.Float(currX - radius , currY - radius, radius, radius);
		
		g2.setPaint(new GradientPaint(currX - radius , currY - radius, Color.gray, currX + radius, currY + radius, Color.red, false));
		g2.fill(p1);
		g2.setPaint(Color.black);
		g2.draw(p1);
	}

	@Override
	public void drawStationaryPieces(Graphics2D g2, FunPanel panel) {
		
		//first time
		if(mainPanel == null)
			this.mainPanel = panel;
		blockRadius = (int)(panel.getHeight()/6.0); 
		backgroundRadius = panel.getWidth();
		//first time
		if(model.getForegroundBlocks().size() == 0)
			makeLevel();
		
		
		drawBackground(g2);
		drawForeground(g2);
		
	}



	private void drawBackground(Graphics2D g2) {
		
		//first time
		if(model.getBackground() == null){
			model.setBackground(model.newHexBlock(mainPanel.getWidth()/2, mainPanel.getHeight()/2));
			HexBlock hb = (HexBlock)model.getBackground();
			//color scheme 
			hb.redMult = .9f;
		}
		
		
		BackgroundPiece bg = model.getBackground();
		if(bg instanceof HexBlock){
			
			HexBlock hb = (HexBlock)bg;
			//have one layer for stationary, one for manipuation
			if(hb.getLayers().size() == 1)
				hb.addLayer();
			
			String instructions = drawHexBlock(g2, hb, backgroundRadius, currBackBlockLayer);
			if("add layer".equals(instructions)){
				currBackBlockLayer ++;
			}else if("remove layer".equals(instructions)){
				if(currBackBlockLayer > 1){
					currBackBlockLayer--;
				}
			}	
		}
		
	}

	private void drawForeground(Graphics2D g2) {
		
		//draw all hexBlocks
		ArrayList<BackgroundPiece> hexBlocks = model.getForegroundBlocks();
		
		String instructions = "";
		for(int i = 0; i < hexBlocks.size() ; i++){
			HexBlock hB = (HexBlock)hexBlocks.get(i);
			//add layer to hexblock to be manipulated, one for constant at bottom
			if(hB.getLayers().size() == 1)
				hB.addLayer();
			instructions = drawHexBlock(g2,(HexBlock)hexBlocks.get(i),blockRadius, currForeBlockLayer);
		}
		if("add layer".equals(instructions)){
			currForeBlockLayer++;
		}else if("remove layer".equals(instructions)){
			if(currForeBlockLayer > 1){
				currForeBlockLayer--;
			}
		}		
	}

	/**
	 * 			Draw one HexBlock. 
	 * 
	 * @requires hB to have at least 2 layers AND 
	 * @requires the manipulated layer (maniLayer) > 0 ... layer 0 cannot be manipulated
	 * 
	 * @param g2
	 * @param hB
	 * @param radius
	 * @param maniLayer
	 * @return
	 */
	private String drawHexBlock(Graphics2D g2, HexBlock hB, int radius, int maniLayer) {
		
		//draw stationary layer ... all patterns start with stationary layer
		drawCompletedHexToTriangles(g2, radius, hB);
		
		//draw current moving block layer
		String instructions = drawCurrentHexToTriangle(g2, maniLayer, radius, hB);
		if("add layer".equals(instructions)){
			hB.addLayer();
		}else if("remove layer".equals(instructions)){
			hB.removeLayer(hB.getLayers().size()-1);
		}
		return instructions;
	}

	private String drawCurrentHexToTriangle(Graphics2D g2, int layer, int rad, HexBlock hB) {
		
		HexToTrianglePattern bgP = (HexToTrianglePattern) hB.getLayers().get(layer);
		// if this is the top layer, don't draw it, start backwards
		if(model.getForegroundBlocks().contains(hB) && layer == maxForeBlockLayers){
			return "remove layer";
		} else if(model.getBackground().equals(hB) && layer == maxBackBlockLayers){ //if at foreground top level
			return "remove layer";
		}
		
		int iteration = bgP.iteration;
		int direction = bgP.direction;
		int piePieces = bgP.currPiePieces;
		int red = bgP.red;
		int green = bgP.green;
		int blue = bgP.blue;
		float redMult = hB.redMult;
		float greenMult = hB.greenMult;
		float blueMult = hB.blueMult;
		int radius = (int) (rad* Math.pow(.75, layer));
		int stX = hB.getCenter().x;
		int stY = hB.getCenter().y;
		
		int numSections = iteration;
		double theta = 2.0 * Math.PI/numSections;
		double phi = 2.0 *Math.PI/ piePieces;
		
		//draw previous patterns on level
		for(int q = bgP.maxPiePieces; q > piePieces ; q--){
			double oldMaxTheta = 2.0 * Math.PI / ((double)q);
			for(int i= 0; i < q ; i++){
			
				//draw triangle
				int x1Points[] = {stX, stX + (int) (radius * Math.sin(oldMaxTheta * i)), stX + (int) (radius * Math.sin(oldMaxTheta * (i+1)))};
				int y1Points[] = {stY, stY + (int) (radius * Math.cos(oldMaxTheta * i)), stY + (int) (radius * Math.cos(oldMaxTheta * (i+1)))};
				GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
				
				polygon.moveTo(x1Points[0], y1Points[0]);
				polygon.lineTo(x1Points[1], y1Points[1]);
				polygon.lineTo(x1Points[2], y1Points[2]);
				polygon.closePath();
				g2.setColor(new Color((int)(red * Math.pow(redMult, q)), (int)(green * Math.pow(greenMult, q)), (int)(blue * Math.pow(blueMult, q))));
				g2.fill(polygon);
	
			}
		}
		//draw current pattern 
		for(double i= 0; i < 2.0*Math.PI ; i += theta){			
			//draw triangle
//			int x1Points[] = {stX, stX + (int) (radius * Math.sin(phi + i)), stX + (int) (radius * Math.sin(phi + (i+theta)))};
//			int y1Points[] = {stY, stY + (int) (radius * Math.cos(phi + i)), stY + (int) (radius * Math.cos(phi + (i+theta)))};
			int x1Points[] = {stX, stX + (int) (radius * Math.sin(i)), stX + (int) (radius * Math.sin(phi + i))};
			int y1Points[] = {stY, stY + (int) (radius * Math.cos(i)), stY + (int) (radius * Math.cos(phi + i))};
			GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
			
			polygon.moveTo(x1Points[0], y1Points[0]);
			polygon.lineTo(x1Points[1], y1Points[1]);
			polygon.lineTo(x1Points[2], y1Points[2]);
			polygon.closePath();
			g2.setColor(new Color((int)(red * Math.pow(redMult, piePieces)), (int)(green * Math.pow(greenMult, piePieces)), (int)(blue * Math.pow(blueMult, piePieces))));
			g2.fill(polygon);
						
		}		
		
		//pattern changed here
		if(iteration == 1 && direction < 0 ){
			bgP.currPiePieces -= bgP.direction;
			bgP.iteration = bgP.currPiePieces;
		} else if(iteration == piePieces && direction > 0 ){
			bgP.currPiePieces -= bgP.direction;
			bgP.iteration = 1;
		} else
			bgP.iteration+= bgP.direction;
		
		// just drew smallest triangle --> reverse pattern from tri --> hex
		if(bgP.currPiePieces == 2){
			bgP.direction*= -1;
			bgP.currPiePieces -= bgP.direction;
			bgP.iteration = 3; 
			return "add layer";
		}
		// just removed last triangle or, in other words, at lowest layer in the block--> remove layer
		if(bgP.currPiePieces > bgP.maxPiePieces ){
			return "remove layer";
		}
		return "";
		
	}

	private void drawCompletedHexToTriangles(Graphics2D g2, int rad, HexBlock hB) {
		
		//leave top for current manipulation
		for(int i = 0; i < hB.getLayers().size() - 1; i++){
			drawCompletedHexToTriangle(g2, i, rad, hB);
		}
		
	}
	public void drawCompletedHexToTriangle(Graphics2D g2, int layer ,int rad, HexBlock hB){
	
		HexToTrianglePattern bgP = (HexToTrianglePattern) hB.getLayers().get(layer);
		int red = bgP.red;
		int green = bgP.green;
		int blue = bgP.blue;
		float redMult = hB.redMult;
		float greenMult = hB.greenMult;
		float blueMult = hB.blueMult;
		int radius = (int) (rad* Math.pow(.75, layer));
		int stX = hB.getCenter().x;
		int stY = hB.getCenter().y;
			
			//draw previous patterns
			for(int q = bgP.maxPiePieces; q >= 3 ; q--){
				double oldMaxTheta = 2.0 * Math.PI / ((double)q); // theta = phi
				for(int i= 1; i <= q ; i++){		
					//draw triangle
					int x1Points[] = {stX, stX + (int) (radius * Math.sin(oldMaxTheta * i)), stX + (int) (radius * Math.sin(oldMaxTheta * (i+1)))};
					int y1Points[] = {stY, stY + (int) (radius * Math.cos(oldMaxTheta * i)), stY + (int) (radius * Math.cos(oldMaxTheta * (i+1)))};
					GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
					
					polygon.moveTo(x1Points[0], y1Points[0]);
					polygon.lineTo(x1Points[1], y1Points[1]);
					polygon.lineTo(x1Points[2], y1Points[2]);
					polygon.closePath();
					g2.setColor(new Color((int)(red * Math.pow(redMult, q)), (int)(green * Math.pow(greenMult, q)), (int)(blue * Math.pow(blueMult, q))));
					g2.fill(polygon);
				}
			}
			
		}
	
	@Override
	public void showFractal(Graphics2D g2, FunPanel funPanel) {
		
		doFractalIteration();
		drawFractal(g2,funPanel);
		
		
	}
	private void drawFractal(Graphics2D g2, FunPanel funPanel) {

		Complex[][] fracMatt = fractal.getCompMatrix();
		int pixToElem = 4;
		int stX = (funPanel.getWidth() - pixToElem*fractal.getWidth()) / 2;
		int stY = (funPanel.getHeight() - pixToElem*fractal.getHeight()) / 2;
		double max = fractal.max;
		
		double mult = 255 / fractal.max;
		
		for(int r = 0; r < fractal.getHeight(); r++){
			for(int c = 0; c < fractal.getWidth(); c++){
				double absVal = fracMatt[r][c].absVal();
				double diff = absVal - fracMattAbsLast[r][c];
				g2.setColor(new Color((int)(255 - diff*mult), (int)(255 - diff*mult), (int)(255 - diff*mult)));
				g2.fillRect(stX + c * pixToElem, stY + r* pixToElem, pixToElem, pixToElem);
				fracMattAbsLast[r][c] = diff;
			}
		}
		
	}
	private void doFractalIteration() {

		if(fractal == null){
			fractal = new Fractal(100, 100);
			fracMattAbsLast = new double[100][100];
			evenPopulatedSeed = fractal.getEvenPopulatedSeed(-5, 5);
			fractal.addSeed(evenPopulatedSeed);
			fractal.applySquareRule();
		} else {
			//fractal.addSeed(evenPopulatedSeed);
			fractal.applySquareRule();
		}
				
	}

}
