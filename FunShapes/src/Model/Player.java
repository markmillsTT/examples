package Model;

public class Player {

	int currX, currY, radius;
	public Player(int currX, int currY, int radius) {
		this.currX = currX;
		this.currY = currY;
		this.radius = radius;
	}
	
	public int getCurrX(){
		return currX;
	}
	
	public int getCurrY(){
		return currY;
	}
	
	public void setCurrX(int currX){
		this.currX = currX;
	}
	
	public void setCurrY(int currY){
		this.currY = currY;
	}
	
	public int getRadius(){
		return radius;
	}
}
