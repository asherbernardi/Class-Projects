package test;

import impl.SortedPriorityQueue;

public class SPQTest extends PriorityQueueTest {

    protected void resetWidgetPopulated() {
        wpq = new SortedPriorityQueue<Widget>(itably, wCompo);
    }

    @Override
    protected void resetMeasEqEmpty(int size) {
         mpq = new SortedPriorityQueue<MeasEq>(size, mCompo);
    }

}
