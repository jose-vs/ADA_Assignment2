/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BruteForce;
import GUI.Point;

/**
 *
 * @author jcvsa
 */
public class BruteForce {
    public static double bf_tesselation(Point[] vertices) {
        
        return dfs(vertices, 0, vertices.length - 1); 
    }
    
    private static double dfs(Point[] vertices, int start, int end) { 
        double ans = Double.MAX_VALUE; 
        
        if (end - start < 3)
            return 0; 
        
        for (int k = start + 1; k < end; k++) { 
            
            double internalEdgeSum = 0; 
            if(start + 1 < k) { 
                internalEdgeSum += Math.sqrt((
                    Math.pow((vertices[k].getxPos() - vertices[start].getxPos()), 2) +
                    Math.pow((vertices[k].getyPos() - vertices[start].getyPos()), 2)));
            }
            if(end - 1 > k) { 
                internalEdgeSum += Math.sqrt((
                    Math.pow((vertices[k].getxPos() - vertices[end].getxPos()), 2) +
                    Math.pow((vertices[k].getyPos() - vertices[end].getyPos()), 2)));
            }
            
            ans = Math.min(ans, internalEdgeSum + dfs(vertices, start, k) + dfs(vertices, k, end));

        }
        
        return ans; 
    }
}