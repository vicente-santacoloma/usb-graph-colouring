import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.File;
import java.util.*;
import java.io.IOException;

/** Implementación concreta de la terna Grafo Dirigido,
 *  utilizando la estructura de datos Tabla de Hash  y Lista simple
 *  enlazada además de las estructuras clásicas de datos
 *  DiGraphHash extiende de DiGraph por su definicion
**/

public class DiGraphHash extends DiGraph 
{
	/**  Los parametros nodes, arcs, arcsr
     *	 son Tablas de Hash destinadas al almacenamiento  
	 *   de nodos, arcos y el reverso de los arcos
	 *   respectivamente (este ultimo para optimizar ciertos metodos).
	 * 	 Los parametros numnodes y numarcs
	 *   Son contadores privados de nodos  y arcos respectivamente
     **/
   private HashTable<Vertice> nodes;
   private HashTable<Arc> arcs;
   private HashTable<Arc> arcsr;
   private  int	numnodes =0;
   private  int numarcs = 0;
    
	/** DiGraphHash crea un Digrafo 
     * Inicializando sus parámetros en null 
     **/
   public DiGraphHash ()  
   {
	this.numnodes = 0;
	this.numarcs = 0;
	this.nodes= null;
	this.arcs= null;
	this.arcsr = null;
   }

	/** DiGraphHash crea un Digrafo 
     * Inicializando sus parámetros dependiendo del numero de nodos  
     **/
   public DiGraphHash(int c)
   {
	numnodes = c;
	this.arcs= new HashTable<Arc>(particion(numnodes));
	this.arcsr= new HashTable<Arc>(particion(numnodes));
	this.nodes = new HashTable<Vertice>(particion(numnodes));
   }
	/** Agrega un arco al parametro implicito
     *  Partiendo de un arco creado
	 *  devuelve el arco agregado
     **/
    @Override
    public Arc addArc(Arc a)
	{
		Arc arco= a;
		Arc aux = (Arc)a.clone();
		boolean v = arcs.add(arco);
		aux.reverse();
		v= arcsr.add(aux);
		if (v)
		{
			numarcs++;
		}	
		return a;
	}
	
	/** Agrega un arco al parametro implicito
     *  Partiendo de un par de nodos
	 *  devuelve el arco agregado
     **/
    @Override
    public Arc addArc(Vertice src, Vertice dst) 
	{
		Arc a= new Arc(src,dst, src.getCosto()+dst.getCosto());
		return addArc(a);
	}
	
	/** Agrega un arco al grafo
     *  Partiendo de  un par de nodos y un costo
	 *  devuelve el arco agregado
     **/
    @Override
    public Arc addArc(Vertice src, Vertice dst, double costo)
	{
		Arc a= new Arc(src,dst, costo);
		return addArc(a);
    }
	 /** Elimina del  arco al grafo
     *  Partiendo de un par de nodos y devuelve el arco
     **/
    @Override
    public Arc delArc(Vertice nodeIniId, Vertice nodeFinId) 
	{
		Arc a= new Arc(nodeIniId,nodeFinId);
		a= arcs.remove(a);
		Arc b = (Arc)a.clone();
		b.reverse();
		a= arcsr.remove(b);
		if (a!=null)
		{
			numarcs--;
		}
		return a;
		
    }
	/** Clona el  grafo implicito
     * Devuelve un objeto equivalente al grafo
     **/
    @Override
    public Object clone() 
	{
		DiGraphHash Digrafo= new DiGraphHash();
		Digrafo.nodes = this.nodes.clone();
		Digrafo.arcs = this.arcs.clone();
		Digrafo.arcsr = this.arcsr.clone();
		return Digrafo;
    }
	/** Recibe un Objeto y compara el objeto con el grafo
     *  retorna true si g es equivalente al grafo
	 * y false  en otros casos
     **/
    @Override
    public boolean equals(Object g)
	{
		boolean v= false;
		if (g instanceof DiGraphHash)
		{
			v= this.arcs.equals(((DiGraphHash)g).arcs) && this.nodes.equals(((DiGraphHash)g).nodes);
		}
	return v;
	}
	
