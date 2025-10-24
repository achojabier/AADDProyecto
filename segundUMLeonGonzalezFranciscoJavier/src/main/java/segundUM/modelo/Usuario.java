package segundUM.modelo;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	private String id;
	@Column(unique = true, nullable = false)
	private String email;
	private String nombre, apellidos,clave;
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	private String telefono;
	private boolean administrador;
	
	public Usuario(){
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public Usuario(String nombre, String apellidos, String email, String clave, Date fecha, String tlf) {
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.email=email;
		this.clave=clave;
		this.fechaNacimiento=fecha;
		this.telefono=(tlf!=null)?tlf: null;
		this.administrador=false;
	}
	
	/*public String altaUsuario(String nombre, String apellidos, String email, Date fecha, String tlf) {
		Usuario u = new Usuario(nombre,apellidos,email,fecha,tlf);
		String id = UUID.randomUUID().toString();
		u.setId(id);
		return id;
	}*/
}
