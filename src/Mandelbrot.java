import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Mandelbrot extends Frame {

    private static int WIDTH = 900;
    private static int HEIGHT = 600;

    private double clickX = 1.5;
    private double clickY = 1.0;
    private double K=0.5;
    private double scalingFactor = 1.0 / 300;

//    private int color[] = new int[MAXCOLOR];

    private void init(){
        this.setName("Julia");
        this.setSize(WIDTH,HEIGHT);
        this.setLocation(1366/2 - WIDTH/2,50);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON1){
                    double tx,ty;
                    tx = e.getX()-((WIDTH*K)/2);
                    ty = e.getY()-((HEIGHT*K)/2);

                    tx*=scalingFactor;
                    ty*=scalingFactor;
                    scalingFactor*=K;

                    clickX-=tx;
                    clickY-=ty;
                    repaint();
                }
                if(e.getButton()==MouseEvent.BUTTON3){
                    double tx,ty;

                    tx = e.getX()-((WIDTH/K)/2);
                    ty = e.getY()-((HEIGHT/K)/2);

                    tx*=scalingFactor;
                    ty*=scalingFactor;
                    scalingFactor/=K;

                    clickX-=tx;
                    clickY-=ty;
                    repaint();
                }
            }
        });
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {

        for(double x=0;x<WIDTH;x++){
            for(double y=0;y<HEIGHT;y++){
                mandelbrot(g,x,y);
            }
        }
    }
    private void mandelbrot(Graphics g, double x, double y){
        double a=0,b=0,c,d,n=0;
        int count = 880;
        while ((c=a*a)+(d=b*b)<4&&n++< count){
            b=2*a*b+(y* scalingFactor -clickY);a=c-d+(x* scalingFactor -clickX);
        }
        g.setColor(new Color((int)(255*Math.pow(n/880,3.)),(int)(255*Math.pow(n/880,.7)),(int)(255*Math.pow(n/880,.5))));
        g.drawLine((int)x,(int)y,(int)x,(int)y);
    }
    public static void main(String[] args) {
        new Mandelbrot().init();
    }

}
