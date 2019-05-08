package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static junit.framework.TestCase.assertEquals;

public class SurpassTestingu6799959 {

    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    String[] RailroadUp = {"A2C21","B2D21"};
    String[] RailroadDown = {"A1E10","B2D13"};
    String[] RailroadLeft = {"A0B21","B2B30"};
    String[] RailroadRight = {"S1F50","B2F42"};
    String[] HighwayUp = {"B1E22","B2F22"};
    String[] HighwayDown = {"S0B41","B2A40"};
    String[] HighwayRight = {"A5E30","B2E21"};
    String[] HighwayLeft = {"A4D51","B2D65"};
    String[] illegalPlace1 = {"B1E33","B2B30"};
    String[] illegalPlace2 = {"S1F20","B2E53"};

    @Test
    public void testRailRoadSubstitute(){ // test when the connection is railway
        String testRailup = RailroadInk.dealWithSurpass(RailroadUp[0],RailroadUp[1]);
        String testRaildown = RailroadInk.dealWithSurpass(RailroadDown[0],RailroadDown[1]);
        String testRailLeft = RailroadInk.dealWithSurpass(RailroadLeft[0],RailroadLeft[1]);
        String testRailRight = RailroadInk.dealWithSurpass(RailroadRight[0],RailroadRight[1]);

        assertEquals(testRailup,"A1D22"); // test if right substitute being made when the connected tile on the upside of surpass tile
        assertEquals(testRaildown,"A1D12"); // test if right substitute being made when the connected tile on the downside of surpass tile
        assertEquals(testRailLeft,"A1B31"); // test if right substitute being made when the connected tile on the left side of surpass tile
        assertEquals(testRailRight,"A1F41"); // test if right substitute being made when the connected tile on the right side of surpass tile

    }

    @Test
    public void testHighwaySubstitute(){ // test the connection is highway
        String testHighwayup = RailroadInk.dealWithSurpass(HighwayUp[0],HighwayUp[1]); // test if right substitute being made when the connected tile on the upside of surpass tile
        String testHighwaydown = RailroadInk.dealWithSurpass(HighwayDown[0],HighwayDown[1]); // test if right substitute being made when the connected tile on the downside of surpass tile
        String testHighwayleft = RailroadInk.dealWithSurpass(HighwayLeft[0],HighwayLeft[1]); // test if right substitute being made when the connected tile on the left side of surpass tile
        String testHighwayright = RailroadInk.dealWithSurpass(HighwayRight[0],HighwayRight[1]); // test if right substitute being made when the connected tile on the right side of surpass tile

        assertEquals(testHighwayup,"A4F22");
        assertEquals(testHighwaydown,"A4A42");
        assertEquals(testHighwayleft,"A4D61");
        assertEquals(testHighwayright,"A4E21");


    }

    @Test
    public void testIllegalPlacement(){ // test if the connection is wrong

        String testIllegalone = RailroadInk.dealWithSurpass(illegalPlace1[0],illegalPlace1[1]);
        String testIllegaltwo = RailroadInk.dealWithSurpass(illegalPlace2[0],illegalPlace2[1]);
        System.out.println("one is " + testIllegalone);
        System.out.println("two is " + testIllegaltwo);
        assertEquals(testIllegalone,"something wrong with connection side");
        assertEquals(testIllegaltwo,"something wrong with connection side");


    }

}
