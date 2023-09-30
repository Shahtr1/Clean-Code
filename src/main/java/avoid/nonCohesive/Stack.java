package avoid.nonCohesive;

import java.util.LinkedList;
import java.util.List;

public class Stack {

    /**
     * Of the three methods only size() fails to use both the variables
     * Keep functions small, and parameters list short
     */


    private int topOfStack = 0;
    List<Integer> elements = new LinkedList<Integer>();

    public int size() {
        return topOfStack;
    }

    public void push(int element) {
        topOfStack++;
        elements.add(element);
    }

    public int pop() throws Exception {
        if (topOfStack == 0) {
            throw new Exception();
        }
        int element = elements.get(--topOfStack);
        elements.remove(topOfStack);
        return element;
    }
}
