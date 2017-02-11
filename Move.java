
/**
 * Represents a move a player wishes to make
 */
public class Move implements MoveInterface
{
    private int xPosition;
    private int yPosition;
    private boolean conceded;

    /**
     * Set the position that the Player wishes to use - both x and y coordinate.
     * 
     * @param x   the x coordinate
     * @param y   the y coordinate
     * @return    true indicating value set correctly
     * 
     * @throws  InvalidPositionException   The position is invalid. E.g. both x and y are negative.
     */
    public boolean setPosition(int x, int y) throws InvalidPositionException
    {
        if(x < 0 || y < 0){
            throw new InvalidPositionException();
        }
        else if(x >= 0 || y >= 0){
            xPosition = x;
            yPosition = y;
            return true;
        }
        System.out.println("UNKOWN ERROR: Move.setPosition()");
        return false;
    }

    /**
     * Has the player conceded in this move?
     * i.e. have they yielded to the fact that their opponent has won before all required
     * moves are made.
     *
     * @return true if the player has conceded.
     */
    public boolean hasConceded()
    {
        return conceded;
    }

    /**
     * Get the x coordinate of the move.
     * 
     * @return the x coordinate.
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * Get the y coordnate of the move.
     * 
     * @return the y coordinate.
     */
    public int getYPosition()
    {
        return yPosition;
    }

    /**
     * Indicate that the player has conceded in this move.
     * 
     * @return true indicating conceded is set.
     */
    public boolean setConceded()
    {
        if(!conceded){
            conceded = true;
            return true;
        }
        System.out.println("UNKOWN ERROR: Move.setConceded()");
        return false;
    }
}
