package iterator;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class HasPreviousAndPrevious {

    /**
     * hasPrevious() and previous():
     * These methods allow you to traverse the list in reverse.
     */

    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C");
        ListIterator<String> listIterator = list.listIterator(list.size()); // Starting from the end
        while (listIterator.hasPrevious()) {
            String item = listIterator.previous();
            System.out.println(item);
        }
    }

}
