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
    public Hashtable<String, String> colors;

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
    public void setClicks(int amount){
        this.clicks = amount;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int newX){
        this.x = newX;
    }
    public void setY(int newY){
        this.y = newY;
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

    public void setPixel(){


    }




}
