/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flappybird;
import javax.swing.JFrame;

public class FlappyBird {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Playground variable = new Playground();//creo un objeto
        variable.setVisible(true); //Aparezca mi codigo en clase AppletExamen1
        variable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
