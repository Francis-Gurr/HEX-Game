import javax.swing.*;
import java.awt.*;

public class DisplayBoard_qwnz51 extends JFrame
{
    private static JFrame myFrame = new JFrame();
    private static JPanel myPanel = new JPanel();
    private static JLabel myLabel = new JLabel();
    
    public static void showBoard(Piece[][] board, Piece turn)
    {
        //Works out what the screen size is and sizes the board and hexagons accordingly
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        int x = board.length;
        int y = board[0].length;
        //Set default hexagon radius and frame size
        int r = 25;
        int frameWidth = 10+(x+3)*2*r+(y+1)*r;
        int frameHeight = 60+(y+3)*2*r;
        //If default is larger than the screen size then shrink all of the dimensions
        if(frameWidth > screenWidth || frameHeight > screenHeight){
            double widthRatio = (double)screenWidth/(double)frameWidth;
            double heightRatio = (double)screenHeight/(double)frameHeight;
            if(widthRatio < heightRatio){
                r = (int)(r*widthRatio*0.95);
                frameWidth = 10+(x+3)*2*r+(y+1)*r;
                frameHeight = 60+(y+3)*2*r;
            }
            else if(widthRatio > heightRatio){
                r = (int)(r*heightRatio*0.95);
                frameWidth = 10+(x+3)*2*r+(y+1)*r;
                frameHeight = 60+(y+3)*2*r;
            }
        }
        //Set JFrame to Exit on Close (hitting 'x' button) and gives the frame a title
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setTitle("Game of Hex");
        myFrame.setResizable(false);

        myPanel.setOpaque(true);
        myPanel.setBackground(Color.BLACK);
        myPanel.setLayout(null);         
        myPanel.removeAll();
        myPanel.revalidate();
        myPanel.repaint();

        //Display whose turn it is
        if(turn.equals(Piece.RED)){
            myLabel.setText("Red's turn");
            myLabel.setForeground(Color.RED);
        }
        else if(turn.equals(Piece.BLUE)){
            myLabel.setText("Blue's turn");
            myLabel.setForeground(Color.BLUE);
        }
        myLabel.setFont(new Font("SansSerif", Font.BOLD, r));
        myLabel.setSize(myLabel.getPreferredSize());
        int xLocation = (int)(frameWidth/2-Math.round(myLabel.getWidth()/2.0));
        myLabel.setLocation(xLocation, 10);
        myLabel.setBackground(Color.BLACK);
        myLabel.setOpaque(true);        
        myLabel.revalidate();
        myLabel.repaint();
        myPanel.add(myLabel);
        //Create hexagons
        for(int i = -1; i <= x; i++){
            for(int j = -1; j <= y; j++){                
                Shape hexagon = Hexagon_qwnz51.radiusShape(r);
                HexagonComponent_qwnz51 hex = null;
                int yPos = 10 + 2*r + ((j+1)*2*r);
                int xPos = 10 + ((j+1)*r + ((i+1)*2*r));
                //Corner blocks don't show up
                if((i == -1 && j == -1) || (i == x && j == y) || (i == -1 && j == y) || (i == x && j == -1)){
                    Color colour = Color.BLACK;
                    hex = new HexagonComponent_qwnz51(hexagon, colour);
                }
                //Set first and last column as blue
                else if(i == -1 || i == x){
                    Color colour = Color.BLUE;
                    hex = new HexagonComponent_qwnz51(hexagon, colour);
                }
                //Set first and last row as red
                else if(j == -1 || j == y){
                    Color colour = Color.RED;
                    hex = new HexagonComponent_qwnz51(hexagon, colour);
                }
                //Create hexagons for the board state
                else if(i < x && j < y){
                    Color colour = Color.WHITE;
                    if(board[i][j].equals(Piece.UNSET)){
                        colour = Color.WHITE;
                    }
                    else if(board[i][j].equals(Piece.RED)){
                        colour = Color.RED;
                    }
                    else if(board[i][j].equals(Piece.BLUE)){
                        colour = Color.BLUE;
                    }                    
                    hex = new HexagonComponent_qwnz51(hexagon, colour);
                    //For the unset hexagons, display their coordinates (aids with selecting the next move [maybe replace with a mouseListener for interactivity time permitting?])
                    if(board[i][j].equals(Piece.UNSET)){
                        JLabel coordLabel = new JLabel();
                        //Set text
                        coordLabel.setText((i+1) +" , "+ (j+1));
                        //Set font
                        coordLabel.setForeground(Color.BLACK);
                        coordLabel.setFont(new Font("SansSerif", Font.BOLD, (int)(r/2)));
                        coordLabel.setSize(coordLabel.getPreferredSize());
                        hex.setSize(hex.getPreferredSize());
                        //Centre label in hex
                        int hexHalfWidth = (int)Math.round(hex.getWidth()/2.0);
                        int halfWidth = (int)Math.round(coordLabel.getWidth()/2.0);
                        int halfHeight = (int)Math.round(coordLabel.getHeight()/2.0);
                        coordLabel.setLocation(xPos-halfWidth+hexHalfWidth, yPos+r-halfHeight);
                        coordLabel.setBackground(Color.WHITE);
                        coordLabel.setOpaque(true);
                        coordLabel.revalidate();
                        coordLabel.repaint();
                        coordLabel.setLabelFor(hex);
                        myPanel.add(coordLabel);
                    }
                }       
                hex.setSize(hex.getPreferredSize());
                hex.setLocation(xPos, yPos);
                hex.revalidate();
                hex.repaint();
                myPanel.add(hex);
            }
        }
        myPanel.revalidate();
        myPanel.repaint();
        myFrame.validate();
        myPanel.repaint();
        myFrame.setContentPane(myPanel);
        myFrame.setSize(frameWidth, frameHeight);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }

