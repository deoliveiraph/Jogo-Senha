public class ValidadorDeTentativa {
    public String verificarTentativa(String[] tentativa, String[] senha) {
        int pretos = 0;
        int brancos = 0;
        boolean[] senhaUsada = new boolean[4];
        boolean[] tentativaUsada = new boolean[4];

        // Contar pinos pretos
        for (int i = 0; i < 4; i++) {
            if (tentativa[i].equals(senha[i])) {
                pretos++;
                senhaUsada[i] = true;
                tentativaUsada[i] = true;
            }
        }

        // Contar pinos brancos
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
}