Reviewer: Peng Chen (u6460012)
Component:

TempTiles(String piece, int pos){

            this.currentPiece = Piece.valueOf(piece);
            int row = 0;
            int tpos = pos;
            while (tpos > 2){
                tpos -= 2;
                row ++;
            }

            setImage(new Image(Viewer.class.getResource(URI_BASE + piece +".png").toString()));
            if (pos % 2 == 1){
                setLayoutX(ROLL_X_OFFSET);
            }
            else setLayoutX(ROLL_X_OFFSET + TT_OFFSET);


            setLayoutY(ROLL_Y_OFFSET + TT_OFFSET*row);
            setFitWidth(TILE_LENGTH);
            setFitHeight(TILE_LENGTH);
            setPickOnBounds(true);                                      //Enable Clicks on transparent Spaces of PNG Files
        }

Author: Joel Chua (u6708832)

Review Comments:

Q1. What are the best features of this code?
A: I think there are two best features of this part of code:
In line 22: The constant TILE_LENGTH is defined smart. Because when you need to use a constant repeatedly, define it at the front your code is a better way,
            which will avoid numbers are difficult to understand everywhere.
In line 24: This simple line of code resolves a hard problem of insensitive mouse click event for us.
            This property can let the mouse click event treat the tile png picture as a whole.
            If we don't do this, the transparent part of png picture will result in insensitive of mouse click event.


Q2. Is the code well-documented?
A: Yes, I feel this part of code is well-documented. In the key point of code, there are detailed comments.


Q3. Is the program decomposition (class and method structure) appropriate?
A: Yes, the program decomposition is appropriate because every several lines there are space line which is helpful to read.


Q4. Does it follow Java code conventions (for example, are methods and variables properly named), and is the style consistent throughout?
A: Yes, it is. But it could be improved. For example, in line 8, if the variable int tpos is commented, that would be perfect.


Q5. If you suspect an error in the code, suggest a particular situation in which the program will not function correctly.
A: I don't find suspect error in this part of code.