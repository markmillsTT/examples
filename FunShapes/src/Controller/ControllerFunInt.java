package Controller;

import java.awt.Graphics2D;

import Model.Player;
import View.ViewFun.FunPanel;

/**
 * Controller interface.
 * 
 * Controller for FunShapes
 * 
 * @author Mark Mills
 * 
 * </pre>
 */
public interface ControllerFunInt {

	void drawStationaryPieces(Graphics2D g2, FunPanel funPanel);

	void makeLevel();

	void drawMovingPieces(Graphics2D g2, FunPanel funPanel);

	Player getPlayer();

	void showFractal(Graphics2D g2, FunPanel funPanel);

   
}
