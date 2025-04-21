package loadScreens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StartNightScreen {

    private Scene scene;
    private StackPane root;
    private VBox vbox;
    private Text nightText;
    private Text hourText;

    public StartNightScreen(int width, int height, int night) {

        root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        scene = new Scene(root, width, height);

        hourText = new Text("12:00 AM");
        hourText.setFont(Font.font("Courier New", FontWeight.BOLD, 40));
        hourText.setFill(Color.WHITE);

        nightText = new Text(night + numberEnding(night) + " Night");
        nightText.setFont(Font.font("Courier New", FontWeight.BOLD, 40));
        nightText.setFill(Color.WHITE);

        vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hourText, nightText);

        root.getChildren().add(vbox);
    }

    public Scene getScene() {
        return scene;
    }

    private String numberEnding(int number) {
        return switch (number) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }
}
