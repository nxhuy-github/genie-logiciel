package fr.univ_lyon1.info.m1.balleauprisonnier_mvn.view;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends ImageView {

	private int cells;
	private final Rectangle2D[] Clips;
	private final IntegerProperty frameCounter = new SimpleIntegerProperty(0);
	private int lastIndex;
	private Timeline timeline;
	private double time;
	private double x, y;
	private Image img;

	public SpriteAnimation(Image animationImage, int Cells, double frameTime,
			double X, double Y) {
		this.img = animationImage;
		this.x = X;
		this.y = Y;
		this.cells = Cells;
		this.time = frameTime;
		double cellWidth = animationImage.getWidth() / Cells;// animationImage.getWidth()
																// / numCells;
																// //64x64
		double cellHeight = animationImage.getHeight();// animationImage.getHeight()
														// / numRows;

		Clips = new Rectangle2D[cells];
		for (int i = 0; i < cells; i++) {
			Clips[i] = new Rectangle2D(i * cellWidth, cellHeight * 0,
					cellWidth, cellHeight);
		}

		setImage(null);

		timeline = new Timeline(new KeyFrame(Duration.millis(time), event -> {
			frameCounter.set((frameCounter.get() + 1) % cells);
			setViewport(Clips[frameCounter.get()]);
		}));

	}

	public void playContinuously(double x, double y) {
		setImage(this.img);
		this.setX(x);
		this.setY(y);
		setViewport(Clips[0]);
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(0),
				ae -> setFrame()), new KeyFrame(Duration.millis(time * cells),
				ae -> removeImage()));
		timeline.play();
	}

	public void setFrame() {

		frameCounter.set(0);
		timeline.setCycleCount(5);
		timeline.playFromStart();
	}

	public void removeImage() {

		this.setImage(null);
	}

}