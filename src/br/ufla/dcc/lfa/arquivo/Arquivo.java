/**
 * GCC122 - Linguagens Formais e Automatos
 *      Universidade Federal de Lavras.
 * 
 *  Algoritmo de Minimizacao de AFD
 * 
 * @author Valdeci Soares da Silva Junior 
 * @author Arlen Mateus Mendes
 * @author Gustavo
 *
 * Professor/Orientador: Ricardo Terra
 * Turma: 14.A - Sistemas de Informação
 */
package br.ufla.dcc.lfa.arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

/**
 * Esta classe é responsável por manipular os arquivos de dados inputados pelo usuário.
 * Ele lê o arquivo, recupera a informação, retorna e depois disso,
 * No próximo estágio de algoritmo ele escreve o resultado em outros dois arquivos.
 */
public class Arquivo {

    /**
     * Atributo de estrutura de dados de cadeia de lista Java.
     */
    private ArrayList<String> leituraArquivoTxt;

    /**
     * Atributo do objeto Java BufferedReader.
     */
    private BufferedReader arquivoDescricao;

    /**
     * Atributo de objeto Java BuffereWriter.
     */
    private BufferedWriter arquivoSaidaTabela;
    
    /**
     * Atributo de objeto Java BuffereWriter.
     */
    private BufferedWriter arquivoSaidaAfdMinimizado;

    /**
     * Construtor de objeto de arquivos txt. Ele recebe 3 cordas com arquivos de nome e caminho.
     * Ele cria 3 novos objetos para ler e escrever esses arquivos. Se houver uma exceção,
     * O método irá tentar com a opção try catch.
     * 
     * @param caminhoArquivoDescricao Introduza o arquivo de texto com nome do caminho.
     * @param caminhoArquivoTabela Saída do arquivo de texto com uma tabela minimizadora de algoritmos.
     * @param caminhoAfdMinimizado Saída do arquivo de texto com o resultado da execução do algoritmo.
     */
    public Arquivo(String caminhoArquivoDescricao, String caminhoArquivoTabela, String caminhoAfdMinimizado) {
        leituraArquivoTxt = new ArrayList<String>();
        try {
            arquivoDescricao = new BufferedReader(new FileReader(caminhoArquivoDescricao));
            //if(!new File(caminhoArquivoTabela).exists()) {
             //   new File("../table.txt").createNewFile();
           // }
            arquivoSaidaTabela = new BufferedWriter(new FileWriter(caminhoArquivoTabela));
            //if(!new File(caminhoAfdMinimizado).exists()) {
             //   new File("afd_minimized.txt").createNewFile();
           // }
            arquivoSaidaAfdMinimizado = new BufferedWriter(new FileWriter(caminhoAfdMinimizado));
        }catch(IOException ioex) {
            System.out.println(ioex.getMessage());
        }
    }

    /**
     * Sobrescrita do construtor caso nao queira passar os arquivos de output
     * @param caminhoArquivoDescricao caminho do arquivo de configuracao
     */
    public Arquivo(String caminhoArquivoDescricao) {
        leituraArquivoTxt = new ArrayList<String>();
        try {
            arquivoDescricao = new BufferedReader(new FileReader(caminhoArquivoDescricao));
        }catch(IOException ioex) {
            System.out.println(ioex.getMessage());
        }
    }

    /**
     * Este método é responsável por obter as informações de descrição do autômato
     * entrada. Ele abre o arquivo pelo método do objeto BufferedReader e verifica o
     * Validação de arquivos. Depois disso, ele lê linha a linha e armazena as linhas em
     * Uma estrutura de dados de cadeia de lista de matrizes. Esse método retorna essa matriz.
     *
     * @return Java ArrayList estrutura de dados que contém toda a descrição do autômato.
     */
    public ArrayList<String> retornaDescricao() {
        boolean controle = false;
        String arquivoDescricaoAux;

        try {
            while((arquivoDescricaoAux = arquivoDescricao.readLine()) != null) {
                if(arquivoDescricaoAux.equals("(")) {
                    controle = !controle;
                }
                if(controle) {
                    arquivoDescricaoAux = arquivoDescricaoAux.trim();
                    leituraArquivoTxt.add(arquivoDescricaoAux);
                }
            }
        }catch(IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return leituraArquivoTxt;
    }

    /*public void printTable(List<Integer> index_i, List<Integer> index_j, List<Boolean> d, List<String> motivo) {
        for(int i=0; i < index_i.size(); i++) {
            
        }
    }*/
}