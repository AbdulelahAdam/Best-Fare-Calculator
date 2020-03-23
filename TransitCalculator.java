import java.util.Arrays;

public class TransitCalculator {
    private int numOfDays = 0; // up to 30 days
    private int numOfRides = 0; // individual rides the person expects to take
    private static double[] prices;

    // Pay-per-ride
    // 7-day Unlimited Rides
    // 30-day Unlimited Rides

    private static double[] fareOption = { 2.75, 33.00, 127.00 };

    public TransitCalculator() {
    }

    public TransitCalculator(int days, int rides) {
        numOfDays = days;
        numOfRides = rides;

        String bestFareMethod = getBestFare(numOfDays, numOfRides);

        System.out.println(bestFareMethod);
    }

    private static double unlimited7Price(int days, int rides) {
        double newDays = Math.ceil((double) days / 7);
        double fare = newDays * fareOption[1];
        double result = fare / rides;
        return result;
    }

    private static double unlimited30Price(int days, int rides) {
        double newDays = Math.ceil((double) days / 30);
        double fare = newDays * fareOption[2];
        double result = fare / rides;
        return result;
    }

    private static double payPerRide(int days, int rides) {
        return fareOption[0] * rides;
    }

    private static double[] getRidePrices(int days, int rides) {
        double[] values = new double[3];
        prices = values;
        values[0] = payPerRide(days, rides);
        values[1] = unlimited7Price(days, rides);
        values[2] = unlimited30Price(days, rides);
        return values;
    }

    public static String getBestFare(int days, int rides) {
        prices = getRidePrices(days, rides);
        Arrays.sort(prices);
        double min = prices[0];
        String bestFare = "";
        for (int i = 0; i < 3; i++) {
            if (min == prices[i]) {
                if (i == 0) {
                    bestFare = "\nYou should get the Pay-per-day option at " + min + " per ride.";
                } else if (i == 1) {
                    bestFare = "\nYou should get the 7-day Unlimited option at " + min + " per ride.";
                } else {
                    bestFare = "\nYou should get the 30-day Unlimited option at " + min + " per ride.";
                }
            }
        }

        return bestFare;
    }

    public static void main(String[] args) {
        TransitCalculator obj;
        obj = new TransitCalculator(5, 12);
    }
}