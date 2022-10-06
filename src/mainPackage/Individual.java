package mainPackage;

import java.util.Random;

public class Individual {
	boolean gene, inDanger; 
	Double x, y, vx, vy;
	static Random rnd = new Random();

	public Individual() {
		
			if(rnd.nextDouble(1) < 0.9) {
				this.gene = false; //selfish
			} else { this.gene = true; }
			
			this.x = rnd.nextDouble(700);
			this.y = rnd.nextDouble(700);
			
			this.vx = rnd.nextDouble(2)-1;
			this.vy = rnd.nextDouble(2)-1;
			
			this.inDanger = false;
	}
	
	public void movement() {
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
}
