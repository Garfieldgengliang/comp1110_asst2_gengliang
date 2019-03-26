package comp1110.ass2;


import java.util.Arrays;

public class Rotated {
    /*
    String[] tile;
    char ori;

    Rotated (Tile tile, char ori){
        this.tile = toStringArray(tile);

    }*/

    public String[] TransTileInfo(String placement) {
        Tile origitile = Tile.valueOf(placement.substring(0,2));
        char direction = placement.charAt(4);
        String[] origiString = origitile.toStringArray();
        String oriup = origiString[0];
        String oridown = origiString[1];
        String orileft = origiString[2];
        String oriright = origiString[3];
        String oricenter = origiString[4];
        if (direction == '0') {

        }
        if (direction == '1') {

            origiString[0] = orileft;
            origiString[1] = oriright;
            origiString[2] = oridown;
            origiString[3] = oriup;
        }
        if (direction == '2') {
            origiString[0] = oridown;
            origiString[1] = oriup;
            origiString[2] = oriright;
            origiString[3] = orileft;
        }
        if (direction == '3') {
            origiString[0] = oridown;
            origiString[1] = oriup;
            origiString[2] = oriright;
            origiString[3] = orileft;
        }
        if (direction == '4') {
            origiString[2] = oriright;
            origiString[3] = orileft;

        }
        if (direction == '5') {
            origiString[0] = oriright;
            origiString[1] = orileft;
            origiString[2] = oridown;
            origiString[3] = oriup;
        }
        if (direction == '6') {
            origiString[0] = oridown;
            origiString[1] = oriup;

        }
        if (direction == '7') {
            origiString[0] = orileft;
            origiString[1] = oriright;
            origiString[2] = oriup;
            origiString[3] = oridown;
        }

        return origiString;


    }

/*    public static void main(String[] args) {
        String test = new String();
        test = "A0B57";
        Rotated r = new Rotated();
        String[] result = r.TransTileInfo(test);

        System.out.println(Arrays.toString(result));
    } */
}
