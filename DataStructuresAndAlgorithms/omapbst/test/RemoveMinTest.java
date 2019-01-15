package test;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.OrderedMap;

public class RemoveMinTest extends MinTest {

    @Test
    public void emptyRemoveMin() {
        reset();
        testMap.removeMin();
        assertEquals(null, testMap.min());
    }

    @Test
    public void singletonRemoveMin() {
        reset();
        testMap.put(romans[3], "");
        testMap.removeMin();
        assertFalse(testMap.containsKey(romans[3]));
        assertEquals(null, testMap.min());
    }

    protected int[] romanOrder = { 14, 0, 2, 3, 16, 10, 5, 13, 15, 4, 11, 6, 1, 9, 12, 8, 7};
    
    @Test
    public void polulatedRemoveMin() {
        reset();
        for (String emperor : romans) 
            testMap.put(emperor, "");
        boolean[] removed = new boolean[romans.length];
        for (int i = 0; i < romans.length; i++) {
            testMap.removeMin();
            removed[romanOrder[i]] = true;
            checkRemoved(testMap, removed);
            if (i == romans.length - 1)
                assertEquals(null, testMap.min());
            else
                assertEquals(romans[romanOrder[i+1]], testMap.min());
        }
        
    }

    private void checkRemoved(OrderedMap<String, String> testMap, boolean[] removed) {
        for (int i = 0; i < romans.length; i++) {
            assertEquals(removed[i], !testMap.containsKey(romans[i]));
        }
    }
    
}
