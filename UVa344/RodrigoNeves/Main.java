import java.util.Scanner;

class Main {
    
    private final static Character [] rom = { 'i', 'v', 'x', 'l', 'c'};
    private final static String[] digits = { "", "i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix" };
    private final static String[] tens = {"", "x", "xx", "xxx", "xl", "l", "lx", "lxx", "lxxx", "xc", };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            int value = Integer.parseInt(sc.nextLine());
            if (value == 0)
                break;
            int[] result = result(value);
            System.out.println(String.format("%d: %d i, %d v, %d x, %d l, %d c", value, result[0], result[1], result[2],
                    result[3], result[4]));

        }
    }

    private static int[] result(int value) {

        int i = 1;
        int[] count = new int[5];
        StringBuilder sb = new StringBuilder();
        while (i <= value) {
           
            if(i < 10) {
                sb.append(digits[i]);
            }
            else  {
                if(i % 10 == 0) {
                    sb.append(tens[i/10]);                 
                }
                else {
                    sb.append(digits[i % 10]);
                    sb.append(tens[i / 10]);
                    sb.reverse();
                }
            }
            i++;
        }
        
        for(int k = 0; k < sb.length(); k++) {
            for(int j = 0; j < rom.length; j++) {
                if(sb.charAt(k) == rom[j]) {
                    count[j]++;
                }
            }
        }
        return count;
    }

}
