public class Converter implements Comparable<Converter> {

    // Prefix to Infix | 1
    // Prefix to Postfix | 2
    // Infix to Prefix | 3
    // Infix to Postfix | 4
    // Postfix to Prefix | 5
    // Postfix to Infix | 6

    private int type;
    private int counter = 0;
    private int priority;
    private static Converter[] array = new Converter[6];

    public Converter(int type) {
        if (1 <= type && type <= 6) {
            this.type = type;
        }
    }

    public int getType() {
        return type;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        if (counter >= 0) {
            this.counter = counter;
        }
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (0 <= priority && priority <= 5) {
            this.priority = priority;
        }
    }

    public static Converter[] getArray() {
        return array;
    }

    public static void setArray(Converter converter) {
        boolean found = false;
        int i;
        for (i = 0; array[i] != null && !found && i < 6; i++) {
            if (array[i].getType() == converter.getType()) {
                found = true;
                array[i].setCounter(array[i].getCounter() + 1);
            }
        }
        if (i < 6 && !found && array[i] == null) {
            array[i] = converter;
            array[i].setCounter(array[i].getCounter() + 1);
            array[i].setPriority(i);
        }
    }

    private int checker(String string) {
        // Number or variable | x or 1 | 1
        // Binary operator | + or - or * or / or ^ | 2
        // Unary operator | _ | 3
        // Functions | sin or cos or tan or cot | 4
        // Open parentheses | ( | 5
        // Closes parentheses | ) | 6
        // Invalid | 0
        if (string.length() == 1) {
            boolean A = '0' <= string.charAt(0) && string.charAt(0) <= '9';
            boolean B = 'a' <= string.charAt(0) && string.charAt(0) <= 'z';
            boolean C = 'A' <= string.charAt(0) && string.charAt(0) <= 'Z';
            boolean D = string.compareTo("+") == 0 || string.compareTo("-") == 0 || string.compareTo("*") == 0
                    || string.compareTo("/") == 0 || string.compareTo("^") == 0;
            if (A || B || C) {
                return 1;
            } else if (D) {
                return 2;
            } else if (string.compareTo("_") == 0) {
                return 3;
            } else if (string.compareTo("(") == 0) {
                return 5;
            } else if (string.compareTo(")") == 0) {
                return 6;
            }
        } else {
            boolean A = string.compareToIgnoreCase("sin") == 0 || string.compareToIgnoreCase("cos") == 0
                    || string.compareToIgnoreCase("tan") == 0 || string.compareToIgnoreCase("cot") == 0;
            boolean B = true;
            for (int i = 0; i < string.length(); i++) {
                if (!('0' <= string.charAt(i) && string.charAt(i) <= '9')) {
                    B = false;
                }
            }
            if (A) {
                return 4;
            } else if (B) {
                return 1;
            }
        }
        return 0;
    }

    private int priority(String operator) {
        if (operator.compareTo("+") == 0 || operator.compareTo("-") == 0) {
            return 1;
        } else if (operator.compareTo("_") == 0) {
            return 2;
        } else if (operator.compareTo("*") == 0 || operator.compareTo("/") == 0) {
            return 3;
        } else if (operator.compareTo("^") == 0) {
            return 4;
        } else {
            return 0;
        }
    }

