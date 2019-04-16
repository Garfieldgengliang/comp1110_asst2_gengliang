package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class taskEightTest extends RailroadInk{
    public static void main(String[] args) {

        String testString = "A3A10A3A52A3G10B2F10S1B50A2B61A0C60A1B41B1A35A4A41A2B31A1C30B0D32A2C50A4E10A3D12B2B10A2F01A0G00A4D01B1A27S3B20A4C10A1D50A0F23B2G25A3E30A4E41S5E51B1E67A3G52B2G31A0F32A4G41";
        int testScore = RailroadInk.getBasicScore(testString);
        System.out.println(testScore);

    }

}

/*
List<String> placementList = new ArrayList<String>();
        // slice the board string into tile placement string
        String boardString = "A4A16B2B14S2C13S5D12S3D25";
        for(int stringindex = 0; stringindex < boardString.length(); stringindex = stringindex + 5){
            String currentPlacement = boardString.substring(stringindex,stringindex+5);
            placementList.add(currentPlacement);
        }



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
            }

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

                    // the number of deadEnds equals to number of roads in a tile minus the road ends that connect to the edge
                    // minus the road ends legally connect to another tile
                }
                else{
                    if(currentTile.left != 'b'){
                        connectEdge++;
                    }

                }
            }

            System.out.print(connectEdge + "   ");
            System.out.print(countNeighbor+ "   ");
            System.out.println(currentNonBlankNumber + "    ");


        }

        String teststr = "A4A10A1A30A1B30B0C32S0D30A3D41A4D51B1E44A2E30S5F31A3D21A3D13A1G30S2C10B2B10A1B01A3D01A0B23S4C23";
        int numDead = RailroadInk.findDeadEnd(teststr);
        System.out.println(numDead);
 */