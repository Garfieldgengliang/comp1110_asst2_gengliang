package comp1110.ass2;

import java.util.ArrayList;

/* Authors:
    1)Joel Chua, U6708832
    2)Gengliang Li, U6799959
    3)Peng Chen, U6460012
 */

public class Tile {

    Piece piece;
    Spot spot;
    char orientation;
    char left;
    char right;
    char up;
    char down;
    char centre;


    public Tile(String placementString) {
        this.piece = Piece.valueOf(placementString.substring(0, 2));
        this.spot = new Spot(placementString.substring(2, 4));
        this.orientation = placementString.charAt(4);

        char oricenter = this.piece.center;
        char oriup = this.piece.upside;
        char oridown = this.piece.downside;
        char orileft = this.piece.left;
        char oriright = this.piece.right;

        if (this.orientation == '0') {
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
        } else {

        }
        // this construction takes a tilePlacementString and split this string into three parts,
        // piece number, spot number and orientation number to instantiate tile instance

    }


    public char tileConnect(Tile other) {                   //Checks the property of connection of neighbouring Tiles.
        if(!this.spot.isNeighboring(other.spot)){
            throw new NullPointerException("Tiles are not beside one another");
        }
        if (this.up == 'h' && other.down == 'h' && (int) other.spot.row - (int) this.spot.row == -1 ||
                this.down == 'h' && other.up == 'h' && (int) other.spot.row - (int) this.spot.row == 1 ||
                this.left == 'h' && other.right == 'h' && (int) other.spot.col - (int) this.spot.col == -1 ||
                this.right == 'h' && other.left == 'h' && (int) other.spot.col - (int) this.spot.col == 1) {
            return 'h';
        } else if (this.up == 'r' && other.down == 'r' && (int) other.spot.row - (int) this.spot.row == -1 ||
                this.down == 'r' && other.up == 'r' && (int) other.spot.row - (int) this.spot.row == 1 ||
                this.left == 'r' && other.right == 'r' && (int) other.spot.col - (int) this.spot.col == -1 ||
                this.right == 'r' && other.left == 'r' && (int) other.spot.col - (int) this.spot.col == 1) {
            return 'r';
        } else return 'b';
    }
    

    //Generate neighbouring valid spot list which contains 4 elements (upSpot, rightSpot, downSpot, and leftSpot) at most.
    public ArrayList<Spot> neighbouringValidSpot() {
        ArrayList<Spot> neighbouringValidSpot = new ArrayList<>();

        //check upside
        if (this.up == 'h') {
            if (this.spot.row != 'A') {
                String upSpotString = String.valueOf((char) (this.spot.row - 1)) + this.spot.col;
                Spot upSpot = new Spot(upSpotString, 'b', 'b', 'h', 'b');
                neighbouringValidSpot.add(upSpot);
            }
        }
        if (this.up == 'r') {
            if (this.spot.row != 'A') {
                String upSpotString = String.valueOf((char) (this.spot.row - 1)) + this.spot.col;
                Spot upSpot = new Spot(upSpotString, 'b', 'b', 'r', 'b');
                neighbouringValidSpot.add(upSpot);
            }
        }

        //check right
        if (this.right == 'h') {
            if (this.spot.col != '6') {
                String rightSpotString = this.spot.row + String.valueOf(this.spot.col - '0' + 1);
                Spot rightSpot = new Spot(rightSpotString, 'b', 'b', 'b', 'h');
                neighbouringValidSpot.add(rightSpot);
            }
        }
        if (this.right == 'r') {
            if (this.spot.col != '6') {
                String rightSpotString = this.spot.row + String.valueOf(this.spot.col - '0' + 1);
                Spot rightSpot = new Spot(rightSpotString, 'b', 'b', 'b', 'r');
                neighbouringValidSpot.add(rightSpot);
            }
        }

        //check downside
        if (this.down == 'h') {
            if (this.spot.row != 'G') {
                String downSpotString = String.valueOf((char) (this.spot.row + 1)) + this.spot.col;
                Spot downSpot = new Spot(downSpotString, 'h', 'b', 'b', 'b');
                neighbouringValidSpot.add(downSpot);
            }
        }
        if (this.down == 'r') {
            if (this.spot.row != 'G') {
                String downSpotString = String.valueOf((char) (this.spot.row + 1)) + this.spot.col;
                Spot downSpot = new Spot(downSpotString, 'r', 'b', 'b', 'b');
                neighbouringValidSpot.add(downSpot);
            }
        }

        //check left
        if (this.left == 'h') {
            if (this.spot.col != '0') {
                String leftSpotString = this.spot.row + String.valueOf(this.spot.col - '0' - 1);
                Spot leftSpot = new Spot(leftSpotString, 'b', 'h', 'b', 'b');
                neighbouringValidSpot.add(leftSpot);
            }
        }
        if (this.left == 'r') {
            if (this.spot.col != '0') {
                String leftSpotString = this.spot.row + String.valueOf(this.spot.col - '0' - 1);
                Spot leftSpot = new Spot(leftSpotString, 'b', 'r', 'b', 'b');
                neighbouringValidSpot.add(leftSpot);
            }
        }
        return neighbouringValidSpot;
    }
    @Override
    public String toString(){
        return this.piece.toString() + this.spot.toString() + this.orientation;
    }
}
