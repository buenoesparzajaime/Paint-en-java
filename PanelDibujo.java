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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelDibujo extends JPanel{
    public static final byte MIOVALO = 0;
    public static final byte MIRECTANGULO = 1;
    public static final byte MILINEA = 2;

    private MiFigura[] figuras;
    private MiFigura figuraActual;
    private Stroke trazoActual;
    private short numeroDeFiguras;
    private byte tipoDeFigura;
    private Paint pintando;
    private boolean relleno = false;
    private JLabel condicionLabel;
        
    public PanelDibujo(JLabel condicion){
	condicionLabel = condicion;
        figuras = new MiFigura[100];
        numeroDeFiguras = 0;
        tipoDeFigura =  MIOVALO;
        pintando= Color.BLACK;
        setBackground (Color.WHITE);
        MouseHandler mh = new MouseHandler();
        addMouseListener(mh);
        addMouseMotionListener(mh);
        }
        
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D grafico = (Graphics2D) g;
        if(figuraActual != null)
	    figuraActual.dibujar(grafico);
        for(byte contador = 0; figuras[contador] != null &&
            contador < figuras.length; ++contador)
            figuras[contador].dibujar(grafico);
    }
	
    public void setTipoFigura(byte newTipoFigura){
        tipoDeFigura = newTipoFigura >= 0 && 
            newTipoFigura < 3 ? newTipoFigura : 0;
    }
	
    public void setPintando (Paint newPintando){
        pintando = newPintando;
    }
	
    public void setTrazoActual(Stroke newTrazo){
        trazoActual = newTrazo;
    }
	
    public void setRellenable(boolean valor){
        relleno= valor;
    }
	
    public void deshacer(){
        if (--numeroDeFiguras < 0)
	    numeroDeFiguras = 0;
	figuras[numeroDeFiguras] = null;
            repaint ();
    }
	
    public void limpiar(){
        numeroDeFiguras = 0;
        figuraActual = null;
        for (short contador = 0; figuras[contador] != null && 
            contador < figuras.length; ++contador)
	    figuras[contador] = null;
            repaint();
    }
        
private class MouseHandler extends MouseAdapter implements MouseMotionListener{
    public void mousePressed(MouseEvent me){
        switch (tipoDeFigura){
            case MIOVALO:
	        figuraActual = new MiOvalo(me.getX (), me.getY (), relleno, pintando, trazoActual);
	            break;
            case MIRECTANGULO:
                figuraActual = new MiRectangulo(me.getX (), me.getY (), relleno, pintando, trazoActual);
                    break;
            case MILINEA:
	        figuraActual = new MiLinea(me.getX(), me.getY(), pintando, trazoActual);
	        }
    }
		
    public void mouseReleased(MouseEvent me){
        refreshCurrentShape (me.getX (), me.getY ());
        figuras[numeroDeFiguras++] = figuraActual;
        figuraActual = null;
        repaint();
    }
		
    public void mouseMoved(MouseEvent me){
        refreshStateLabel (me.getX (), me.getY ());
        }
		
    public void mouseDragged(MouseEvent me){
        refreshStateLabel (me.getX (), me.getY ());
        refreshCurrentShape (me.getX (), me.getY ());
            repaint();
    }
		
    private void refreshStateLabel(int x, int y){
        condicionLabel.setText(String.format ("(%d, %d)", x, y));
    }
		
    private void refreshCurrentShape(int x, int y) {
        figuraActual.setPuntoFinal(x, y);
    }
    }
}
