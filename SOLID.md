# SOLID Principles Guide

This guide covers the SOLID principles, which are a set of design principles that can help you build robust, maintainable, and scalable software in object-oriented programming languages like Java, Python, and C#.

### Original post by Vikas Rajput
You can find the original post [here](https://twitter.com/vikasrajputin/status/1715235144733520172?s=46).

## Contents

- [Single Responsibility Principle (SRP)](#single-responsibility-principle-srp)
- [Open/Closed Principle (OCP)](#openclosed-principle-ocp)
- [Liskov Substitution Principle (LSP)](#liskov-substitution-principle-lsp)
- [Interface Segregation Principle (ISP)](#interface-segregation-principle-isp)
- [Dependency Inversion Principle (DIP)](#dependency-inversion-principle-dip)

## S - Single Responsibility Principle (SRP)

A class should have only one reason to change, meaning that a class should have only one job or responsibility.

### Bad Implementation

```java
public class Employee {
    private String fullName;
    private String dateOfJoining;
    private String annualSalaryPackage;

    // business logic
    public long calculateEmployeeSalary(Employee emp) { ... }
    public long calculateEmployeeLeaves(Employee emp) { ... }
    public long calculateTaxOnSalary(Employee emp) { ... }

    // data persistence logic
    public Employee saveEmployee(Employee emp) { ... }
    public Employee updateEmployee(Employee emp) { ... }
}
```
In this example, the Employee class has multiple responsibilities like managing personal details, business logic, and data persistence.

### Good Implementation

Splitting the Employee class into multiple classes as per their specific responsibility.

```java
public class Employee {
    private String fullName;
    private String dateOfJoining;
    private String annualSalaryPackage;

    // Standard getters and setters
}

public class EmployeeService {
    public long calculateEmployeeSalary(Employee emp) { ... }
    public long calculateEmployeeLeaves(Employee emp) { ... }
    public long calculateTaxOnSalary(Employee emp) { ... }
}

public class EmployeeDAO {
    public Employee saveEmployee(Employee emp) { ... }
    public Employee updateEmployee(Employee emp) { ... }
}

```
## O - Open/Closed Principle

Software entities should be open for extension, but closed for modification. This means that the behavior of a module can be extended without modifying its source code.

### Bad Implementation

```java
public class EmployeeSalary {
    public Long calculateSalary(Employee emp) {
        Long salary = null;
        if (emp.getType().equals("PERMANENT")) {
            salary = (totalWorkingDay * basicPay) + getCompanyBenefits() + getBonus();
        } else if (emp.getType().equals("CONTRACT")) {
            salary = (totalWorkingDay * basicPay);
        }
        return salary;
    }
}

```
In this example, adding a new type of employee would require modifying the calculateSalary method.

### Good Implementation

Using polymorphism to extend the class behavior without modifying it.

```java
public abstract class Employee {
    // ...
    public abstract Long calculateSalary();
}

public class PermanentEmployee extends Employee {
    // ...
    public Long calculateSalary() {
        // Calculation for permanent employee
    }
}

public class ContractEmployee extends Employee {
    // ...
    public Long calculateSalary() {
        // Calculation for contract employee
    }
}

```
In this good implementation, if a new type of employee is added, we simply create a new class that extends Employee.

## L - Liskov Substitution Principle

Child Classes should be replaceable with Parent Classes without breaking the behavior of our code.

### Bad Implementation

Below, TeslaToyCar extends Car but does not support fuel() method as its toy. That's why it's violating the LS principle.

In our code where ever we've used Car, we can't substitute it directly with TeslaToyCar because fuel() will throw Exception.

```java
// Parent class
public class Car {
    public void fuel() { ... }
    public void wheels() { ... }
    public void run() { ... }
}

// Poorly inherited class, violating LSP
public class TeslaToyCar extends Car {
    @Override
    public void fuel() {
        throw new IllegalStateException("Not Supported");
    }

    @Override
    public void run() { ... }

    @Override
    public void wheels() { ... }
}

// Correctly inherited class, adhering to LSP
public class TeslaRealCar extends Car {
    @Override
    public void fuel() { ... }

    @Override
    public void run() { ... }

    @Override
    public void wheels() { ... }
}

```

### Good Implementation
```java
// Parent class
public class Car {
    public void wheels() { ... }
    public void run() { ... }
}

// Intermediate class to support fuel
public class RealCar extends Car {
    public void fuel() { ... }
}

// Child class of Car that supports run and wheels but not fuel
public class TeslaToyCar extends Car {
    @Override
    public void run() { ... }

    @Override
    public void wheels() { ... }
}

// Child class of RealCar that supports fuel, run, and wheels
public class TeslaRealCar extends RealCar {
    @Override
    public void fuel() { ... }

    @Override
    public void run() { ... }

    @Override
    public void wheels() { ... }
}

```

Creating new subclass RealCar from parent Car class, so that RealCar can support fuel() and Car can support generic functions support by any type of car.

As shown below, TeslaToyCar and TeslaRealCar can be substituted with their respective Parent class.

## I - Interface Segregation:

Interface should only have methods that are applicable to all child classes.

If an interface contains a method applicable to some child classes then we need to force the rest to provide dummy implementation.

Move such methods to a new interface.

### Bad Implementation

Vehicle interface contains the fly() method which is not supported by all vehicles i.e. Bus, Car, etc. Hence they've to forcefully provide a dummy implementation.

It violates the Interface Segregation principle as shown below:

```java
// Interface with methods not necessarily applicable to all vehicles
public interface Vehicle {
    void accelerate();
    void applyBrakes();
    void fly();
}

// Bus class that cannot implement 'fly' meaningfully
public class Bus implements Vehicle {
    @Override
    public void accelerate() {
        // implementation for accelerate
    }

    @Override
    public void applyBrakes() {
        // implementation for applyBrakes
    }

    @Override
    public void fly() {
        // Dummy implementation, as Bus cannot fly
    }
}

// Aeroplane class which can implement all methods
public class Aeroplane implements Vehicle {
    @Override
    public void accelerate() {
        // implementation for accelerate
    }

    @Override
    public void applyBrakes() {
        // implementation for applyBrakes
    }

    @Override
    public void fly() {
        // implementation for fly
    }
}

```

### Good Implementation:

Pulling out fly() method into new Flyable interface solves the issue.

Now, Vehicle interface contains methods supported by all Vehicles.

And, Aeroplane implements both Vehicle and Flyable interface as it can fly too.

```java
// Interface segregation applied by splitting into two interfaces
public interface Vehicle {
    void accelerate();
    void applyBrakes();
}

public interface Flyable {
    void fly();
}

// Bus implements only Vehicle as it doesn't support fly
public class Bus implements Vehicle {
    @Override
    public void accelerate() {
        // Bus-specific acceleration logic
    }

    @Override
    public void applyBrakes() {
        // Bus-specific brake logic
    }
}

// Aeroplane implements both Vehicle and Flyable
public class Aeroplane implements Vehicle, Flyable {
    @Override
    public void accelerate() {
        // Aeroplane-specific acceleration logic
    }

    @Override
    public void applyBrakes() {
        // Aeroplane-specific brake logic
    }

    @Override
    public void fly() {
        // Aeroplane-specific flying logic
    }
}


```

## D - Dependency Inversion

Class should depend on abstractions (interface and abstract class) instead of concrete implementations.

It makes our classes de-coupled with each other.

If implementation changes then the class referring to it via abstraction won't change.

### Bad Implementation

We've got a Service class, in which we've directly referenced concrete class(SQLRepository).

Issue: Our class is now tightly coupled with SQLRepository, in future if we need to start supporting NoSQLRepository then we need to change Service class.

```java
// Classes representing different storage repositories without a common interface
class SQLRepository {
    public void save() {
        // SQL save logic
    }
}

class NoSQLRepository {
    public void save() {
        // NoSQL save logic
    }
}

// Service class that is tightly coupled to SQLRepository
public class Service {
    private SQLRepository repository = new SQLRepository();

    public void save() {
        repository.save();
    }
}

```

### Good Implementation

Create a parent interface Repository and SQL and NoSQL Repository implements it.

Service class refers to Repository interface, in future if we need to support NoSQL then simply need to pass its instance in constructor without changing Service class.

```java
// Interface to abstract the details of the data access layer
interface Repository {
    void save();
}

// SQLRepository now implements Repository
class SQLRepository implements Repository {
    @Override
    public void save() {
        // SQL save logic
    }
}

// NoSQLRepository now implements Repository
class NoSQLRepository implements Repository {
    @Override
    public void save() {
        // NoSQL save logic
    }
}

// Service is now dependent on the Repository abstraction, not the concrete implementations
public class Service {
    private Repository repository;

    // Constructor injection is used for dependency inversion
    public Service(Repository repository) {
        this.repository = repository;
    }

    public void save() {
        repository.save();
    }
}

```
## Thanks for reading!