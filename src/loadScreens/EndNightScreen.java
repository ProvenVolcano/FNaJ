package loadScreens;

import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class for night complete end screen
 */
public class EndNightScreen {

    private Scene scene;
    private Pane root;
    private VBox vbox;
    private Text amText;
    private Text five;
    private Text six;
    private Rectangle topRec;
    private Rectangle bottomRec;
    private TranslateTransition moveTransition;

    public EndNightScreen(int width, int height, Stage stage, Scene scene) {
        root = new Pane();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        this.scene = new Scene(root, width, height);

        amText = new Text("  AM");
        amText.setFont(Font.font("Courier New", FontWeight.BOLD, 80));
        amText.setFill(Color.WHITE);
        amText.setX((double) width / 2 - amText.getBoundsInLocal().getWidth() / 2);
        amText.setY((double) height / 2 - amText.getBoundsInLocal().getHeight() / 2 + 70);
        root.getChildren().add(amText);

        five = new Text("5");
        five.setFont(Font.font("Courier New", FontWeight.BOLD, 80));
        five.setFill(Color.WHITE);

        six = new Text("6");
        six.setFont(Font.font("Courier New", FontWeight.BOLD, 80));
        six.setFill(Color.WHITE);

        vbox = new VBox();
        vbox.setLayoutX(623);
        vbox.setLayoutY(363);
        vbox.getChildren().addAll(five, six);

        bottomRec = new Rectangle(width, (double) height / 2);
        bottomRec.setY(height - bottomRec.getBoundsInLocal().getHeight() + 35);
        bottomRec.setFill(Color.BLACK);

        topRec = new Rectangle(width, (double) height / 2 - 35);
        topRec.setFill(Color.BLACK);

        root.getChildren().addAll(vbox, bottomRec, topRec);

        moveTransition = new TranslateTransition(new Duration(5500), vbox);
        moveTransition.setOnFinished(e -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            stage.setScene(scene);
        });
        moveTransition.setByY(-92);
    }

    public Scene getScene() {
        return scene;
    }

    /**
     * Plays the 6AM animation
     */
    public void play() {
        moveTransition.play();
    }
}
