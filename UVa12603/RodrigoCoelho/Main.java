import java.util.Locale;
import java.util.Scanner;

/* Resolução do problema 12603 BouncingBowlingBall do Uva
 * Rodrigo Sta. Marta Coelho fc61771
 * 
 * TODO esquema a explicar calculos
 * Todas as variaveis estão em cm (radianos no caso de angle)
 */
class Main {
    
    private final static int WIDTH_OF_LANE = 85;
    private final static int RADIUS_OF_PIN = 6;
    private final static int RADIUS_OF_BALL = 10;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.useLocale(Locale.US);
        int numTestes = scan.nextInt();
        
        for (int teste = 0; teste < numTestes; teste++) {
            final double totalDistanceX = scan.nextDouble() * 100;
            final double angle = Math.toRadians( scan.nextInt() );
            final double l = totalDistanceX * Math.tan(angle);
            
            final double hitY = (l - WIDTH_OF_LANE / 2.0) % WIDTH_OF_LANE;
            
            final double d = RADIUS_OF_PIN / Math.cos(angle) - RADIUS_OF_PIN;

            final double e = RADIUS_OF_BALL / Math.cos(angle);
            
            final double max = WIDTH_OF_LANE / 2.0 + RADIUS_OF_PIN + d + e;
            final double min = WIDTH_OF_LANE / 2.0 - RADIUS_OF_PIN - d - e;
            System.out.println( min <= hitY && hitY <= max ? "yes" : "no");
        }
        
        scan.close();
    }
}
