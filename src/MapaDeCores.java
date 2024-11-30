import java.util.HashMap;
import java.util.Map;

public class MapaDeCores {
    public void exibirCoresDisponiveis() {
        System.out.print("Cores disponiveis \n");
        System.out.print("1 - Vermelho \n");
        System.out.print("2 - Azul \n");
        System.out.print("3 - Roxo \n");
        System.out.print("4 - Amarelo \n");
        System.out.print("5 - Laranja \n");
        System.out.print("6 - Verde \n");
        System.out.print("7 - Rosa \n");
    }

    public Map<Integer, String> criarMapaDeCores() {
        Map<Integer, String> corMap = new HashMap<>();
        corMap.put(1, "Vermelho");
        corMap.put(2, "Azul");
        corMap.put(3, "Roxo");
        corMap.put(4, "Amarelo");
        corMap.put(5, "Laranja");
        corMap.put(6, "Verde");
        corMap.put(7, "Rosa");
        return corMap;
    }
}