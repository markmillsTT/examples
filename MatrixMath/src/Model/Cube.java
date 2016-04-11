package Model;

public class Cube implements ViewableObject {

	private float radius;
	float[] cubeVerticies;
	int[] cubeSurfaceTriangleIndicies = {
			4 , 5 , 6 , 7,	//back	
			5 , 4 , 1 , 0,	//bottom
			1 , 2 , 7 , 4,	//right 
			5 , 0 , 3 , 6,	//left
			3 , 2 , 7 , 6,	//top
			0 , 1 , 2 , 3	//Front
	};
	
	public Cube(float width){
		this.radius = width / 2.0f;
		
		float[] cubeVerticiesHold = {
				-radius, -radius, radius,		//0
				radius, -radius, radius,		//1
				radius, radius, radius,		//2
				-radius, radius, radius,		//3
				
				radius, -radius, -radius,	//4
				-radius, -radius, -radius,		//5
				-radius, radius, -radius,		//6
				radius, radius, -radius,		//7
		};
		
		this.cubeVerticies = cubeVerticiesHold;
	}

	@Override
	public float[] getVertexLocations() {
		return this.cubeVerticies;
	}

	@Override
	public int[] getVertexIndicies() {
		return this.cubeSurfaceTriangleIndicies;
	}
	
}
