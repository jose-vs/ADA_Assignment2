/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jcvsa
 */
public class Polygon {
    private List<Point> vertices; 
    private int xBound, yBound; 
    private final Random RAND = new Random(); 
    
     /**
     * Default Constructor
     */
    public Polygon() { 
        vertices = new ArrayList<>();
    }
    
    /**
     * Constructor
     * Creates a new randomized polygon based on the paramaters
     * 
     * @param numOfVertices         number of vertices
     * @param height                y bound for the polygon
     * @param width                 x bound for the polygon
     */
    public Polygon(int numOfVertices, int height, int width) {
        createPolygon(numOfVertices, height, width); 
    }
    
    /**
     * Constructor
     * sets the local list of vertices
     * 
     * @param vertices 
     */
    public Polygon(List<Point> vertices){ 
        this.vertices = vertices;
    }
    
    
    public List<Point> getVertices() { 
        return this.vertices;
    }
    
    public int getNumOfVertices() { 
        return vertices.size();
    }
    
    /**
     * retrieves an int array of the x coords from the vertices list
     * 
     * @return              xCoords array
     */
    public int[] getXCoords() { 
        
        /**
         * only retrieve the list if vertices is not empty 
         */
        if(!vertices.isEmpty()) {
            int xCoords[] = new int[vertices.size()]; 

            Iterator it = vertices.iterator(); 
            int index = 0;
            
            while (it.hasNext()){
                Point p = (Point) it.next();
                xCoords[index] = p.getxPos();
                index++;
            }
                        
            return xCoords; 
            
        } else 
            return null;
    }
    
    /**
     * retrieves an int array of the y coords from the vertices list
     * 
     * @return              yCoords array
     */
    public int[] getYCoords() { 
        
        /**
         * only retrieve the list if vertices is not empty 
         */
        if(!vertices.isEmpty()) { 
            int[] yCoords = new int[vertices.size()]; 

            Iterator it = vertices.iterator(); 
            int index = 0;
            
            while (it.hasNext()){
                Point p = (Point) it.next();
                yCoords[index] = p.getyPos();
                index++;
            }

            return yCoords;
            
        } else 
            return null;
    }
    
    /**
     * draws the polygon without tessellations
     * 
     * @param g 
     */
    public void drawPolygon(Graphics g){ 
        g.setColor(Color.WHITE);     
        g.drawPolygon(getXCoords(), getYCoords(), getNumOfVertices());
    }
    
    /** 
     * Randomly generate points for a convex polygon 
     * 
     * @param numOfVertices     the number of vertices to generate
     * @param height            y bound for the randomly generated point
     * @param width             x bound for the randomly generated point
     */
    public void createPolygon(int numOfVertices, int height, int width) { 
        List<Integer> xPool = new ArrayList<>(numOfVertices); 
        List<Integer> yPool = new ArrayList<>(numOfVertices); 
        
        for (int i = 0; i < numOfVertices; i++){ 
            xPool.add(RAND.nextInt(width)); 
            yPool.add(RAND.nextInt(height));
        }
        
        Collections.sort(xPool); 
        Collections.sort(yPool);
        
        int minX = xPool.get(0); 
        int maxX = xPool.get(numOfVertices - 1); 
        int minY = yPool.get(0); 
        int maxY = yPool.get(numOfVertices - 1); 
        
        //extract vector componenets
        List<Integer> xVec = new ArrayList<>(numOfVertices);
        List<Integer> yVec = new ArrayList<>(numOfVertices);
        
        int lastTop = minX, lastBot = minX;
        
        for (int i = 1; i < numOfVertices - 1; i++) {
            int x = xPool.get(i);

            if (RAND.nextBoolean()) {
                xVec.add(x - lastTop);
                lastTop = x;
            } else {
                xVec.add(lastBot - x);
                lastBot = x;
            }
        }
        
        xVec.add(maxX - lastTop);
        xVec.add(lastBot - maxX);

        int lastLeft = minY, lastRight = minY;

        for (int i = 1; i < numOfVertices - 1; i++) {
            int y = yPool.get(i);

            if (RAND.nextBoolean()) {
                yVec.add(y - lastLeft);
                lastLeft = y;
            } else {
                yVec.add(lastRight - y);
                lastRight = y;
            }
        }

        yVec.add(maxY - lastLeft);
        yVec.add(lastRight - maxY);
        
        //randomly pair up x and y 
        Collections.shuffle(yVec);
        
        List<Point> vec = new ArrayList<>(numOfVertices);
        
        for (int i = 0; i < numOfVertices; i++) {
            vec.add(new Point(xVec.get(i), yVec.get(i)));
        }
        
        // Sort the vectors by angle
        Collections.sort(vec, Comparator.comparingDouble(v -> Math.atan2((double)v.getyPos(), (double)v.getxPos())));
        
        // Lay them end-to-end
        int x = 0, y = 0;
        int minPolygonX = 0;
        int minPolygonY = 0;
        List<Point> points = new ArrayList<>(numOfVertices);

        for (int i = 0; i < numOfVertices; i++) {
            points.add(new Point(x, y));

            x += vec.get(i).getxPos();
            y += vec.get(i).getyPos();

            minPolygonX = Math.min(minPolygonX, x);
            minPolygonY = Math.min(minPolygonY, y);
        }

        // Move the polygon to the original min and max coordinates
        int xShift = minX - minPolygonX;
        int yShift = minY - minPolygonY;

        for (int i = 0; i < numOfVertices; i++) {
            Point p = points.get(i);
            points.set(i, new Point(p.getxPos() + xShift, p.getyPos() + yShift));
        }
        
        //set vertices with new points
        this.vertices = points;
    }
    
    @Override
    public String toString(){ 
        String s = "";
        int index = 0;
        
        if (vertices.isEmpty() || vertices == null) return "Polygon not Initialized";
        
        Iterator it = vertices.iterator(); 
        while (it.hasNext()) { 
            Point p = (Point) it.next(); 
            s += index + ": (" + p.getxPos() + ", " + p.getyPos() + ") \n";
            index++;
        }
        
        return s; 
    }
    
}
