import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* Resolução do problema 11340 Newspaper do Uva
 * Rodrigo Sta. Marta Coelho fc61771
 */
class Main {
    
    private static final int READ_INT_MARK_LIMIT = 3;

    public static void main(String[] args) {
        try {
            
        BufferedReader reader = new BufferedReader(new 
InputStreamReader(System.in));
        int tests = readInt(reader);
        skipLine(reader);
        for (int currentTest = 0; currentTest < tests; currentTest++) {
                
            // Map containing characters with values that are not zero
            Map<Character, Integer> map = new TreeMap<>();
            
            // number of chars with values
            int numKeys = readInt(reader);
            skipLine(reader);
                
            // creating map
            for (int i = 0; i < numKeys; i++) {
                char key = (char) reader.read();
                reader.skip(1);
                map.put((char) key, readInt(reader));
                skipLine(reader);
            }
            
            // reading text article and count total money
            int lines = readInt(reader);
            skipLine(reader);
            int lineCount = 0;
            
            double total = 0;    
            int ascii;   
            while (lineCount < lines) {
                ascii = reader.read();
                
                Integer value = map.get((char) ascii);
                total += value != null ? value : 0;

                if (ascii == 10) {
                    lineCount++;
                }
            }
            
            System.out.printf(Locale.US, "%.2f$\n", total * 0.01);
        }
        
        } 
        catch (Exception e) {
            System.out.println("Exception: " +  e );
        }
        
    }
    
    /**
     * Reads a multi-character, non negative, integer from BufferedReader
     * It also reads the character after the integer.
     * @param reader the BufferedReader to use
     * @return the parsed integer from reader
     * @throws IOException
     */
    private static int readInt(BufferedReader reader) throws IOException {
        
        int current = reader.read();
        //while not a number
        while (48 > current || current > 57) {
            current = reader.read();
        }
        //first digit of number read, continue until no digit is found
        int value = 0;
        while (48 <= current && current <= 57) {
            value = value * 10 + current - 48;
            reader.mark(READ_INT_MARK_LIMIT);
            current = reader.read();
        }
        reader.reset();
        return value;
    }
    
    private static void skipLine(BufferedReader reader) throws 
IOException{
        while (reader.read() != 10) {
            
        }
    }
}

