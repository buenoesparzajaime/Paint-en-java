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
import java.awt.Paint;
import java.awt.Stroke;
import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.io.IOException;

public class Figura2D extends MiFigura{
    private boolean estaRelleno;
	
    public Figura2D () {
    }
	
    public  Figura2D(int x1, int y1, int x2, int y2, boolean estaRelleno, 
                     Paint pintar, Stroke trazo){
        super (x1, y1, x2, y2, pintar, trazo);
        setRellenable (estaRelleno);
    }
	
    public Figura2D(int x, int y, boolean estaRelleno, Paint pintar, 
                    Stroke trazo){
        super (x, y, 0, 0, pintar, trazo);
        setRellenable (estaRelleno);
    }
	
    public final void setRellenable(boolean valor){
        estaRelleno = valor;
    }
	
    public final boolean estaRelleno(){
        return estaRelleno;
    }
	
    @Override
    public void writeExternal(final ObjectOutput ou)throws IOException{
        super.writeExternal(ou);
        ou.writeBoolean(estaRelleno ());
    }
	
    @Override
    public void readExternal(final ObjectInput oi)throws IOException, 
                             ClassNotFoundException{
        super.readExternal(oi);
        setRellenable(oi.readBoolean());
    }
}
