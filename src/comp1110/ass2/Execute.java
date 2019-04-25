package comp1110.ass2;

public class Execute {
    public static void main(String[] args) {
        String InitialString = "";
        for(int roundIndex = 0;roundIndex < 7; roundIndex ++ ){
            String EachRoundDiceRoll = RailroadInk.generateDiceRoll();
            String ValidBoardString = RailroadInk.generateMove(InitialString, EachRoundDiceRoll);

            InitialString = ValidBoardString;
            int currentScore = RailroadInk.getBasicScore(ValidBoardString);
            // At the same time, do sth with Viewer Class so we can initialize the four moves for each round
            // and we should visialize the currentScore, too
        }

        int finalScore = RailroadInk.getAdvancedScore(InitialString);
        // then we also visualize the finalScore
    }
}
