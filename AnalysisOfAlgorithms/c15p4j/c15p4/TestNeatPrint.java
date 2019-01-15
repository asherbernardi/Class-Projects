package c15p4;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.Test;

public class TestNeatPrint {

    /**
     * Load a textfile into a string with no new lines.
     * @param filename The name of the file to read
     * @return The content of that file as one string with no line breaks.
     */
    private static String getText(String filename){
        try {
            Scanner file = new Scanner(new FileInputStream(filename));
            String text = "";
            while (file.hasNext())
                text += file.nextLine();
            file.close();
            return text;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            assert false;
        }
        return null;  // shouldn't happen
    }
    
    /**
     * Compute the penalty (sum of the cubes of the differences between
     * the given line length and the actual number of characters for
     * each line.
     * @param formatedText
     * @param lineLength
     * @return
     */
    private int computePenalty(String formatedText, int lineLength) {
        String[] lines = formatedText.split("\\n");
        int penalty = 0;
        int i = 0;
        for (String line : lines) {
            line = line.trim();
            assert line.length() <= lineLength;
            if (i < lines.length - 1) 
                penalty += cb(lineLength - line.length());
            i++;
        }
        return penalty;
    }
    

    private static int cb(int i) {
        return i * i * i;
    }

    
    @Test
    public void testGettysburg30() {
        String text = getText("gettysburg");
        String formattedText = NeatPrint.neatPrint(text, 30);
        assertEquals(2511, computePenalty(formattedText, 30));
    }
    @Test
    public void testGettysburg80() {
        String text = getText("gettysburg");
        String formattedText = NeatPrint.neatPrint(text, 80);
        assertEquals(329, computePenalty(formattedText, 80));
    }
    @Test
    public void testSimple() {
        String text = "There is a thing here";
        String formattedText = NeatPrint.neatPrint(text, 8);
        assertEquals(1, computePenalty(formattedText, 8));
    }
    @Test
    public void testObvious() {
        String text = "a a a a a a a a a a a a a a a a a a";
        String formattedText = NeatPrint.neatPrint(text, 5);
        assertEquals(0, computePenalty(formattedText, 5));
    }
    @Test
    public void testNewLineEachTime() {
        String text = "Hot Tamales are very hot";
        String formattedText = NeatPrint.neatPrint(text, 7);
        assertEquals(155, computePenalty(formattedText, 7));
    }
    @Test
    public void testNotGreedy() {
        String text = "Jake eats an apple a semester";
        String formattedText = NeatPrint.neatPrint(text, 12);
        assertEquals(35, computePenalty(formattedText, 12));
    }

}
