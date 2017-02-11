import java.util.*;

/**
 * This class represents a player within the game of Hex and the methods that a player should make
 */
public class HumanPlayer implements PlayerInterface
{
    private Piece colour = Piece.UNSET;
    private GameState gameState = GameState.INCOMPLETE;
    /**
     * Ask the player to make a move.
     * 
     * @param  boardView   the current state of the board
     * @return        a Move object representing the desired place to place a piece
     * 
     * @throws NoValidMovesException Indicates that no valid moves are possible - e.g. all cells on the
     * board are already occupied by a piece.
     */
    public MoveInterface makeMove(Piece[][] boardView) throws NoValidMovesException
    {
        //Check if their are any more possible moves
        boolean unset = false;
        for(int i = 0; i < boardView.length; i++){
            for(int j = 0; j < boardView[0].length; j++){
                if(boardView[i][j].equals(Piece.UNSET)){
                    unset = true;
                    break;
                }
            }
        }
        //Display board to player
        DisplayBoard_qwnz51.showBoard(boardView, colour);
        MoveInterface move = new Move();
        int xPos = -1;
        int yPos = -1;
        if(!unset){
            throw new NoValidMovesException();
        }
        else{
            System.out.println();
            //Displays who's turn it is
            if(colour.equals(Piece.RED)){
                System.out.println("Red's turn");
            }
            else if(colour.equals(Piece.BLUE)){
                System.out.println("Blue's turn");
            }
            //Asks for a move or to concede
            System.out.print("Press 'M' to make a move or 'C' to concede: ");
            boolean methodSuccess = false;;
            while(!methodSuccess){
                Scanner in = new Scanner(System.in);
                String input = in.nextLine();
                if(input.equals("C") || input.equals("c")){
                    methodSuccess = move.setConceded();
                    if(colour.equals(Piece.RED)){
                        DisplayBoard_qwnz51.winner(Piece.BLUE);
                    }
                    else if(colour.equals(Piece.BLUE)){
                        DisplayBoard_qwnz51.winner(Piece.RED);
                    }
                }
                else if(input.equals("M") || input.equals("m")){
                    System.out.println();
                    int j = 0;
                    //Asks fo xPos and yPos of move
                    while(j == 0){
                        xPos = -1;
                        yPos = -1;
                        System.out.print("X position: ");
                        int k = 0;
                        while(k == 0){
                            try{
                                in = new Scanner(System.in);
                                xPos = in.nextInt()-1;
                                k = 1;
                            }
                            catch(InputMismatchException e){
                                System.out.println("INPUT ERROR");
                                System.out.print("Try again: ");
                            }
                        }
                        k = 0;
                        while(k == 0){
                            System.out.print("Y position: ");
                            try{
                                in = new Scanner(System.in);
                                yPos = in.nextInt()-1;
                                k = 1;
                            }
                            catch(InputMismatchException e){
                                System.out.println("INPUT ERROR");
                                System.out.print("Try again: ");
                            }
                        }
                        try{
                            methodSuccess = move.setPosition(xPos, yPos);
                            //Make a copy of the boardView and display this copy with the nextTurn's 'turn' label
                            //This means that if playing vs a computer the board will both show up that the computers turn is in progress
                            //Also display the players move before the computer has made a move
                            //Otherwise the players move will appear simultaneously with that of the computer player
                            Piece[][] boardCopy = new Piece[boardView.length][boardView[0].length];
                            for(int i  = 0; i < boardView.length; i++){
                                System.arraycopy(boardView[i], 0, boardCopy[i], 0, boardView[0].length);
                            }
                            boardCopy[xPos][yPos] = colour;
                            if(colour.equals(Piece.RED)){
                                DisplayBoard_qwnz51.showBoard(boardCopy, Piece.BLUE);
                            }
                            else if(colour.equals(Piece.BLUE)){
                                DisplayBoard_qwnz51.showBoard(boardCopy, Piece.RED);
                            }        
                            j = 1;
                        }
                        catch(InvalidPositionException e){
                            System.out.println("INVALID POSITION");
                            System.out.println("Try again: ");
                        }                        
                    }
                    //if move.setPosition or move.setConceeded returned false, display the error
                    if(!methodSuccess){
                        System.out.print("UNKNOWN ERROR: Move.setPosition()");
                    }                    
                }
                else{
                    System.out.println("INPUT ERROR");
                    System.out.print("Try again: ");
                }
            }
        }
        return move;
    }

    /**
     * Set the colour that this player will be
     * 
     * @param colour  A Piece (RED/BLUE) that this player will be
     * @return   true indicating that the method succeeded
     * 
     * @throws InvalidColourException   A colour other than RED/BLUE was provided
     * @throws ColourAlreadySetException  The colour has already been set for this player.
     */
    public boolean setColour(Piece colour) throws InvalidColourException, ColourAlreadySetException
    {
        if(!this.colour.equals(Piece.UNSET)){
            throw new ColourAlreadySetException();
        }
        else if(colour.equals(Piece.RED) == colour.equals(Piece.BLUE)){
            throw new InvalidColourException();
        }
        else if(this.colour.equals(Piece.UNSET) && !(colour.equals(Piece.RED) == colour.equals(Piece.BLUE))){
            this.colour = colour;
            return true;
        }
        System.out.println("UNKOWN ERROR: Player.setColour()");
        return false;
    }

    /**
     * Informs the player of the final game state. Player has Won, lost.
     * 
     * @param state   either WON or LOST
     * @return   true indicating method has compleated successfully.
     * 
     */
    public boolean finalGameState(GameState state)
    {
        //Checks game state hasn't already been set
        if(gameState.equals(GameState.INCOMPLETE)){
            gameState = state;
            return true;
        }
        System.out.println("UNKOWN ERROR: Player.finalGameState()");
        return false;
    }
}
