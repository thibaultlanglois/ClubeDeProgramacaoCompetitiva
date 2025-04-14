class RomanToInt {

    private int romanValues(char c){
        switch(c){
                case 'I':
                return 1;
                 case 'V':
                return 5;
                 case 'X':
                return 10;
                 case 'L':
                return 50;
                 case 'C':
                return 100;
                 case 'D':
                return 500;
                 case 'M':
                return 1000;
                default:
                return 0;

            }
    } 
    public int romanToInt(String s) {

        char[] sChar = s.toCharArray(); 
        int result = 0;
        int currentIndex = romanValues(sChar[0]);

        for (int i = 1; i < sChar.length; i++){
            int nextIndex = romanValues(sChar[i]);
            
            if (currentIndex < nextIndex){
                result -= currentIndex;
            }
            else {
                result += currentIndex;
            }

            currentIndex = nextIndex;
        }
        result += currentIndex;
        return result;
    }
}
