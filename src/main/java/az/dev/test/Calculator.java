package az.dev.test;

public class Calculator {

    public int sumNumbers(int a, int b) {
        return privateMethodTest(a, b);
    }

    public int subtractNumbers(int a, int b) {
        return a - b;
    }

    public int divideNumbers(int a, int b) {
        return a / b;
    }

    private int privateMethodTest(int a, int b) {
        return a + b;
    }
}
