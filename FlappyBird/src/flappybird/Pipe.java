/*
 *Class Base
 *
 *@Author Gustavo Ferrufino &&  Andrés Gutiérrez Castaño
 *@Matricula A00812572  && A01191581
 */
package flappybird;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Metodo <I>Pelota</I> constructor de la clase <code>Pelota</code>, En este
 * metodo se construye el objeto.
 *
 * @paramposX tipo de dato <code>Entero</code> es el valor utilizado para
 * manejar la posicion en x.
 * @paramposY tipo de dato <code>Entero</code> es el valor utilizado para
 * manejar la posicion en y.
 * @parageimage tipo de dato <code>Imagen</code> es la imagen utilizada para
 * construir el objeto.
 */
public class Pipe extends Base {

    private int speed;

    public Pipe(int posX, int posY, Image image) {
        super(posX, posY, image);

        speed = 5;

    }
    //Variables Strings
    private static final String PAUSADO = "PAUSADO";
    private static final String DESAPARECE = "DESAPARECE";

    /**
     * Metodo <I>getPausado</I> de la clase <code>Bloque</code>.
     *
     * @return regresa el valor de la variable PAUSADO
     *
     */
    public static String getPausado() {
        return PAUSADO;
    }

    /**
     * Metodo <I>getGone</I> de la clase <code>Bloque</code>.
     *
     * @return regresa el valor de la variable DESAPARECE.
     *
     */
    public static String getGone() {
        return DESAPARECE;
    }

    /**
     * Metodo <I>getSpeed</I> de la clase <code>Bloque</code>.
     *
     * @return regresa el valor del atributo speed.
     *
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Metodo <I>setSpeed</I> metodo de la clase  <code>Bloque</code> modifica el
     * score de la variable speed
     *
     * @paramcant valor de <I>Entero</I> para modificar el valor de la variable
     * speed.
     */
    public void setSpeed(int cant) {
        speed = cant;
    }

}
