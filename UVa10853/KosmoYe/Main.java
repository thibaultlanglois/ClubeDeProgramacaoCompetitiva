import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        String [] winners = new String[cases];

        for (int i = 0; i < cases; i++) {
            String[] inputs = br.readLine().split("\\s+");
            int L = Integer.parseInt(inputs[0]);
            int Amin = Integer.parseInt(inputs[1]);
            int Amax = Integer.parseInt(inputs[2]);
            int Bmin = Integer.parseInt(inputs[3]);
            int Bmax = Integer.parseInt(inputs[4]);
            winners[i] = determineWinner(L, Amin, Amax, Bmin, Bmax);
        }

            for (String winner : winners) {
                System.out.println(winner);
            }
                System.exit(0);
    }

    public static String determineWinner(int length, int Amin, int Amax, int Bmin, int Bmax) {

        boolean playerA = true;
        boolean canWin = false;
        String winner = "";


        while(length > 0) {

            if(playerA) {
                if(length - Amax <= 0) {
                    length -= Amax;
                    winner = "A";
                } else {
                    for(int hit = Amin; hit <= Amax; hit++) {
                        int simulation = length - hit;
                        simulation -= Bmin;
                        if(simulation - Amax <= 0 && simulation > 0) {
                            length -= hit;
                            canWin = true;
                            break;
                        }
                    }
                    if(!canWin) {
                        length -= Amin;
                    }
                    playerA = false;
                }

            } else {
                if(length - Bmax <= 0) {
                    length -= Bmax;
                    winner = "B";
                } else {
                    for(int hit = Bmin; hit <= Bmax; hit++) {
                        int simulation = length - hit;
                        simulation -= Amin;
                        if(simulation - Bmax <= 0 && simulation > 0) {
                            length -= hit;
                            canWin = true;
                            break;
                        }
                    }
                    if(!canWin) {
                        length -= Bmin;
                    }
                    playerA = true;
                }
            }

        }
        return winner;
    }


}
