/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BruteForce;
import GUI.Point;
import java.util.List;

/**
 * 
 * @author jcvsa
 */
public class BruteForce {
    
    public static double bf_tessellation(List<Point> vertices) {
        
        return dfs(vertices, 0, vertices.size() - 1); 
        
    }
    
    private static double dfs(List<Point> vertices, int start, int end) { 
        
        double ans = Double.MAX_VALUE; 
        
        /**
         * Base Case: 
         * Return 0 if current polygon is already a 
         * triangle.
         * If the difference between the end vertices and the start  vertices
         * is less than 3, there can't be any possible tessellations made
         */
        if (end - start < 3)
            return 0; 
        
        for (int k = start + 1; k < end; k++) { 
            double internalEdgeSum = 0; 
            
            /**
             * Add the length of the internal edge 
             * 
             * An internal edge can only be internal if the k vertices is either 
             * not the adjacent vertices of the start or end point of the current
             * tessellation of the polygon.
             * 
             * TODO: 
             * Save points so we can show it in the GUI
             */
            if(start + 1 < k) { 
                internalEdgeSum += Math.sqrt((
                    Math.pow((vertices.get(k).getxPos() - vertices.get(start).getxPos()), 2) +
                    Math.pow((vertices.get(k).getyPos() - vertices.get(start).getyPos()), 2)));
//                System.out.print("V1: ("+vertices.get(start).getxPos()+", "+vertices.get(start).getyPos()+") ");
//                System.out.println("V2: ("+vertices.get(k).getxPos()+", "+vertices.get(k).getyPos()+")");

            }
            if(end - 1 > k) { 
                internalEdgeSum += Math.sqrt((
                    Math.pow((vertices.get(k).getxPos() - vertices.get(end).getxPos()), 2) +
                    Math.pow((vertices.get(k).getyPos() - vertices.get(end).getyPos()), 2)));
//                System.out.print("V1: ("+vertices.get(end).getxPos()+", "+vertices.get(end).getyPos()+") ");
//                System.out.println("V2: ("+vertices.get(k).getxPos()+", "+vertices.get(k).getyPos()+")");
            }
            
            /**
             * compare each sum and save the minimum to ans
             */
            ans = Math.min(ans, internalEdgeSum + dfs(vertices, start, k) + dfs(vertices, k, end));

        }
        
        /**
         * Maybe return a list of points instead?
         * so we can use the list for calculating the sum and plotting onto 
         * an interface
         */
        
        return ans; 
    }
}