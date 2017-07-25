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

import br.ufla.dcc.lfa.arquivo.LinhaTabela;
import br.ufla.dcc.lfa.arquivo.Transicao;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Esta classe descreve o objeto do autômato e implementa seus métodos.
 * O autômato representa aqui, é um autômato finito determinista, então
 * Ele é construído por um quintuplo, onde o construtor de objeto recebe: um
 * Lista de cordas dos estados do autômato, corda do alfabeto do autômato, autômato
 * Lista de sequencia de transições, cadeia de estado inicial do autômato e autômato
 * Lista de cadeias de estados finais.
 */
public class Automato {
    
    /**
     * Estados conjunto de autômatos atributo;
     */
    private List<String> estados;

    /**
     * Estado transições conjunto de autômato atributo;
     */
    private ArrayList<String> transicoes;

    /**
     * Estado final conjunto de autômatos atributo;
     */
    private List<String> estadoFinal;

    /**
     * Conjunto de alfabetes aceito pelo atributo de autômato;
     */
    private char[] alfabeto;

    /**
     * Atributo do estado inicial do autômato;
     */
    private String estadoInicial;

    /**
     * Construtor de objetos de autômatos. Ele recebe os estados estabelecidos,
     * Conjunto de alfabeto, conjunto de transições, estado inicial e finais
     * Estados ou estados de aceitação do autômato.
     * 
     * @param estadosAutomato Passo um arraylist contendo os estados dos automatos
     * @param alfabetoAutomato String com o alfabeto aceita pelo autômato.
     * @param transicoesAutomato Estrutura de dados da lista de Java com todas as transições de autômatos.
     * @param estadoInicialAutomato String com o estado inicial do autômato.
     * @param estadoFinalAutomato Estrutura de dados da lista Java com todos os estados de aceitação do autômato.
     */
    public Automato(
                        ArrayList<String> estadosAutomato,
                        String alfabetoAutomato,
                        ArrayList<String> transicoesAutomato,
                        String estadoInicialAutomato,
                        List<String> estadoFinalAutomato
                    ) {
        this.estados = estadosAutomato;
        this.transicoes = transicoesAutomato;
        this.estadoFinal = estadoFinalAutomato;
        this.alfabeto = alfabetoAutomato.toCharArray();
        this.estadoInicial = estadoInicialAutomato;
    }

    /**
     * Metodo que realiza a minimizacao
     * @param nomeTabela nome dado para o arquivo .txt tabela
     * @param nomeAfdMinimizado nome dado para o arquivo .txt do AFD Minimizado
     */
    public void minimizar(String nomeTabela, String nomeAfdMinimizado) {
        //cria um hashMap das linhas da  tabela de minimização
        HashMap<String, LinhaTabela> linhasTabela = new HashMap<>();
        //preenche o hashMap com as linhas
        for(int i = 0; i < this.estados.size();i++ ){
            for(int j = i+1; j < this.estados.size();j++ ){
                if(!estados.get(i).equals(estados.get(j))){
                    linhasTabela.put(estados.get(i) +","+estados.get(j), new LinhaTabela(0, estados.get(i) +","+estados.get(j)));
                }
            }
        }
        //primeiro seta todas combinações que sao finais/nao-finais
        for(int i = 0; i < this.estados.size();i++ ){
            for(int j = i+1; j <this.estados.size();j++ ){
                if(!eEstadoFinal(estados.get(i)) && eEstadoFinal(estados.get(j)) || !eEstadoFinal(estados.get(i)) && eEstadoFinal(estados.get(j))){
                    LinhaTabela l = linhasTabela.get(estados.get(i) +","+estados.get(j));
                    l.setD(1);
                    l.setMotivo("final/nao-final");
                }
            }
        }
        //verifica os que vao para combinacoes de final/nao-final
        for(int i = 0; i <this.estados.size();i++ ){
            for(int j = i+1; j <this.estados.size();j++){
                if(eEstadoFinal(estados.get(i)) && eEstadoFinal(estados.get(j))){
                    verificaProximosEstadosQueVaoParaFinaisENaoFinais(linhasTabela,i,j);

                } else if (!eEstadoFinal(estados.get(i)) && !eEstadoFinal(estados.get(j))) {
                    verificaProximosEstadosQueVaoParaFinaisENaoFinais(linhasTabela,i,j);
                }
            }
        }
        //adiciona dependencias
        for(int i = 0; i <this.estados.size();i++ ){
            for(int j = i+1; j <this.estados.size();j++){
                adicionaDependencia(linhasTabela, i, j);
            }
        }
        //aplica propagacoes, quando houver
        for(int i = 0; i <this.estados.size();i++ ){
            for(int j = i+1; j <this.estados.size();j++){
                LinhaTabela l = linhasTabela.get(estados.get(i) +","+estados.get(j));
                if(l.getD() == 1){
                    propagacao(linhasTabela, l.getS(), estados.get(i) +","+estados.get(j));
                }
            }
        }
        
        try {
            criaTabela(nomeTabela, linhasTabela);
            afdMinimizado(nomeAfdMinimizado, linhasTabela);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar tabela");
        }
    }
    

