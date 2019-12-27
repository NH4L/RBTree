package utils;

/**
 * 斐波那契数列
 * @author lcy
 * @date 2019-12-27
 */
public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci rc = new Fibonacci(1);
        rc.printFibonacci(5);
    }

    private int _firstNumber = 0;

    /**
     * 构造函数
     * @param firstNumber 第一个数
     */
    public Fibonacci(int firstNumber) {
        this._firstNumber = firstNumber;
    }


    public void printFibonacci(int n) {
        int[] _arr = new int[n];
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                _arr[i - 1] = this._firstNumber;
            } else if (i == 2) {
                _arr[i - 1] = this._firstNumber;
            } else {
                _arr[i - 1] = _arr[i - 2] + _arr[i - 3];
            }
        }
        print(_arr);
    }

    private void print(int[] _arr) {
        System.out.println("结果：");
        byte b;
        int i, arrayOfInt[];
        for (i = (arrayOfInt = _arr).length, b = 0; b < i; ) {
            int ii1 = arrayOfInt[b];
            System.out.print(ii1 + "  ");
            b++;
        }

    }
}