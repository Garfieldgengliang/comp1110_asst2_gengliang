package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class RailroadInk {
    /**
     * Determine whether a tile placement string is well-formed:
     * - it consists of exactly 5 characters;
     * - the first character represents a die A or B, or a special tile S
     * - the second character indicates which tile or face of the die (0-5 for die A and special tiles, or 0-2 for die B)
     * - the third character represents the placement row A-G
     * - the fourth character represents the placement column 0-6
     * - the fifth character represents the orientation 0-7
     *
     * @param tilePlacementString a candidate tile placement string
     * @return true if the tile placement is well formed
     */
    public static boolean isTilePlacementWellFormed(String tilePlacementString) {
        // FIXME Task 2: determine whether a tile placement is well-formed
        String secondStringA = "012345";
        String secondStringB = "012";
        String secondStringS = "012345";
        String thirdString = "ABCDEFG";
        String fourthString = "0123456";
        String fifthString = "01234567";

        char[] ct = tilePlacementString.toCharArray();
        if (tilePlacementString.length() == 5) {
            if (ct[0] == 'A') {
                if (secondStringA.indexOf(ct[1]) != -1) {
                    if (thirdString.indexOf(ct[2]) != -1) {
                        if (fourthString.indexOf(ct[3]) != -1) {
                            if (fifthString.indexOf(ct[4]) != -1) {
                                return true;
                            }
                        }
                    }
                }
            }

            if (ct[0] == 'B') {
                if (secondStringB.indexOf(ct[1]) != -1) {
                    if (thirdString.indexOf(ct[2]) != -1) {
                        if (fourthString.indexOf(ct[3]) != -1) {
                            if (fifthString.indexOf(ct[4]) != -1) {
                                return true;
                            }
                        }
                    }
                }
            }

            if (ct[0] == 'S') {
                if (secondStringS.indexOf(ct[1]) != -1) {
                    if (thirdString.indexOf(ct[2]) != -1) {
                        if (fourthString.indexOf(ct[3]) != -1) {
                            if (fifthString.indexOf(ct[4]) != -1) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }



    /**
     * Determine whether a board string is well-formed:
     * - it consists of exactly N five-character tile placements (where N = 1 .. 31);
     * - each piece placement is well-formed
     * - no more than three special tiles are included
     *
     * @param boardString a board string describing the placement of one or more pieces
     * @return true if the board string is well-formed
     */
    public static boolean isBoardStringWellFormed(String boardString) {
        // FIXME Task 3: determine whether a board string is well-formed
        if (boardString == null) {
            return false;
        }
        else if (boardString.equals("")) {
            return false;
        }
        else if (boardString.length() - boardString.replaceAll("S", "").length() > 3) {
            return false;
        }
        else if (boardString.length() / 5 > 0 && boardString.length() / 5 < 32) {
            if (boardString.length() % 5 == 0) {
                for (int i = 0; i < boardString.length(); i += 5) {
                    if (isTilePlacementWellFormed(boardString.substring(i, i + 5))) {

                    } else return false;
                }
                return true;
            } else return false;
        }
        return false;
    }



    /**
     * Determine whether the provided placements are neighbours connected by at least one validly connecting edge.
     * For example,
     * - areConnectedNeighbours("A3C10", "A3C23") would return true as these tiles are connected by a highway edge;
     * - areConnectedNeighbours("A3C23", "B1B20") would return false as these neighbouring tiles are disconnected;
     * - areConnectedNeighbours("A0B30", "A3B23") would return false as these neighbouring tiles have an
     * invalid connection between highway and railway; and
     * areConnectedNeighbours("A0B30", "A3C23") would return false as these tiles are not neighbours.
     *
     * @return true if the placements are connected neighbours
     */
    public static boolean areConnectedNeighbours(String tilePlacementStringA, String tilePlacementStringB) {
        // FIXME Task 5: determine whether neighbouring placements are connected
        Tile firstTile = new Tile(tilePlacementStringA);
        Tile secondTile = new Tile(tilePlacementStringB);

        Spot firstspot = firstTile.spot;
        Spot secondspot = secondTile.spot;

        if(firstspot.isNeighboring(secondspot)){
            String connectionside = firstspot.connectionside(secondspot);
            if(connectionside == "right"){
                if(firstTile.right == secondTile.left && firstTile.right!= 'b' && secondTile.left!='b'){
                    return true;
                }else{return false;}
            }
            if(connectionside == "left"){
                if(firstTile.left == secondTile.right && firstTile.left!= 'b' && secondTile.right!='b'){
                    return true;
                }else{return false;}

            }
            if(connectionside == "upside"){
                if(firstTile.up == secondTile.down && firstTile.up!= 'b' && secondTile.down!='b'){
                    return true;
                }else{return false;}

            }
            if(connectionside == "downside"){
                if(firstTile.down == secondTile.up && firstTile.down!= 'b' && secondTile.up!='b'){
                    return true;
                }else{return  false;}
            }
            else{
                return false;
            }

        }
        else{
            return false;
        }

    }
     public static  boolean isvalidExit(String tilePlacementString){
        // this method is to check whether a tileplacement string is legally connected to an exit
        Tile teststring = new Tile(tilePlacementString);

        if(teststring.spot.col == '0' && teststring.spot.row=='B'){
            if(teststring.left == 'r'){
                return true;
            }else{
                return false;
            }
        }
         if(teststring.spot.col == '0' && teststring.spot.row=='D'){
             if(teststring.left == 'h'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '0' && teststring.spot.row=='F'){
             if(teststring.left == 'r'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '1' && teststring.spot.row=='A'){
             if(teststring.up == 'h'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '3' && teststring.spot.row=='A'){
             if(teststring.up == 'r'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '5' && teststring.spot.row=='A'){
             if(teststring.up == 'h'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '6' && teststring.spot.row=='B'){
             if(teststring.right == 'r'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '6' && teststring.spot.row=='D'){
             if(teststring.right == 'h'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '6' && teststring.spot.row=='F'){
             if(teststring.right == 'r'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '1' && teststring.spot.row=='G'){
             if(teststring.down == 'h'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '3' && teststring.spot.row=='G'){
             if(teststring.down == 'r'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '5' && teststring.spot.row=='G'){
             if(teststring.down == 'h'){
                 return true;
             }else{
                 return false;
             }
         }
         else{
             return false;
         }
     }
    /**
     * Given a well-formed board string representing an ordered list of placements,
     * determine whether the board string is valid.
     * A board string is valid if each tile placement is legal with respect to all previous tile
     * placements in the string, according to the rules for legal placements:
     * - A tile must be placed such that at least one edge connects to either an exit or a pre-existing route.
     *   Such a connection is called a valid connection.
     * - Tiles may not be placed such that a highway edge connects to a railway edge;
     *   this is referred to as an invalid connection.
     *   Highways and railways may only join at station tiles.
     * - A tile may have one or more edges touching a blank edge of another tile;
     *   this is referred to as disconnected, but the placement is still legal.
     *
     * @param boardString a board string representing some placement sequence
     * @return true if placement sequence is valid
     */
    public static boolean isValidPlacementSequence(String boardString) {
        // FIXME Task 6: determine whether the given placement sequence is valid
        List<String> placementList = new ArrayList<String>();

        for(int stringindex = 0; stringindex < boardString.length(); stringindex = stringindex + 5){
            String currentPlacement = boardString.substring(stringindex,stringindex+5);
            placementList.add(currentPlacement);
        }

        int legalPlaceCount = 0;
        int exitTileNumber = 0;

        for(int listindex = 0; listindex < placementList.size(); listindex++){
            String currentCheck = placementList.get(listindex);
            Tile currentTile = new Tile(currentCheck);
            if(currentTile.spot.isExit()){
                exitTileNumber ++;
                if(!isvalidExit(currentCheck)){
                    return false;
                }
                else{

                    List<String> neighborString = new ArrayList<String>();
                    // find all the neiboring tile placement in the whole board string and check if these is illegal connection
                    for(int listcheck = 0; listcheck < placementList.size(); listcheck++){
                        Tile checkTile = new Tile(placementList.get(listcheck));
                        if(currentTile.spot.isNeighboring(checkTile.spot)){
                            neighborString.add(placementList.get(listcheck));
                        }
                    }


                    for(int checkindex = 0; checkindex < neighborString.size(); checkindex++ ){
                        Tile currentNeighbor = new Tile(neighborString.get(checkindex));
                        if(currentTile.spot.row - currentNeighbor.spot.row == 1){
                            if((currentTile.up == 'h'&&currentNeighbor.down == 'r')||(currentTile.up == 'r'&&currentNeighbor.down == 'h')){
                                return false;
                            }
                        }
                        if(currentTile.spot.row - currentNeighbor.spot.row == -1){
                            if((currentTile.down == 'h'&&currentNeighbor.up == 'r')||(currentTile.down == 'r'&&currentNeighbor.up == 'h')){
                                return false;
                            }

                        }
                        if(currentTile.spot.col - currentNeighbor.spot.col == 1){
                            if((currentTile.left == 'h'&&currentNeighbor.right == 'r')||(currentTile.left == 'r'&&currentNeighbor.right == 'h')){
                                return false;
                            }
                        }
                        if(currentTile.spot.col - currentNeighbor.spot.col == -1){
                            if((currentTile.right == 'h'&&currentNeighbor.left == 'r')||(currentTile.right == 'r'&&currentNeighbor.left == 'h')){
                                return false;
                            }
                        }
                    }
                  legalPlaceCount = legalPlaceCount + 1;
                }
            }
            else{

                List<String> neighborString = new ArrayList<String>();
                // find all the neiboring tile placement in the whole board string and check if
                // these is any legal ceoonection and also check the illegal connection

                for(int listcheck = 0; listcheck < placementList.size(); listcheck++){
                    Tile checkTile = new Tile(placementList.get(listcheck));
                    if(currentTile.spot.isNeighboring(checkTile.spot)){
                        neighborString.add(placementList.get(listcheck));
                    }
                }

                int legalConnectionCount = 0;

                for(int checkindex = 0; checkindex < neighborString.size(); checkindex++ ) {
                    Tile currentNeighbor = new Tile(neighborString.get(checkindex));
                    if (currentTile.spot.row - currentNeighbor.spot.row == 1) {
                        if ((currentTile.up == 'h' && currentNeighbor.down == 'r') || (currentTile.up == 'r' && currentNeighbor.down == 'h')) {
                            return false;
                        }
                        if ((currentTile.up == 'h' && currentNeighbor.down == 'h') || (currentTile.up == 'r' && currentNeighbor.down == 'r')) {
                            legalConnectionCount ++;
                        }
                    }
                    if (currentTile.spot.row - currentNeighbor.spot.row == -1) {
                        if ((currentTile.down == 'h' && currentNeighbor.up == 'r') || (currentTile.down == 'r' && currentNeighbor.up == 'h')) {
                            return false;
                        }
                        if ((currentTile.down == 'h' && currentNeighbor.up == 'h') || (currentTile.down == 'r' && currentNeighbor.up == 'r')) {
                            legalConnectionCount ++;
                        }

                    }
                    if (currentTile.spot.col - currentNeighbor.spot.col == 1) {
                        if ((currentTile.left == 'h' && currentNeighbor.right == 'r') || (currentTile.left == 'r' && currentNeighbor.right == 'h')) {
                            return false;
                        }
                        if ((currentTile.left == 'h' && currentNeighbor.right == 'h') || (currentTile.left == 'r' && currentNeighbor.right == 'r')) {
                            legalConnectionCount ++;
                        }
                    }
                    if (currentTile.spot.col - currentNeighbor.spot.col == -1) {
                        if ((currentTile.right == 'h' && currentNeighbor.left == 'r') || (currentTile.right == 'r' && currentNeighbor.left == 'h')) {
                            return false;
                        }
                        if ((currentTile.right == 'h' && currentNeighbor.left == 'h') || (currentTile.right == 'r' && currentNeighbor.left == 'r')) {
                            legalConnectionCount ++;
                        }
                    }
                }
                if(legalConnectionCount == 0){
                    return false;
                }
                legalPlaceCount = legalPlaceCount + 1;
            }

        }

        if(legalPlaceCount == placementList.size()&&exitTileNumber!= 0){
            return true;
        }
        else {
            return false;
        }


    }

    /**
     * Generate a random dice roll as a string of eight characters.
     * Dice A should be rolled three times, dice B should be rolled once.
     * Die A has faces numbered 0-5.
     * Die B has faces numbered 0-2.
     * Each die roll is composed of a character 'A' or 'B' representing the dice,
     * followed by a digit character representing the face.
     *
     * @return a String representing the die roll e.g. A0A4A3B2
     */
    public static String generateDiceRoll() {
        // FIXME Task 7: generate a dice roll
        return "";
    }

    /**
     * Given the current state of a game board, output an integer representing the sum of all the following factors
     * that contribute to the player's final score.
     * <p>
     * * Number of exits mapped
     * * Number of centre tiles used
     * * Number of dead ends in the network
     *
     * @param boardString a board string representing a completed game
     * @return integer (positive or negative) for score *not* considering longest rail/highway
     */
    public static int getBasicScore(String boardString) {
        // FIXME Task 8: compute the basic score
        return -1;
    }

    /**
     * Given a valid boardString and a dice roll for the round,
     * return a String representing an ordered sequence of valid piece placements for the round.
     * @param boardString a board string representing the current state of the game as at the start of the round
     * @param diceRoll a String representing a dice roll for the round
     * @return a String representing an ordered sequence of valid piece placements for the current round
     * @see RailroadInk#generateDiceRoll()
     */
    public static String generateMove(String boardString, String diceRoll) {
        // FIXME Task 10: generate a valid move
        return null;
    }

    /**
     * Given the current state of a game board, output an integer representing the sum of all the factors contributing
     * to `getBasicScore`, as well as those attributed to:
     * <p>
     * * Longest railroad
     * * Longest highway
     *
     * @param boardString a board string representing a completed game
     * @return integer (positive or negative) for final score (not counting expansion packs)
     */
    public static int getAdvancedScore(String boardString) {
        // FIXME Task 12: compute the total score including bonus points
        return -1;
    }
}

