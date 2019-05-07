package comp1110.ass2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TileConnectTestU6708832 {
    @Test
    public void testRailConnect(){
        Tile t1 = new Tile("A1B10");
        Tile t2 = new Tile("A2C13");
        assertEquals(t1.tileConnect(t2), 'r'); // Test Up Down
        Tile t3 = new Tile("A1B21");
        Tile t4 = new Tile("A0B30");
        Tile t5 = new Tile("A0B11");
        assertEquals(t3.tileConnect(t4), 'r'); //Test Left Right
        assertEquals(t5.tileConnect(t3), 'r'); //Test Right Left
        Tile t6 = new Tile("B0C12");
        Tile t7 = new Tile("B1B11");
        assertEquals(t6.tileConnect(t7), 'r'); // Test Down Up
    }

    @Test
    public void testHighConnect(){
        Tile t1 = new Tile("A3B10");
        Tile t2 = new Tile("A3B25");
        assertEquals(t1.tileConnect(t2), 'h'); // Test Up Down
        Tile t3 = new Tile("A5D11");
        Tile t4 = new Tile("B0D23");
        assertEquals(t3.tileConnect(t4), 'h'); //Test Left Right
        Tile t5 = new Tile("A3D22");
        Tile t6 = new Tile("B1D11");
        assertEquals(t5.tileConnect(t6), 'h'); //Test Right Left
        Tile t7 = new Tile("B0C10");
        Tile t8 = new Tile("B0B12");
        assertEquals(t7.tileConnect(t8), 'h'); // Test Down Up
    }

    @Test(expected = NullPointerException.class)
    public void test2DownUpConnection(){
        Tile t1 = new Tile("B0C20");
        Tile t2 = new Tile("B0D20");  //Double Left/Right
        t1.tileConnect(t2); //Throws Null Pointer Exception
    }

    @Test(expected = NullPointerException.class)
    public void test2LeftRightConnection(){
        Tile t1 = new Tile("B0C21");
        Tile t2 = new Tile("B0C31");  //Double Left/Right
        t1.tileConnect(t2); //Throws Null Pointer Exception
    }

    @Test(expected = NullPointerException.class)
    public void testBesideBlankConnection(){
        Tile t3 = new Tile("A3C20");
        Tile t4 = new Tile("A3C40"); //Spots not beside
        t3.tileConnect(t4); //Throws Null Pointer Exception
    }


    @Test(expected = NullPointerException.class)
    public void testNotBeside(){
        Tile t3 = new Tile("S1C20");
        Tile t4 = new Tile("A1C41");
        t3.tileConnect(t4); //Throws Null Pointer Exception

    }
}
