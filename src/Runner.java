import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

//*********************************
//* Complete Methods in Grid.java *
//*********************************

public class Runner extends JPanel implements KeyListener, ActionListener, MouseListener {
    Grid grid = new Grid("White");
    Grid matchGrid = new Grid("random");
    Point mousePosition = new Point(0,0);
    boolean mouseDown;
    Point tempClickPosition;
    int[] passthrough = new int[3];
    int score = 0;
    int frameDelay = 0;
    String activeAnimation = "none";
    boolean initialize = true;
    private AffineTransform tx;
    private Image Sprite;
    //timer variables

    private long timer = 0; // animation timer - time based on the animation speed
    private long time = 3;
    //show image variables
    private boolean showLoadingScreen = true;
    private boolean startGame = false;
    private boolean matchScreen = false;
    private boolean blankScreen = false;
    private boolean showScoreScreen = false;
    private boolean showInstructionsScreen = false;
    private boolean showTimerScreen = false;
    private int clickStart = 0;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Runner f = new Runner();
    }
    public void setActiveAnimation(String s) {
        activeAnimation = s;
    }
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tickRate();
        setScreen(g,"Background.png");
        if(showLoadingScreen){
            setScreen(g,"StartScreen.gif");
            if(clickStart > 0){
                showInstructionsScreen = true;
                showLoadingScreen = false;
                clickStart = 0;
                setTime(12);
                matchScreen = true;
                showTimerScreen = true;
            }
        }
        if(showTimerScreen){
            g.setFont(new Font("Impact",Font.PLAIN, 35 ));
            g.setColor(Color.BLACK);
            g.drawString("Time: " + displayMinutes(time) + ":" + displaySeconds(time),635,770 );
        }
        if(showScoreScreen){
            g.setColor(Color.white);
            g.setFont(new Font("IMPACT", Font.PLAIN, 50));
            g.drawString("Your Score is:" + score, 50,65);
            g.drawString(grid.checkWinLose(score),450,65);
        }
       if(startGame){
           updatePointer();
           if(matchScreen){
               grid.paint(g);
               paintGridContents(g,matchGrid);
           }
           timer += 20;
           if(timer % 1000 == 0){
               time--;
           }
           if(time == 0){
               matchScreen = false;
               blankScreen = true;
               setTime(180);
           }
           if(blankScreen){
               grid.paint(g);
               paintGridContents(g,grid);
           }
       }
       if(showInstructionsScreen){
           setScreen(g,"InstructionsScreen.gif");
           timer += 20;
           if(timer % 1000 == 0){
               time--;
           }
           if(time == 0){
               showInstructionsScreen = false;
               startGame = true;
               setTime(15);
           }
       }
        clickFunctionUpdate(g);
    }
    private void setTime(int n){
        this.time = n;
    }
    public void tickRate() {
        if (frameDelay > 0) {frameDelay-=1;}
        if (!(grid.animationOverride.equals(""))) {
            activeAnimation = grid.animationOverride;
        }
    }
    private void paintGridContents(Graphics g, Grid gridType) {
        for (int r = 0; r < gridType.getLength();r++) {
            for (int c = 0; c < gridType.getHeight(); c++) {
                if (gridType.getValue(r,c)!=null) {
                    gridType.getValue(r,c).paint(g, gridType, c, r);
                }
            }
        }
    }
    private void clickFunctionUpdate(Graphics g) {
        if (getGridLocation(grid)[0] == 1) {
            if (!mouseDown)
            {
                passthrough[0] = 1;
                passthrough[1] = getGridLocation(grid)[1];
                passthrough[2] = getGridLocation(grid)[2];
            } else {
                g.setColor(new Color(150,150,150));
                int[] temp = {1, getGridLocation(grid)[2],getGridLocation(grid)[1]};
                drawPointer(temp,grid,g);
                g.setColor(Color.WHITE);
            }
            int[] temp = {passthrough[0],passthrough[2],passthrough[1]};
            drawPointer(temp, grid, g);
        }
    }
    private void setScreen(Graphics g, String fileName){
        Graphics2D g2 = (Graphics2D) g;
        tx = AffineTransform.getTranslateInstance(0,0);
        Sprite = getImage("Colors//"+ fileName);
        g2.drawImage(Sprite,tx,null);
    }

    private long displayMinutes(long time){
        long amt = this.time / 60;
        return amt;
    }

    private long displaySeconds(long time){
        long amt = this.time / 60;
        long seconds = this.time - (amt * 60);
        return seconds;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        repaint();
    }
    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }
    private void updatePointer()
    {
        PointerInfo p = MouseInfo.getPointerInfo();
        Point point = p.getLocation();
        SwingUtilities.convertPointFromScreen(point, getFocusCycleRootAncestor());
        point.setLocation(point.getX()-8,point.getY()-31);
        mousePosition.setLocation(point.getLocation());
    }
    private int[] getGridLocation(Grid g)
    {
        int[] output = new int[3]; //[0] = on grid; [1] = xpos; [2] = ypos
        if (mousePosition.getX()>g.xOffset && mousePosition.getX()<g.getLength()*g.squareSize+g.xOffset)
        {
            if (mousePosition.getY()>g.yOffset && mousePosition.getY()<g.getHeight()*g.squareSize+g.yOffset)
            {
                output[0] = 1;
                int tempx = (int)(mousePosition.getX());
                int tempy = (int)(mousePosition.getY());
                tempx-=g.xOffset;
                tempy-=g.yOffset;
                output[2] = tempx/g.squareSize;
                output[1] = tempy/g.squareSize;
                return output;
            }
        }
        output[0] = 0;
        return output;
    }
    private int[] translateGridPosition(int[] a,Grid g)
    {
        int[] output = {(a[1]*g.squareSize+g.xOffset),a[2]*g.squareSize+g.yOffset};
        return output;
    }
    Timer t;
    public Runner() {
        JFrame f = new JFrame("PixelMatch");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,825);
        f.add(this);
        f.addMouseListener(this);
        f.addKeyListener(this);
        f.setResizable(false);
        t = new Timer(16, this);
        t.start();
        f.setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        if(blankScreen){
            for(int row = 0; row < grid.getGridRows(); row ++ ){
                for(int col = 0; col < grid.getGridCols(); col ++){
                    Pixel temp = grid.getPixel(row,col);
                    int pixelX = temp.getX();
                    int pixelY = temp.getY();
                    int pixelRX = temp.getrX();
                    int pixelRY = temp.getrY();
                    if(startGame){
                        if((e.getY() >= pixelY && e.getY() <= pixelRY) && (e.getX() >= pixelX && e.getX() <= pixelRX) ){
                            grid.setPixelClicks(row,col,1);
                        }
                    }

                }
            }
        }


        if(showLoadingScreen){
            System.out.println(clickStart);
            if(e.getX() >= 80 && e.getX() <= 450 && e.getY() >= 560 && e.getY() <= 720){
                clickStart++;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    protected Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = Runner.class.getResource(path);
            tempImage    = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {e.printStackTrace();}
        return tempImage;
    }
    private void drawPointer(int[] gridPosition, Grid grid, Graphics g) {
        g.fillRect(translateGridPosition(gridPosition,grid)[0]+2,
                translateGridPosition(gridPosition,grid)[1]+2,20,6);
        g.fillRect(translateGridPosition(gridPosition,grid)[0]+2,
                translateGridPosition(gridPosition,grid)[1]+2,6,20);

        g.fillRect(translateGridPosition(gridPosition,grid)[0]+43,
                translateGridPosition(gridPosition,grid)[1]+2,20,6);
        g.fillRect(translateGridPosition(gridPosition,grid)[0]+58,
                translateGridPosition(gridPosition,grid)[1]+2,6,20);

        g.fillRect(translateGridPosition(gridPosition,grid)[0]+2,
                translateGridPosition(gridPosition,grid)[1]+58,20,6);
        g.fillRect(translateGridPosition(gridPosition,grid)[0]+2,
                translateGridPosition(gridPosition,grid)[1]+43,6,20);

        g.fillRect(translateGridPosition(gridPosition,grid)[0]+43,
                translateGridPosition(gridPosition,grid)[1]+58,20,6);
        g.fillRect(translateGridPosition(gridPosition,grid)[0]+58,
                translateGridPosition(gridPosition,grid)[1]+43,6,20);
    }
}