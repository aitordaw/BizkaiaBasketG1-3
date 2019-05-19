package DAL.OBJECTDB;

import java.util.ArrayList;

import javax.persistence.*;

import DAL.DataModel;
import DAL.Roles;

@Entity
public class Usuario extends ObjectDbDataModel {
	private static final long serialVersionUID = 2871418349717969581L;

	@Id
	@GeneratedValue
	private long id;
	@Id
	private String usuario;
	private String password;
	private Roles rol;
	
	public Usuario() {
		super();
		campoBusqueda = "usuario";
	}
	
	public Usuario(String usuario, String password, Roles rol) {
		this();
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}
	
	@Override
	public ArrayList<DataModel> crearPorDefecto() {
		ArrayList<DataModel> result = new ArrayList<DataModel>();
		
			result.add(new Usuario("admin", "admin", Roles.ADMINISTRADOR));
		
		return result;
	}
}
