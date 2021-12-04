# CSC 4890 PROJECT 1 - DFA

## Description
This project is for the creation of the DFA, M = ({s, q1, q2, r1, r2}, {a, b}, delta, {s}, {q1, r1}), where delta is the transition function depicted in the drawing for the project assignment. 
Each state is enumerated inside the DFA class with a boolean field accept, transition definition, and transition function. 
The DFA class also contains the method accept, that returns a boolean indicating whether a string is accepted or rejected, and the method path, that returns a linked list of the states visited in-order while processing the strings. 
The pdf, project1, contains the project description as well as a screenshot of the output which shows the strings being processed by the DFA.

## Run the Code
1. Verify JDK (15.0.1+) is installed
	java -version

2. Navigate to src of unzipped contents
	cd ~/project1/src

3. Compile Main.java
	javac Main.java

4. Run Main.class
	java Main

You should see output showing whether 10 pre-defined strings are accepted or rejected by the DFA created in the file DFA.java.

## Source Code Review
1. Create DFA object
	DFA dfa = new DFA();

2. Define strings to check
	var strings = new String [] {"a", "bb", "babab", "abaaba", "bbbaaab", "", "abb", "baba", "abaab", "baaaaaa"};

These are the strings I used to demonstrate the different ways a string can be accepted/rejected. 
Feel free to change the strings in this array to test a variety of other strings. 
Try and see what happens when your string has characters not in the alphabet, sigma = {a, b}, or null strings to see the implemented error handling.


    3. Loop through strings, check if DFA accepts/rejects each, and display results and states visited
	for (String string : strings) {
            System.out.println(dfa.accept(string) ? "'" + string + "' is accepted" : "'" + string + "' is not accepted");
            System.out.println("states visited - " + dfa.path(string));
            System.out.println();
        }

The first print statement uses the java ternary operator with the accept method call to indicate in words if the string is accepted or rejected. 
The second line print line uses the path method call to print out the states visited.

## Resources Used
https://grrinchas.github.io/posts/dfa-in-java
- used code structure from link to guide creation of DFA
- changed state names/properties and transition function to match DFA
- added exception handling and private access modifiers to State fields/function
- created path function