    /**
     * Retorna o proximo estado
     * @param estadoAtual Estado atua
     * @param palavrasAlfabeto palavras sobre o alfabeto
     * @return String contendo os proximos estados
     */
//    private String retornaProximoEstado(String estadoAtual, String palavrasAlfabeto) {
//        String[][] tokens = transicaoPharser();
//        for(int i=0; i < this.transicoes.size(); i++) {
//            if(tokens[i][0].equals(estadoAtual)) {
//                if(tokens[i][1].equals(palavrasAlfabeto)) {
//                    return tokens[i][2];
//                }
//            }else {
//                continue;
//            }
//        }
//        return null;
//    }

    /**
     * Metodo que realiza a transicao
     * @return Vetor de String
     */
//    private String[][] transicaoPharser() {
//        String[][] tokens = new String[this.transicoes.size()][3];
//        String[] aux_1 = new String[2];
//        String[] aux_2 = new String[2];
//        
//        for(int k = 0; k < this.transicoes.size(); k ++){
//            for(int i=0; i < this.transicoes.size(); i++) {
//                aux_1 = this.transicoes.get(i).split(",");
//                aux_1[0] = aux_1[0].replace("(", "");
//                aux_1[0] = aux_1[0].trim();
//                aux_2 = aux_1[1].split("->");
//                aux_2[0] = aux_2[0].trim();
//                aux_2[1] = aux_2[1].replace(")", "");
//                aux_2[1] = aux_2[1].trim();
//                    tokens[i][0] = aux_1[0];
//                    tokens[i][1] = aux_2[0];
//                    tokens[i][2] = aux_2[1]; 
//            }
//        }
//        return tokens;
//    }    

    /**
     * Este método privado é responsável por verificações se
     * O estado recebido por parâmetro é um estado final.
     * Ele retorna verdadeiro se sim.
     * 
     * @param estado String para comparar.
     * @return boolean Resultado da comparacao.
     */
    private boolean eEstadoFinal(String estado) {
        for(String s : this.estadoFinal) {
            if(s.equals(estado)) {
                return true;
            }
        }
        return false;
    }
    
    private String proximoEstado(String estadoAtual, String terminal){
        
        String estado = null;
        String verificacao = estadoAtual + "," + terminal;
        for (String transicao : this.transicoes) {
            if(transicao.contains(verificacao)){
                
                estado = transicao.split("->")[1];
                estado = estado.replace(")", "");
            }
        }
        
        return estado;
    }
    /**
     * Esta funcao verifica os indices que vao para combinacoes de finais/nao-finais
     */
    private void verificaProximosEstadosQueVaoParaFinaisENaoFinais(HashMap<String, LinhaTabela> linhasTabela, int i, int j){
        for(char c : alfabeto){
            String aux[] = ordenaEstados(proximoEstado(estados.get(i), String.valueOf(c)), proximoEstado(estados.get(j), String.valueOf(c)));
            
            String proximoEstado1 = aux[0];
            String proximoEstado2 = aux[1];
            
            // verifica se os proximos estados sao diferentes.
            if(!proximoEstado1.equals(proximoEstado2)){
                String motivo = linhasTabela.get(proximoEstado1 + "," + proximoEstado2).getMotivo();
                if(motivo.equals("final/nao-final")){
                    LinhaTabela l = linhasTabela.get(estados.get(i)+","+estados.get(j));
                    l.setMotivo(c + "[" + proximoEstado1 + "," + proximoEstado2 + "]");
                    l.setD(1);
                }
            }
        }
    }
    
