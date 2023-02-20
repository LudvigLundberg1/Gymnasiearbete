package mainPackage;
import java.util.Random;

public class Group {
	
	Random rnd = new Random();
	
	int groupIndex;
	int size;
	int currentSize = size;
	Double mutationRisk;
	Double shareOfAltruists = 0.0;
	Individual id[];
	
	
	boolean genepool [];
	int index;
	
	boolean safeFromPredator;
	
	int count;
	
	//initializing individual stats
	
	    Double altruistRate;
	    
//--------
	    
		public Group(){
			this.groupIndex = groupIndex;
			this.size = 50;
			this.currentSize = this.size;
			this.mutationRisk = 0.1;
			this.id = new Individual[this.currentSize];
			
			this.genepool = genepool;
			this.index = index;
			
			this.safeFromPredator = false;
			
			//setting individual stats
			   
				   this.altruistRate = mainClass.altruistRateUniversal;
			
			
		
	}
	

		public void createIndividuals() {
			for(int i = 0; i < this.currentSize; i++) {
				id[i] = new Individual();
				id[i].firstGeneration();
				id[i].groupIndex = this.groupIndex;
			} //initial population
			
			calculateShareOfAltruists();
			
		}
	
	public void action() {
		for(int i = 0; i < id.length; i++) {
				if(id[i].alive) {
					id[i].movement1();
			
					if(safeFromPredator == false) {
						id[i].gettingDetected();
					}
				}	
			}
	//System.out.println("Group " + groupIndex + ": " + currentSize);
	}
	
	
	
	public void replication() {
		
		//genepool

		genepool = new boolean[currentSize];
		
		count = 0;
		for(int i = 0; i < id.length; i++) {
			//System.out.println("groupIndex: " + id[i].groupIndex);
			if(id[i].alive) {
				count ++;
			}
		}


		index = 0;
		for(int i = 0; i < id.length; i++) {
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
					//mutation
					if(mainClass.risk(mutationRisk)) {
						if(id[2*i+j].gene) {
							id[2*i+j].gene = false;
						} else { id[2*i+j].gene = true; }
					}
					
					
				
						}
				
				id[2*i+j].groupIndex = this.groupIndex;

			}
			
		}
		
		
		calculateShareOfAltruists();
	
	
		
	}
	
	
	
	public void calculateShareOfAltruists(){
		//calculating shareOfAltruists
		shareOfAltruists = 0.0;
		for(int i = 0; i < id.length; i++) {
			if(id[i].gene) {
				shareOfAltruists++;
			}
		}
		if(shareOfAltruists != 0) {
		shareOfAltruists /= id.length;
		}
	}
	
	
	
	public void malnutrition() {
		
			for(int i = 0; i < id.length; i++) {
				if(id[i].alive) {
			if(mainClass.risk(Double.valueOf(mainClass.maxPopulation)/Double.valueOf(mainClass.storeTotalPopulation))) { //chance of survival
				
			} else {
				id[i].alive = false; //die of malnutrition
				currentSize--;
			}
			}
			}
		}
	
		
	
}
	


	

