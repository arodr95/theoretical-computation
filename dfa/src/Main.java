public class Main {
    public static void main(String args[]) {
        DFA dfa = new DFA();

        var strings = new String [] {"a", "bb", "babab", "abaaba", "bbbaaab", "", "abb", "baba", "abaab", "baaaaaa"};

        for (String string : strings) {
            System.out.println(dfa.accept(string) ? "'" + string + "' is accepted" : "'" + string + "' is not accepted");
            System.out.println("states visited - " + dfa.path(string));
            System.out.println();
        }
    }
}
