// -*- coding: utf-8 -*-

package fcul.cprog.uva101;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author tl
 */
class Main {
    private static int nbBlocks;
    private static final int MAX_BLOCKS = 25;
    private static final int [][] blocks = new int[MAX_BLOCKS][MAX_BLOCKS];
    private static final int empty = -1;
    
    public static void main (String [] args) throws FileNotFoundException {
        // Scanner in = new Scanner(new FileReader("data/input9.dat"));        
        Scanner in = new Scanner(System.in);   
        nbBlocks = in.nextInt();
        resetBlocks();
        String command = in.next();
        while (!command.equals("quit")) {
            // System.out.print(command + " ");
            int from = in.nextInt();
            // System.out.print(from + " ");
            String how = in.next();
            // System.out.print(how + " ");
            int to = in.nextInt();
            // System.out.println(to);
            switch (command) {
                case "move":
                    switch (how) {
                        case "onto": moveOnto(from,to); break;
                        case "over": moveOver(from, to); break;
                    } 
                    break;
                case "pile": switch (how) {
                    case "onto": pileOnto(from, to); break;
                    case "over": pileOver(from, to); break;
                } 
                break;
            }
            command = in.next();
        }
        in.close();
        print("");
    }

    private static void resetBlocks() {
        for (int i = 0; i < nbBlocks; i++) {
            blocks[i][0] = i;
            for (int j = 1; j < nbBlocks; j++) {
                blocks[i][j] = empty;
            }
        }
    }
    
    private static int[] getPosition(int block) {
        // where is the block?
        boolean found = false;
        int destRow = -1, destCol = -1;
        int i = 0;
        while (i < nbBlocks && ! found) {
            int j = 0;
            while (j < nbBlocks && !found) {
                if (blocks[i][j] == block) {
                    found = true;
                    destRow = i;
                    destCol = j;
                }
                j++;
            }
            i++;
        }
        int [] pos = new int[2];
        pos[0] = destRow;
        pos[1] = destCol+1;
        return pos;
    }
    
    private static void moveBlocksBack(int row, int col) {
        int j = col;
        while (blocks[row][j] != empty && j < nbBlocks) {
            blocks[blocks[row][j]][0] = blocks[row][j];
            blocks[row][j] = empty;
            j++;
        }
    }
    
    private static void moveOnto(int from, int to) {
        if (from == to) return;
        int[] dest = getPosition(to);
        // if the destination block has something on it move these blocks to 
        // their original position.
        moveBlocksBack(dest[0],dest[1]);
        // where is the from block ?
        boolean found = false;
        int i = 0;
        
        while (i < nbBlocks && !found) {
            int j = 0;
            while (blocks[i][j] != empty && !found) { 
                if (blocks[i][j] == from) {
                    found = true;
                    if (i != dest[0]) {
                        int k = j+1;
                        //int jj = dest[1];
                        // move the blocks that are above from to their original position
                        moveBlocksBack(i,k);
                        blocks[dest[0]][dest[1]] = blocks[i][j];
                        blocks[i][j] = empty;
                    }
                }
                j++;
            }
            i++;
        }
        
    }

    private static void moveOver(int from, int to) {
        if (from == to) return;
        // where is the to block?
        int [] dest = getPosition(to);
        boolean found = false;
        int i = 0;
        while (i < nbBlocks && !found) {
            int j = 0;
            while (blocks[i][j] != empty && !found) { 
                if (blocks[i][j] == from) {
                    found = true;
                    if (i != dest[0]) {
                        moveBlocksBack(i,j+1);
                        while (blocks[dest[0]][dest[1]] != empty) dest[1]++;
                        blocks[dest[0]][dest[1]] = blocks[i][j];
                        blocks[i][j] = empty;
                    }
                }
                j++;
            }
            i++;
        }
    }

    private static void pileOver(int from, int to) {
        int [] dest = getPosition(to);
        boolean found = false;
        int i = 0;
        while (i < nbBlocks && ! found) {
            int j = 0;
            while (j < nbBlocks && !found) {
                if (blocks[i][j] == from) {
                    found = true;
                    if (i != dest[0]) { // this is not clear in the problem description
                        int k = dest[1];
                        while (blocks[dest[0]][k] != empty) k++;   
                        while (blocks[i][j] != empty) {
                            blocks[dest[0]][k] = blocks[i][j];
                            blocks[i][j] = empty;
                            j++; k++;
                        }
                    }
                }
                j++;
            }
            i++;
        }
    }

    private static void pileOnto(int from, int to) {
        int[] dest = getPosition(to);
        // blocks above destination are placed at their original position.
        moveBlocksBack(dest[0],dest[1]);
        int i = 0; boolean found = false;
        while (i < nbBlocks && ! found) {
            int j = 0;
            while (j < nbBlocks && !found) {
                if (blocks[i][j] == from) {
                    found = true;
                    if (i != dest[0]) {
                        int k = 0;
                        while (blocks[dest[0]][k] != empty) k++;
                        while (blocks[i][j] != empty) {
                            blocks[dest[0]][k] = blocks[i][j];
                            blocks[i][j] = empty;
                            j++; k++;
                        }
                    }
                }
                j++;
            }
            i++;
        }
    }
    
    private static void print(String a) {
        // System.out.println("---------------");
        // System.out.println(a);
        for (int i = 0; i < nbBlocks; i++) {
            int j = 0;
            System.out.print(i + ":");
            while (blocks[i][j] != empty /* j < nbBlocks*/) {
                System.out.print(" " + blocks[i][j]);
                j++;
            }
            System.out.println();
        }
	// System.out.println();        
    }

}
