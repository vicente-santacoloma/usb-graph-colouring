/**
 * Arc es una clase que permite almancenar la informacion de los arcos de un
 * grafo
 **/

public class Arc {

    private String id = "";
	private Vertice fuente = null;
	private Vertice destino = null;
	private double costo= 0;
	
    /** Crea un arco entre los nodos src y dst
    */
    public Arc(Vertice src, Vertice dst) 
	{
		this.fuente = src;
		this.destino = dst;
		this.id = src.getId();
	
	}
	
	/** Sobreescritura de la forma hashCode 
	* aplica el hashCode  al parametro fuente
    */
	public int hashCode()
	{
		return this.fuente.hashCode();
	}
	
	/** Constructor de la clase
	* crea un arco a partir de dos nodos y un costo
    */
	public Arc(Vertice src, Vertice dst, double Costo)
	{
		this.fuente = src;
		this.destino = dst;
		this.costo = Costo;
	}

    /**
     * retorna el nodo inicial del arco
     */
    public Vertice getSrc() 
	{
        return this.fuente;
    }
	
    /**
     * retorna el nodo final del arco
     */
    public Vertice getDst() 
	{
        return this.destino;
    }

    /**
     * Retorna un nuevo  Arc con el mismo fuente y el mismo destino que
     * este Arc.
    */
    @Override
    public Object clone()
	{
        Arc c =  new Arc(this.fuente, this.destino, this.costo);
       return c;
    }

    /**
     * Indica si el Arc a es igual a este Arc
     */
    public boolean equals(Object a) {
		
		boolean v = false;
	   if (!(a instanceof Arc))
		{
			v = false;
			
		} else 
		{	
			
			v = this.fuente.toString().equals(((Arc)a).getSrc().toString()) && this.destino.toString().equals(((Arc)a).getDst().toString());
			
		}
       return v;
    }

    /**
     * Retorna la representacion en String del Arc this
     */
    @Override
    public String toString() 
	{
		String S = null;
		S= this.fuente.obtenerId()+" "+this.destino.obtenerId()+" "+(int)this.costo;	
		return S;
    }

    /** Invierte los nodos de un arco  
     */
    public void reverse()
	{
		Vertice nuevodestino = this.fuente;
		Vertice nuevofuente = this.destino;
		this.fuente =  nuevofuente;
		this.destino = nuevodestino;
		
    }
	
	/** Asigna al parametro this.costo el intero cos  
     */
	public void asigCosto(double cos)
	{
		this.costo = cos;
	}
	
	/** Retorna el real costo
	*/
	public double getCosto()
	{
		return this.costo;
	}
	
	/** Retorna el String que contiene el id del arco
     */
	public String getId()
	{
		return this.id;
	}

}

	
	
