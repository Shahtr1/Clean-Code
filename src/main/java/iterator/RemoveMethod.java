package iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class RemoveMethod {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String item = listIterator.next();
            if ("B".equals(item)) {
                listIterator.remove();
            }
        }
        System.out.println(list); // Outputs: [A, C]
    }

}
