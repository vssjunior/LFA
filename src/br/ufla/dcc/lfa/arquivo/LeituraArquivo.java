/**
 * GCC122 - Linguagens Formais e Automatos
 *      Universidade Federal de Lavras.
 * 
 *  Algoritmo de Minimizacao de AFD
 * 
 * @author Valdeci Soares da Silva Junior 
 * @author Arlen Mendes
 * @author Gustavo
 *
 * Professor/Orientador: Ricardo Terra
 * Turma: 14.A - Sistemas de Informação
 */
package br.ufla.dcc.lfa.arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Esta classe é responsável por manipular os arquivos de dados inputados pelo usuário.
 * Ele lê o arquivo, recupera a informação, retorna e depois disso,
 * No próximo estágio de algoritmo ele escreve o resultado em outros dois arquivos.
 */
public class LeituraArquivo {

    /**
     * Atributo de estrutura de dados de cadeia de lista Java.
     */
    private ArrayList<String> leituraArquivoTxt;

    /**
     * Atributo do objeto Java BufferedReader.
     */
    private BufferedReader arquivoDescricao;

    /**
     * Construtor padrao para leitura do arquivo de configuracao
     * @param caminhoArquivoDescricao caminho do arquivo de configuracao
     */
    public LeituraArquivo(String caminhoArquivoDescricao) {
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
}