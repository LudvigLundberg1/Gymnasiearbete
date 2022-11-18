package mainPackage;
import java.util.Random;

public class Predator {
	Double x, y, v, vx, vy;
	static Random rnd = new Random();

		public Predator(){
			this.x = rnd.nextDouble(700);
			this.y = rnd.nextDouble(700);
			
			this.v = 10.0;
			this.vx = v*(rnd.nextDouble(2)-1);
			this.vy = v*(rnd.nextDouble(2)-1);
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
