package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumKeysTest extends RemoveMinTest {

    @Test
    public void emptyNumKeys() {
        reset();
        assertEquals(0, testMap.numKeys());
    }
    
    @Test
    public void singleNumKeys() {
        reset();
        testMap.put(romans[4], "");
        assertEquals(1, testMap.numKeys());
    }

    @Test
    public void populatedNumKeys() {
        reset();
        for (String emperor: romans)
            testMap.put(emperor, "");
        assertEquals(romans.length, testMap.numKeys());
    }

    @Test
    public void numKeysAfterRemove() {
        reset();
        for (String emperor: romans)
            testMap.put(emperor, "");
        int numToRemove = romans.length / 2;
        for (int i = 0; i < numToRemove; i++)
            testMap.remove(romans[romans.length-i-1]);
        assertEquals(romans.length-numToRemove, testMap.numKeys());
        
    }
    
}
