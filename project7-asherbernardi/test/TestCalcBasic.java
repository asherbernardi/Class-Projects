package test;


import org.junit.Test;

import calc.CalculatorFace;

public class TestCalcBasic extends TestCalcAbs {

    @Test
    public void singleDigit() {
        testSequence("5", new String[] {"5"});
    }
    
    @Test
    public void tripleDigit() {
        testSequence("213", new String[] {"213"});
    }
    
    @Test
    public void decimal() {
        testSequence("12.7", new String[] {"12.7"});
    }
    
    @Test
    public void negativePre() {
        testSequence(CalculatorFace.PLUS_MINUS + "15", new String[] {"-15"});
    }

    @Test
    public void negativePost() {
        testSequence("15" + CalculatorFace.PLUS_MINUS, new String[] {"-15"});
    }

    @Test
    public void addEq() {
        testSequence("1+2=", new String[] {"3", "3.0"});
    }
    
    @Test
    public void addOp() {
        testSequence("1+2+", new String[] {"3", "3.0"});
    }
    
    @Test
    public void addThreeNotFinished() {
        testSequence("1+2+4", new String[] {"4", "4.0"});
    }
    
    @Test
    public void addThreeEq() {
        testSequence("1+2+4=", new String[] {"7", "7.0"});
    }

    @Test
    public void clear() {
        testSequence("1+5C4+9=", new String[] {"13", "13.0"});
    }
    
    @Test
    public void negativeMid() {
        testSequence("1" + CalculatorFace.PLUS_MINUS + "5", new String[] {"-15","-15.0"});
    }    
    
    @Test
    public void oneDigit() {
        testSequence("1", new String[] {"1", "1.0"});
    }
    @Test
    public void twoDigits() {
        testSequence("47", new String[] {"47", "47.0"});
    }
    @Test
    public void threeDigits() {
        testSequence("467", new String[] {"467", "467.0"});
    }
    @Test
    public void decimalTest() {
        testSequence("2.5", new String[] {"2.5"});
    }
    @Test
    public void biggerDecimal() {
        testSequence("14.67", new String[] {"14.67"});
    }
    @Test
    public void firstPlusMinus() {
        testSequence("1+5C4+9=", new String[] {"13", "13.0"});
    }
    @Test
    public void secondPlusMinus() {
        testSequence("4±", new String[] {"-4", "-4.0"});
    }
    @Test
    public void onePlusOneNoEquals() {
        testSequence("±51", new String[] {"-51", "-51.0"});
    }
    @Test
    public void onePlusThreeNoEquals() {
        testSequence("1+1", new String[] {"1", "1.0"});
    }
    @Test
    public void moreAdditionNoEquals() {
        testSequence("1+3", new String[] {"3", "3.0"});
    }
    @Test
    public void onePlusOne() {
        testSequence("27+45", new String[] {"45", "45.0"});
    }
    @Test
    public void addition() {
        testSequence("1+1=", new String[] {"2", "2.0"});
    }
    @Test
    public void onePlusOnePlus() {
        testSequence("1+1+", new String[] {"2", "2.0"});
    }
    @Test
    public void onePlusOnePlusOne() {
        testSequence("1+1+1", new String[] {"1", "1.0"});
    }
    @Test
    public void onePlusOnePlusOneEquals() {
        testSequence("1+1+1=", new String[] {"3", "3.0"});
    }
    @Test
    public void negativeTesting() {
        testSequence("3+5=+3", new String[] {"3", "3.0"});
    }
    @Test
    public void moreNegativeTesting2() {
        testSequence("3+5=+3=", new String[] {"11", "11.0"});
    }
    @Test
    public void decimalAddtion() {
        testSequence("3.5+5=", new String[] {"8.5"});
    }
    @Test
    public void doubleDecimal() {
        testSequence("3.5+2.7=", new String[] {"6.2"});
    }
    @Test
    public void decimalSubtraction() {
        testSequence(".75-1.25=", new String[] {"-0.5"});
    }
    @Test
    public void lotsOfPlusMinus() {
        testSequence("15±+30=", new String[] {"15", "15.0"});
    }
    @Test
    public void MorePlusMinus() {
        testSequence("1±5+30=", new String[] {"15", "15.0"});
    }
    @Test
    public void division() {
        testSequence("16/4=", new String[] {"4", "4.0"});
    }
    @Test
    public void infiniteDivision() {
        testSequence("16/6=", new String[] {"2.6666666666667"});
    }
    @Test
    public void multiplication() {
        testSequence("3.5*8=", new String[] {"28", "28.0"});
    }
    @Test
    public void lotsOfStuff() {
        testSequence("3.7+C2.9+5=", new String[] {"7.9"});
    }
    @Test
    public void oneOverNine() {
        testSequence("1/9=", new String[] {"0.1111111111111"});
    }    
    @Test
    public void randomStuff() {
        testSequence("3.5+2.7=.75-1.25=", new String[] {"-0.5"});
    }
    public void otherStuff() {
        testSequence("30±+10= +10.5=", new String[] {"-9.5"});
    }
}
