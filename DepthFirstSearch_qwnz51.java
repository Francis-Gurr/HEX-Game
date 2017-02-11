
public class DepthFirstSearch_qwnz51
{
    private static boolean[][] blueVisited;
    private static boolean[][] redVisited;
    private static int sizeX;
    private static int sizeY;

    public static Piece newDFS(Piece[][] board){
        sizeX = board.length;
        sizeY = board[0].length;
        Piece winner = Piece.UNSET;
        redVisited = new boolean[sizeX][sizeY];
        //Check there's actually a red piece in the first and last row, if so then start a DFS for red
        for(int k = 0; k < sizeX; k++){
            if(board[k][sizeY-1].equals(Piece.RED)){                
                for(int i = 0; i < sizeX; i++){
                    if(board[i][0].equals(Piece.RED)){
                        if(redDFS(board, i, 0).equals(Piece.RED)){
                            return Piece.RED;
                        }
                    }
                }
                break;
            }
        }
        blueVisited = new boolean[sizeX][sizeY];
        //Check there's actually a blue piece in the first and last column, if so then start a DFS for blue
        for(int k = 0; k < sizeY; k++){
            if(board[sizeX-1][k].equals(Piece.BLUE)){
                for(int j = 0; j < sizeY; j++){
                    if(board[0][j].equals(Piece.BLUE)){
                        if(blueDFS(board, 0, j).equals(Piece.BLUE)){
                            return Piece.BLUE;
                        }
                    }
                }
                break;
            }
        }
        return winner;
    }

    //Same as newDFS() but doesn't bother checking for first and last row as the boards should all be full
    //Also just for red
    public static Piece aiRedDFS(Piece[][] board){
        sizeX = board.length;
        sizeY = board[0].length;
        Piece winner = Piece.UNSET;
        redVisited = new boolean[sizeX][sizeY];
        for(int i = 0; i < sizeX; i++){
            if(board[i][0].equals(Piece.RED)){
                if(redDFS(board, i, 0).equals(Piece.RED)){
                    return Piece.RED;
                }
            }
        }
        return winner;
    }
    
    //Same as newDFS() but doesn't bother checking for first and last column as the boards should all be full
    //Also just for blue
    public static Piece aiBlueDFS(Piece[][] board){
        sizeX = board.length;
        sizeY = board[0].length;
        Piece winner = Piece.UNSET;
        blueVisited = new boolean[sizeX][sizeY];
        for(int j = 0; j < sizeY; j++){
            if(board[0][j].equals(Piece.BLUE)){
                if(blueDFS(board, 0, j).equals(Piece.BLUE)){
                    return Piece.BLUE;
                }
            }
        }
        return winner; 
    }

    public static Piece blueDFS(Piece[][] board, int x, int y){
        //if reached last column just return blue
        if(x == sizeX-1){
            return Piece.BLUE;
        }
        //else check surrounding hexagons for unvisited blue pieces
        //check right adjacent hexes first (speeds up search as blue is left to right)
        else{
            blueVisited[x][y] = true;
            if(x+1 < sizeX){
                if(board[x+1][y].equals(Piece.BLUE) && blueVisited[x+1][y] == false){
                    if(blueDFS(board, x+1, y).equals(Piece.BLUE)){
                        return Piece.BLUE;
                    }
                }
                if(y > 0 && board[x+1][y-1].equals(Piece.BLUE) && blueVisited[x+1][y-1] == false){
                    if(blueDFS(board, x+1, y-1).equals(Piece.BLUE)){
                        return Piece.BLUE;
                    }
                }
            }
            if(y+1 < sizeY && board[x][y+1].equals(Piece.BLUE) && blueVisited[x][y+1] == false){
                if(blueDFS(board, x, y+1).equals(Piece.BLUE)){
                    return Piece.BLUE;
                }
            }
            if(y > 0 && board[x][y-1].equals(Piece.BLUE) && blueVisited[x][y-1] == false){
                if(blueDFS(board, x, y-1).equals(Piece.BLUE)){
                    return Piece.BLUE;
                }
            }
            if(x > 0){
                if(y+1 < sizeY && board[x-1][y+1].equals(Piece.BLUE) && blueVisited[x-1][y+1] == false){
                    if(blueDFS(board, x-1, y+1).equals(Piece.BLUE)){
                        return Piece.BLUE;
                    }
                }
                if(board[x-1][y].equals(Piece.BLUE) && blueVisited[x-1][y] == false){
                    if(blueDFS(board, x-1, y).equals(Piece.BLUE)){
                        return Piece.BLUE;
                    }
                }                
            }            
            return Piece.UNSET;
        }
    }

    public static Piece redDFS(Piece[][] board, int x, int y){
        //if reached last row just return red
        if(y == sizeY-1){
            return Piece.RED;
        }
        //else check surrounding hexagons for unvisited red pieces
        //check bottom adjacent hexes first (speeds up search as red is top to bottom)
        else{
            redVisited[x][y] = true;
            if(y+1 < sizeY){
                if(board[x][y+1].equals(Piece.RED) && redVisited[x][y+1] == false){
                    if(redDFS(board, x, y+1).equals(Piece.RED)){
                        return Piece.RED;
                    }
                }
                if(x > 0 && board[x-1][y+1].equals(Piece.RED) && redVisited[x-1][y+1] == false){
                    if(redDFS(board, x-1, y+1).equals(Piece.RED)){
                        return Piece.RED;
                    }
                }
            }
            if(x+1 < sizeX && board[x+1][y].equals(Piece.RED) && redVisited[x+1][y] == false){
                if(redDFS(board, x+1, y).equals(Piece.RED)){
                    return Piece.RED;
                }
            }
            if(x > 0 && board[x-1][y].equals(Piece.RED) && redVisited[x-1][y] == false){
                if(redDFS(board, x-1, y).equals(Piece.RED)){
                    return Piece.RED;
                }
            }
            if(y > 0 && board[x][y-1].equals(Piece.RED) && redVisited[x][y-1] == false){
                if(x+1 < sizeX && board[x+1][y-1].equals(Piece.RED) && redVisited[x+1][y-1] == false){
                    if(redDFS(board, x+1, y-1).equals(Piece.RED)){
                        return Piece.RED;
                    }
                }
                if(redDFS(board, x, y-1).equals(Piece.RED)){
                    return Piece.RED;
                } 
            }
            return Piece.UNSET;
        }
    }
}
