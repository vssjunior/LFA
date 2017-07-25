/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.lfa.arquivo;

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
public class Transicao {
    private String terminal;
    private String proximoEstado;
    
    public Transicao(String t, String e) {
        this.terminal = t;
        this.proximoEstado = e;
    }

    /**
     * @return the terminal
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * @param terminal the terminal to set
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return proximoEstado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.proximoEstado = estado;
    }
}