    private void adicionaDependencia(HashMap<String, LinhaTabela> linhasTabela, int i, int j){
        for(char c : alfabeto){
            String aux[] = ordenaEstados(proximoEstado(estados.get(i), String.valueOf(c)), proximoEstado(estados.get(j), String.valueOf(c)));
            
            String proximoEstado1 = aux[0];
            String proximoEstado2 = aux[1];
            if(!this.estados.get(i).equals(proximoEstado(this.estados.get(i), String.valueOf(c))) && !this.estados.get(j).equals(proximoEstado(this.estados.get(j), String.valueOf(c)))){
                linhasTabela.get(proximoEstado1+ "," +proximoEstado2).getS().add("[" + this.estados.get(i) + "," + this.estados.get(j) + "]");
            }
            
            
        }
    }
    
    private void propagacao(HashMap<String, LinhaTabela> linhas, ArrayList<String> strings, String estado) {
        if(strings.size() > 0){
            for(String s : strings) {
                for(char c : alfabeto){
                    s = s.replace("[", "");
                    s = s.replace("]", "");
                    linhas.get(s).setD(1);
                    linhas.get(s).setMotivo("prop[" + estado + "]");
                    propagacao(linhas, linhas.get(s).getS(), s);
                }
            }
        }
    }
    
    /**
     * Metodo que orde os key de forma decrescente
     */
    private String[] ordenaEstados(String estado1, String estado2){
         
        int aux1 = Integer.parseInt(estado1.split("q")[1]);
        int aux2 = Integer.parseInt(estado2.split("q")[1]);
        if(aux1 > aux2){
            String[] s = {estado2,estado1};
            return s;
        } else {
            String[] s = {estado1,estado2};
            return s;
        }
    }
    
    
    /**
     * Cria tabela de minimizacao
     * 
     */
    private void criaTabela(String nomeArquivo, HashMap<String, LinhaTabela> linhas) throws IOException{
        FileWriter tabela = new FileWriter(nomeArquivo + ".txt");
        tabela.write("INDICE\t\tD[i,j] =\tS[i,j] =\t\tMOTIVO \n");
        for(int i = 0; i<this.estados.size();i++) {
            for(int j = i+1; j<this.estados.size();j++) {
                String indice = "q"+i+","+"q"+j;
                LinhaTabela linha = linhas.get(indice);
                String esses = "{";
                if(linha.getS().size()> 0){
                    for(String s : linha.getS()){
                        esses += s;
                    }
                }
                esses += "}";
                tabela.write("["+indice+"]\t\t"+linha.getD()+"\t\t" + esses + "\t\t\t" + linha.getMotivo() + "\n");
            }
        }
        
        tabela.close();
    }
    
    private void afdMinimizado(String nomeArquivo, HashMap<String, LinhaTabela> linhas) throws IOException{
        FileWriter arquivo = new FileWriter(nomeArquivo + ".txt");
        List<String> aux = this.estados;
        ArrayList<String> novosEstados = new ArrayList<>();
        String alfabeto = "";
        for(char c: this.alfabeto){
            alfabeto += c + ",";
        }
        
        for(int i = 0; i <this.estados.size();i++ ){
            for(int j = i+1; j <this.estados.size();j++){
                if(linhas.get(this.estados.get(i)+","+this.estados.get(j)).getD() == 0){
                    novosEstados.add(linhas.get(this.estados.get(i)+","+this.estados.get(j)).getIndice());
                    
                }
            }
        }
        
        
        arquivo.write("(\n");
        //escreve os estados
        arquivo.write("\t{");
        for(String s: aux){
            arquivo.write(s + ",");
        }
        for(String s: novosEstados){
            s = s.replace(",", "");
            arquivo.write(s + ",");
        }
        arquivo.write("},\n");
        //escreve o alfabeto    
        arquivo.write("\t{" + alfabeto + "},\n");
        //escreve transicoes
        arquivo.write("\t{\n");
        for(String s : this.transicoes) {
            arquivo.write("\t\t" + s + ",\n");
        }
        arquivo.write("\t},\n");
        //escreve estado inicial
        arquivo.write("\t" + this.estadoInicial + ",\n");
        //escreve estados finais
        arquivo.write("\t{");
        for(String s : this.estadoFinal) {
            arquivo.write(s + ",");
        }
        arquivo.write("}\n");
        arquivo.write(")");
        arquivo.close();
    }
}