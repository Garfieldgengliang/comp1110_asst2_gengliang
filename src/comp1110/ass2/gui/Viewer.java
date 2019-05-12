package comp1110.ass2.gui;

import comp1110.ass2.Piece;
import comp1110.ass2.RailroadInk;
import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    /* Image locations */
    private static final String URI_BASE = "assets/";

    /*Node Groups*/
    TextField textField;
    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group board = new Group();
    private final Group tiles = new Group();
    private final Group tempTiles = new Group();
    private final Group specialTiles = new Group();
    private final Group textFields = new Group();
    private final Group textScore = new Group();

    /*Definitions of Board/Tiles etc... */
    private static final int TILE_LENGTH = 70; //Defining each Tile length
    private static final int BOARD_WIDTH = TILE_LENGTH*9; //Defining board width
    private static final int BOARD_HEIGHT = TILE_LENGTH*9; //defining board height
    private static final int BOARD_X_OFFSET = 20; //Setting offset for the board
    private static final int BOARD_Y_OFFSET = 20;
    private static final Paint SUBBOARD_FILL = Color.DARKGREY;
    private static final Paint SUBBOARD_TILE = Color.GREY;
    private static final String HighExit = Viewer.class.getResource(URI_BASE + "HighExit.png").toString();
    private static final String RailExit = Viewer.class.getResource(URI_BASE + "RailExit.png").toString();
    private static final int ROLL_X_OFFSET = (BOARD_X_OFFSET + BOARD_WIDTH +TILE_LENGTH);
    private static final int ROLL_Y_OFFSET = BOARD_Y_OFFSET + 50;
    private static final int TT_OFFSET = TILE_LENGTH +40;
    private static final int ROLL_BUTTON_OFFSET =ROLL_X_OFFSET;
    private int rounds = 0;
    private int ROLL_HOLDER = 0;
    private int SPECIAL_TILES_LEFT = 3;
    private  String  boardString = "";


    /* Logic of the Game */
    RailroadInk logic;


    /* The Board */
    private void makeBoard(){
        Rectangle background = new Rectangle(BOARD_WIDTH,BOARD_HEIGHT);
        background.setFill(SUBBOARD_FILL);
        background.setLayoutX(BOARD_X_OFFSET);
        background.setLayoutY(BOARD_Y_OFFSET);
        board.getChildren().add(background); //Setting up board by calling Rectangle

        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                Rectangle piece = new Rectangle(TILE_LENGTH, TILE_LENGTH);
                piece.setFill(Color.WHITE);
                piece.setStroke(SUBBOARD_TILE);
                piece.setLayoutX(col*TILE_LENGTH + BOARD_X_OFFSET + TILE_LENGTH);
                piece.setLayoutY(row*TILE_LENGTH +BOARD_Y_OFFSET +TILE_LENGTH);
                board.getChildren().add(piece); //Setting the Grid for Tile placements
            }
        }

        Image HE = new Image(HighExit);
        Image RE = new Image(RailExit);

        for (int row = 0; row < 7; row++){   //Setting up the Board exits depending on row and column
            for (int col = 0 ; col < 7; col++){
                if (row == 0) {
                    if (col == 1 || col == 5){
                        ImageView hExit = new ImageView(HE);
                        hExit.setX(BOARD_X_OFFSET+TILE_LENGTH*(col+1));
                        hExit.setY(BOARD_Y_OFFSET);
                        hExit.setFitWidth(TILE_LENGTH);
                        hExit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(hExit);
                    }
                    if (col == 3){
                        ImageView rExit = new ImageView(RE);
                        rExit.setX(BOARD_X_OFFSET+TILE_LENGTH*(col+1));
                        rExit.setY(BOARD_Y_OFFSET);
                        rExit.setFitWidth(TILE_LENGTH);
                        rExit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(rExit);
                    }

                }

                if (row == 6) {
                    if (col == 1 || col == 5){
                        ImageView hExit = new ImageView(HE);
                        hExit.setRotate(hExit.getRotate() + 180);
                        hExit.setX(BOARD_X_OFFSET+TILE_LENGTH*(col+1));
                        hExit.setY(BOARD_Y_OFFSET+ 8*TILE_LENGTH);
                        hExit.setFitWidth(TILE_LENGTH);
                        hExit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(hExit);
                    }
                    if (col == 3){
                        ImageView rExit = new ImageView(RE);
                        rExit.setRotate(rExit.getRotate() + 180);
                        rExit.setX(BOARD_X_OFFSET+TILE_LENGTH*(col+1));
                        rExit.setY(BOARD_Y_OFFSET + 8*TILE_LENGTH);
                        rExit.setFitWidth(TILE_LENGTH);
                        rExit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(rExit);
                    }

                }

                if (col == 0){
                    if (row == 1 ||row == 5){
                        ImageView rExit = new ImageView(RE);
                        rExit.setRotate(rExit.getRotate() + 270);
                        rExit.setX(BOARD_X_OFFSET);
                        rExit.setY(BOARD_Y_OFFSET+TILE_LENGTH*(row+1));
                        rExit.setFitWidth(TILE_LENGTH);
                        rExit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(rExit);
                    }
                    if (row == 3){
                        ImageView hExit = new ImageView(HE);
                        hExit.setRotate(hExit.getRotate() + 270);
                        hExit.setX(BOARD_X_OFFSET);
                        hExit.setY(BOARD_Y_OFFSET+TILE_LENGTH*(row+1));
                        hExit.setFitWidth(TILE_LENGTH);
                        hExit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(hExit);
                    }
                }

                if (col == 6){
                    if (row == 1 ||row == 5){
                        ImageView rExit = new ImageView(RE);
                        rExit.setRotate(rExit.getRotate() + 90);
                        rExit.setX(BOARD_X_OFFSET+TILE_LENGTH*8);
                        rExit.setY(BOARD_Y_OFFSET+TILE_LENGTH*(row+1));
                        rExit.setFitWidth(TILE_LENGTH);
                        rExit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(rExit);
                    }
                    if (row == 3){
                        ImageView hExit = new ImageView(HE);
                        hExit.setRotate(hExit.getRotate() + 90);
                        hExit.setX(BOARD_X_OFFSET+TILE_LENGTH*8);
                        hExit.setY(BOARD_Y_OFFSET+TILE_LENGTH*(row+1));
                        hExit.setFitWidth(TILE_LENGTH);
                        hExit.setFitHeight(TILE_LENGTH);
                        board.getChildren().add(hExit);
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

    /*Placed Tiles on the board*/
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
        tiles.getChildren().clear(); //clear board before placing

        int counter = 0; //For string slicing
        String [] sPlacement = new String[placement.length()/5]; //Counting number of pieces to place it into String Array
        for (int sub=0; sub < (placement.length()/5); sub++){
            sPlacement [sub] = placement.substring(counter, counter+5);
            counter = counter+5;
        }

        for (int i= 0; i < sPlacement.length; i++){
          Image pType = new Image(Viewer.class.getResource(URI_BASE + sPlacement[i].substring(0,2) +".png").toString()); // loading the image of the piece called
          ImageView tile = new ImageView(pType);
          int col = Integer.parseInt(sPlacement[i].substring(3,4)); //Determining column of string by slicing
          char row = sPlacement[i].charAt(2);   //Determining row of string by slicing
          tile.setX(BOARD_X_OFFSET+TILE_LENGTH*(col+1)); //Setting offset of Col
          tile.setY(BOARD_Y_OFFSET+TILE_LENGTH*(Character.getNumericValue(row)-9)); //Setting offset of row
          tile.setFitWidth(TILE_LENGTH); //Setting width and height of tile
          tile.setFitHeight(TILE_LENGTH);
          switch (sPlacement[i].substring(4,5)){ //Set tile into various orientation
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




    class TempTiles extends ImageView{

        Piece currentPiece; // give the tempTile the piece property

        TempTiles(String piece, int pos){

            this.currentPiece = Piece.valueOf(piece);
            int row = 0;
            int tpos = pos;
            while (tpos > 2){
                tpos -= 2;
                row ++;
            }

            setImage(new Image(Viewer.class.getResource(URI_BASE + piece +".png").toString()));
            if (pos % 2 == 1){
                setLayoutX(ROLL_X_OFFSET);
            }
            else setLayoutX(ROLL_X_OFFSET + TT_OFFSET);


            setLayoutY(ROLL_Y_OFFSET + TT_OFFSET*row);
            setFitWidth(TILE_LENGTH);
            setFitHeight(TILE_LENGTH);
            setPickOnBounds(true);
        }
    }


    class DraggableTiles extends TempTiles{
        double homeX, homeY;
        double mouseX, mouseY;
        int orientation = 0;

        DraggableTiles(String piece, int pos) {
            super(piece, pos);

            homeX = getLayoutX();
            homeY = getLayoutY();

            setOnMouseClicked(event -> {
                
            });

            setOnMousePressed(event -> {
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
            });


            setOnMouseDragged(event -> {
                double movementX = event.getSceneX() - mouseX;
                double movementY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                event.consume();

            });



            setOnMouseReleased(event ->snapToGrid());
        }




        private void snapToGrid(){
            if(onBoard()){
                int xPosition = (int) Math.round((getLayoutX() - BOARD_X_OFFSET)/TILE_LENGTH );
                int yPosition = (int) Math.round((getLayoutY() - BOARD_Y_OFFSET)/TILE_LENGTH );

                char currentCol = (char)(xPosition - 1 + '0');
                char currentRow = (char)(yPosition -1 + 'A');
                int currentOri = orientation%8;
                char currentOrien = (char)(currentOri + '0');

                String currentTilePlacement = this.currentPiece.name() + currentRow + currentCol + currentOrien;
                String boardStringBeforeAdding = boardString;
                boardString = boardString + currentTilePlacement;

                if(RailroadInk.isValidPlacementSequence(boardString)){
                    if (this.currentPiece.name().contains("S")){
                        specialTiles.setDisable(true);  //Disables Placement for special tiles in that turn
                        SPECIAL_TILES_LEFT --;        // Counts Special Tiles Placed
                        setText();
                    }
                    else {
                        ROLL_HOLDER --;                 // Reduces the Roll Holder Count for normal Tiles
                    }
                    setLayoutX(BOARD_X_OFFSET+xPosition*TILE_LENGTH);
                    setLayoutY(BOARD_Y_OFFSET+yPosition*TILE_LENGTH);
                    setDisable(true);
                    getTextScore();
                }
                else{
                    boardString = boardStringBeforeAdding;
                    snapToHome();
                }
            }
            else{
                snapToHome();
            }
        }



        private boolean onBoard() {
            return getLayoutX() > (BOARD_X_OFFSET+TILE_LENGTH) && (getLayoutX() < (BOARD_X_OFFSET+BOARD_WIDTH-TILE_LENGTH))
                    && getLayoutY() > (BOARD_Y_OFFSET+TILE_LENGTH) && (getLayoutY() < (BOARD_Y_OFFSET+BOARD_HEIGHT-TILE_LENGTH));
        }

        private void snapToHome() {
            setLayoutX(homeX);
            setLayoutY(homeY);

        }

        private void tileRotate(){
            this.setRotate(90);
        }

        private void tileFliptoLeft(){
            this.nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
        }
        private void tileFliptoRight(){
            this.nodeOrientationProperty().setValue(NodeOrientation.LEFT_TO_RIGHT);
        }

    }

    private void getTextScore(){
        textScore.getChildren().clear();
        String advanced_score = Integer.toString(logic.getAdvancedScore(boardString));
        Text score;
        if (rounds == 7 && ROLL_HOLDER ==0){
            score = new Text("Final Score: " + advanced_score + "GAME OVER!");

        }
        else score = new Text("Current Score: " + advanced_score);
        score.setLayoutX(130);
        score.setLayoutY(VIEWER_HEIGHT - 50);
        score.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        textScore.getChildren().add(score);

    }

    private void specialPlacementHolder(){
        specialTiles.getChildren().add(new DraggableTiles("S0", 7));
        specialTiles.getChildren().add(new DraggableTiles("S1", 8));
        specialTiles.getChildren().add(new DraggableTiles("S2", 9));
        specialTiles.getChildren().add(new DraggableTiles("S3", 10));
        specialTiles.getChildren().add(new DraggableTiles("S4", 11));
        specialTiles.getChildren().add(new DraggableTiles("S5", 12));
    }

    private void rollPlacementHolder(){
        if (ROLL_HOLDER == 0 && rounds != 7){                      // Checks if Previous 4 pieces are placed
            String pieces = logic.generateDiceRoll();
            tempTiles.getChildren().add(new DraggableTiles(pieces.substring(0,2), 1));
            tempTiles.getChildren().add(new DraggableTiles(pieces.substring(2,4), 2));
            tempTiles.getChildren().add(new DraggableTiles(pieces.substring(4,6), 3));
            tempTiles.getChildren().add(new DraggableTiles(pieces.substring(6,8), 4));
            ROLL_HOLDER += 4;                       // Resets Roll Holder Count
            rounds ++;
            setText();
            if (SPECIAL_TILES_LEFT != 0){
                specialTiles.setDisable(false);         // Enable Special Tile Placement
            }
        }
    }


    private void setText() {
        textFields.getChildren().clear();
        Text round_Number = new Text("Round: " + rounds);
        String disabled = "";
        Color fill;
        Text ssCounter = new Text("Special Tiles Left: " + SPECIAL_TILES_LEFT);

        if (specialTiles.isDisabled()){
            disabled = "Unavailable!";
            fill = Color.RED;
        }
        else {
            disabled = "Available!";
            fill = Color.GREEN;
        }

        Text disable_text = new Text(disabled);
        disable_text.setLayoutX(ROLL_X_OFFSET);
        disable_text.setLayoutY(ROLL_Y_OFFSET + TT_OFFSET *2 +TILE_LENGTH);
        disable_text.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        disable_text.setFill(fill);

        round_Number.setLayoutX(ROLL_BUTTON_OFFSET + 100);
        round_Number.setLayoutY(40);
        round_Number.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        ssCounter.setLayoutX(ROLL_X_OFFSET);
        ssCounter.setLayoutY(ROLL_Y_OFFSET + TT_OFFSET *2 +TILE_LENGTH - 24);
        ssCounter.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        textFields.getChildren().add(round_Number);
        textFields.getChildren().add(ssCounter);
        textFields.getChildren().add(disable_text);

    }

    private void generateRoll() {
        Button rollButton = new Button("Roll Dices!");

        rollButton.setOnAction(e -> rollPlacementHolder());

        HBox roll = new HBox();
        roll.getChildren().add(rollButton);
        //roll.setSpacing(10); // this is to set the space between two buttons/nodes
        roll.setLayoutX(ROLL_BUTTON_OFFSET);
        roll.setLayoutY(20);
        controls.getChildren().add(roll);

    }




    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StepsGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(controls);
        root.getChildren().add(board);
        root.getChildren().add(tiles);
        root.getChildren().add(tempTiles);
        root.getChildren().add(specialTiles);
        root.getChildren().add(textFields);
        root.getChildren().add(textScore);

        //makeControls();
        makeBoard();
        generateRoll();
        specialPlacementHolder();
        setText();
        getTextScore();


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
