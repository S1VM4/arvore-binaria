import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Nodo {
    char caractere;
    Nodo esquerdo;
    Nodo direito;

    public Nodo(char caractere) {
        this.caractere = caractere;
        this.esquerdo = null;
        this.direito = null;
    }
}

class ArvoreBinariaMorse {
    private Nodo raiz;
    private Map<Character, String> morseMap;

    public ArvoreBinariaMorse() {
        raiz = new Nodo('\0');
        morseMap = new HashMap<>();
        inicializarMapaMorse();
        inserirTodosCaracteres();
    }

    private void inicializarMapaMorse() {
        // Mapeamento do código Morse
        morseMap.put('A', ".-");
        morseMap.put('B', "-...");
        morseMap.put('C', "-.-.");
        morseMap.put('D', "-..");
        morseMap.put('E', ".");
        morseMap.put('F', "..-.");
        morseMap.put('G', "--.");
        morseMap.put('H', "....");
        morseMap.put('I', "..");
        morseMap.put('J', ".---");
        morseMap.put('K', "-.-");
        morseMap.put('L', ".-..");
        morseMap.put('M', "--");
        morseMap.put('N', "-.");
        morseMap.put('O', "---");
        morseMap.put('P', ".--.");
        morseMap.put('Q', "--.-");
        morseMap.put('R', ".-.");
        morseMap.put('S', "...");
        morseMap.put('T', "-");
        morseMap.put('U', "..-");
        morseMap.put('V', "...-");
        morseMap.put('W', ".--");
        morseMap.put('X', "-..-");
        morseMap.put('Y', "-.--");
        morseMap.put('Z', "--..");
        morseMap.put('1', ".----");
        morseMap.put('2', "..---");
        morseMap.put('3', "...--");
        morseMap.put('4', "....-");
        morseMap.put('5', ".....");
        morseMap.put('6', "-....");
        morseMap.put('7', "--...");
        morseMap.put('8', "---..");
        morseMap.put('9', "----.");
        morseMap.put('0', "-----");
    }

    private void inserirTodosCaracteres() {
        for (Map.Entry<Character, String> entry : morseMap.entrySet()) {
            inserir(entry.getValue(), entry.getKey());
        }
    }

    public void inserir(String codigoMorse, char caractere) {
        Nodo atual = raiz;
        for (char simbolo : codigoMorse.toCharArray()) {
            if (simbolo == '.') {
                if (atual.esquerdo == null) {
                    atual.esquerdo = new Nodo('\0');
                }
                atual = atual.esquerdo;
            } else if (simbolo == '-') {
                if (atual.direito == null) {
                    atual.direito = new Nodo('\0');
                }
                atual = atual.direito;
            }
        }
        atual.caractere = caractere;
    }

    public String buscarCodigoMorse(char caractere) {
        return morseMap.getOrDefault(Character.toUpperCase(caractere), "Caractere não encontrado");
    }

    public void exibirMensagem(String mensagem) {
        for (char caractere : mensagem.toUpperCase().toCharArray()) {
            String codigoMorse = morseMap.get(caractere);
            if (codigoMorse != null) {
                System.out.print(codigoMorse + " ");
            } else {
                System.out.print("/ "); // Separador para espaços entre palavras
            }
        }
        System.out.println();
    }

    public void exibirArvore() {
        exibirSubarvore(raiz, "");
    }

    private void exibirSubarvore(Nodo nodo, String codigo) {
        if (nodo != null) {
            if (nodo.caractere != '\0') {
                System.out.println(codigo + ": " + nodo.caractere);
            }
            exibirSubarvore(nodo.esquerdo, codigo + ".");
            exibirSubarvore(nodo.direito, codigo + "-");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ArvoreBinariaMorse arvoreMorse = new ArvoreBinariaMorse();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Buscar código Morse por caractere");
            System.out.println("2. Exibir árvore Morse");
            System.out.println("3. Escrever mensagem em código Morse");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o caractere: ");
                    char caractere = scanner.nextLine().charAt(0);
                    String codigoMorse = arvoreMorse.buscarCodigoMorse(caractere);
                    System.out.println("Código Morse: " + codigoMorse);
                    break;

                case 2:
                    arvoreMorse.exibirArvore();
                    break;

                case 3:
                    System.out.print("Digite a mensagem: ");
                    String mensagem = scanner.nextLine();
                    arvoreMorse.exibirMensagem(mensagem);
                    break;

                case 4:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
