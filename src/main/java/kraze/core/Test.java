package kraze.core;

import java.util.Random;

public class Test {

	Random random = new Random();
	double[] weights;
	double c = 0.01;

	Test(int n) {
	    weights = new double[n];
	    for (int i = 0; i < weights.length; i++) {
	      weights[i] = random(-1,1);
	    }
	  }


	private double random(int from, int to) {

		int total = to -from;
		
		return random.nextDouble()*total + from;
		
	}


	double feedforward(double[] inputs) {
	    double sum = 0;
	    for (int i = 0; i < weights.length; i++) {
	      sum += inputs[i]*weights[i];
	    }
	    return activate(sum);
	  }


	double activate(double sum) {
		return sum;
	}


	boolean train(double[] inputs, int desired) {
	    double guess = feedforward(inputs);
	    double error = desired - guess;
	    if(error<=0.1 && error>= (-0.1)){
	    	for (int l = 0; l < weights.length; l++) {
	  	      System.out.print(weights[l] + " ");
	  	    }
	    	return true;
	    }
	    for (int i = 0; i < weights.length; i++) {
	      weights[i] += c * error * inputs[i];
	    }
	    
	    return false;
	  }
	
	public static void main(String[] args) {
		
		int input = 81;
		double res = input*0.3779822882697647 -0.2101770915915882*input+ 0.9407623252500289*input -0.48077129397710544*input +0.8397758099111469*input -0.186194047222293*input +0.36808924758158484*input -0.4809859611257353*input -0.09989068931314865*input -0.726692945685269*input;
		System.out.println(res);
		
		boolean found= false;
		
		for (int k = 0; k < 1000000; k++) {
//			System.out.println("iteration " + k);
			
			int size = 10;
			
			Test app = new Test(size);
			
			for (int i = 2; i < 100; i++) {
				
				int desired = i;
				double[] inputs = new double[size];
				for (int j = 0; j < size; j++) {
					inputs[j] = i*i;
				}
				
				found = app.train(inputs, desired);
				
				if(found){
					System.out.println("found for " + i + " at iteration " + k);
					continue;
				}
				
			}
			
			if(found){
				continue;
			}
		}
		
		
		
	}

}
