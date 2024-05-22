package ConexionBD;

public class Paciente {

    //atributos
    private int id;
    private String nombre;
    private String apellidos;
    private int n_operaciones;

    //constructor
    public Paciente(int id, String nombre, String apellidos, int num_operaciones) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.n_operaciones = num_operaciones;
    }

    //getters y setters
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
    public int getNum_operaciones() {
        return n_operaciones;
    }
    public void setNum_operaciones(int num_operaciones) {
        this.n_operaciones = num_operaciones;
    }

    //método toString
    @Override
    public String toString() {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", num_operaciones=" + n_operaciones +
                '}';
    }
}
