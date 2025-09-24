class GfG {

    // Insert element into sorted stack
    static void sortedInsert(Stack<Integer> st, int x) {
        
        // If stack is empty or
        // top element is smaller, push x
        if (st.isEmpty() || st.peek() <= x) {
            st.push(x);
            return;
        }

        int top = st.pop();

        // Recursively insert x in sorted order
        sortedInsert(st, x);

        st.push(top);
    }

    // Sort the stack recursively
    static void sortStack(Stack<Integer> st) {
        if (st.isEmpty()) return;

        int top = st.pop();

        // Recursively sort the remaining stack
        sortStack(st);

        sortedInsert(st, top);
    }

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(41);
        st.push(3);
        st.push(32);
        st.push(2);
        st.push(11);

        sortStack(st);

        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
    }
}
