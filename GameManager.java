/**
 * GameManager Class.
 */
public class GameManager implements GameManagerInterface
{
    private BoardInterface board;
    private PlayerInterface playerRed;
    private PlayerInterface playerBlue;

    /**
     * Define who will be playing each colour. This method will be called twice for each game once for
     * RED and once for BLUE.
     * 
     * @param  player     the player who will be playing red
     * @param  colour     the enum for a Piece (RED or BLUE)
     * @return boolean    true if the player was successfully set to the specified colour
     * 
     * @throws ColourAlreadySetException  If the colour is alredy allocated to a player
     */
    public boolean specifyPlayer(PlayerInterface player, Piece colour) throws InvalidColourException, ColourAlreadySetException
    {
        if(!colour.equals(Piece.RED) && !colour.equals(Piece.BLUE)){
            throw new InvalidColourException();
        }
        else if(colour.equals(Piece.RED)){
            if(playerRed != null){
                throw new ColourAlreadySetException();
            }
            player.setColour(Piece.RED);
            playerRed = player;
            return true;
        }
        else if(colour.equals(Piece.BLUE)){
            if(playerBlue != null){
                throw new ColourAlreadySetException();
            }
            player.setColour(Piece.BLUE);
            playerBlue = player;
            return true;
        }
        System.out.println("UNKOWN ERROR: GameManager.specifyPlayer()");
        return false;
    }

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
    public boolean boardSize(int sizeX, int sizeY) throws InvalidBoardSizeException, BoardAlreadySizedException
    {
        if(board != null){
            throw new BoardAlreadySizedException();
        }
        else if(sizeX < 1 || sizeY < 1){
            throw new InvalidBoardSizeException();
        }
        else if(board == null && (sizeX > 0 || sizeY > 0)){
            board = new Board();
            board.setBoardSize(sizeX, sizeY);
            return true;
        }
        System.out.println("UNKOWN ERROR: GameManager.boardSize()");
        return false;
    }

    /**
     * The core of the game manager. This requests each player to make a move and plays these out on the 
     * game board.
     */
    public boolean playGame()
    {
        Piece[][] boardState;
        boolean finished = false;
        Piece winner = Piece.UNSET;
        while(!finished){
            //Update boardState
            try{
                boardState = board.getBoardView();
            }
            catch(NoBoardDefinedException e){
                System.out.println("NO BOARD DEFINED");
                return false;                
            }            
            //PlayerRED move
            MoveInterface move = new Move();
            //Ensure methods run correctly (i.e. return true)
            boolean methodSuccess1 = false;
            while(!methodSuccess1){                
                try{
                    move = playerRed.makeMove(boardState);
                }
                catch(NoValidMovesException e){
                    System.out.println("NO VALID MOVES");
                    //Break out and move on to checking who has won (board is full & Hex game has no draws)
                    break;
                }
                //Check if red has chosen to concede
                if(move.hasConceded()){
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess2 = playerRed.finalGameState(GameState.LOST);
                    if(!methodSuccess2){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess3 = playerBlue.finalGameState(GameState.WON);
                    if(!methodSuccess3){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    return methodSuccess2 && methodSuccess3;
                }
                //Else place the red piece
                else{                    
                    try{
                        methodSuccess1 = board.placePiece(Piece.RED, move);
                    }
                    catch(InvalidPositionException e){
                        System.out.println("INVALID POSITION");
                    }
                    catch(PositionAlreadyTakenException e){
                        System.out.println("POSITION ALREADY TAKEN");
                    }
                    catch(InvalidColourException e){
                        System.out.println("INVALID COLOUR");
                        //Break loop in order to continue to the other player (the next colour)
                        break;
                    }
                    catch(NoBoardDefinedException e){
                        System.out.println("NO BOARD DEFINED");
                        return false;
                    }
                }
            }
            //Check if game has been won
            try{
                winner = board.gameWon();
                //Set final game state
                if(winner.equals(Piece.RED)){
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess2 = playerRed.finalGameState(GameState.WON);
                    if(!methodSuccess2){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess3 = playerBlue.finalGameState(GameState.LOST);
                    if(!methodSuccess3){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    return methodSuccess2 && methodSuccess3;
                }
                else if(winner.equals(Piece.BLUE)){
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess2 = playerBlue.finalGameState(GameState.WON);
                    if(!methodSuccess2){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess3 = playerRed.finalGameState(GameState.LOST);
                    if(!methodSuccess3){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    return methodSuccess2 && methodSuccess3;
                }
            }
            catch(NoBoardDefinedException e){
                System.out.println("NO BOARD DEFINED");
                return false;
            }
            //Update boardState
            try{
                boardState = board.getBoardView();
            }
            catch(NoBoardDefinedException e){
                System.out.println("NO BOARD DEFINED");
                return false;
            }
            //Ensure methods run correctly (i.e. return true)
            methodSuccess1 = false;
            while(!methodSuccess1){        
                //PlayerBLUE move            
                try{
                    move = playerBlue.makeMove(boardState);
                }
                catch(NoValidMovesException e){
                    System.out.println("NO VALID MOVES");
                    //Break out and move on to checking who has won (board is full & Hex game has no draws)
                    break;
                }
                //Check if blue has chosen to concede
                if(move.hasConceded()){
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess2 = playerBlue.finalGameState(GameState.LOST);
                    if(!methodSuccess2){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess3 = playerRed.finalGameState(GameState.WON);
                    if(!methodSuccess3){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    return methodSuccess2 && methodSuccess3;
                }
                //Else place the red piece
                else{
                    try{
                        methodSuccess1 = board.placePiece(Piece.BLUE, move);
                    }
                    catch(InvalidPositionException e){
                        System.out.println("INVALID POSITION");
                    }
                    catch(PositionAlreadyTakenException e){
                        System.out.println("POSITION ALREADY TAKEN");
                    }
                    catch(InvalidColourException e){
                        System.out.println("INVALID COLOUR");
                        //Break loop in order to continue to the other player (the next colour)
                        break;
                    }
                    catch(NoBoardDefinedException e){
                        System.out.println("NO BOARD DEFINED");
                        return false;
                    }
                }
            }
            //Check if game has been won
            try{
                winner = board.gameWon();
                //Set final game state
                if(winner.equals(Piece.RED)){
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess2 = playerRed.finalGameState(GameState.WON);
                    if(!methodSuccess2){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess3 = playerBlue.finalGameState(GameState.LOST);
                    if(!methodSuccess3){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    return methodSuccess2 && methodSuccess3;
                }
                else if(winner.equals(Piece.BLUE)){
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess2 = playerBlue.finalGameState(GameState.WON);
                    if(!methodSuccess2){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    //Check method ran correctly and didn't cause any errors (i.e. returns true)
                    boolean methodSuccess3 = playerRed.finalGameState(GameState.LOST);
                    if(!methodSuccess3){
                        System.out.println("UNKNOWN ERROR: Player.finalGameState()");
                    }
                    return methodSuccess2 && methodSuccess3;
                }
            }
            catch(NoBoardDefinedException e){
                System.out.println("NO BOARD DEFINED");
                return false;
            }
        }
        //playgame() should always continue running until game has been won (returns true) or there has been an error (returns false)
        //If this line is reached, neither of the above has happened (no winner, and not continuing to play on and no known errors)
        System.out.println("UNKNOWN ERROR: GameManager.playGame()");
        return false;
    }
}