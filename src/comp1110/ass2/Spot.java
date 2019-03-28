package comp1110.ass2;

public class Spot {

        int col; // or char col;
        int row;
        boolean exitproperty;

        public Spot(String SpotString){
            // in this constructor, get the information of col and row by
            // this.col = SpotString.chatAt(0)...sth like this
        }

        public void isExit(Spot testspot){
            // this method checks whether a spot is a neighboring an exit
            // by writing some if condition
            // and if this spot is neighboring exit, then this.exitproperty = true

        }

        public boolean isNeighboring(Spot testspotA, Spot testspotB){
            // this method checks whether two spots are neighboring
            // you may want to split the two charactor of a spot, like split"B6" into "B" and "6"
            // and "A6" into "A" and "6" to check if they are neighboring
            return false;
        }

        public boolean isCenter(Spot testspot){
            // this method check is a given spot in the center of the board
            return false;
        }
        
        public boolean isOccupied(){
            // for this method, we haven't think about its parameter and usage yet
            return  false;

        }



}
