package machineLearning;

public class Perceptron {
	//CONSTANTS
	public static final int SIDE = ImageChar.SIDE;
	public static final long ITERATIONS = 10000;
    public static final int OUTPUT  = 26;
    public static final float POSITIVE_DELTA = 0.01f;
    public static final float NEGATIVE_DELTA = 0.01f;

    //MEMBER VARIABLES
    ImageChar img;
    float[][][]weights = new float[SIDE][SIDE][OUTPUT];
    float[]outputs = new float[OUTPUT];

    //CONSTRUCTOR
    public Perceptron(){
        img = new ImageChar();
        for(int r = 0; r < SIDE; r++) {
            for(int c = 0; c < SIDE; c++) {
                for(int output = 0; output < OUTPUT; output ++) {
                    weights[r][c][output] = (float)Math.random();
                }
            }
        }
        solve();
    }
	
	//MEMBER METHODS
    public void solve() {
    	for(int i = 0; i<ITERATIONS; i++) {
    		
    		char real = img.drawRandomChar(0,0);
    		char guess = 0;
    		float[] tempOutput = new float[OUTPUT];
    	    for(int x = 0; x< SIDE; x++) {
    	    	for(int y =0; y<SIDE; y++) {
    	    		if(img.getPixel(x,y)==1) {
    	    			for(int w = 0; w<OUTPUT;w++) {
    	    				tempOutput[w] += weights[x][y][w];
    	    				
    	    			}
    	    		}
    	    		guess = (char)(getGreatestIndex(tempOutput)+'A');
    	    	}//x
    	    }//y
    	    System.out.println(guess + " " + real);
    	    adjust(real,guess, tempOutput);
    	    img.clear();
    	}//iterations
    }
    
    private void adjust(char real, char guess, float[] tempWeights) {
    	if(real == guess)return;
    	for(int x = 0; x< SIDE; x++) {
	    	for(int y =0; y<SIDE; y++) {
	    		if(img.getPixel(x,y)==1) {
	    		
	    			weights[x][y][real-'A'] += POSITIVE_DELTA;
	    			weights[x][y][guess-'A'] -= NEGATIVE_DELTA;
	    		}
	    	}
    	}
		
	}

    
    private int getGreatestIndex(float[] letters) {
    	int greatest = 0;
    	for(int i = 0; i< letters.length;i++) {
    		if(letters[i]> greatest)greatest = i;
    	}
    	return greatest;
    }
	
	
	
}