    private String prefixToInfix(String string) throws InvalidExpressionException {
        String result = "";
        String str = "";
        String[] array = string.split(" ");
        Stack<String> stack = new Stack<String>();
        int n = 1;
        for (int i = array.length - 1; i > -1; i--) {
            switch (checker(array[i])) {
                case 0: {
                    throw new InvalidExpressionException("Invalid Input Entered!");
                }
                case 1: {
                    stack.push(array[i]);
                    break;
                }
                case 2: {
                    if (stack.getSize() < 2) {
                        throw new InvalidExpressionException("Invalid Input Entered!");
                    } else {
                        String operandOne = stack.pop();
                        String operandTwo = stack.pop();
                        if (operandOne.charAt(operandOne.length() - 1) != ' ') {
                            operandOne += " ";
                        }
                        if (operandTwo.charAt(operandTwo.length() - 1) != ' ') {
                            operandTwo += " ";
                        }
                        stack.push("( " + operandOne + array[i] + " " + operandTwo + ") ");
                    }
                    break;
                }
                case 3: {
                }
                case 4: {
                    if (stack.getSize() < 1) {
                        throw new InvalidExpressionException("Invalid Input Entered!");
                    } else {
                        String operand = stack.pop();
                        if (operand.charAt(operand.length() - 1) != ' ') {
                            operand += " ";
                        }
                        stack.push("( " + array[i] + " " + operand + ") ");
                    }
                    break;
                }
            }
            str += n + ")\t" + stack + "\n\n";
            n++;
        }
        if (stack.getSize() != 1) {
            throw new InvalidExpressionException("Invalid Input Entered!");
        } else {
            result = stack.pop();
        }
        Converter.setArray(this);
        return "Conversion Result: " + result + "\n\n" + str;
    }

    private String prefixToPostfix(String string) throws InvalidExpressionException {
        String result = "";
        String str = "";
        String[] array = string.split(" ");
        Stack<String> stack = new Stack<String>();
        int n = 1;
        for (int i = array.length - 1; i > -1; i--) {
            switch (checker(array[i])) {
                case 0: {
                    throw new InvalidExpressionException("Invalid Input Entered!");
                }
                case 1: {
                    stack.push(array[i]);
                    break;
                }
                case 2: {
                    if (stack.getSize() < 2) {
                        throw new InvalidExpressionException("Invalid Input Entered!");
                    } else {
                        String operandOne = stack.pop();
                        String operandTwo = stack.pop();
                        if (operandOne.charAt(operandOne.length() - 1) != ' ') {
                            operandOne += " ";
                        }
                        if (operandTwo.charAt(operandTwo.length() - 1) != ' ') {
                            operandTwo += " ";
                        }
                        stack.push(operandOne + operandTwo + array[i] + " ");
                    }
                    break;
                }
                case 3: {
                }
                case 4: {
                    if (stack.getSize() < 1) {
                        throw new InvalidExpressionException("Invalid Input Entered!");
                    } else {
                        String operand = stack.pop();
                        if (operand.charAt(operand.length() - 1) != ' ') {
                            operand += " ";
                        }
                        stack.push(operand + array[i] + " ");
                    }
                    break;
                }
            }
            str += n + ")\t" + stack + "\n\n";
            n++;
        }
        if (stack.getSize() != 1) {
            throw new InvalidExpressionException("Invalid Input Entered!");
        } else {
            result = stack.pop();
        }
        Converter.setArray(this);
        return "Conversion Result: " + result + "\n\n" + str;
    }

    private String infixToPrefix(String string) throws InvalidExpressionException {
        String result = "";
        String str = "";
        String[] array = string.split(" ");
        Stack<String> stack = new Stack<String>();
        int n = 1;
        for (int i = array.length - 1; i > -1; i--) {
            switch (checker(array[i])) {
                case 0: {
                    throw new InvalidExpressionException("Invalid Input Entered!");
                }
                case 1: {
                    result += array[i] + " ";
                    break;
                }
                case 2: {
                }
                case 3: {
                    while (stack.getSize() != 0
                            && (checker(stack.top()) == 4
                            || (checker(stack.top()) == 3 && priority(array[i]) < priority(stack.top()))
                            || (checker(stack.top()) == 2 && priority(array[i]) < priority(stack.top()))
                            || (checker(stack.top()) == 2 && array[i].compareTo("^") == 0
                            && priority(array[i]) == priority(stack.top())))) {
                        result += stack.pop() + " ";
                    }
                    stack.push(array[i]);
                    break;
                }
                case 4: {
                    stack.push(array[i]);
                    break;
                }
                case 5: {
                    while (stack.getSize() != 0 && stack.top().compareTo(")") != 0) {
                        result += stack.pop() + " ";
                    }
                    if (stack.getSize() == 0) {
                        throw new InvalidExpressionException("Invalid Input Entered!");
                    }
                    stack.pop();
                    break;
                }
                case 6: {
                    stack.push(array[i]);
                    break;
                }
            }
            str += n + ")\t" + result + "\n" + "\t" + stack + "\n\n";
            n++;
        }
        while (stack.getSize() != 0) {
            if (stack.top().compareTo(")") == 0) {
                throw new InvalidExpressionException("Invalid Input Entered!");
            }
            result += stack.pop() + " ";
            str += n + ")\t" + result + "\n" + "\t" + stack + "\n\n";
            n++;
        }
        String[] resultArray = result.split(" ");
        result = "";
        for (int i = resultArray.length - 1; i > -1; i--) {
            result += resultArray[i] + " ";
        }
        str += n + ")\t" + result + "\n" + "\t" + stack + "\n\n";
        n++;
        Converter.setArray(this);
        return "Conversion Result: " + result + "\n\n" + str;
    }

