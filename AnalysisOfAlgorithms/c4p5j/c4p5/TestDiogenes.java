package c4p5;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class TestDiogenes {

    @Test
    public void testManyLiars() {
        HashSet<Chip> chips = new HashSet<Chip>();
        chips.add(Chip.makeGood());
        for (int i = 0; i < 50; i++) {
            chips.add(Chip.makeLiar());
            chips.add(Chip.makeGood());
        }
        chips.add(Chip.makeGood());
        runAndCheck(chips);
    }
    @Test
    public void testManyLiarsOdd() {
        HashSet<Chip> chips = new HashSet<Chip>();
        chips.add(Chip.makeGood());
        for (int i = 0; i < 50; i++) {
            chips.add(Chip.makeLiar());
            chips.add(Chip.makeGood());
        }
        runAndCheck(chips);
    }
    @Test
    public void testThree() {
        HashSet<Chip> chips = new HashSet<Chip>();
        chips.add(Chip.makeGood());
        chips.add(Chip.makeLiar());
        chips.add(Chip.makeGood());
        runAndCheck(chips);
    }
    @Test
    public void testFive() {
        HashSet<Chip> chips = new HashSet<Chip>();
        chips.add(Chip.makeGood());
        chips.add(Chip.makeLiar());
        chips.add(Chip.makeGood());
        chips.add(Chip.makeLiar());
        chips.add(Chip.makeGood());
        runAndCheck(chips);
    }
    @Test
    public void testOne() {
        HashSet<Chip> chips = new HashSet<Chip>();
        chips.add(Chip.makeGood());
        runAndCheck(chips);
    }
    @Test
    public void testManyJW() {
        HashSet<Chip> chips = new HashSet<Chip>();
        chips.add(Chip.makeGood());
        for (int i = 0; i < 50; i++) {
            chips.add(Chip.makeJW());
            chips.add(Chip.makeGood());
        }
        chips.add(Chip.makeGood());
        runAndCheck(chips);
    }
    @Test
    public void testManyCR() {
        HashSet<Chip> chips = new HashSet<Chip>();
        chips.add(Chip.makeGood());
        for (int i = 0; i < 50; i++) {
            chips.add(Chip.makeCR());
            chips.add(Chip.makeGood());
        }
        chips.add(Chip.makeGood());
        runAndCheck(chips);
    }
    @Test
    public void testManyFC() {
        HashSet<Chip> chips = new HashSet<Chip>();
        chips.add(Chip.makeGood());
        for (int i = 0; i < 50; i++) {
            chips.add(Chip.makeFC());
            chips.add(Chip.makeGood());
        }
        chips.add(Chip.makeGood());
        runAndCheck(chips);
    }
    @Test
    public void testAllBadKinds() {
        HashSet<Chip> chips = new HashSet<Chip>();
        chips.add(Chip.makeGood());
        for (int i = 0; i < 50; i++) {
            chips.add(Chip.makeLiar());
            chips.add(Chip.makeGood());
            chips.add(Chip.makeJW());
            chips.add(Chip.makeGood());
            chips.add(Chip.makeCR());
            chips.add(Chip.makeGood());
            chips.add(Chip.makeFC());
            chips.add(Chip.makeGood());
        }
        runAndCheck(chips);
    }
    @Test
    public void testAllGood() {
        HashSet<Chip> chips = new HashSet<Chip>();
        chips.add(Chip.makeGood());
        for (int i = 0; i < 100; i++) {
            chips.add(Chip.makeGood());
        }
        chips.add(Chip.makeGood());
        runAndCheck(chips);
    }
    private void runAndCheck(HashSet<Chip> chips) {
        Diogenes.diogenes(new HashSet<Chip>(chips));
        for (Chip c : chips)
            assertTrue(c.reveal());
    }
    
}

