/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jcvsa
 */
public class Polygon {
    private List<Point> vertices; 
    
    private final Random RAND = new Random(); 
    
    public Polygon(int numOfVertices) { 
        createPolygon(numOfVertices); 
    }
    
    public Polygon(List<Point> vertices){ 
        this.vertices = vertices;
    }
    
    public List<Point> getVertices() { 
        return this.vertices;
    }
    
    public void createPolygon(int numOfVertices) { 
//        List<Integer> xPool = new ArrayList<>(numOfVertices); 
//        List<Integer> yPool = new ArrayList<>(numOfVertices); 
//        
//        for (int i = 0; i < numOfVertices; i++){ 
//            xPool.add(RAND.nextInt()); 
//            yPool.add(RAND.nextInt());
//        }
//        
//        Collections.sort(xPool); 
//        Collections.sort(yPool);
//        
//        int minX = xPool.get(0); 
//        int maxX = xPool.get(numOfVertices - 1); 
//        int minY = yPool.get(0); 
//        int maxY = yPool.get(numOfVertices - 1); 
//        
//        //extract vector componenets
//        List<Integer> xVec = new ArrayList<>(numOfVertices);
//        List<Integer> yVec = new ArrayList<>(numOfVertices);
//        
//        int lastTop = minX, lastBot = minX;
//        
//        for (int i = 1; i < numOfVertices - 1; i++) {
//            int x = xPool.get(i);
//
//            if (RAND.nextBoolean()) {
//                xVec.add(x - lastTop);
//                lastTop = x;
//            } else {
//                xVec.add(lastBot - x);
//                lastBot = x;
//            }
//        }
//        
//        xVec.add(maxX - lastTop);
//        xVec.add(lastBot - maxX);
//
//        int lastLeft = minY, lastRight = minY;
//
//        for (int i = 1; i < numOfVertices - 1; i++) {
//            int y = yPool.get(i);
//
//            if (RAND.nextBoolean()) {
//                yVec.add(y - lastLeft);
//                lastLeft = y;
//            } else {
//                yVec.add(lastRight - y);
//                lastRight = y;
//            }
//        }
//
//        yVec.add(maxY - lastLeft);
//        yVec.add(lastRight - maxY);
//        
//        //randomly pair up x and y 
//        Collections.shuffle(yVec);
        
    }
}