	/** Recibe dos vertices y si
	*  estos dos forman un arco de la forma  (nodoSrc)->(nodoDst)
	 *  devuelve  dicho arco, si no retorna null
     **/
    @Override
    public Arc getArc(Vertice nodoSrc, Vertice nodoDst) 
	{
		Arc a = new Arc(nodoSrc, nodoDst);
		boolean v= false; 
		v= arcs.contains(a);
		if (!v)
		{
			a = null;	
		}
		return a;
	}
	
	public Arc getArc2( Vertice fuente, Vertice destino)
	{
		Arc arco = new Arc(fuente,destino);
		int k = arcs.funcion(arco);
		Lista<Arc>[] tablan = arcsr.obtenerTabla();
		Lista<Arc> lista = tablan[k];
		
		
		for(int i=0; i< lista.size(); i++)
		{
			Arc arc = lista.get(i);
			
			if(arc.getSrc().obtenerId().equals(fuente.obtenerId()) && arc.getDst().obtenerId().equals(destino.obtenerId()))
			{
				return arc;
			}
		}
		arco = null;
		return arco;
	}
	
	
	
	public Vertice getVertice (Vertice vertice)
	{
		Vertice nodo = vertice;
		Lista<Vertice> [] tabla = nodes.obtenerTabla();
		int k = nodes.funcion(vertice);
		Lista<Vertice> lista = tabla[k];
		
		for (int i = 0; i<lista.size(); i++)
		{
			Vertice ver = lista.get(i);
			
			if(ver.obtenerId().equals(vertice.obtenerId()))
			{
				return ver;
			}
		}
		nodo = null;
		return nodo;
			
	}
	
	public Vertice getVerticeRandom ()
	{
		Vertice nodo = new Vertice("0",4.0);
		Lista<Vertice> [] tabla = nodes.obtenerTabla();
		int k = nodes.funcion(nodo);
		Lista<Vertice> lista = tabla[k];
		nodo = lista.get(0);
		return nodo;
	}
	
	/** Devuelve el grado de un nodo
	 *  sumando su grado de salida mas su grado de entrada
     **/
    @Override
    public int getDegree(Vertice node)
	{
		int degree;
		if (!( getInDegree(node)==0 && getOutDegree(node)==0))
		{
			degree = getInDegree(node) + getOutDegree(node);		
		} else
		{
			degree = -1;
		}
		
		return degree;
	  
	}
	
	/** Retorna un entero con el grado interno del nodo "node"
     **/
    @Override
    public int getInDegree(Vertice node) 
	{	
		int degree= 0;
		int k = nodes.funcion(node);
		Lista<Arc>[] tablan = arcsr.obtenerTabla();
		Lista<Arc> lista = tablan[k];
		Object[] arcos=  lista.toArray();
		
	
		for (int i=0; i< arcos.length ; i++)
		{
			if (((Arc)arcos[i]).getSrc().equals(node))
			{
				degree++;			
			}
		}
		
		return degree;
    }
	/** Retorna una lista con los arcos que tienen como
	* 	nodo  de llegada al parametro de entrada "node"
    **/
    @Override
    public List<Arc> getInEdges(Vertice node) 
	{
		int k = nodes.funcion(node);
		Lista<Arc>[] tablan = arcsr.obtenerTabla();
		Lista<Arc> lista = tablan[k];
		Object[] arcos=  lista.toArray();
		Lista<Arc> aux = new Lista<Arc> ();
		
		for (int i=0; i< arcos.length ; i++)
		{
			if (((Arc)arcos[i]).getSrc().equals(node))
			{
				((Arc)arcos[i]).reverse();
				Arc arcoAux = (Arc) arcos[i];
				aux.add(arcoAux);
			}
		}
		
		return aux;
			
    }
	
	/** Retorna un entero con el numero de arcos del grafo
     **/
    @Override
    public int getNumberOfArcs() 
	{
		return this.numarcs;
    }
	
	/** Retorna un entero con el numero de nodos del grafo
     **/
    @Override
    public int getNumberOfNodes()
	{
		return this.numnodes;
	}
	
