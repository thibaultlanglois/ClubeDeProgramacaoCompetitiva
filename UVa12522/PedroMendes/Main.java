import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
// UVa 12522
/**
 * Problem #12522 - "The Imperial Problem"
 * 
 * RESULT:
 * >> Accepted -> Run Time: 0.280 [Submission 30368876]
 * 
 * @author Pedro Reinaldo Mendes (Dot) -> Online Judge ID: 1695802
 * @version 1.1
 */
class Main {
    
    private static final String[][] conversion = {{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                                                  {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                                                  {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                                                  {"", "M", "MM", "MMM", "", "", "", "", "", ""}};
    
    public static void main(String[] args) {
        
        // We'll always suppose that the input is valid and that the correspondent number of each Roman numeral is less than 4000
        // We'll also suppose that four-times repeated numerals are always 'I', 'X' or 'C'
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null && !line.equals("*")) {
                
                System.out.println(resolveProblem(line));
                
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public static String resolveProblem(String wrong) {
        
        char[] wrongChars = wrong.toCharArray();
        String correct = intToRoman(romanToInt(wrongChars));
        char[] correctChars = correct.toCharArray();
        
        // For the sake of clarity, we will use the same variable names as in the problem statement
        int e;
        int c;
        int m = wrong.length();
        int n = correct.length();
        int beste = 128;
        int bestc = 128;
        
        for (int i = 0; i <= m - n; i++) {
            
            c = 0;
            
            for (int j = 0; j < n; j++) {
                if (correctChars[j] != wrongChars[i + j]) {
                    c++;
                }
            }
            
            e = m - (n - c);
            
            if (e + c < beste + bestc) {
                beste = e;
                bestc = c;
            }
            else if (e + c == beste + bestc && e < beste) {
                beste = e;
                bestc = c;
            }
            // Else, we don't update our best values
            
        }
        
        if (beste == 128 || bestc == 128) {
            beste = 0;
            bestc = 0;
        }
        
        return beste + " " + bestc;
    }
    
    public static int romanToInt(char[] chars) {
        
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            
            switch (chars[i]) {
                case 'I': num += 1; break;
                case 'V': num += 5; break;
                case 'X': num += 10; break;
                case 'L': num += 50; break;
                case 'C': num += 100; break;
                case 'D': num += 500; break;
                case 'M': num += 1000; break;
            }
            
        }
        
        return num;
    }
    
    public static String intToRoman(int num) {
        
        StringBuilder roman = new StringBuilder("");
        
        for (int i = 0; num > 0; i++) {
            roman.insert(0, conversion[i][num % 10]);
            num /= 10;
        }
        
        return roman.toString();
    }
    

}
