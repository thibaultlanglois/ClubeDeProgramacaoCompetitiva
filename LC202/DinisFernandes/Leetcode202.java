import java.util.ArrayList;
import java.util.List;

public class Leetcode202{
    public boolean isHappy(int n) {
        List<Integer> nums = new ArrayList<>();
        while (!nums.contains(n)) {
            nums.add(n);
            int lastNum = nums.getLast();
            n = 0;
            while (lastNum > 0) {
                n += Math.pow(lastNum % 10, 2);
                lastNum /= 10;
            }
            if (n == 1) {
                return true;                
            }
        }
        return false;
    }
}
