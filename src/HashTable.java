/** Contenedor Generico HashTable,
*  implementa la interfaz MyHashTable <E>
*	Representa una tabla de Hash con las operaciones basicas
*  pero almacenando en la tabla listas de un tipo de objeto <E> generico 
*/
public class HashTable <E> implements  MyHashTable <E>
{
  /** Capacidad   cantidad de clases de equivalencia de la tabla
  *  Tabla  contiene listas con objetos del tipo <E>
  *  Tam representa la cantidad de elementos que no son vacios de la tabla
  */
	private int capacidad;
    private Lista<E> [] Tabla ;
    private  int tam = 1  ;
	   
	/** Constructor de la clase, Recibe un entero c para el tamaño de la tabla,
	* es decir , las clases de equivalencia
	*/   
	public  HashTable (int c)
	{
		Tabla = (Lista<E> []) new Lista [c];
		this.capacidad= c;	
	}
	
	/** Funcion de asignacion de elementos a posiciones de la tabla
	*/
	public int funcion(E elemento)
    {
		return elemento.hashCode() % capacidad;
    }	
	
	/** Agrega un elemento a la tabla, si este pudo ser agregado devuelve true
	* en casos  contrarios  devuelve false 
	*/
    public boolean add(E element)
	{	
		boolean v = false;
		if (Tabla[funcion(element)] == null) 
		{
			Tabla[funcion(element)] = new Lista<E>();

		} 
		
			v= Tabla[funcion(element)].add(element);
		
		if (v)
		{
			tam++;
		}
		return v;
		
	}
   
	/**  Reinicia la tabla de hash en una nueva tabla
	*/
    public void clear()
	{
		this.Tabla=(Lista<E> [] ) new Lista[capacidad];
		this.tam = 0;
	}	
	
	/** Clona la tabla de hash 
	*/
    public HashTable clone()
	{
		HashTable<E> H;
		H= new HashTable<E> (this.capacidad);
		
		for (int i=0; i<this.capacidad; i++)
		{
			if (Tabla[i] != null)
			{
				H.Tabla[i] = this.Tabla[i].clone();
			}
		}
		H.modificarTam(this.tam);  
		return H;
	}
	
	/** Devuelve true si el objeto o pertenece a la tabla de hash 
	* false  en casos contrarios 
	*/
	public boolean contains(Object o)
	{
		boolean v;
		v= false;
		int k = funcion((E)o);
		if(Tabla[k]!= null && !Tabla[k].isEmpty())
		{
			v = Tabla[k].contains((E)o);
		}
		
		return v;
	}
	
	/** Devuelve true si el objeto o es equivalente a la tabla de hash 
	*/
	public boolean equals(Object o)
	{
		boolean v;
		v= true;	
		
		if ( !(((HashTable<E>)o).size() == this.size()))
		{
			v = false;
		} else 
		{
			for (int i=0 ; i< this.capacidad && v ; i++)
			{
			if (Tabla[i]!= null )
			{
					v = this.Tabla[i].equals((((HashTable<E>)o).obtenerTabla())[i]);
			}else
			if (Tabla[i] == null )
			{
				v= ((((HashTable<E>)o).obtenerTabla())[i]) == null;
			}
			}
		
		}
		return v;
	}
	
	/**Devuelve un arreglo de listas similar a la tabla 
	*/
	public Lista<E>[] obtenerTabla()
	{
		return this.Tabla;
	}
	
	/**Retorna la cantidad de elementos no vacios de la tabla
	*/
	public int size()
	{
		return this.tam;
	}
	
	/**Si la  tabla no contiene elementos no vacios devuelve true
	* en otros casos  devuelve false 
	*/
	public boolean isEmpty()
	{
		return (size() == 0);
	}
	
	/**Elimina un elemento de la tabla
	* 	y lo devuelve si pertenece  a ella y fue eliminado
	*/
    public E remove(E e)
	{
		E eliminado= null;
		boolean v= false;
		
		if (Tabla[funcion(e)] != null)
		{
			v = Tabla[funcion(e)].remove(e);
		}
		if (v)
		{
			eliminado= e;
		}		
		
		return eliminado;
	}
	
	/** Devuelve un arreglo de objetos con los elementos
	* 	de la tabla de hash
	*/
    public Object[] toArray()
	{
		return this.Tabla;
	}
	
	/** Retorna un String representativo del grafo 	
	*/
	public String toString()
	{
		String stabla = "";
		if (Tabla != null)
		{
			for( int i=0; i<this.capacidad; i++)
			{
				if (Tabla[i]!=null && !Tabla[i].isEmpty()) 
				{
					stabla = stabla+"\n"+Tabla[i].toString();
				}
			}
		}
	return stabla;
	}

	/** Modifica el tamaño de la  tabla de hash	
	*/	
	public void modificarTam (int x) {
	
		this.tam = x;
	
	}
	
}