    private String infixToPostfix(String string) throws InvalidExpressionException {
        String result = "";
        String str = "";
        String[] array = string.split(" ");
        Stack<String> stack = new Stack<String>();
        int n = 1;
        for (int i = 0; i < array.length; i++) {
            switch (checker(array[i])) {
                case 0: {
                    throw new InvalidExpressionException("Invalid Input Entered!");
                }
                case 1: {
                    result += array[i] + " ";
                    break;
                }
                case 2: {
                }
                case 3: {
                    while (stack.getSize() != 0
                            && (checker(stack.top()) == 4
                            || (checker(stack.top()) == 3 && priority(array[i]) <= priority(stack.top()))
                            || (checker(stack.top()) == 2 && priority(array[i]) < priority(stack.top()))
                            || (checker(stack.top()) == 2 && array[i].compareTo("^") != 0
                            && priority(array[i]) == priority(stack.top())))) {
                        result += stack.pop() + " ";
                    }
                    stack.push(array[i]);
                    break;
                }
                case 4: {
                    stack.push(array[i]);
                    break;
                }
                case 5: {
                    stack.push(array[i]);
                    break;
                }
                case 6: {
                    while (stack.getSize() != 0 && stack.top().compareTo("(") != 0) {
                        result += stack.pop() + " ";
                    }
                    if (stack.getSize() == 0) {
                        throw new InvalidExpressionException("Invalid Input Entered!");
                    }
                    stack.pop();
                    break;
                }
            }
            str += n + ")\t" + result + "\n" + "\t" + stack + "\n\n";
            n++;
        }
        while (stack.getSize() != 0) {
            if (stack.top().compareTo("(") == 0) {
                throw new InvalidExpressionException("Invalid Input Entered!");
            }
            result += stack.pop() + " ";
            str += n + ")\t" + result + "\n" + "\t" + stack + "\n\n";
            n++;
        }
        Converter.setArray(this);
        return "Conversion Result: " + result + "\n\n" + str;
    }

    private String postfixToPrefix(String string) throws InvalidExpressionException {
        String result = "";
        String str = "";
        String[] array = string.split(" ");
        Stack<String> stack = new Stack<String>();
        int n = 1;
        for (int i = 0; i < array.length; i++) {
            switch (checker(array[i])) {
                case 0: {
                    throw new InvalidExpressionException("Invalid Input Entered!");
                }
                case 1: {
                    stack.push(array[i]);
                    break;
                }
                case 2: {
                    if (stack.getSize() < 2) {
                        throw new InvalidExpressionException("Invalid Input Entered!");
                    } else {
                        String operandOne = stack.pop();
                        String operandTwo = stack.pop();
                        if (operandOne.charAt(operandOne.length() - 1) != ' ') {
                            operandOne += " ";
                        }
                        if (operandTwo.charAt(operandTwo.length() - 1) != ' ') {
                            operandTwo += " ";
                        }
                        stack.push(array[i] + " " + operandTwo + operandOne);
                    }
                    break;
                }
                case 3: {
                }
                case 4: {
                    if (stack.getSize() < 1) {
                        throw new InvalidExpressionException("Invalid Input Entered!");
                    } else {
                        String operand = stack.pop();
                        if (operand.charAt(operand.length() - 1) != ' ') {
                            operand += " ";
                        }
                        stack.push(array[i] + " " + operand);
                    }
                    break;
                }
            }
            str += n + ") " + stack + "\n\n";
            n++;
        }
        if (stack.getSize() != 1) {
            throw new InvalidExpressionException("Invalid Input Entered!");
        } else {
            result = stack.pop();
        }
        Converter.setArray(this);
        return "Conversion Result: " + result + "\n\n" + str;
    }

