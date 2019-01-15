package test;

import impl.NaivePriorityQueue;

public class NPQTest extends PriorityQueueTest {

    protected void resetWidgetPopulated() {
        wpq = new NaivePriorityQueue<Widget>(itably, wCompo);
    }

    @Override
    protected void resetMeasEqEmpty(int size) {
         mpq = new NaivePriorityQueue<MeasEq>(size, mCompo);
        
    }

}
