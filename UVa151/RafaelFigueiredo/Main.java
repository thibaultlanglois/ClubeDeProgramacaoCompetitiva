import java.util.LinkedList;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfRegions = input.nextInt();
        while (numberOfRegions != 0) {
            boolean foundM = false;
            int m = 1;
            while (!foundM) {
                LinkedList<Integer> list = new LinkedList<>();
                for (int i = 1; i <= numberOfRegions; i++) {
                    list.add(i);
                }
                int place = 0;
                while (list.size() > 1 && list.get(place) != 13) {
                    list.remove(place);
                    place = (place + m - 1) % list.size();
                }
                if (list.size() == 1) {
                    System.out.println(m);
                    foundM = true;
                }
                m++;
            }
            numberOfRegions = input.nextInt();
        }
    }
}
