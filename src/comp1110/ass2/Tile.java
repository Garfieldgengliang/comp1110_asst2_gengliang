package comp1110.ass2;

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
    private String downside;
    private String left;
    private String right;
    private String center;

    Tile(String upside, String downside, String left, String right, String center){
        this.center = center;
        this.upside = upside;
        this.downside = downside;
        this.left = left;
        this.right = right;
    }
/*
    public void transTile( char direction){
        String oriup = this.upside;
        String oridown = this.downside;
        String orileft =this.left;
        String oriright = this.right;


        if(direction == '0'){

        }
        if(direction == '1'){

            this.upside = orileft;
            this.downside = oriright;
            this.left = oridown;
            this.right = oriup;
        }
        if(direction == '2'){
            this.upside = oridown;
            this.downside = oriup;
            this.left = oriright;
            this.right = orileft;
        }
        if(direction == '3'){
            this.upside = oridown;
            this.downside = oriup;
            this.left = oriright;
            this.right = orileft;
        }
        if(direction == '4'){
            this.left = oriright;
            this.right = orileft;

        }
        if(direction == '5'){
            this.upside = oriright;
            this.downside = orileft;
            this.left = oridown;
            this.right = oriup;
        }
        if(direction == '6'){
            this.upside = oridown;
            this.downside = oriup;

        }
        if(direction == '7'){
            this.upside = orileft;
            this.downside = oriright;
            this.left = oriup;
            this.right = oridown;

        }else{  // maybe later we can through some exceptions here later.
        }


    }
*/


}
