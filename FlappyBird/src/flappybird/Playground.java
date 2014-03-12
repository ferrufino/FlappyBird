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
import java.util.LinkedList;
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
 * @author Ferrufino y Andres
 */
public class Playground extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    // Se declaran las variables objetos. 
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private SoundClip fail;    // Objeto AudioClip
    private SoundClip collide;    //Objeto AudioClip 
    private SoundClip flap;    //Objeto AudioClip 
    private Pipe fireBasket;    // Objeto de la clase Elefante
    private Pipe imagenAbajo1;
    private Pipe imagenAbajo2;
    private Bird fishFlap;   //Objeto de la clase Raton
    private Rectangle box;

    private int tempInicio;
    private int tempLevel2;
    private int tempLevel3;
    private int tempLevelup;
    private boolean go;

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
    private Image playagain;
    private Image name;
    private Image gameover; //imagen de gameover
    private Image background;
    private Image fishFlap1;
    private Image fireBasket1;
    private Image chocan;
    private Image play;
    private Image level2;
    private Image level3;
    private String soundOn;
    private String soundOff;
    private double velXI;
    private double velYI;
    private int speed;
    private int teclaPresionada;
    private int posX;
    private int posY;
    private int score;

    private int xMayor;
    private int xMenor;
    private int yMayor;
    private int yMenor;
    private boolean pause; //Permite al usuario pausar el juego
    private boolean action;
    private boolean soundsOn;
    private boolean gameOver;
    private long tiempoActual;
    private long tiempoInicial;
    private boolean BEGIN;
    private boolean ballClicked;
    private boolean lose;
    private boolean instrucciones;
    private int g;
    private String bclicked;
    private double vyi;
    private double vxi;
    private int[] array = new int[]{-30, -10, 0, 10, 30};
    private int[] array2 = new int[]{-75, -50, 0, 50, 75};
    private int[] array3 = new int[]{-150, -50, 10, 80,120};



    //Declaracion de objetos pipe
    private LinkedList<Pipe> listTop;
    private LinkedList<Pipe> listBot;
    private Pipe columnsTop;
    private Pipe columnsBot;
    private int diffColumns;

    /**
     * Metodo <I>PlayGround()</I> de la clase <code>PlayGround</code>. Es el
     * constructor de la clase donde se definen las variables
     */
    public Playground() {

        tempInicio = 0;
        tempLevel2 = 0;
        tempLevel3 = 0;
        tempLevelup = 0;
        go = false;
        gameOver = false;
        setSize(450, 660);
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
        lose = false;

        diffColumns = 200; //diferencia entre columna

        nombreArchivo = "Archivo.txt";
        tempInicio = 0;
        gravedad = 9;
        time = 0;
        score = 5;                    //puntaje inicial
        vidas = 5;                    //vida inicial
        xMayor = (getWidth() - getWidth() / 10);           //posicion máxima en x que tendrán el fishFlap
        xMenor = 0;           //posicion mínima en x que tendrá el fishFlap
        yMayor = (getHeight() - (getHeight() / 10));          //posicion máxima en y que tendrán el fishFlap
        yMenor = 10;        //posicion mínima en y que tendrá el fishFlap
        velXI = 0;
        velYI = 0;
        //Se cargan los sonidos.

        collide = new SoundClip("Sounds/fail-buzzer-03.wav");
        fail = new SoundClip("Sounds/fail-buzzer-03.wav");
        flap = new SoundClip("Sounds/Choque.wav");
        posX = 100;     //se generarán los fishFlaps en posiciones aleatorias fuera del applet
        posY = 100;
        URL plURL = this.getClass().getResource("Images/taptoFlap.png");
        play = Toolkit.getDefaultToolkit().getImage(plURL);

        URL tagURL = this.getClass().getResource("Images/tapagain.png");
        playagain = Toolkit.getDefaultToolkit().getImage(tagURL);

        URL fbURL = this.getClass().getResource("Images/barArriba.png");

        URL namURL = this.getClass().getResource("Images/splshyfish.png");
        name = Toolkit.getDefaultToolkit().getImage(namURL);

        URL l2URL = this.getClass().getResource("Images/level-2.png");
        level2 = Toolkit.getDefaultToolkit().getImage(l2URL);

        URL l3URL = this.getClass().getResource("Images/level-3.png");
        level3 = Toolkit.getDefaultToolkit().getImage(l3URL);

        URL abj1URL = this.getClass().getResource("Images/abajo1.png");
        URL abj2URL = this.getClass().getResource("Images/abajo2.png");

        imagenAbajo1 = new Pipe(0, getHeight() - 140, Toolkit.getDefaultToolkit().getImage(abj1URL));
        imagenAbajo2 = new Pipe(450, getHeight() - 140, Toolkit.getDefaultToolkit().getImage(abj2URL));

        URL bbotURL = this.getClass().getResource("Images/barAbajo.png");
        URL bbURL = this.getClass().getResource("Images/fish.gif");
        fireBasket = new Pipe(50, 250, Toolkit.getDefaultToolkit().getImage(bbotURL));

        fishFlap = new Bird(50, 250, Toolkit.getDefaultToolkit().getImage(bbURL));

        box = new Rectangle(50, 250, fishFlap.getAncho(), fishFlap.getAlto());
        URL xuURL = this.getClass().getResource("Images/gameover.png");
        gameover = Toolkit.getDefaultToolkit().getImage(xuURL);

        URL bgURL = this.getClass().getResource("Images/fondo.png");
        background = Toolkit.getDefaultToolkit().getImage(bgURL);

        URL bbalURL = this.getClass().getResource("Images/fish.png");
        fishFlap1 = Toolkit.getDefaultToolkit().getImage(bbalURL);

        URL fbasURL = this.getClass().getResource("Images/fireBasket1.png");
        fireBasket1 = Toolkit.getDefaultToolkit().getImage(fbasURL);

        URL cHURL = this.getClass().getResource("Images/boom.png");
        chocan = Toolkit.getDefaultToolkit().getImage(cHURL);
        //Se cargan los pipes 
        listTop = new LinkedList<Pipe>();
        listBot = new LinkedList<Pipe>();
        //pilar 1
        int temp1 = (int) (Math.random() * 5);
        columnsTop = new Pipe(getWidth(), 350 + array[temp1], Toolkit.getDefaultToolkit().getImage(fbURL));
        listTop.add(columnsTop);

        columnsBot = new Pipe(getWidth(), -300 + array[temp1], Toolkit.getDefaultToolkit().getImage(bbotURL));
        listBot.add(columnsBot);
        //pilar 2
        int temp2 = (int) (Math.random() * 5);
        columnsTop = new Pipe(getWidth() + (diffColumns + 75), 350 + array[temp2], Toolkit.getDefaultToolkit().getImage(fbURL));
        listTop.add(columnsTop);

        columnsBot = new Pipe(getWidth() + (diffColumns + 75), -300 + array[temp2], Toolkit.getDefaultToolkit().getImage(bbotURL));
        listBot.add(columnsBot);
        //pilar3
        int temp3 = (int) (Math.random() * 5);
        columnsTop = new Pipe(getWidth() + (diffColumns + 75) * 2, 350 + array[temp3], Toolkit.getDefaultToolkit().getImage(fbURL));
        listTop.add(columnsTop);

        columnsBot = new Pipe(getWidth() + (diffColumns + 75) * 2, -300 + array[temp3], Toolkit.getDefaultToolkit().getImage(bbotURL));
        listBot.add(columnsBot);

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
            if (go) {
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
     * fishFlap.
     *
     */
    public void Actualiza() {

        //Actualizacion de distancia entre Pilares
        if (score == 10 || score == 20) {
            if (tempLevelup == 0) {
                diffColumns -= 70;
              
                tempLevelup++;
            }

        }
        if (score != 10 && score != 20) {
            tempLevelup = 0;
        }
        ////// pilares como varian
        /*
        if (score == 10) {
             ///1
        columnsTop = (Pipe) (listTop.get(0));
        columnsBot = (Pipe) (listBot.get(0));
        int tempa = (int) (Math.random() * 5);
     
        columnsTop.setPosY(350 + array[tempa]);
      
        columnsBot.setPosY(-300 + array[tempa]);

        ///2
        columnsTop = (Pipe) (listTop.get(1));
        columnsBot = (Pipe) (listBot.get(1));
        int tempb = (int) (Math.random() * 5);

        columnsTop.setPosX(getWidth() + (diffColumns + 75));
        columnsTop.setPosY(350 + array[tempb]);
        columnsBot.setPosX(getWidth() + (diffColumns + 75));
        columnsBot.setPosY(-280 + array[tempb]);

        ///3
        columnsTop = (Pipe) (listTop.get(2));
        columnsBot = (Pipe) (listBot.get(2));
        int tempc = (int) (Math.random() * 5);

        columnsTop.setPosX(getWidth() + (diffColumns + 75) * 2);
        columnsTop.setPosY(350 + array[tempc]);
        columnsBot.setPosX(getWidth() + (diffColumns + 75) * 2);
        columnsBot.setPosY(-280 + array[tempc]);
        
        }
        */
        //si la bandera de grabar esta en true se graba el juego en el archivo 
        if (grabar) {
            try {

                grabaArchivo();    //Graba el vector en el archivo.
            } catch (IOException e) {
                System.out.println("Error en " + e.toString());
            }
            grabar = false;
        }
        //Actualizacion del piso debido a la velocidad de los tubos
        //Primer Pedazo
        if (imagenAbajo1.getPosX() != -450) {

            imagenAbajo1.setPosX(imagenAbajo1.getPosX() - 1);

        }
        if (imagenAbajo1.getPosX() == -450) {

            imagenAbajo1.setPosX(450);
        }
        //Segundo Pedazo
        if (imagenAbajo2.getPosX() != -450) {

            imagenAbajo2.setPosX(imagenAbajo2.getPosX() - 1);

        }
        if (imagenAbajo2.getPosX() == -450) {

            imagenAbajo2.setPosX(450);
        }
        //Actualizacion de Pipes
        if (score < 10) {
            for (int i = 0; i < 3; i++) {

                columnsTop = (Pipe) (listTop.get(i));

                columnsTop.setPosX(columnsTop.getPosX() - 1);

                if (columnsTop.getPosX() == fishFlap.getPosX()) {
                    score++;

                }

            }

            for (int i = 0; i < 3; i++) {
                columnsBot = (Pipe) (listBot.get(i));
                columnsBot.setPosX(columnsBot.getPosX() - 1);

            }

        } else if (score >= 10 && score < 20) {
            for (int i = 0; i < 3; i++) {

                columnsTop = (Pipe) (listTop.get(i));

                columnsTop.setPosX(columnsTop.getPosX() - 2);

                if (columnsTop.getPosX() == fishFlap.getPosX() - 1) {
                    score++;

                }

            }

            for (int i = 0; i < 3; i++) {
                columnsBot = (Pipe) (listBot.get(i));
                columnsBot.setPosX(columnsBot.getPosX() - 2);

            }
        } else if (score >= 20) {
            for (int i = 0; i < 3; i++) {

                columnsTop = (Pipe) (listTop.get(i));
                columnsTop.setPosX(columnsTop.getPosX() - 3);
                if (columnsTop.getPosX() == fishFlap.getPosX() - 2) {
                    score++;

                }

            }

            for (int i = 0; i < 3; i++) {
                columnsBot = (Pipe) (listBot.get(i));
                columnsBot.setPosX(columnsBot.getPosX() - 3);

            }
        }
        //Aumento de velocidad
        //si la bandera de cargar esta en true se carga del archivo al juego y se asignan los valores
        if (cargar) {
            try {

                leeArchivo();    //Graba el vector en el archivo.
            } catch (IOException e) {
                //System.out.println("Error en " + e.toString());
            }
            fishFlap.setConteo(sco);
            teclaPresionada = dir;
            fishFlap.setPosX(bbposx);
            fishFlap.setPosY(bbposy);
            fireBasket.setPosX(fbposx);
            fireBasket.setPosY(fbposy);
            fishFlap.setSpeedX(bbspeedx);
            fishFlap.setSpeedY(bbspeedy);

            time = t;
            cargar = false;
            vidas = v;
            contPerdidas = cp;
            gravedad = g;
            boxClicked = true;
            velXI = vxi;
            velYI = vyi;

        }

        //si el rectangulo de la posicion inicial fue presionada se inicializa el tiempo
        if (boxClicked) {

            time += 0.040;
            // fishFlap.setSpeedX(velXI);
            fishFlap.setSpeedY((velYI * -1) + gravedad * time);
            //fishFlap.setPosX(fishFlap.getPosX() + (int) (fishFlap.getSpeedX()));
            fishFlap.setPosY(fishFlap.getPosY() + (int) (fishFlap.getSpeedY()));

        }

        if (fishFlap.getPosY() < getHeight()) {
            fishFlap.setPosX((int) (fishFlap.getPosX() + fishFlap.getSpeedX()));
        }

    }

    /**
     * Metodo usado para checar las colisiones del objeto elefante y fishFlap
     * con las orillas del <code>Applet</code>.
     */
    public void ChecaColision() {

        //Pipes colisionan con lado izquierdo del applet
        for (int i = 0; i < listTop.size(); i++) {
            int temp = (int) (Math.random() * 5);
            columnsTop = (Pipe) (listTop.get(i));

            if (columnsTop.getPosX() < -75) {
                columnsTop.setPosX(getWidth() + diffColumns + 75);
                columnsTop.setPosY(350);
                if (score < 10) {
                columnsTop.setPosY(columnsTop.getPosY() + array[temp]);
                } else if (score < 20 ) {
                
                columnsTop.setPosY(columnsTop.getPosY() + array2[temp]);
                } else {
                    
                columnsTop.setPosY(columnsTop.getPosY() + array3[temp]);
                }
            }
            columnsBot = (Pipe) (listBot.get(i));

            if (columnsBot.getPosX() < -75) {
                columnsBot.setPosX(getWidth() + diffColumns + 75);
                columnsBot.setPosY(-300);
                if (score < 10) {
                columnsBot.setPosY(columnsBot.getPosY() + array[temp]);
                } else if (score < 20 ) {
                
                columnsBot.setPosY(columnsBot.getPosY() + array2[temp]);
                } else {
                    
                columnsBot.setPosY(columnsBot.getPosY() + array3[temp]);
                }
            }
        }

        //checa colision del pajaro con los pilares
        /*
        for (int i = 0; i < listTop.size(); i++) {
            columnsTop = (Pipe) (listTop.get(i));
            columnsBot = (Pipe) (listBot.get(i));

            if (columnsTop.intersecta(fishFlap) || columnsBot.intersecta(fishFlap)) {
                if (soundsOn) {
                    collide.play();

                }
                score = 0;
                fishFlap.setPosX(60);     // se reposiciona el fishFlap
                fishFlap.setPosY(250);
                boxClicked = false;
                time = 0;
                ballClicked = false;
                go = false;

            }
        }
         */
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

        //fishFlap colisiona arriba
        if (fishFlap.getPosY() < 10) {
           
            if (soundsOn) {
                fail.play();
            }
            score = 0;
            boxClicked = false;
            time = 0;
            fishFlap.setPosX(50);  //se reposiciona en su posicion inicial
            fishFlap.setPosY(250);
            fishFlap.setSpeedX(0);
            go = false;
            // fishFlap.setSpeedY(0);
            bbspeedy = fishFlap.getSpeedY();
            bbspeedx = fishFlap.getSpeedX();

            if (fishFlap.getConteo() > 0) {
                fishFlap.setConteo(fishFlap.getConteo() - 1);
            }
            ballClicked = false;
            
        }
        //fishFlap colisiona abajo
        if (fishFlap.getPosY() + fishFlap.getAlto() > getHeight()) {

            //contPerdidas += 1;
            if (soundsOn) {
                fail.play();
            }
            score = 0;
            boxClicked = false;
            time = 0;
            fishFlap.setPosX(50);  //se reposiciona en su posicion inicial
            fishFlap.setPosY(250);
            fishFlap.setSpeedX(0);
            go = false;
            // fishFlap.setSpeedY(0);
            bbspeedy = fishFlap.getSpeedY();
            bbspeedx = fishFlap.getSpeedX();

            if (fishFlap.getConteo() > 0) {
                fishFlap.setConteo(fishFlap.getConteo() - 1);
            }
            ballClicked = false;
        }

        //Colision entre objetos
        if (fireBasket.intersecta(fishFlap)) {
            boxClicked = false;
            time = 0;
            if (soundsOn) {
                collide.play();
            }
            fishFlap.setConteo(fishFlap.getConteo() + 2);

            fishFlap.setPosX(50);     // se reposiciona el fishFlap
            fishFlap.setPosY(250);
            fishFlap.setSpeedX(0);
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

        x = "" + (teclaPresionada) + "," + score + "," + "" + fishFlap.getPosX() + ","
                + fishFlap.getPosY() + "," + fireBasket.getPosX() + "," + fireBasket.getPosY() + ","
                + fishFlap.getSpeedX() + "," + fishFlap.getSpeedY() + "," + time + "," + vidas + ","
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

        if (fireBasket != null) {

            g.drawImage(background, 0, 0, this);

            if (pause) {

                g.setFont(new Font("Avenir Black", Font.BOLD, 18));
                g.setColor(Color.white);
                g.drawString(fireBasket.getPausado(), 200, 400);
                //si la bandera booleana de instrucciones esta en true se despliega el menu de instrucciones

            } else {

                g.setColor(Color.white);
                g.setFont(new Font("Avenir Black", Font.ITALIC, 18));

                g.setColor(Color.white);

                for (int i = 0; i < listTop.size(); i++) {
                    columnsTop = (Pipe) (listTop.get(i));
                    g.drawImage(columnsTop.getImagenI(), columnsTop.getPosX(), columnsTop.getPosY(), this);
                }
                for (int i = 0; i < listBot.size(); i++) {
                    columnsBot = (Pipe) (listBot.get(i));
                    g.drawImage(columnsBot.getImagenI(), columnsBot.getPosX(), columnsBot.getPosY(), this);
                }

                if (ballClicked) {
                    g.drawImage(fishFlap.getImagenI(), fishFlap.getPosX(), fishFlap.getPosY(), this);

                } else {
                    g.drawImage(fishFlap1, fishFlap.getPosX(), fishFlap.getPosY(), this);

                }
                //Imagenes encima de todo
                g.setColor(Color.white);
                g.setFont(new Font("Avenir Black", Font.ITALIC, 30));

                g.drawString("Score: " + score, 170, 60);
                if (score == 10) {
                    if (tempLevel2 < 35) {
                        g.drawImage(level2, 30, 100, this);
                        tempLevel2++;
                    }

                }

                if (score == 20) {
                    if (tempLevel3 < 35) {
                        g.drawImage(level3, 30, 100, this);
                        tempLevel3++;
                    }

                }
                g.drawImage(imagenAbajo1.getImagenI(), imagenAbajo1.getPosX(), imagenAbajo1.getPosY(), this);
                g.drawImage(imagenAbajo2.getImagenI(), imagenAbajo2.getPosX(), imagenAbajo2.getPosY(), this);
                if ((!go) && (tempInicio == 0)) {
                    g.drawImage(play, getWidth() / 2, getHeight() / 2, this);
                    g.drawImage(name, 80, 100, this);

                } else if (!go) {
                    g.drawImage(gameover, 80, 100, this);
                    g.drawImage(playagain, 30, 300, this);
                }

                //}
            }

        } else {
            //Da un mensaje mientras se carga el dibujo	
            g.drawString("No se cargo la imagen..", 20, 20);
        }

    }

    /*
     *Metodo keyPressed
     *Cuando una tecla esta apretada
     *recibe de param un evento, en este caso se busca que sea la p
     *para pausar el juego
     */
    public void keyPressed(KeyEvent e) {
            
        if (soundsOn) {
            flap.play();
            }

        if (e.getKeyCode() == KeyEvent.VK_P) {

            pause = !pause;

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

        if (e.getKeyCode() == KeyEvent.VK_S) {
            soundsOn = !soundsOn;
            
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (soundsOn) {
            flap.play();
            }
            if (!go && (tempInicio != 0)) {
                restart();

            }
            if (!pause) {

                boxClicked = true;
                speed = 10;
                velXI = speed * (Math.cos(Math.toRadians(45)));
                velYI = speed * (Math.sin(Math.toRadians(45)));
                time = 0; //time hace que se pueda volver a hacer click;
                ballClicked = true;
                go = true;
                tempInicio = 10;

            }
        }

    }

    public void keyTyped(KeyEvent e) { //metodo cuando una tecla fue typeada
    }

    public void keyReleased(KeyEvent e) {   //metodo cuandos e suelta la tecla

    }
    /*
     *Metodo mouseClicked
     *Cuando el mouse es apretado
     *recibe de param un evento, que ayudara a definir donde fue picado
     *dentro del applet
     */

    public void mouseClicked(MouseEvent e) {
        if (!go && (tempInicio != 0)) {
            restart();

        }
        if (!pause) {

            boxClicked = true;
            speed = 10;
            velXI = speed * (Math.cos(Math.toRadians(45)));
            velYI = speed * (Math.sin(Math.toRadians(45)));
            time = 0; //time hace que se pueda volver a hacer click;
            ballClicked = true;
            go = true;

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

    public void restart() {

        diffColumns = 200;

        //////
        ///1
        columnsTop = (Pipe) (listTop.get(0));
        columnsBot = (Pipe) (listBot.get(0));
        int tempa = (int) (Math.random() * 5);
        columnsTop.setPosX(getWidth());
        columnsTop.setPosY(350 + array[tempa]);
        columnsBot.setPosX(getWidth());
        columnsBot.setPosY(-300 + array[tempa]);

        ///2
        columnsTop = (Pipe) (listTop.get(1));
        columnsBot = (Pipe) (listBot.get(1));
        int tempb = (int) (Math.random() * 5);

        columnsTop.setPosX(getWidth() + (diffColumns + 75));
        columnsTop.setPosY(350 + array[tempb]);
        columnsBot.setPosX(getWidth() + (diffColumns + 75));
        columnsBot.setPosY(-280 + array[tempb]);

        ///3
        columnsTop = (Pipe) (listTop.get(2));
        columnsBot = (Pipe) (listBot.get(2));
        int tempc = (int) (Math.random() * 5);

        columnsTop.setPosX(getWidth() + (diffColumns + 75) * 2);
        columnsTop.setPosY(350 + array[tempc]);
        columnsBot.setPosX(getWidth() + (diffColumns + 75) * 2);
        columnsBot.setPosY(-280 + array[tempc]);

        tempLevelup = 0;

    }

}
