/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flappybird;
import java.awt.Rectangle;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.LinkedList;
import java.awt.Point;
import javax.swing.JFrame;// 
/**
 *
 * @author Ferrufino
 */
public class Playground extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener {
    
    private static final long serialVersionUID = 1L;
    // Se declaran las variables objetos. 
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private SoundClip fail;    // Objeto AudioClip
    private SoundClip collide;    //Objeto AudioClip 
    private Bloque fireBasket;    // Objeto de la clase Elefante
    private Pelota basketBall;   //Objeto de la clase Raton
    private Rectangle box;

    //variables auxiliares para cargar
    private String[] arr;   //arreglo para grabar
    private String nombreArchivo; //nombre del archivo que se utiliza para cargar/guardar
    private int dir;    //variable auxiliar de direccion del cesto
    private int sco;    //variable auxiliar del score
    private int bbposx; //variable auxiliar de la posicion en x de la pelota
    private int bbposy; //variable auxiliar de la posicion en y de la pelota
    private int fbposx; //variable auxiliar de la posicion en x del cesto
    private int fbposy; //variable axuliar de la posicion en y del cesto
    private double bbspeedx;    //variable auxiliar de la velocidad en x de la pelota
    private double bbspeedy;    //variable auxiliar de la velocidad en y de la pelota
    private double t;   //variable auxiliar para guardar el tiempo
    private int v;  //variable auxiliar para guardar las vidas
    private int cp; //variable auxiliar para guardar cantidad de veces que cae al piso
    private boolean cargar; //banderas de control
    private boolean grabar;
    private int gravedad;   //gravedad utilizada para el tiro parabolico
    private int timeRetard;    //Contador para retrazar aparicion de DESAPARECE
    private boolean boxClicked; //variable de control para saber si se dio click en la posicion inicial de la pelota
    private int contPerdidas;   //cuenta cuantas veces cae al piso la pelota si llega a 3 se resta una vida
    private int difVel; //aumento de la velocidad
    private int vidas;  //vidas del juego
    private double time;    //tiempo utilizado para la trayectoria de la parabola

    //imagenes
    private Image gameover; //imagen de gameover
    private Image background;
    private Image basketBall1;
    private Image fireBasket1;
    private Image chocan;
    private String soundOn;
    private String soundOff;
    private double velXI;
    private double velYI;
    private int speed;
    private int teclaPresionada;
    private int posX;
    private int posY;
    private int score;
    private int POINTS;
    private int xMayor;
    private int xMenor;
    private int yMayor;
    private int yMenor;
    private boolean pause; //Permite al usuario pausar el juego
    private boolean action;
    private boolean soundsOn;
    private long tiempoActual;
    private long tiempoInicial;
    private boolean BEGIN;
    private boolean ballClicked;
    private boolean instrucciones;
    private int g;
    private String bclicked;
    private double vyi;
    private double vxi;

    /**
     * Metodo <I>PlayGround()</I> de la clase <code>PlayGround</code>. Es el
     * constructor de la clase donde se definen las variables
     */
    public PlayGround() {

        setSize(800, 600);
        ballClicked = false;
        BEGIN = true;
        pause = false;
        action = false;
        instrucciones = false;
        timeRetard = 0;
        teclaPresionada = 0;
        contPerdidas = 0;
        difVel = 0;
        soundOn = "On";
        soundOff = "Off";
        soundsOn = true;

        nombreArchivo = "Archivo.txt";

        gravedad = 6;
        time = 0;
        score = 0;                    //puntaje inicial
        vidas = 5;                    //vida inicial
        xMayor = (getWidth() - getWidth() / 10);           //posicion máxima en x que tendrán el basketBall
        xMenor = 0;           //posicion mínima en x que tendrá el basketBall
        yMayor = (getHeight() - (getHeight() / 10));          //posicion máxima en y que tendrán el basketBall
        yMenor = 10;        //posicion mínima en y que tendrá el basketBall
        velXI = 0;
        velYI = 0;
        //Se cargan los sonidos.

        collide = new SoundClip("sounds/applause.wav");
        fail = new SoundClip("sounds/fail-buzzer-03.wav");

        posX = 100;     //se generarán los basketBalls en posiciones aleatorias fuera del applet
        posY = 100;

        URL fbURL = this.getClass().getResource("images/fireBasket.gif");
        fireBasket = new Bloque((getWidth() - 150), (getHeight() - 130), Toolkit.getDefaultToolkit().getImage(fbURL));

        URL bbURL = this.getClass().getResource("images/basketBall.gif");
        basketBall = new Pelota(50, 250, Toolkit.getDefaultToolkit().getImage(bbURL));

        box = new Rectangle(50, 250, basketBall.getAncho(), basketBall.getAlto());
        URL xuURL = this.getClass().getResource("images/gOVER.png");
        gameover = Toolkit.getDefaultToolkit().getImage(xuURL);

        URL bgURL = this.getClass().getResource("images/background3.jpg");
        background = Toolkit.getDefaultToolkit().getImage(bgURL);

        URL bbalURL = this.getClass().getResource("images/basketBall1.png");
        basketBall1 = Toolkit.getDefaultToolkit().getImage(bbalURL);

        URL fbasURL = this.getClass().getResource("images/fireBasket1.png");
        fireBasket1 = Toolkit.getDefaultToolkit().getImage(fbasURL);

        URL cHURL = this.getClass().getResource("images/boom.png");
        chocan = Toolkit.getDefaultToolkit().getImage(cHURL);

        //Inicializadores 
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        // Declaras un hilo
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();

    }

