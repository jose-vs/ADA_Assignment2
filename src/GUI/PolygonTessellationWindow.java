/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BruteForce.BruteForce;
import ExactApproach.ExactApproach;
import GreedyMethod.GreedyMethod;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    public ExactApproach ea;
    public int numOfVertices;
    
    public JButton generatePolygon, BruteForce, GreedyApproach, ExactApproach;
    public JTextField textBox; 
    public JLabel inputLabel;

    
    public PolygonTessellationWindow() { 
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        
        polygon = new Polygon();
        bf = new BruteForce();
        ea = new ExactApproach();
        
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
        
        BruteForce = new JButton("Brute Force"); 
        BruteForce.setBounds(PANEL_WIDTH - 275,105,250,25);
        BruteForce.addActionListener(new TessellationListener());
        add(BruteForce);

        GreedyApproach = new JButton("Greedy Approach");
        GreedyApproach.setBounds(PANEL_WIDTH - 275,145,250,25);
        GreedyApproach.addActionListener(new TessellationListener());
        add(GreedyApproach);
        
        ExactApproach = new JButton("Exact Approach"); 
        ExactApproach.setBounds(PANEL_WIDTH - 275,185,250,25);
        ExactApproach.addActionListener(new TessellationListener());
        add(ExactApproach);
          
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
                //tell the user to enter a value <= 3
            }
        }
        
    }
    
    public class TessellationListener implements ActionListener { 

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(polygon.getNumOfVertices() >= 3){ 
                
                if(ae.getActionCommand().equals("Brute Force"))
                    bf.updatePolgyon(polygon);
                else if (ae.getActionCommand().equals("Exact Approach"))
                    ea.updatePolgyon(polygon);
                else if (ae.getActionCommand().equals("Greedy Approach")) {
                    GreedyMethod greedyMethod = new GreedyMethod(polygon);
                    greedyMethod.updateGreedy(polygon);
                }
                
                repaint();
            }
        }
    }
    
    
    
    
    
}
