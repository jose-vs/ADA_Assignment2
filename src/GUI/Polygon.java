/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author jcvsa
 */
public class Polygon {
    private Point[] vertices; 
    
    public Polygon() { 
        createPolygon(); 
    }
    
    public Polygon(Point[] vertices){ 
        this.vertices = vertices;
    }
    
    public Point[] getVertices() { 
        return this.vertices;
    } 
    
    
    public void createPolygon() { 
        
    }
}
