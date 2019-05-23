package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;

import static junit.framework.TestCase.*;


public class GetEmptyValidSpotTest {
    /**
     * Author: Peng Chen, u6460012
     * test method getEmptyValidSpot() in Task 10
     * pass a boardString and a diceRoll to generateMove(), get a moveString
     * combine moveString and original boardString to a newBoardString
     * check newBoardString using Task 6 method
     */

    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void testEmptyBoard() {
        String boardString = "";
        ArrayList<Spot> emptyValidSpot = RailroadInk.getEmptyValidSpot(boardString);
        ArrayList<String> emptyValidPlace = new ArrayList<>();
        emptyValidPlace.add("A1");
        emptyValidPlace.add("A3");
        emptyValidPlace.add("A5");

        emptyValidPlace.add("B6");
        emptyValidPlace.add("D6");
        emptyValidPlace.add("F6");

        emptyValidPlace.add("G1");
        emptyValidPlace.add("G3");
        emptyValidPlace.add("G5");

        emptyValidPlace.add("B0");
        emptyValidPlace.add("D0");
        emptyValidPlace.add("F0");

        //emptyValidPlace.add("A2");
        //emptyValidPlace.add("B4");
        //emptyValidPlace.add("C6");

        for(int i = 0; i < emptyValidSpot.size(); i++){
            boolean whetherLegal = false;
            for (int j = 0; j < emptyValidPlace.size(); j++){
                if (emptyValidSpot.get(i).toString().equals(emptyValidPlace.get(j))){
                    whetherLegal = true;
                    emptyValidPlace.remove(j);
                    j = 0;
                }
            }
            assertTrue("\"" + emptyValidSpot.get(i).toString() + "\"" + " is not a valid empty position.", whetherLegal);
        }

        if (emptyValidPlace.size() != 0){
            StringBuffer missingPosition = new StringBuffer();
            for (int i = 0; i < emptyValidPlace.size(); i++){
                missingPosition.append(emptyValidPlace.get(i) + " ");
            }
            fail("Valid empty position missing: " + missingPosition);
        }
    }

    @Test
    public void testOnePosition() {
        String boardString = "A0F00A0B00A0A31B1A14A0B61A0F61A0G32B1D61A0G43A0A62A0E61B1G56S1G60A0E03A0A03B1G12A0G02S0A50A0B50A5D03B1B40";
        ArrayList<Spot> emptyValidSpot = RailroadInk.getEmptyValidSpot(boardString);
        assertEquals("A4", emptyValidSpot.get(0).toString());
    }

    @Test
    public void testNoExits() {
        String boardString = "A5D00A0B03A0A30B1A10S3A20A0F03A0G32A5G53B1F60A5D61A0B61A0G43B1A54S1A40A0B40A0B31S4A60A5C62B1G12A0G01A0C00A5E62B1B23";
        ArrayList<Spot> emptyValidSpot = RailroadInk.getEmptyValidSpot(boardString);
        assertEquals("B1", emptyValidSpot.get(0).toString());
    }

    @Test
    public void testOneExit() {
        String boardString = "A0F00A0B00A0A30B1A10A0B61A0F61A0G32B1D61A0G43A0A62A0E61B1G56S1G60S5A20A0E03A0A03B1G12A0G02S0A50A0B50A0B41B1A41";
        ArrayList<Spot> emptyValidSpot = RailroadInk.getEmptyValidSpot(boardString);
        ArrayList<String> emptyValidPlace = new ArrayList<>();
        emptyValidPlace.add("B2");
        emptyValidPlace.add("D0");

        //emptyValidPlace.add("A1");
        //emptyValidPlace.add("C3");
        //emptyValidPlace.add("E4");

        for(int i = 0; i < emptyValidSpot.size(); i++){
            boolean whetherLegal = false;
            for (int j = 0; j < emptyValidPlace.size(); j++){
                if (emptyValidSpot.get(i).toString().equals(emptyValidPlace.get(j))){
                    whetherLegal = true;
                    emptyValidPlace.remove(j);
                    j = 0;
                }
            }
            assertTrue("\"" + emptyValidSpot.get(i).toString() + "\"" + " is not a valid empty position.", whetherLegal);
        }

        if (emptyValidPlace.size() != 0){
            StringBuffer missingPosition = new StringBuffer();
            for (int i = 0; i < emptyValidPlace.size(); i++){
                missingPosition.append(emptyValidPlace.get(i) + " ");
            }
            fail("Valid empty position missing: " + missingPosition);
        }
    }

    @Test
    public void testOthers() {
        String boardString = "A3A10B2A31A1B30A0F61A4A21B1B14A4A41A4D61S2A50A5A63A2B01A1C02B0G52S0B63A0E63A2E51A4D51B0C32A5D31A5C61A0E41S5D41B1D03A5B51A4G10A0C42B0G30";
        ArrayList<Spot> emptyValidSpot = RailroadInk.getEmptyValidSpot(boardString);
        ArrayList<String> emptyValidPlace = new ArrayList<>();
        emptyValidPlace.add("C5");
        emptyValidPlace.add("F0");
        emptyValidPlace.add("F1");
        emptyValidPlace.add("F3");
        emptyValidPlace.add("F5");

        //emptyValidPlace.add("A1");
        //emptyValidPlace.add("C3");
        //emptyValidPlace.add("E4");

        for(int i = 0; i < emptyValidSpot.size(); i++){
            boolean whetherLegal = false;
            for (int j = 0; j < emptyValidPlace.size(); j++){
                if (emptyValidSpot.get(i).toString().equals(emptyValidPlace.get(j))){
                    whetherLegal = true;
                    emptyValidPlace.remove(j);
                    j = 0;
                }
            }
            assertTrue("\"" + emptyValidSpot.get(i).toString() + "\"" + " is not a valid empty position.", whetherLegal);
        }

        if (emptyValidPlace.size() != 0){
            StringBuffer missingPosition = new StringBuffer();
            for (int i = 0; i < emptyValidPlace.size(); i++){
                missingPosition.append(emptyValidPlace.get(i) + " ");
            }
            fail("Valid empty position missing: " + missingPosition);
        }
    }
}
