package iterator;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class SetMethod {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C");

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String item = listIterator.next();
            if ("B".equals(item)) {
                listIterator.set("D");
            }
        }
        System.out.println(list); // Outputs: [A, D, C]
    }

}
