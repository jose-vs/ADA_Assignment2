/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BruteForce.BruteForce;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jcvsa
 */
public class PolygonTessellationWindow extends JPanel{
    public final int PANEL_WIDTH = 1080; public final int PANEL_HEIGHT = 720;
    public Polygon polygon; 
    public BruteForce bf; 
    public int numOfVertices;
    
    public JButton generatePolygon, tessellatePolygon; 
    public JTextField textBox; 
    public JLabel inputLabel;

    
    public PolygonTessellationWindow() { 
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        
        polygon = new Polygon();
        bf = new BruteForce();
        
        generatePolygon = new JButton("Generate Polygon"); 
        generatePolygon.setBounds(PANEL_WIDTH - 275,25,250,25);
        generatePolygon.addActionListener(new GeneratePolygonListener());
        add(generatePolygon);
        
        inputLabel = new JLabel("No. of Vertices"); 
        inputLabel.setForeground(Color.WHITE);
        inputLabel.setBounds(PANEL_WIDTH - 275,65,250,25);
        add(inputLabel);
        
        textBox = new JTextField(); 
        textBox.setBounds(PANEL_WIDTH - 175,65,150,25);
        add(textBox);
        
        tessellatePolygon = new JButton("Tessellate"); 
        tessellatePolygon.setBounds(PANEL_WIDTH - 275,105,250,25);
        tessellatePolygon.addActionListener(new TessellationListener());
        add(tessellatePolygon);
          
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(polygon.getNumOfVertices() >= 3){
            polygon.drawPolygon(g);
            polygon.drawTessellation(g);
        } 
     
    }
    
    public class GeneratePolygonListener implements ActionListener{ 

        @Override
        public void actionPerformed(ActionEvent ae) {           
            
            try { 
                
                numOfVertices = Integer.parseInt(textBox.getText());
                polygon.createPolygon(numOfVertices, 600, 600);
                repaint();
                
            } catch (NumberFormatException o){ 
                //tell users to enter a number
            } catch (IndexOutOfBoundsException o){ 
                //tell teh user to enter a value <= 3
            }
        }
        
    }
    
    public class TessellationListener implements ActionListener { 

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(polygon.getNumOfVertices() >= 3){ 
                bf.updatePolgyon(polygon);
                
                repaint();
            }
        }
    }
    
    
    
    
    
}
