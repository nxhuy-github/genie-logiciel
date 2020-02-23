package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model;

import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.view.*;
import javafx.scene.canvas.GraphicsContext;

public interface CharacterFactory {
	public Character createCharacter(GraphicsContext gc, String color, int xInit, int yInit, String side, String image);
}
