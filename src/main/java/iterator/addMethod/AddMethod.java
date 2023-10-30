package iterator.addMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class AddMethod {
    public static void main(String[] args) {
        /**
         * change unmodifiable list to ArrayList,
         * so we can add data to it,
         * otherwise it will throw UnsupportedOperationException,
         * while using listIterator.add("E");
         */

        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String item = listIterator.next();
            if ("B".equals(item)) {
                listIterator.add("E");
            }
            System.out.println(item);
//            E Doesn't get printed here, Why?
//            Check AddMethodPrintAll class for Answer

        }
        System.out.println(list); // Outputs: [A, B, E, C]

    }
}
