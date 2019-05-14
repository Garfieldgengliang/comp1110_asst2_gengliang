Reviewer: Joel Chua (u6708832)

Component: <public static boolean isValidPlacementSequence(String boardString)>

Author: Gengliang Li (u6799959)

Review Comments:

What are the best features of this code?

    - The use of sets to check if the size of the set is equivalent to the list, helps with efficiency.
    - Flow of logic in the method.
    

Is the code well-documented?

    - Could be documented better so that anyone reading the code would immediately understand what the coder is doing.

Is the program decomposition (class and method structure) appropriate?

    -Instead of Slicing the boardstring into a list of String, a list of tiles could've been created immediately and use the the methods in the tile class, such as tile.spot, to check if tiles are on same spot or for other purposes in the method.

    -Could've created a method to check the neighbouring tiles and connection type to see if the connection was valid, reducing the need of the many redundant if statements.
    
    E.g. Instead of: if((currentTile.up == 'h'&&currentNeighbor.down == 'r')||(currentTile.up == 'r'&&currentNeighbor.down == 'h')){
                                         return false;
                                     }
        It Could be: return currentTile.up == currentNeighbor.down;
        
    This reduces the need of listing it repeatedly as the main point is to check if the connection is invalid.
    
    - Could have shortened code in the for loop; 
        E.g. Instead of stringindex = stringindex + 5 -> stringindex += 5; 
        
    -Methods created for this component could've been simplified greatly by creating Methods in the Tile class before creating this component. Since we already created classes to give the program a structure, it should be utilised well.
     
Does it follow Java code conventions (for example, are methods and variables properly named), and is the style consistent throughout?

    - Style follows conventions, however minor spelling mistakes here and there.
    - Method naming is good and allows user to understand what is being created.
      

If you suspect an error in the code, suggest a particular situation in which the program will not function correctly.

    -NIL


