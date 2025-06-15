import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

class Main {
    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            NumberFormat numFormat = new DecimalFormat();
            numFormat = new DecimalFormat("0.000E0");
            BigDecimal decimal = new BigDecimal(0.5);
            StringBuilder sb = new StringBuilder();
    
            int r = Integer.parseInt(br.readLine());
            Queue <Integer> q = new LinkedList<>();
    
            for(int i = 0; i < r; i++) {
                q.add(Integer.parseInt(br.readLine()));
            }
    
            for(int i = 0; i < r; i++) {
                sb.append("2^-" + q.peek() + " = " + numFormat.format(decimal.pow(q.remove())).replace(',', '.')+ "\n");
            }
    
            System.out.print(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }


    }
}
