import java.util.*;

// made a design decision to put transitions in their own class outside the Turing machine class
// to force the user to define all rules before machine creation. this way the user does not
// have the potential to add/edit a rule during string processing. i think it also is nice that
// the Turing machines are created with a fully defined 7-tuple instead of having to define delta
// after machine creation.

public class Transitions {
    private final Map<String, Map<Character, Transition>> transitionMap;
    private final Set<Character> Gamma;
    private final Set<Character> directions = new HashSet<>(Arrays.asList('L', 'R'));

    public Transitions(Set<String> Q, Set<Character> Gamma) {
        // use each state as a key to its own transitions for use in lookups
        transitionMap = new HashMap<>();
        for (var state : Q) {
            transitionMap.put(state, new HashMap<>());
        }
        this.Gamma = Gamma;
    }

    public static class Transition {
        private final String next;
        private final Character write;
        private final Character direction;

         private Transition(String next, Character write, Character direction) {
            this.next = next;
            this.write = write;
            this.direction = direction;
        }

        // getter methods for Transition data members
        public String nextState() {
             return next;
        }

        public Character getWriteSymbol() {
             return write;
        }

        public Character getDirection() {
             return direction;
        }
    }

    public void addTransition(String state, Character read, String next, Character write, Character direction) {
        // error handling
        if (!transitionMap.containsKey(state))
            throw new IllegalArgumentException("State does not exist.");

        if (!transitionMap.containsKey(next))
            throw new IllegalArgumentException("Next state does not exist.");

        if (!Gamma.contains(read))
            throw new IllegalArgumentException("Read symbol not recognized.");

        if (!Gamma.contains(write) && write != null)
            throw new IllegalArgumentException("Write symbol not recognized.");

        if (!directions.contains(direction))
            throw new IllegalArgumentException("Not a valid direction.");

        // find state for transition
        // each transition is a map from a read symbol to a Transition object
        transitionMap.get(state).put(read, new Transition(next, write, direction));
    }

    // getter for transition map
    public Map<String, Map<Character, Transition>> getTransitionMap() {
        return transitionMap;
    }
}