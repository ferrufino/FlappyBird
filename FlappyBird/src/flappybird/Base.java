/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flappybird;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Ferrufino
 */
public class Base {
    
    private int posX;    //posicion en x.      
    private int posY;   //posicion en y.
    private ImageIcon icono;    //icono.

    /**
     * Metodo <I>Base</I> constructor de la clase <code>Base</code>, En este
     * metodo se construye el objet.
     *
     * @paramposX tipo de dato <code>Entero</code> es el valor utilizado para
     * manejar la posicion en x.
     * @paramposY tipo de dato <code>Entero</code> es el valor utilizado para
     * manejar la posicion en y.
     * @parageimage tipo de dato <code>Imagen</code> es la imagen utilizada para
     * construir el objeto.
     */
    public Base(int posX, int posY, Image image) {
        this.posX = posX;
        this.posY = posY;
        icono = new ImageIcon(image);

    }

    /**
     * Metodo <I>setPosX</I> de la clase <code>Base</code>, En este metodo se
     * construye el objeto.
     *
     * @paramposX tipo de dato <code>Entero</code> es el valor utilizado para
     * manejar la posicion en x.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Metodo <I>setPosy</I> de la clase <code>Base</code>, En este metodo se
     * construye el objeto.
     *
     * @paramposX tipo de dato <code> Entero </code> es el valor utilizado para
     * manejar la posicion en y.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Metodo <I>getPosX</I> de la clase <code>Base</code>.
     *
     * @return regresa la posicion en x del objeto.
     *
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Metodo <I>getPosY</I> de la clase <code>Base</code>,
     *
     * @return regresa el valor de la posicion en y.
     *
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Metodo <I>setImageIcon</I> de la clase <code>Base</code>, En este metodo
     * se modifica la imagen del objeto.
     *
     * @paramsetImageIcon contiene la imagen para modificar.
     */
    public void setImageIcon(ImageIcon icono) {
        this.icono = icono;
    }

    /**
     * Metodo <I>getImageIcon</I> de la clase <code>Base</code>, En este metodo
     * se
     *
     * @return regresa la imagen del objeto
     *
     */
    public ImageIcon getImageIcon() {
        return icono;
    }

    /**
     * Metodo <I>getAncho<I> metodo de acceso que regresa el ancho del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del
     * icono.
     */
    public int getAncho() {
        return icono.getIconWidth();
    }

    /**
     * Metodo <I>getIconHeight<I> metodo de acceso que regresa la altura del
     * icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es la altura del
     * icono.
     */
    public int getAlto() {
        return icono.getIconHeight();
    }

    /**
     * Metodo <I>getImagen</I> metodo de acceso que regresa la imagen del
     * objeto.
     *
     * @return un objeto de la clase <code>Image</code> que es la imagen del
     * icono.
     */
    public Image getImagenI() {
        return icono.getImage();
    }

    /**
     * Metodo <I>getPerimetro</I> metodo de acceso que regresa el rectangulo del
     * objeto.
     *
     * @return un objeto de la clase <code>Rectangle</code> que es el perimetro
     * del rectangulo
     */
    public Rectangle getPerimetro() {
        return new Rectangle(getPosX(), getPosY(), getAncho(), getAlto());
    }

    /**
     * Metodo <I>intersecta2</I> metodo de acceso que regresa un valor booleano
     * si intersecta con otro objeto.
     *
     * @paramobj tipo de dato <code>Base</code> objeto mandado para verificar si
     * intersecta.
     * @return un booleano que determina si los objetos se intersectan.
     */
    public boolean intersecta2(Base obj) {
        return getPerimetro().intersects(obj.getPerimetro());
    }

    /**
     * Metodo <I>intersecta2</I> metodo de acceso que regresa un valor booleano
     * si intersecta con ciertos puntos.
     *
     * @paramposX tipo de dato <code>Entero</code> objeto mandado para verificar
     * si intersecta.
     * @paramposY tipo de dato <code>Entero</code> objeto mandado para verificar
     * si intersecta.
     * @return un booleano que determina si los objetos se intersectan.
     */
    public boolean intersectaPuntos(int posX, int posY) {
        return getPerimetro().contains(posX, posY);
    }

    /**
     * Metodo <I>intersecta</I> metodo de acceso que regresa un valor booleano
     * si intersecta con cierto objeto.
     *
     * @obj tipo de dato <code>Entero</code> objeto mandado para verificar si
     * intersecta.
     * @return un booleano que determina si los objetos se intersectan.
     */
    public boolean intersecta(Base obj) {
        // return getPerimRec().intersects(obj.getPerimetro());
        return getPerimetro().intersects(obj.getPerimetro());
    }
    
}
