import java.util.*;
/**
 * Main method.
 */
public class Main
{
    public static void main(String[] args)
    {
        PlayerInterface player1;
        PlayerInterface player2 = null;
        GameManagerInterface gameManager = new GameManager();

        System.out.println("GAME OF HEX");
        System.out.println("");
        //have game mode as enum?
        System.out.println("Press '1' for player vs. player");
        System.out.println("Press '2' for player vs. computer");
        System.out.println("Press '3' for computer vs. computer)");
        System.out.print("Game mode: ");
        int gameMode = 0;
        int i = 0;
        while(i == 0){
            try{
                Scanner in = new Scanner(System.in);
                gameMode = in.nextInt();
                if(gameMode < 1 || gameMode > 4){
                    System.out.println("INPUT ERROR");
                    System.out.print("Try again: ");
                }
                else{
                    i = 1;
                }
            }
            catch(InputMismatchException e){
                System.out.println("INPUT ERROR");
                System.out.print("Try again: ");
            }
        }
        i = 0;
        //Define players
        while(i == 0){
            Piece colour1 = Piece.UNSET;
            Piece colour2 = Piece.UNSET;
            if(gameMode == 3){
                player1 = new ComputerPlayer_qwnz51();
                player2 = new ComputerPlayer_qwnz51();
                colour1 = Piece.RED;
                colour2 = Piece.BLUE;
                //set computer 1 colour
                try{
                    gameManager.specifyPlayer(player1, colour1);
                }
                catch(ColourAlreadySetException e){
                    System.out.println("COLOUR ALREADY SET");
                    e.getStackTrace();
                }
                catch(InvalidColourException e){
                    System.out.println("INVALID COLOUR");
                    e.getStackTrace();
                }
                //set computer 2 colour
                try{
                    gameManager.specifyPlayer(player2, colour2);
                }
                catch(ColourAlreadySetException e){
                    System.out.println("COLOUR ALREADY SET");
                    e.getStackTrace();
                }
                catch(InvalidColourException e){
                    System.out.println("INVALID COLOUR");
                    e.getStackTrace();
                }
                i = 1;
            }
            else if(gameMode == 1 || gameMode == 2){
                player1 = new HumanPlayer();
                if(gameMode == 1){
                    player2 = new HumanPlayer();                       
                }
                else if(gameMode == 2){
                    player2 = new ComputerPlayer_qwnz51();
                }
                //Let player one choose a colour
                System.out.println();
                System.out.println("Choose colour (RED or BLUE)");
                System.out.print("Player 1: ");
                int j = 0;
                while(j == 0){
                    Scanner in = new Scanner(System.in);
                    String clr = in.nextLine();
                    if(clr.equals("RED") || clr.equals("red") || clr.equals("Red")){
                        colour1 = Piece.RED;
                        colour2 = Piece.BLUE;
                    }
                    else if(clr.equals("BLUE") || clr.equals("blue") || clr.equals("Blue")){
                        colour1 = Piece.BLUE;
                        colour2 = Piece.RED;
                    }
                    //set player 1 colour
                    try{
                        gameManager.specifyPlayer(player1, colour1);
                        j = 1;
                    }
                    catch(ColourAlreadySetException e){
                        System.out.println("COLOUR ALREADY SET");
                        e.getStackTrace();
                    }
                    catch(InvalidColourException e){
                        System.out.println("INVALID COLOUR");
                        System.out.print("Try again: ");
                    }
                }
                //set player 2 colour
                try{
                    gameManager.specifyPlayer(player2, colour2);
                }
                catch(ColourAlreadySetException e){
                    System.out.println("COLOUR ALREADY SET");
                    e.getStackTrace();                    
                }
                catch(InvalidColourException e){
                    System.out.println("INVALID COLOUR");
                    e.getStackTrace();                    
                }
                i = 1;
            }
            else{
                System.out.println("ERROR");
            }
        }
        //Define board size
        System.out.println();
        System.out.println("Define board size");
        i = 0;
        while(i == 0){
            try{
                System.out.print("Size x: ");
                Scanner in = new Scanner(System.in);
                int sizeX = in.nextInt();
                System.out.print("Size y: ");
                in = new Scanner(System.in);
                int sizeY = in.nextInt();
                gameManager.boardSize(sizeX, sizeY);
                i = 1;
            }
            catch(InvalidBoardSizeException e){
                System.out.println("INVALID BOARD SIZE");
                System.out.println("Try again");
            }
            catch(BoardAlreadySizedException e){
                System.out.println("BOARD ALREADY SIZED");
                i = 1;
            }
            catch(InputMismatchException e){
                System.out.println("INPUT ERROR");
                System.out.println("Try again");
            }
        }        
        if(gameManager.playGame()){
            System.out.println("finished");
        }
    }
}
