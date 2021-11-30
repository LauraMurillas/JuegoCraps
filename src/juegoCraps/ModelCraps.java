package juegoCraps;

//Esta clase pone todas las reglas del juego

/**
 * ModelCraps apply craps rules
 * estado 1=  natural winner
 * estado 2= craps looser
 * estado 3= Establish Punto
 * estado 4= Punto winner
 * estado 5= punto looser
 * Autor: Laura Murillas
 * Version: v.1.0.0 Date: 30/11/2021
 */
public class ModelCraps {

    private Dado dado1, dado2;

    private int tiro, punto, estado, flag;
    //flag es como un contador para saber donde estoy en el codigo

    //Esta variable retorna el mensaje en pantalla
    private String estadoToString;

    //Esta es la sintaxis para decir que ese objeto es de tipo arreglo o vector
    private int[] caras;


    /**
     * Class Constructor
     */
    //Este es el contructor. Establece los valores por defectos que quiero que tengan los atributos.
    //Crea las instancias y los datos de los atributos
    public ModelCraps(){
        dado1 = new Dado();
        dado2 = new Dado();
        flag=0;

        //Esto dice "El objeto caras es un arreglo de tipo entero de 2 elementos
        caras = new int[2];
    }

    /**
     * Establish the tiro value according to each dado
     */
    //Esta clase calcula y almacena el valor de los dados

    public void calcularTiro(){
        caras[0]=dado1.getCara();
        caras[1]=dado2.getCara();
        tiro = caras[0] + caras[1] ;
    }


    /**
     * Establish game state according to Estado atribute value. If is 1,2,3.
     */
    public void determinarJuego() {
        //el flag nos ayuda a saber si es el tiro de salida o si esta en ronda de puntos
        if (flag == 0) {
            if (tiro == 7 || tiro == 11) {
                estado = 1;
                //Gano con natural
            } else {
                if (tiro == 2 || tiro == 3 || tiro == 12) {
                    estado = 2;
                    //Perdio con craps
                } else {
                    estado = 3;
                    punto = tiro;
                    //Se le asigna el puntaje de su tiro
                    flag=1;
                }
            }
        }else{
            //Esta es la ronda de puntos
            rondaPunto();
        }
    }

    /**
     * Establish game state according to state  atribute value (If is 4 or 5)
     */
    //Es  un metodo
    private void rondaPunto() {
        //estas son las reglas del juego para la ronda punto
        if(tiro==punto){
            estado=4;
            flag=0;

        }
        if(tiro==7){
            estado=5;
            flag=0;
        }
    }


    //Generamos estos metodos por el TIP anterior (Getter)

    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    /**
     * Establish message gome state according to estado atribute value
     * @return message for the view class
     */
    public String getEstadoToString() {
        //El switch es un condicional, es una estructura que funciona con enteros y que verifica varios if
        // Case 1 significa que "en caso de que el entero que se introduce es 1"
        //El break siempre debe ir porque sino ejecuta todo de corrido

        switch (estado){
            case 1: estadoToString="Sacaste Natural ¡GANASTE!";
                break;

            case 2: estadoToString="Sacaste Craps, ¡PERDISTE!";
                break;

            case 3: estadoToString="Estableciste "+punto+"" +
                                    "¡Debes seguir lanzando!"+
                                    "\n pero si sacas 7 antes que"+punto+" vas a perder :(";
                break;

            case 4: estadoToString="volviste a sacar" +punto+", ¡GANASTE!";
                break;

            case 5: estadoToString="Sacaste 7 antes que "+punto+", ¡PERDISTE!";
                break;


        }
        return estadoToString;
    }

    public int[] getCaras() {
        return caras;
    }
}
