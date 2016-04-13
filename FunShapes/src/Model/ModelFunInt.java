package Model;

import java.awt.Point;
import java.util.ArrayList;

import Controller.ControllerFunInt;
import Model.ModelFun.HexToTrianglePattern;

/**
 * Model interface.
 * 
 * Model for FunShapes
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public interface ModelFunInt {

	ArrayList<BackgroundPiece> getForegroundBlocks();

	BackgroundPiece newHexBlockForeground(int stX, int stY);

	BackgroundPiece newHexBlock(int stX, int stY);

	void setBackground(BackgroundPiece background);

	BackgroundPiece getBackground();
	
	Fractal newFractal(int pixWidth, int pixHeight);
    
}
