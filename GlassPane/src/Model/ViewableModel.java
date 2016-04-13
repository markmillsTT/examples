package Model;

import java.awt.Shape;
import java.util.List;
import javax.vecmath.*;

public interface ViewableModel {

	public CoordinateSystem getObjectCoordSystem();

	/**
	 *  When implementing in an "implements ViewableModel"... if you only reference 
	 *  	one point (OR InOthrWds one green position vector) for the ViewableModel, just
	 *  	send back a list of size 1. A List<T> is passed back because the first shape created
	 *  	was a SwirlPattern that uses n phases of a pattern to be drawn for each SwirlPattern.
	 *  
	 * @param t
	 * @return
	 */
	public List<Vector3d> getAllPositionVectorsInOCS(long t);

}
