package LayerPaint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class MyLine implements Drawable {
    
    private static final double HIT_BOX_SIZE = 10;
    
    public boolean selected = false;
    private double x1, y1, x2, y2;
    private Color color = null;
    private Stroke stroke;
    

    public MyLine() {
    }

    public MyLine(double x1, double y1, double x2, double y2, Stroke stroke) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.stroke = stroke;
    }
    
    public Stroke stroke(){
        return stroke;
    }

    public MyLine(Tuple4d location){
        x1 = location.x;
        y1 = location.y;
        x2 = location.z;
        y2 = location.w;
    }
    @Override
    public boolean equals(Object other){
        return other instanceof MyLine;
    }

    @Override
    public void draw(Graphics2D g) {
            Line2D r = new Line2D.Double(x1, y1, x2, y2);
            g.setStroke(stroke);
            g.draw(r);
            if(selected){
                Rectangle2D s = new Rectangle2D.Double(x1, y1, x2-x1, y2-y1);
                g.setStroke(dashed);
                g.setColor(Color.BLACK);
                g.draw(s);
            }
    }

    public Tuple4d getCoords() {
        return new Tuple4d(x1, y1, x2, y2);
    }
    
    public void setCoords( Tuple4d tuple){
        x1 = tuple.x;
        y1 = tuple.y;
        x2 = tuple.z;
        y2 = tuple.w;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    @Override
    public boolean contains(double x, double y) {
        int boxX = (int) (x - (HIT_BOX_SIZE / 2));
        int boxY = (int) (y - (HIT_BOX_SIZE) / 2);
        int width = (int) HIT_BOX_SIZE;
        int height = (int) HIT_BOX_SIZE;
        Rectangle2D b = new Rectangle2D.Double(x, y, width, height);
        System.out.println(b);
        Line2D r = new Line2D.Double(x1, y1, x2, y2);
        System.out.println(r);
        return r.intersects(b);
    }

    @Override
    public void select(boolean select) {
        selected = select;
    }
}
