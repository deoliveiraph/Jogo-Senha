import java.util.Map;
import java.util.Scanner;

public class Jogo {
    private final Scanner scanner = new Scanner(System.in);
    private final MapaDeCores mapaDeCores = new MapaDeCores();
    private final GeradorDeSenha geradorDeSenha = new GeradorDeSenha();
    private final ValidadorDeTentativa validadorDeTentativa = new ValidadorDeTentativa();
    private final ManipuladorDeEntrada manipuladorDeEntrada = new ManipuladorDeEntrada(scanner, mapaDeCores);

    public void iniciar() {
        boolean jogarNovamente;
        do {
            exibirMensagemInicial();
            mapaDeCores.exibirCoresDisponiveis();

            Map<Integer, String> corMap = mapaDeCores.criarMapaDeCores();
            String[] senha = geradorDeSenha.gerarSenha(corMap);
            String[][] matriz = new String[8][4];

            jogarNovamente = jogar(corMap, senha, matriz);
        } while (jogarNovamente);
    }

    private void exibirMensagemInicial() {
        System.out.print("O jogo e simples uma senha de 4 cores diferentes e gerada aleatoriamente a partir de 7 cores disponiveis\n" +
                "Sua missao e descobrir essa senha com o menor numero de tentativas possivel\n" +
                "Lembre-se as senhas nunca terao cores repetidas Apos cada jogada vou te dar uma ajudinha\n" +
                "Se voce acertar uma cor mas ela estiver no lugar errado eu colocarei um pino branco\n" +
                "Se voce acertar tanto a cor quanto a posicao sera indicado um pino preto\n" +
                "Agora e com voce Boa sorte na descoberta da senha e pense bem nas suas jogadas o maximo de tentativas sao 8\n");
    }

    private boolean jogar(Map<Integer, String> corMap, String[] senha, String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.println("Tentativa " + (i + 1) + ":");
            manipuladorDeEntrada.preencherTentativa(matriz[i]);
            String resultado = validadorDeTentativa.verificarTentativa(matriz[i], senha);
            System.out.println("Resultado da tentativa " + resultado);
            if (resultado.equals("4 pretos, 0 brancos")) {
                System.out.println("Aeee, voce descobriu a senha. Deseja jogar novamente? (sim/nao)");
                return desejaJogarNovamente();
            }
        }
        exibirSenhaCorreta(senha);
        System.out.println("putz, que pena quer mais uma chance? (sim/nao)");
        return desejaJogarNovamente();
    }

    private void exibirSenhaCorreta(String[] senha) {
        System.out.println("Voce nao conseguiu descobrir a senha. A senha era ");
        for (String cor : senha) {
            System.out.print(cor + " ");
        }
        System.out.println();
    }

    private boolean desejaJogarNovamente() {
        while (true) {
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.equals("sim")) {
                return true;
            } else if (resposta.equals("nao")) {
                return false;
            } else {
                System.out.println("Resposta invalida. Por favor, digite 'sim' ou 'nao'.");
            }
        }
    }
}