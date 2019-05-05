package DAL.OBJECTDB;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 2871418349717969581L;

	@Id
	@GeneratedValue
	private long id;
	private String usuario;
	private String password;
	
	public Usuario() {
	}
	
	public Usuario(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
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
}
