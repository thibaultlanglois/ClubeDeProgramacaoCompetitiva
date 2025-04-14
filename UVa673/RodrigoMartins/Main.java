
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Stack;

class Main {
    
    public static void main(String[] args) throws FileNotFoundException{
        Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int numberOfStrings = s.nextInt();
        s.nextLine();
        for(int i = 0; i < numberOfStrings;i++) {
            String str = s.nextLine();
            System.out.println(isValid(str) ? "Yes" : "No");
        }
        s.close();
    }

    public static boolean isValid(String str) {
        Stack<Character> s = new Stack<>();
        int n = str.length();
        if(n % 2 != 0 && n != 0) return false;
        for(int i = 0; i < n;i++) {
            char currentChar = str.charAt(i);
            if(currentChar == '(' || currentChar == '[')
                s.push(currentChar);
            else if (currentChar == ')' || currentChar == ']') {
                if(s.isEmpty()) return false;
                if(currentChar == ')' && s.peek() != '(' || currentChar == ']' && s.peek() != '[') return false;
                s.pop();
            }
            
            
        }
        return s.isEmpty();
    }
}
