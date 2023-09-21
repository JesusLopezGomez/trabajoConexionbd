package dbconnect.main.java.model;

public class Cliente {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String dni;
	private String email;
	private String fechaNacimiento;
	
	
	public Cliente(int id, String nombre, String apellidos, String fechaNacimiento, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	@Override
	public String toString() {
		return String.format("Id %s, nombre %s, apellidos %s, dni %s, email %s, fecha de nacimiento %s",this.id,this.nombre,this.apellidos,this.dni,this.email,this.fechaNacimiento);
	}
	
	
	

}
