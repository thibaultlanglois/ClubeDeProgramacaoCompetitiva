import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


class Main {

	private Queue<Tree[]> forests;
	private Tree maxRight;
	private Tree maxLeft;
	private Tree maxUp;
	private Tree maxDown;
	private StringBuilder sb;
	
	public Main(Queue<Tree[]> forests) throws FileNotFoundException {
		this.forests = forests;
		sb = new StringBuilder();
		solveProblem();
	}
	
	
	public void solveProblem() {
		int forestCounter = 1;
		while(!forests.isEmpty()) {
			findSolution(forests.peek(), forestCounter);
			forests.poll();
			forestCounter++;
			if(!forests.isEmpty()) {
				sb.append("\n\n");
			}
		}
		System.out.println(sb.toString());
	}



	private void findSolution(Tree[] forest, int forestNumber) {
		Queue<Integer> cutTrees = new PriorityQueue<>();
		Queue<Tree> bestSolution = findBestTrees(forest, cutTrees);
		double extraWood = getExtraWood(forest, bestSolution);
		while(!bestSolution.isEmpty()) {
			cutTrees.add(bestSolution.poll().treeNumber);
		}
		solutionLog(cutTrees, forestNumber, extraWood);
	}


	private void solutionLog(Queue<Integer> cutTrees, int forestNumber, double extraWood) {
		sb.append("Forest " + forestNumber + "\nCut these trees:");
		while(!cutTrees.isEmpty()) {
			sb.append(" " + cutTrees.poll());
		}
		sb.append(String.format(Locale.US, "\nExtra wood: %.2f", extraWood));
	}


	private Queue<Tree> findBestTrees(Tree[] forest, Queue<Integer> cutTrees) {
		Queue<Queue<Tree>> possibleSolutions = new LinkedList<>();
		findPossibleSolutions(forest, possibleSolutions);
		
		Queue<Tree> bestSolution = possibleSolutions.poll();
		while(!possibleSolutions.isEmpty()) {
			Queue<Tree> nextSolution = possibleSolutions.poll();
			if(calcTotalValue(nextSolution) < calcTotalValue(bestSolution) ||
			calcTotalValue(nextSolution) == calcTotalValue(bestSolution) && nextSolution.size() < bestSolution.size()) {
				bestSolution = nextSolution;
			}
		}
		
	    return bestSolution;
	}


	private void findPossibleSolutions(Tree[] forest, Queue<Queue<Tree>> possibleSolutions) {
		int numTrees = forest.length;
		for(int mask = 0; mask < (1 << numTrees); mask++) {
			Queue<Tree> choppedTreeTrial = new LinkedList<>();
			for(int i = 0; i < numTrees; i++) {
				if((mask & (1 << i)) != 0) {
					choppedTreeTrial.add(forest[i]);
				}
			}
			if(getExtraWood(forest, choppedTreeTrial) >= 0) {
				possibleSolutions.add(choppedTreeTrial);
			}
		}
	}


	private int calcTotalValue(Queue<Tree> forest) {
		int value = 0;
		Queue<Tree> trialForest = new LinkedList<>(forest);
		while(!trialForest.isEmpty()) {
			value += trialForest.poll().value;
		}
		return value;
	}


	private double getExtraWood(Tree[] forest, Queue<Tree> choppedTrees) {
	    double obtainedWood = 0;
	    Tree[] nextForest = forest;
	    for (Tree t : choppedTrees) {
	        obtainedWood += t.length;
	        nextForest = removeTreeElement(nextForest, t);
	    }
	    return obtainedWood - calcMinLength(nextForest);
	}


	private Tree[] removeTreeElement(Tree[] forest, Tree t) {
		Tree[] trial = new Tree[forest.length-1];
		int counter = 0;
		for(int i = 0; i < forest.length; i++) {
			if(forest[i] != t) {
				trial[counter] = forest[i];
				counter++;
			}
		}
		return trial;
	}


