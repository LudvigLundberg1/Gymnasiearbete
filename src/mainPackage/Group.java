package mainPackage;
import java.util.Random;

public class Group {
	static int size = 10;
	Individual id[] = new Individual[size];
	
		public Group(){
		for(int i = 0; i < size; i++) {
			id[i] = new Individual();
		}
	}
	
	public int move(){
	return 9;
	
	}
	
	public void movement() {
		for(int i = 0; i < size; i++) {
			id[i].movement();
			
		}
	}

}
