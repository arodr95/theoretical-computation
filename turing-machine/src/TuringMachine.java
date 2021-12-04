import java.util.Set;

public class TuringMachine {
    private final Set<String> Q;
    private final Set<Character> Sigma;
    private final Set<Character> Gamma;
    private final Transitions delta;
    private final String startState;
    private final String acceptState;
    private final String rejectState;

    private char[] tape;
    private String currState;
    private int index;
    private Character currSymbol;

    public TuringMachine(Set<String> Q, Set<Character> Sigma, Set<Character> Gamma, Transitions delta, String startState, String acceptState, String rejectState) {
        this.Q = Q;
        this.Sigma = Sigma;
        this.Gamma = Gamma;
        this.delta = delta;
        this.startState = startState;
        this.acceptState = acceptState;
        this.rejectState = rejectState;
    }

    public boolean process(String str) {
        setTape(str);
        printTape();

        while (stillProcessing()) {
            executeTransition(lookupTransition(currState, currSymbol));
            printTape();
        }

        return currState.equals(acceptState);
    }

    private void setTape(String str) {
        // initial tape and head setup
        tape = (str).toCharArray();
        index = 0;
        currState = startState;
        currSymbol = index < tape.length ? tape[index] : '.';
    }

    private boolean stillProcessing() {
        // accept or reject state is reached
        return !currState.equals(acceptState) && !currState.equals(rejectState);
    }

    private Transitions.Transition lookupTransition(String state, Character symbol) {
        return delta.getTransitionMap().get(state).get(symbol);
    }

    private void executeTransition(Transitions.Transition t) {
        // write new symbol; don't write if null
        if (t.getWriteSymbol() != null)
            tape[index] = t.getWriteSymbol();

        // move index
        switch (t.getDirection()) {
            case 'L' -> index--;
            case 'R' -> index++;
        }

        // update current state
        currState = t.nextState();

        // update current symbol using index
        // if at the end of tape, symbol is . (blank)
        currSymbol = (index < tape.length) ? tape[index] : '.';
    }

    private void printTape() {
        for (int i = 0; i < tape.length; i++) {
            // print state before the symbol head is pointing too
            // follows textbook's convention for displaying tape sequences
            if (index == i)
                System.out.printf("%s", currState);
            System.out.printf("%c", tape[i]);
        }

        // only show . at end of tape when head is at end of tape
        // which is when state is q1, q2, q4, qA, qR and head is at end of tape
        if (index >= tape.length)
            if (stillProcessing())
                System.out.printf("%s.", currState);
            else
                System.out.printf(".%s", currState);

        System.out.printf("\n");
    }
}