package test;


import org.junit.Test;

import calc.CalculatorFace;

public class TestCalcRPN extends TestCalcAbs {

    @Test
    public void oneOp() {
        testSequence("1.1+", new String[] {"2"});
    }

    @Test
    public void oneOp2() {
        testSequence("1.3+", new String[] {"4"});
    }

    @Test
    public void trailingDot() {
        testSequence("1.3.+", new String[] {"4"});
    }
    
    @Test
    public void stackedOps() {
        testSequence("5.2.8+*", new String[] {"50"});
    }
    
    @Test
    public void stackedOps2() {
        testSequence("5.8.3-*", new String[] {"25"});
    }
    
    @Test
    public void sequentialOps() {
        testSequence("5.2+8*", new String[] {"56"});
    }
    
    @Test
    public void sequentialOps2() {
        testSequence("15.2+8*", new String[] {"136"});
    }
    
    @Test
    public void division() {
        testSequence("16.9+5/", new String[] {"5"});
    }
    
    @Test
    public void divisionTruncate() {
        testSequence("16.12+5/", new String[] {"5"});
    }
    
    @Test
    public void inlineClear() {
        testSequence("5C12.3-", new String[] {"9"});
    }

    @Test
    public void inlineClear2() {
        testSequence("5.1.6.3+C2.1+", new String[] {"3"});
    }

    @Test
    public void fullReset() {
        testSequence("5.1.6.3+C2+1", new String[] {"21"});
    }

    @Test
    public void opFirst() {
        testSequence("+5*2.3+", new String[] {"55"});
    }
}
