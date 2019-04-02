package comp1110.ass2;

public class Tile {

    Piece piece;
    Spot spot;
    char orientation;
    char left;
    char right;
    char up;
    char down;
    char centre;


    Tile (String placementString){
        this.piece = Piece.valueOf(placementString.substring(0,2));
        this.spot = new Spot(placementString.substring(2,4));
        this.orientation = placementString.charAt(4);

        char oricenter = this.piece.center;
        char oriup = this.piece.upside;
        char oridown = this.piece.downside;
        char orileft = this.piece.left;
        char oriright = this.piece.right;

        if(this.orientation == '0'){
            this.up = oriup;
            this.down = oridown;
            this.left = orileft;
            this.right = oriright;
            this.centre = oricenter;
        }

        if (this.orientation == '1') {

            this.up = orileft;
            this.down = oriright;
            this.left = oridown;
            this.right = oriup;
            this.centre = oricenter;
        }
        if (this.orientation == '2') {
            this.up = oridown;
            this.down = oriup;
            this.left = oriright;
            this.right = orileft;
            this.centre = oricenter;
        }
        if (this.orientation == '3') {
            this.up = oriright;
            this.down = orileft;
            this.left = oriup;
            this.right = oridown;
            this.centre = oricenter;
        }
        if (this.orientation == '4') {
            this.up = oriup;
            this.down = oridown;
            this.left = oriright;
            this.right = orileft;
            this.centre = oricenter;

        }
        if (this.orientation == '5') {
            this.up = oriright;
            this.down = orileft;
            this.left = oridown;
            this.right = oriup;
            this.centre = oricenter;
        }
        if (this.orientation == '6') {
            this.left = orileft;
            this.right = oriright;
            this.up = oridown;
            this.down = oriup;
            this.centre = oricenter;

        }
        if (this.orientation == '7') {
            this.up = orileft;
            this.down = oriright;
            this.left = oriup;
            this.right = oridown;
            this.centre = oricenter;
        }
        else{

        }
        // this construction takes a tilePlacementString and split this string into three parts,
        // piece number, spot number and orientation number to instanciate tile instance

    }

/*
    public void tileProperties(){

        // in the following code, write if conditions to seperate different situations, look up to Rotated
        // as reference

        // finally we will have this.left, this.right, this.down, this.up and this.center
    } */

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
