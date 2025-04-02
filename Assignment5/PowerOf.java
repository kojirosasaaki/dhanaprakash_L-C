package LandC;

class PowerOf implements Calculation {
    public double calculate(double a, double b) {
        double result = 1;
        for (int i = 0; i < (int) b; i++) {
            result *= a;
        }
        return result;
    }
}
