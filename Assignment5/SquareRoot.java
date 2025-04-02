package LandC;

public class SquareRoot implements Calculation {
    public double calculate(double a, double b) {
        double assumedNumber = a / 2;
        for (int i = 0; i < 10; i++) {
        	assumedNumber = (assumedNumber + a / assumedNumber) / 2;
        }
        return assumedNumber;
    }
}
