/*
 * Istituto Tecnologico de León
 * Ingenieria en Sistemas Computacionales.
 * Alumno: Bueno Esparza Jaime
 * Profesor: Levy Rojas Carlos Rafael
 * Este programa nos permite dibuja en un panel varias figuras basicas, que 
 * son ovalos, rectangulos y lineas, por medio del escucha del mouse del cual 
 * obtenemos dos puntos iniciales y dos puntos finales, del mismo modo los
 * ovalos y rectangulos tienen la opcion de contar co un relleno en color.
 */
package PaintEnJava;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.geom.Point2D;
import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.io.IOException;

public class MiFigura implements Externalizable{
    protected Point pInicial;
    protected Point pFinal;
    private Paint pintar;
    private Stroke trazo;
    
    public MiFigura(){
    }
    
    public MiFigura (int x1, int y1, int x2, int y2, Paint pintar, Stroke trazo){
	pInicial = new Point(x1, y1);
	pFinal = new Point(x2, y2);
        setPintar(pintar);
        setTrazo(trazo);
    }
          
    public final void setTrazo (Stroke newTrazo) {
	trazo = newTrazo == null ? new BasicStroke (2.0f) : newTrazo;
    }
	
    public final Stroke getTrazo() {
	return trazo;
    }
	
    public final void setPintar(Paint newPintar){
	pintar = newPintar;
    }
	
    public final Paint getPintar(){ 
        return pintar;
    }
	
    public final void setPuntoFinal(int x, int y){
        pFinal.x = x < 0 ? 0 : x;
        pFinal.y = y < 0 ? 0 : y;
    }
	
    public void dibujar(Graphics2D g2d) {
        g2d.setStroke(trazo);
        g2d.setPaint(getPintar ());
    }
	
    public void writeExternal(final ObjectOutput ou) throws IOException {
        ou.writeObject(pInicial);
	ou.writeObject(pFinal);
	ou.writeBoolean(pintar instanceof Color);
		
	if (pintar instanceof Color)
	    ou.writeObject((Color) pintar);
        else if(pintar instanceof GradientPaint){
	    GradientPaint gp = (GradientPaint) pintar;
			
	    ou.writeObject(gp.getPoint1 ());
	    ou.writeObject(gp.getColor1 ());
            ou.writeObject(gp.getPoint2 ());
            ou.writeObject(gp.getColor2 ());
            ou.writeBoolean(gp.isCyclic ());
        } else
            System.err.println ("No se puede pintar el objeto que escogio");
		
        if (trazo instanceof BasicStroke) {
            BasicStroke bs = (BasicStroke) trazo;
			
            ou.writeBoolean(bs.getDashArray() == null);
            ou.writeFloat(bs.getLineWidth());
            ou.writeInt(bs.getEndCap());
            ou.writeInt(bs.getLineJoin());
			
            if (bs.getDashArray() != null) {
                ou.writeFloat(bs.getMiterLimit());
                ou.writeObject(bs.getDashArray());
                ou.writeFloat(bs.getDashPhase());
             	}
            } else
                System.err.println("No se pudo hacer el trazo");
    }
        
    public void readExternal(final ObjectInput oi) throws IOException, ClassNotFoundException{
        pInicial = (Point) oi.readObject();
        pFinal = (Point) oi.readObject();
		
        if (oi.readBoolean()){
           setPintar((Color) oi.readObject());
        }else{
            setPintar(new GradientPaint((Point2D) oi.readObject(),
                    (Color) oi.readObject(),
                    (Point2D) oi.readObject(),
                    (Color) oi.readObject(),
                    oi.readBoolean()));
            if (oi.readBoolean())
                setTrazo(new BasicStroke(oi.readFloat(),
                        oi.readInt(),
                        oi.readInt()));
            else
                setTrazo(new BasicStroke (oi.readFloat(),
                    oi.readInt(),
                    oi.readInt(),
                    oi.readFloat(),
                    (float[])oi.readObject(),
                    oi.readFloat()));
        }
    }
}
