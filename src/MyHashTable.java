  /**
   * List es una interfaz que determina los metodos basicos que debe tener una
   * lista independiente de su implementacion interna
   * 
   * @author Les profs
   * @version 1.0
   * @since 1.6
   */

public interface MyHashTable <E> 
{
 
    /**
     * Agrega <i>element</i> a la tabla.
     *
     * @param Elemento de tipo E, con el que se declaro el objeto
     * @return true si el elemento fue insertado, false en caso contrario
     */
	
    public boolean add(E element);
    /**
     * Reinicializa la tabla de hash
     * queda como recien creada
     */

    public void clear();

    /**
     * Retorna una nueva {@code MyHashTable} con los mismos elementos que estan
     * {@code this}.
     *
     * @return una lista con los mismos elementosque esta lista
     * @see java.lang.Cloneable
     */

    public MyHashTable clone();

    /**
     * Determina si el objeto <i>o</i> esta contenidoe n la lista.
     * {@code Object equals}
     *
     * @see Object#equals
     */
    public boolean contains(Object o);

    /**
     * Determina si el objeto <i>o</i> es igual a la tabla actual.
     *
     * @param la tabla con la que se desea comparar
     * 
     * @return true si las dos tablas tienen el mismo tamaño y contienen 
     * los mismos objetos. false en caso contrario
     *
     */

    public boolean equals(Object o);

    /**
     * Determina si la tabla tiene elementos.
     *
     * @return true si size() &eq; 0. falso en caso contrario
     */
    public boolean isEmpty();

    /**
     * Elimina el elemento que contiene la clave.
     *
     * @param index la posicion del elemento a eliminar, 0 &le; index &lt; size()
     * @return el elemento eliminado, si no se elimino ningun elemento retorna
     * null
     */
    public E remove(E e);
    
    /**
     * Retorna el numero de elementos enla tabla
     *
     * @return el numero de elementos en la lista
     */
    public int size();

    /**
     * Retorna un nuevo arreglo que contiene todos los elementos
     * en esta lista {@code MyHahsTable}.
     *
     * @return an array of the elements from this {@code MyHashTable}.
     */

    public Object[] toArray();

    /**
     * Retorna la retpresentacion en String de esta {@code tabla}
     *
     * @return la retpresentacion en String de esta {@code tabla}
     */
    @Override
    public String toString();

}
