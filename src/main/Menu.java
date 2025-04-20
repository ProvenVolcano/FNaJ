package main;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Menu {

    private final int WIDTH;
    private final int HEIGHT;

    private int night;

    private Stage stage;
    private Scene scene;
    private AnchorPane root;

    private ImageView background;
    private Text newGameText;
    private Text continueText;
    private Text night6Text;
    private Text customNightText;
    private Text exitText;

    private VBox buttonLayout;

    public Menu(Stage stage) {
        loadSave();

        WIDTH = 16 * 90;
        HEIGHT = 9 * 90;

        this.stage = stage;

        root = new AnchorPane();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        scene = new Scene(root, WIDTH, HEIGHT);

        createButtons();

        buttonLayout = new VBox();
        buttonLayout.setSpacing(15);
        AnchorPane.setBottomAnchor(buttonLayout, 100.0);
        AnchorPane.setLeftAnchor(buttonLayout, 120.0);
        buttonLayout.getChildren().addAll(newGameText, continueText, night6Text, customNightText, exitText);

        background = new ImageView("file:res/static.gif");
        background.setOpacity(0.15);
        root.getChildren().add(background);
        root.getChildren().add(buttonLayout);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    private void loadSave() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("res/save.txt"));
            night = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            night = 1;
            System.out.println(e.getMessage());
        }
    }

    private void createButtons() {

        Font font = Font.font("Courier New", FontWeight.BOLD, 40);

        newGameText = new Text("New Game");
        newGameText.setFont(font);
        newGameText.setFill(Color.WHITE);
        newGameText.setOnMouseClicked(e -> {
            startGame(1);
        });

        continueText = new Text("Continue");
        continueText.setFont(font);
        continueText.setFill(Color.WHITE);
        continueText.setOnMouseClicked(e -> {
            startGame(night);
        });

        night6Text = new Text("6th Night");
        night6Text.setFont(font);
        night6Text.setFill(Color.WHITE);
        night6Text.setOnMouseClicked(e -> {
            startGame(6);
        });

        customNightText = new Text("Custom Night");
        customNightText.setFont(font);
        customNightText.setFill(Color.WHITE);
        customNightText.setOnMouseClicked(e -> {
            startGame(7);
        });

        exitText = new Text("Exit");
        exitText.setFont(font);
        exitText.setFill(Color.WHITE);
        exitText.setOnMouseClicked(e -> {
            System.exit(0);
        });
    }

    private void startGame(int night) {
        Game game = new Game(stage, WIDTH, HEIGHT, night);
    }
}
