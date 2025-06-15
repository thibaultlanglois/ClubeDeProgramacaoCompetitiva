
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

class Main {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            List<String> palavras = new ArrayList<>();
            String linha;

            while(scanner.hasNextLine() && !(linha = scanner.nextLine()).equals("#")) {
                palavras.add(linha);
            }
            if (palavras.isEmpty()) continue;

            Map<Character, Set<Character>> graph = new HashMap<>();
            Map<Character, Integer> inDegree = new HashMap<>();

            for (String palavra : palavras) {
                for (char c : palavra.toCharArray()) {
                    graph.putIfAbsent(c, new HashSet<>());
                    inDegree.putIfAbsent(c, 0);
                }
            }

            for (int i = 1; i < palavras.size(); i++) {
                String w1 = palavras.get(i - 1);
                String w2 = palavras.get(i);
                int minLen = Math.min(w1.length(), w2.length());

                boolean encontrou = false;
                 for (int j = 0; j < minLen && !encontrou; j++) {
                    char c1 = w1.charAt(j);
                    char c2 = w2.charAt(j);
                    if(c1 != c2) {
                        if(!graph.get(c1).contains(c2)){
                            graph.get(c1).add(c2);
                            inDegree.put(c2, inDegree.get(c2) + 1);
                        }
                        encontrou = true;
                    }
                }
            }

            Queue<Character> fila = new LinkedList<>();
            StringBuilder ordem = new StringBuilder();

            for (char c : inDegree.keySet()) {
                if (inDegree.get(c) == 0) {
                    fila.offer(c);
                }
            }

            while (!fila.isEmpty()) {
                char atual = fila.poll();
                ordem.append(atual);

                for (char vizinho : graph.get(atual)) {
                    inDegree.put(vizinho, inDegree.get(vizinho) - 1);
                    if (inDegree.get(vizinho) == 0) {
                        fila.offer(vizinho);
                    }
                }
            }

            System.out.println(ordem.toString());
        }
        scanner.close();
    }
}
