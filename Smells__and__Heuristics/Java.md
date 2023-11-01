# Java

## J1: Avoid Long Import Lists by Using Wildcards

- If you use two or more classes from a package, prefer wildcard imports.
  Example:
  `import package.*;`
- Specific imports form hard dependencies, while wildcard imports don't.
- Long import lists can be daunting; aim for concise import statements.
- Wildcard imports can help make the codebase less tightly coupled.
- IDEs often allow conversion from wildcard imports to specific ones.
- Beware: Wildcard imports might cause name conflicts but are rare.

## J2: Constants versus Enums

Now that Enums have been added to language ( Java 5 ), use them!
Dont use the old trick of `public static final ints`.

Here's how it was done:

```java
public class Days {
    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    // ... and so on for the rest of the days
}
```

---

# Drawbacks of Using `public static final int` over Enums in Java

## 1. Lack of Type Safety

Using `int` constants doesn't prevent a developer from assigning any arbitrary integer value. For example, having
constants:

```java
public class Days {
    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
}
```

doesn't stop someone from doing:

```java
int today=100; // This is obviously not a valid day, but the compiler won't complain.
```

## 2. Reduced Readability

When you see methods that accept or return these ints, it's not immediately clear which set of valid values they expect.
For instance:

```java
public void setDay(int day){...}
```

Here, calling `setDay(3)` makes you wonder: What day does 3 represent? It's not clear.

## 3. No Functionality Association

With int constants, you cannot easily associate behavior (methods) with each constant.
In contrast, enums allow methods to be associated with each value.

## 4. Namespace Pollution

All values are in the same namespace, potentially leading to naming conflicts.

## 5. Refactoring Risks

If the underlying integer values change, code that depends on specific numbers might break.
---

# Advantages of Using Enums in Java

Enums, introduced in Java 5, offer a robust and flexible way to represent a fixed set of related constants. Their
advantages over the old approach of `public static final int` constants include:

## 1. Enhanced Type Safety

Enums provide type safety. This means you can't assign an arbitrary value that isn't one of the defined enum constants.

Example:

```java
public enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
}

    Day today = Day.SUNDAY; // Valid
    Day tomorrow = 100;     // Compile error
```

## 2. Improved Readability

Using enums makes the code more readable because the values are named descriptively. When you see a method that accepts
an enum type, you know exactly which values are valid.

Example:

```java
public void setDay(Day day){...}
```

Here, it's evident that setDay expects a value of type `Day`.

## 3. Ability to Associate Functionality

With enums, you can associate behavior (methods) with each constant, making it more than just a value.

```java
public enum Day {
    SUNDAY {
        @Override
        public boolean isWeekend() {
            return true;
        }
    },
    MONDAY {
        @Override
        public boolean isWeekend() {
            return false;
        }
    };
    // ... other days

    public abstract boolean isWeekend();
}

```

## Distinct Namespaces with Enums

When we talk about namespaces in programming, we're referring to a container that holds a set of identifiers, like
variables, functions, or types. The purpose of a namespace is to group these identifiers so that they can be used
without any ambiguity and to avoid naming collisions.

In Java, packages serve as namespaces at a higher level. However, when using public static final int for constants, all
constants reside in the same namespace of the class they belong to, which can lead to potential naming conflicts.

Example:

You have two sets of constants, one representing Days and another representing Months. If you wanted to have a constant
called FIRST in both sets, you would run into a problem:

```java
public class CalendarConstants {
    public static final int FIRST_DAY = 1;
    public static final int FIRST_MONTH = 1;
    // ... other constants
}

```

In the above, you had to name the constants FIRST_DAY and FIRST_MONTH to avoid a naming collision.

Now, let's look at enums:

```java
public enum Day {
    FIRST, SECOND, THIRD, // ... other days
}

public enum Month {
    FIRST, SECOND, THIRD, // ... other months
}

```
