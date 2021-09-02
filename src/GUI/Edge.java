/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author jcvsa
 * @param <Point>
 */
public final class Edge {
    private Point start; 
    private Point end; 
    
    public Edge(Point start, Point end){ 
        this.start = start; 
        this.end = end; 
    }
    
    public double length(){ 
        return 
            Math.sqrt((
                Math.pow((start.getxPos() - end.getxPos()), 2) +
                Math.pow((start.getyPos() - end.getyPos()), 2)));
    }
    
    

    /**
     * @return the start
     */
    public Point getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public Point getEnd() {
        return end;
    }
    
    
    
}
