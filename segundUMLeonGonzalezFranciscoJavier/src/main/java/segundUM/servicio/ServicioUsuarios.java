package segundUM.servicio;

import java.util.Date;
import java.util.UUID;

import segundUM.modelo.Usuario;
import segundUM.repositorio.IRepositorioUsuarios;

public class ServicioUsuarios {
	private IRepositorioUsuarios repositorioUsuarios;
	
	public ServicioUsuarios(IRepositorioUsuarios ru) {
		this.repositorioUsuarios = ru;
	}
	
	public String altaUsuario(String nombre, String apellidos, String email, String clave, Date fechaNacimiento, String tlf) {
		Usuario usuario = new Usuario(nombre,apellidos,email, clave,fechaNacimiento,tlf);
		
		String id = UUID.randomUUID().toString();
		
		usuario.setId(id);
		
		this.repositorioUsuarios.add(usuario);
		return id;
	}
	
	public void modificarUsuario(String id, String nombre, String apellidos, String clave, Date fechaNacimiento, String tlf) {
		Usuario usuario = this.repositorioUsuarios.get(id);
		if(usuario==null) {
			throw new RuntimeException("Usuario con ID " + id+ "no encontrado\n");
		}
		if (nombre != null && !nombre.trim().isEmpty()) {
	        usuario.setNombre(nombre);
	    }
	    
	    if (apellidos != null && !apellidos.trim().isEmpty()) {
	        usuario.setApellidos(apellidos);
	    }
	    
	    if (clave != null && !clave.trim().isEmpty()) {
	        usuario.setClave(clave);
	    }
	    
	    if (fechaNacimiento != null) {
	        usuario.setFechaNacimiento(fechaNacimiento);
	    }
	    
	    usuario.setTelefono(tlf);
	    
	    this.repositorioUsuarios.update(usuario);
	}
}
