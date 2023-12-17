package edu.school21.numbers;

public class NumberWorker {
    public static class IllegalNumberException extends IllegalArgumentException {
        public IllegalNumberException(String errorMessage) {
            super(errorMessage);
        }
    }

    public boolean isPrime(int number)  {
        if (number <= 1) {
            throw new IllegalNumberException("Illegal argument");
        }
        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int digitsSum(int number) {
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }

}
