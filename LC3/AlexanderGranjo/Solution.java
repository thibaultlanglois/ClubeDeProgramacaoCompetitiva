// difficulty: medium

class Solution {

    /**
     * Encontra o comprimento da maior substring sem chars repetidos
     */
    public int lengthOfLongestSubstring(String s) {
        int trueCounter = 0;
        int start = 0;
        char[] seen = new char[s.length()];
        int counter = 0;

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            while (isPresent(currentChar, seen, counter)) {
                removeChar(seen, s.charAt(start), counter);
                start++;
                counter--;
            }

            seen[counter] = currentChar;
            counter++;
            trueCounter = Math.max(trueCounter, counter);
        }

        return trueCounter;
    }

    /**
     * Verifica se um char estÃ¡ presente no array fornecido
     */
    public boolean isPresent(char c, char[] array, int length) {
        for (int i = 0; i < length; i++) {
            if (array[i] == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove um char do array ajustando os elementos restantes
     */
    public void removeChar(char[] array, char c, int length) {
        for (int i = 0; i < length; i++) {
            if (array[i] == c) {
                for (int j = i; j < length - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[length - 1] = '\u0000';
                break;
            }
        }
    }
}
