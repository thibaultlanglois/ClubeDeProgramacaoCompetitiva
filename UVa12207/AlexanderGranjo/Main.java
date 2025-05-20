import java.io.*;
import java.util.ArrayDeque;
// UVa 12207
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nrCases = 1;
        
        while (true) {
            String[] info = br.readLine().split(" ");
            int ppl = Math.min(Integer.parseInt(info[0]), 1000);  
            int comms = Math.min(Integer.parseInt(info[1]), 1000);  
            
            if (ppl + comms == 0) break;
            
            ArrayDeque<Integer> queue = new ArrayDeque<>();  
            for (int i = 0; i < ppl; i++) {                   
                queue.addLast(i+1);
            }
            
            System.out.println("Case " + nrCases + ":");
            
            
            while (comms-- > 0){
                String[] currCom = br.readLine().split(" ");
                
                
                    if (currCom[0].equals("N")) {
                        int current = queue.pollFirst();
                        System.out.println(current);
                        queue.addLast(current);
                        
                    } else if (currCom[0].equals("E")) {
                        int prio = Integer.parseInt(currCom[1]);
                        queue.removeFirstOccurrence(prio);
                        queue.addFirst(prio);
                    }
                
            }
            
            nrCases++;
        }
        
        
        
    }
}
