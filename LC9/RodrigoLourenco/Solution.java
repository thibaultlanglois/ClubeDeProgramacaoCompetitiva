class Solution {
    public boolean isPalindrome(int x) {
        char[] xArray = String.valueOf(x).toCharArray();

        if (xArray[0] == '-'){return false;}
        else {
        int lastDigit = xArray.length - 1;


        for (int i = 0; i < xArray.length / 2; i++){
            if(xArray[i] != xArray[lastDigit - i]){return false;}
        }
        }
        return true;
    }
}
