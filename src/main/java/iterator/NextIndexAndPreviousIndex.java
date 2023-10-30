package iterator;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class NextIndexAndPreviousIndex {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C");
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
            System.out.println("Next index: " + listIterator.nextIndex());
            System.out.println("Previous index: " + listIterator.previousIndex());
        }

    }
}
