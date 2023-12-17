package edu.school21.numbers;

public class IllegalNumberException extends IllegalArgumentException {
    public IllegalNumberException(String errorMessage) {
        super(errorMessage);
    }
}
