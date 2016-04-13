package Model;

import quicktime.std.image.Matrix;
import OracleExamples.Complex;

public class Fractal extends BackgroundPiece{
	
	private int pixWidth, pixHeight;
	private Complex[][] fracMatt;
	public double max, min;

	public int getWidth(){
		return pixWidth;
	}
	
	public int getHeight(){
		return pixHeight;
	}
	
	public Fractal(int pixWidth, int pixHeight) {
		this.pixWidth = pixWidth;
		this.pixHeight = pixHeight;
		fracMatt = new Complex[pixHeight][pixWidth];
		for(int r = 0; r < pixHeight; r++){
			for(int c = 0; c < pixWidth; c++){
				fracMatt[r][c] = new Complex(0, 0);
			}
		}
	}
	
	public void applySquareRule(){
		max = fracMatt[0][0].absVal();
		min = fracMatt[0][0].absVal();
		for(int r = 0; r < pixHeight; r++){
			for(int c = 0; c < pixWidth; c++){
				fracMatt[r][c] = fracMatt[r][c].times(fracMatt[r][c]);
				if(fracMatt[r][c].absVal() > max)
					max = fracMatt[r][c].absVal();
				else if(fracMatt[r][c].absVal() < min)
					min = fracMatt[r][c].absVal();	
			}
		}
	}
	
	public void addSeed(Complex[][] seed){
		max = fracMatt[0][0].absVal();
		min = fracMatt[0][0].absVal();
		for(int r = 0; r < pixHeight; r++){
			for(int c = 0; c < pixWidth; c++){
				fracMatt[r][c] = fracMatt[r][c].plus(seed[r][c]);
				if(fracMatt[r][c].absVal() > max)
					max = fracMatt[r][c].absVal();
				else if(fracMatt[r][c].absVal() < min)
					min = fracMatt[r][c].absVal();	
			}
		}
	}
	
	
	public Complex[][] getEvenPopulatedSeed(double low, double max){
		Complex[][] ret = new Complex[pixHeight][pixWidth];
		double multR = (max-low)/pixHeight;
		double multC = (max-low)/pixWidth;
		for(int r = 0; r < pixHeight; r++){
			for(int c = 0; c < pixWidth; c++){
				ret[r][c] = new Complex(low + multC*c,low + multR*r);
			}
		}
		
		return ret;
	}
	
	public Complex[][] getCompMatrix(){
		return fracMatt;
	}
}
