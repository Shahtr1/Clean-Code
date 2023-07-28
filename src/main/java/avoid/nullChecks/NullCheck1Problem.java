package avoid.nullChecks;

import java.util.ArrayList;
import java.util.List;

class Employee {
    String name;

    public Employee(String name) {
        this.name = name;
    }

}

public class NullCheck1Problem {
    public List<Employee> getEmployees() {
        if (1 == 1) {
            List<Employee> employeeList = new ArrayList<>();
            employeeList.add(new Employee("John"));
            return employeeList;
        } else {
            return null;
        }
    }

    public void printEmployees() {
        List<Employee> employees = getEmployees();
        if (employees != null) {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }
}
