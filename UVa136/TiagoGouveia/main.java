class Main {
    // UVa 136
    public static void main(String[] args) {
        int n = 1500;
        int[] numerosFeios = new int[n];
        numerosFeios[0] = 1;

        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        int proximo2 = 2;
        int proximo3 = 3;
        int proximo5 = 5;

        for (int i = 1; i < n; i++) {
            int proximoFeio = Math.min(proximo2, Math.min(proximo3, proximo5));
            numerosFeios[i] = proximoFeio;

            if (proximoFeio == proximo2) {
                i2++;
                proximo2 = numerosFeios[i2] * 2;
            }
            if (proximoFeio == proximo3) {
                i3++;
                proximo3 = numerosFeios[i3] * 3;
            }
            if (proximoFeio == proximo5) {
                i5++;
                proximo5 = numerosFeios[i5] * 5;
            }
        }

        System.out.println("The 1500'th ugly number is " + numerosFeios[n - 1] + ".");
    }
}
