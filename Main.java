import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Classe que representa um nó da árvore binária
class Nodo {
    char caractere; // Caractere armazenado no nó
    Nodo esquerdo;  // Ponteiro para o filho à esquerda
    Nodo direito;   // Ponteiro para o filho à direita

    // Construtor da classe Nodo
    public Nodo(char caractere) {
        this.caractere = caractere; // Inicializa o caractere
        this.esquerdo = null;       // Inicializa o filho esquerdo como nulo
        this.direito = null;        // Inicializa o filho direito como nulo
    }
}

// Classe que representa a árvore binária para código Morse
class ArvoreBinariaMorse {
    private Nodo raiz; // Raiz da árvore binária
    private Map<Character, String> morseMap; // Mapa para armazenar caracteres e seus códigos Morse

    // Construtor da classe ArvoreBinariaMorse
    public ArvoreBinariaMorse() {
        raiz = new Nodo('\0'); // Inicializa a raiz com um caractere nulo
        morseMap = new HashMap<>(); // Inicializa o mapa de código Morse
        inicializarMapaMorse(); // Inicializa o mapa com os códigos Morse
        inserirTodosCaracteres(); // Insere todos os caracteres na árvore
    }

    // Método para inicializar o mapa de código Morse
    private void inicializarMapaMorse() {
        // Mapeamento do código Morse para cada caractere
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

    // Método para inserir todos os caracteres no código Morse na árvore
    private void inserirTodosCaracteres() {
        for (Map.Entry<Character, String> entry : morseMap.entrySet()) {
            inserir(entry.getValue(), entry.getKey()); // Insere cada caractere na árvore
        }
    }

    // Método para inserir um caractere na árvore de acordo com seu código Morse
    public void inserir(String codigoMorse, char caractere) {
        Nodo atual = raiz; // Começa na raiz da árvore
        // Percorre o código Morse para determinar a posição do caractere na árvore
        for (char simbolo : codigoMorse.toCharArray()) {
            if (simbolo == '.') { // Se for ponto
                if (atual.esquerdo == null) { // Se não houver filho à esquerda
                    atual.esquerdo = new Nodo('\0'); // Cria um novo nó à esquerda
                }
                atual = atual.esquerdo; // Move para o filho à esquerda
            } else if (simbolo == '-') { // Se for traço
                if (atual.direito == null) { // Se não houver filho à direita
                    atual.direito = new Nodo('\0'); // Cria um novo nó à direita
                }
                atual = atual.direito; // Move para o filho à direita
            }
        }
        atual.caractere = caractere; // Atribui o caractere ao nó atual
    }

    // Método para buscar o código Morse correspondente a um caractere
    public String buscarCodigoMorse(char caractere) {
        return morseMap.getOrDefault(Character.toUpperCase(caractere), "Caractere não encontrado"); // Retorna o código Morse ou uma mensagem de erro
    }

    // Método para exibir a mensagem em código Morse
    public void exibirMensagem(String mensagem) {
        for (char caractere : mensagem.toUpperCase().toCharArray()) {
            String codigoMorse = morseMap.get(caractere); // Busca o código Morse
            if (codigoMorse != null) {
                System.out.print(codigoMorse + " "); // Imprime o código Morse
            } else {
                System.out.print("/ "); // Separador para espaços entre palavras
            }
        }
        System.out.println(); // Nova linha após a exibição da mensagem
    }

    // Método para exibir a árvore binária
    public void exibirArvore() {
        exibirSubarvore(raiz, ""); // Inicia a exibição a partir da raiz
    }

    // Método auxiliar para exibir a subárvore a partir de um nó
    private void exibirSubarvore(Nodo nodo, String codigo) {
        if (nodo != null) {
            if (nodo.caractere != '\0') {
                System.out.println(codigo + ": " + nodo.caractere); // Exibe o código e o caractere
            }
            exibirSubarvore(nodo.esquerdo, codigo + "."); // Exibe o filho à esquerda
            exibirSubarvore(nodo.direito, codigo + "-"); // Exibe o filho à direita
        }
    }
}

// Classe principal do programa
public class Main {
    public static void main(String[] args) {
        ArvoreBinariaMorse arvoreMorse = new ArvoreBinariaMorse(); // Cria uma nova árvore binária de código Morse
        Scanner scanner = new Scanner(System.in); // Cria um scanner para entrada do usuário

        while (true) {
            // Exibe as opções do menu
            System.out.println("Escolha uma opção:");
            System.out.println("1. Buscar código Morse por caractere");
            System.out.println("2. Exibir árvore Morse");
            System.out.println("3. Escrever mensagem em código Morse");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt(); // Lê a opção do usuário
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o caractere: ");
                    char caractere = scanner.nextLine().charAt(0); // Lê o caractere do usuário
                    String codigoMorse = arvoreMorse.buscarCodigoMorse(caractere); // Busca o código Morse
                    System.out.println("Código Morse: " + codigoMorse); // Exibe o resultado
                    break;

                case 2:
                    arvoreMorse.exibirArvore(); // Exibe a árvore Morse
                    break;

                case 3:
                    System.out.print("Digite a mensagem: ");
                    String mensagem = scanner.nextLine(); // Lê a mensagem do usuário
                    arvoreMorse.exibirMensagem(mensagem); // Exibe a mensagem em código Morse
                    break;

                case 4:
                    System.out.println("Saindo..."); // Mensagem de saída
                    scanner.close(); // Fecha o scanner
                    return; // Sai do programa

                default:
                    System.out.println("Opção inválida."); // Mensagem de erro para opção inválida
            }
        }
    }
}
