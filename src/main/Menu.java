package main;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import loadScreens.EndNightScreen;
import loadScreens.StartNightScreen;

import java.io.*;

/**
 * The main menu where the player selects a night
 */
public class Menu {

    private final int WIDTH;
    private final int HEIGHT;

    private int nightUnlocked;

    private Stage stage;
    private Scene scene;
    private AnchorPane root;

    private ImageView background;
    private ImageView bgImage;
    private Text title;
    private Text newGameText;
    private Text continueText;
    private Text night6Text;
    private Text customNightText;
    private Text exitText;
    private Text continueText2;

    private VBox buttonLayout;

    private Image starImage;
    private HBox stars;

    public Menu(Stage stage) {
        loadSave();

        WIDTH = 16 * 90;
        HEIGHT = 9 * 90;

        this.stage = stage;

        root = new AnchorPane();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        scene = new Scene(root, WIDTH, HEIGHT);

        bgImage = new ImageView("file:res/images/jecmen.png");
        bgImage.setOpacity(0.25);
        AnchorPane.setTopAnchor(bgImage, 0.0);
        AnchorPane.setRightAnchor(bgImage, 0.0);
        root.getChildren().add(bgImage);

        background = new ImageView("file:res/images/static.gif");
        background.setOpacity(0.15);
        root.getChildren().add(background);

        title = new Text("Five \nNights \nat \nJečná");
        title.setFill(Color.rgb(225, 8, 8));
        title.setFont(Font.font("Courier New", FontWeight.BOLD, 60));
        title.setLineSpacing(7);
        AnchorPane.setTopAnchor(title, 50.0);
        AnchorPane.setLeftAnchor(title, 120.0);

        stars = new HBox();
        stars.setSpacing(20);
        AnchorPane.setTopAnchor(stars, 340.0);
        AnchorPane.setLeftAnchor(stars, 120.0);
        starImage = new Image("file:res/images/star.png");
        updateStars();

        createButtons();

        buttonLayout = new VBox();
        buttonLayout.setSpacing(17);
        AnchorPane.setTopAnchor(buttonLayout, 450.0);
        AnchorPane.setLeftAnchor(buttonLayout, 120.0);

        buttonUpdate();

        root.getChildren().addAll(buttonLayout, stars, title);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Updates the number of stars displayed on the menu, depending on how many nights the player completed
     */
    private void updateStars() {
        stars.getChildren().clear();
        for(int i = 0; i < nightUnlocked - 5; i++) {
            ImageView star = new ImageView(starImage);
            star.setFitWidth(90);
            star.setFitHeight(90);
            stars.getChildren().add(star);
        }
    }

    /**
     * Loads the last reached night from a file
     */
    private void loadSave() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("res/save.txt"));
            nightUnlocked = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            nightUnlocked = 1;
        }
    }

    /**
     * Saves the last night reached into a file
     */
    private void saveProgress() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("res/save.txt"));
            bw.write(String.valueOf(nightUnlocked));
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes all the buttons in the menu
     */
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

        int tempNight = Math.min(nightUnlocked, 5);
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
            int tempNight2 = Math.min(nightUnlocked, 5);
            startGame(tempNight2);
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

        customNightText = new Text("20/20/20");
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
            saveProgress();
            System.exit(0);
        });
        exitText.setOnMouseEntered(e -> {
            exitText.setUnderline(true);
        });
        exitText.setOnMouseExited(e -> {
            exitText.setUnderline(false);
        });
    }

    /**
     * Starts a night
     * @param night - number of the night
     */
    private void startGame(int night) {
        StartNightScreen startScreen = new StartNightScreen(WIDTH, HEIGHT, night);
        stage.setScene(startScreen.getScene());
        Game game = new Game(stage, WIDTH, HEIGHT, night, this);

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        game.startGame();
    }

    /**
     * Displays the end screen and increases reached night
     * @param playedNight - number of night that the player played
     */
    public void endNight(int playedNight) {
        Platform.runLater(() -> {
            EndNightScreen endScreen = new EndNightScreen(WIDTH, HEIGHT, stage, scene);
            stage.setScene(endScreen.getScene());
            endScreen.play();
        });
        increaseNight(playedNight);
        int tempNight = Math.min(nightUnlocked, 5);
        continueText2.setText("(Night " + tempNight + ")");
        removeButtons();
        buttonUpdate();
        updateStars();
    }

    /**
     * Increases reached night
     * @param playedNight- number of night that the player played
     */
    private void increaseNight(int playedNight) {
        if(nightUnlocked < 6 && playedNight < 6) {
            nightUnlocked++;
        } else if(playedNight > 5 && nightUnlocked < 8) {
            nightUnlocked++;
        }
    }

    /**
     * Removes all the buttons from the menu
     */
    private void removeButtons() {
        root.getChildren().remove(continueText2);
        buttonLayout.getChildren().clear();
    }

    /**
     * Updates the displayed buttons depending on the reached night
     */
    private void buttonUpdate() {

        buttonLayout.getChildren().add(newGameText);

        if(nightUnlocked > 1) {
            buttonLayout.getChildren().add(continueText);
            root.getChildren().add(continueText2);
        }
        if(nightUnlocked > 5) {
            buttonLayout.getChildren().add(night6Text);
        }
        if(nightUnlocked > 6) {
            buttonLayout.getChildren().add(customNightText);
        }

        buttonLayout.getChildren().add(exitText);
    }
}
