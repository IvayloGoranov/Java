package application;

import java.io.IOException;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelOneController {
	
	public ToggleButton toggleStart;
	public Button buttonFinish;
	public Rectangle obstacle2;
	public Rectangle obstacle3;
	public Arc obstacle1;
	public Circle obstacle4;
	
	private PathTransition animation1 = new PathTransition();
	private PathTransition animation2 = new PathTransition();
	private RotateTransition animation3 = new RotateTransition();
	private ScaleTransition animation4 = new ScaleTransition();
	
	public void onMouseExited (MouseEvent event) throws IOException {
		
		if (!this.toggleStart.isSelected()) {
			
			return;
		}
		
		double x = event.getX();
		double y = event.getY();
		
		if (this.contains(this.buttonFinish, x, y)) {
			
			return;
		}
		
		if (this.contains(this.toggleStart, x, y)) {
			
			return;
		}
		
		Parent root = FXMLLoader.load(getClass().getResource("loseDialog.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Game Over!");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		this.stopGame();
	}
	
	public void onStart (ActionEvent actionEvent) {
		
		if (this.toggleStart.isSelected()) {
			
			this.toggleStart.setText("Stop");
			
			startGame();
		} else {
			
			this.toggleStart.setText("Start");
			this.stopGame();
		}
	}

	public void onFinish (ActionEvent actionEvent) throws IOException {
		
		if (!this.toggleStart.isSelected()) {
			
			return;
		}
		
		Parent root = FXMLLoader.load(getClass().getResource("loseDialog.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Game Over!");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		this.toggleStart.setSelected(false);
		this.toggleStart.setText("Start");
	}
	
	private void startGame() {
		
		this.animation1.setNode(this.obstacle2);
		Path path1 = new Path();
		path1.getElements().add(new MoveTo(obstacle2.getWidth() / 2, obstacle2.getHeight() / 2));
		path1.getElements().add(new HLineTo(170.00));
		this.animation1.setPath(path1);
		this.animation1.setDuration(Duration.seconds(2.0));
		this.animation1.setAutoReverse(true);
		this.animation1.setCycleCount(Timeline.INDEFINITE);
		this.animation1.play();
		
		this.animation2.setNode(this.obstacle3);
		Path path2 = new Path();
		path2.getElements().add(new MoveTo(obstacle3.getWidth() / 2, obstacle3.getHeight() / 2));
		path2.getElements().add(new HLineTo(-70.00));
		this.animation2.setPath(path2);
		this.animation2.setDuration(Duration.seconds(2.0));
		this.animation2.setAutoReverse(true);
		this.animation2.setCycleCount(Timeline.INDEFINITE);
		this.animation2.play();
		
		this.animation3.setNode(this.obstacle1);
		this.animation3.setDuration(Duration.seconds(4.0));
		this.animation3.setCycleCount(Timeline.INDEFINITE);
		this.animation3.setToAngle(360.0);
		this.animation3.play();
		
		this.animation4.setNode(this.obstacle4);
		this.animation4.setDuration(Duration.seconds(6.0));
		this.animation4.setCycleCount(Timeline.INDEFINITE);
		this.animation4.setByX(6.0);
		this.animation4.setByY(6.0);
		this.animation4.play();
	}
	
	private void stopGame() {
		
		this.toggleStart.setSelected(false);
		this.toggleStart.setText("Start");
		
		this.animation1.jumpTo(Duration.ZERO);
		this.animation1.stop();
		
		this.animation2.jumpTo(Duration.ZERO);
		this.animation2.stop();
		
		this.animation3.jumpTo(Duration.ZERO);
		this.animation3.stop();
		
		this.animation4.jumpTo(Duration.ZERO);
		this.animation4.stop();
	}
	
	private boolean contains(Labeled item, double x, double y) {
		
		double tolerance = 2.0;
		double top = item.getLayoutY() - tolerance;;
		double bottom = item.getLayoutY() + item.getHeight() + tolerance;
		double left = item.getLayoutX() - tolerance;
		double right = item.getLayoutX() + item.getWidth() + tolerance;
		
		return top <= y && y <= bottom && left <= x && x <= right;
	}
}
