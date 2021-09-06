/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import GUI.Point;
import GUI.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcvsa
 */
public class TestMain {
    
    public static void main(String[] args) {
        
        List<Point> testList = new ArrayList<>();
        testList.add(new Point(1,2));
        testList.add(new Point(1,4));
        testList.add(new Point(3,5));
        testList.add(new Point(4,3));
        testList.add(new Point(3,1));
        
//        System.out.println(bf_tessellation(testList));
        
        Polygon testPolygon = new Polygon(17,800,600);
        System.out.println(testPolygon.toString());
//        //System.out.println(bf_tessellation(testPolygon.getVertices()));
//        System.out.println(ea_tessellation(testPolygon.getVertices()));
    }
}