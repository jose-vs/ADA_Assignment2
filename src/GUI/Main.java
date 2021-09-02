/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;

/**
 *
 * @author jcvsa
 */
public class Main {
    public static void main(String[] args) {
        PolygonTessellationWindow window = new PolygonTessellationWindow(); 
        JFrame frame = new JFrame("Assignment 2"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(window);  
        frame.pack(); 
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
