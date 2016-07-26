/** Clase Vertice, diseñada para  representar concretamente
 * los nodos que pertenecen a un grafo
 */
public class Vertice 
{
	/** Id representa la identificacion única del nodo
	**/
    private String id;
	private double costo;
	
	/** Sobreescritura del metodo hashCode, 
	 * toma el String  "id"  y aplica el hashCode al ese String
	**/
	public int hashCode()
	{
		int code=0;
		code = this.id.hashCode();
		if (code <0)
		{
			code = -code;
		}		
		return code;
	}
	
	/** Retorna el id de un nodo
	**/
	public String obtenerId()
	{
		return this.id;
	}
	/** Constructor de la Clase 
	* crea  un nodo con un id y un costo
	**/
	public Vertice(String id, double costo)
	{
		this.id = id;
		this.costo = costo;
	}
    
	/** Construye el String representativo del vertice this
	*
	**/
	public String toString()
	{
		String S = this.id+" "+(int)this.costo;
		return S; 
	}
	
	/** Compara el Vertice implicito con el objeto a
	* Si son equivalentes  retorna true, en otros casos false
	**/
	public boolean equals(Object a)
	{
		boolean v= false;
		
		if ( a instanceof Vertice)
		{
			v = this.id.equals(((Vertice)a).getId()) && this.costo == (((Vertice)a).getCosto());
		}
		
		return v;
	}
	
	/**  Retorna el String representativo del id del Vertice this
	**/
	public String getId()
	{
		return this.id;
	}
	
	/**  Retorna el double representativo del costo del Vertice this
	**/	
	public double getCosto()
	{
		return this.costo;
	}
	
	/** Asigna el double  costo al parametro costo del Vertice this
	**/
	public void asigCosto(double costo)
	{
		this.costo = costo;
	}
	
	/** Retorna un objeto equivalente al vertice implicito
	**/

	public Object clone()
	{
		Vertice a= new Vertice(this.id,this.costo);
		return a;
	}
	
}