    public static void winner(Piece winner){
        JFrame endFrame = new JFrame();
        JPanel endPanel = new JPanel();
        JLabel winnerLabel = new JLabel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        //Set frame height and width
        int frameWidth = 500;
        int frameHeight = 500;
        //Set JFrame to Exit on Close (hitting 'x' button) and gives the frame a title
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setTitle("HEX WINNER");
        endFrame.setResizable(false);
        
        endPanel.setOpaque(true);
        endPanel.setBackground(Color.BLACK);
        endPanel.setLayout(null);         
        endPanel.removeAll();
        endPanel.revalidate();
        endPanel.repaint();

        Shape hexagon = Hexagon_qwnz51.radiusShape(200);
        HexagonComponent_qwnz51 hex = null;
        //Display the winner
        if(winner.equals(Piece.RED)){
            hex = new HexagonComponent_qwnz51(hexagon, Color.RED);
            winnerLabel.setText("RED WINS");
            winnerLabel.setBackground(Color.RED);
        }
        else if(winner.equals(Piece.BLUE)){
            hex = new HexagonComponent_qwnz51(hexagon, Color.BLUE);
            winnerLabel.setText("BLUE WINS");
            winnerLabel.setBackground(Color.BLUE);
        }
        winnerLabel.setForeground(Color.BLACK);
        winnerLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        winnerLabel.setSize(myLabel.getMaximumSize());
        winnerLabel.setBounds(0, 0, winnerLabel.getMaximumSize().width, winnerLabel.getMaximumSize().height);
        //Set location of label
        int xLocation = (int)(frameWidth/2-Math.round(winnerLabel.getWidth()/2.0));
        int yLocation = (int)(frameHeight/2-Math.round(winnerLabel.getHeight()/2.0));
        winnerLabel.setLocation(xLocation, yLocation);
        winnerLabel.setOpaque(true);
        winnerLabel.revalidate();
        winnerLabel.repaint();
        endPanel.add(winnerLabel);
        
        hex.setSize(hex.getPreferredSize());
        //Set location of hex
        xLocation = (int)(frameWidth/2-Math.round(hex.getWidth()/2.0));
        yLocation = (int)(frameHeight/2-Math.round(hex.getHeight()/2.0));
        hex.setLocation(xLocation, yLocation);
        hex.revalidate();
        hex.repaint();
        endPanel.add(hex);

        endPanel.revalidate();
        endPanel.repaint();
        endFrame.validate();
        endPanel.repaint();
        endFrame.setContentPane(endPanel);
        endFrame.setSize(frameWidth, frameHeight);
        endFrame.setLocationRelativeTo(null);
        endFrame.setVisible(true);
    }
}
