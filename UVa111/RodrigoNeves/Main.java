import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int length = Integer.parseInt(sc.nextLine());
        while (length > 0 && sc.hasNextLine()) {

            String input = sc.nextLine();
            
            String[] line = input.split(" ");
            int[] correctOrder = new int[length];
            for (int i = 0; i < length; i++) {
                int index = Integer.parseInt(line[i]) - 1;
                correctOrder[index] = i + 1;
            }
            while (sc.hasNextLine()) {
                String in = sc.nextLine();
                String[] studentInput = in.split(" ");
                
                if (studentInput.length == 1) {
                    if (!in.isEmpty()) {
                        length = Integer.parseInt(studentInput[0]);
                    } else {
                        length = 0;
                    }
                    break;
                }
                int[] studentOrder = new int[studentInput.length];

                for (int i = 0; i < length; i++) {
                    int index = Integer.parseInt(studentInput[i]) - 1;
                    studentOrder[index] = i + 1;
                }
                
                int score = max(correctOrder, studentOrder);
                System.out.println(score);
            }
        }
        sc.close();
        System.exit(0);
    }

    public static int max(int[] correct, int[] student) {

        int[][] track = new int[correct.length + 1][correct.length + 1];
        int length = track.length;

        for (int i = 1; i < length; i++) {
            for (int j = 1; j < length; j++) {
                if (student[i - 1] == correct[j - 1]) {
                    track[i][j] = track[i - 1][j - 1] + 1;
                }
                else {
                    track[i][j] = Math.max(track[i][j - 1], track[i - 1][j]);
                }
            }
        }
        return track[length - 1][length - 1];
    }

}
