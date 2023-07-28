package avoid.nullChecks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NullCheck1Solution {
    public List<Employee> getEmployees() {
        if (1 == 1) {
            List<Employee> employeeList = new ArrayList<>();
            employeeList.add(new Employee("John"));
            return employeeList;
        } else {
            // return predefined immutable list
            return Collections.emptyList();
        }
    }

    public void printEmployees() {
        List<Employee> employees = getEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }

    }
}
