package LandC;

class Division implements Calculation {
    public double calculate(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero.");
            return Double.NaN;
        }
        return a / b; 
    }
}
