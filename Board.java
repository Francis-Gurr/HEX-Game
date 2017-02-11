import java.util.*;
/**
 * This class is used to communicate wth an instance of a Board.
 */
public class Board implements BoardInterface
{
    private int sizeX;
    private int sizeY;
    private Piece[][] board;

    /**
     * Specifiy the size of the board that we are playing on. Both numbers must be greater than zero
     * 
     * @param  sizeX      how wide the board will be
     * @param  sizeY      how tall the board will be
     * @returns boolean   true if the board could be set successfully
     * 
     * @throws InvalidBoardSizeException  If either size value is less than one.
     * @throws BoardAlreadySizedException If the board has already been created.
     */
    public boolean setBoardSize(int sizeX, int sizeY) throws InvalidBoardSizeException, BoardAlreadySizedException
    {
        if(board != null){
            throw new BoardAlreadySizedException();
        }
        else if(sizeX < 1 || sizeY < 1){
            throw new InvalidBoardSizeException();
        }
        else if(board == null && (sizeX > 0 || sizeY > 0)){
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            board = new Piece[sizeX][sizeY];
            for(int i = 0; i < sizeX; i++){
                for(int j = 0; j < sizeY; j++){
                    board[i][j] = Piece.UNSET;
                }
            }
            return true;
        }
        System.out.println("UNKOWN ERROR: Board.setBoardSize()");
        return false;
    }

    /**
     * This method will return a two dimentional array of Pieces which represents the current state of the 
     * board. As this is just a copy of the data it is safe to send to a Player.
     * 
     * @returns Piece[][]  a two dimentional representation of the game board.
     * 
     * @throws  NoBoardDefinedException   Thrown when a call is made to this method before the boardSize 
     * method.
     */
    public Piece[][] getBoardView() throws NoBoardDefinedException
    {
        if(board == null){
            throw new NoBoardDefinedException();
        }
        //Return a copy of the board
        Piece[][] boardCopy = new Piece[sizeX][sizeY];
        for(int i  = 0; i < sizeX; i++){
            System.arraycopy(board[i], 0, boardCopy[i], 0, sizeY);
        }
        return boardCopy;
    }

    /**
     * Places a piece on the board at the specified location.
     * 
     * @param colour     the colour of the piece to place (RED or BLUE)
     * @param move       the position where you wish to place a piece
     * @return boolean   true if the piece was placed successfully
     * 
     * @throws PositionAlreadyTakenException   if there is already a Piece in this position
     * @throws InvalidPositionException        if the specified position is invalid - e.g. (-1, -1)
     * @throws InvalidColourException          if the colour being set is invalid. E.g. if you try to place two BLUE pieces one after the other
     */
    public boolean placePiece(Piece colour, MoveInterface move) throws PositionAlreadyTakenException, InvalidPositionException, InvalidColourException, NoBoardDefinedException
    {
        int x = move.getXPosition();
        int y = move.getYPosition();
        boolean piecePlaced = false;
        if(board == null){
            throw new NoBoardDefinedException();
        }
        else if(x < 0 || x > sizeX || y < 0 || y > sizeY){
            throw new InvalidPositionException();
        }
        else if(!board[x][y].equals(Piece.UNSET)){
            throw new PositionAlreadyTakenException();
        }
        else if(colour.equals(Piece.RED) == colour.equals(Piece.BLUE)){
            throw new InvalidColourException();
        }
        else{
            board[x][y] = colour;
            piecePlaced = true;
        }
        if(!piecePlaced){
            System.out.println("UNKOWN ERROR: Board.placePiece()");
        }
        return piecePlaced;
    }

    /**
     * Checks to see if either player has won.
     * 
     * @return Piece   RED if red has won, BLUE if blue has won, UNSET if neither player has won.
     * 
     * @throws NoBoardDefinedException  Indicates that this method has been called before the boardSize 
     * method
     */
    public Piece gameWon() throws NoBoardDefinedException
    {
        if(board == null){
            throw new NoBoardDefinedException();
        }
        Piece winner = DepthFirstSearch_qwnz51.newDFS(board);
        if(!winner.equals(Piece.UNSET)){
            DisplayBoard_qwnz51.winner(winner);
        }        
        return winner;
    }
}
