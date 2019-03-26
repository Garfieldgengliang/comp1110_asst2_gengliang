package comp1110.ass2;

import java.util.Arrays;

public enum Tile {
    // we use "h" to denote highway, "r" as railway, "s" as station, "b" as blank and "p" as surpass
    S0("h","r","h","h","s"),
    S1("h","r","r","r","s"),
    S2("h","h","h","h","h"),
    S3("r","r","r","r","r"),
    S4("h","r","h","r","s"),
    S5("h","h","r","r","s"),
    A0("r","b","r","b","r"),
    A1("r","r","b","b","r"),
    A2("r","r","b","r","r"),
    A3("h","h","b","h","h"),
    A4("h","h","b","b","h"),
    A5("h","b","h","b","h"),
    B0("h","r","b","b","s"),
    B1("h","b","b","r","s"),
    B2("h","h","r","r","p");

    public String upside;
    public String downside;
    public String left;
    public String right;
    public String center;

    Tile(String upside, String downside, String left, String right, String center){
        this.center = center;
        this.upside = upside;
        this.downside = downside;
        this.left = left;
        this.right = right;
    }

    String [] toStringArray(){
        String [] output = new String[5];
        output[0] = this.upside;
        output[1]= this.downside;
        output[2]= this.left;
        output[3]= this.right;
        output[4] = this.center;
        return output;

    }


}
