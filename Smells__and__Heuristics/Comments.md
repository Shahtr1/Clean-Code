# Comments Best Practices

## C1: Inappropriate Information

- Avoid placing information better suited for version control or issue tracking systems.
- Exclude meta-data like authors or modified dates.
- Focus comments on technical notes about the code/design.

## C2: Obsolete Comment

- Avoid comments that become quickly outdated.
- Update or remove old comments promptly.
- Prevent comments from being disconnected from related code.

## C3: Redundant Comment

- Don't describe what's already self-explanatory.
- Comments should provide information that code alone can't.

e.g.,

```java
    i++; // increment i

/**
 * @param sellRequest
 * @return
 * @throws ManagedComponentException
 */
public SellResponse beginSellItem(SellRequest sellRequest)throws ManagedComponentException
```

## C4: Commented-Out Code

- Refrain from keeping stretches of commented-out code.
- Commented-out code tends to become irrelevant over time.
- If a code is not in use, delete it. Rely on source control for history.


