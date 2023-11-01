# Functions

## F1: Too Many Arguments

- Aim for a small number of arguments in functions.
- Ideally, functions should have no arguments, but 1-3 arguments are generally acceptable.

## F2: Output Arguments

- Functions should change the state of their object, not return outputs.
- Users typically expect function arguments to be inputs.

## F3: Flag Arguments

- Avoid using Boolean arguments that indicate a function does multiple things.
- They can make the function's purpose unclear.

## F4: Dead Function

- Unused methods or functions should be removed to prevent clutter.