    /**
     * Metodo <I>run</I> sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, es un ciclo indefinido donde se
     * incrementa la posicion en x o y dependiendo de la direccion, finalmente
     * se repinta el <code>Applet</code> y luego manda a dormir el hilo.
     *
     */
    public void run() {
        tiempoActual = System.currentTimeMillis();
        while (vidas > 0) {
            if (!pause) {
                Actualiza();
                ChecaColision();
            }
            repaint();    // Se Actualiza el <code>Applet</code> repintando el POINTSenido.
            try {
                // El thread se duerme.
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }
    }

    /**
     * Metodo usado para Actualizar la posicion de objetos fireBasket y
     * basketBall.
     *
     */
    public void Actualiza() {
        //si la bandera de grabar esta en true se graba el juego en el archivo 
        if (grabar) {
            try {

                grabaArchivo();    //Graba el vector en el archivo.
            } catch (IOException e) {
                System.out.println("Error en " + e.toString());
            }
            grabar = false;
        }
        //si la bandera de cargar esta en true se carga del archivo al juego y se asignan los valores

        if (cargar) {
            try {

                leeArchivo();    //Graba el vector en el archivo.
            } catch (IOException e) {
                //System.out.println("Error en " + e.toString());
            }
            basketBall.setConteo(sco);
            teclaPresionada = dir;
            basketBall.setPosX(bbposx);
            basketBall.setPosY(bbposy);
            fireBasket.setPosX(fbposx);
            fireBasket.setPosY(fbposy);
            basketBall.setSpeedX(bbspeedx);
            basketBall.setSpeedY(bbspeedy);

            time = t;
            cargar = false;
            vidas = v;
            contPerdidas = cp;
            gravedad = g;
            boxClicked = true;
            velXI = vxi;
            velYI = vyi;

        }

        if (contPerdidas == 3) {
            contPerdidas = 0;

            if (gravedad < 17) {
                gravedad += 7;
                difVel += 2;
            } else if (gravedad <= 24) {
                gravedad += 16;
                difVel += 2;
            } else {
                gravedad += 19;

            }

            vidas--;
        }
        //si el rectangulo de la posicion inicial fue presionada se inicializa el tiempo
        if (boxClicked) {

            time += 0.020;
            basketBall.setSpeedX(velXI);
            basketBall.setSpeedY((velYI * -1) + gravedad * time);
            basketBall.setPosX(basketBall.getPosX() + (int) (basketBall.getSpeedX()));
            basketBall.setPosY(basketBall.getPosY() + (int) (basketBall.getSpeedY()));

        }

        if (action) {
            switch (teclaPresionada) {
                case 1: {

                    fireBasket.setPosX(fireBasket.getPosX() - 4);
                    break; //se mueve hacia la izquierda
                }
                case 2: {

                    fireBasket.setPosX(fireBasket.getPosX() + 4);
                    break; //se mueve hacia la derecha
                }

            }
        }

        if (basketBall.getPosY() < getHeight()) {
            basketBall.setPosX((int) (basketBall.getPosX() + basketBall.getSpeedX()));
        }

    }

    /**
     * Metodo usado para checar las colisiones del objeto elefante y basketBall
     * con las orillas del <code>Applet</code>.
     */
    public void ChecaColision() {

        //checa colision con el applet
        if (fireBasket.getPosY() < 0) {              //choca borde de arriba
            fireBasket.setPosY(0);
        }

        if (fireBasket.getPosY() + fireBasket.getAlto() > getHeight()) {       //si se pasa del borde de abajo
            fireBasket.setPosY(getHeight() - fireBasket.getAlto());
        }

        if (fireBasket.getPosX() < 350) {                             //si se pasa del borde de la izquierda
            fireBasket.setPosX(350);
        }

        if (fireBasket.getPosX() + fireBasket.getAncho() > getWidth()) {      //si se pasa del borde de la derecha
            fireBasket.setPosX(getWidth() - fireBasket.getAncho());
        }

        //basketBall colisiona abajo
        if (basketBall.getPosY() + basketBall.getAlto() > getHeight() - 30) {

            contPerdidas += 1;
            if (soundsOn) {
                fail.play();
            }
            boxClicked = false;
            time = 0;
            basketBall.setPosX(50);  //se reposiciona en su posicion inicial
            basketBall.setPosY(250);
            basketBall.setSpeedX(0);

            // basketBall.setSpeedY(0);
            bbspeedy = basketBall.getSpeedY();
            bbspeedx = basketBall.getSpeedX();

            if (basketBall.getConteo() > 0) {
                basketBall.setConteo(basketBall.getConteo() - 1);
            }
            ballClicked = false;
        }

        //Colision entre objetos
        if (fireBasket.intersecta(basketBall)) {
            boxClicked = false;
            time = 0;
            if (soundsOn) {
                collide.play();
            }
            basketBall.setConteo(basketBall.getConteo() + 2);

            basketBall.setPosX(50);     // se reposiciona el basketBall
            basketBall.setPosY(250);
            basketBall.setSpeedX(0);
            ballClicked = false;

        }

    }

    /**
     * Metodo que lee a informacion de un archivo y lo agrega a un vector.
     *
     * @throws IOException
     */
    public void leeArchivo() throws IOException {

        BufferedReader fileIn;
        try {
            fileIn = new BufferedReader(new FileReader(nombreArchivo));
        } catch (FileNotFoundException e) {
            //direccion,score,bbposx,bbposy,fbposx,fbposy,bbspeedx,bbspeedy,time;
            File puntos = new File(nombreArchivo);
            PrintWriter fileOut = new PrintWriter(puntos);
            fileOut.println("0,0,50,250,650,460,0,5,0,7,false,3.15,5.105");
            fileOut.close();
            fileIn = new BufferedReader(new FileReader(nombreArchivo));
        }
        String dato = fileIn.readLine();
        //se pasan los datos del arreglo a las variables auxiliares
        arr = dato.split(",");
        dir = ((arr[0].charAt(0)));
        sco = (Integer.parseInt(arr[1]));
        bbposx = (Integer.parseInt(arr[2]));
        bbposy = (Integer.parseInt(arr[3]));
        fbposx = (Integer.parseInt(arr[4]));
        fbposy = (Integer.parseInt(arr[5]));
        bbspeedx = Double.valueOf(arr[6]).doubleValue();
        bbspeedy = Double.valueOf(arr[7]).doubleValue();
        t = Double.valueOf(arr[8]).doubleValue();
        v = (Integer.parseInt(arr[9]));
        cp = (Integer.parseInt(arr[10]));
        g = (Integer.parseInt(arr[11]));
        bclicked = arr[12];
        vyi = (Double.valueOf(arr[13]));
        vxi = (Double.valueOf(arr[14]));

        fileIn.close();
    }

    /**
     * Metodo que agrega la informacion del vector al archivo.
     *
     * @throws IOException
     */
    public void grabaArchivo() throws IOException {

        PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivo));
        String x;
        //direccion,score,bbposx,bbposy,fbposx,fbposy,bbspeedx,bbspeedy,time;

