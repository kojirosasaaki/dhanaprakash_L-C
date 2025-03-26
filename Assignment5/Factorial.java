package LandC;

class Factorial implements Calculation {
    public double calculate(double a, double b) {
        if (a < 0) {
            System.out.println("Factorial of negative numbers is not defined.");
            return Double.NaN;
        }
        double result = 1;
        for (int i = 1; i <= a; i++) {
            result *= i;
        }
        return result;
    }
}
