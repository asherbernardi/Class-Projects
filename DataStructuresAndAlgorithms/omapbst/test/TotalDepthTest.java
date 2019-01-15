package test;

import static org.junit.Assert.*;

import org.junit.Test;

import impl.RecursiveBSTMap;

public class TotalDepthTest {

    public void testTD(int[] keys, int totalDepth) {
        RecursiveBSTMap<Integer, String> depthTestMap = 
                new RecursiveBSTMap<Integer,String>();
        for (int x : keys) depthTestMap.put(x, "");
        assertEquals(totalDepth, depthTestMap.totalDepth());
    }
    
    @Test
    public void worst() {
        testTD(new int[]{6,0,5,1,4,2,3}, 21);
    }
    
    @Test
    public void a() {
        testTD(new int[]{0,3,5,2,6,1,4}, 14);
    }

    @Test
    public void b() {
        testTD(new int[]{4,2,5,3,0,1,6}, 11);
    }

    @Test
    public void c() {
        testTD(new int[]{1,6,5,2,4,3,0}, 16);
    }
    
    @Test
    public void d() {
        testTD(new int[]{1,2,5,4,3,0,6}, 14);
    }

    @Test
    public void best() {
        testTD(new int[]{3,1,5,0,2,4,6}, 10);
    }

    
    
}
