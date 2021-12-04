# Turing Machine Project 

## Description
This project is for the creation of the Turing Machine, M = ({s, q1, q2, q3, q4, q5, qA, qR}, {0}, {., X, 0}, delta, q1, qA, qR), where delta is the transition function which is depicted in the slides and textbook. In this Java application, a Turing Machine is instantiated by providing the 7-tuple of Q, Sigma, Gamma, delta, q0, qAccept, and qReject. There is a single method, process(), which has a single parameter, an input string, that is processed and displays the sequence of states, tape, and head location for the Turing Machine. The pdf, project3, contains the project description as well as a screenshot of the output which shows the strings being processed by the Turing Machine.

## Run the Code
1. Verify JDK (15.0.1+) is installed
```
java -version
```
2. Navigate to src of unzipped contents
```
cd ~/theoretical-computation/turing-machine/src
```
3. Compile Main.java
```
javac Main.java
```
4. Run Main.class
```
java Main
```
You should see output showing whether each string is accepted or rejected by the Turing Machine created in Main.java.

## Source Code Review
1. Define Q (set of states), Sigma (input alphabet), and Gamma (tape alphabet)
``` java
var Q = new HashSet<>(List.of("q1", "q2", "q3", "q4", "q5", "qA", "qR"));
var Sigma = new HashSet<>(List.of('0'));
var Gamma = new HashSet<>(List.of('.', 'X', '0'));
```
2. Define delta by creating a Transitions object and adding the rules for your Turing Machine.
``` java 
var delta = new Transitions(Q, Gamma);
delta.addTransition(String state, Character read, String next, Character write, Character direction);
...
delta.addTransition(...);
```
I made a design decision to put transitions in their own class outside the Turing machine class to force the user to define all rules before machine creation. This way the user does not have the potential to add/edit a rule during string processing. I think it also is nice that the Turing machines are created with a fully defined 7-tuple instead of having to define delta after the machine is created.
3. Define q0, qAccept, and qReject.
``` java 
var q0 = "q1";
var qAccept = "qA";
var qReject = "qR";
```
4. Create Turing Machine object using definitions from above.
``` java 
TuringMachine tm = new TuringMachine(Q, Sigma, Gamma, delta, q0, qAccept, qReject);
```
5. Define strings to process
``` java 
var strings = new String[] {"", "0", "00", "000", "0000", "00000", "000000"};
```
6. Loop trough strings, process each string, and display each state using textbook convention for head location on tape. Also, a little of formatting to make the output pretty.
``` java 
for (var string : strings) {
    System.out.println("STRING - '" + string + "'");
    System.out.println("SEQUENCE:");
    var result = tm.process(string);
    System.out.println();
    System.out.println(result ? "'" + string + "' is accepted by this Turing machine." : "'" + string + "'" + " is not accepted by this Turing machine.");
    System.out.println("---------------------------------------------");
}
```
Result stores the boolean value indicating whether the string was accepted or rejected by the Turing Machine. The java ternary operator is used on result to simplify the print logic.
