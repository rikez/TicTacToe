import java.util.Scanner;

/**
 *
 * @author enrico
 */
public class JogoDaVelhaProcedural {

    public JogoDaVelhaProcedural() {
        zeraTabuleiro();
    }

    Scanner entrada = new Scanner(System.in);
    int rodada;
    int[][] tabuleiro = new int[3][3];

    void exibeTabuleiro() {
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {

                if (c == 1 || c == 2) {
                    System.out.print(" | ");
                }

                if (tabuleiro[l][c] == 0) {
                    System.out.print(" ");
                }

                if (tabuleiro[l][c] == 1) {
                    System.out.print("X");
                }

                if (tabuleiro[l][c] == 2) {
                    System.out.print("O");
                }

            }
            System.out.println("");

        }

    }

    void zeraTabuleiro() {
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                tabuleiro[l][c] = 0;

                if (tabuleiro[l][c] == 0) {
                    System.out.print(" ");
                }
            }
        }

    }

    int venceuJogo() {
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                if (tabuleiro[l][0] == tabuleiro[l][1] && tabuleiro[l][0] == tabuleiro[l][2]) {
                    return tabuleiro[l][0];
                }// checando Linhas
                if (tabuleiro[0][c] == tabuleiro[1][c] && tabuleiro[0][c] == tabuleiro[2][c]) {
                    return tabuleiro[0][c];
                }// checando colunas

                if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[0][0] == tabuleiro[2][2]) {
                    return tabuleiro[0][0];
                }// checando diagonais
                if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[0][2] == tabuleiro[2][0]) {
                    return tabuleiro[0][2];
                }// checando diagonais2

            }
        }

        return 0;
    }

    void jogar() {   //metodo geral!
        int jogador = 1;
        String opcao = "";

        System.out.println("Olá, Bem-vindo ao servidor de jogos Petrovínsk!");
        do {
            System.out.println("Vamos começar um jogo da Velha?");
            System.out.println("Se Sim, digite S/n");
            System.out.println("Se Nao, digite N/n");
            System.out.printf("Opcao: ", opcao);
            opcao = entrada.next();
            System.out.println("");

            boolean equalsIgnoreCase = opcao.equalsIgnoreCase(opcao);

            if (opcao.equals("N") || opcao.equals("n")) {
                System.exit(0);
            }

        } while (!opcao.equals("S") && !opcao.equals("N"));

        exibeTabuleiro();

        while (venceuJogo() == 0) {

            System.out.println("");
            System.out.println("Turno do jogador: " + jogador);
            System.out.println("Rodada: " + rodada);
            System.out.println("");
            try {
                System.out.print("Linha: ");
                int tentativaLinha = entrada.nextInt();
                System.out.print("Coluna: ");
                int tentativaColuna = entrada.nextInt();

                if (realizarJogada(jogador, (tentativaLinha-1), (tentativaColuna-1)) == false) {
                    System.out.println("");
                    System.out.println("Jogada invalida!!! NOVAMENTE!!!");
                    System.out.println("");
                } else {
                    rodada++;
                    if (jogador == 1) // faz a troca de jogadores!
                    {
                        jogador = 2;
                    } else {
                        jogador = 1;
                    }
                }

            } catch (Exception msg) {
                System.out.println("ERRO AO DIGITAR LINHA OU COLUNA!: " + msg);
            }

            exibeTabuleiro();
            venceuJogo();

            if (venceuJogo() == 1) {
                System.out.println("Jogador 1 VENCEU!");
            } else if (venceuJogo() == 2) {
                System.out.println("Jogador 2 VENCEU!");
            } else if (isTabuleiroCheio()==true && rodada == 9) {
                System.out.println("EMPATE!");

            }

        }
    }

    boolean realizarJogada(int jogador, int tentativaLinha, int tentativaColuna) {
        if (tentativaLinha < 0 || tentativaLinha > 2)//linha
        {
            return false;
        }

        if (tentativaColuna < 0 || tentativaColuna > 2)//coluna
        {
            return false;
        }

        if (tabuleiro[tentativaLinha][tentativaColuna] != 0) {
            return false;
        }

        tabuleiro[tentativaLinha][tentativaColuna] = jogador;
        return true;
    }

    boolean isTabuleiroCheio() {
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                if (tabuleiro[l][c] == 0) {
                    return false;
                }

            }
        }
        System.out.println("EMPATE!!");
        System.exit(0);
        return true;

    }

    public static void main(String[] args) {
        // TODO code application logic here

        JogoDaVelhaProcedural jogo = new JogoDaVelhaProcedural();
        jogo.jogar();
    }

}
