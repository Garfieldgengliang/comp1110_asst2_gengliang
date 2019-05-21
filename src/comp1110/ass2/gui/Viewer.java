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
import javafx.scene.input.MouseButton;
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

/* Authors:
    1)Joel Chua, U6708832
    2)Gengliang Li, U6799959
    3)Peng Chen, U6460012
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
    private final Group cpuBoard = new Group();
    private final Group tiles = new Group();
    private final Group cpuTiles = new Group();
    private final Group tempTiles = new Group();
    private final Group specialTiles = new Group();
    private final Group textFields = new Group();
    private final Group textScore = new Group();

    /*Definitions of Board/Tiles etc... */
    private static final int TILE_LENGTH = 55; //Defining each Tile length
    private static final int BOARD_WIDTH = TILE_LENGTH*9; //Defining board width
    private static final int BOARD_HEIGHT = TILE_LENGTH*9; //Defining board height
    private static final int BOARD_X_OFFSET = 0; //Setting offset for the board
    private static final int BOARD_Y_OFFSET = 0;
    private static final Paint SUBBOARD_FILL = Color.DARKGREY;
    private static final Paint SUBBOARD_TILE = Color.GREY;
    private static final String HighExit = Viewer.class.getResource(URI_BASE + "HighExit.png").toString();
    private static final String RailExit = Viewer.class.getResource(URI_BASE + "RailExit.png").toString();
    private static final int ROLL_X_BUTTON_OFFSET = (BOARD_X_OFFSET + BOARD_WIDTH +TILE_LENGTH/4);  // Roll Button Offsets
    private static final int ROLL_Y_BUTTON_OFFSET =10;
    private static final int ROLL_X_OFFSET = ROLL_X_BUTTON_OFFSET;      //X Offset for Tile Holder
    private static final int ROLL_Y_OFFSET = BOARD_Y_OFFSET + ROLL_Y_BUTTON_OFFSET +45; //Y Offset for Tile Holder
    private static final int TT_OFFSET = TILE_LENGTH + 20;  //Offset for TempTiles
    private static final int CPU_TILE_LENGTH = 34; //Defining each Tile length CPU
    private static final int CPU_BOARD_WIDTH = CPU_TILE_LENGTH*9; //Defining board width CPU
    private static final int CPU_BOARD_HEIGHT = CPU_TILE_LENGTH*9; //Defining board height CPU
    private static final int CPU_BOARD_X_OFFSET = ROLL_X_OFFSET + TT_OFFSET * 2 + 60 ; //Setting offset for the board CPU
    private static final int CPU_BOARD_Y_OFFSET = 0;
    private int rounds = 0;
    private int ROLL_HOLDER = 0;
    private int SPECIAL_TILES_LEFT = 3;
    private String boardString = "";
    private String cpu_boardString = "";


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

    private void CPUmakeBoard(){
        Rectangle background = new Rectangle(CPU_BOARD_WIDTH,CPU_BOARD_HEIGHT);
        background.setFill(SUBBOARD_FILL);
        background.setLayoutX(CPU_BOARD_X_OFFSET);
        background.setLayoutY(CPU_BOARD_Y_OFFSET);
        cpuBoard.getChildren().add(background); //Setting up board by calling Rectangle

        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                Rectangle piece = new Rectangle(CPU_TILE_LENGTH, CPU_TILE_LENGTH);
                piece.setFill(Color.WHITE);
                piece.setStroke(SUBBOARD_TILE);
                piece.setLayoutX(col*CPU_TILE_LENGTH + CPU_BOARD_X_OFFSET + CPU_TILE_LENGTH);
                piece.setLayoutY(row*CPU_TILE_LENGTH +CPU_BOARD_Y_OFFSET +CPU_TILE_LENGTH);
                cpuBoard.getChildren().add(piece); //Setting the Grid for Tile placements
            }
        }

        Image HE = new Image(HighExit);
        Image RE = new Image(RailExit);

        for (int row = 0; row < 7; row++){   //Setting up the Board exits depending on row and column
            for (int col = 0 ; col < 7; col++){
                if (row == 0) {
                    if (col == 1 || col == 5){
                        ImageView hExit = new ImageView(HE);
                        hExit.setX(CPU_BOARD_X_OFFSET+CPU_TILE_LENGTH*(col+1));
                        hExit.setY(CPU_BOARD_Y_OFFSET);
                        hExit.setFitWidth(CPU_TILE_LENGTH);
                        hExit.setFitHeight(CPU_TILE_LENGTH);
                        cpuBoard.getChildren().add(hExit);
                    }
                    if (col == 3){
                        ImageView rExit = new ImageView(RE);
                        rExit.setX(CPU_BOARD_X_OFFSET+CPU_TILE_LENGTH*(col+1));
                        rExit.setY(CPU_BOARD_Y_OFFSET);
                        rExit.setFitWidth(CPU_TILE_LENGTH);
                        rExit.setFitHeight(CPU_TILE_LENGTH);
                        cpuBoard.getChildren().add(rExit);
                    }

                }

                if (row == 6) {
                    if (col == 1 || col == 5){
                        ImageView hExit = new ImageView(HE);
                        hExit.setRotate(hExit.getRotate() + 180);
                        hExit.setX(CPU_BOARD_X_OFFSET+CPU_TILE_LENGTH*(col+1));
                        hExit.setY(CPU_BOARD_Y_OFFSET+ 8*CPU_TILE_LENGTH);
                        hExit.setFitWidth(CPU_TILE_LENGTH);
                        hExit.setFitHeight(CPU_TILE_LENGTH);
                        cpuBoard.getChildren().add(hExit);
                    }
                    if (col == 3){
                        ImageView rExit = new ImageView(RE);
                        rExit.setRotate(rExit.getRotate() + 180);
                        rExit.setX(CPU_BOARD_X_OFFSET+CPU_TILE_LENGTH*(col+1));
                        rExit.setY(CPU_BOARD_Y_OFFSET + 8*CPU_TILE_LENGTH);
                        rExit.setFitWidth(CPU_TILE_LENGTH);
                        rExit.setFitHeight(CPU_TILE_LENGTH);
                        cpuBoard.getChildren().add(rExit);
                    }

                }

                if (col == 0){
                    if (row == 1 ||row == 5){
                        ImageView rExit = new ImageView(RE);
                        rExit.setRotate(rExit.getRotate() + 270);
                        rExit.setX(CPU_BOARD_X_OFFSET);
                        rExit.setY(CPU_BOARD_Y_OFFSET+CPU_TILE_LENGTH*(row+1));
                        rExit.setFitWidth(CPU_TILE_LENGTH);
                        rExit.setFitHeight(CPU_TILE_LENGTH);
                        cpuBoard.getChildren().add(rExit);
                    }
                    if (row == 3){
                        ImageView hExit = new ImageView(HE);
                        hExit.setRotate(hExit.getRotate() + 270);
                        hExit.setX(CPU_BOARD_X_OFFSET);
                        hExit.setY(CPU_BOARD_Y_OFFSET+CPU_TILE_LENGTH*(row+1));
                        hExit.setFitWidth(CPU_TILE_LENGTH);
                        hExit.setFitHeight(CPU_TILE_LENGTH);
                        cpuBoard.getChildren().add(hExit);
                    }
                }

                if (col == 6){
                    if (row == 1 ||row == 5){
                        ImageView rExit = new ImageView(RE);
                        rExit.setRotate(rExit.getRotate() + 90);
                        rExit.setX(CPU_BOARD_X_OFFSET+CPU_TILE_LENGTH*8);
                        rExit.setY(CPU_BOARD_Y_OFFSET+CPU_TILE_LENGTH*(row+1));
                        rExit.setFitWidth(CPU_TILE_LENGTH);
                        rExit.setFitHeight(CPU_TILE_LENGTH);
                        cpuBoard.getChildren().add(rExit);
                    }
                    if (row == 3){
                        ImageView hExit = new ImageView(HE);
                        hExit.setRotate(hExit.getRotate() + 90);
                        hExit.setX(CPU_BOARD_X_OFFSET+CPU_TILE_LENGTH*8);
                        hExit.setY(CPU_BOARD_Y_OFFSET+CPU_TILE_LENGTH*(row+1));
                        hExit.setFitWidth(CPU_TILE_LENGTH);
                        hExit.setFitHeight(CPU_TILE_LENGTH);
                        cpuBoard.getChildren().add(hExit);
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

    void cpuMakePlacement(String placement) {
        cpuTiles.getChildren().clear(); //clear board before placing

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
            tile.setX(CPU_BOARD_X_OFFSET+CPU_TILE_LENGTH*(col+1)); //Setting offset of Col
            tile.setY(CPU_BOARD_Y_OFFSET+CPU_TILE_LENGTH*(Character.getNumericValue(row)-9)); //Setting offset of row
            tile.setFitWidth(CPU_TILE_LENGTH); //Setting width and height of tile
            tile.setFitHeight(CPU_TILE_LENGTH);
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


            cpuTiles.getChildren().add(tile);
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
            setPickOnBounds(true);                                      //Enable Clicks on transparent Spaces of PNG Files
        }
    }

    /**
     * this class extends Temptiles class and
     * thus we can create draggable tiles by create new instance of this class
     */

    class DraggableTiles extends TempTiles{
        double homeX, homeY;
        double mouseX, mouseY;
        int orientation = 0; // keep track of the orientation of the draggable tile

        DraggableTiles(String piece, int pos) {
            super(piece, pos);

            homeX = getLayoutX();
            homeY = getLayoutY();

            // when the mouse is right clicked, rotate of mirror the tile
            setOnMouseClicked(event -> {
                if(event.getButton() == MouseButton.SECONDARY){
                    setTileRotate();
                }
            });

            // when the mouse is pressed, prepare for the drag
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

        // this method gives correct rotation when the mouse is right clicked
        public  void setTileRotate(){
            int currentOri = orientation%8;
            if(currentOri == 0||currentOri == 1||currentOri == 2){
                setRotate(90*(currentOri+1));
            }
            else if(currentOri == 3){
                setRotate(0);
                setScaleX(-1);
            }
            else if(currentOri == 4||currentOri == 5||currentOri==6){
                // when the currentOri is 4 or 5 or 6, first mirror the tile and rotate the tile to the right degree
                setRotate(0);
                setScaleX(-1);
                setRotate(90*(currentOri-3));
            }
            else if(currentOri==7){
                setRotate(0);
                setScaleX(1);
            }
            orientation++;
        }

        // this method gives correct judgement whether to snap the tile to the board or not
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
                        SPECIAL_TILES_LEFT --;          // Counts Special Tiles Placed
                        setText();                      // Refresh TextFields for Rounds and Special Tiles
                    }
                    else {
                        ROLL_HOLDER --;                 // Reduces the Roll Holder Count for normal Tiles
                    }
                    setLayoutX(BOARD_X_OFFSET+xPosition*TILE_LENGTH);
                    setLayoutY(BOARD_Y_OFFSET+yPosition*TILE_LENGTH);
                    setDisable(true);
                    getTextScore();                     //Refresh Scores Text
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


        // tell if the tile is currently dragged to the board area
        private boolean onBoard() {
            return getLayoutX() > (BOARD_X_OFFSET+TILE_LENGTH) && (getLayoutX() < (BOARD_X_OFFSET+BOARD_WIDTH-TILE_LENGTH))
                    && getLayoutY() > (BOARD_Y_OFFSET+TILE_LENGTH) && (getLayoutY() < (BOARD_Y_OFFSET+BOARD_HEIGHT-TILE_LENGTH));
        }

        // snap the tile to its original place if it cannot snap to the board
        private void snapToHome() {
            setLayoutX(homeX);
            setLayoutY(homeY);

        }
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
        if (ROLL_HOLDER == 0 && rounds != 7){                      // Checks if Previous 4 pieces are placed and if its not the final round
            String pieces = logic.generateDiceRoll();
            tempTiles.getChildren().add(new DraggableTiles(pieces.substring(0,2), 1));
            tempTiles.getChildren().add(new DraggableTiles(pieces.substring(2,4), 2));
            tempTiles.getChildren().add(new DraggableTiles(pieces.substring(4,6), 3));
            tempTiles.getChildren().add(new DraggableTiles(pieces.substring(6,8), 4));
            ROLL_HOLDER += 4;                       // Resets Roll Holder Count
            rounds ++;                              // Increment round Counter
            setText();
            String cpu_out = logic.generateMove(cpu_boardString,pieces);    //Generate CPU moves on roll button click
            cpu_boardString+=cpu_out;                                       //State of CPU board
            cpuMakePlacement(cpu_boardString);                              //Generate the tiles for the cpu boardString.
            if (SPECIAL_TILES_LEFT != 0){
                specialTiles.setDisable(false);         // Enable Special Tile Placement Every New round if there are tiles left.
            }
        }
    }


    private void setText() {
        textFields.getChildren().clear();                                           //Clears previous texts
        Text round_Number = new Text("Round: " + rounds);                           //Round Counter Text
        String disabled = "";
        Color fill;
        Text ssCounter = new Text("Special Tiles Left: " + SPECIAL_TILES_LEFT);     //Special Tiles Counter Text

        if (specialTiles.isDisabled()){                                             //Checks if Special Tile is available for current Round.
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

        round_Number.setLayoutX(ROLL_X_BUTTON_OFFSET + 100);
        round_Number.setLayoutY(ROLL_Y_BUTTON_OFFSET+20);
        round_Number.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        ssCounter.setLayoutX(ROLL_X_OFFSET);
        ssCounter.setLayoutY(ROLL_Y_OFFSET + TT_OFFSET *2 +TILE_LENGTH - 24);
        ssCounter.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        textFields.getChildren().add(round_Number);                                     //Adds Various texts to textField Group
        textFields.getChildren().add(ssCounter);
        textFields.getChildren().add(disable_text);

    }

    private void getTextScore(){
        textScore.getChildren().clear();                                            //Clears previous texts
        int advanced_score = logic.getAdvancedScore(boardString);  //Counts Advanced Score
        int basic_score = logic.getBasicScore(boardString);        //Counts Basic Score
        int cpu_advanced_score = logic.getAdvancedScore(cpu_boardString);  //Counts CPU Advanced Score
        int cpu_basic_score = logic.getBasicScore(cpu_boardString);        //Counts CPU Basic Score
        Text score_adv;
        Text score_basic;
        if (rounds == 7 && ROLL_HOLDER ==0){
            score_adv = new Text("Final Score: " + advanced_score + "  GAME OVER!");    //Change to Game Over popup once game is finished
            score_basic = new Text ("");

        }
        else {
            score_basic = new Text("Player Basic Score: " + basic_score);               //Calculates scores every placement
            score_adv = new Text("Player Advanced Score: " + advanced_score);
        }
        score_adv.setLayoutX(BOARD_X_OFFSET + 20);
        score_adv.setLayoutY(BOARD_HEIGHT + BOARD_Y_OFFSET +80);
        score_adv.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        score_basic.setLayoutX(BOARD_X_OFFSET+ 20);
        score_basic.setLayoutY(BOARD_HEIGHT + BOARD_Y_OFFSET +50);
        score_basic.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        textScore.getChildren().add(score_adv);
        textScore.getChildren().add(score_basic);

        Text cpu_score_adv;
        Text cpu_score_basic;
        Text result;
        if (rounds == 7 && ROLL_HOLDER ==0){
            cpu_score_adv = new Text("CPU Final Score: " + cpu_advanced_score + "  GAME OVER!");
            cpu_score_basic = new Text ("");
            if (cpu_advanced_score == advanced_score){              //Result of Player vs CPU
                result = new Text("DRAW!");
                result.setFill(Color.BLACK);
            }
            else if (cpu_advanced_score > advanced_score){
                result = new Text("YOU LOSE!");
                result.setFill(Color.RED);
            }
            else {
                result = new Text("YOU WIN!");
                result.setFill(Color.GREEN);
            }
            result.setLayoutX(CPU_BOARD_X_OFFSET);
            result.setLayoutY(VIEWER_HEIGHT - 50);
            result.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
            textScore.getChildren().add(result);
        }
        else {
            cpu_score_basic = new Text("CPU Basic Score: " + cpu_basic_score);      //Calculates CPU Score per round
            cpu_score_adv = new Text("CPU Advanced Score: " + cpu_advanced_score);
        }
        cpu_score_adv.setLayoutX(CPU_BOARD_X_OFFSET);
        cpu_score_adv.setLayoutY(CPU_BOARD_HEIGHT + CPU_BOARD_Y_OFFSET + 40);
        cpu_score_adv.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        cpu_score_basic.setLayoutX(CPU_BOARD_X_OFFSET);
        cpu_score_basic.setLayoutY(CPU_BOARD_HEIGHT + CPU_BOARD_Y_OFFSET + 20);
        cpu_score_basic.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        textScore.getChildren().add(cpu_score_adv);
        textScore.getChildren().add(cpu_score_basic);

    }

    private void generateRoll() {
        Button rollButton = new Button("Roll Dices!");              //Button to Click To Generate roll

        rollButton.setOnAction(e -> rollPlacementHolder());              //Call Method on click

        HBox roll = new HBox();
        roll.getChildren().add(rollButton);
        roll.setLayoutX(ROLL_X_BUTTON_OFFSET);
        roll.setLayoutY(ROLL_Y_BUTTON_OFFSET);
        controls.getChildren().add(roll);                               // Add the button to controls group.

    }



    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("StepsGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(controls);
        root.getChildren().add(board);
        root.getChildren().add(tiles);
        root.getChildren().add(tempTiles);
        root.getChildren().add(specialTiles);
        root.getChildren().add(textFields);
        root.getChildren().add(textScore);
        root.getChildren().add(cpuBoard);
        root.getChildren().add(cpuTiles);

        makeControls();
        makeBoard();
        CPUmakeBoard();
        generateRoll();
        specialPlacementHolder();
        setText();
        getTextScore();


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
