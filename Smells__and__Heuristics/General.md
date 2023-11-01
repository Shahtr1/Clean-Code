# General

## G1: Multiple Languages in One Source File

- Avoid mixing languages in a single source file.
- Aim for each file to contain only one language to avoid confusion.

## G2: Obvious Behavior Is Unimplemented

- Implement functionalities that users expect.
- Helps in maintaining trust in the software.

## G3: Incorrect Behavior at the Boundaries

- Ensure code behaves correctly, especially at boundaries and edge cases.
- Dont rely on intuitions.

## G3: Incorrect Behavior at the Boundaries

It's vital for code to behave correctly. Often, the complexity of correct behavior is underestimated. Developers tend to
write functions based on what they think will work, relying on intuition rather than ensuring that the code works for
all corner and boundary cases.

**Key Points**:

- There's no substitute for due diligence.
- Each boundary condition and every corner case represents potential pitfalls for algorithms.
- Avoid solely relying on intuition.
- For every boundary condition, make sure to write a test.

## G4: Overridden Safeties

The dangers of overriding safeties can be likened to the Chernobyl disaster where the plant manager bypassed safety
mechanisms, leading to dire consequences. Similarly, in software, overriding safeties such as `serialVersionUID` or
ignoring compiler warnings can be risky.

**Key Points**:

- It's dangerous to override safeties.
- Manual control over `serialVersionUID` or turning off compiler warnings can lead to unpredictable behaviors.
- Consider the risks; failing tests and telling yourself to address them later can have serious implications.

## G5: Duplication

Duplication in code is a serious issue that is emphasized by many software design experts. The DRY (Don't Repeat
Yourself) principle, coined as "Once, and only once" is a core principle of Extreme Programming. Repetitive code
indicates a missed opportunity for abstraction.

**Key Points**:

- DRY (Don't Repeat Yourself) is fundamental to software design.
- Every instance of code duplication points to a potential abstraction opportunity.
- Avoiding duplication enhances code readability and allows other developers to utilize the abstractions, improving the
  overall codebase.
- A more subtle form is the `switch/case` or `if/else` chain again and again in various modules, always testing for the
  same set of conditions. These should be replaced with polymorphism.

## G6: Code at Wrong Level of Abstraction

When creating abstractions, it's crucial to differentiate higher-level general concepts from the lower-level detailed
concepts. This involves creating abstract classes to hold the lower-level concepts and ensuring a complete separation
between the two.

**Key Points**:

- Ensure the separation is complete: lower level concepts shouldn't be present in higher level abstractions and vice
  versa.
- The base class should remain unaware of constants, variables, or utility functions related only to the detailed
  implementation.
- Source files, components, and modules should be designed with this separation in mind, ensuring that concepts at
  different levels aren't mixed.

## G7: Base Classes Depending on Their Derivatives

The partitioning of concepts into base and derivative classes is done to keep higher-level base class concepts
independent of the lower-level derivative class concepts. When base classes reference their derivatives, it's usually a
sign of a problem.

**Key Points**:

- Base classes should ideally not know anything about their derivatives.
- Exceptions exist, like when the base class needs to select between derivatives.
- Tightly coupling base and derivative classes can lead to deployment challenges. It's preferable to deploy them in
  separate jar files.

## G8: Vertical Separation

It's important for code readability and maintenance to define variables and functions close to their usage. This ensures
that developers don't have to scroll or search extensively to understand the scope and use of a particular variable or
function.

**Key Points**:

- Local variables should have a small vertical scope and be declared just above their first usage.
- While private functions belong to the scope of the whole class, limit the vertical distance between their definitions
  and invocations. Ideally, finding a private function should be straightforward by scanning downwards from its first
  invocation.

## G9: Inconsistency

Consistency is paramount in coding. When a certain convention or style is chosen, it should be applied uniformly across
the codebase.

**Key Points**:

- Always follow the principle of least surprise.
- Ensure you remain consistent with the conventions you choose.
- For instance, if you name a variable `response` to hold an `HttpServletResponse`, use the same variable name
  consistently in other functions with similar objectives.
- Method naming should also be consistent. If you have a method named `processVerificationRequest`, you might name
  another method `processDeletionRequest` to maintain a clear and consistent naming pattern.

## G10: Feature Envy

Feature Envy is a code smell identified by Martin Fowler. It refers to the situation where one class seems to be more
interested in the functions and data of another class, rather than focusing on its own responsibilities.

**Characteristics**:

- A method that accesses the data of another class more than its own data can be a sign of this smell.
- This situation may indicate that the method may be more appropriate in the class of the data it's manipulating.

**Example**:

```java
public class HourlyPayCalculator {
    public Money calculateWeeklyPay(HourlyEmployee e) {
        int tenthRate = e.getTenthRate().getPennies();
        int tenthsWorked = e.gettenthsWorked();
        int straightTime = Math.min(400, tenthsWorked);
        int overtime = Math.max(0, tenthsWorked - straightTime);
    }
}
```

- The `calculateWeeklyPay` method reaches into the `HourlyEmployee` object to get the data on which it operates. It
  envies the scope of `HourlyEmployee`. It `wishes` that it could be inside `HourlyEmployee`.

However, there are times when moving a method might expose internal workings of a class, leading to other problems.

**Illustrative Case**:

```java
public class HourlyEmployeeReport {
    private HourlyEmployee employee;

    public HourlyEmployeeReport(HourlyEmployee e) {
        this.employee = e;
    }

    String reportHours() {
        return String.format(
                "Name: %s\tHours: %.1d.%.1d\n",
                employee.getName(),
                employee.getTenthsWorked() / 10,
                employee.getTenthsWorked() % 10
        );
    }
}

```

In the provided `HourlyEmployeeReport` class, the `reportHours` method seems to "envy" the `HourlyEmployee` class. While
it may seem intuitive to move the formatting logic of `reportHours` into the `HourlyEmployee` class, doing so would
make `HourlyEmployee` aware of the report format. This could violate several principles of object-oriented design, such
as encapsulation.

**Conclusion**:
Feature Envy can be a signal to rethink class responsibilities. However, it's crucial to weigh the trade-offs and
consider the broader implications on the system's design before making changes.
