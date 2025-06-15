import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String line = br.readLine();
            if(line==null)break;
            int n=Integer.parseInt(line.trim());
            if(n==0)break;

            double[] coef = new double[n+1];
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int i=0;i<=n;i++)
                coef[i]=Double.parseDouble(st.nextToken());

            st=new StringTokenizer(br.readLine());
            double s=Double.parseDouble(st.nextToken()), e=Double.parseDouble(st.nextToken());

            double integral=integrate(coef,s,e);
            double avg=integral/(e-s);

            System.out.printf("%.3f\n",avg);
        }
    }

    static double integrate(double[] coef, double s, double e){
        double res=0;
        int n=coef.length-1;
        for(int i=0;i<=n;i++){
            int pow=n-i+1; // new exponent after integration
            res += coef[i]/pow*(Math.pow(e,pow)-Math.pow(s,pow));
        }
        return res;
    }
}
