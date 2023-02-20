package mainPackage;
import java.nio.file.Files;
import java.nio.file.Path;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.WindowConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
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

	static Double widthWindow = 700.0;
	static Double heightWindow = 700.0;
	static Double width = widthWindow/2;
	static Double height = heightWindow/2;
    static Double distance;
    
    static Group dino[] = new Group[4];
    static Predator predator = new Predator();
	Individual ind;


    static int maxPopulation = 80; //probability of getting enough food for each individual is 100/current Population 
	static Random rnd = new Random();
	static String a;
	static int populationEveryGeneration[] = new int[4];
 
	static int numOfSims = 0;
	static int generations;
	static int generationTime = 0;
	static int storeTotalPopulation;
	static Double winningShareOfAltruists;
	static Double altruistRateUniversal = 0.0;
	
	Color bg = new Color(255,255,255);
	Timer tm = new Timer(0, this);
	
	
	public mainClass(){
		
	    setupGroup();

		tm.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
 
        // Defining the file name of the file
   	
	}
	
	public static void setupGroup() {
		for(int i = 0; i < dino.length; i++) {
			 dino[i] = new Group();
			 dino[i].groupIndex = i;
			 dino[i].createIndividuals();
		}
		generations = 0;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//statistik från samtliga grupper
		
		for(int i = 0; i < dino.length; i++) {
			g.drawString("Group " + i + " Population: " + Integer.toString(dino[i].currentSize) + "Share of Altruists: " + Double.toString(dino[i].shareOfAltruists), 400, 20 + 30*i);
		}
		
		//en enda grupp visas
for(int i = 0; i < 1; i++) 
	{	
	
		//statistics
	
		g.drawString("Population: " + Integer.toString(dino[i].currentSize), 20, 20);
		g.drawString("Generations: " + Integer.toString(generations), 20, 50);
		g.drawString("Share of altruists: " + Double.toString(dino[i].shareOfAltruists), 20, 80);
		if(dino[i].safeFromPredator) 
		{
		g.setColor(Color.blue);
		g.drawString("ALL SAFE", 20, 110);
		}
		//graph
		//g.fillRect(200, 100-dino[i].currentSize, 5,5);
	
		
		
		for(int j = 0; j < dino[i].id.length; j ++) {
			ind = dino[i].id[j]; //skapar referens
		

			if(ind.alive) {
				
				if(ind.gene) {
				g.setColor(Color.green);
				} else { g.setColor(Color.red);}
		
				g.fillOval(ind.x.intValue()-ind.diameterOfIndividual/2,ind.y.intValue()-ind.diameterOfIndividual/2,ind.diameterOfIndividual,ind.diameterOfIndividual);
				g.drawOval(ind.x.intValue()-ind.radius.intValue(), ind.y.intValue()-ind.radius.intValue(), ind.radius.intValue()*2, ind.radius.intValue()*2);
				if(ind.safe == false) {
				 a = "not safe";
				} else {
				 a = "safe";

				}
				g.drawString(a, ind.x.intValue(), ind.y.intValue());


				}
			}
		//predator
		g.setColor(Color.black);
		g.fillOval(predator.x.intValue(), predator.y.intValue(),20,20);
		
		
	}

		
}
	
	
	public void actionPerformed(ActionEvent e){
		

			
		
		
		
		if(numOfSims == 400) {
			
			System.out.println("Simulation done.");
			
			altruistRateUniversal += 0.1; 
			setupGroup();

			System.out.println("New simulation with altruistRate: " + dino[0].altruistRate);
			
			numOfSims = 0; //resetting number of simulations
			
			//tm.stop();
			
			} 
		
		
//Collecting data from the surviving group		
int surviving = 0; 

for(int i = 0; i < dino.length; i++) {
	if(dino[i].currentSize>0) {
		surviving++;	
	}
}


if(surviving==1) {
	for(int i = 0; i < dino.length; i++) {
		if(dino[i].currentSize>0) {
			dino[i].calculateShareOfAltruists();
			System.out.println(dino[i].shareOfAltruists);
			//System.out.println("generations: " + generations);
		
			}
		}
	// New groups are created after every group has been checked.
	numOfSims ++; 
	setupGroup();

	} 

		
		
		
		for(int i = 0; i < dino.length; i++) {
			if(dino[i].currentSize > 0) { //if the group is still alive
		dino[i].action();
			}
		}
		
		if(generationTime==100) {
			storeTotalPopulation = 0;

			for(int i = 0; i < dino.length; i++) {
				storeTotalPopulation += dino[i].currentSize;
			}
			if(storeTotalPopulation>maxPopulation) {
				for(int i = 0; i < dino.length; i++) {
					if(dino[i].currentSize > 0) {
						dino[i].malnutrition();
					}
				}
			}
		}
			
			if(generationTime==200) {
			for(int i = 0; i < dino.length; i++) {
				if(dino[i].currentSize>0) {
					dino[i].replication();
				}
			}
			generationTime = 0;
			generations ++; 
	
			}
			
			generationTime ++;
			
			predator.action();

		
		//repaint(); //have it in brackets to freeze
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
		jf.setSize(widthWindow.intValue(), heightWindow.intValue());
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf.add(t);
	    jf.setVisible(true);

		
		
	    
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	public static Double distance(Double x1, Double y1, Double x2, Double y2){
		distance = 0.0;
		distance = Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
		return distance;
	}
	
	public static boolean risk(Double r) {
		if(r>rnd.nextDouble(1)) {
return true;
		} else {return false; }
	}
	
	
}
