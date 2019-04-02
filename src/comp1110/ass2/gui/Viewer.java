package comp1110.ass2.gui;

import comp1110.ass2.Tile;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.FileInputStream;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_LEFT;

/**
 * A very simple viewer for tile placements in the Railroad Ink game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various tile placements.
 */
public class Viewer extends Application {
    /* board layout */
    private static final int VIEWER_WIDTH = 1024;
    private static final int VIEWER_HEIGHT = 768;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    TextField textField;

    private final Group board = new Group();
    private final Group tiles = new Group();


    private int TILE_LENGTH = 70;
    private int BOARD_WIDTH = TILE_LENGTH*9;
    private int BOARD_HEIGHT = TILE_LENGTH*9;
    private int BOARD_X_OFFSET = (VIEWER_WIDTH - BOARD_WIDTH)/2;
    private int BOARD_Y_OFFSET = 20;
    private static final Paint SUBBOARD_FILL = Color.DARKGREY;
    private static final Paint SUBBOARD_STROKE = Color.GREY;
    public static final String HighExit = Viewer.class.getResource(URI_BASE + "HighExit.png").toString();
    public static final String RailExit = Viewer.class.getResource(URI_BASE + "RailExit.png").toString();

    private void makeBoard(){
        Rectangle background = new Rectangle(BOARD_WIDTH,BOARD_HEIGHT);
        background.setFill(SUBBOARD_FILL);
        background.setLayoutX(BOARD_X_OFFSET);
        background.setLayoutY(BOARD_Y_OFFSET);
        background.setStrokeType(StrokeType.CENTERED);
        board.getChildren().add(background);

        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                Rectangle piece = new Rectangle(TILE_LENGTH, TILE_LENGTH);
                piece.setFill(Color.WHITE);
                piece.setStroke(SUBBOARD_STROKE);
                piece.setLayoutX(col*TILE_LENGTH + BOARD_X_OFFSET + TILE_LENGTH);
                piece.setLayoutY(row*TILE_LENGTH +BOARD_Y_OFFSET +TILE_LENGTH);
                board.getChildren().add(piece);
            }
        }

        Image HE = new Image(HighExit);
        Image RE = new Image(RailExit);

        for (int row = 0; row < 7; row++){
            for (int col = 0 ; col < 7; col++){
                if (row == 0) {
                    if (col == 1 || col == 5){
                        ImageView Hexit = new ImageView(HE);
                        Hexit.setX(BOARD_X_OFFSET+TILE_LENGTH*(col+1));
                        Hexit.setY(BOARD_Y_OFFSET);
                        Hexit.setFitWidth(TILE_LENGTH);
                        Hexit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(Hexit);
                    }
                    if (col == 3){
                        ImageView Rexit = new ImageView(RE);
                        Rexit.setX(BOARD_X_OFFSET+TILE_LENGTH*(col+1));
                        Rexit.setY(BOARD_Y_OFFSET);
                        Rexit.setFitWidth(TILE_LENGTH);
                        Rexit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(Rexit);
                    }

                }

                if (row == 6) {
                    if (col == 1 || col == 5){
                        ImageView Hexit = new ImageView(HE);
                        Hexit.setRotate(Hexit.getRotate() + 180);
                        Hexit.setX(BOARD_X_OFFSET+TILE_LENGTH*(col+1));
                        Hexit.setY(BOARD_Y_OFFSET+ 8*TILE_LENGTH);
                        Hexit.setFitWidth(TILE_LENGTH);
                        Hexit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(Hexit);
                    }
                    if (col == 3){
                        ImageView Rexit = new ImageView(RE);
                        Rexit.setRotate(Rexit.getRotate() + 180);
                        Rexit.setX(BOARD_X_OFFSET+TILE_LENGTH*(col+1));
                        Rexit.setY(BOARD_Y_OFFSET + 8*TILE_LENGTH);
                        Rexit.setFitWidth(TILE_LENGTH);
                        Rexit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(Rexit);
                    }

                }

                if (col == 0){
                    if (row == 1 ||row == 5){
                        ImageView Rexit = new ImageView(RE);
                        Rexit.setRotate(Rexit.getRotate() + 270);
                        Rexit.setX(BOARD_X_OFFSET);
                        Rexit.setY(BOARD_Y_OFFSET+TILE_LENGTH*(row+1));
                        Rexit.setFitWidth(TILE_LENGTH);
                        Rexit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(Rexit);
                    }
                    if (row == 3){
                        ImageView Hexit = new ImageView(HE);
                        Hexit.setRotate(Hexit.getRotate() + 270);
                        Hexit.setX(BOARD_X_OFFSET);
                        Hexit.setY(BOARD_Y_OFFSET+TILE_LENGTH*(row+1));
                        Hexit.setFitWidth(TILE_LENGTH);
                        Hexit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(Hexit);
                    }
                }

                if (col == 6){
                    if (row == 1 ||row == 5){
                        ImageView Rexit = new ImageView(RE);
                        Rexit.setRotate(Rexit.getRotate() + 90);
                        Rexit.setX(BOARD_X_OFFSET+TILE_LENGTH*8);
                        Rexit.setY(BOARD_Y_OFFSET+TILE_LENGTH*(row+1));
                        Rexit.setFitWidth(TILE_LENGTH);
                        Rexit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(Rexit);
                    }
                    if (row == 3){
                        ImageView Hexit = new ImageView(HE);
                        Hexit.setRotate(Hexit.getRotate() + 90);
                        Hexit.setX(BOARD_X_OFFSET+TILE_LENGTH*8);
                        Hexit.setY(BOARD_Y_OFFSET+TILE_LENGTH*(row+1));
                        Hexit.setFitWidth(TILE_LENGTH);
                        Hexit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(Hexit);
                    }
                }
            }
        }

    }

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */





    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
        tiles.getChildren().clear();


        int counter = 0;
        String [] sPlacement = new String[placement.length()/5];
        for (int sub=0; sub < (placement.length()/5); sub++){
            sPlacement [sub] = placement.substring(counter, counter+5);
            counter = counter+5;
        }

        for (int i= 0; i < sPlacement.length; i++){
          Image p = new Image(Viewer.class.getResource(URI_BASE + sPlacement[i].substring(0,2) +".png").toString());
          ImageView tile = new ImageView(p);
          int col = Integer.parseInt(sPlacement[i].substring(3,4));
          char row = sPlacement[i].charAt(2);
          tile.setX(BOARD_X_OFFSET+TILE_LENGTH*(col+1));
          tile.setY(BOARD_Y_OFFSET+TILE_LENGTH*(Character.getNumericValue(row)-9));
          tile.setFitWidth(TILE_LENGTH);
          tile.setFitHeight(TILE_LENGTH);
          switch (sPlacement[i].substring(4,5)){
              default:
                  break;
              case "1":
                  tile.setRotate(tile.getRotate() + 90);
                  break;
              case "2":
                  tile.setRotate(tile.getRotate() + 180);
                  break;
              case "3":
                  tile.setRotate(tile.getRotate() + 270);
                   break;
              case "4":
                  tile.nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
                  break;
              case "5":
                  tile.nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
                  tile.setRotate(tile.getRotate() + 90);
                  break;
              case "6":
                  tile.nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
                  tile.setRotate(tile.getRotate() + 180);
                  break;
              case "7":
                  tile.nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
                  tile.setRotate(tile.getRotate() + 270);
                  break;
          }


          tiles.getChildren().add(tile);
        }
    }


    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        tiles.getChildren().clear();
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(e -> {
            makePlacement(textField.getText());
            textField.clear();
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StepsGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(controls);
        root.getChildren().add(board);
        root.getChildren().add(tiles);


        makeControls();
        makeBoard();


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
