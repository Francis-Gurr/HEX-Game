------------------------------------------------------------------------
PROJECT TITLE: HEX Game
PURPOSE OF PROJECT: Final Project for Computer Science Module: COMP1011 Introduction to Programming
VERSION: Final submitted version
DATE: 03/05/2016
AUTHOR: Francis Gurr
MARK: 97%
------------------------------------------------------------------------

THE HEX BOARD GAME:
- A Strategy game played on a board of any size
- Two players: red and blue
- Idea is to make a link between your two sides
- Independently invented by Piet Hein (1942) and John Nash (1947)
- A game can only ever be won, there are no draws or ties
- It has been mathematically proven that there is a winning strategy, but it is not know what it is
- It can be played on a board of any size n*m, but is only fair when n=m
- https://en.wikipedia.org/wiki/Hex_(board_game)

THE ASSIGNMENT:
Produce a Hex game
 - Allow human player to play human player
 - Allow human player to play computer player
 - Allow computer player to play computer player

FEEDBACK:
The following is the feedback I recieved for the assignment.
I hope to improve the game further if I get the time.

qwnz51 (Francis Gurr)
                                                                                        Max Y/N | Score
Part 1: Board Class                                                                             |
Part 1.1                                                                                        |
Does the Board Class compile?                                                           3    Y  | 3
Trying to view a board before it is created gives NoBoardDefinedException               0.5  Y  | 0.5
Part 1.2                                                                                        |
Trying make move before board is created gives NoBoardDefinedException                  0.5  Y  | 0.5
Trying for winner before board is created gives NoBoardDefinedException                 0.5  Y  | 0.5
Create board of size 1x1 works correctly                                                1    Y  | 1
Trying to resize a board gives BoardAlreadySizedException                               0.5  Y  | 0.5
Trying to create a board of invalid size gives InvalidBoardSizeException                0.5  Y  | 0.5
Placing a piece at an invalid location causes an InvalidPositionException               0.5  Y  | 0.5
Part 1.3                                                                                        |
Part 1.4                                                                                        |
Board can be constructed at any size                                                    3    Y  | 3
Board is the correct orientation                                                        5    Y  | 5
Part 1.5                                                                                        |
Pieces are placed in the correct locations on the board                                 2    Y  | 2
Is the board stored in the Board class protected from changes                           1    Y  | 1
Placing a piece where a piece already exists causes a PositionAlreadyTakenException     1    Y  | 1
Placing two pieces consecutively of the same colour causes a InvalidColourException     1    N  | 0
Part 2: GameManager Class                                                                       |
Part 2.1                                                                                        |
Does the GameManager Class compile?                                                     3    Y  | 3
Part 2.2                                                                                        |
Calling playGame before anything else causes return of false                            1    N  | 0
Comment: uncaught exception : java.lang.NullPointerException                                    |
Specifying Piece.UNSET causes specifyPlayer to throw InvalidColourException             0.25 Y  | 0.25
Trying to set two players to be BLUE will throw ColourAlreadySetException               0.25 Y  | 0.25
Specifying an illegal board size will throw an InvalidBoardSizeException                0.25 Y  | 0.25
Trying to re-set the size of a game will throw a BoardAlreadySizedException             0.25 Y  | 0.25
Part 2.3                                                                                        |
Correct moves made by RED                                                               2.5  Y  | 2.5
Red player got end game state                                                           1.5  Y  | 1.5
Correct moves made by BLUE                                                              2.5  Y  | 2.5
Blue player got end game state                                                          1.5  Y  | 1.5
Part 2.4                                                                                        |
Prevent player from cheating                                                            5    Y  | 5
Part 2.5                                                                                        |
Player can concede                                                                      2    Y  | 2
Part 3: Human Player                                                                            |
Part 3.1                                                                                        |
Board is displayed                                                                      2    Y  | 2
Board is correctly orientated                                                           2    Y  | 2
Board is tilted correctly                                                               2    Y  | 2
Symbols are easy to interpret                                                           2    Y  | 2
It is obvious which way the player should be constructing their line                    2    Y  | 2
Part 3.2                                                                                        |
User is informed how to make a move                                                     3    Y  | 3
Interface allows move to be entered                                                     2    Y  | 2
The format for entering a move is simple and clear                                      2    N  | 1
Comment: Having to select concede each move is convoluted                                       |
Part 3.3                                                                                        |
Player is informed which colour they are                                                3    Y  | 3
Part 4: Testing for who has won                                                                 |
Part 4.1                                                                                        |
Board can detect a win on a 1x1 board                                                   6    Y  | 6
Part 4.2                                                                                        |
Board can detect a win on a 5x5 board                                                   6    Y  | 6
Part 4.3                                                                                        |
Board can detect a win on a complex 5x5 board                                           8    Y  | 8
Part 5: Computer Player                                                                         |
Part 5.1                                                                                        |
Working Computer player                                                                 10   Y  | 10
Part 5.2                                                                                        |
Entered to competition                                                                       Y  |
Position Score                                                                          10   3  | 10
                                                                                                |
Total                                                                                   100     | 97