    private String postfixToInfix(String string) throws InvalidExpressionException {
        String result = "";
        String str = "";
        String[] array = string.split(" ");
        Stack<String> stack = new Stack<String>();
        int n = 1;
        for (int i = 0; i < array.length; i++) {
            switch (checker(array[i])) {
                case 0: {
                    throw new InvalidExpressionException("Invalid Input Entered!");
                }
                case 1: {
                    stack.push(array[i]);
                    break;
                }
                case 2: {
                    if (stack.getSize() < 2) {
                        throw new InvalidExpressionException("Invalid Input Entered!");
                    } else {
                        String operandOne = stack.pop();
                        String operandTwo = stack.pop();
                        if (operandOne.charAt(operandOne.length() - 1) != ' ') {
                            operandOne += " ";
                        }
                        if (operandTwo.charAt(operandTwo.length() - 1) != ' ') {
                            operandTwo += " ";
                        }
                        stack.push("( " + operandTwo + array[i] + " " + operandOne + ") ");
                    }
                    break;
                }
                case 3: {
                }
                case 4: {
                    if (stack.getSize() < 1) {
                        throw new InvalidExpressionException("Invalid Input Entered!");
                    } else {
                        String operand = stack.pop();
                        if (operand.charAt(operand.length() - 1) != ' ') {
                            operand += " ";
                        }
                        stack.push("( " + array[i] + " " + operand + ") ");
                    }
                    break;
                }
            }
            str += n + ")\t" + stack + "\n\n";
            n++;
        }
        if (stack.getSize() != 1) {
            throw new InvalidExpressionException("Invalid Input Entered!");
        } else {
            result = stack.pop();
        }
        Converter.setArray(this);
        return "Conversion Result: " + result + "\n\n" + str;
    }

    public String convert(String string) throws InvalidExpressionException {
        switch (this.getType()) {
            case 1: {
                return prefixToInfix(string);
            }
            case 2: {
                return prefixToPostfix(string);
            }
            case 3: {
                return infixToPrefix(string);
            }
            case 4: {
                return infixToPostfix(string);
            }
            case 5: {
                return postfixToPrefix(string);
            }
            case 6: {
                return postfixToInfix(string);
            }
            default: {
                return string;
            }
        }
    }

    @Override
    public int compareTo(Converter o) {
        int result = 0;
        if (o.getClass() == Converter.class) {
            if (this.getCounter() > o.getCounter()) {
                result = 1;
            } else if (this.getCounter() < o.getCounter()) {
                result = -1;
            } else {
                if (this.getPriority() > o.getPriority()) {
                    result = 1;
                } else if (this.getPriority() < o.getPriority()) {
                    result = -1;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String string = "";
        switch (this.getType()) {
            case 1: {
                string = "Prefix To Infix:\t\t" + this.getCounter() + "\n\n";
                break;
            }
            case 2: {
                string = "Prefix To Postfix:\t" + this.getCounter() + "\n\n";
                break;
            }
            case 3: {
                string = "Infix To Prefix:\t\t" + this.getCounter() + "\n\n";
                break;
            }
            case 4: {
                string = "Infix To Postfix:\t\t" + this.getCounter() + "\n\n";
                break;
            }
            case 5: {
                string = "Postfix To Prefix:\t" + this.getCounter() + "\n\n";
                break;
            }
            case 6: {
                string = "Postfix To Infix:\t\t" + this.getCounter() + "\n\n";
                break;
            }
        }
        return string;
    }

}
