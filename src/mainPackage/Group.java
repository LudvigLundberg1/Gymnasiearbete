package mainPackage;
import java.util.Random;

public class Group {
	
	Random rnd = new Random();
	int size = 15;
	int currentSize = size;
	int generations = 0;
	Individual id[] = new Individual[currentSize];
	
	boolean genepool [];
	int index;
	
	static boolean safeFromPredator = false;

		public Group(){
		for(int i = 0; i < currentSize; i++) {
			id[i] = new Individual();
			id[i].firstGeneration();
		} //initial population
	}
	

	
	public void action() {
		for(int i = 0; i < id.length; i++) {
			id[i].movement1();

			if(safeFromPredator == false) {
				id[i].gettingDetected();
			}
			
		}

	}
	
	
	
	public void replication() {
		
		//genepool

		genepool = new boolean[currentSize];
		index = 0;
		for(int i = 0; i < currentSize; i++) {
			if(id[i].alive) {
				genepool[index] = id[i].gene; //moves population to genepool
				index++;
			} 
		}
		
		//shuffle elements in genepool (randomness)
		for(int i = 0; i < genepool.length; i++) {
			int randomIndexToSwap = rnd.nextInt(genepool.length);
			boolean temp = genepool[randomIndexToSwap]; //saving the value temporarily
			genepool[randomIndexToSwap] = genepool[i];
			genepool[i] = temp;
			
		}

		
		currentSize = genepool.length*2;
		id = new Individual[currentSize];
		safeFromPredator = false;
		
		//Every individual mates with random and makes two children. Four children per two parents are thus created.
		for(int i = 0; i < genepool.length; i++) 
		{
			for(int j = 0; j < 2; j++) 
			{

				id[2*i+j] = new Individual();
				if(mainClass.risk(0.5)) {
					id[2*i+j].gene = genepool[i];
				} else {
					if(i+1<genepool.length) {
						id[2*i+j].gene = genepool[i+1];
											} 
						else {
						id[2*i+j].gene = genepool[0];
							 }	
					
						}

			}
			
		}
		
	}
	
	public void malnutrition() {
		if(currentSize>mainClass.maxPopulation) {
			for(int i = 0; i < id.length; i++) {
			if(mainClass.risk(Double.valueOf(mainClass.maxPopulation/currentSize))) { //chance of survival
				
			} else {
				id[i].alive = false; //die of malnutrition
				currentSize--;
			}
			}
		}
	}
		
	
}
	


	

