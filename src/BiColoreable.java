public class BiColoreable extends Algoritmo
{
	private DiGraphHash digrafo;
	private Lista <Vertice> nodosAbiertos;
	private String Bicolor ;
	
	public BiColoreable()
	{
		this.nodosAbiertos = new Lista<Vertice>() ;
	}

	public DiGraph  ejecutar(DiGraph D)
	{
		this.digrafo = (DiGraphHash) D;
		boolean solucion = BiColoreableB();
		if (solucion)
		{
			this.Bicolor= "BICOLOREABLE.";	
		}
		if (!solucion)
		{
			this.Bicolor = "NOT BICOLOREABLE.";
		}
	
		return this.digrafo ;
	}
	public String Bicolor()
	{
		return this.Bicolor;
	}
	
	public boolean BiColoreableB()
	{
		Vertice n = digrafo.getVerticeRandom();
		nodosAbiertos.add(n);
		Lista <Vertice> Sucesores = digrafo.getSucesors(n);
		boolean v = false;
		while (!(nodosAbiertos.isEmpty())) 
		{
			n = nodosAbiertos.get(nodosAbiertos.size()-1);
			colorear(n); 
			nodosAbiertos.remove(n);
			v = ((int)n.getCosto()) <= 1;
			if (v == false)
			{
				nodosAbiertos.clear();
			}
		}		
		return v;
	}

	private void colorear(Vertice M)
	{
		Lista<Vertice> SucesoresM = digrafo.getSucesors(M);
		M.asigCosto(MenorCostoNoUtilizado(SucesoresM));
		
	}	
	
	private int MenorCostoNoUtilizado(Lista<Vertice> Sucesores)
	{
		int menor = 0;
		boolean usado0 = false;
		boolean usado1 = false;
		
		int l = Sucesores.size();
		
		for (int i = 0; i<l; i++)
		{
			Vertice aux = Sucesores.get(i);
				if( ((int)aux.getCosto()) == 0)
				{
					usado0= true;
				}
				if ( ((int)aux.getCosto()) == 1)
				{
					usado1= true;
				}
				if (usado0 && !usado1)
				{
					menor = 1;
				}
				if ( usado0 && usado1)
				{
					menor = 2;
					i = l;
				}
				if ( !usado0 && usado1)
				{
					menor = 0;
				}
				if ( aux.getCosto()== 4.0)
				{
					nodosAbiertos.add(aux);
				}
		}
		return menor; 
	}
}	
	