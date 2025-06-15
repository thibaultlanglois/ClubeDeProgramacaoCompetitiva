import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

/**
 * Problem #11403
 * 
 * RESULT:
 * >> Accepted -> Run Time: 0.630 [Submission 30425482]
 * 
 * @author Pedro Reinaldo Mendes (Dot) -> Online Judge ID: 1695802
 * @version 1.0
 */
class Main {

    private static String EOL = System.lineSeparator();
    
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            boolean isFirstCase = true;
            String line;
            
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" ");
                String x = parts[0];
                String y = parts[1];
                
                if (x.equals("0") && y.equals("0")) {
                    break;
                }
                
                if (!isFirstCase) {
                    System.out.println();
                }
                else {
                    isFirstCase = false;
                }
                
                resolve(x, y);

            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    private static void resolve(String x, String y) {

        String product = new BigInteger(x, 2).multiply(new BigInteger(y, 2)).toString(2);
        
        List<LineEntry> entries = new ArrayList<>();
        entries.add(new LineEntry(x, 0));
        entries.add(new LineEntry(y, 0));
        entries.add(new LineEntry(hyphens(Math.max(x.length(), y.length())), 0));
        
        for (int i = y.length() - 1; i >= 0; i--) {

            int shiftLevel = y.length() - 1 - i;
            String line = (y.charAt(i) == '1') ? x : zeros(x.length());
            entries.add(new LineEntry(line, shiftLevel));

        }
        
        entries.add(new LineEntry(hyphens(product.length()), 0));
        entries.add(new LineEntry(product, 0));
        
        int maxWidth = product.length();
        
        StringBuilder output = new StringBuilder();

        for (LineEntry entry : entries) {
            output.append(spaces(maxWidth - entry.content.length() - entry.shift)).append(entry.content).append(EOL);
        }
        
        System.out.print(output.toString());

    }
    
    
    private static String hyphens(int n) {
        return (n != 0)? new String(new char[n]).replace('\0', '-') : "";
    }
    
    private static String spaces(int n) {
        return (n != 0)? new String(new char[n]).replace('\0', ' ') : "";
    }
    
    private static String zeros(int n) {
        return (n != 0)? new String(new char[n]).replace('\0', '0') : "";
    }
    
    private static class LineEntry {

        String content;
        int shift;
        
        LineEntry(String content, int shift) {
            this.content = content;
            this.shift = shift;
        }

    }
}
