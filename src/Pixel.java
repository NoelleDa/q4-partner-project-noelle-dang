import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Hashtable;

public class Pixel {

    //instance variables
    private int clicks;
    private String color;
    private AffineTransform tx;
    private Image Color;
    private int x,y;
    private int rX, rY;
    private int squareSize = 65;
    public Hashtable<String, String> colors;

    /*
    White - 0 click
    Black - 1 click
    Blue - 2 click
    Brown - 3 click
    Green - 4 click
    Orange - 5 click
    Pink - 6 click
    Purple - 7 click
    Red - 8 click
    White - 9 click
    Yellow - 10 click
     */

    //constructors
    public Pixel(String color){
        colors = new Hashtable<String,String>();
        colors.put("Black","Colors//Black.png");
        colors.put("Blue","Colors//Blue.png");
        colors.put("Brown","Colors//Brown.png");
        colors.put("Green","Colors//Green.png");
        colors.put("Orange","Colors//Orange.png");
        colors.put("Pink","Colors//Pink.png");
        colors.put("Purple","Colors//Purple.png");
        colors.put("Red","Colors//Red.png");
        colors.put("White","Colors//White.png");
        colors.put("Yellow","Colors//Yellow.png");

        if(colors.containsKey(color)){
            this.color = color;
        }else{
            this.color = "Invalid";
        }
    }

    protected Image getImage(String path){
        Image tempImage = null;
        try{
            URL imageURL = Pixel.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        }catch (Exception e) {e.printStackTrace();}
        return tempImage;
    }
    //getters and setters
    public String getColor(){
        return this.color;
    }
    public int getClicks(){
        return this.clicks;
    }
    public void setClicks(int amount){
        this.clicks += amount;
        if(this.clicks == 1){
            this.color = "Black";
        }
        if(this.clicks == 2){
            this.color = "Blue";
        }
        if(this.clicks == 3){
            this.color = "Brown";
        }
        if(this.clicks == 4){
            this.color = "Green";
        }
        if(this.clicks == 5){
            this.color = "Orange";
        }
        if(this.clicks == 6){
            this.color = "Pink";
        }
        if(this.clicks == 7){
            this.color = "Purple";
        }
        if(this.clicks == 8){
            this.color = "Red";
        }
        if(this.clicks == 9){
            this.color = "Yellow";
        }
        if(this.clicks == 0 || this.clicks == 10 || this.clicks >= 10){
            this.color = "White";
            this.clicks = 0;
        }
    }
    public int getrX(){
        return rX;
    }
    public int getrY(){
        return rY;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int newX){
        this.x = newX;
        this.rX = newX + squareSize;
    }
    public void setY(int newY){
        this.y = newY;
        this.rY = newY + squareSize;
    }
    private int[] translateGridPosition(int[] a, Grid g){
        int[] output = {(a[1]*g.squareSize+g.xOffset),a[2]*g.squareSize+g.yOffset};
        return output;
    }
    public void setColor(String newColor){
        this.color = newColor;
    }

    public void paint (Graphics g, Grid grid, int x, int y){
        Graphics2D g2 = (Graphics2D) g;
        int[] passthrough = {1,x,y};
        int[] transform = translateGridPosition(passthrough,grid);
        tx = AffineTransform.getTranslateInstance(transform[0],transform[1]);
        Color = getImage(colors.get(color));
        g2.drawImage(Color,tx,null);
    }
    public String toString(){
        return color;
    }





}
