package Model;

import java.awt.Point;
import java.util.ArrayList;

import Controller.ControllerFunInt;

/**
 * Model for FunShapes
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public final class ModelFun implements ModelFunInt {

	private ArrayList<BackgroundPiece> hexBlocksFore = new ArrayList<BackgroundPiece>();
	private BackgroundPiece background;
	
    public ModelFun() {
     
    }
    
    @Override
    public Fractal newFractal(int pixWidth, int pixHeight){
    	return new Fractal(pixWidth,pixHeight);
    }
    
    @Override
    public BackgroundPiece getBackground(){
    	return background;
    }
    
    @Override
    public BackgroundPiece newHexBlock(int stX, int stY){
    	return new HexBlock(new Point(stX, stY));
    }
    
    @Override
    public void setBackground(BackgroundPiece background){
    	this.background = background;
    }
    
    @Override
    public BackgroundPiece newHexBlockForeground(int stX, int stY){
    	HexBlock hb = new HexBlock(new Point(stX, stY));
    	hexBlocksFore.add(hb);
    	return hb;
    }
	
	@Override
	public ArrayList<BackgroundPiece> getForegroundBlocks(){
		return hexBlocksFore;
	}
	
    public class HexToTrianglePattern extends ShapePattern{
    	
        public int iteration = 1, direction = 1, currPiePieces = 8, maxPiePieces = 8, red = 255, green =255, blue = 255; 
        public final int layerIndex;

        public HexToTrianglePattern(int layerIndex){
        	this.layerIndex = layerIndex;
        }
        
        public HexToTrianglePattern newFullHexToTriangle(int radiusOfPattern) {
        	HexToTrianglePattern ret = new HexToTrianglePattern(radiusOfPattern);
    		ret.iteration = 3;
    		ret.direction = -1;
    		ret.currPiePieces = 3;
    		return ret;
    	}
        
        public void removeLastAddedBackgroundPattern(){
        	if(hexBlocksFore.size() > 0)
        		hexBlocksFore.remove(hexBlocksFore.size()-1);
        }
        
    }
    
    public class HexBlock extends BackgroundPiece{
    
    	private ArrayList<ShapePattern> layers = new ArrayList<ShapePattern>();
    	// must be 1 > mult > 0
        public float redMult = 1, greenMult = 1, blueMult = .9f;
    	private Point center;
    	
    	//by default all hexblocks start with one layer
    	//... since whole point of HexBlock is to hold layers
    	public HexBlock(Point center){
    		layers.add(new HexToTrianglePattern(layers.size()));
    		this.center = center;
    	}
    	
    	public void addLayer(){
    		layers.add(new HexToTrianglePattern(layers.size()));
    	}
    	
    	public void removeLayer(int index){
    		layers.remove(index);
    	}
    	
    	public ArrayList<ShapePattern> getLayers(){
    		return layers;
    	}
    	
    	public Point getCenter(){
    		return center;
    	}
    	
    	public void setCenter(Point center){
    		this.center = center;
    	}
    }

}
