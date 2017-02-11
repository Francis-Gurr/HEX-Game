import java.util.*;
import java.awt.Point;
public class ComputerAI_qwnz51
{
    private static int[][] wins;
    private static int sizeX;
    private static int sizeY;

    //Calculates a next move using a Monte Carlo Tree search method
    public static Point calculateMove(Piece[][] board, Piece colour){
        sizeX = board.length;
        sizeY = board[0].length;
        //If the board is 1 wide/high place piece instantly
        if(colour.equals(Piece.RED) && sizeY == 1){
            for(int i = 0; i < sizeX; i++){
                if(board[i][0].equals(Piece.UNSET)){
                    return new Point(i,0);
                }
            }
        }
        else if(colour.equals(Piece.BLUE) && sizeX == 1){
            for(int i = 0; i < sizeY; i++){
                if(board[0][i].equals(Piece.UNSET)){
                    return new Point(0,i);
                }
            }
        }
        //Creates a list of all of the possible moves
        LinkedList<Point> availableMoves = new LinkedList<Point>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j].equals(Piece.UNSET)){
                    Point point = new Point(i, j);
                    availableMoves.add(point);
                }
            }
        }
        int size = availableMoves.size();
        //If there is only one more move left, return the last possible move available
        if(size == 1){
            return availableMoves.get(0);
        }
        //If the board is empty (i.e. first move) go in the centre

        if(size == sizeX*sizeY){    
            int xPos = (int)Math.round(sizeX/2.0) - 1;            
            int yPos = (int)Math.round(sizeY/2.0) - 1;
            return new Point(xPos, yPos);
        }
        double t = 200000000.0;
        //Equation removes variation in time per board (bigger boards are slower as arrays are bigger [e.g. therfore createMovesCollection takes longer and is called more often]) Used Excel to get as close as possible
        int triesPerHex = (int)(t/((Math.pow(size, 2.15))-15*size+100));
        //Stop errors for extremely large sized boards where above equation would = 0
        if(triesPerHex == 0){
            triesPerHex = 1;
        }
        wins = new int[sizeX][sizeY];
        //Repeat for the number of iterations specified
        for(int n = 0; n < triesPerHex; n++){
            Piece winner = Piece.UNSET;
            for(int i = 0; i < sizeX; i++){
                for(int j = 0; j < sizeY; j++){
                    if(board[i][j].equals(Piece.UNSET)){
                        //Create a copy of the board
                        Piece[][] boardCopy = new Piece[sizeX][sizeY];
                        for(int k = 0; k < sizeX; k++){
                            System.arraycopy(board[k], 0, boardCopy[k], 0, sizeY);
                        }
                        //Place piece
                        Piece currentColour = colour;                        
                        boardCopy[i][j] = currentColour;
                        //Remove piece from availableMoves list
                        Point currentPoint = new Point(i,j);
                        for(int k = 0; k < size; k++){
                            if(availableMoves.get(k).equals(currentPoint)){
                                availableMoves.remove(k);
                                break;
                            }
                        }                        
                        //Shuffle list
                        Collections.shuffle(availableMoves);
                        //Fill up board (faster than doing move -> DFS -> move -> DFS, as the recursive nature of the DFS means it is slow)
                        for(int k = 0; k < availableMoves.size(); k++){
                            //Switch between blue and red player
                            if(currentColour.equals(Piece.RED)){                            
                                currentColour = Piece.BLUE;
                            }
                            else if(currentColour.equals(Piece.BLUE)){
                                currentColour = Piece.RED;
                            }                            
                            Point move = availableMoves.get(k);
                            int posX = (int)move.getX();                
                            int posY = (int)move.getY();
                            boardCopy[posX][posY] = currentColour;
                        }                        
                        //Check who has won (will always return a colour as the board is full and there are no draws)
                        if(colour.equals(Piece.RED)){
                            winner = DepthFirstSearch_qwnz51.aiRedDFS(boardCopy);
                        }
                        else if(colour.equals(Piece.BLUE)){
                            winner = DepthFirstSearch_qwnz51.aiBlueDFS(boardCopy);
                        }                        
                        //If the winner is this players colour increment the 2D array of wins
                        if(winner.equals(colour)){
                            wins[i][j]++;
                        }
                        //Add the currentPoint back to the list
                        availableMoves.add(currentPoint);
                    }
                }
            }            
        }
        return mostSuccesful();
    }

    //Iterates through the 2D array of wins and returns the coordinate with the most wins
    public static Point mostSuccesful(){
        int mostWins = -1;
        LinkedList<Point> bestMovesList = new LinkedList<Point>();
        for(int i = 0; i < wins.length; i++){
            for(int j = 0; j < wins[0].length; j++){
                if(wins[i][j] > mostWins){                    
                    mostWins = wins[i][j];
                    bestMovesList.clear();
                    Point move = new Point(i, j);
                    bestMovesList.add(move);
                }
                else if(wins[i][j] == mostWins){
                    Point move = new Point(i, j);
                    bestMovesList.add(move);
                }
            }
        }
        //If there is no possible way for red to win (i.e. Other player is garunteed a win therefore most wins = 0) then return null so that computer can conceed
        if(mostWins == 0){
            return null;
        }        
        //Check if list has more than one entry, if so choose a random move (Same number of wins so all 'equal')
        Point bestMove = new Point();
        if(bestMovesList.size() == 1){
            bestMove = bestMovesList.get(0);
        }
        else{
            Random rand = new Random();
            int index = rand.nextInt(bestMovesList.size());
            bestMove = bestMovesList.get(index);
        }
        return bestMove;
    }
}
