package test;

import static org.junit.Assert.*;

import org.junit.Test;

import impl.RecursiveBSTMap;

public class MinTest extends MapRemoveTest {

    protected void reset() {
        testMap = new RecursiveBSTMap<String, String>();
    }

    @Test
    public void emptyMin() {
        reset();
        assertEquals(null, testMap.min());
    }

    @Test
    public void singleMin() {
        reset();
        testMap.put(romans[4], "");
        assertEquals(romans[4], testMap.min());
    }
    
    @Test
    public void populatedMin() {
        reset();
        for (String emperor : romans) 
            testMap.put(emperor, "");
        assertEquals("Antoninus Pius", testMap.min());
    }
    
    @Test
    public void minAfterRemoves() {
        reset();
        for (String emperor : romans) 
            testMap.put(emperor, "");
        testMap.remove("Antoninus Pius");
        assertEquals("Augustus", testMap.min());
        testMap.remove("Augustus");
        assertEquals("Caligula", testMap.min());
        testMap.remove("Tiberius");
        assertEquals("Caligula", testMap.min());
        testMap.remove("Caligula");
        assertEquals("Claudius", testMap.min());
      
    }
    
    
    
}
