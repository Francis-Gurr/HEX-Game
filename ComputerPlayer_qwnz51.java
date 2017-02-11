import java.awt.Point;

/**
 * This class represents a computer AI within the game of Hex and the methods that a computer AI should make
 */
public class ComputerPlayer_qwnz51 implements PlayerInterface
{
    private Piece colour = Piece.UNSET;
    private GameState gameState = GameState.INCOMPLETE;
    /**
     * Ask the computer to make a move.
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
                }
            }
        }
        if(!unset){
            throw new NoValidMovesException();
        }
        //Make a move
        else{
            System.out.println();
            //Displays who's turn it is
            if(colour.equals(Piece.RED)){
                System.out.println("Red's turn");
            }
            else if(colour.equals(Piece.BLUE)){
                System.out.println("Blue's turn");
            }
            MoveInterface move = new Move();
            //Check move.setPosition doesn't fail (i.e. doesn't return false)
            boolean methodSuccess = false;            
            while(!methodSuccess){
                ComputerAI_qwnz51 ai = new ComputerAI_qwnz51();
                Point movePos = ai.calculateMove(boardView, colour);
                if(movePos == null){
                    methodSuccess = move.setConceded();
                    if(colour.equals(Piece.RED)){
                        DisplayBoard_qwnz51.winner(Piece.BLUE);
                    }
                    else if(colour.equals(Piece.BLUE)){
                        DisplayBoard_qwnz51.winner(Piece.RED);
                    }
                }
                else{
                    int x = (int)movePos.getX();
                    int y = (int)movePos.getY();                
                    try{
                        methodSuccess = move.setPosition(x, y);
                    }
                    catch(InvalidPositionException e){
                        e.getStackTrace();
                    }                    
                }
                //if move.setPosition or move.setConceeded returned false, display the error
                if(!methodSuccess){
                    System.out.print("UNKNOWN ERROR: Move.setPosition() or Move.setConceded()");
                }
            }
            return move;
        }
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
        System.out.println("UNKOWN ERROR: Computer.setColour()");
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
        System.out.println("UNKOWN ERROR: Computer.finalGameState()");
        return false;
    }
}
