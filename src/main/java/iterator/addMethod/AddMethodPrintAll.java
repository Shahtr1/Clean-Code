package iterator.addMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


/**
 * ANSWER:
 * when you add an element using ListIterator,
 * it adds the element at the current position (right after the element you just retrieved using next())
 * and then shifts the cursor position past the added element.
 * So, when you call next() after adding "E", it moves to "C".
 */
public class AddMethodPrintAll {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String item = listIterator.next();
            System.out.println(item);
            if ("B".equals(item)) {
                listIterator.add("E");
                listIterator.previous(); // Move the cursor back to 'E' so it's printed on the next iteration.
            }
        }
        System.out.println(list); // Outputs: [A, B, E, C]

    }
}
