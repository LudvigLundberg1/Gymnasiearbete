
package mainPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.WindowConstants;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class mainClass extends JPanel implements ActionListener, KeyListener{

	
	static Double width = 700.0;
	static Double height = 700.0;
    static Double distance;
    static Group dino = new Group();
    static Group frog = new Group();
   
	Random rnd = new Random();
	Timer tm = new Timer(20, this);
	
 
	Color bg = new Color(255,255,255);



	
	public mainClass(){

		tm.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
		for(int i = 0; i < 10; i ++) {
			if(dino.id[i].gene) {
				g.setColor(Color.green);
			} else { g.setColor(Color.red);}
			g.fillOval(dino.id[i].x.intValue(),dino.id[i].y.intValue(),10,10);
		}
		for(int i = 0; i < 10; i ++) {
			if(frog.id[i].gene) {
				g.setColor(Color.green);
			} else { g.setColor(Color.red);}
			
			g.fillOval(frog.id[i].x.intValue(),frog.id[i].y.intValue(),20,20);
		}
		

	}
	
	public void actionPerformed(ActionEvent e){

		dino.movement();
		frog.movement();
    repaint(); //have it before brackets to freeze
		} 
	
	
	public void keyPressed(KeyEvent e){
		
				
int c = e.getKeyCode();


		if(c == KeyEvent.VK_E ){
			
		}
		if(c == KeyEvent.VK_ENTER){
			
		}		
		if(c == KeyEvent.VK_ESCAPE){
			
		}
	}
	
	public void keyTyper(KeyEvent e){}
	public void keyReleased(KeyEvent e){
int c = e.getKeyCode();
		
		if(c == KeyEvent.VK_A){ 
		
		}
		if(c == KeyEvent.VK_D){
	
		}
		if(c == KeyEvent.VK_W){
		
		}
		if(c == KeyEvent.VK_S){
		
		}
	}
	public static void main(String[] args) throws IOException {

		mainClass t = new mainClass();
		JFrame jf = new JFrame();
		
			
		jf.setTitle("Tutorial");
		jf.setSize(width.intValue(), height.intValue());
	    jf.setVisible(true);
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf.add(t);
	    
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	public Double distance(Double x1, Double y1, Double x2, Double y2){
		distance = 0.0;
		distance = Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
		return distance;
	}
}













