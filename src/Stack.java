public class Stack<T> {
    class Node {
        private T value;
        private Node next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    private Node top = null;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size >= 0) {
            this.size = size;
        }
    }

    public void push(T value) {
        Node node = new Node(value);
        if (this.top != null) {
            node.next = this.top;
        }
        this.top = node;
        this.setSize(this.getSize() + 1);
    }

    public T pop() {
        if (this.top == null) {
            return null;
        } else {
            Node node = this.top;
            this.top = this.top.next;
            this.setSize(this.getSize() - 1);
            return node.value;
        }
    }

    public T top() {
        return this.top.value;
    }

    @Override
    public String toString() {
        String result = "";
        Node current = this.top;
        while (current != null) {
            if (current.toString().charAt(current.toString().length() - 1) != ' ') {
                result = "[ " + current + " ] " + result;
            } else {
                result = "[ " + current + "] " + result;
            }
            current = current.next;
        }
        return result;
    }
}