import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        // states
        var Q = new HashSet<>(List.of("q1", "q2", "q3", "q4", "q5", "qA", "qR"));

        // input alphabet
        var Sigma = new HashSet<>(List.of('0'));

        // tape alphabet
        var Gamma = new HashSet<>(List.of('.', 'X', '0'));

        // transition function
        var delta = new Transitions(Q, Gamma);
            delta.addTransition("q1", '.', "qR", null, 'R');
            delta.addTransition("q1", 'X', "qR", null, 'R');
            delta.addTransition("q1", '0', "q2", '.', 'R');
            delta.addTransition("q2", '.', "qA", null, 'R');
            delta.addTransition("q2", '0', "q3", 'X', 'R');
            delta.addTransition("q2", 'X', "q2", null, 'R');
            delta.addTransition("q3", '0', "q4", null, 'R');
            delta.addTransition("q3", '.', "q5", null, 'L');
            delta.addTransition("q3", 'X', "q3", null, 'R');
            delta.addTransition("q4", '.', "qR", null, 'R');
            delta.addTransition("q4", '0', "q3", 'X', 'R');
            delta.addTransition("q4", 'X', "q4", null, 'R');
            delta.addTransition("q5", '.', "q2", null, 'R');
            delta.addTransition("q5", '0', "q5", null, 'L');
            delta.addTransition("q5", 'X', "q5", null, 'L');

        // start state
        var q0 = "q1";

        // accept state
        var qAccept = "qA";

        // reject state
        var qReject = "qR";

        // create Turing machine using 7-tuple
        TuringMachine tm = new TuringMachine(Q, Sigma, Gamma, delta, q0, qAccept, qReject);

        var strings = new String[] {"", "0", "00", "000", "0000", "00000", "000000"};
        for (var string : strings) {
            System.out.println("STRING - '" + string + "'");
            System.out.println("SEQUENCE:");
            var result = tm.process(string);
            System.out.println();
            System.out.println(result ? "'" + string + "' is accepted by this Turing machine." : "'" + string + "'" + " is not accepted by this Turing machine.");
            System.out.println("---------------------------------------------");
        }
    }
}