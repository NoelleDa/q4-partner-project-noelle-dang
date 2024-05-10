import java.awt.*;

public class Grid {
    //instance variables
    //timer variables
    private long timer = 0; // animation timer - time based on the animation speed
    private long time = 180;
    private long nextLevelTimer = 0;
    private long nextLevelTime = 3;
    //scoring variables
    private int score = 0;
    private int level = 0;
    private boolean levelIncrease = false;
    private int prevLevel = level - 1;
    //showScreen booleans
    //graphics variables
    public Pixel[][] grid;
    public Pixel[][] matchGrid;
    private int gridCols = 10;
    private int gridRows = 10;
    public int xOffset = 65;
    public int yOffset = 80;
    protected int squareSize = 65;
    private int xGridSquare = 65;
    private int yGridSquare = 100;
    public String animationOverride = "";
    //constructor
    public Grid(String fill){
        grid = new Pixel[gridRows][gridCols];
        matchGrid = new Pixel[gridRows][gridCols];
        if(fill == "White"){
            fillGrid();
        }else if(fill == "random"){
            randomRound(0);
        }
    }
    public void fillGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = new Pixel( "White" ) ;
                matchGrid[i][j] = new Pixel(randomColor( (int) (Math.random()*9) ));
                grid[i][j].setClicks(-1);
                grid[i][j].setX(xGridSquare);
                grid[i][j].setY(yGridSquare);
                xGridSquare += squareSize;
                if(xGridSquare == (gridCols * squareSize) + squareSize) {
                    yGridSquare += squareSize;
                    xGridSquare = squareSize;
                }
            }
        }
    }
    public void fillMatchGridWithHeart(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = new Pixel("White");
                grid[i][j].setX(xGridSquare);
                grid[i][j].setY(yGridSquare);
                xGridSquare += squareSize;
                if(xGridSquare == (gridCols * squareSize) + squareSize) {
                    yGridSquare += squareSize;
                    xGridSquare = squareSize;
                }
            }
        }
        //black outline
        grid[1][2].setColor("Black");
        grid[1][3].setColor("Black");
        grid[1][6].setColor("Black");
        grid[1][6].setColor("Black");
        grid[1][7].setColor("Black");
        grid[2][1].setColor("Black");
        grid[2][4].setColor("Black");
        grid[2][5].setColor("Black");
        grid[2][8].setColor("Black");
        grid[3][0].setColor("Black");
        grid[3][9].setColor("Black");
        grid[3][9].setColor("Black");
        grid[4][0].setColor("Black");
        grid[4][9].setColor("Black");
        grid[5][0].setColor("Black");
        grid[5][9].setColor("Black");
        grid[6][1].setColor("Black");
        grid[6][8].setColor("Black");
        grid[7][2].setColor("Black");
        grid[7][7].setColor("Black");
        grid[8][3].setColor("Black");
        grid[8][6].setColor("Black");
        grid[9][4].setColor("Black");
        grid[9][5].setColor("Black");
        //red
        grid[2][2].setColor("Red");
        grid[2][3].setColor("Red");
        grid[2][6].setColor("Red");
        grid[2][7].setColor("Red");
        grid[3][1].setColor("Red");
        grid[3][2].setColor("Red");
        grid[3][3].setColor("Red");
        grid[3][4].setColor("Red");
        grid[3][5].setColor("Red");
        grid[3][6].setColor("Red");
        grid[3][7].setColor("Red");
        grid[3][8].setColor("Red");
        grid[4][1].setColor("Red");
        grid[4][2].setColor("Red");
        grid[4][3].setColor("Red");
        grid[4][4].setColor("Red");
        grid[4][5].setColor("Red");
        grid[4][6].setColor("Red");
        grid[4][7].setColor("Red");
        grid[4][8].setColor("Red");
        grid[5][1].setColor("Red");
        grid[5][2].setColor("Red");
        grid[5][3].setColor("Red");
        grid[5][4].setColor("Red");
        grid[5][5].setColor("Red");
        grid[5][6].setColor("Red");
        grid[5][7].setColor("Red");
        grid[5][8].setColor("Red");
        grid[6][2].setColor("Red");
        grid[6][3].setColor("Red");
        grid[6][4].setColor("Red");
        grid[6][5].setColor("Red");
        grid[6][6].setColor("Red");
        grid[6][7].setColor("Red");
        grid[7][3].setColor("Red");
        grid[7][4].setColor("Red");
        grid[7][5].setColor("Red");
        grid[7][6].setColor("Red");
        grid[8][4].setColor("Red");
        grid[8][5].setColor("Red");

    }
    public void fillWithMatchGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = new Pixel(randomColor( (int) (Math.random() * 9) ));
                grid[i][j].setX(xGridSquare);
                grid[i][j].setY(yGridSquare);
                xGridSquare += squareSize;
                if(xGridSquare == (gridCols * squareSize) + squareSize) {
                    yGridSquare += squareSize;
                    xGridSquare = squareSize;
                }
            }
        }
    }
    public int getLength(){
        return grid.length;
    }
    public int getHeight(){
        return grid[0].length;
    }
    public void paint(Graphics g){
        paintGridOutline(g);
        //time countdown graphics
        //when time runs out


    }
    //display time
    //drawing the lines for the grid
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
    public void setPixelClicks(int row, int col, int n){
        grid[row][col].setClicks(1);
    }
    private void randomRound(int num){
        if(num == 0){
            fillMatchGridWithHeart();
        }
    }
    public String randomColor(int num){
        if(num == 0){
            return "Black";
        }else if(num == 1){
            return "Blue";
        }else if(num == 2){
            return "Brown";
        }else if(num == 3){
            return "Green";
        }else if(num == 4){
            return "Orange";
        }else if(num == 5){
            return "Pink";
        }else if(num == 6){
            return "Purple";
        }else if(num == 7){
            return "Red";
        }else if(num == 8){
            return "White";
        }else{
            return "Yellow";
        }
    }
    public Pixel getValue(int row, int col){
        return grid[row][col];
    }

    private void resetScore(){
        score = 0;
    }
    public void setPixel(int row, int col,String newColor, int clicks){
        grid[row][col] = new Pixel(newColor);
        grid[row][col].setClicks(clicks);

    }

} // end of class
