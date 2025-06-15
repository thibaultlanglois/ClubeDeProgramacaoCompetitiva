import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int cases = Integer.parseInt(br.readLine().trim());
        while (cases-- > 0) {
            boolean[] genuine = new boolean[12]; // true if coin is genuine
            int[] suspect = new int[12]; // heavier: positive, lighter: negative
            for (int i = 0; i < 3; i++) {
                String[] line = br.readLine().split(" ");
                char[] left = line[0].toCharArray();
                char[] right = line[1].toCharArray();
                String result = line[2];

                if ("even".equals(result)) {
                    for (char c : left) genuine[c - 'A'] = true;
                    for (char c : right) genuine[c - 'A'] = true;
                } else {
                    int sign = "up".equals(result) ? 1 : -1;
                    for (char c : left) suspect[c - 'A'] += sign;
                    for (char c : right) suspect[c - 'A'] -= sign;
                }
            }

            int max = 0;
            char coin = '\0';
            String weight = "";
            for (int i = 0; i < 12; i++) {
                if (!genuine[i] && Math.abs(suspect[i]) > max) {
                    max = Math.abs(suspect[i]);
                    coin = (char) ('A' + i);
                    weight = suspect[i] > 0 ? "heavy" : "light";
                }
            }
            System.out.println(coin + " is the counterfeit coin and it is " + weight + ".");
        }
    }
}
