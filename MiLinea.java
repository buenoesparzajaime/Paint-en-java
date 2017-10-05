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
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Line2D;
import java.awt.Stroke;

public class MiLinea extends MiFigura{
    public MiLinea(){
    }

    public MiLinea(int x, int y, Paint pintar, Stroke trazo){
        super(x, y, 0, 0, pintar, trazo);
    }
	
    public MiLinea(int x1, int y1, int x2, int y2, Paint pintar, Stroke trazo){
        super (x1, y1, x2, y2, pintar, trazo);
    }
	
    public void dibujar(Graphics2D grafico){
        super.dibujar(grafico);
        grafico.draw(new Line2D.Double (pInicial.x, pInicial.y, pFinal.x, pFinal.y));
    }
}
