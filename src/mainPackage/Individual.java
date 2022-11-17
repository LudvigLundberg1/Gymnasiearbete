package mainPackage;
import java.util.Random;

public class Individual {
	boolean gene, detected, alive, safe; 
    Double x, y, vx, vy;
    Double radius;
    static int diameterOfIndividual = 10;
    static Double egoistRiskOfDetection = 0.5;
    static Double RiskOfDeath = 0.5;
	static Random rnd = new Random();

	public Individual() {
			
			this.x = rnd.nextDouble(700);
			this.y = rnd.nextDouble(700);

			this.vx = rnd.nextDouble(4)-2;
			this.vy = rnd.nextDouble(4)-2;
			
			this.radius = 100.0;
			this.detected = false;
			this.alive = true;
			this.safe = false;
		
	}
	
	public void firstGeneration() {
		if(rnd.nextDouble(1) > 0.90) {
			this.gene = false; //egoistic
		} else { this.gene = true;  }//altruistic 
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
		if(safe == false && mainClass.distance(this.x, this.y, mainClass.predator.x,mainClass.predator.y)<this.radius) 
			{
			if(this.gene) { //it's altruistic
				//100% risk of getting detected
				detected = true;
				mainClass.dino.safeFromPredator = true; //everyone else is now safe
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
				}
				if(alive==false) {
					mainClass.dino.currentSize--;
				}
				safe = true;
			}
			}
	}
	

}
