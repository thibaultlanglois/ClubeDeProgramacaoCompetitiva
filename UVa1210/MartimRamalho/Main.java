import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {

public Main(Queue<Integer> input) {
StringBuilder sb = new StringBuilder();
while(input.size() > 1) {
sb.append(howManyRepresentations(input.poll()) + "\n");
}
sb.append(howManyRepresentations(input.poll()));
System.out.println(sb.toString());
}


public int howManyRepresentations(int i) {
int howMany = isPrime(i)? 1 : 0;
int currentNum = 2;
while(currentNum <= i/2) {
if(hasSumOfPrimeNumbers(currentNum, i)) {
howMany++;
}
currentNum = findNextPrime(currentNum);
}
return howMany;
}


public boolean hasSumOfPrimeNumbers(int initial, int total) {
int currentNum = initial;
int sum = currentNum;
while(sum < total) {
currentNum = findNextPrime(currentNum);
sum += currentNum;
}
if(sum == total)
return true;
else
return false;
}


public int findNextPrime(int i) {
int number = i+1;
while(!isPrime(number)) {
number++;
}
return number;
}


public boolean isPrime(int num) {
if(num % 2 == 0 && num != 2)
return false;
for(int i = 3; i*i <= num; i += 2) {
if(num % i == 0) {
return false;
}
}
return true;
}


public static void main(String[] args) {
try {
Scanner sc = new Scanner(System.in);
Queue<Integer> input = new LinkedList<>();
while(sc.hasNext()) {
int nextInput = sc.nextInt();
if(nextInput != 0) {
input.add(nextInput);
}
else {
break;
}
}
Main solution = new Main(input);
sc.close();
} catch (Exception e) {
System.out.println("Error reading input.");
}
}
}
