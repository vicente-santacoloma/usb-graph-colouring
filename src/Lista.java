/** Contenedor Generico Lista,
*  implementa la interfaz list <E>
*	Representa una lista con las operaciones basicas
*  almanenando en la clase "caja"  un tipo de objeto <E> generico 
*/
public class Lista <E> implements List<E> 
{
	
	/** Los  atributos elementos y ultimo son 
	*  cajas que construyen la lista elementos tiene su cabeza
	*  y ultimo su cola para fines practicos
	*/
    private Caja <E> elementos;
	private Caja <E> ultimo;
	
	/**  Clase privada Caja
	* representa el objeto caracteristico de la lista 
	* Un elemento y un apuntador a la siguiente "caja"
 	*/
	private class Caja <E> 
	{

        private  E informacion;
        private  Caja <E> siguiente;
		

		public Caja () {

            this.informacion = null;
            this.siguiente = null;
		
        }	
	}
	
	/**  Retorna la "caja" con el ultimo apuntador (nulo) de la lista
 	*/
	public Caja <E> obtenerSiguiente() 
	{
	
		return this.ultimo.siguiente;
	}
	
	/** Constructor de la Clase
	* Inicializa la lista en null
 	*/
	public Lista() 
	{
		this.elementos = null;
		this.ultimo = null;

    }
	
	/**  Metodo que agrega un elemento a la lista 
	*  retorna true si el elemento fue agregado y no estaba
	* previamente en la lista
	* y false  en casos  contrarios
 	*/
	public boolean add(E element) 
	{

		boolean insertar = false;
		boolean repetido = false;
		Caja<E> nueva = new Caja<E> ();
		
		nueva.informacion = element;
		nueva.siguiente = null;
		
		repetido = buscarRepetido(element);
	
		if (elementos == null && !repetido) {
			this.elementos = nueva;
			this.ultimo = nueva;
			insertar = true;
		} 
		else if (elementos != null && !repetido) {
			this.ultimo.siguiente = nueva;
			this.ultimo = nueva;
			insertar = true;
		}
		return insertar;
		
	}
	
	/**  Metodo que agrega un elemento a la lista
	* Solo debe utilizarse para elementos
	* No existentes previamente en la lista
	*/
	public boolean addi(E element)
	{
	
		boolean insertar = false;
		boolean repetido = false;
		Caja<E> nueva = new Caja<E> ();
		
		nueva.informacion = element;
		nueva.siguiente = null;
		

	
		if (elementos == null) {
			this.elementos = nueva;
			this.ultimo = nueva;
			insertar = true;
		} 
		else if (elementos != null) {
			this.ultimo.siguiente = nueva;
			this.ultimo = nueva;
			insertar = true;
		}
		return insertar;
		
	}
	
	/**  Metodo que agrega un elemento a la lista
	*  recibe un parametro entero para 
	* agregar a la lista en un lugar especifico
	*/
	public boolean add(int index, E element) 
	{
	
		boolean insertar = false;
		boolean repetido = false;
		Caja<E> nueva = new Caja<E> ();
		Caja<E> aux = this.elementos;
		Caja<E> ant = this.elementos;
		int k = 0;
		int tam = size();
		
		nueva.informacion = element;
		nueva.siguiente = null;
		repetido = buscarRepetido(element);
		
		if (0 <= index && index <= tam ) {
		
			if (index == 0) {
				nueva.siguiente = this.elementos;
				this.elementos = nueva;	
			}
			else if (index == tam) {
				ultimo.siguiente = nueva;
				ultimo = nueva;
			}
			else {
				while (k != index) {
					ant = aux;
					aux = aux.siguiente;
					k++;
				}	
				ant.siguiente = nueva;
				nueva.siguiente = aux;
			
			}
			insertar = true;
		}
		insertar = insertar && repetido;
		return insertar;
	}
	
	/**  Metodo que busca un elemento en la lista
	* devuelve true si element pertenece a la lista 
	* false en otros casos
	*/	
	private boolean buscarRepetido (E element) 
	{
	
		boolean repetido = false;
		Caja<E> aux = this.elementos;
	
		while (aux != null && !repetido) {
		
			if (aux.informacion.equals(element) ) {
			
				repetido = true; 
			
			}
			aux = aux.siguiente;
		
		}
		
		return repetido;
	
	}
	
	/**  Retorna el objeto que representa la ultima caja de la lista
	*/	
	public Caja <E> obtenerUltimo() 
	{
	
		return this.ultimo;
	}
	
	/**  Retorna la caja "cabeza" de la lista
	*/	
	public Caja <E> obtenerElementos() 
	{
	
		return this.elementos;
	}
	
	/** Retorna el entero que contiene el tamaño de la lista
	*/
	public int size() 
	{
	
		int tam=0;
		Caja <E> aux = this.elementos;
		
		if (aux == null) 
		{
			tam = 0 ;
		}
		else 
		{
			
			while (aux != null) 
			{
			
				aux = aux.siguiente;
				tam++;
			
			}
		}
		return tam;
	
	}
	
	/** Reinicializa la lista en null
	*/
	public void clear() 
	{
		this.elementos = null;
		this.ultimo = null;
	}
	
