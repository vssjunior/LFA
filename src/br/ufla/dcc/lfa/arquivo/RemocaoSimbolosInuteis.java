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

import java.util.List;
import java.util.ArrayList;

/**
 * Esta classe é responsável pelo tratamento de strings de dados.
 * Sua função no software é retornar os estados do autômato,
 Estados finais, estados iniciais, transicoes e outras informações
 Formatado, pronto para ser processado.
 */
public class RemocaoSimbolosInuteis {

    /**
     * Atributo de estrutura de dados de cadeia de lista Java.
     */
    private ArrayList transicoes;
    
    /**
     * Atributo de estrutura de dados de cadeia de lista Java.
     */
    private List<String> conteudoArquivoDescricao;
    
    /**
     * Atributo de estrutura de dados de cadeia de lista Java.
     */
    private List<String> estadoFinal;

    /**
     * Atributo de estrutura de dados de cadeia de lista Java.
     */
    private ArrayList<String> estados;

    /**
     * Construtor de objeto RemocaoSimbolosInuteis. Ele recebe uma lista única
      * Estrutura de dados por parâmetro com todos os dados carregados por arquivo
      * Na classe Dados.
     * 
     * @param conteudo Estrutura de dados da lista de Java com informações de dados de autômatos.
     */
    public RemocaoSimbolosInuteis(List conteudo) {
        this.conteudoArquivoDescricao = conteudo;
        estadoFinal = new ArrayList<String>();
        transicoes = new ArrayList<String>();
        estados = new ArrayList<String>();
    }

    /**
     * Este método é responsável pelo tratamento de conjunto de estados de cadeia.
     * Ele remove todos os caracteres indesejados para o processamento do autômato. Ele substitui
     * Que os caracteres em string e lojas na lista estados devem ser retornados
     * Por método.
     * 
     * @return Liste a estrutura de dados da lista Java com todos os estados do autômato.
     */
    public ArrayList<String> retornaEstados() {
        String linha = "";
        for(String s : this.conteudoArquivoDescricao) {
            if(s.contains("{")) {
                linha = s;
                break;
            }
        }
        String[] simblolosInuteis = linha.split(",");
        for(String s : simblolosInuteis) {
            s = s.replace("{", "");
            s = s.replace("}", "");
            s = s.replace(";", "");
            s = s.replace(",", "");
            s = s.replace(" ", "");
            estados.add(s);
        }
        return estados;
    }

    /**
     * Este método é responsável pelo arquivo de dados de carga e retorna
     * O alfabeto que o autômato irá funcionar.
     * Ele identifica o último personagem da última linha e depois disso,
     * Decide se a linha atual é alfabeto.
     * 
     * @return String Automaton alphabet.
     */
    public String retornaAlfabeto() {
        String linha="";
        boolean control = false;
        for(String s : this.conteudoArquivoDescricao) {
            if(control) {
                linha = s;
                break;
            }
            if(s.contains("},")) {
                control = !control;
            }
        }
        linha = linha.replace("{", "");
        linha = linha.replace("}", "");
        linha = linha.replace(",", "");
        linha = linha.replace(";", "");
        return linha;
    }

    /**
     * Este método é responsável por retornar o conjunto de transicoes
     * Por Autômatos do conteúdo do arquivo de carga de dados.
     * 
     * @return Liste a estrutura de dados da lista de string Java com todos os transatos automáticos.
     */
    public ArrayList<String> retornaTransicoes() {
        boolean controle = false;
        for(String s : this.conteudoArquivoDescricao) {
            if(s.equals("{") ) {
                controle = !controle;
                continue;
            }else if(s.contains("(") && controle) {
                s = s.replace(" ", "");
                s = s.replace("),", ")");
                transicoes.add(s);
                continue;
            }else {
                controle = false;
            }
        }
        return transicoes;
    }

    /**
     * Este método retorna a sequência de estado inicial pelo conteúdo do arquivo de carga de dados.
     * @return String contendo os estados iniciais
     */
    public String retornaEstadoInicial() {
        String estadoInicial = "";
        boolean controle = false;
        for(String s : conteudoArquivoDescricao) {
            s = s.replace(" ","");
            if(s.equals("},") && (controle == false)) {
                controle = !controle;
                continue;
            }
            if((s != null) && controle) {
                controle = !controle;
                s = s.replace(" ", "");
                s = s.replace(",", "");
                estadoInicial = s;
            }else {
                continue;
            }
        }
        return estadoInicial;
    }
    
    /**
     * Metodo que retorna os estados finais
     * @return um vetor de strings com os estados finais
     */
    public List<String> retornaEstadoFinal() {
        String strEstados;
        strEstados = this.conteudoArquivoDescricao.get(this.conteudoArquivoDescricao.size()-2);
        String[] simbolosInuteis = strEstados.split(",");
        for(String s : simbolosInuteis) {
            s = s.replace(",","");
            s = s.replace("{", "");
            s = s.replace("}", "");
            estadoFinal.add(s);
        }
        return estadoFinal;
    }

}