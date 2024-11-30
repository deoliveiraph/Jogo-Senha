import java.util.Map;
import java.util.Random;

public class GeradorDeSenha {
    public String[] gerarSenha(Map<Integer, String> corMap) {
        Random random = new Random();
        String[] senha = new String[4];
        int[] indicesUsados = new int[4];
        int count = 0;

        while (count < 4) {
            int indice = random.nextInt(7) + 1;
            if (!isIndiceUsado(indicesUsados, count, indice)) {
                indicesUsados[count] = indice;
                senha[count] = corMap.get(indice);
                count++;
            }
        }
        return senha;
    }

    private boolean isIndiceUsado(int[] indicesUsados, int count, int indice) {
        for (int i = 0; i < count; i++) {
            if (indicesUsados[i] == indice) {
                return true;
            }
        }
        return false;
    }
}