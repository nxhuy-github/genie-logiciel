package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model;

import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.view.*;

import java.util.ArrayList;
import java.util.Random;

public class InputHandler {
	private ArrayList<String> vStr;
	
	public InputHandler(ArrayList<String> vS){
		vStr = vS;
	}
	
	public Command handleInput(int i){
		if(i == 1){
			if(vStr.contains("Q")){
				return new MoveLeftCommand();
			}else if(vStr.contains("D")){
				return new MoveRightCommand();
			}else if(vStr.contains("Z")){
				return new TurnLeftCommand();
			}else if(vStr.contains("S")){
				return new TurnRightCommand();
			}else if(vStr.contains("SPACE")){
				return new ShootCommand();
			}else{
				return null;
			}
		}else if (i == 0){
			if(vStr.contains("LEFT")){
				return new MoveLeftCommand();
			}else if(vStr.contains("RIGHT")){
				return new MoveRightCommand();
			}else if(vStr.contains("UP")){
				return new TurnLeftCommand();
			}else if(vStr.contains("DOWN")){
				return new TurnRightCommand();
			}else if(vStr.contains("F")){
				return new ShootCommand();
			}else{
				return null;
			}
		}else{
			return new AutoCommand();
		}
	}
}
