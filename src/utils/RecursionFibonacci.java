package utils;

public class RecursionFibonacci {
    public static void main(String[] args) {
        RecursionFibonacci rc = new RecursionFibonacci(1);
        rc.printFibonacci(24);
    }

    private int _firstNumber = 0;
    private int _n = 0;
    private int _NthNumber = 0;


    public RecursionFibonacci(int firstNumber) {
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

        debug(_arr);
    }

    private void debug(int[] _arr) {
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