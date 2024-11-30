import java.util.Map;
import java.util.Scanner;

public class ManipuladorDeEntrada {
    private final Scanner scanner;
    private final MapaDeCores mapaDeCores;

    public ManipuladorDeEntrada(Scanner scanner, MapaDeCores mapaDeCores) {
        this.scanner = scanner;
        this.mapaDeCores = mapaDeCores;
    }

    public void preencherTentativa(String[] tentativa) {
        Map<Integer, String> corMap = mapaDeCores.criarMapaDeCores();
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print("Escolha as cores para a tentativa (digite os numeros correspondentes separados por espaco): ");
            String[] entradas = scanner.nextLine().split(" ");

            if (entradas.length != 4) {
                System.out.println("Por favor, insira exatamente 4 numeros.");
                continue;
            }

            entradaValida = true;
            for (int j = 0; j < tentativa.length; j++) {
                try {
                    int corEscolhida = Integer.parseInt(entradas[j]);
                    if (!corMap.containsKey(corEscolhida)) {
                        System.out.println("Numero invalido. Por favor, insira um valor entre 1 e 7.");
                        entradaValida = false;
                        break;
                    } else {
                        tentativa[j] = corMap.get(corEscolhida);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada invalida. Por favor, insira apenas numeros.");
                    entradaValida = false;
                    break;
                }
            }
        }
    }
}