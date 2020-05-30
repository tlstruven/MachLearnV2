package machineLearning;

public class Letter {
	//CONSTANTS
	int PIXEL_LENGTH =24;
	//MEMBER VARIABLES
	float vertical;
	float horizontal;
	float rightDiagonal;
	float leftDiagonal;
	ImageChar ic = new ImageChar();
	//CONSTRUCTOR
	public Letter(boolean isUnknown) {
		if(!isUnknown)fillRNG();
		else 
		{
			getLines();
		}
	}
	
	//MEMBER METHODS
	
	public void fillRNG() {
		 vertical = (float) Math.random();
		 horizontal = (float) Math.random();
		 rightDiagonal = (float) Math.random();
		 leftDiagonal = (float) Math.random();
	}//end of RNG thing
	
	public void getLines() {
		
	}
}
