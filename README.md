
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

    O Trabalho foi desenvolvido com java versão 8 utilizando o NetBeans IDE e 
NetBeans Platform que são baseados em software da netbeans.org, 
que tem licenciamento duplo sob a Licença de Desenvolvimento e Distribuição Comum (CDDL)
e a GNU General Public License versão 2 com excepção do Classpath.
Product Version: NetBeans IDE 8.2 (Build 201609300101) Atualizações: 
    O IDE NetBeans está atualizado para a versão NetBeans 8.2 Patch 2 
Java: 1.8.0_121; Java HotSpot(TM) 64-Bit Server VM 25.121-b13 
Runtime: Java(TM) SE Runtime Environment 1.8.0_121-b13 
System: Linux version 4.8.0-56-generic running on amd64; UTF-8; pt_BR (nb) User 
directory: /home/junior/.netbeans/8.2 Cache 
directory: /home/junior/.cache/netbeans/8.2

    Para o bom desempenho do sistema é altemente recomendado que se utilize a 
plataforma do netbeans uma vez que os testes foram feitos somente para esta plataforma.
    É IMPRECINDÍVEL realizar a configuração de execucao do pacote. Para isso clique com
o botao direito do mouse em cima do pacote LFA, navegue até a opção propriedades,
após selecionar está opção você será redirecionado para a janela de 'Propriedades do Projeto'.
Estando lá navegue até a opção Executar, e no campo Argumentos sete os seguintes
arquivos .txt na respectiva ordem: 
    <descricao-afd> <arquivo-saida-tabela> <arquivo-saida-afd-minimizado>
(Os arquivos de saída serão criados automaticamentes quando o botao Executar Projeto
for acionado caso não exista e/ou se existirem serão sobrescritos).

# Como executar sem o Netbeans pelo terminal compilando as classes - Linux?

    O projeto não possui nenhum binário associado para execução. 
    Para isso, você precisará compilar todos os código antes de executar a aplicação. 
    Abra o terminal do linux, navegue até o diretório principal e executar os seguintes comandos:

````
$ cd src
$ javac br/ufla/dcc/lfa/automato/*.java
$ javac br/ufla/dcc/lfa/arquivo/*.java
$ javac br/ufla/dcc/lfa/principal/*.java
$ java br/ufla/dcc/lfa/principal/Main <descricao-afd> <arquivo-saida-tabela> <arquivo-saida-afd-minimizado>

````
# Apos isto é só verificar os arquivos de saida

## Como executar sem o Netbeans pelo terminal com ARQUIVO.JAR - Linux?

    O projeto não possui nenhum binário associado para execução. 
    Para isso, você precisará compilar todos os código antes de executar a aplicação. 
    Abra o terminal do linux, navegue até o diretório principal e executar os seguintes comandos:

````

$ cd dist
$ java -jar LFA.jar <descricao-afd> <arquivo-saida-tabela> <arquivo-saida-afd-minimizado>

````
## Apos isto é só verificar os arquivos de saida

## O projeto possui Javadoc

    Para abrir navegue até o diretório principal
    Depois abra a pasta dist/javadoc e dê dois cliques no arquivo index.html

## Apos isto é sera aberto o browser para exibicao do javadoc