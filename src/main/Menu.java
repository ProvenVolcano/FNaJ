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
    private Text title;
    private Text newGameText;
    private Text continueText;
    private Text night6Text;
    private Text customNightText;
    private Text exitText;
    private Text continueText2;

    private VBox buttonLayout;

    public Menu(Stage stage) {
        loadSave();

        WIDTH = 16 * 90;
        HEIGHT = 9 * 90;

        this.stage = stage;

        root = new AnchorPane();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        scene = new Scene(root, WIDTH, HEIGHT);

        background = new ImageView("file:res/static.gif");
        background.setOpacity(0.15);
        root.getChildren().add(background);

        title = new Text("Five \nNights \nat \nJečná");
        title.setFill(Color.rgb(225, 8, 8));
        title.setFont(Font.font("Courier New", FontWeight.BOLD, 60));
        title.setLineSpacing(7);
        AnchorPane.setTopAnchor(title, 50.0);
        AnchorPane.setLeftAnchor(title, 120.0);

        createButtons();

        buttonLayout = new VBox();
        buttonLayout.setSpacing(17);
        AnchorPane.setTopAnchor(buttonLayout, 450.0);
        AnchorPane.setLeftAnchor(buttonLayout, 120.0);

        buttonLayout.getChildren().addAll(newGameText);

        if(night > 1) {
            buttonLayout.getChildren().add(continueText);
            root.getChildren().add(continueText2);
        }
        if(night > 5) {
            buttonLayout.getChildren().add(night6Text);
        }
        if(night > 6) {
            buttonLayout.getChildren().add(customNightText);
        }

        buttonLayout.getChildren().add(exitText);

        root.getChildren().add(buttonLayout);
        root.getChildren().add(title);

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
        newGameText.setOnMouseEntered(e -> {
            newGameText.setUnderline(true);
        });
        newGameText.setOnMouseExited(e -> {
            newGameText.setUnderline(false);
        });

        int tempNight = night;
        if(night > 5) {
            tempNight = 5;
        }
        continueText2 = new Text("(Night " + tempNight + ")");
        continueText2.setFont(font);
        continueText2.setFill(Color.WHITE);
        continueText2.setX(325);
        continueText2.setY(547);
        continueText2.setVisible(false);

        continueText = new Text("Continue");
        continueText.setFont(font);
        continueText.setFill(Color.WHITE);
        continueText.setOnMouseClicked(e -> {
            startGame(night);
        });
        continueText.setOnMouseEntered(e -> {
            continueText.setUnderline(true);
            continueText2.setVisible(true);
        });
        continueText.setOnMouseExited(e -> {
            continueText.setUnderline(false);
            continueText2.setVisible(false);
        });

        night6Text = new Text("6th Night");
        night6Text.setFont(font);
        night6Text.setFill(Color.WHITE);
        night6Text.setOnMouseClicked(e -> {
            startGame(6);
        });
        night6Text.setOnMouseEntered(e -> {
            night6Text.setUnderline(true);
        });
        night6Text.setOnMouseExited(e -> {
            night6Text.setUnderline(false);
        });

        customNightText = new Text("Custom Night");
        customNightText.setFont(font);
        customNightText.setFill(Color.WHITE);
        customNightText.setOnMouseClicked(e -> {
            startGame(7);
        });
        customNightText.setOnMouseEntered(e -> {
            customNightText.setUnderline(true);
        });
        customNightText.setOnMouseExited(e -> {
            customNightText.setUnderline(false);
        });

        exitText = new Text("Exit");
        exitText.setFont(font);
        exitText.setFill(Color.WHITE);
        exitText.setOnMouseClicked(e -> {
            System.exit(0);
        });
        exitText.setOnMouseEntered(e -> {
            exitText.setUnderline(true);
        });
        exitText.setOnMouseExited(e -> {
            exitText.setUnderline(false);
        });
    }

    private void startGame(int night) {
        Game game = new Game(stage, WIDTH, HEIGHT, night);
    }
}
