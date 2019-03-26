package comp1110.ass2;

public class Board {
    int col;
    int row;
    int Num_Cols = 7;// Number of Columns 0-6
    int Num_Rows = 7;// Number of Rows A-G


    public Board(int row, int col) {
        this.row = row;
        this.col = col;
    }


    // Gets Board placement from String Index E.g B4 : row = 1 col = 4
    public Board (String index) {
        row = index.toCharArray()[0] - 'A';
        col = (int) index.toCharArray()[1];
    }

    public boolean isCentre(Board piece){ // checks if piece is in centre
        if (2<=row && row <= 4 && 2<=col && col<=4){
            return true;
        }
        return false;
    }

    public boolean isExit(Board piece){ // checks if piece is beside exit

        if (row % 2 == 1 && col %2 == 1 && (row ==0 || row == 7|| col == 0 || col == 7)){
            return true;
        }
        return false;
    }

    public String getPos(String tilePlacementString){ // Takes Placement String to Board Position in String
        char row = tilePlacementString.toCharArray()[2];
        char col = tilePlacementString.toCharArray()[3];
        char [] pos = {row, col};
        return new String(pos);
    }

    public boolean isWithinBoard(String tilePlacementString){ // Checks if Placement String is within Board
        Board place = new Board(tilePlacementString);
        if ( row <= Num_Rows && col <=Num_Cols){
            return true;
        }
        return false;
    }


}