package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

class NumberWorkerTest {

    NumberWorker numberWorker;

    @BeforeEach
    void initEach() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 11, 53})
    void isPrimeForPrimes(int num) {
        Assertions.assertTrue(numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 25, 44})
    void isPrimeForNotPrimes(int num) {
        Assertions.assertFalse(numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 1})
    void isPrimeForIncorrectNumbers(int num) {
        Assertions.assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void digitsSum(int number, int sum) {
        Assertions.assertEquals(numberWorker.digitsSum(number), sum);
    }
}