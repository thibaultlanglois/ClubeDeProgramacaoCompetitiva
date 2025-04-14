public class Leetcode367 {
    public boolean isPerfectSquare(int num) {
        long lowerValue = 0;
        long value;
        long higherValue = num;
        if (num == 1) {
            return true;
        }
        while (higherValue - lowerValue > 1) {
            value = lowerValue + (higherValue - lowerValue) / 2;
            long valueSqr = value * value;
            if (valueSqr == num) {
                return true;
            }
            else if (valueSqr > num) {
                higherValue = value;
            }
            else {
                lowerValue = value;
            }
        }
        return false;
    }
}