	/** Retorna un entero con el  grado de salida del nodo "node" 
     **/
    @Override
    public int getOutDegree(Vertice node) 
	{
		
		int degree= 0;
		int k = nodes.funcion(node);
		Lista<Arc>[] tablan = arcs.obtenerTabla();
		Lista<Arc> lista = tablan[k];
		Object[] arcos=  lista.toArray();
		
		for (int i=0; i< arcos.length ; i++)
		{
			if (((Arc)arcos[i]).getSrc().equals(node))
			{
				degree++;			
			}
		}
		
		return degree;
    }

	/** Retorna una lista que contiene los arcos que tienen como
	* 	nodo  de salida al parametro de entrada "node"
    **/
    @Override
    public Lista<Arc> getOutEdges(Vertice node)
	{

		int k = nodes.funcion(node);
		Lista<Arc>[] tablan = arcs.obtenerTabla();
		Lista<Arc> lista = tablan[k];
		Object[] arcos= lista.toArray();
		Lista<Arc> aux = new Lista<Arc> ();
		
		for (int i=0; i< arcos.length ; i++)
		{
			if (((Arc)arcos[i]).getSrc().equals(node))
			{
				aux.add((Arc)arcos[i]);
			}
		}
		
		return aux;
	}

	/** Retorna una lista que contiene los nodos "A" tal que existe un arco
	* 	y su nodo inical es "A" y su nodo  de salida es el parametro de entrada "node"
    **/
    @Override
    public List<Vertice> getPredecesors(Vertice node) 
	{
		
		int k = nodes.funcion(node);
		Lista<Arc>[] tablan = arcsr.obtenerTabla();
		Lista<Arc> lista = tablan[k];
		Object[] arcos= lista.toArray();
		Lista<Vertice> aux = new Lista<Vertice> ();
		
		for (int i=0; i< arcos.length ; i++)
		{
			if (((Arc )arcos[i]).getSrc().equals(node))
			{
				aux.add(((Arc)arcos[i]).getDst());
			}
		}
		return aux;	
    }
	
	/** Retorna una lista que contiene los nodos "A" tal que existe un arco "X"
	* 	 su nodo de salida es "A" y su nodo  incial es el parametro de entrada "node"
    **/	
    @Override
    public Lista<Vertice> getSucesors(Vertice node)
	{
		
		int k = nodes.funcion(node);
		Lista<Arc>[] tablan = arcs.obtenerTabla();
		Lista<Arc> lista = tablan[k];
		
		
		if (lista == null)
		{
			return null;
		}
		
		Object[] arcos = lista.toArray();
		Lista<Vertice> aux = new Lista<Vertice> ();
		for (int i=0; i< arcos.length ; i++)
		{			
				
				if (((Arc) arcos[i]).getSrc().obtenerId().equals(node.obtenerId()))
				{
					aux.add(((Arc)arcos[i]).getDst());
				}
			
		}
		return aux;	
    }
	
	/** Construye un arco con el nodo "src" y el nodo "dst"
	* 	Y devuelve true si dicho arco pertenece a el grafo false en otros casos
    **/	
    @Override
    public boolean isArc(Vertice src, Vertice dst) 
	{
		
		Arc a = new Arc(src, dst);
		boolean v = false;
		v = arcs.contains(a);	
		return v;
    }

	/** Metodo para escoger el tamaño de las clases
	* de equivalencia del  grafo retorna un entero con el numero de
	* particiones de equivalencia de las tablas nodes, arcs, arcsr
    **/
	private int particion (int num) 
	{
		return num;
	
	}

	/** Elimina todos los arcos del grafo y retorna una lista con todos ellos
	* Las  tablas permanecen con su  particion
	**/
    @Override
    public List<Arc> removeAllArcs() {
		
		Lista <Arc> remove = new Lista<Arc> ();
		Lista <Arc> [] tablan = arcs.obtenerTabla();
		boolean v = false;
		int k = 0;
		
		
		for (int i = 0 ; i<tablan.length; i++)
			{
			
			if (tablan[i] != null)
			{
					Object[] arcosre = new Arc[tablan[i].size()];
					arcosre = tablan[i].toArray();
						for(int j=0; j<arcosre.length; j++)
						{
							v = remove.addi((Arc) arcosre[j]);					
						}
			}
		}
				
		this.arcs = new HashTable<Arc> (this.numarcs);
		this.arcsr = new HashTable<Arc> (this.numarcs);
		
		return remove;
		
    }

