package q5string;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class BuildTrieTest {

    @Test
    public void sample() {
       String[] data = new String[] {"ANN", "ANNA", "ANNIKA", "BETH", "BETHANY", "CARL"};
       TrieSet testTrie = new TrieSet(data, 0, data.length, 0); 
       assertEquals(6, testTrie.size());
       for (String s : data) 
           assertTrue(testTrie.contains(s));
       assertFalse(testTrie.contains("ANNE"));
    }
    
    @Test
    public void singleton() {
        
        String[] data = new String[] {"BETH"};
        TrieSet testTrie = new TrieSet(data, 0, data.length, 0); 
        assertEquals(1, testTrie.size());
        assertTrue(testTrie.contains("BETH"));
        assertFalse(testTrie.contains("ANNE"));
  }

    @Test
    public void noSharedPaths() {
        String[] data = new String[] {"ANN", "BETH", "CARL", "DAHLIA", "EVERGREEN"};
        TrieSet testTrie = new TrieSet(data, 0, data.length, 0); 
        assertEquals(5, testTrie.size());
        for (String s : data) 
            assertTrue(testTrie.contains(s));
        assertFalse(testTrie.contains("ANNE"));
     }   
    
    @Test
    public void deepShare() {
        String[] data = new String[] {"CONSTANS", "CONSTANZE", "CONSTANTINE", "CONSTANTINUS", "CONSTANTIUS", "CONSTANTINOPLE", "CONSTANT", "CONSTANCE", "CONSTANTS", "CONSUBSTANIATION"};
        Arrays.sort(data);
        TrieSet testTrie = new TrieSet(data, 0, data.length, 0); 
        assertEquals(data.length, testTrie.size());
        for (String s : data) 
            assertTrue(testTrie.contains(s));
        assertFalse(testTrie.contains("ANNE"));
        
    }
    
    @Test
    public void stress() {
        String[] data = new String[] {
                "CONSTANCE", "HELEN", "JUSTIN", "JON", "JOHN", "CONSTANTIUS",
                "HELENA", "HELLA", "JONATHAN", "CONSTANTINE", "JUSTINIAN",
                "CLEMENS", "HEROD", "ANN", "JOHANA", "JUSTINIANUS",
                "CONSTANTINUS", "ELLEN", "ELAINE", "ELLIE", "ELLA",
                "HERODIAS", "HERODIAN", "JOHNA", "ANNALISE", "ANNETTE",
                "ANNIKA", "CLEMENT", "CAESAR", "AUGUSTUS", "ANNE", "ANNMARIE",
                "JUSTINMARTYR", "JOHNBAPTIST", "ANNIE", "ANNA", "CAESARION",
                "CAESAREA", "AUGUSTA", "ANNMARGARET", "AUGUST", "AUGUSTINE",
                "CLEMENZO", "JONATHANIAN", "CAESARINA", "AUGUSTINUS", "CONSTANS",
                "HELENE"  
        };
        Arrays.sort(data);
        TrieSet testTrie = new TrieSet(data, 0, data.length, 0); 
        assertEquals(data.length, testTrie.size());
        for (String s : data) 
            assertTrue(testTrie.contains(s));
        assertFalse(testTrie.contains("BOB"));
    }

    @Test
    public void n() {
        String[] keys = new String[] {"AX", "AY", "BX"};
        TrieSet testTrie = new TrieSet(keys, 0, keys.length, 0);
        assertTrue(testTrie.contains("AX"));
        assertTrue(testTrie.contains("AY"));
        assertTrue(testTrie.contains("BX"));
       
    }
}
