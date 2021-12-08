package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUIGridBagLayout extends JFrame {


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
    private JButton lanzar, ayuda, salir;
    private JPanel panelDados;
    private ImageIcon imageDado;
    private JTextArea mensajesSalida, resultadosDados;
    private Escucha escucha;
    private ModelCraps modelCraps; //La vista (el GUI) utiliza un objeto de tipo modelcraps

    public GUIGridBagLayout(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Juego Craps by Laura");
        this.setUndecorated(true); //esto el marco de la ventana, como el boton de minimizar y etc
        this.setSize(1200,690);
        //this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initGUI() {

        //Set up JFrame Container's Layout - utilizo el gridbaglayout AQUI
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();


        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();

        //Set up JComponents
        headerProject = new Header("Mesa del juego Craps Por Laura :D", Color.BLACK);

        //configuracion del GridBagLayout
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints); //Este es el titulo de la ventana

        //Boton AYUDA
        ayuda = new JButton("  ?  ");
        ayuda.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        //BOTON SALIR
        salir = new JButton("  EXIT  ");
        salir.addActionListener(escucha);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir,constraints);


        //Panel de los dados
        imageDado =  new ImageIcon(getClass().getResource("/recursos/dado.png")); //Es un puntero a nivel de src, o sea, entra a cualquier archivo
        dado1 =  new JLabel(imageDado);
        dado2 = new JLabel(imageDado);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(600, 280)); //Esto define el tamaño al panel
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));

        panelDados.add(dado1);
        panelDados.add(dado2);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(panelDados,constraints);

        //Panel de los resultados
        resultadosDados = new JTextArea(10,50);
        resultadosDados.setBorder(BorderFactory.createTitledBorder("RESULTADOS"));
        resultadosDados.setText("Debes lanzar los dados");
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(resultadosDados,constraints);


        //Boton LANZAR
        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(lanzar,constraints);

        //Panel del mensaje salida
        mensajesSalida = new JTextArea(7, 31);
        mensajesSalida.setText("Usa el botón ayuda (?) para leer las reglas");
        mensajesSalida.setBorder(BorderFactory.createTitledBorder("Mensajes"));
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(mensajesSalida,constraints);


    }


    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }



    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){

            if(e.getSource()==lanzar){

                modelCraps.calcularTiro();
                int [] caras = modelCraps.getCaras();
                imageDado =  new ImageIcon(getClass().getResource("/recursos/"+caras[0]+".png")); //Es un puntero a nivel de src, o sea, entra a cualquier archivo
                dado1.setIcon(imageDado);

                imageDado =  new ImageIcon(getClass().getResource("/recursos/"+caras[1]+".png")); //Es un puntero a nivel de src, o sea, entra a cualquier archivo
                dado2.setIcon(imageDado);

                modelCraps.determinarJuego();


                resultadosDados.setText(modelCraps.getEstadoToString()[0]);
                mensajesSalida.setText(modelCraps.getEstadoToString()[1]);//Trae le resultado que dio

            }else{
                if(e.getSource()==ayuda){
                    JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
                }else{
                    System.exit(0); //Esto es lo que cierra la ventana
                }
            }


        }
    }

}
