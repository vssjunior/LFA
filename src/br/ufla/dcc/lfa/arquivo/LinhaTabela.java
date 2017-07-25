/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.lfa.arquivo;

import java.util.ArrayList;
import java.util.List;

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
public class LinhaTabela {

    private int d;
    private ArrayList<String> s;
    private String motivo;
    private String indice;
    
    public LinhaTabela(int d, String indice) {
        this.d = d;
        this.s = new ArrayList<>();
        this.motivo = "{}";
        this.indice = indice;
    }
    
    /**
     * @return the d
     */
    public int getD() {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setD(int d) {
        this.d = d;
    }

    /**
     * @return the s
     */
    public ArrayList<String> getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(ArrayList<String> s) {
        this.s = s;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the indice
     */
    public String getIndice() {
        return indice;
    }

    /**
     * @param indice the indice to set
     */
    public void setIndice(String indice) {
        this.indice = indice;
    }
    
}
