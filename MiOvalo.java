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
import java.awt.geom.Ellipse2D;
import java.awt.Stroke;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class MiOvalo extends Figura2D{
    
    public MiOvalo(){
    }
	
	public MiOvalo(int x, int y, boolean estaRelleno, 
                       Paint pintar, Stroke trazo){
            super (x, y, estaRelleno, pintar, trazo);
        }
	
        public MiOvalo(int x1, int y1, int x2, int y2, boolean estaRelleno, 
                       Paint pintar, Stroke trazo){
            super (min (x1, x2), min (y1, y2), max (x1, x2), 
                   max (y1, y2), estaRelleno, pintar, trazo);
        }
	
        public void dibujar(Graphics2D grafico){
            super.dibujar(grafico);
            Ellipse2D elipse = new Ellipse2D.Double (pInicial.x,pInicial.y,
                    pFinal.x -pInicial.x,pFinal.y - pInicial.y);
	    if(estaRelleno())
                grafico.fill (elipse);
	    else
                grafico.draw (elipse);
	}
}