        x = "" + (teclaPresionada) + "," + score + "," + "" + basketBall.getPosX() + ","
                + basketBall.getPosY() + "," + fireBasket.getPosX() + "," + fireBasket.getPosY() + ","
                + basketBall.getSpeedX() + "," + basketBall.getSpeedY() + "," + time + "," + vidas + ","
                + contPerdidas + "," + gravedad + "," + boxClicked + "," + velXI + "," + velYI;

        fileOut.println(x.toString());
        fileOut.close();
    }

    /**
     * Metodo <I>paint</I> sobrescrito de la clase <code>Applet</code>, heredado
     * de la clase Container.<P>
     * En este metodo lo que hace es Actualizar el Paint
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint(Graphics g) {
        // Inicializan el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Update la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Update el Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);

        // Dibuja la imagen Actualizada
        g.drawImage(dbImage, 0, 0, this);
    }

    /**
     * Metodo <I>paint1</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion Actualizada, ademas
     * que cuando la imagen es cargada te despliega una advertencia.
     *
     * @paramg es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint1(Graphics g) {
        if (vidas > 0) {
            if (fireBasket != null) {

                g.drawImage(background, 0, 0, this);

                if (pause) {

                    g.setFont(new Font("Avenir Black", Font.BOLD, 60));
                    g.setColor(Color.white);
                    g.drawString(fireBasket.getPausado(), 400, 400);
                    //si la bandera booleana de instrucciones esta en true se despliega el menu de instrucciones

                    if (instrucciones) {
                        g.setColor(Color.black);
                        g.drawRect(getWidth() / 3, getHeight() / 5, 500, 450);
                        g.fillRect(getWidth() / 3, getHeight() / 5, 500, 450);
                        g.setColor(Color.white);
                        g.setFont(new Font("Avenir Black", Font.BOLD, 18));
                        g.drawString("Instrucciones", getWidth() / 3 + 10, getHeight() / 5 + 30);
                        g.setFont(new Font("Avenir Black", Font.BOLD, 16));
                        g.drawString("P - Pausar", getWidth() / 3 + 50, getHeight() / 5 + 50);
                        g.drawString("G - Guardar", getWidth() / 3 + 50, getHeight() / 5 + 70);
                        g.drawString("C - Cargar", getWidth() / 3 + 50, getHeight() / 5 + 90);
                        g.drawString("S - Activar/Desactivar Sonido", getWidth() / 3 + 50, getHeight() / 5 + 110);
                        g.drawString("I - Instrucciones", getWidth() / 3 + 50, getHeight() / 5 + 130);
                        g.drawString("Flechas (Derecha e Izquiera) - Movimiento canasta", getWidth() / 3 + 50, getHeight() / 5 + 150);
                        g.drawString("Click en pelota - Iniciar ruta parabolica", getWidth() / 3 + 50, getHeight() / 5 + 170);

                    }

                } else {
                    //si la bandera booleana de instrucciones esta en true se despliega el menu de instrucciones
                    if (instrucciones) {
                        g.setColor(Color.black);
                        g.drawRect(getWidth() / 3, getHeight() / 5, 500, 500);
                        g.fillRect(getWidth() / 3, getHeight() / 5, 500, 500);
                        g.setColor(Color.white);
                        g.setFont(new Font("Avenir Black", Font.BOLD, 18));
                        g.drawString("Instrucciones", getWidth() / 3 + 10, getHeight() / 5 + 30);
                        g.setFont(new Font("Avenir Black", Font.BOLD, 16));
                        g.drawString("P - Pausar", getWidth() / 3 + 50, getHeight() / 5 + 50);
                        g.drawString("G - Guardar", getWidth() / 3 + 50, getHeight() / 5 + 70);
                        g.drawString("C - Cargar", getWidth() / 3 + 50, getHeight() / 5 + 90);
                        g.drawString("S - Activar/Desactivar Sonido", getWidth() / 3 + 50, getHeight() / 5 + 110);
                        g.drawString("I - Instrucciones", getWidth() / 3 + 50, getHeight() / 5 + 130);
                        g.drawString("Flechas (Derecha e Izquiera) - Movimiento canasta", getWidth() / 3 + 50, getHeight() / 5 + 150);
                        g.drawString("Click en pelota - Iniciar ruta parabolica", getWidth() / 3 + 50, getHeight() / 5 + 170);
                        pause = true;
                    } else {
                        //Dibuja string Score
                        g.setColor(Color.white);
                        g.setFont(new Font("Avenir Black", Font.ITALIC, 18));

                        g.drawString("Score: " + basketBall.getConteo(), 550, 60);

                        g.drawString("Life: " + vidas, 550, 80);
                        if (soundsOn) {
                            g.drawString("Sound: " + soundOn, 650, 80);
                        } else {
                            g.drawString("Sound: " + soundOff, 650, 80);
                        }

                        g.setColor(Color.white);

                        if (action) {
                            g.drawImage(fireBasket.getImagenI(), fireBasket.getPosX(), fireBasket.getPosY(), this);
                        } else {
                            g.drawImage(fireBasket1, fireBasket.getPosX(), fireBasket.getPosY(), this);
                        }
                        if (ballClicked) {
                            g.drawImage(basketBall.getImagenI(), basketBall.getPosX(), basketBall.getPosY(), this);

                        } else {
                            g.drawImage(basketBall1, basketBall.getPosX(), basketBall.getPosY(), this);

                        }
                    }

                }

            } else {
                //Da un mensaje mientras se carga el dibujo	
                g.drawString("No se cargo la imagen..", 20, 20);
            }

        } else {
            g.drawImage(gameover, 0, 0, this);
            g.setColor(Color.cyan);
            g.setFont(new Font("Avenir Black", Font.ITALIC, 30));
            g.drawString("Creditos: ", 85, 100);
            g.setFont(new Font("Avenir Black", Font.BOLD, 40));
            g.drawString("Proyecto de Video Juegos", 140, 150);
            g.setColor(Color.green);
            g.setFont(new Font("Avenir Black", Font.BOLD, 35));
            g.drawString("A00812572 Gustavo Ferrufino", 140, 500);
            g.drawString("A01191581 Andrés Gutiérrez", 140, 550);
        }
    }
    /*
     *Metodo keyPressed
     *Cuando una tecla esta apretada
     *recibe de param un evento, en este caso se busca que sea la p
     *para pausar el juego
     */

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_P) {

            pause = !pause;

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            action = true;
            teclaPresionada = 1;

        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            action = true;
            teclaPresionada = 2;

        }

        if (e.getKeyCode() == KeyEvent.VK_G) {

            if (instrucciones || pause) {
                grabar = false;
            } else {
                grabar = true;
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_C) {

            if (instrucciones || pause) {
                cargar = false;
            } else {
                cargar = true;
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_I) {

            if (instrucciones) {
                instrucciones = false;
                pause = false;
            } else {
                pause = true;
                instrucciones = true;
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            soundsOn = !soundsOn;
        }

    }

    public void keyTyped(KeyEvent e) { //metodo cuando una tecla fue typeada
    }

    public void keyReleased(KeyEvent e) {   //metodo cuandos e suelta la tecla

        action = false; //Presiono flecha arriba

    }
    /*
     *Metodo mouseClicked
     *Cuando el mouse es apretado
     *recibe de param un evento, que ayudara a definir donde fue picado
     *dentro del applet
     */

    public void mouseClicked(MouseEvent e) {

        if (!pause) {
            if (box.contains(e.getX(), e.getY()) && (box.getX() == basketBall.getPosX())) {
                boxClicked = true;
                speed = (int) ((Math.random() * ((6 + difVel) - (3 + difVel))) + 3 + difVel);
                velXI = speed * (Math.cos(Math.toRadians(45)));
                velYI = speed * (Math.sin(Math.toRadians(45)));
                ballClicked = true;

            }
        }

    }

    public void mouseEntered(MouseEvent e) { //metodo cuando entra el mouse

    }

    public void mouseExited(MouseEvent e) { //metodo cuando sale el mouse

    }

    public void mousePressed(MouseEvent e) {    //metodo cuando el mouse es presionado

    }

    public void mouseReleased(MouseEvent e) {//metodo cuando el mouse es soltado

        ballClicked = false;
    }

    public void mouseMoved(MouseEvent e) {  //metodos de MouseMotionListener

    }

    public void mouseDragged(MouseEvent e) {   //metodos de MouseMotionListener

    }
}
