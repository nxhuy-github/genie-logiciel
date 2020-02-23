package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;

import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.controller.Field;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javafx.scene.shape.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;
import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.model.*;
import fr.univ_lyon1.info.m1.balleauprisonnier_mvn.view.*;

/**
 * Classe principale de l'application s'appuie sur javafx pour le rendu
 */

public class App extends Application {

	/**
	 * En javafx start() lance l'application
	 *
	 * On cree le SceneGraph de l'application ici
	 * 
	 * @see http://docs.oracle.com/javafx/2/scenegraph/jfxpub-scenegraph.htm
	 *
	 */

	@Override
	public void start(Stage stage) throws Exception {
		// Nom de la fenetre
		stage.setTitle("BalleAuPrisonnier");
		Group root = new Group();
		Scene scene = new Scene(root);

		// On cree le terrain de jeu et on l'ajoute a la racine de la scene
		Field gameField = new Field(scene, 600, 600);
		root.getChildren().add(gameField);
		for (int i = 0; i < gameField.getCharacters().size(); i++) {
			root.getChildren().add(gameField.getCharacters().get(i).sprite);
		}
		Vector<Cloud> a = gameField.getCloud();

		for (int i = 0; i < a.size(); i++) {
			root.getChildren().add(a.get(i));
		}

		root.getChildren().add(gameField.getAnimationSprite());

		for (int i = 0; i < gameField.getWall().getRect().size(); i++)
			root.getChildren().add(gameField.getWall().getRect().get(i));

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
