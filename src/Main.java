import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.File;
import java.util.*;
import java.io.IOException;

public class Main
 {

	private String inFile;
	private String outFile;
	private	DiGraphHash digrafo;
	
	public Main (String inF, String outF)
	{
		inFile = inF;
		outFile = outF;
	}
	
	/** Metodo para procesar  y ejecutar el algoritmo Alcance
	*
	*/
	private int procesar ()
	{
		String linea = "";
		BufferedReader in = null;
		PrintStream out = null;
		
		try {
		
			in = new BufferedReader(new FileReader(inFile));
			out = new PrintStream(outFile);
			linea = in.readLine();
			
			while (linea != null) {
			
					int numnodes = Integer.parseInt(linea);
					digrafo = new DiGraphHash(numnodes);
					linea = in.readLine();
					int numarcs = Integer.parseInt(linea);
					cargarNodes(numnodes);
					for (int i = 0; i<numarcs; i++) {
					
						linea = in.readLine();
						String [] tokens = linea.split(" ");
						String fuente = tokens[0];
						String destino = tokens[1];
						Vertice nodof = new Vertice (fuente,4.0);
						Vertice nodod = new Vertice (destino,4.0);
						nodof = digrafo.getVertice(nodof);
						nodod = digrafo.getVertice(nodod);
						digrafo.addArc(nodof, nodod);
						digrafo.addArc(nodod, nodof);
						
					}
					Algoritmo alg = new BiColoreable();
					digrafo = (DiGraphHash)alg.ejecutar((DiGraph)digrafo);
					String bicolor = ((BiColoreable) alg).Bicolor();
					linea = in.readLine();
					if (linea.equals("0")) {
						out.print(bicolor);
						out.flush();
					} else {
						out.println(bicolor);
						out.flush();
					}
			}
		
		} catch (Exception ioe) 
		{
			return -1;
		}
		
		return 0;
	}
	
	private void cargarNodes (int numnodes) 
	{
		double costo = 4.0;
		for (int i = 0; i<numnodes; i++)
		{
			String id = Integer.toString(i);
			Vertice nodo = new Vertice(id, costo);
			digrafo.addnode(nodo);
		}
	}
	
	 public static void main(String[] args) 
	 {
        if (args.length != 2) 
		{
            System.exit(-1);
        }

        String fileIn = args[0];
        String fileOut = args[1];

        Main m = new Main(fileIn, fileOut);

        System.exit(m.procesar());
    }
}