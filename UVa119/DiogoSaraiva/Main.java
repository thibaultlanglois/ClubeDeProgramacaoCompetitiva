import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Main {

	public static void main(String[] args) throws FileNotFoundException {
        boolean fromFile = false;
        Scanner sc = fromFile ? new Scanner(new File("in.txt"))
                : new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		int cases = 0;
		while (sc.hasNext()) {

			int numberOfPersons = sc.nextInt(); // Lê o número de pessoas
			String[] personsNames = new String[numberOfPersons];

			for (int i = 0; i < numberOfPersons; i++) {
				personsNames[i] = sc.next(); // Lê os nomes das pessoas
			}

			int[] personProfit = new int[numberOfPersons]; // Cada pessoa começa com 0

			for (int giverNumber = 0; giverNumber < numberOfPersons; giverNumber++) { // percorre cada pessoa
				String personWhoGives = sc.next(); // Pessoa que dá
				int howManyGives = sc.nextInt(); // Quanto a pessoa dá
				int toDivideBy = sc.nextInt(); // A quantas pessoas a pessoa dá
				String[] receiversName = new String[toDivideBy]; // Lista de pessoas a que a pessoa dá

				// Encontrar quem é o doador correto
				for (int i = 0; i < numberOfPersons; i++) {
					if (personsNames[i].equals(personWhoGives)) {
						// Se houver recetores, subtrai o valor distribuído
						if (toDivideBy > 0) {
							personProfit[i] -= (howManyGives / toDivideBy) * toDivideBy;
						}

						// Adicionar os valores aos recetores
						for (int receiverNumber = 0; receiverNumber < toDivideBy; receiverNumber++) { // Verificar cada
																										// receptor
							receiversName[receiverNumber] = sc.next(); // Atribuir nome a cada receptor

							// Encontrar o recetor correto
							for (int k = 0; k < numberOfPersons; k++) {
								if (receiversName[receiverNumber].equals(personsNames[k])) {
									personProfit[k] += howManyGives / toDivideBy; // Adicionar dinheiro ao recetor
								}
							}
						}

					}
				}
			}

			if (cases > 0) {
				System.out.println();
			}

			for (int i = 0; i < numberOfPersons; i++) {
				System.out.println(personsNames[i] + " " + personProfit[i]);
			}

			cases++;
		}
		sc.close();
	}
}
