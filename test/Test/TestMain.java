/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import static BruteForce.BruteForce.*;
import static ExactApproach.ExactApproach.*; 
import GUI.Point;
import GUI.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcvsa
 */
public class TestMain {
    
//    public static double bf_tesselate(int[] values) {
//        return dfs(values, 0, values.length - 1);
//    }
//    
//    private static double dfs(int[] values, int start, int end) {
//        double ans = Double.MAX_VALUE;
//        
//        if (end - start < 3) {
//            return 0;
//        }
//        for (int k = start + 1; k < end; k++) {
//            
//            //only add the sum off internal edges
//            double internalEdgeSum = 0; 
//            if(start + 1 < k) 
//                internalEdgeSum += values[start] * values[k];
//            if(end - 1 > k) 
//                internalEdgeSum += values[end] * values[k];
//            
//            ans = Math.min(ans, internalEdgeSum + dfs(values, start, k) + dfs(values, k, end));
//        }
//        return ans;
//    }
    
 
    public static void main(String[] args) {
        
        List<Point> testList = new ArrayList<>();
        testList.add(new Point(1,2));
        testList.add(new Point(1,4));
        testList.add(new Point(3,5));
        testList.add(new Point(3,3));
        testList.add(new Point(3,1));
        
//        System.out.println(bf_tessellation(testList));
        
        Polygon testPolygon = new Polygon(100,10,10);
        System.out.println(testPolygon.toString());
        //System.out.println(bf_tessellation(testPolygon.getVertices()));
        System.out.println(ea_tessellation(testPolygon.getVertices()));
    }
}
