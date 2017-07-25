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
package br.ufla.dcc.lfa.automato;

/**
 * Esta classe descreve o objeto de estado. Ele contém
 * Numero possíveis próximos estados lendo qualquer elemento do alfabeto.
 */
public class Estado {

    /**
     * Atributo de cadeia de estado atual;
     */
    private String estadoAtual;
    
    /**
     * Os próximos estados sobre o atributo de matriz de estado atual;
     */
    private String[] proximosEstado;

    /**
     * Atributo de sequencia de elementos sobre o alfabeto;
     */
    private String alfabeto;

    /**
     * Construtor de objeto estatal. Ele recebe um estado atual String
     * E alfabeto String por parâmetros, depois disso, ele cria uma nova
     * lista de estados sobre o alfabeto.
     *
     * @param estadoAtual String com o estado atual.
     * @param alfabeto String com todos os elementos do alfabeto.
     */
    public Estado(String estadoAtual, String alfabeto) {
        proximosEstado = new String[alfabeto.length()];
        this.estadoAtual = estadoAtual;
        this.alfabeto = alfabeto;
    }
    
    /**
     * Este método é responsável por
     * @param palavrasAlfabeto palavras sobre o alfabeto
     * @param proximoEstado Proximo estado
     */
    public void setProximoEstado(String palavrasAlfabeto, String proximoEstado) {
        for(int i=0; i < alfabeto.length(); i++) {
            if(palavrasAlfabeto.equals(String.valueOf(alfabeto.charAt(i)))) {
                proximosEstado[i] = proximoEstado;
            }
        }
    }

    /**
     * Retorna o estado atual
     * @return String contendo o estado atual
     */
    public String getEstadoAtual() {
        return this.estadoAtual;
    }
    
    /**
     * Metodo que retorna o proximo estado
     * @param palavrasAlfabeto Palavras sobre o alfabeto
     * @return String contendo o proximo estado
     */
    public String getNextState(String palavrasAlfabeto) {
        for(int i=0; i < alfabeto.length(); i++) {
            if(palavrasAlfabeto.equals(String.valueOf(alfabeto.charAt(i)))) {
                return proximosEstado[i];
            }
        }
        return null;
    }
}