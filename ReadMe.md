# QuantLab tools and technology tutorium.

This project contains exercises to be worked on during the quantlab tutorium. Very often you will find a test in **src/test/java**, which you can use to check your solutions. Of course you can also create your own main method to run your code, while working on the problem. The following week you will often find a (possible) solution in **src/solution/java**. If you have problems or questions always feel free to ask!





## 06/07 Random Numbers

Today we review various methods to create random numbers in Java, using the native libraries as well as externals.
We also take note on thread safety with random number generation.


### Playing dice

For the exercise you will find a test called `SetOfDiceTest`. For this you will need to implements the two factories
`BasicDieFactory` and `LoadedDieFactory`. The former producing fair dice showing the numbers 1 through 6.
The latter produces loaded dice, which have a one-in-four chance to yield the result 6 when cast. (The other numbers
being equal in probability.)

You can use the provided class `SimpleSetOfDice` in your factories.
This class builds a set from individual dice.
Note that each die needs to be unique, the class does not accept duplicates.
Take care of how you build your random number generator with your factory,
especially when you want to run your program in parallel.
You don't want your die tosses to be correlated!


### Bonus

You can try different RNG for your dice and you can also try to find a more efficient implementation of `SetOfDice`.






## 05/31 Building your own project

There is nothing to be coded in this exercise. Instead this exercise is about creating your "own project and managing
dependencies with and without the use of maven. In src/test/resources you will find two test classes. However, these
require some dependencies and will not work by themselves.

Please create two new projects and set up their dependencies such that you can run the tests.

First start with the BrownianMotionTest from the finmath-lib. Please import the lib as a Project using your IDE's build
management tools. If you like you can set up the modules, but this is optional.

Second, set up a project for RandomVariableGPUTest from the finmath-lib-cuda-extensions. This time try to use maven to
manage the dependencies. We would like to use version 4.1.7 of the extension.

Hints:
* You will need to configure your project to use JUnit4. JUnit5 will not work with the tests as they are.
* Take care which package you put the tests into. Either you create a package of the name specified in the class file or
 you adjust the file. 
* Some tests may fail. Especially, when you don't have a dedicated GPU installed on your computer. Don't worry, it's
fine as long as the tests themselves are running.








## 05/17 Concurrency and Parallelism

Today we look at parallel and concurrent programming. Especially with the use of streams and lambda
expressions.


### Primes

The exercise consists of implementing the method getPrimes(long minNumber, long maxNumber) of
PrimeNumbersParallel such that it finds prime numbers on multiple threads in parallel.


### Bonus

Disclaimer: This one is a bit more advanced. Don't worry if you are having problems with this.

Implement the method getPrimes(). The method is supposed to calculate primes indefinitely until the
user presses "enter" in the console. 

Note, the test simulates the user by overwriting System.in to send a new line character after 0.1s.
You can implement a more sophisticated interaction method, but for the test to work you will want to
utilize System.in.







##05/10 Primitive vs reference types and Collections

A review of primitive types (e.g. `double`) vs reference types (e.g. `Double`), followed by a discussion of their use inside collections (`java.util.Collection`).

### RandomToList

This is a short exercise in which to create a list of Double from `java.util.Random`, which returns 
primitive double.

### GuestList

A mock ledger to keep track of guests in a hotel. Where each guest is assigned a unique hotel room.
The API is build upon the Java Collections Framework, which uses reference types, but you can use 
any form of internal representation.

Hint: You can use a Map of said framework to make this exercise pretty straight forward.






## 05/03 Debugging in eclipse

We go through some of the debugging tools that eclipse has to offer. We also discuss throwing and handling exceptions.

### Debugging

Use the stack trace and debugging tools to find the errors in `SpyCipher` and `SequenceNDivbyAverage`. There are tests as well as a main method to run for each class.

### ArithmeticException

The function sin(x)/((x+pi)(x-pi)) is undefined for x=+-pi. However, it can be continuously extended. Please change the class `ContinuousExtension` so it works for all x. Do NOT use an if clause to check for pi, but try to use a try/catch for the exception.




## 04/26 Submitting assignments

We take a more in depth look into git and github. In particular we review how to submit your assignments.
You can find a little introduction by GitHub on https://github.com/quantlab-tutorium/github-starter-course.git.

### Fibonacci

From Wikipedia:
"In mathematics, the Fibonacci numbers, commonly denoted Fn, form a sequence, called the Fibonacci
sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1.

  That is,

    F_{0}=0, F_{1}=1,
    F_{n}=F_{n-1}+F_{n-2}, for n > 1.

The beginning of the sequence is thus:
1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, \ldots"

The exercise consists of summing all even Fibonacci numbers up to (and including) a given barrier.
I.e. for the limit 8 the result would be 10.
Please complete the static method `sumEven` of the class `Fibonacci`.

### CeasarCipher

From Wikipedia:
"In cryptography, a Caesar cipher, also known as Caesar's cipher, the shift cipher, Caesar's code
or Caesar shift, is one of the simplest and most widely known encryption techniques. It is a type
of substitution cipher in which each letter in the plaintext is replaced by a letter some fixed
number of positions down the alphabet. For example, with a left shift of 3, D would be replaced
by A, E would become B, and so on. The method is named after Julius Caesar, who used it in his
private correspondence."

Please complete the class `CeasarCipher`, which implements the interface `Cipher`. The class
contains the methods `encode` and `decode` from its interface as well as a static factory
method `of`. Please try to keep the constructors private and use this method instead to instantiate your ciphers.




## 04/16 Introduction to our tools

Today we get used to the eclipse IDE and check out the other tools we will be using for the lecture Numerical Methods for Financial Mathematics.

### Hello World

Like any respectable coding tutorial your first first exercise is to write your own "Hello world" program, i.e. create a class with a main method that prints "Hello world" to the command line. You can create the class either in project or create your own java project in your workspace.

### Bonus

Write a program that will ask you for your name and then greet you. Alternatively you can also give your name to the program as a launch argument.

**Hint:** Maybe the easiest way to get input data to a running program is via a `Scanner` on `System.in`. That way you can interact directly on the command line.
