package mainPackage;
import java.util.Random;

public class Individual {
	
	boolean gene, detected, alive, safe; 
    Double x, y, vx, vy;
    
    Double radius;
    int diameterOfIndividual;
    Double egoistRiskOfDetection;
    Double RiskOfDeath;
    Double chanceOfSuccessfulWarning;
    Double altruistRate;
    
	static Random rnd = new Random();
	int groupIndex;

	public Individual() {
			
			this.x = rnd.nextDouble(mainClass.width);
			this.y = rnd.nextDouble(mainClass.height);

			this.vx = rnd.nextDouble(4)-2;
			this.vy = rnd.nextDouble(4)-2;
			
			this.detected = false;
			this.alive = true;
			this.safe = false;
			
			 this.radius = 40.0; //set to 40.0
			 this.diameterOfIndividual = 10; //set to 10
			 this.egoistRiskOfDetection = 0.9; //set to 0.9
			 this.RiskOfDeath = 0.8; //set to 0.8
			 this.chanceOfSuccessfulWarning = 1.0; //100% that the warning makes everyone safe   
	}
	

	public void firstGeneration() {
	
		
		    this.altruistRate = mainClass.dino[groupIndex].altruistRate;
		    
			if(rnd.nextDouble(1) < altruistRate) {
				this.gene = true; //egoistic
			} else { this.gene = false;  }//altruistic 
	}
	
	public void movement1() {
		if(this.x>mainClass.width) {
			this.vx *= -1;
		}
		if(this.x<0) {
			this.vx *= -1;
		}
		if(this.y<0) {
			this.vy *= -1;
		}
		if(this.y>mainClass.height) {
			this.vy *= -1;
		}
		this.x += this.vx;
		this.y += this.vy;

	}
	public void gettingDetected() { //at this distance, the individual notices the predator
		if(this.safe == false && mainClass.distance(this.x, this.y, mainClass.predator.x,mainClass.predator.y)<this.radius) 
			{
			if(this.gene) { //it's altruistic
				//100% risk of getting detected
				detected = true;
				if(mainClass.risk(this.chanceOfSuccessfulWarning)) {
					mainClass.dino[groupIndex].safeFromPredator = true; //everyone else is now safe
				}
			} else {
				//50% risk of getting detected
				detected = mainClass.risk(egoistRiskOfDetection);
				if(detected==false){
					safe = true;
				}
			}
			
			if(detected) {
				//50% risk of getting caught
				if(safe==false) {
					alive = mainClass.risk(1-RiskOfDeath);
					if(alive==false) {

						mainClass.dino[this.groupIndex].currentSize--;
					}
				}
				
				safe = true;
				}
			}
	}
	

}
