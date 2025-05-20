// difficulty: easy
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        // 1)calcular quantos digitos o numero tem
        int temp = x;
        int digitNum = 0;
        while (temp > 0) {
            temp /= 10;
            digitNum++;
        }
        // 2) por os numeros nos Array em Ordem
        int[] digits = new int[digitNum];
        temp = x;
        for (int i = 0; i <= digitNum - 1; i++) {
            digits[i] = temp % 10;
            temp /= 10;
        }
        // 3)comparar o index 0+n e x.length-1-i-n
        for (int i = 0; i < digitNum / 2; i++) {
            if (digits[i] != digits[digitNum - 1 - i]) {
                return false;
            }
        }

        return true;
    }
}
