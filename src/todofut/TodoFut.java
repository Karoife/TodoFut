/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todofut;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author joser
 */
public class TodoFut {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            CalculadoraPostal calc = new CalculadoraPostal();
            calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            calc.setVisible(true);
        } catch (Exception e){
            System.out.println("No se pudo inicizlizar el programa");
        }
    }
    
}
