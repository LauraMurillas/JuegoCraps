package juegoCraps;

//Este clase es la que me va a decir cual cara del dado es el resultado

import java.util.Random;

/**
Class Dado generate a Random value between one and six.
Autor: Laura Murillas - 201944153
Version: v.1.0.0 date 30/11/2021
 */
public class Dado {
    private int cara;
    
    //Este es el metodo
    //TIP: para crear metodos rapidamente le doy click derecho 
    //Luego le doy click en Generate
    //Me van a salir los metodos principales de las clases
    //Le doy en el metodo Getter (Este metodo retornar los atributos de la clase)
    //Esto crea lo que esta abajo

    /**
    Method that generate an random value to cara
    return: number between (1,6)
     */
    public int getCara() {
        Random aleatorio = new Random();
        cara = aleatorio.nextInt(6)+1; //le sumamos 1 xq el nextInt nos da un numero entre 0 y 5 y el rango de los dados es 1 y 6
        return cara;
    }
}