	public void findEdges(Tree[] forest) {
		maxRight = forest[0];
		maxLeft = forest[0];
		maxUp = forest[0];
		maxDown = forest[0];
		for(int i = 1; i < forest.length; i++) {
			if(forest[i].x > maxRight.x || 
				forest[i].x == maxRight.x && forest[i].y < maxRight.y) {
				maxRight = forest[i];
			}
			if(forest[i].y > maxUp.y || 
				forest[i].y == maxUp.y && forest[i].x > maxUp.x) {
				maxUp = forest[i];
			}
			if(forest[i].x < maxLeft.x ||
				forest[i].x == maxLeft.x && forest[i].y > maxLeft.y) {
				maxLeft = forest[i];
			}
			if(forest[i].y < maxDown.y ||
				forest[i].y == maxDown.y && forest[i].x < maxDown.x) {
				maxDown = forest[i];
			}
		}
	}
	
	
	public double calcMinLength(Tree[] forest) {
		if(forest.length == 0)
			return 0;
		findEdges(forest);
		double l = 0;
		
		if(maxUp != maxRight) {
			l += process(forest, maxUp, maxRight);
			if(maxRight != maxDown)
				l += process(forest, maxRight, maxDown);
		}
		else
			l += process(forest, maxUp, maxDown);
		
		if(maxDown != maxLeft) {
			l += process(forest, maxDown, maxLeft);
			if(maxLeft != maxUp)
				l += process(forest, maxLeft, maxUp);
		}
		else
			l += process(forest, maxDown, maxUp);
		
		return l;
	}


	private double process(Tree[] forest, Tree pInitial, Tree pFinal) {
		Tree leftOutTree = findLeftOut(forest, pInitial, pFinal);
		if(leftOutTree != null)
			return distance(pInitial, leftOutTree) + process(forest, leftOutTree, pFinal);
		else
			return distance(pInitial, pFinal);
	}


	private Tree findLeftOut(Tree[] forest, Tree pInitial, Tree pFinal) {
		Queue<Tree> leftOut = new LinkedList<>();
		if(pInitial.x == pFinal.x || pInitial.y == pFinal.y) {
			return null;
		}
		else {
			EquationLine eq = new EquationLine(pInitial, pFinal);
			if(pFinal.x > pInitial.x) {
				for(Tree t : forest) {
					if(!eq.inLine(t) && t.y > eq.getLineY(t))
						leftOut.add(t);
				}
			}
			else {
				for(Tree t : forest) {
					if(!eq.inLine(t) && t.y < eq.getLineY(t))
						leftOut.add(t);
				}
			}
			return leftOut.isEmpty()? null : getOuterTree(leftOut, eq, pInitial, pFinal);
		}
	}


	private Tree getOuterTree(Queue<Tree> leftOut, EquationLine eq, Tree pInitial, Tree pFinal) {
		Tree outer = leftOut.peek();
		for(Tree t : leftOut) {
			if(eq.findAngle(t, pInitial) > eq.findAngle(outer, pInitial)) {
				outer = t;
			}
		}
		return outer;
	}


	private double distance(Tree currentTree, Tree next) {
		return Math.sqrt((next.x-currentTree.x)*(next.x-currentTree.x)+(next.y-currentTree.y)*(next.y-currentTree.y));
	}


	public static class Tree {
		private double x;
		private double y;
		private double value;
		private double length;
		private int treeNumber;

		public Tree(double x, double y, double value, double length, int number) {
			this.x = x;
			this.y = y;
			this.treeNumber = number;
			this.value = value;
			this.length = length;
		}
	}


	public static class EquationLine {
		private double m;
		private double b;
		private static final double EPSILON = 1e-9;
		
		public EquationLine(Tree pInitial, Tree pFinal) {
			m = (pFinal.y-pInitial.y)/(pFinal.x-pInitial.x);
			b = pFinal.y-(m*pFinal.x);
		}
		
		public double findAngle(Tree t1, Tree t2) {
			EquationLine newEq = new EquationLine(t1, t2);
			return Math.abs(Math.atan((this.m-newEq.m)/(1+this.m*newEq.m)));
		}

		public boolean inLine(Tree t) {
			return Math.abs(t.y - getLineY(t)) < EPSILON;
		}

		
		public double getLineY(Tree t) {return m*t.x+b;}
	}


	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			Queue<Tree[]> forests = new LinkedList<>();
			Tree[] sample;
			while(sc.hasNext()) {
				sample = new Tree[sc.nextInt()];
				if(sample.length != 0) {
					for(int i = 0; i < sample.length; i++) {
						sample[i] = new Tree(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), i+1);
					}
					forests.add(sample);
				}
				else {
					break;
				}
			}
			sc.close();
			Main teste = new Main(forests);
		} catch(FileNotFoundException e) {
			System.out.println("Failed to read input.");
		}
	}
}
