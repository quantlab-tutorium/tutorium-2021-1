# QuantLab tools and technology tutorium.

This project contains exercises to be worked on during the quantlab tutorium. Very often you will find a test in **src/test/java**, which you can use to check your solutions. Of course you can also create your own main method to run your code, while working on the problem. The following week you will often find a (possible) solution in **src/solution/java**. If you have problems or questions always feel free to ask!




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
