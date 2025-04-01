import java.util.*;

/**
 * Sistema para simular o estacionamento de carros num parque com 20 lugares,
 * os carros esperam para estacionar em lugares que ficaram disponiveis devido à saída de outros carros.
 * A função do programa é calcular, para cada carro em espera, em que lugar ele vai estacionar ou se não 
 * vai conseguir estacionar.
 */
class Main {

    /**
     * Simula o estacionamento dos carros e tenta colocar os carros em espera nos lugares 
     * que ficaram disponíveis. Quando um carro estaciona, o método atribui-lhe o lugar correspondente.
     * 
     */
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        
        while (sc.hasNext()) {
            int numeroDeEventos = sc.nextInt();  // número de carros por estacionar
            sc.nextLine();  

            // Lista para armazenar a ordem de chegada dos carros que estão à espera
            ArrayList<Integer> carrosAespera = new ArrayList<>();
            // Lista para armazenar as posições que ficaram livres (de onde os carros saíram)
            ArrayList<Integer> posicoesVazias = new ArrayList<>();
            
            // Lê as posições dos carros em espera
            String linha;
            for (int i = 0; i < numeroDeEventos; i++) {
                carrosAespera.add(sc.nextInt());  // Adiciona a posição do carro em espera
            }
            sc.nextLine(); 
            
            // Lê as posições que ficaram livres
            while (sc.hasNextLine()) {
                linha = sc.nextLine().trim();
                if (linha.isEmpty()) {
                    break;  
                }
                String[] posicoes = linha.split(" ");
                for (String pos : posicoes) {
                    posicoesVazias.add(Integer.parseInt(pos));  // Adiciona o lugar disponível
                }
            }
            
            // Estacionamento inicial com carros nas posições de 1 a 20 
            int[] estacionamento = new int[20];
            Arrays.fill(estacionamento, 1);  // Marca todas as vagas como ocupadas (1 = ocupado)

            // Mapa para armazenar a correspondência entre o carro em espera e o lugar onde estacionou
            HashMap<Integer, Integer> resultado = new HashMap<>();

            // Processa a saída dos carros e tenta colocar os carros em espera num lugar
            for (int posicaoVaga : posicoesVazias) {
                int vaga = posicaoVaga - 1;  // Converte para índice 0-19  
                
                // Tenta colocar o carro em espera num lugar
                for (int i = 0; i < carrosAespera.size(); i++) {
                    int carroEmEspera = carrosAespera.get(i) - 1;  // Converte para índice 0-19
                    if (carroEmEspera == vaga && estacionamento[vaga] == 1) {
                        
                        estacionamento[vaga] = 0;  // Marca o lugar como ocupado
                        resultado.put(carrosAespera.get(i), posicaoVaga);  // Regista o lugar onde o carro estacionou
                        carrosAespera.remove(i);  // Remove o carro da lista
                        break; 
                    }
                }
            }

            // Imprime os resultados para cada carro
            for (int carroEmEspera : carrosAespera) {
                // Se o carro conseguiu estacionar
                if (resultado.containsKey(carroEmEspera)) {
                    System.out.println("Original position " + carroEmEspera + " parked in " + resultado.get(carroEmEspera));
                } else {
                    System.out.println("Original position " + carroEmEspera + " did not park");
                }
            }
        }
        
        sc.close();
    }
}
