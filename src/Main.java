//Criado por Pedro Henrique (divirta-se!:)
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        exibirMensagemInicial();
        exibirCoresDisponiveis();

        Map<Integer, String> corMap = criarMapaDeCores();
        String[] senha = gerarSenha(corMap);
        String[][] matriz = new String[8][4];

        jogar(scanner, corMap, senha, matriz);
    }

    // Método para exibir a mensagem inicial do jogo
    private static void exibirMensagemInicial() {
        System.out.print("O jogo é simples: uma senha de 4 cores diferentes é gerada aleatoriamente a partir de 7 cores disponíveis.\n" +
                "Sua missão é descobrir essa senha com o menor número de tentativas possível.\n" +
                "Lembre-se: as senhas nunca terão cores repetidas. Após cada jogada, vou te dar uma ajudinha:\n" +
                "Se você acertar uma cor, mas ela estiver no lugar errado, eu colocarei um pino branco.\n" +
                "Se você acertar tanto a cor quanto a posição, será indicado um pino preto.\n" +
                "Agora, é com você! Boa sorte na descoberta da senha, e pense bem nas suas jogadas... o máximo de tentativas são 8!\n");
    }

    // Método para exibir as cores disponíveis e seus respectivos números
    private static void exibirCoresDisponiveis() {
        System.out.print("Cores disponíveis: \n");
        System.out.print("1 - Vermelho \n");
        System.out.print("2 - Azul \n");
        System.out.print("3 - Roxo \n");
        System.out.print("4 - Amarelo \n");
        System.out.print("5 - Laranja \n");
        System.out.print("6 - Verde \n");
        System.out.print("7 - Rosa \n"); // Nova cor adicionada
    }

    // Método para criar o mapa de cores
    private static Map<Integer, String> criarMapaDeCores() {
        Map<Integer, String> corMap = new HashMap<>();
        corMap.put(1, "Vermelho");
        corMap.put(2, "Azul");
        corMap.put(3, "Roxo");
        corMap.put(4, "Amarelo");
        corMap.put(5, "Laranja");
        corMap.put(6, "Verde");
        corMap.put(7, "Rosa"); // Nova cor adicionada
        return corMap;
    }

    // Método para gerar uma senha aleatória de 4 cores
    private static String[] gerarSenha(Map<Integer, String> corMap) {
        Random random = new Random();
        String[] senha = new String[4];
        int[] indicesUsados = new int[4];
        int count = 0;

        while (count < 4) {
            int indice = random.nextInt(7) + 1; // Atualizado para considerar a nova cor
            if (!isIndiceUsado(indicesUsados, count, indice)) {
                indicesUsados[count] = indice;
                senha[count] = corMap.get(indice);
                count++;
            }
        }
        return senha;
    }

    // Método auxiliar para verificar se o índice já foi usado
    private static boolean isIndiceUsado(int[] indicesUsados, int count, int indice) {
        for (int i = 0; i < count; i++) {
            if (indicesUsados[i] == indice) {
                return true;
            }
        }
        return false;
    }

    // Método principal do jogo
    private static void jogar(Scanner scanner, Map<Integer, String> corMap, String[] senha, String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.println("Tentativa " + (i + 1) + ":");
            preencherTentativa(scanner, corMap, matriz[i]);
            String resultado = verificarTentativa(matriz[i], senha);
            System.out.println("Resultado da tentativa: " + resultado);
            if (resultado.equals("4 pretos")) {
                System.out.println("Parabéns! Você descobriu a senha!");
                return;
            }
        }
        exibirSenhaCorreta(senha);
    }

    // Método para preencher a tentativa do usuário
    private static void preencherTentativa(Scanner scanner, Map<Integer, String> corMap, String[] tentativa) {
        for (int j = 0; j < tentativa.length; j++) {
            int corEscolhida;
            do {
                System.out.print("Escolha a cor para a posição [" + (j + 1) + "] (digite o número correspondente): ");
                corEscolhida = scanner.nextInt();
                if (!corMap.containsKey(corEscolhida)) {
                    System.out.println("Número inválido. Por favor, insira um valor entre 1 e 7."); // Atualizado para considerar a nova cor
                }
            } while (!corMap.containsKey(corEscolhida));
            tentativa[j] = corMap.get(corEscolhida);
        }
    }

    // Método para verificar a tentativa do usuário e retornar pinos brancos e pretos
    private static String verificarTentativa(String[] tentativa, String[] senha) {
        int pretos = 0;
        int brancos = 0;
        boolean[] senhaUsada = new boolean[4];
        boolean[] tentativaUsada = new boolean[4];

        for (int i = 0; i < 4; i++) {
            if (tentativa[i].equals(senha[i])) {
                pretos++;
                senhaUsada[i] = true;
                tentativaUsada[i] = true;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (!tentativaUsada[i]) {
                for (int j = 0; j < 4; j++) {
                    if (!senhaUsada[j] && tentativa[i].equals(senha[j])) {
                        brancos++;
                        senhaUsada[j] = true;
                        break;
                    }
                }
            }
        }

        return pretos + " pretos, " + brancos + " brancos";
    }

    // Método para exibir a senha correta caso o usuário não consiga descobrir
    private static void exibirSenhaCorreta(String[] senha) {
        System.out.println("Você não conseguiu descobrir a senha. A senha era: ");
        for (String cor : senha) {
            System.out.print(cor + " ");
        }
        System.out.println();
    }

    // Método para exibir a matriz preenchida
    private static void exibirMatriz(String[][] matriz) {
        System.out.println("Matriz preenchida:");
        for (String[] linha : matriz) {
            for (String cor : linha) {
                System.out.print(cor + " ");
            }
            System.out.println();
        }
    }
}