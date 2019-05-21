package comp1110.ass2;

import java.util.*;


/* Authors:
    1)Joel Chua, U6708832
    2)Gengliang Li, U6799959
    3)Peng Chen, U6460012
 */
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

        if(firstspot.isNeighboring(secondspot)){ // first we check whether the spot is neighboring or not
            String connectionside = firstspot.connectionside(secondspot);
            // then we check of the connection is valid
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

    public static  boolean isLegalExit(String tilePlacementString){
        // this method is to check whether a tileplacement string is legally connected to an exit
        // this means first we check if a tile is neighboring an exit, then we check if the connection is legal
        Tile teststring = new Tile(tilePlacementString);

        if(teststring.spot.col == '0' && teststring.spot.row=='B'){
            if(teststring.left == 'h'){
                return false;
            }else{
                return true;
            }
        }
        if(teststring.spot.col == '0' && teststring.spot.row=='D'){
            if(teststring.left == 'r'){
                return false;
            }else{
                return true;
            }
        }
        if(teststring.spot.col == '0' && teststring.spot.row=='F'){
            if(teststring.left == 'h'){
                return false;
            }else{
                return true;
            }
        }
        if(teststring.spot.col == '1' && teststring.spot.row=='A'){
            if(teststring.up == 'r'){
                return false;
            }else{
                return true;
            }
        }
        if(teststring.spot.col == '3' && teststring.spot.row=='A'){
            if(teststring.up == 'h'){
                return false;
            }else{
                return true;
            }
        }
        if(teststring.spot.col == '5' && teststring.spot.row=='A'){
            if(teststring.up == 'r'){
                return false;
            }else{
                return true;
            }
        }
        if(teststring.spot.col == '6' && teststring.spot.row=='B'){
            if(teststring.right == 'h'){
                return false;
            }else{
                return true;
            }
        }
        if(teststring.spot.col == '6' && teststring.spot.row=='D'){
            if(teststring.right == 'r'){
                return false;
            }else{
                return true;
            }
        }
        if(teststring.spot.col == '6' && teststring.spot.row=='F'){
            if(teststring.right == 'h'){
                return false;
            }else{
                return true;
            }
        }
        if(teststring.spot.col == '1' && teststring.spot.row=='G'){
            if(teststring.down == 'r'){
                return false;
            }else{
                return true;
            }
        }
        if(teststring.spot.col == '3' && teststring.spot.row=='G'){
            if(teststring.down == 'h'){
                return false;
            }else{
                return true;
            }
        }
        if(teststring.spot.col == '5' && teststring.spot.row=='G'){
            if(teststring.down == 'r'){
                return false;
            }else{
                return true;
            }
        }
        else{
            return false;
        }
    }

     public static  boolean isTileExitBlank(String tilePlacementString){
        // this method is to check whether a exittile connects to an exit with its blank face
         // this means first we check if a tile is neighboring an exit, then we check if the connection is legal
        Tile teststring = new Tile(tilePlacementString);

        if(teststring.spot.col == '0' && teststring.spot.row=='B'){
            if(teststring.left == 'b'){
                return true;
            }else{
                return false;
            }
        }
         if(teststring.spot.col == '0' && teststring.spot.row=='D'){
             if(teststring.left == 'b'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '0' && teststring.spot.row=='F'){
             if(teststring.left == 'b'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '1' && teststring.spot.row=='A'){
             if(teststring.up == 'b'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '3' && teststring.spot.row=='A'){
             if(teststring.up == 'b'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '5' && teststring.spot.row=='A'){
             if(teststring.up == 'b'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '6' && teststring.spot.row=='B'){
             if(teststring.right == 'b'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '6' && teststring.spot.row=='D'){
             if(teststring.right == 'b'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '6' && teststring.spot.row=='F'){
             if(teststring.right == 'b'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '1' && teststring.spot.row=='G'){
             if(teststring.down == 'b'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '3' && teststring.spot.row=='G'){
             if(teststring.down == 'b'){
                 return true;
             }else{
                 return false;
             }
         }
         if(teststring.spot.col == '5' && teststring.spot.row=='G'){
             if(teststring.down == 'b'){
                 return true;
             }else{
                 return false;
             }
         }
         else{
             return false;
         }
     }

    public static  boolean isvalidExit(String tilePlacementString){
        // this method is to check whether a tileplacement string is validly connected to an exit
        // this means first we check if a tile is neighboring an exit, then we check if the connection is valid
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
        // slice the board string into tile placement string
        for(int stringindex = 0; stringindex < boardString.length(); stringindex = stringindex + 5){
            String currentPlacement = boardString.substring(stringindex,stringindex+5);
            placementList.add(currentPlacement);
        }

        List<String> locationList = new ArrayList<>();
        for(int locaIndex = 0; locaIndex< placementList.size();locaIndex++){
            String currentPlace = placementList.get(locaIndex);
            String currentLoc = currentPlace.substring(2,4);
            locationList.add(currentLoc);
        }

        Set<String> locaSet = new HashSet<>(locationList); // check if there are duplicate locations
        if(locaSet.size()!= locationList.size()){
            return false;
        }

        int legalPlaceCount = 0;
        int exitTileNumber = 0;

        for(int listindex = 0; listindex < placementList.size(); listindex++){
            String currentCheck = placementList.get(listindex);
            Tile currentTile = new Tile(currentCheck);
            if(currentTile.spot.isExit()){
                exitTileNumber ++;
                if(!isLegalExit(currentCheck)){
                    return false;
                }
                else{

                    List<String> neighborString = new ArrayList<String>();
                    // find all the neighbouring tile placement in the whole board string and check if these is illegal connection
                    for(int listcheck = 0; listcheck < placementList.size(); listcheck++){
                        Tile checkTile = new Tile(placementList.get(listcheck));
                        if(currentTile.spot.isNeighboring(checkTile.spot)){
                            neighborString.add(placementList.get(listcheck));
                        }
                    }

                    //System.out.println(currentCheck + "neighbor is " + neighborString);

                    if(isTileExitBlank(currentCheck)){
                        int validconnection = 0;
                        for(int checkneighbor = 0; checkneighbor<neighborString.size();checkneighbor++){
                            if(areConnectedNeighbours(currentCheck, neighborString.get(checkneighbor))){
                                validconnection++;
                            }
                        }
                        if(validconnection == 0){
                            return false;
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
                // find all the neighbouring tile placement in the whole board string and check if
                // these is any legal connection and also check the illegal connection

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
        Random rand = new Random();
        //generate random number

        //generate A -> A -> A -> B in order
        String firstDieA = "A" + rand.nextInt(6);
        String secondDieA = "A" + rand.nextInt(6);
        String thirdDieA = "A" + rand.nextInt(6);
        String dieB = "B" + rand.nextInt(3);

        ArrayList<String> diceRoll = new ArrayList<>();
        diceRoll.add(firstDieA);
        diceRoll.add(secondDieA);
        diceRoll.add(thirdDieA);

        //get random index from list
        int firstIndex = rand.nextInt(3);
        String firstRoll = diceRoll.get(firstIndex);
        diceRoll.remove(firstIndex);

        int secondIndex = rand.nextInt(2);
        String secondRoll = diceRoll.get(secondIndex);
        diceRoll.remove(secondIndex);

        String thirdRoll = diceRoll.get(0);

        return firstRoll + secondRoll + thirdRoll + dieB;
    }



    /**
     * this method is to find the number of deadEnds of a given boardString
     * first for a given tile, find the number of tiles that connect to this current tile
     * second find the number of road ends that connect to the edge of board, the edge also includes exits
     * Then the number of deadEnds equals to number of roads in a tile minus the road ends that connect to the edge
     *   minus the road ends legally connect to another tile
     * Finally, check through all the tiles in the boardString
     * @param boardString
     * @return number of deadEnds in this boardString
     */


    public static int findDeadEnd(String boardString){

        List<String> placementList = new ArrayList<String>();
        // slice the board string into tile placement string
        for(int stringindex = 0; stringindex < boardString.length(); stringindex = stringindex + 5){
            String currentPlacement = boardString.substring(stringindex,stringindex+5);
            placementList.add(currentPlacement);
        }

        int DeadEndNumber = 0; // the number of dead ends

        for(int listindex = 0; listindex < placementList.size(); listindex++) {
            String currentCheck = placementList.get(listindex);
            Tile currentTile = new Tile(currentCheck);

            int countNeighbor = 0;
            int currentNonBlankNumber = currentTile.piece.NonBlankNumber();

            for(int checkIndex = 0; checkIndex < placementList.size(); checkIndex++){
                String checkNeighbor = placementList.get(checkIndex);
                if(areConnectedNeighbours(currentCheck,checkNeighbor)){
                    countNeighbor++;
                }
            }  // find the number of tiles that connect to this current tile

            int connectEdge = 0;
            // find the number of road ends that connect to the edge of board, the edge also includes exits
            if(currentTile.spot.row == 'A'&&currentTile.spot.col!='6'){
                // if the tile lies on the upper edge of the board
                if(currentTile.spot.col == '0'){

                    if(currentTile.left != 'b'){
                        connectEdge++;
                    }
                    if(currentTile.up != 'b'){
                        connectEdge++;
                    }
                    // the number of deadEnds equals to number of roads in a tile minus the road ends that connect to the edge
                    // minus the road ends legally connect to another tile
                }
                else{
                    if(currentTile.up != 'b'){
                        connectEdge++;
                    }

                }
            }
            if(currentTile.spot.col == '6'&&currentTile.spot.row!='G'){
                // if the tile lies on right edge of the board
                if(currentTile.spot.row == 'A'){

                    if(currentTile.right != 'b'){
                        connectEdge++;
                    }
                    if(currentTile.up != 'b'){
                        connectEdge++;
                    }

                }
                else{
                    if(currentTile.right != 'b'){
                        connectEdge++;
                    }

                }
            }
            if(currentTile.spot.row == 'G'&&currentTile.spot.col!='0'){
                // if the tile lies on downer edge of the board
                if(currentTile.spot.col == '6'){

                    if(currentTile.down != 'b'){
                        connectEdge++;
                    }
                    if(currentTile.right != 'b'){
                        connectEdge++;
                    }

                 }
                else{
                    if(currentTile.down != 'b'){
                        connectEdge++;
                    }

                }
            }
            if(currentTile.spot.col == '0'&&currentTile.spot.row!='A'){
                // if the tile lies on left edge of the board
                if(currentTile.spot.row == 'G'){

                    if(currentTile.left != 'b'){
                        connectEdge++;
                    }
                    if(currentTile.down != 'b'){
                        connectEdge++;
                    }

                }
                else{
                    if(currentTile.left != 'b'){
                        connectEdge++;
                    }

                }
            }

            // the number of deadEnds equals to number of roads in a tile minus the road ends that connect to the edge
            // minus the road ends legally connect to another tile

            DeadEndNumber = DeadEndNumber + currentNonBlankNumber - connectEdge - countNeighbor;
           // System.out.println(currentNonBlankNumber - connectEdge - countNeighbor);
          //  System.out.println(DeadEndNumber);
        }
        return DeadEndNumber;
    }

    /**
     * this method is to deal with surpass tiles, substitute surpass tile with corresponding
     * A1 or A4 tile  with certain orientation so that when checking tile connection,
     * surpass tile won't connect two routes that are not connected
     * @param connected
     * @param surpass
     * @return the new tile placement string that substitute the original surpass tile placement String
     */

    public static String dealWithSurpass(String connected, String surpass){

        String target = "";
        Tile connectedTile = new Tile(connected);
        Tile surpassTile = new Tile(surpass);
        List<String> orienStirng = new ArrayList<String>(List.of("upside","downside","left","right"));

        String connectionSide = surpassTile.spot.connectionside(connectedTile.spot);
        if(connectionSide == "left" || connectionSide == "right"){
            // if the connected tile and surpass tile are connected in a horizontal way
            if(surpassTile.left == 'r'){
                target = target + "A1";
                target = target + surpass.substring(2,4); // add the spot place
                target = target + '1';
            }
            if(surpassTile.left == 'h'){
                target = target + "A4";
                target = target + surpass.substring(2,4); // add the spot place
                target = target + '1';
            }
        }
        if(connectionSide == "upside" || connectionSide == "downside"){
            // if the connected tile and surpass tile are connected in a vertical way
            if(surpassTile.up == 'r'){
                target = target + "A1";
                target = target + surpass.substring(2,4); // add the spot place
                target = target + '2';
            }
            if(surpassTile.up == 'h'){
                target = target + "A4";
                target = target + surpass.substring(2,4); // add the spot place
                target = target + '2';
            }
        }
        else if (!orienStirng.contains(connectionSide)){
            target = "something wrong with connection side";
        }

        return target;
    }

    /**
     * this method is to deal with the situation where surpass is connected to an exit
     * the logic of this method is similar to the previous one
     * @param surpassExit
     * @return the new tile placement string that substitute the original surpass tile placement String
     */
    public static String dealSurpassExit(String surpassExit){

        String target = "";

        Tile surpassExitTile = new Tile(surpassExit);
        if(surpassExitTile.spot.col == '0' || surpassExitTile.spot.col == '6'){
            // if the connected tile and surpass tile are connected in a horizontal way
                if(surpassExitTile.left == 'h'){
                    target = target + "A4";
                    target = target + surpassExit.substring(2,4); // add the spot place
                    target = target + '1';
                }
                if(surpassExitTile.left == 'r'){
                    target = target + "A1";
                    target = target + surpassExit.substring(2,4); // add the spot place
                    target = target + '1';
                }
        }
        if(surpassExitTile.spot.row == 'A' || surpassExitTile.spot.row == 'G'){
            // if the connected tile and surpass tile are connected in a vertical way
               if(surpassExitTile.up == 'h'){
                   target = target + "A4";
                   target = target + surpassExit.substring(2,4); // add the spot place
                   target = target + '2';

               }
               if(surpassExitTile.up == 'r' ){
                   target = target + "A1";
                   target = target + surpassExit.substring(2,4); // add the spot place
                   target = target + '2';
               }
        }

        if(target == ""){
            System.out.println("something wrong with dealSurpassExit");
        }
        return target;
    }

    /**
     * this method is to find all the new connection based on the old connection given total placement list
     * which means, given the old partially checked route, checking all the tiles in the placement list
     * and extend the route by one tile layer, and at the same time, if the connection tile is a surpass, we
     * should also do the substitute by apply the dealWithSurpass of dealSurpassExit method
     * @param oldConnectionList
     * @param placementList
     * @return new list of tile placement string
     */

    public static List<String> findNewConnection(List<String> oldConnectionList, List<String> placementList){

        List<String> newConnectionList = oldConnectionList;// first, we add all the elements in oldlist to the new list
        for(int CheckIndex = 0; CheckIndex<oldConnectionList.size(); CheckIndex++){
            //Then for each element in the oldConnectionList, we check through the total placement list
            // if there is any new tile placement string that is neighboring the current placement string
            String currentCheck = oldConnectionList.get(CheckIndex);
            //  Tile currentTile = new Tile(currentCheck);
            for(int placementIndex = 0; placementIndex<placementList.size();placementIndex++){
                String checkTotal = placementList.get(placementIndex);
                Tile checkTile = new Tile(checkTotal);
                if(areConnectedNeighbours(currentCheck,checkTotal)){
                    if(checkTile.piece.center == 'p'){// if the checkTile is a surpass
                        String replace = dealWithSurpass(currentCheck,checkTotal);// do the substitute
                        if(!newConnectionList.contains(replace)) {
                            newConnectionList.add(replace);
                        }
                    }
                    else{
                        if(!newConnectionList.contains(checkTotal)){
                            newConnectionList.add(checkTotal);
                        }
                    }
                }

            }
        }
        return newConnectionList;
    }

    /**
     * this method is to find all the tiles that connect to a given exit tile in tile placement list
     * given a placementList and an exitSpot
     * The logic behind this method is that each route starts with a connection to the exit, so the check begins with an exit tile
     * and extends the every tile that directly or indirectly connected to this tile
     * @param placementList
     * @param exitSpot
     * @return a list of tile placement string
     */

    public static  List<String> findConnectedRoute(List<String> placementList, String exitSpot){

        String targetTileplacement = "";

        for(int placementIndex = 0; placementIndex < placementList.size();placementIndex++){
            String checkTotal = placementList.get(placementIndex);
            if(checkTotal.substring(2,4).equals(exitSpot)){
                Tile checkTile = new Tile(checkTotal);
                if(checkTile.piece.center == 'p'){
                    String modifySurpass = dealSurpassExit(checkTotal);
                    // if the exit is connected directly to a surpass, then apply the deaSurpassExit method
                    targetTileplacement = targetTileplacement + modifySurpass;
                }
                else {
                    targetTileplacement = targetTileplacement + checkTotal;
                }
            } // find the tileplacement string that lies on the given exit spot
        }

        if(targetTileplacement == ""||targetTileplacement.length()>5){
            System.out.println("something wrong with findConnectedRoute");
        }

        List<String> oldConnection = new ArrayList<String>();
        oldConnection.add(targetTileplacement);

        List<String> newConnection = findNewConnection(oldConnection, placementList);
        while(oldConnection!=newConnection){
            // while there is new element adding in the newConnection
            oldConnection = newConnection;
            newConnection = findNewConnection(oldConnection, placementList);
        }
        return newConnection;
    }

    /**
     * this method is to find all the exits location that have been validly connected by a list of tile placement string
     * @param placementList
     * @return a list of spot string
     */

    public static List<String> findExitSpot(List<String> placementList){

        List<String> exitSpotList = new ArrayList<String>();

        for(int listindex = 0; listindex < placementList.size(); listindex++) {
            String currentCheck = placementList.get(listindex);
            //Tile currentTile = new Tile(currentCheck);
            if (isvalidExit(currentCheck)) {
                String currentSpot = currentCheck.substring(2,4);
                exitSpotList.add(currentSpot);

            }
        }
        // System.out.println(exitSpotList);
        return exitSpotList;
    }

    /**
     * this methods is to return the number of exits one route connects
     * @param oneConnectedRoute
     * @return the number of exits connected by th given route
     */
    public static int findNumberExit(List<String> oneConnectedRoute){

        int exitNumberCount = 0;

        for(int listindex = 0; listindex < oneConnectedRoute.size(); listindex++) {
            String currentCheck = oneConnectedRoute.get(listindex);
            //Tile currentTile = new Tile(currentCheck);
            if (isvalidExit(currentCheck)) {
                exitNumberCount++;

            }
        }
        //System.out.println("exit Number connected " + exitNumberCount);
        return exitNumberCount;
    }

    /**
     *
     * @param exitNumberCount
     * @return the score that corresponding to the exit number
     */

    public static int findRoutePoints(int exitNumberCount){
        int routePoints = 0;
        if(exitNumberCount == 1){ routePoints = 0;
            // System.out.println("Notice! this route only connects one exit")
            ;}
        if(exitNumberCount == 2){ routePoints = 4; }
        if(exitNumberCount == 3){ routePoints = 8; }
        if(exitNumberCount == 4){ routePoints = 12; }
        if(exitNumberCount == 5){ routePoints = 16; }
        if(exitNumberCount == 6){ routePoints = 20; }
        if(exitNumberCount == 7){ routePoints = 24; }
        if(exitNumberCount == 8){ routePoints = 28; }
        if(exitNumberCount == 9){ routePoints = 32; }
        if(exitNumberCount == 10){ routePoints = 36; }
        if(exitNumberCount == 11){ routePoints = 40; }
        if(exitNumberCount == 12){ routePoints = 45; }
        else if(exitNumberCount > 12){
            System.out.println("something wrong with extiNumber");
        }
        return routePoints;
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
        List<String> placementList = new ArrayList<String>();
        // slice the board string into tile placement string
        for(int stringindex = 0; stringindex < boardString.length(); stringindex = stringindex + 5){
            String currentPlacement = boardString.substring(stringindex,stringindex+5);
            placementList.add(currentPlacement);
        }
       // System.out.println(placementList);

        int deadEnd = findDeadEnd(boardString); //find the number of dead ends

        int centerScore = 0;

        for(int listindex = 0; listindex < placementList.size(); listindex++){
            String currentCheck = placementList.get(listindex);
            Tile currentTile = new Tile(currentCheck);

            if(currentTile.spot.isCenter()){
                centerScore++;
            }
        }  // find the number of centeral tiles

        int routeScore = 0;
        //List<String>  nextPlacementList = new ArrayList<String>();

        List<String> totalExitSpot = findExitSpot(placementList);
        // first we put all the exit that has tile connection in a list

        while(totalExitSpot.size() > 0) {
            List<String> currentRoute = findConnectedRoute(placementList, totalExitSpot.get(0));
           // System.out.println("current route is " + currentRoute);

            int exitNum = findNumberExit(currentRoute);
            int currentScore = findRoutePoints(exitNum);
            routeScore += currentScore;

            List<String> currentExitSpot = findExitSpot(currentRoute);
           // System.out.println("current Exit Spot is " + currentExitSpot);


            for (int spotIndex = 0; spotIndex < currentExitSpot.size(); spotIndex++) {
                String checkSpot = currentExitSpot.get(spotIndex);
                if (totalExitSpot.contains(checkSpot)) {
                    totalExitSpot.remove(checkSpot);
                }
            } // then every time we find a route, we eliminate all the exits that have been connected by this route
            // from the total exits list
            //System.out.println("after remove total is " + totalExitSpot);
        }

       // System.out.println("dead end number is " + deadEnd);
       // System.out.println("center score is " + centerScore);
       // System.out.println("route score is " + routeScore);


        int totalScore = routeScore + centerScore - deadEnd;
        return totalScore;
    }

    /**
     * Given a valid boardString and a dice roll for the round,
     * return a String representing an ordered sequence of valid piece placements for the round.
     *
     * @param boardString a board string representing the current state of the game as at the start of the round
     * @param diceRoll    a String representing a dice roll for the round
     * @return a String representing an ordered sequence of valid piece placements for the current round
     * @see RailroadInk#generateDiceRoll()
     */
    public static String generateMove(String boardString, String diceRoll) {
        // FIXME Task 10: generate a valid move

        //get empty neighbouring valid spot for first attempt
        ArrayList<Spot> firstValidSpot = getEmptyValidSpot(boardString);

        //check whether there is empty valid position in boardString
        if (firstValidSpot.size() > 0) {
            //do nothing
        } else {
            return "";
        }

        //split diceRoll into 4 die and put them into diceList
        ArrayList<String> diceList = new ArrayList<>();
        for (int i = 0; i < diceRoll.length(); i = i + 2) {
            diceList.add(diceRoll.substring(i, i + 2));
        }

        //calculate the numbers of special tile in boardString
        int currentSpecial = boardString.length() - boardString.replaceAll("S", "").length();
        int currentUseSpecial = 0;

        //initialize special tile list
        ArrayList<String> specialList = new ArrayList<>();
        specialList.add("S0");
        specialList.add("S1");
        specialList.add("S2");
        specialList.add("S3");
        specialList.add("S4");
        specialList.add("S5");

        //delete the used special tiles from specialList
        ArrayList<String> currentSpecialList = new ArrayList<>();
        for (int i = 0; i < boardString.length(); i = i + 5){
            if (boardString.substring(i, i + 1).equals("S")){
                currentSpecialList.add(boardString.substring(i, i + 2));
            }
        }
        for (int i = 0; i < currentSpecialList.size(); i++){
            for (int j = 0; j < specialList.size(); j++){
                if (currentSpecialList.get(i).equals(specialList.get(j))){
                    specialList.remove(j);
                    j = 0;
                }
            }
        }

        //test firstDie
        String firstAttempt = "";
        int firstStep = 0;

        //find the possible position using diceRoll
        loopFirst:
        for (int i = 0; i < firstValidSpot.size(); i++) {
            for (int j = 0; j < diceList.size(); j++) {
                for (int k = 0; k < 8; k++) {
                    firstAttempt = boardString + diceList.get(j) + firstValidSpot.get(i).toString() + k;
                    if (isValidPlacementSequence(firstAttempt)) {
                        diceList.remove(j);
                        firstStep = 1;
                        break loopFirst;
                    }
                }
            }
        }

        //check whether the first step is successful.
        // If it fails, test special tile.
        if (firstStep == 1) {
            //check whether there is empty valid position in firstAttempt
            if (getEmptyValidSpot(firstAttempt).size() > 0) {
                //do nothing
            } else {
                return firstAttempt.substring(firstAttempt.length() - 5);
            }
        } else if (currentSpecial < 3) {
            // if special tiles are used less than 3 times, try it
            loopFirstSpecial:
            for (int i = 0; i < firstValidSpot.size(); i++) {
                for (int j = 0; j < specialList.size(); j++) {
                    for (int k = 0; k < 8; k++) {
                        firstAttempt = boardString + specialList.get(j) + firstValidSpot.get(i).toString() + k;
                        if (isValidPlacementSequence(firstAttempt)) {
                            specialList.remove(j);
                            firstStep = 1;
                            currentUseSpecial = 1;
                            break loopFirstSpecial;
                        }
                    }
                }
            }
        } else {
            return "";
        }

        //double check whether the first step is successful
        if (firstStep == 1) {
            //check whether there is empty valid position in firstAttempt
            if (getEmptyValidSpot(firstAttempt).size() > 0) {
                //do nothing
            } else {
                return firstAttempt.substring(firstAttempt.length() - 5);
            }
        } else {
            return "";
        }

        //test the second die
        String secondAttempt = "";
        int secondStep = 0;
        ArrayList<Spot> secondValidSpot = getEmptyValidSpot(firstAttempt);

        //calculate the numbers of special tile in firstAttempt
        int secondSpecial = firstAttempt.length() - firstAttempt.replaceAll("S", "").length();

        //find the possible position using diceRoll
        loop:
        for (int i = 0; i < secondValidSpot.size(); i++) {
            for (int j = 0; j < diceList.size(); j++) {
                for (int k = 0; k < 8; k++) {
                    secondAttempt = firstAttempt + diceList.get(j) + secondValidSpot.get(i).toString() + k;
                    if (isValidPlacementSequence(secondAttempt)) {
                        diceList.remove(j);
                        secondStep = 1;
                        break loop;
                    }
                }
            }
        }

        //check whether the second step is successful.
        // If it fails, test special tile.
        if (secondStep == 1) {
            //check whether there is empty valid position in secondAttempt
            if (getEmptyValidSpot(secondAttempt).size() > 0) {
                //do nothing
            } else {
                return secondAttempt.substring(secondAttempt.length() - 10);
            }
        } else if (secondSpecial < 3 && currentUseSpecial == 0) {
            // if special tiles are used less than 3 times, and never used special tiles this round, try it
            loop:
            for (int i = 0; i < secondValidSpot.size(); i++) {
                for (int j = 0; j < specialList.size(); j++) {
                    for (int k = 0; k < 8; k++) {
                        secondAttempt = firstAttempt + specialList.get(j) + secondValidSpot.get(i).toString() + k;
                        if (isValidPlacementSequence(secondAttempt)) {
                            specialList.remove(j);
                            secondStep = 1;
                            currentUseSpecial = 1;
                            break loop;
                        }
                    }
                }
            }
        } else {
            return firstAttempt.substring(firstAttempt.length() - 5);
        }

        //double check whether the second step is successful
        if (secondStep == 1) {
            //check whether there is empty valid position in secondAttempt
            if (getEmptyValidSpot(secondAttempt).size() > 0) {
                //do nothing
            } else {
                return secondAttempt.substring(secondAttempt.length() - 10);
            }
        } else {
            return firstAttempt.substring(firstAttempt.length() - 5);
        }

        //test the third die
        String thirdAttempt = "";
        int thirdStep = 0;
        ArrayList<Spot> thirdValidSpot = getEmptyValidSpot(secondAttempt);

        //calculate the numbers of special tile in secondAttempt
        int thirdSpecial = secondAttempt.length() - secondAttempt.replaceAll("S", "").length();

        //find the possible position using diceRoll
        loop:
        for (int i = 0; i < thirdValidSpot.size(); i++) {
            for (int j = 0; j < diceList.size(); j++) {
                for (int k = 0; k < 8; k++) {
                    thirdAttempt = secondAttempt + diceList.get(j) + thirdValidSpot.get(i).toString() + k;
                    if (isValidPlacementSequence(thirdAttempt)) {
                        diceList.remove(j);
                        thirdStep = 1;
                        break loop;
                    }
                }
            }
        }

        //check whether the third step is successful.
        // If it fails, test special tile.
        if (thirdStep == 1) {
            //check whether there is empty valid position in thirdAttempt
            if (getEmptyValidSpot(thirdAttempt).size() > 0) {
                //do nothing
            } else {
                return thirdAttempt.substring(thirdAttempt.length() - 15);
            }
        } else if (thirdSpecial < 3 && currentUseSpecial == 0) {
            // if special tiles are used less than 3 times, and never used special tiles this round, try it
            loop:
            for (int i = 0; i < thirdValidSpot.size(); i++) {
                for (int j = 0; j < specialList.size(); j++) {
                    for (int k = 0; k < 8; k++) {
                        thirdAttempt = secondAttempt + specialList.get(j) + thirdValidSpot.get(i).toString() + k;
                        if (isValidPlacementSequence(thirdAttempt)) {
                            specialList.remove(j);
                            thirdStep = 1;
                            currentUseSpecial = 1;
                            break loop;
                        }
                    }
                }
            }
        } else {
            return secondAttempt.substring(secondAttempt.length() - 10);
        }

        //double check whether the third step is successful
        if (thirdStep == 1) {
            //check whether there is empty valid position in thirdAttempt
            if (getEmptyValidSpot(thirdAttempt).size() > 0) {
                //do nothing
            } else {
                return thirdAttempt.substring(thirdAttempt.length() - 15);
            }
        } else {
            return secondAttempt.substring(secondAttempt.length() - 10);
        }

        //test the last die
        String lastAttempt = "";
        int lastStep = 0;
        ArrayList<Spot> lastValidSpot = getEmptyValidSpot(thirdAttempt);

        //calculate the numbers of special tile in thirdAttempt
        int lastSpecial = thirdAttempt.length() - thirdAttempt.replaceAll("S", "").length();

        //find the possible position using diceRoll
        loop:
        for (int i = 0; i < lastValidSpot.size(); i++) {
            for (int j = 0; j < diceList.size(); j++) {
                for (int k = 0; k < 8; k++) {
                    lastAttempt = thirdAttempt + diceList.get(j) + lastValidSpot.get(i).toString() + k;
                    if (isValidPlacementSequence(lastAttempt)) {
                        lastStep = 1;
                        break loop;
                    }
                }
            }
        }

        //check whether the last step is successful.
        // If it fails, test special tile.
        if (lastStep == 1) {
            return lastAttempt.substring(lastAttempt.length() - 20);
        } else if (lastSpecial < 3 && currentUseSpecial == 0) {
            // if special tiles are used less than 3 times, and never used special tiles this round, try it
            loop:
            for (int i = 0; i < lastValidSpot.size(); i++) {
                for (int j = 0; j < specialList.size(); j++) {
                    for (int k = 0; k < 8; k++) {
                        lastAttempt = thirdAttempt + specialList.get(j) + lastValidSpot.get(i).toString() + k;
                        if (isValidPlacementSequence(lastAttempt)) {
                            lastStep = 1;
                            currentUseSpecial = 1;
                            break loop;
                        }
                    }
                }
            }
        } else {
            return thirdAttempt.substring(thirdAttempt.length() - 15);
        }

        //double check whether the last step is successful
        if (lastStep == 1) {
            //check whether there is empty valid position in lastAttempt
            if (getEmptyValidSpot(lastAttempt).size() > 0) {
                //do nothing
            } else {
                return lastAttempt.substring(lastAttempt.length() - 20);
            }
        } else {
            return thirdAttempt.substring(thirdAttempt.length() - 15);
        }

        //If a special tile is used this round, there is still one die left
        //test the left die
        String leftAttempt = "";
        int leftStep = 0;
        ArrayList<Spot> leftValidSpot = getEmptyValidSpot(lastAttempt);

        //find the possible position using diceRoll
        if (currentUseSpecial == 1) {
            loop:
            for (int i = 0; i < leftValidSpot.size(); i++) {
                for (int j = 0; j < diceList.size(); j++) {
                    for (int k = 0; k < 8; k++) {
                        leftAttempt = lastAttempt + diceList.get(j) + leftValidSpot.get(i).toString() + k;
                        if (isValidPlacementSequence(leftAttempt)) {
                            leftStep = 1;
                            break loop;
                        }
                    }
                }
            }
        }

        //check whether the left step is successful
        if (leftStep == 1) {
            return leftAttempt.substring(leftAttempt.length() - 25);
        } else {
            return lastAttempt.substring(lastAttempt.length() - 20);
        }
    }

    //get neighbouring empty valid spot list
    public static ArrayList<Spot> getEmptyValidSpot(String boardString) {

        //Split boardString into position
        StringBuilder hasCovered = new StringBuilder();
        ArrayList<Tile> currentTile = new ArrayList<>();

        for (int i = 0; i < boardString.length(); i = i + 5) {
            hasCovered.append(boardString.substring(i + 2, i + 4));
            currentTile.add(new Tile(boardString.substring(i, i + 5)));
        }

        //initialize exit tile
        ArrayList<Spot> exitSpot = new ArrayList<>();
        exitSpot.add(new Spot("A1", 'h', 'b', 'b', 'b'));
        exitSpot.add(new Spot("A3", 'r', 'b', 'b', 'b'));
        exitSpot.add(new Spot("A5", 'h', 'b', 'b', 'b'));

        exitSpot.add(new Spot("B6", 'b', 'r', 'b', 'b'));
        exitSpot.add(new Spot("D6", 'b', 'h', 'b', 'b'));
        exitSpot.add(new Spot("F6", 'b', 'r', 'b', 'b'));

        exitSpot.add(new Spot("G1", 'b', 'b', 'h', 'b'));
        exitSpot.add(new Spot("G3", 'b', 'b', 'r', 'b'));
        exitSpot.add(new Spot("G5", 'b', 'b', 'h', 'b'));

        exitSpot.add(new Spot("B0", 'b', 'b', 'b', 'r'));
        exitSpot.add(new Spot("D0", 'b', 'b', 'b', 'h'));
        exitSpot.add(new Spot("F0", 'b', 'b', 'b', 'r'));

        //Find every neighbouring valid spot
        ArrayList<Spot> neighbouringValidSpot = new ArrayList<>();
        ArrayList<Spot> allNeighbouringValidSpot = new ArrayList<>();
        for (int j = 0; j < currentTile.size(); j++) {
            neighbouringValidSpot = currentTile.get(j).neighbouringValidSpot();
            for (int k = 0; k < neighbouringValidSpot.size(); k++) {
                allNeighbouringValidSpot.add(neighbouringValidSpot.get(k));
            }
        }

        //find exit tile which is not covered
        ArrayList<Spot> unCoverExit = new ArrayList<>();
        for (int i = 0; i < exitSpot.size(); i++) {
            int isCovered = 0;
            for (int j = 0; j < currentTile.size(); j++) {
                if (exitSpot.get(i).toString().equals(currentTile.get(j).spot.toString())) {
                    isCovered = 1;
                }
            }
            if (isCovered == 0) {
                unCoverExit.add(exitSpot.get(i));
            }
        }

        //Find every neighbouring string except hasCovered
        HashSet<String> neighbouringValidString = new HashSet<>();
        for (int p = 0; p < allNeighbouringValidSpot.size(); p++) {
            if (!hasCovered.toString().contains(allNeighbouringValidSpot.get(p).toString())) {
                neighbouringValidString.add(allNeighbouringValidSpot.get(p).toString());
            }
        }

        //Find all empty valid neighbouring spot, merge and update the emptyValidSpot
        Iterator<String> iterator = neighbouringValidString.iterator();
        ArrayList<Spot> emptyValidSpot = new ArrayList<>();

        while (iterator.hasNext()) {
            int counter = 0;
            String tempSpot = iterator.next();
            for (int q = 0; q < allNeighbouringValidSpot.size(); q++) {
                if (tempSpot.equals(allNeighbouringValidSpot.get(q).toString())) {
                    emptyValidSpot.add(allNeighbouringValidSpot.get(q));
                    counter++;
                }
            }
            if (counter == 1) {
                //do nothing
            }
            if (counter == 2) {
                //update spot status and remove repetitive elements

                //update upEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 2).upEdge != 'b') {
                    //do nothing
                } else {
                    emptyValidSpot.get(emptyValidSpot.size() - 2).upEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).upEdge;
                }

                //update rightEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 2).rightEdge != 'b') {
                    //do nothing
                } else {
                    emptyValidSpot.get(emptyValidSpot.size() - 2).rightEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).rightEdge;
                }

                //update downEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 2).downEdge != 'b') {
                    //do nothing
                } else {
                    emptyValidSpot.get(emptyValidSpot.size() - 2).downEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).downEdge;
                }

                //update leftEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 2).leftEdge != 'b') {
                    //do nothing
                } else {
                    emptyValidSpot.get(emptyValidSpot.size() - 2).leftEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).leftEdge;
                }

                //remove last one repetitive element
                emptyValidSpot.remove(emptyValidSpot.size() - 1);
            }
            if (counter == 3) {
                //update spot status and remove repetitive elements

                //update upEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 3).upEdge != 'b') {
                    //do nothing
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 2).upEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 3).upEdge = emptyValidSpot.get(emptyValidSpot.size() - 2).upEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 1).upEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 3).upEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).upEdge;
                }

                //update rightEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 3).rightEdge != 'b') {
                    //do nothing
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 2).rightEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 3).rightEdge = emptyValidSpot.get(emptyValidSpot.size() - 2).rightEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 1).rightEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 3).rightEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).rightEdge;
                }

                //update downEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 3).downEdge != 'b') {
                    //do nothing
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 2).downEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 3).downEdge = emptyValidSpot.get(emptyValidSpot.size() - 2).downEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 1).downEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 3).downEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).downEdge;
                }

                //update leftEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 3).leftEdge != 'b') {
                    //do nothing
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 2).leftEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 3).leftEdge = emptyValidSpot.get(emptyValidSpot.size() - 2).leftEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 1).leftEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 3).leftEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).leftEdge;
                }

                //remove last two repetitive elements
                emptyValidSpot.remove(emptyValidSpot.size() - 1);
                emptyValidSpot.remove(emptyValidSpot.size() - 2);
            }
            if (counter == 4) {
                //update spot status and remove repetitive elements

                //update upEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 4).upEdge != 'b') {
                    //do nothing
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 3).upEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).upEdge = emptyValidSpot.get(emptyValidSpot.size() - 3).upEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 2).upEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).upEdge = emptyValidSpot.get(emptyValidSpot.size() - 2).upEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 1).upEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).upEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).upEdge;
                }

                //update rightEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 4).rightEdge != 'b') {
                    //do nothing
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 3).rightEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).rightEdge = emptyValidSpot.get(emptyValidSpot.size() - 3).rightEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 2).rightEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).rightEdge = emptyValidSpot.get(emptyValidSpot.size() - 2).rightEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 1).rightEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).rightEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).rightEdge;
                }

                //update downEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 4).downEdge != 'b') {
                    //do nothing
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 3).downEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).downEdge = emptyValidSpot.get(emptyValidSpot.size() - 3).downEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 2).downEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).downEdge = emptyValidSpot.get(emptyValidSpot.size() - 2).downEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 1).downEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).downEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).downEdge;
                }

                //update leftEdge
                if (emptyValidSpot.get(emptyValidSpot.size() - 4).leftEdge != 'b') {
                    //do nothing
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 3).leftEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).leftEdge = emptyValidSpot.get(emptyValidSpot.size() - 3).leftEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 2).leftEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).leftEdge = emptyValidSpot.get(emptyValidSpot.size() - 2).leftEdge;
                } else if (emptyValidSpot.get(emptyValidSpot.size() - 1).leftEdge != 'b') {
                    emptyValidSpot.get(emptyValidSpot.size() - 4).leftEdge = emptyValidSpot.get(emptyValidSpot.size() - 1).leftEdge;
                }

                //remove last three repetitive elements
                emptyValidSpot.remove(emptyValidSpot.size() - 1);
                emptyValidSpot.remove(emptyValidSpot.size() - 2);
                emptyValidSpot.remove(emptyValidSpot.size() - 3);
            }
        }

        //update exit tile state
        //contain
        for (int i = 0; i < emptyValidSpot.size(); i++) {
            int isContain = 0;
            for (int j = 0; j < unCoverExit.size(); j++) {
                if (emptyValidSpot.get(i).toString().equals(unCoverExit.get(j).toString())) {
                    isContain = 1;
                }
            }
            if (isContain == 1) {
                switch (emptyValidSpot.get(i).toString()) {
                    case "A1":
                    case "A5":
                        emptyValidSpot.get(i).upEdge = 'h';
                        break;
                    case "A3":
                        emptyValidSpot.get(i).upEdge = 'r';
                        break;

                    case "B6":
                    case "F6":
                        emptyValidSpot.get(i).rightEdge = 'r';
                        break;
                    case "D6":
                        emptyValidSpot.get(i).rightEdge = 'h';
                        break;

                    case "G1":
                    case "G5":
                        emptyValidSpot.get(i).downEdge = 'h';
                        break;
                    case "G3":
                        emptyValidSpot.get(i).downEdge = 'r';
                        break;

                    case "B0":
                    case "F0":
                        emptyValidSpot.get(i).leftEdge = 'r';
                        break;
                    case "D0":
                        emptyValidSpot.get(i).leftEdge = 'h';
                        break;
                }
            }
        }

        //not contain
        for (int i = 0; i < unCoverExit.size(); i++) {
            int isContain = 0;
            for (int j = 0; j < emptyValidSpot.size(); j++) {
                if (unCoverExit.get(i).toString().equals(emptyValidSpot.get(j).toString())) {
                    isContain = 1;
                }
            }
            if (isContain == 0) {
                emptyValidSpot.add(unCoverExit.get(i));
            }
        }
        return emptyValidSpot;
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
        List<Tile> tiles = new ArrayList<>();
        // slice the board string into Tile list
        for(int stringindex = 0; stringindex < boardString.length(); stringindex = stringindex + 5){
            String currentPlacement = boardString.substring(stringindex,stringindex+5);
            tiles.add(new Tile(currentPlacement));
        }

        int hCountMax = 0; // Store maximum length checked for highway
        int rCountMax = 0; // store maximum length checked for railway


        for(Tile tempTile: tiles){      //Calculates Longest Highway
            if(tempTile.up == 'h' || tempTile.down == 'h' || tempTile.left == 'h' || tempTile.right == 'h'){
                List<Path> unfinishedPaths = new ArrayList<>();         //Creates a list of tiles to scan
                Path currentPath = new Path();
                unfinishedPaths.add(currentPath);
                currentPath.open.add(tempTile);                         // Add the current tile to open path

                boolean isSearching = true;                             // Opens Search Loop

                while (isSearching) {

                    if (currentPath.open.size() == 1){
                        Tile currentTile = currentPath.open.get(0);
                        for (int i = 0; i < tiles.size(); i++) {                    //Adds Neighbouring Tiles to Open List and shifts current Tile to Closed List
                            if(tiles.get(i) != currentTile && currentTile.spot.isNeighboring((tiles.get(i)).spot) && currentTile.tileConnect(tiles.get(i)) == 'h' && !currentPath.closed.contains(tiles.get(i))) {

                                currentPath.open.add(tiles.get(i));
                            }
                        }
                        currentPath.closed.add(currentTile);
                        currentPath.open.remove(currentTile);
                    }

                    if (currentPath.open.size() > 1){                               //Condition where A path splits
                        for (int i = 1; i < currentPath.open.size(); i++){
                            Path secondary = new Path();
                            secondary.open.add(currentPath.open.get(i));
                            secondary.closed.addAll(currentPath.closed);
                            unfinishedPaths.add(secondary);
                        }
                        Tile x = currentPath.open.get(0);
                        currentPath.open.clear();
                        currentPath.open.add(x);
                    }


                    if (currentPath.open.size() == 0){                              //Counts the Length when it reaches the end of a specific path
                        if (hCountMax < currentPath.closed.size()){
                            hCountMax = currentPath.closed.size();
                        }
                        unfinishedPaths.remove(currentPath);
                        if (unfinishedPaths.size() > 0 ){
                            currentPath = unfinishedPaths.get(0);                   //Switches to next unfinished Paths
                        }
                        else {isSearching = false;}                                 //Closes the Search loop
                    }
                }
            }
        }

        for(Tile tempTile: tiles){  //Calculates Longest Railway
            if(tempTile.up == 'r' || tempTile.down == 'r' || tempTile.left == 'r' || tempTile.right == 'r'){
                List<Path> unfinishedPaths = new ArrayList<>();
                Path currentPath = new Path();
                unfinishedPaths.add(currentPath);
                currentPath.open.add(tempTile);

                boolean isSearching = true;

                while (isSearching) {

                    if (currentPath.open.size() == 1){                      //Adds Neighbouring Tiles to Open List and shifts current Tile to Closed List
                        Tile currentTile = currentPath.open.get(0);
                        for (int i = 0; i < tiles.size(); i++) {
                            if(tiles.get(i) != currentTile && currentTile.spot.isNeighboring((tiles.get(i)).spot) && currentTile.tileConnect(tiles.get(i)) == 'r' && !currentPath.closed.contains(tiles.get(i))) {

                                currentPath.open.add(tiles.get(i));
                            }
                        }
                        currentPath.closed.add(currentTile);
                        currentPath.open.remove(currentTile);
                    }

                    if (currentPath.open.size() > 1){                        //Condition where A path splits
                        for (int i = 1; i < currentPath.open.size(); i++){
                            Path secondary = new Path();
                            secondary.open.add(currentPath.open.get(i));
                            secondary.closed.addAll(currentPath.closed);
                            unfinishedPaths.add(secondary);
                        }
                        Tile x = currentPath.open.get(0);
                        currentPath.open.clear();
                        currentPath.open.add(x);
                    }


                    if (currentPath.open.size() == 0){                      //Counts the Length when it reaches the end of a specific path
                        if (rCountMax < currentPath.closed.size()){
                            rCountMax = currentPath.closed.size();
                        }
                        unfinishedPaths.remove(currentPath);
                        if (unfinishedPaths.size() > 0 ){
                            currentPath = unfinishedPaths.get(0);           //Switches to next unfinished Paths
                        }
                        else {isSearching = false;}                         //Closes the Search loop
                    }
                }
            }
        }

        return getBasicScore(boardString) + rCountMax + hCountMax ;
    }

}

