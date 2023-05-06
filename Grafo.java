/**
 * Classe usada para representar um grafo usando uma matrix de adjacencias
 * @author Leonardo Bochnia
 * @author https://github.com/leonardoboch
 * @version 1.0
 * @since 1.0
 */

public class Grafo {
    /**
     * Matriz de inteiros usada para representar as adjacencias do Grafo
     */
    private int[][] matrix;
    /**
     * Contador de vertices de um grafo
     */
    private int vertices;

    /**
     * Cria uma instancia do Grafo e inicializa seus vértices
     * Caso o número de vértices seja maior que zero ele inicializa a matriz de adjacencia
     * @param numVertices
     */
    public Grafo (int numVertices){
        setVertices(numVertices);

        if(getNumVertices() > 0) {
            setMatrix();
        }

    }

    /**
     * Seta o número de vértices.
     * Como o índice começa em 0 foi adicionado em 1 o inteiro que representa os vértices
     * Isso foi feito para que a matríz seja acessada a partir do indice 1 até o num + 1.
     * A coluna do 0 e linha 0 não é usada na matriz.
     * @param num
     */
    public void setVertices(int num) {
        if(num > 0) {
            this.vertices = num + 1;
        }
        else {
            System.out.println("O numero de vertices deve ser maior que 0");
        }

    }

    /**
     * Getter do número de vértices.
     * @return um int que representa o número de vértices.
     */
    public int getNumVertices() {
        return this.vertices;
    }

    /**
     * Setter da matriz de adjacencias.
     * Perceba que estamos considerando o 0 como coluna e linha.
     * Não iremos usar eles para usar as adjacencias.
     */
    public void setMatrix() {
        this.matrix = new int[getNumVertices()][getNumVertices()];
    }

    /**
     * Setter de arestas.
     * Recebe dois inteiros maiores que zero e faz a adjacencia.
     * O grafo é um modelo matemático formado por um conjunto de vértices (V) e um conjunto de arestas (E)
     * e uma função w: E->P(V) que associa a cada aresta outro vértice, sendo os pontos da aresta.
     * O grafo mostrado no problema não é um grafo direcional, e sim bidirecional.
     * Portanto ao associar uma aresta dois vértices fazemos a operação demonstada no método abaixo
     * A---->B
     * B---->A
     * logo
     * A<----->B
     * @param verticeA
     * @param verticeB
     */
    public void setAresta(int verticeA, int verticeB) {
        if(verticeA >= 0 && verticeB >= 0) {
            this.matrix[verticeA][verticeB] = 1;
            this.matrix[verticeB][verticeA] = 1;
        }
        else {
            System.out.println("Os vértices não estão armazenados em indices menores do que 0");
        }
    }

    /**
     * Conta o número de arestas em um grafo.
     * Como é um grafo bidirecional usando uma matriz de adjacencias
     * dependendo do modo como as arestas são contadas ela pode retonar um numero duplicado.
     * Para que isso não aconteça considerei apenas a matriz superior.
     * @return int que conta o numero de arestas em um grafo
     */
    public int getNumArestas() {
        int num = 0;
        for(int i = 0; i < getNumVertices(); i++) {
            for(int j = 0; j < getNumVertices(); j++) {
                if( i > j && this.matrix[i][j] != 0) {
                    num += 1;
                }
            }
        }
        return num;
    }

    /**
     * Determina se dois vértices são vizinhos. Caso eles sejam retorna true.
     * Caso não seja retorna false
     * @param verticeA
     * @param verticeB
     * @return boolean determinando se são vizinhos ou não
     */
    public boolean saoVizinhos(int verticeA, int verticeB) {
        if(verticeA > 0 && verticeB > 0) {
            return (matrix[verticeA][verticeB] == 1);
        }
        else {
            System.out.println("O indice do vertice deve ser maior ou igual a 1");
            System.out.println();
        }
        return false;
    }

    /**
     * Imprime uma mensagem caso os vertices inseridos sejam vizinhos.
     * @param verticeA
     * @param verticeB
     */
    public void imprimeVizinhos(int verticeA, int verticeB) {
        if(saoVizinhos(verticeA, verticeB)) {
            System.out.println("O vertice: " + verticeA + " é vizinho de " + verticeB);
        } else {
            System.out.println("O vertice: " + verticeA + " NÃO é vizinho de " + verticeB);
        }
    }

    /**
     * Imprime os vizinhos de um determinado vértice
     * @param vertice
     */
    public void imprimeVizinhosVertice(int vertice) {
       if(!getVizinhosVertice(vertice).equals("")) {
           System.out.println("O vertice: " + vertice + " tem como vizinhos:");
           System.out.println(getVizinhosVertice(vertice));
       } else {
           System.out.println("O vertice não possui vizinhos");
       }
    }

    /**
     * Monta uma string com os vizinhos do vértice
     * @param vertice
     * @return String com os vertices vizinhos concatenados.
     */
    private String getVizinhosVertice(int vertice) {
        StringBuilder indexes = new StringBuilder();
        for (int i = 1; i < getNumVertices(); i++){
            if(saoVizinhos(i, vertice)) {
                indexes.append(" ").append(i).append(",");
            }
        }
        return indexes.toString();

    }

    public static void main(String[] args) {
        Grafo gf = new Grafo(5);
        //Insere arestas, cria relações
        gf.setAresta(1,2);
        gf.setAresta(1,5);
        gf.setAresta(2,3);
        gf.setAresta(2,4);
        gf.setAresta(2,5);
        gf.setAresta(3,4);
        gf.setAresta(4,5);

        System.out.println("O numero de arestas neste vértice é de : " + gf.getNumArestas());
        // Imprime os vizinhos
        //Verificação da condição de bidirecionalidade.
        gf.imprimeVizinhos(1,2);
        gf.imprimeVizinhos(1,5);
        gf.imprimeVizinhos(5,1);
        gf.imprimeVizinhos(2,1);

        gf.imprimeVizinhos(1,3);
        gf.imprimeVizinhos(1,4);

    // Impressao de todos os vizinhos dos vertices

        gf.imprimeVizinhosVertice(1);
        gf.imprimeVizinhosVertice(2);
        gf.imprimeVizinhosVertice(3);
        gf.imprimeVizinhosVertice(4);
        gf.imprimeVizinhosVertice(5);



        System.out.println("Fim do programa");
    }

}
