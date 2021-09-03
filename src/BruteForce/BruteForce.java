/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BruteForce;
import GUI.Edge;
import GUI.Point;
import GUI.Polygon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author jcvsa
 */
public class BruteForce {
    
    /**
     * default constructor
     * 
     * empty as we only need the methods
     */
    public BruteForce() { }
    
    /**
     * updates the polygons internalEdge and internalEdgeSum variables
     * 
     * @param p     polygon that is to be updated 
     */
    public void updatePolgyon(Polygon p){ 
        List<Edge> curr = getEfficientEdges(p.getVertices());
        p.setInternalEdges(curr);
        p.setInternalEdgeSum(edgeSum(curr));
    }
    
    /**
     * returns a list of minimum length internal edges based on the convex polygon 
     * points passed through 
     * 
     * @param vertices          convex polygon vertices
     * @return                  list of edge objects
     */
    public List<Edge> getEfficientEdges(List<Point> vertices) {
        return dfs(vertices, 0, vertices.size() - 1);
    }
        
    /**
     * 
     * returns a list of minimum length internal edges based on the convex polygon 
     * points passed through 
     * 
     * @param vertices          convex polygon vertices
     * @param start             first vertices of the polygon
     * @param end               last vertices of the polygon
     * @return                  list of edge objects
     */
    private List<Edge> dfs(List<Point> vertices, int start, int end) { 
        
        /**
         * placeholder for checking if current edge sum
         */
        double sum = Double.MAX_VALUE;   
        
        /**
         * used for the answer returned
         */
        List<Edge> edge = new ArrayList<>();    
        
        /**
         * Base Case: 
         * Return an empty list if current polygon is already a 
         * triangle.
         * If the difference between the end vertices and the start  vertices
         * is less than 3, there can't be any possible tessellations made
         */
        if (end - start < 3)
            return edge; 
        
        for (int k = start + 1; k < end; k++) { 
            
            /**
             * placeholder for storing possible tessellations 
             */
            List<Edge> temp = new ArrayList<>();

            
            /**
             * Add the length of the internal edge 
             * 
             * An internal edge can only be internal if the k vertices is either 
             * not the adjacent vertices of the start or end point of the current
             * tessellation of the polygon.
             */
            if(start + 1 < k) { 
                temp.add(new Edge(new Point(vertices.get(k).getxPos(), vertices.get(k).getyPos()), 
                         new Point(vertices.get(start).getxPos(), vertices.get(start).getyPos())));
            }
            if(end - 1 > k) { 
                temp.add(new Edge(new Point(vertices.get(k).getxPos(), vertices.get(k).getyPos()), 
                        new Point(vertices.get(end).getxPos(), vertices.get(end).getyPos())));
            }
            
            /**
             * calculate left and right side of tessellated polygon
             */
            temp.addAll(dfs(vertices, start, k));
            temp.addAll(dfs(vertices, k, end));
            
            /**
             * Checks if the current edge sum is less than the previous sum 
             * if is less then overwrite the current edge object
             */
            double edgeSum = edgeSum(temp);          
            if (sum > edgeSum){  
                sum = edgeSum;
                edge = temp;
            }
            
        } 
       
        return edge; 
    }
    
    /**
     * Calculates the sum of all the edge object lengths
     * 
     * @param e     A list containing edge objects
     * @return      sum 
     */
    public double edgeSum(List<Edge> e){ 
        double sum = 0; 
        
        sum =
            e.stream()
            .map((curr) -> 
            curr.length()).reduce(sum, (accumulator, _item) -> accumulator + _item);
        
        return sum;
    }
    
    /**
     * For Testing
     */
    public static void main(String[] args) {
        BruteForce bf = new BruteForce();
        List<Point> testList = new ArrayList<>();
//        testList.add(new Point(1,2));
//        testList.add(new Point(1,4));
//        testList.add(new Point(3,5));
//        testList.add(new Point(4,3));
//        testList.add(new Point(3,1));
        
        testList.add(new Point(1,1));
        testList.add(new Point(0,4));
        testList.add(new Point(3,5));
        testList.add(new Point(4,4));
        testList.add(new Point(4,2));
        testList.add(new Point(2,0));
        
        
        List<Edge> e = bf.getEfficientEdges(testList); 
        Iterator it = e.iterator();
        while (it.hasNext()){ 
            Edge curr = (Edge) it.next(); 
            System.out.println(curr.toString());
        } 
    }
}