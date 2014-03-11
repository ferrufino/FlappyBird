/*
 *Class Base
 *
 *@Author Gustavo Ferrufino &&  Andrés Gutiérrez Castaño
 *@Matricula A00812572  && A01191581
 */
package flappybird;

import java.awt.Image;
import java.awt.Toolkit;

public class Bird extends Base {

    private static int conteo;
    private double speedX;
    private double speedY;

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
    public Bird(int posX, int posY, Image image) {
        super(posX, posY, image);
        speedX = 0;
        speedY = 0;
        conteo = 0;

    }

    /**
     * Metodo <I>Pelota</I> metodo <code>getConteo</code> de la clase Pelota
     * regresa el valor de la variable de clse conteo utilizado para el score.
     *
     * @return regresa el valor <I>Entero<I> de la variable de clase conteo.
     */
    public static int getConteo() {
        return conteo;
    }

    /**
     * Metodo <I>Pelota</I> metodo <code>setConteo</code> de la clase Pelota
     * modifica el score de la variable de clase conteo.
     *
     * @paramcont valor de <I>Entero</I> para modificar el valor de la variable
     * de clase conteo.
     */
    public static void setConteo(int cont) {
        conteo = cont;
    }

    /**
     * Metodo <I>getSpeedX</I> de la clase <code>Pelota</code>.
     *
     * @return regresa la velocidad en x del objeto.
     *
     */
    public double getSpeedX() {
        return speedX;
    }

    /**
     * Metodo <I>getSpeedX</I> de la clase <code>Pelota</code>.
     *
     * @return regresa la velocidad en y del objeto.
     *
     */
    public double getSpeedY() {
        return speedY;
    }

    /**
     * Metodo <I>setSpeedX</I> de la clase <code>Pelota</code>, En este metodo
     * se modifica la velocidad en x del objeto pelota.
     *
     * @paramcant tipo de dato <code> double </code> es el valor utilizado para
     * manejar la velocidad en x.
     */
    public void setSpeedX(double cant) {
        speedX = cant;
    }

    /**
     * Metodo <I>setSpeedY</I> de la clase <code>Pelota</code>, En este metodo
     * se modifica la velocidad en y del objeto pelota.
     *
     * @paramcant tipo de dato <code> double </code> es el valor utilizado para
     * manejar la velocidad en y.
     */
    public void setSpeedY(double cant) {
        speedY = cant;
    }
}
