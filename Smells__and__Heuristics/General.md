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

## G11: Inappropriate Static

`Math.max(double a, double b)` is an example of a good static method. It doesn't operate on a single instance, and it
would be silly to have to say `new Math().max(a, b)` or even `a.max(b)`. Since all the data this method uses come from
its two arguments and not from any "owning" instance. Hence, there's almost no chance that we'd want `Math.max` to be
polymorphic.

Sometimes, though, we come across static functions that should not be static. For instance, consider:

```java
HourlyPayCalculator.calculatePay(employee,overtimeRate);
```

This seems like a reasonable static function, but it doesn't operate on any particular object and gets all its data from
its arguments.
However, there's a reasonable chance that we'd want this function to be `polymorphic`.

For example, we might want to implement different algorithms for calculating hourly pay, like
`OvertimeHourlyPayCalculator` and `StraightTimeHourlyPayCalculator`. In such cases, the function shouldn't be static but
should instead be a non-static function of Employee.

## G12: Understand the Algorithm

Programming is often an exploration. You think you know the right algorithm for something but then you fiddle with it,
poking at it to mak eit work, but it only works for the test case you have in mind. So you should understand it
completely.

## G13: Make Logical Dependencies Physical

 ```java
public class HourlyReporter {
    private HourlyReportFormatter formatter;
    private List<LineItem> page;
    private static final int PAGE_SIZE = 55;

    public HourlyReporter(HourlyReportFormatter formatter) {
        this.formatter = formatter;
        this.page = new ArrayList<LineItem>();
    }

    public void generateReport(List<HourlyEmployee> employees) {
        for (HourlyEmployee e : employees) {
            addLineItemToPage(e);
            if (page.size() == PAGE_SIZE) {
                printAndClearItemList();
            }
        }

        if (page.size() > 0) {
            printAndClearItemList();
        }
    }

    private void printAndClearItemList() {
        formatter.format(page);
        page.clear();
    }

    private void addLineItemToPage(HourlyEmployee e) {
        LineItem item = new LineItem();
        item.name = e.getName();
        item.hours = e.getTenthsWorked() / 10;
        item.tenths = e.getTenthsWorked() % 10;
        page.add(item);
    }

    public class LineItem {
        public String name;
        public int hours;
        public int tenths;
    }
}

```

The code has a logical dependency that has not been physicalized. It is the constant `PAGE_SIZE`. Why should the
`HourlyReporter` know the size of the page? Page size should be the responsibility of the `HourlyReportFormatter`.

The fact that `PAGE_SIZE` is declared in `HourlyReporter` represents a misplaced responsibility that causes
`HourlyReporter` to assume that it knows what the page size ought to be. Such an assumption is a logical dependency.

We can physicalize this dependency by creating a new method in `HourlyReportFormatter` named `getMaxPageSize()`.
`HourlyReporter` will then call that function rather than using the `PAGE_SIZE` constant.

## G14: Replace Magic Numbers with Named Constants

For example, the number `86,400` should be hidden behind the constant `SECONDS_PER_DAY`. If you are printing `55` lines
per
page, then the constant `55` should be hidden behind the constant `LINES_PER_PAGE`.

Some constants are so easy to recognize that they don’t always need a named constant to hide behind so long as they are
used in conjunction with very self-explanatory code. For example:

```java
        double milesWalked=feetWalked/5280.0;
        int dailyPay=hourlyRate*8;
        double circumference=radius*Math.PI*2;
```

Do we really need the constants `FEET_PER_MILE`, `WORK_HOURS_PER_DAY`, and `TWO` in the above examples? Clearly, the
last case
is absurd. There are some formulae in which constants are simply better written as raw numbers.

Constants like `3.141592653589793` are also very well known and easily recognizable. However, the chance for error is
too
great to leave them raw. Every time someone sees `3.1415927535890793`, they know that it is `π`, and so they fail to
scrutinize it. (`Did you catch the single-digit error?`) We also don’t want people using `3.14`, `3.14159`, `3.142`, and
so
forth. Therefore, it is a good thing that `Math.PI` has already been defined for us.

## G15: Functions Should Do One Thing

It is often tempting to create functions that have multiple sections that perform a series of operations.
Functions of this kind do more than _one thing_, and should be converted into many smaller functions, each of which does
_one thing_.

For example:

```java
public class _ {
    public void pay() {
        for (Employee e : employees) {
            if (e.isPayday()) {
                Money pay = e.calculatePay();
                e.deliverPay(pay);
            }
        }
    }
}
```

This bit of code does three things. It loops over all the employees, checks to see whether each employee ought to be
paid, and then pays the employee. This code would be better written as:

```java
public class _ {
    public void pay() {
        for (Employee e : employees) {
            payIfNecessary(e);
        }
    }

    private void payIfNecessary(Employee e) {
        if (e.isPayday()) {
            calculateAndDeliverPay(e);
        }
    }

    private void calculateAndDeliverPay(Employee e) {
        Money pay = e.calculatePay();
        e.deliverPay(pay);
    }
}

```

## G16: Hidden Temporal Couplings

```java
public class MoogDiver {
    Gradient gradient;
    List<Spline> splines;

    public void dive(String reason) {
        saturateGradient();
        reticulateSplines();
        diveForMoog(reason);
    }
}
```

The order of the three functions is important. You must saturate the gradient before you can reticulate the splines, and
only then can you dive for the moog. Unfortunately, the code does not enforce this temporal coupling. Another programmer
could call `reticulateSplines` before `saturateGradient` was called, leading to an `UnsaturatedGradientException`. A
better solution is:

```java
public class MoogDiver {
    Gradient gradient;
    List<Spline> splines;

    public void dive(String reason) {
        Gradient gradient = saturateGradient();
        List<Spline> splines = reticulateSplines(gradient);
        diveForMoog(splines, reason);
    }
}

```

You might complain that this increases the complexity of the functions, and you'd be right. But that extra syntactic
complexity exposes the true temporal complexity of the situation.

Note: I left the instance variables in place. I presume that they are needed by private methods in the class. Even so, I
want the arguments in place to make the temporal coupling explicit.