	/** Devuelve (Clona) una lista<E> equivalente a las lista this
	*/
	public Lista<E> clone() 
	{
	
		Caja <E> aux = this.elementos;
		Caja <E> ant = this.elementos;
		Caja <E> nueva = new Caja<E> ();
		Lista <E> clone = new Lista<E>();
		
		if (this.elementos != null) 
		{
		
		clone.elementos = nueva;
		nueva.informacion = aux.informacion;
		aux = aux.siguiente;
		ant = nueva;
		
			while (aux != null) 
			{
		
				nueva = new Caja<E> ();
				nueva.informacion = aux.informacion;
				ant.siguiente = nueva;
				ant = nueva;
				aux = aux.siguiente;
		
			}
		nueva.siguiente = null;
		clone.ultimo = nueva;

		}

		return clone;
		
	}
	
	/** Devuelve  true  si el objeto o pertenece a la lista
	*/
	public boolean contains(Object o) 
	{
		return buscarRepetido((E)o);
	}
	
	/** Devuelve  true si la lista o  es equivalente a la lista this
	*/
	public boolean equals(Lista <E> o) 
	{
	 
		boolean v = false;
		
		if (o != null)
			if (o.elementos.informacion.equals(this.elementos.informacion) && (size() == o.size()))
			{ 
			
				Caja <E> aux1 = this.elementos;
				Caja <E> aux2 = o.elementos;
				v = true;
			
				while (aux1 != null && v) 
				{
				
					v = (aux1.informacion.equals(aux2.informacion));
					aux1 = aux1.siguiente;
					aux2 = aux2.siguiente;
		
				}
			}
		return v;
	}
	
	/** Devuelve el elemento E que se encuentra en la iesima posicion de la lista
	*/
	public E get(int index) 
	{
	
		Caja<E> aux = this.elementos;
		
		if (0 <= index && index < size()) 
		{			
			for (int i = 0; i < index; i++) 
			{
				aux = aux.siguiente;
			
			}
			return aux.informacion;
			
		}
		
		return null;
	}
	
	/** Devuelve la iesima posicion en la que se encuentra el objeto o
	* si el objeto no se encuentra en la lista devuelve -1
	*/
	public int indexOf(Object o)
	{
	
		Caja<E> aux = this.elementos;
		int index = -1;
		int k = 0;
		
		while (aux != null && index == -1) 
		{
		
			if (o.equals(aux.informacion)) 
			{
			index = k; 
			}
			aux = aux.siguiente;
			k++;
		}
		
		return index;
	}
	
	/** Devuelve true si la lista es vacia false en casos contrarios
	*/
	public boolean isEmpty() 
	{
			return size() == 0;
	}
	
	/** Elimina el elemento de la posicion index de la lista
	*/
	public E remove(int index)
	{
	
		Caja<E> aux = this.elementos;
		Caja<E> ant = this.elementos;
		E remove = null;
		
	
		
		if ((0<= index && index<size()) && this.elementos != null) {
		
			if (index == 0) 
			{
				remove = this.elementos.informacion; 
				this.elementos = this.elementos.siguiente;
				aux.siguiente = null;
				if (elementos == null) 
				{
					ultimo = null;
				}
			}
			else {
			
				for (int i = 0; i<index ; i++)
				{
					ant = aux;
					aux = aux.siguiente;
				}
		
				remove = aux.informacion;
				ant.siguiente = aux.siguiente;
				if (ant.siguiente == null)
				{
					ultimo = ant;
				}
			}
		}
		return remove;
	}
	
	/** Elimina el elemento o si este esta en la lista
	* devuelve  true y false  en casos contrarios
	*/
	public boolean remove(Object o) 
	{
		Caja<E> aux = this.elementos;
		Caja<E> ant = this.elementos;
		boolean v = false;
	
		while (aux != null && !v)
		{
		
			if (this.elementos.informacion.equals(((E)o)))
			{
				this.elementos = this.elementos.siguiente;
				v = true;
			}
			else if (aux.informacion.equals(((E)o)))
			{
				ant.siguiente = aux.siguiente;
				aux.siguiente = null;
				v = true;
			
			}
			if (ant.siguiente == null)
			{
			ultimo = ant;
			
			}
			
			ant = aux;
			aux = aux.siguiente;
		}
	
		return v;
		
	}
	
	/** Devuelve un arreglo de objetos que contiene los elementos de la lista
	*/ 
	public Object[] toArray() 
	{
	
		int tam = size();
		Caja<E> aux = this.elementos;
		Object [] a = new Object [tam];
		
		for (int i =0 ; i<tam; i++) 
		{
			a[i] = aux.informacion;
			aux = aux.siguiente;
		}
		
		return a;
	
	}
	
	/** Retorna el String Representativo de la lista
	*/
	public String toString() 
	{
	 
		Caja<E> aux = this.elementos;
		String s = "";

		
		if (this.elementos != null && this.ultimo != null) 
		{
		
				s = aux.informacion.toString();
				aux = aux.siguiente;
		
				while (aux != null) 
				{
				
				s = s+"\n"+aux.informacion.toString();
				aux = aux.siguiente;
				
				}
		
		}

	return s;
	 
	}
	
}