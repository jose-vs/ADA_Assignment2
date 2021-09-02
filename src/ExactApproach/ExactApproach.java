/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExactApproach;

import GUI.Point;
import java.util.List;

/**
 *
 * @author jcvsa
 */
public class ExactApproach {
    
     public static double ea_tessellation(List<Point> vertices) {
        
        int n = vertices.size();
        double[][] memo = new double[n][n];
        
        return dfs(vertices, 0, n - 1, memo); 
        
    }
    
    private static double dfs(List<Point> vertices, int start, int end, double[][] memo) { 
        
        double ans = Double.MAX_VALUE; 
        
        /**
         * if the current memo has been visited before, 
         * return the value in the memo 
         */
        if (memo[start][end] != 0) { 
            return memo[start][end];
        }
        
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
            }
            if(end - 1 > k) { 
                internalEdgeSum += Math.sqrt((
                    Math.pow((vertices.get(k).getxPos() - vertices.get(end).getxPos()), 2) +
                    Math.pow((vertices.get(k).getyPos() - vertices.get(end).getyPos()), 2)));
            }
            
            /**
             * compare each sum and save the minimum to ans
             */
            ans = Math.min(ans, internalEdgeSum + dfs(vertices, start, k, memo) + dfs(vertices, k, end, memo));

        }
        
        
        return memo[start][end] = ans; 
    }
}
