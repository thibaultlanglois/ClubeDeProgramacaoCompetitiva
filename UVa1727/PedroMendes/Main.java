import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Problem #1727
 * 
 * RESULT:
 * >> Accepted -> Run Time: 0.260 [Submission 30437115]
 * 
 * @author Pedro Reinaldo Mendes (Dot) -> Online Judge ID: 1695802
 * @version 1.0
 */
class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> daysInMonth = new HashMap<>();
        daysInMonth.put("JAN", 31);
        daysInMonth.put("FEB", 28); // Not a leap year
        daysInMonth.put("MAR", 31);
        daysInMonth.put("APR", 30);
        daysInMonth.put("MAY", 31);
        daysInMonth.put("JUN", 30);
        daysInMonth.put("JUL", 31);
        daysInMonth.put("AUG", 31);
        daysInMonth.put("SEP", 30);
        daysInMonth.put("OCT", 31);
        daysInMonth.put("NOV", 30);
        daysInMonth.put("DEC", 31);

        Map<String, Integer> dayOfWeekIndex = new HashMap<>();
        dayOfWeekIndex.put("SUN", 0);
        dayOfWeekIndex.put("MON", 1);
        dayOfWeekIndex.put("TUE", 2);
        dayOfWeekIndex.put("WED", 3);
        dayOfWeekIndex.put("THU", 4);
        dayOfWeekIndex.put("FRI", 5);
        dayOfWeekIndex.put("SAT", 6);

        int T = scanner.nextInt();
        scanner.nextLine(); 

        for (int t = 0; t < T; t++) {
            String mth = scanner.next();
            String day = scanner.next();

            int numDaysInMonth = daysInMonth.get(mth);
            int firstDayIndex = dayOfWeekIndex.get(day);
            int weekendDays = 0;
            
            for (int i = 0; i < numDaysInMonth; i++) {
                int currentDayOfWeek = (firstDayIndex + i) % 7;
                if (currentDayOfWeek == dayOfWeekIndex.get("FRI") || currentDayOfWeek == dayOfWeekIndex.get("SAT")) {
                    weekendDays++;
                }
            }
            System.out.println(weekendDays);
        }
        scanner.close();
    }
}
