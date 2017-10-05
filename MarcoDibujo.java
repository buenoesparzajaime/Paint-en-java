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
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

//Se crea la clase MarcoDibujo que contendra el lienzo donde se podra dibujar 
public class MarcoDibujo implements ActionListener, ItemListener {
    private PanelDibujo lienzo;
    private JButton bDeshacer;
    private JButton bBorrar;
    private JCheckBox relleno;
    private JComboBox listaFiguras;
    private JComboBox colores; 
    private JFrame ventana;
    private Color color;
    
    
    public MarcoDibujo(){
	    JLabel condicionLabel = new JLabel();
	    JPanel controlPanel = new JPanel();
	    JPanel  principal = new JPanel();
	    JPanel fondo = new JPanel();
	    bDeshacer = new JButton("Deshacer");
	    bDeshacer.addActionListener(this);
            bBorrar = new JButton("Borrar Todo");
            bBorrar.addActionListener(this);
            ventana = new JFrame("Paint en java");
            String[] nombresFiguras = {"Ovalo", "Rectangulo", "Linea"};
            String[] sColores = {"Negro", "Verde", "Rojo","Amarillo","Azul"};
	    listaFiguras = new JComboBox(nombresFiguras);
	    listaFiguras.setMaximumRowCount(3);
	    listaFiguras.addItemListener(this);
            colores = new JComboBox(sColores);
	    colores.setMaximumRowCount(5);
            colores.addItemListener(this);
	    color = Color.BLACK;
	    lienzo = new PanelDibujo(condicionLabel);
		
	    relleno = new JCheckBox("Relleno");
	    relleno.addItemListener(this);

            principal.add(bBorrar);
            principal.add(bDeshacer);
            principal.add(listaFiguras);
            principal.add(colores);
            principal.add(relleno);
		
	    controlPanel.add(principal);
	    controlPanel.add(fondo);
	    ventana.add(controlPanel, BorderLayout.NORTH);
            ventana.add(condicionLabel, BorderLayout.SOUTH);
	    ventana.add(lienzo);
	    ventana.setSize(600, 400);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setVisible(true);
	}

	public static void main (String args[]) {
	    new MarcoDibujo();
	}
	
    @Override
	public void actionPerformed (ActionEvent ae) {
	    if (ae.getSource () == bDeshacer)
		lienzo.deshacer();
	    else if (ae.getSource () == bBorrar){
		lienzo.limpiar();
            }
	}
	
	public void itemStateChanged(ItemEvent ie){
	    if (ie.getSource () == relleno){
		lienzo.setRellenable(relleno.isSelected());
            } 
	    if (ie.getStateChange() == ItemEvent.SELECTED){
		lienzo.setTipoFigura((byte)listaFiguras.getSelectedIndex());  
            }
            if(colores.getSelectedItem().toString().equals("Negro")){
                color = Color.BLACK;
            }else if(colores.getSelectedItem().toString().equals("Verde")){
                color = Color.green;
            }else if(colores.getSelectedItem().toString().equals("Rojo")){
                color = Color.RED;
            }else if(colores.getSelectedItem().toString().equals("Amarillo")){
                color = Color.YELLOW;
            }else if(colores.getSelectedItem().toString().equals("Azul")){
                color = Color.BLUE;
            }
                lienzo.setPintando(color);
	}
}
