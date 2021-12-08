package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.io.StringWriter;

/**
 * This class is used as a view craps class.
 * @autor Laura Murillas, laura.murillas@correounivalle.edu.co
 * @version v.1.0.0 date:06/12/2021
 */
public class GUI extends JFrame {

    //Esta es la sintaxis de una constante, se usa el 'final'
    private static final String MENSAJE_INICIO= "Bienvenido a Craps \n"
                + "Oprime el botón lanzar para iniciar el juego"
                + "\nSi tu tiro de salida es 7 u 11 ganas con Natural"
                + "\nSi tu tiro de salida es 2, 3 o 12 pierdes con Craps"
                + "\nSi sacas cualquier otro valor establecerás el Punto"
                + "\nSi estas en Punto podrás seguir lanzando los dados"
                + "\npero entonces ganarás si sacas nuevamente el valor que sacaste antes"
                + "\nSin que previamente hayas sacado 7";


    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private JTextArea mensajesSalida, resultadosDados;
    private JSeparator separador; //Este es el separador entre los dos paneles de texto


    private javax.swing.JPanel JPanel;
    private javax.swing.JPanel panelMouse;

    private Escucha escucha;
    private ModelCraps modelCraps; //La vista (el GUI) utiliza un objeto de tipo modelcraps



    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();


        //Default JFrame configuration
        this.setTitle("Juego Craps by Laura");
        this.setSize(1200,690);
        //this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object

        escucha = new Escucha();
        modelCraps = new ModelCraps();

        //Set up JComponents
        headerProject = new Header("Mesa del juego Craps Por Laura :D", Color.BLACK);

        this.add(headerProject,BorderLayout.NORTH); //Este es el titulo de la ventana

        imageDado =  new ImageIcon(getClass().getResource("/recursos/dado.png")); //Es un puntero a nivel de src, o sea, entra a cualquier archivo
        dado1 =  new JLabel(imageDado);
        dado2 = new JLabel(imageDado);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300, 280)); //Esto define el tamaño al panel
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));

        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);

        this.add(panelDados,BorderLayout.CENTER); //Esto hace que se vea lo que agregamos a la pantalla


        mensajesSalida = new JTextArea(7, 31);
        mensajesSalida.setText(MENSAJE_INICIO);
        //mensajesSalida.setBorder(BorderFactory.createTitledBorder("Qué debes hacer..."));
        JScrollPane scroll = new JScrollPane(mensajesSalida); //hace que se vea el texto

        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Qué debes hacer..."));
        panelResultados.add(scroll);
        panelResultados.setPreferredSize(new Dimension(400, 100));

        this.add(panelResultados,BorderLayout.EAST);

        resultadosDados = new JTextArea(10,50);

        separador = new JSeparator();
        separador.setPreferredSize(new Dimension(420, 7));
        separador.setBackground(Color.pink);

        //panelMouse = new JPanel();
        //panelMouse.addMouseListener(escucha);
        //panelMouse.addMouseMotionListener(escucha);
        //panelMouse.setFocusable(true);
        //panelMouse.setBackground(Color.BLUE);
        //panelMouse.setPreferredSize(new Dimension(300, 250));

    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }


    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){

            modelCraps.calcularTiro();
            int [] caras = modelCraps.getCaras();
            imageDado =  new ImageIcon(getClass().getResource("/recursos/"+caras[0]+".png")); //Es un puntero a nivel de src, o sea, entra a cualquier archivo
            dado1.setIcon(imageDado);

            imageDado =  new ImageIcon(getClass().getResource("/recursos/"+caras[1]+".png")); //Es un puntero a nivel de src, o sea, entra a cualquier archivo
            dado2.setIcon(imageDado);

            modelCraps.determinarJuego();

            panelResultados.removeAll();
            panelResultados.setBorder(BorderFactory.createTitledBorder("RESULTADOS"));
            panelResultados.setPreferredSize(new Dimension(200, 100));
            panelResultados.add(resultadosDados);
            panelResultados.add(separador);
            panelResultados.add(mensajesSalida);
            resultadosDados.setText(modelCraps.getEstadoToString()[0]);
            mensajesSalida.setRows(4); //Esto achica la caja de texto inicial
            mensajesSalida.setText(modelCraps.getEstadoToString()[1]);


            revalidate();
            repaint();

            //mensajesSalida.setText(modelCraps.getEstadoToString()); //Trae le resultado que dio



        }


        /**
        @Override
        public void mouseClicked(MouseEvent e) {
            panelMouse.setBackground(Color.CYAN);
            StringWriter mensajes;
            mensajes.append("mouseClicked was detected");
            if(e.getButton()==MouseEvent.BUTTON3){
                JOptionPane.showMessageDialog(null,"Click derecho mouse");
            }else{

            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            panelMouse.setBackground(Color.BLACK);

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
        */
    }

}
