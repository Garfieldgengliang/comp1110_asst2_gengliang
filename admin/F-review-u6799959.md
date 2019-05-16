Reviewer: Gengliang Li (u6799959)
Component: <task10 public static String generateMove(String boardString, String diceRoll)>
Author: Peng Chen (uu6460012)

Review Comments:

1. The best features of the code?
I think there are two good features of the code. The first one is that the code has dealt with special tiles,
which means it considered the special tiles that has been used,
and whether there is still a chance to use the left special tiles.
The second one is that he write a method to get all the available spot on the board given a boardString,
which makes life easier.

2. Is the code well-documented?
Yes, I think the code has been well-documented, I find it’s easy to read and understand his code.

3. Is the program decomposition (class and method structure) appropriate?
I think the decomposition is not bad, but it can also be improved. For example, for each attempt,
it first check whether the normal tile can be used or not, then check the special can be used or not,
and this process repeat four times. I think this can be decomposed into a method.

4. Does it follow Java code conventions (for example, are methods and variables properly named),
and is the style consistent throughout?
Yes, the naming of methods and variable follows the convention of Java code, and the code style is basically fresh and neat.

5. A suspected error in the code
After carefully review the code, I found a ill-considered situation of the code.
During first time when he checks whether the special tiles can be used or not,
the specialTile list contains the whole 6 special tiles, which means he fails to consider
removing the previously used specialTiles. This may lead to duplicate special tiles in boardString,
but the situation is rare and thus inconspicuous.
