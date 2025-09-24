static void insertAtBottom(Stack<Integer> st, int x) {
        if (st.isEmpty()) {
            st.push(x);
            return;
        }

        // hold the top element and remove it
        int top = st.pop();

        // recursively call to reach the bottom
        insertAtBottom(st, x);

        st.push(top);
    }

    // function to reverse the stack
    static void reverseStack(Stack<Integer> st) {
        if (st.isEmpty()) return;

        // hold the top element and remove it
        int top = st.pop();

        // reverse the remaining stack
        reverseStack(st);

        // insert the held element at the bottom
        insertAtBottom(st, top);
    }
