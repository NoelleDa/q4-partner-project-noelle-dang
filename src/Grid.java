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
    private boolean showCopyScreen = false;
    private boolean showMatchScreen = false;
    private boolean showScoreScreen = false;
    private boolean showTimerScreen = true;
    //graphics variables
    private Pixel[][] grid;
    private Pixel[][] matchGrid;
    private int gridCols = 10;
    private int gridRows = 10;
    public int xOffset = 65;
    public int yOffset = 80;
    protected int squareSize = 65;
    private int xGridSquare = 65;
    private int yGridSquare = 100;
    public String animationOverride = "";
    //constructor
    public Grid(){
        grid = new Pixel[gridRows][gridCols];
        matchGrid = new Pixel[gridRows][gridCols];
        fillGrid();
        fillMatchGrid();
    }

    // randomColor( (int) (Math.random()*9)
    public void fillGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = new Pixel( "White");
                matchGrid[i][j] = new Pixel(randomColor( (int) (Math.random()*9) ));
                grid[i][j].setClicks(0);
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
    public void fillMatchGrid(){
        for(int i = 0; i < matchGrid.length; i++){
            for(int j = 0; j < matchGrid[i].length; j++){
                matchGrid[i][j] = grid[i][j];
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
        if(showTimerScreen == true){
            timer += 20;
            if(timer % 1000 == 0){
                time --;
            }
            g.setFont(new Font("Impact",Font.PLAIN, 35 ));
            g.setColor(Color.BLACK);
            g.drawString("Time: " + displayMinutes(time) + ":" + displaySeconds(time),635,770 );
        }
        //when time runs out
        if(time == 0){
            showTimerScreen = false;
            showScoreScreen = true;
        }
        if(showScoreScreen){
            g.setColor(Color.white);
            g.setFont(new Font("IMPACT", Font.PLAIN, 50));
            g.drawString("Your Score is:" + score, 50,65);
            g.drawString(checkWinLose(score),450,65);
        }
    }
    //display time
    private long displayMinutes(long time){
        long amt = this.time / 60;
        return amt;
    }
    private long displaySeconds(long time){
        long amt = this.time / 60;
        long seconds = this.time - (amt * 60);
        return seconds;
    }
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
    public int getGridCols(){
        return  gridCols;
    }
    public int getGridRows(){
        return gridRows;
    }
    public Pixel getPixel(int row, int col){
        return grid[row][col];
    }

    public void setPixelClicks(int row, int col, int n){
        grid[row][col].setClicks(1);
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
    private int checkResults(Pixel[][] gridA, Pixel[][] gridB){
        Pixel[][] a = gridA;
        Pixel[][] b = gridB;
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                if(a[i][j] == b[i][j]){
                    score ++;
                }
            }
        }
        return score;
    }
    private void resetScore(){
        score = 0;
    }
    private String checkWinLose(int score){
        if(score >= 65){
            return "!! You Pass!!";
        }else{
            return "!? You FAIL!!";
        }
    }
    public void setPixel(int row, int col,String newColor, int clicks){
        grid[row][col] = new Pixel(newColor);
        grid[row][col].setClicks(clicks);

    }

} // end of class
