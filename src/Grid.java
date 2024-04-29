import java.awt.*;

public class Grid {

    //instance variables
    private Pixel[][] grid;
    private int gridCols = 10;
    private int gridRows = 10;
    public int xOffset = 65;
    public int yOffset = 80;
    public int squareSize = 65;
    public String animationOverride = "";
    //constructor
    public Grid(){
        grid = new Pixel[gridRows][gridCols];
        gridReset();
    }

    public int getLength(){
        return grid.length;
    }
    public int getHeight(){
        return grid[0].length;
    }
    public void paint(Graphics g){
        paintGridOutline(g);

    }

    private void paintGridOutline(Graphics g) {
        for (int a = 0; a < getLength();a++)
        {
            g.drawLine(xOffset, yOffset+(squareSize)*a, xOffset+(squareSize)*getLength(),
                    yOffset+(squareSize)*a);

            g.drawLine(xOffset+(squareSize)*a, yOffset, xOffset+(squareSize)*a,
                    yOffset+(squareSize)*getHeight());
        }
        g.drawLine(xOffset+(squareSize)*getLength(), yOffset,xOffset+(squareSize)*getLength(),
                yOffset+(squareSize)*getHeight());
        g.drawLine(xOffset, yOffset+(squareSize)*getHeight(),xOffset+(squareSize)*getLength(),
                yOffset+(squareSize)*getHeight());
    }
    //methods
    public void gridReset(){
        for(int i = 0; i < gridRows; i ++) {
            for (int j = 0; j < gridCols; j++) {
                grid[i][j] = new Pixel("white");
            }
        }
    }
    //adding

    public int getGridCols(){
        return  gridCols;
    }

    public int getGridRows(){
        return gridRows;
    }

    public Pixel getValue(int row, int col){
        return grid[row][col];
    }



} // end of class
