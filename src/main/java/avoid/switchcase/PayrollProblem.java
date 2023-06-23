package avoid.switchcase;

public class PayrollProblem {
    public Money calculatePay(Employee e) throws InvalidEmployeeType {
        switch(e.type) {
            case COMMISSIONED:
                return calculateCommissionedPay(e);
            case HOURLY:
                return calculateHourlyPay(e);
            case SALARIED:
                return calculateSalariedPay(e);
            default:
                throw new InvalidEmployeeType(e.type);
        }
    }
}

/**
 *
 * There are several problems with this function.
 * First, it's large, and when new employees are added, it will grow.
 * Second, it very closely does more than one thing.
 * Third, it violates the Single Responsibility Principle (SRP)
 * Fourth, it violates the Open Closed Principle (OCP), because it must change whenever new types are added.
 * But the worst is that there are unlimited number of functions that will have the same structure
 * For example.,
 * isPayday(Employee e, Date date);
 * deliveryPay(Employee e, Money pay);
 *
 * */
