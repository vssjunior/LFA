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

package br.ufla.dcc.lfa.principal;

import br.ufla.dcc.lfa.arquivo.RemocaoSimbolosInuteis;
import br.ufla.dcc.lfa.arquivo.Arquivo;
import br.ufla.dcc.lfa.automato.Automato;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe principal
 * Esta classe é responsável por executar o software.
 * Ele cria os objetos de algoritmo como: Automato, data,
 RemocaoSimbolosInuteis e outros e execute os estágios do algoritmo.
 */
public class Principal {
    
    public static void main(String[] args) {
        try{
            Arquivo arquivo = new Arquivo(args[0]);
            List<String> data = arquivo.retornaDescricao();
            RemocaoSimbolosInuteis arquivoTratado = new RemocaoSimbolosInuteis(data);
            Automato m = new Automato(arquivoTratado.retornaEstados(), arquivoTratado.retornaAlfabeto(), arquivoTratado.retornaTransicoes(), arquivoTratado.retornaEstadoInicial(), arquivoTratado.retornaEstadoFinal());
            m.minimizar(args[1], args[2]);
        }catch(ArrayIndexOutOfBoundsException aioe){
            JOptionPane.showMessageDialog(null, "Arquivo de configuração não informado.\n"
                    + "Leia o arquivo README contido na pasta do projeto.");
        }
    }
}