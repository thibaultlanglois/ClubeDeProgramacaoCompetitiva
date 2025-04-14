import java.util.Scanner;

/* Resolução do problema 573 The Snail do UVa
 *
 * @author Rodrigo Sta. Marta Coelho fc61771
 */
class Main {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        double wellHeight = scan.nextDouble();
        double climbDistance = scan.nextDouble();
        double slideDistance = scan.nextDouble();
        double factor = climbDistance * 0.01 * scan.nextDouble();
        
        while (wellHeight != 0) {            
            double currentHeight = 0;
            int day = 0;
            boolean succeeded = false;
            boolean falling = false;
            
            while (!succeeded && !falling) {
                // daytime
                day++;
                // update currentHeight
                currentHeight += climbDistance;
                // update climbDistance
                climbDistance -= factor;
                if (climbDistance < 0)
                    climbDistance = 0;
                
                // check if succeeded
                if (currentHeight > wellHeight)
                    succeeded = true;
                
                // night time
                
                // fall - update currentHeight
                currentHeight -= slideDistance;
                
                // check if falling
                
                if (currentHeight < 0)
                    falling = true;
            }
            // print if succeeded or failed
            if (succeeded)
                System.out.println("success on day " + day);
            else
                System.out.println("failure on day " + day);
            // update day
            
            
             wellHeight = scan.nextInt();
             climbDistance = scan.nextInt();
             slideDistance = scan.nextInt();
             factor = climbDistance * 0.01 *scan.nextInt();
        }
        
        scan.close();
    }
}
