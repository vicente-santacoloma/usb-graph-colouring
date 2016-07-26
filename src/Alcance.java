/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/** La clase alcance esta destinada a construir
 * la matriz  asociada a la clausura transitiva del grafo
 * 
 */
public class Alcance extends Algoritmo
 {

	/** Metodo para ejecutar el algoritmo Roy Warshall a el grafo d 
	*   retorna un Digraph x talque x es la clausura transitiva de d 
	*/	
    public DiGraph ejecutar(DiGraph d) 
	{
	
		d = RoyWarshallMatrix((DiGraphHash) d);
		return d;
		
    }
	
	/** Crea la matriz de adyacencia a partir de un grafo  y una matriz de objetos
	*/
	private boolean [][] crearMatrix (DiGraphHash d, Object [] a)
	{
	
		boolean [][] matrix = new boolean [a.length][a.length];
		
		for (int i = 0; i<a.length; i++) 
		{	
		
			for (int j = 0; j<a.length; j++) 
			{
				matrix[i][j] = d.isArc((Vertice) a[i], (Vertice) a[j]);
			}
		}
		
		return matrix;
	}
	
	/** Convierte una matriz de booleanos en un Grafo
	*/
	private DiGraphHash MatrixToDiGraph (boolean [][] matrix, DiGraphHash di, Object [] a) 
	{
	
		DiGraphHash d = di;
	
		for (int i = 0; i<a.length; i++)
		{	
			for (int j = 0; j<a.length; j++) 
			{
				if (matrix[i][j] == true)
				{	
					d.addArc((Vertice) a[i], (Vertice) a[j]);				
				}
			}
		}
		
		return d;
	}
	
	/** Aplicacion  del algoritmo de RoyWarshall  a una matriz
	*/
	private DiGraphHash RoyWarshallMatrix (DiGraphHash di) 
	{
	
		Object [] a = di.getAllNodes();
		boolean [][] matrix = crearMatrix(di,a);
		int tam = a.length;
		
		for (int k = 0; k<tam; k++)
		{		
			for (int i = 0; i<tam; i++) 
			{			
				if (matrix[i][k] == true && i!=k) 
				{				
					for (int j = 0; j<tam; j++)
					{
						matrix[i][j] = matrix[i][j] || matrix[k][j];
					}
				}
			}
		}
		
		return MatrixToDiGraph(matrix,di,a);
	}
	
	/** Aplicacion  del algoritmo de RoyWarshall a un grafo
	*/
	private DiGraphHash RoyWarshall (DiGraphHash d) {
	
		Object [] a = d.getAllNodes();
		
		for (int k = 0; k<a.length; k++) 
		{
			for (int i = 0; i<a.length; i++)
			{
					if (d.isArc((Vertice) a[i], (Vertice) a[k]) && i!=k)
					{
					
						for (int j = 0; j<a.length; j++) 
						{
						
							if (d.isArc((Vertice) a[i], (Vertice) a[j]) || d.isArc((Vertice) a[k], (Vertice) a[j])) 
							{
						
							d.addArc((Vertice) a[i], (Vertice) a[j]);
							}
						
						
						}
					
					}
			}
		}
		
		return d;
	}

}
