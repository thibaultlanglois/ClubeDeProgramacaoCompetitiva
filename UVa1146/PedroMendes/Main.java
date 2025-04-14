import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Problem #1146
 * 
 * RESULT:
 * >> Time limit exceeded
 * 
 * @author Pedro Reinaldo Mendes (Dot)
 * @version 0.91
 */
class Main {
	
	private static int[][] times;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String v = br.readLine();

		while (v != null && !v.equals("")) {
			
			times = new int[Integer.parseInt(v)][2];

			for (int i = 0; i < times.length; i++) {
				String[] line = br.readLine().split(" ");
				times[i][0] = Integer.parseInt(line[0]);
				times[i][1] = Integer.parseInt(line[1]);
			}
			
			System.out.println(findMaxGap(0, Integer.MAX_VALUE));
			
			v = br.readLine();
		}
		
		br.close();
		
	}
	
	private static int findMaxGap(int min, int max) {
		
		int value = (min + max) / 2;
		
		if (value == min) {
			return min;
		}
		
		return (isPossible(value))? findMaxGap(value, max) : findMaxGap(min, value);
		
	}
	
	private static boolean isPossible(int gap) {
		
		boolean[] isLate = new boolean[times.length];
		
		int xTime;
		int  yTime;
		int  iError = 0;
		int  originalx;
		boolean hadProblem = false;
		
		for(int x = 0; x < times.length; x++) {
			
			originalx = x;
			xTime = times[x][(isLate[x])? 1 : 0]; 
			
			for (int y = x - 1; y >= 0; y--) {
				
				yTime = times[y][(isLate[y])? 1 : 0];
				
				if(Math.abs(xTime - yTime) < gap) {
					
					if (isLate[x]) {
						
						if (hadProblem) {
							
							if (isLate[iError]) {
								
								x = planB(x, isLate);
								if (x == -2)
									return false;
								hadProblem = false;
								break;
								
							} else {
								
								isLate[iError] = true;
								x = iError - 1;
								everythingToFalse(iError + 1, isLate.length, isLate);
								hadProblem = false;
								break;
								
							}
							
						} else {
							
							x = planB(x, isLate);
							if (x == -2)
								return false;
							hadProblem = false;
							break;
							
						}
						
					} else {
						
						hadProblem = true;
						iError = y;
						isLate[x] = true;
						x--;
						break;
						
					}
					
				}
				
			}
			
			if(originalx == x)
				hadProblem = false;
				
		}
		
		return true;
		
	}

	private static int planB(int x, boolean[] isLate) {
		
		for (int i = x; i >= 0; i--) {
			
			if (!isLate[i]) {
				isLate[i] = true;
				everythingToFalse(i, isLate.length, isLate);
				return i - 1;
			}
			
		}
		
		return -2;
	}

	private static void everythingToFalse(int start, int end, boolean[] arr) {
		
		for (int i = start; i < end; i++) {
			arr[i] = false;
		}
		
	}
}
