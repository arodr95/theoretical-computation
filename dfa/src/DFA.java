import java.util.LinkedList;

public class DFA {

    // reference: https://grrinchas.github.io/posts/dfa-in-java
    // - used code structure from link to guide creation of DFA
    // - changed state names/properties and transition function to match DFA
    // - added exception handling and private access modifiers to State fields/function
    // - created path function

    private enum State {
        // Q = {s, q1, q2, r1, r2}
        // q0 = {s}
        // F = {q1, r1}
        s(false),
        q1(true),
        q2(false),
        r1(true),
        r2(false);

        private final boolean accept;

        State(boolean accept) {
            this.accept = accept;
        }

        // Sigma = {a, b}
        private State a;
        private State b;

        // delta is described as:
        static {
            s.a = q1; s.b = r1;
            q1.a = q1; q1.b = q2;
            q2.a = q1; q2.b = q2;
            r1.a = r2; r1.b = r1;
            r2.a = r2; r2.b = r1;
        }

        private State transition(char symbol) {
            switch(symbol) {
                case 'a':
                    return this.a;
                case 'b':
                    return this.b;
                default:
                    throw new IllegalArgumentException("'" + symbol + "' is not in the alphabet for this DFA");
            }
        }
    }

    public boolean accept(String string) {
        if(string == null)
            throw new IllegalArgumentException("string cannot be null");

        State state = State.s;

        for(int i = 0; i < string.length(); i++)
            state = state.transition(string.charAt(i));

        return state.accept;
    }

    public LinkedList<String> path(String string) {
        if(string == null)
            throw new IllegalArgumentException("string cannot be null");

        State state = State.s;

        LinkedList<String> visited = new LinkedList<>();
        visited.addLast(state.toString());

        for(int i = 0; i < string.length(); i++) {
            state = state.transition(string.charAt(i));
            visited.addLast(state.toString());
        }

        return visited;
    }
}