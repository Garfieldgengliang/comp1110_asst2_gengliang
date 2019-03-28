package comp1110.ass2;

public class Tile {

    Piece piece;
    Spot spot;
    char Orientation;
    char left;
    char right;
    char up;
    char down;
    char centre;


    Tile (String placementString){
        this.piece = Piece.valueOf(placementString.substring(0,2));
        this.spot = new Spot(placementString.substring(2,4));
        this.Orientation = placementString.charAt(4);
        // this construction takes a tilePlacementString and split this string into three parts,
        // piece number, spot number and orientation number to instanciate tile instance

    }

    public void tileProperties(char Orientation){
        this.Orientation = Orientation;
        // in the following code, write if conditions to seperate different situations, look up to Rotated
        // as reference

        // finally we will have this.left, this.right, this.down, this.up and this.center
    }

    public boolean isValidExit(){
        //first we need to check if the spot is neighboring the exit by using spot.isExit()
        // if the answer is true, then we need to check if the connection between tile and exit is valid
        //for different exits, we check different side of tile, for example
        // if the exit is B0 then we check if testtile.left == 'r'
        return false;
    }

    public boolean isCernter(){
        boolean result = spot.isCenter();
        return result;
        // when we use this method, just write testTile.isCenter()
    }


}
