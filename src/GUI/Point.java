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
public class Point {
    private int xPos, yPos; 
    
    public Point(int xPos, int yPos){ 
        this.xPos = xPos; 
        this.yPos = yPos; 
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
    
    public String toString() { 
        return "(" + xPos + ", " + yPos + ")";
    }
    
}
