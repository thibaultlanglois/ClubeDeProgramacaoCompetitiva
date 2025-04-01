import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.Scanner;

class Main {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		 	Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
			int n = sc.nextInt();
			sc.nextLine();
		
			for(int i = 0; i < n; i++) {
					String s = sc.nextLine();
					if(Eng(s)) {
						System.out.println("Yes");
					}else {
						System.out.println("No");
					}
			}
		sc.close();
	}
	
	public static boolean Eng(String s) {
		Stack<Character> st = new Stack<>();
        int n = s.length();
        if(n % 2 != 0 && n != 0) return false;
        for(int i = 0; i < n; i++) {
            char currentChar = s.charAt(i);
            if(currentChar == '(' || currentChar == '[')
                st.push(currentChar);
            else if (currentChar == ')' || currentChar == ']') {
                if(st.isEmpty()) 
                	return false;
                if(currentChar == ')' && st.peek() != '(' || currentChar == ']' && st.peek() != '[') 
                	return false;
                st.pop();
            } 
        }
        return s.isEmpty();
    }
}
