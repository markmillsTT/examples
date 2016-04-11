package Helpers;

public class Vector3f {

	float x = 0;
	float y = 0;
	float z = 0;
	
	public Vector3f(float aX, float aY, float aZ)
	{
		this.x = aX;
		this.y = aY;
		this.z = aZ;
	}
	
	public float x()
	{
		return this.x;
	}
	
	public float y()
	{
		return this.y;
	}
	
	public float z()
	{
		return this.z;
	}
	
	public void setX(float aX)
	{
		this.x = aX;
	}
	
	public void setY(float aY)
	{
		this.y = aY;
	}
	
	public void setZ(float aZ)
	{
		this.z = aZ;
	}
	
}
