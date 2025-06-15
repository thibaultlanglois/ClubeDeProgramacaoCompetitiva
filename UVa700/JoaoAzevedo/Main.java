import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Para ler a entrada do utilizador
        int caseNumber = 1; // Contador 

        while (true) {
            int numberOfEntries = scanner.nextInt(); // Número de entradas (v)
            if (numberOfEntries == 0) break; // Termina se for 0

            List<Integer> yearDifferences = new ArrayList<>(); // Diferenças (b - a)
            List<Integer> displayedYears = new ArrayList<>();  // anos mostrados 
            int currentYear = 0; // maior ano inicial

            // Ler os dados para cada entrada
            for (int i = 0; i < numberOfEntries; i++) {
                int displayedYear = scanner.nextInt(); 
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                yearDifferences.add(b - a);
                displayedYears.add(displayedYear);

                // Atualiza o ano inicial para a pesquisa
                if (displayedYear > currentYear) {
                    currentYear = displayedYear;
                }
            }

            // procura o ano correto começando do maior ano dado até 9999
            for (; currentYear < 10000; currentYear++) {
                boolean allMatch = true; // indicador se o ano atual satisfaz todas as condições

                // verifica se o ano corrente está correto para todas as diferenças e anos apresentados
                for (int j = 0; j < yearDifferences.size() && allMatch; j++) {
                    int diff = yearDifferences.get(j);
                    int yearDisplayed = displayedYears.get(j);

                    // se o resto da divisão não for 0, este ano não é válido
                    if ((currentYear - yearDisplayed) % diff != 0) {
                        allMatch = false;
                    }
                }

                if (allMatch) {
                    break; // ano correto encontrado
                }
            }

            
            System.out.println("Case #" + caseNumber + ":");
            if (currentYear < 10000) {
                System.out.println("The actual year is " + currentYear + ".");
            } else {
                System.out.println("Unknown bugs detected.");
            }
            System.out.println();

            caseNumber++; // incrementa o número do caso
        }

        scanner.close(); 
    }
}
