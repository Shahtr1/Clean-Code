We dont prove our software correct we demonstrate that it is not incorrect by surrounding it with tests.
A try block should be in separate function and never use nested try catch blocks
Prefer exceptions to return error codes
Command and Query separation:

- A function that returns a value should not have a side effect
- A function that returns a void will have a side effect
  A jar file is there so you can independently deploy chunks of your system
  Open Closed principle:
- A module should be open for extension but closed for modification
- We do that by having base classes and having derivatives
  No double take moments in the code
  If we have set of functions taht manipulates a set if variables, then we have a class.
  Allow the reader to exit early
  Every line of a function should be at the same level of abstraction
  Use variables to explain code rather than comments
  Never talk about code which is somewhere else in Comments
  File size is not a function of project size, it is a style that we can impose on our system
  Try not to go beyond 150 lines
  A variable name should be proportional to the size of scope it is in, long scopes need long names
  The bigger the scope, the smaller the name of function. so inversely proportional to the size of scope it contains in.
  Size of Class name is inversely proportional too as per scope
  Software was invented so we can easily change the behaviour of our machines, if we didnt wanted to change the behaviour of machines we would have stayed with hardware. So if your software is hard to change, you just have reinvented hardware
  What does coders do? Sequence, Selection and Iteration thats what we do,
- Sequence is the order in which instructions occur and are processed.
- Selection determines which path a program takes when it is running.
- Iteration is the repeated execution of a section of code when a program is running.

- There are two types of iteration:
  1.  count-controlled iteration
  2.  condition-controlled iteration

Test Driven Development is a discipline
Rules:

1.  You are not allowed to write any production code until you have first written a test that fails because the production code doesnt exist
2.  You are not allowed to write any more of a unit test than is sufficient to fail; and compilation failures are failures.
3.  You are not allowed to write any more production code than is sufficient to pass the one failing unit test
    In TDD, you dont run for the goal, you write tests for everything else first.
    Production code should evolve in one direction i.e., general. And Test should be more specific. They will have very different shapes
    We write unit tests then we refactor production code
    The test code will look differently from the production code

AGILE is a framework in which we can apply a good architecture
Architecture changes with the system on a day to day basis
The architecture rules are independent of every other variable
Twenty-two orders of magnitude in hardware. Yet software remains the same
The goal of software architect is to minimize the human resources required to build and maintanin software systems. Its all about people.
As effort increases, design quality decreases

Architecture is not about making decisions early, it about making decisions late.
A good architecture is a software structure that allows you to defer critical decisions for as long as possible to the point you have enough info to make them or not to make them as the case maybe
A user story is a card that reminds us what this feature is not the whole story
In first sprint we make a guess
In second sprint we do real implementation
Agile is you take project and you sub divide it into time boxes, and you measure how much you can do in that time boxes, and with that measurement you project the end date and then you manage and manage and manage.
Agile is to get the bad news as early as possible
SCRUM is an agile process, it deal with the business facing process, dont deal with internals
Check ( agile pratcices.png ) in images folder

- The green ring is SCRUM
- The red inner circle is the engrineering practices, the programmer specific practices
- The blue is the team practices, the disciplines used by the team
  SCRUM without the inner rings has a name: Flatted SCRUM
- It starts to work initially
- Then with time team slows down, why?
- Because there are no engg practices in the middle
- So augment SCRUM with Engg practices
  Lets talk about Green ones ( SCRUM )
- SCRUM is mostly about planning game
- Agile is for small teams doing relatively small things
- Relative estimates are better than absolute estimates
- Iterations do not fail, they give you data, we are measuring how much is done
- Velocity cure should be flat, positive curve means pressure on team, we dont need that
- Always compare the one with the reference card ( login card )
- QA will write on the card, the definition of DONE, if they are programmers (make them write acceptance tests)
- QA dont come at last, they come at fast
- How do you deal with non fucntional requirements and tell stake holders that they are important?
  Put a number on it that QA can write, like we need to be able to handle 10000 transactions per second
  Architecture related, you never say a word about these. never say to your boss, if i dont write test it will be done fast. The only way to go fast is to go well. Never talk about refactoring to stake holders, ( why didnt you do it right in the first time?????? )
- A spike is a period of time that you will take to try to figure out how long you can estimate? ( estimate another story )
