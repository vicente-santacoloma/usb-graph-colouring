/**
   * DiGraph es una interfaz que determina los metodos basicos que debe tener un
   * grafo dirigido independiente de su implementacion interna
   *
   * @author Les profs
   * @version 1.0
   * @since 1.6
**/

import java.io.IOException;

public abstract class DiGraph {
  protected int numNodes  = -1;
  protected int numArcs = -1;

    /**
     * Agrega un arco a este DiGraph
     *
     * @param a arco
     * @return El arco agregado
     *
     */
    public abstract Arc addArc(Arc a);

    /**
     * Agrega un arco a este DiGraph
     *
     * @param src nodo fuente del arco
     * @param dst nodo destino del arco
     * @return El arco agregado
     *
     */
    public abstract Arc addArc(Vertice src, Vertice dst);

    /**
     * Agrega un arco a este DiGraph
     *
     * @param src nodo fuente del arco
     * @param dst nodo destino del arco
     * @param costo del arco
     * @return El arco agregado
     *
     */

    public abstract Arc addArc(Vertice src, Vertice dst, double costo);

    /**
     * Remueve un arco de este DiGraph
     *
     * @param nodeIniId nodo fuente del arco a eliminar
     * @param nodeFinId nodo destino del arco a eliminar
     *
     * @return arco eliminado, null en caso de que el arco no exista o no haya
     * sido eliminado
     */
    public abstract Arc delArc(Vertice nodeIniId, Vertice nodeFinId);

    /**
     * Genera una copia de este DiGraph.
     * @return una copia de este DiGraph.
     */
    public abstract Object clone();

    /**
     * Determina si el DiGraph g es igual a este DiGraph
     *
     * @param g el grafo con el que se quiere comparar
     * @return true si los dos DiGraph contienen los mismos nodos y los mismos
     * arcos
     */
    public abstract boolean equals(Object g);

    /**
     * Retorna el Arco cuyo nodo fuente es nodoSrc y nodo destino es nodoDst.
     *
     * @param nodoSrc nodo fuente
     * @param nodoDst nodo destino
     *
     * @return lista de sucesores de nodeId
     */
    public abstract Arc getArc(Vertice nodoSrc, Vertice nodoDst);

    /**
     * Retorna el grado de un nodo en este DiGraph.
     *
     * @param nodeId identificacion del nodo
     * @return el grado del nodo nodeId en este Grafo, -1 si el nodo no se
     * encuentra en el grafo
     */
    public abstract int getDegree(Vertice node);

/**
     * Retorna el grado interno de un nodo en este DiGraph.
     *
     * @param nodeId identificacion del nodo
     * @return el grado interno del nodo nodeId en este Grafo, -1 si el nodo no se
     * encuentra en el grafo
     */
    public abstract int getInDegree(Vertice node);

    /**
     * Retorna la lista de arcos que tienen a nodeId como destino
     * @param nodeId identificador del nodo
     *
     * @return la lista de arcos que tienen a nodeId como destino
     */
    public abstract List<Arc> getInEdges(Vertice node );

/**
     * Retorna el numero de arcos en el grafo
     *
     * @return numero de arcos en el grafo
     */
    public abstract int getNumberOfArcs();

    /**
     * Retorna el numero de nodos en el grafo
     *
     * @return numero de nodos en el grafo
     */
    public abstract int getNumberOfNodes();
  
    /**
     * Retorna el grado externo de un nodo en este DiGraph.
     *
     * @param nodeId identificacion del nodo
     * @return el grado externo del nodo nodeId en este Grafo, -1 si el nodo no
     * se encuentra en el grafo
     */
    public abstract int getOutDegree(Vertice node);
   /**
     * Retorna la lista de arcos que tienen a nodeId como fuente
     * @param nodeId identificador del nodo
     *
     * @return la lista de arcos que tienen a nodeId como fuente
     */
    public abstract List<Arc> getOutEdges(Vertice node );
 
    /**
     * Retorna la lista de predecesores del nodo nodeId
     * 
     * @param nodeId el id del nodo del que se quierenlos predecesores
     * 
     * @return lista de predecesores de nodeId
     */
    
    public abstract List<Vertice> getPredecesors(Vertice node);

    /**
     * Retorna la lista de sucesores del nodo nodeId
     *
     * @param nodeId el id del nodo del que se quieren los sucesores
     *
     * @return lista de sucesores de nodeId
     */
    public abstract List<Vertice> getSucesors(Vertice node);


    /**
     * Indica si un arco existe en este DiGraph
     * 
     * @param src el id del nodo origen del arco
     * @param dst el id del nodo destino del arco
     * @return true si exite un arco desde el nodo src hasta el nodo dst.
     * false en caso contrario
     */
    public abstract boolean isArc(Vertice src, Vertice dst);
  

    /**
     * remueve todos los arcos de este grafo
     *
     * @return lista de arcos eliminados
     */
    public abstract List<Arc> removeAllArcs();

    /**
     * retorna todos los arcos de este grafo
     *
     * @return lista de arcos
     */
    public abstract List<Arc> getAllArcs();


    /**
     * Invierte la direccion de un arco
     * @param nodeIniId nodo fuente del arco antes de invertirlo
     * @param nodeFinId nodo destino del arco antes de invertirlo
     * @return true si el arco fue invertido, false en caso contrario
     */
    public abstract boolean reverseArc(Vertice nodeIniId, Vertice nodeFinId);

    /**
     * Invierte todos los arcos del DiGraph.
     *
     *
     * @return true si todos los arcos fueron invertidos, false en caso 
     * contrario. En caso de que algun nodo no puede ser invertido, el grafo
     * debe quedar sin alteraciones.
     */
    public abstract boolean reverseArcs();

    /**
     * Retorna la representacion en String de este DiGraph.
     * @return la representacion en String de este DiGraph.
     */
    @Override
    public abstract String toString();

}
