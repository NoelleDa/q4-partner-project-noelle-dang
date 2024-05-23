import java.awt.*;

public class Grid {
   //instance variables
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
        if(fill.equals("White")){
            fillGrid();
        }else if(fill.equals("random")){
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
    public void fillWithMatchGridHARD(){
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
    public void fillWithMatchGridEASY(){
        Pixel temp = new Pixel(randomColor((int) (Math.random() * 9)));
        Pixel temp1 = new Pixel(randomColor((int) (Math.random() * 9)));
        Pixel temp2 = new Pixel(randomColor((int) (Math.random() * 9)));
        Pixel temp3 = new Pixel(randomColor((int) (Math.random() * 9)));
        for(int i = 0; i < grid.length/2; i++){
            for(int j = 0; j < grid[i].length/2; j++){
                grid[i][j] = temp;
            }
        }
        for(int i = 0; i < grid.length/2; i++){
            for(int j = grid[i].length/2; j < grid[i].length; j++){
                grid[i][j] = temp1;
            }
        }
        for(int i = grid.length/2; i < grid.length; i++){
            for(int j = 0; j < grid[i].length/2; j++){
                grid[i][j] = temp2;
            }
        }
        for(int i = grid.length/2; i < grid.length; i++){
            for(int j = grid[i].length/2; j < grid[i].length; j++){
                grid[i][j] = temp3;
            }
        }
    }
    public void setWholeGridBlank(){
        for(int i = 0; i < gridRows; i++){
            for(int j = 0; j < gridCols; j++){
                grid[i][j].setColor("White");
            }
        }
        System.out.println("Editable grid has been reset!");
    }

    public void resetClicksOnGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j].resetClicks();
            }
        }
    }
    public void fillWithMatchGridMedium(){
        for(int i = 0; i < grid.length; i+=2){
            for(int j = 0; j <grid[i].length; j+=2){
                Pixel temp = new Pixel(randomColor((int) (Math.random() * 9)));
                grid[i][j] = temp;
                grid[i+1][j] = temp;
                grid[i][j+1] = temp;
                grid[i+1][j+1] = temp;
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
        grid[row][col].setClicks(n);
    }
    private void randomRound(int num){
        if(num == 0){
            fillWithMatchGridHARD();
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
} // end of class
