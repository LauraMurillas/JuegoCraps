package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.StringWriter;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    private Header headerProject;
    private javax.swing.JPanel JPanel;
    private javax.swing.JPanel panelMouse;


    /**
     * Constructor of GUI class
     * @param panelDatos
     */
    public GUI(javax.swing.JPanel panelDatos){
        panelDatos = new JPanel();
        initGUI();


        //Default JFrame configuration
        this.setTitle("The Title app");
        this.setSize(200,100);
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
        //Create Listener Object and Control Object
        //Set up JComponents
        headerProject = new Header("Header ...", Color.BLACK);

        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout
        panelMouse = new JPanel();
        panelMouse.addMouseListener(escucha);
        panelMouse.addMouseMotionListener(escucha);
        panelMouse.setFocusable(true);
        panelMouse.setBackground(Color.BLUE);
        panelMouse.setPreferredSize(new Dimension(300, 250));

    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI myProjectGUI = new GUI(panelDatos);
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class escucha implements MouseListener {

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
    }
}