	/** Obtiene todos los vertices del grafo 
	* Retorna un arreglo de Object con los vertices
	**/
    public Object[] getAllNodes()
	{
		Lista <Vertice> remove = new Lista<Vertice> ();
		Lista <Vertice> [] tablan = nodes.obtenerTabla();
		boolean v = false;
		for (int i = 0 ; i<tablan.length; i++)
			{
			
			if (tablan[i] != null)
			{
					Object [] vertices = new Vertice[tablan[i].size()];
					vertices = tablan[i].toArray();
						for(int j=0; j<vertices.length; j++)
						{
							v = remove.addi((Vertice) vertices[j]);						
						}
			}
			}
		
		return remove.toArray();
	
	
	}
	
	/** Obtiene todos los arcos del grafo 
	* Retorna un arreglo de Object con los arcos
	**/
	@Override
	public Lista<Arc> getAllArcs() {
		
		Lista <Arc> remove = new Lista<Arc> ();
		Lista <Arc> [] tablan = arcs.obtenerTabla();
		boolean v = false;
		int k = 0;
		
		
		for (int i = 0 ; i<tablan.length; i++)
			{
			
			if (tablan[i] != null)
			{
					Object[] arcosre = new Arc[tablan[i].size()];
					arcosre = tablan[i].toArray();
						for(int j=0; j<arcosre.length; j++)
						{
							v = remove.addi((Arc) arcosre[j]);						
						}
			}
		}
		
		return remove;

	}
	
	/** Toma dos vertices , comprueba que existn en el grafo
	* y si existen construye un nuevo grafo con nodo de partida nodeFinId y
	* nodo de llegada nodeIniId
	* Retorna un booleano si la operacion pudo realizarse
	**/
    @Override
    public boolean reverseArc(Vertice nodeIniId, Vertice nodeFinId) {
		
		boolean v = isArc(nodeIniId, nodeFinId);
		
		if (v) {
			
			Arc aux = new Arc (nodeIniId, nodeFinId);
			aux = arcs.remove(aux);
			aux.reverse();
			v = arcs.add(aux);
			aux.reverse();
			aux = arcsr.remove(aux);
			v = arcsr.add(aux);
			
		}
		
		return v;
    }
	
	/** Le aplica el metodo reverseArc a todos los arcos del grafo
	* entendiendo por ella "revertir" sus nodos de entrada y de salida
	**/
    @Override
    public boolean reverseArcs() {
		
		HashTable<Arc> clone = arcs.clone();
		this.arcs = this.arcsr;
		this.arcsr = clone;
		
		return true;
    }
	/** Convierte toda la informacion del grafo en un String
	**/
    @Override
    public String toString() {
		
		String s = "";
		s = numnodes+nodes.toString()+"\n"+numarcs+arcs.toString();
		
		return s;
		
    }
	
	/** Metodo para agregar nodos, parte de un vertice V
	* y lo agrega a la tabla de Hash nodes
	**/
	public boolean addnode(Vertice V)
	{
		boolean vo = this.nodes.add(V);
		return vo;
	}
	
	/** Metodo para obtener los  nodos impares del parametro implicito
	* Devuelve la lista con los nodos impares
	**/
	public Lista<Vertice> nodosimpares()
	{
		Lista<Vertice> impares = new Lista<Vertice>();
		Object[] nodos = this.getAllNodes();
		int k = nodos.length;
		int j =0;
		
		for (int i=0; i<k ; i++)
		{
			int m = getDegree((Vertice) nodos[i]);
				
			if((( m/ 2) % 2) == 1)
			{		
			 impares.add((Vertice)nodos[i]);			 
			}
		}
			
		return impares;
	}

	
}
