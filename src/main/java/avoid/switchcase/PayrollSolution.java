package avoid.switchcase;

public abstract class Employee{
    public abstract boolean isPayday();
    public abstract Money calculatePay();
    public abstract void deliverPay(Money pay);
}

public interface EmployeeFactory{
    public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType;
}

public class EmployeeFactoryImpl implements EmployeeFactory {
    public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType{
        switch(r.type){
            case COMMISSIONED:
                return new CommissionedEmployee(r);
            case HOURLY:
                return new HourlyEmployee(r);
            case SALARIED:
                return new SalariedEmployee(r);
            default:
                throw new InvalidEmployeeType(r.type);
        }
    }
}

/**
 *
 * The solution to PayrollProblem.java is to bury the switch statement in the basement of an ABSTRACT FACTORY,
 * and never let anyone see it.
 * The factory will use the switch statement to create instances of derivatives of Employee
 * and the functions, such as calculatePay, isPayday and deliverPay,
 * will be dispatched polymorphically through the Employee interface
 *
 * SWITCH statement can be tolerated if they appear only once, and are hidden behind an inheritance relationship
 * so that rest of the system cant see them
 *
 * */
