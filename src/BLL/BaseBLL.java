package BLL;

// Clase abstracta de la que heredaran todas las clases BLL
public abstract class BaseBLL<T> {
	public abstract T Get(String busqueda) throws Exception;
}
