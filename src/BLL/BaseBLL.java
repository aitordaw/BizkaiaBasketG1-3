package BLL;

public abstract class BaseBLL<T> {
	public abstract T Get(String busqueda) throws Exception;
}
