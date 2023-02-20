package mainPackage;
import java.util.Random;

public class Predator {
	Double x, y, v, vx, vy, angle;
	static Random rnd = new Random();

		public Predator(){
			this.x = rnd.nextDouble(mainClass.width);
			this.y = rnd.nextDouble(mainClass.height);
			
			this.v = 50.0; //set to 50.0     (this should be constant, calculate the components with trig)
			this.angle = rnd.nextDouble(2*Math.PI);
			this.vx = v*Math.sin(angle); 
			this.vy = v*Math.cos(angle);
		}
	
	public void action() {
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
