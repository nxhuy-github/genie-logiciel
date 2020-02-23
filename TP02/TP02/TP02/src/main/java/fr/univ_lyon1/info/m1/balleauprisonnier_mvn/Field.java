package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;


import java.util.ArrayList;
import java.util.Vector;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {
	
	/** Joueurs */
	Player [] joueurs = new Player[2];
	/** Computers */
	Computer [] computers = new Computer[4];
	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traÃ§ant les evenements */
    ArrayList<String> input = new ArrayList<String>();
    
    Vector<Character> vC = new Vector<Character>();

    final GraphicsContext gc;
    final int width;
    final int height;
    
    Vector<Vector<Projectile>> vBalls;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     * 
     * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public Field(Scene scene, int w, int h) 
	{
		super(w, h); 
		width = w;
		height = h;
		vBalls = new Vector<Vector<Projectile>>();
		
		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);
		
        gc = this.getGraphicsContext2D();
        
        /** On initialise le terrain de jeu */
        CharacterFactory cF = new PlayerFactory();
        Character c = cF.createCharacter(gc, colorMap[0], w/2, h-50, "bottom");
        vC.add(c);
        c = cF.createCharacter(gc, colorMap[1], w/2, 20, "top");
        vC.add(c);
        
        for(int i = 0; i < 4; i++){
        	cF = new ComputerFactory();
        	if(i % 2 == 0){
        		c = cF.createCharacter(gc, colorMap[0], w/(3 + i), h-50, "bottom");
        	}else{
        		c = cF.createCharacter(gc, colorMap[1], w/(3 + i), 20, "top");
        	}
        	vC.add(c);
        }
        
//    	joueurs[0] = new Player(gc, colorMap[0], w/2, h-50, "bottom");
//    	joueurs[0].display();
//    	computers[0] = new Computer(gc, colorMap[0], w/3, h-50, "bottom");
//    	computers[0].display();
//    	computers[1] = new Computer(gc, colorMap[0], w/4, h-50, "bottom");
//    	computers[1].display();
//
//    	joueurs[1] = new Player(gc, colorMap[1], w/2, 20, "top");
//    	joueurs[1].display();
//    	computers[2] = new Computer(gc, colorMap[0], w/3, 20, "top");
//    	computers[2].display();
//    	computers[3] = new Computer(gc, colorMap[0], w/4, 20, "top");
//    	computers[3].display();

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute a la liste d'input
	     *   
	     */
	    this.setOnKeyPressed(
	    		new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            // only add once... prevent duplicates
	    	            if ( !input.contains(code) )
	    	                input.add( code );
	    	        }
	    	    });

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est relachee on l'enleve de la liste d'input
	     *   
	     */
	    this.setOnKeyReleased(
	    	    new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            input.remove( code );
	    	        }
	    	    });
	    
	    /** 
	     * 
	     * Boucle principale du jeu
	     * 
	     * handle() est appelee a chaque rafraichissement de frame
	     * soit environ 60 fois par seconde.
	     * 
	     */
	    new AnimationTimer() 
	    {
	        public void handle(long currentNanoTime)
	        {	 
	            // On nettoie le canvas a chaque frame
	            gc.setFill( Color.LIGHTGRAY);
	            gc.fillRect(0, 0, width, height);
	        	
	            // Deplacement et affichage des joueurs
	        	for (int i = 0; i < joueurs.length; i++) 
	    	    {
	        		if (i==0 && input.contains("LEFT"))
	        		{
	        			vC.get(i).moveLeft();
	        			//joueurs[i].moveLeft();
	        		} 
	        		if (i==0 && input.contains("RIGHT")) 
	        		{
	        			vC.get(i).moveRight();
	        			//joueurs[i].moveRight();	        			
	        		}
	        		if (i==0 && input.contains("UP"))
	        		{
	        			vC.get(i).turnLeft();
	        			//joueurs[i].turnLeft();
	        		} 
	        		if (i==0 && input.contains("DOWN")) 
	        		{
	        			vC.get(i).turnRight();
	        			//joueurs[i].turnRight();	        			
	        		}
	        		if (i==0 && input.contains("F")){
	        			vC.get(i).shoot();
	        			//joueurs[i].shoot();
					}
	        		if (i==1 && input.contains("Q")) // French's Keyboard is ZQSD instead of WASD
	        		{
	        			vC.get(i).moveLeft();
	        			//joueurs[i].moveLeft();
	        		} 
	        		if (i==1 && input.contains("D")) 
	        		{
	        			vC.get(i).moveRight();
	        			//joueurs[i].moveRight();	        			
	        		}
	        		if (i==1 && input.contains("Z"))
	        		{
	        			vC.get(i).turnLeft();
	        			//joueurs[i].turnLeft();
	        		} 
	        		if (i==1 && input.contains("S")) 
	        		{
	        			vC.get(i).turnRight();
	        			//joueurs[i].turnRight();	        			
	        		}
	        		if (i==1 && input.contains("SPACE")){
	        			vC.get(i).shoot();
	        			//joueurs[i].shoot();
	        			
					}
	        		
	    	    }
//	        	for(int i = 0; i < computers.length; i++){
//	        		computers[i].display();
//	        	}
//	        	for(int i = 0; i < joueurs.length; i++){
//	        		joueurs[i].display();
//	        		vBalls.add(joueurs[i].getBall());
//	        	}
	        	
	        	for(Character c : vC){
	        		c.display();
	        	}
	        	for(int i = 0; i < 2; i++){
	        		vBalls.add(vC.get(i).getBall());
	        	}
	        	
	        	for(Vector<Projectile> vBall : vBalls){
	        		if(!vBall.isEmpty()){
	        			for(Projectile b : vBall){
			        		if(b.getx() > 1000 || b.gety() > 1000 || b.getx() < -1000 || b.gety() < -1000){
			        			vBall.remove(b);
			        		}else{
			        			b.fly();
				        		b.display();	
			        		}
			        		
			        	}	
	        		}
	        	}
	        	
	        	for(int i = 0; i < 2; i++){
	        		for(int j = 0; j < vBalls.size(); j++){
	        			if( i != j){
	        				for(Projectile b : vBalls.get(j)){
	        					if(vC.get(i).isHitted(b)){
	        						vC.get(i).sprite.playDead();
	        					}
	        					
	        				}
	        			}
	        		}
	        	}
	    	}
	     }.start(); // On lance la boucle de rafraichissement 
	     
	}

	public Player[] getJoueurs() {
		return joueurs;
	}
	
	public Computer[] getComputers(){
		return computers;
	}
	
	public Vector<Character> getCharacters(){
		return vC;
	}
	
}
