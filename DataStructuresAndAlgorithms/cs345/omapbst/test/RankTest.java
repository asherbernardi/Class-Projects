package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class RankTest extends NumKeysTest {

    @Test
    public void presentRanks() {
        reset();
        for (String emperor : romans) 
            testMap.put(emperor, "");
        for (int i = 0; i < romans.length; i++) 
            assertEquals(i, testMap.rank(romans[romanOrder[i]]));
    }
    
    @Test
    public void spuriousRanks() {
        reset();
        for (String emperor : romans) 
            testMap.put(emperor, "");
        assertEquals(0, testMap.rank("Aaron Burr"));
        assertEquals(17, testMap.rank("Vito Corleone"));
        assertEquals(8, testMap.rank("Justinian"));
        assertEquals(3, testMap.rank("Charlemagne"));
    }
   

}
