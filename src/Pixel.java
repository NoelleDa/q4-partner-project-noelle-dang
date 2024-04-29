import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Hashtable;

public class Pixel {

    //instance variables
    private String color;
    private AffineTransform tx;
    private Image Color;
    public Hashtable<String, String> colors;

    //constructors
    public Pixel(String color){

        colors = new Hashtable<String,String>();
        colors.put(color, "Colors//" + color + ".png");
        if(colors.containsKey(color)){
            this.color = color;
        }else{
            this.color = "Invalid";
        }
    }
    //getters and setters
    public String getColor(){
        return this.color;
    }
    private int[] translateGridPosition(int[] a, Grid g){
        return new int[1];
    }
    public void setColor(String newColor){
        this.color = newColor;
    }

    public void paint (Graphics g, Grid grid, int x, int y){
        Graphics2D g2 = (Graphics2D) g;

    }
    public String toString(){
        return color;
    }

    public void setPixel(){


    }




}
