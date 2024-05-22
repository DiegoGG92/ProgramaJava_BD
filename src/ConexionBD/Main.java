package ConexionBD;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    static Connection conn = null;
    public static void main(String[] args) throws SQLException {

        int opcion;
        Scanner ska = new Scanner(System.in);
        boolean conectada = false;
        boolean creada = false;
        boolean insertado = false;

        do {
            System.out.println("Elija una opción.");
            System.out.println("Pulse 1 para establecer la conexión con la base de datos.");
            System.out.println("Pulse 2 para crear tablas en la base de datos.");
            System.out.println("Pulse 3 para eliminar tablas en la base de datos.");
            System.out.println("Pulse 4 para insertar registros en la base de datos.");
            System.out.println("Pulse 5 para borrar un paciente de la base de datos.");
            System.out.println("Pulse 6 para borrar una tabla de la base de datos.");
            System.out.println("Pulse 7 para actualizar el número de operaciones de un paciente de la base de datos.");
            System.out.println("Pulse 8 para consultar un paciente de la base de datos.");
            System.out.println("Pulse 9 para consultar por apellidos un paciente de la base de datos.");
            System.out.println("Pulse 10 para almacenar pacientes en un ArrayList.");
            System.out.println("Pulse 0 para salir el programa.");
            System.out.print("¿Cuál es su elección?: ");
            opcion = ska.nextInt();


            switch (opcion){
                case 0: System.out.println("Fin del programa. Espero haber sido de utilidad.");
                    break;
                case 1: crearConexion();
                    conectada=true;
                    break;

                case 2: if (conectada){
                    crearTablas();
                    creada = true;
                } else {
                    System.out.println("Primero se debe establecer la conexión con la opción 1.");
                }
                    break;

                case 3:  if (conectada && creada) {
                    eliminarTablas();
                } else if (conectada == false) {
                    System.out.println("Primero se debe establecer la conexión con la opción 1.");
                } else {
                    System.out.println("Las tablas no están creadas.");
                }
                    break;

                case 4: if (conectada && creada) {
                    insertarRegistros();
                } else if (conectada == false) {
                    System.out.println("Primero se debe establecer la conexión con la opción 1.");
                } else {
                    System.out.println("Las tablas no están creadas.");
                }
                    break;

                case 5: if (conectada && creada) {
                    borrarPaciente();
                } else if (conectada == false) {
                    System.out.println("Primero se debe establecer la conexión con la opción 1.");
                } else {
                    System.out.println("Las tablas no están creadas.");
                }
                    break;

                case 6: if (conectada && creada) {
                    borrarTabla();
                } else if (conectada == false) {
                    System.out.println("Primero se debe establecer la conexión con la opción 1.");
                } else {
                    System.out.println("Las tablas no están creadas.");
                }
                    break;

                case 7: if (conectada && creada) {
                    actualizarNumeroOperaciones();
                } else if (conectada == false) {
                    System.out.println("Primero se debe establecer la conexión con la opción 1.");
                } else {
                    System.out.println("Las tablas no están creadas.");
                }
                    break;

                case 8: if (conectada && creada) {
                    consultarPaciente();
                } else if (conectada == false) {
                    System.out.println("Primero se debe establecer la conexión con la opción 1.");
                } else {
                    System.out.println("Las tablas no están creadas.");
                }
                    break;

                case 9: if (conectada && creada) {
                    consultarPorApellido();
                } else if (conectada == false) {
                    System.out.println("Primero se debe establecer la conexión con la opción 1.");
                } else {
                    System.out.println("Las tablas no están creadas.");
                }
                    break;

                case 10: if (conectada && creada) {
                    almacenarPacientes();
                } else if (conectada == false) {
                    System.out.println("Primero se debe establecer la conexión con la opción 1.");
                } else {
                    System.out.println("Las tablas no están creadas.");
                }
                    break;

                default:
                    System.out.println("El número introducido no es correcto.");
            }

        } while (opcion != 0);
    }

    private static void crearConexion() throws SQLException {
        //Establecimiento de la conexión
        String url = "jdbc:mysql://localhost:3306/programa_java";
        String user = "root";
        String passwd = "admin";

        conn = DriverManager.getConnection(url, user, passwd);
        System.out.println("Establecida la conexión.");
        System.out.println(conn.getCatalog());
        System.out.println();
    }

    private static void crearTablas() throws SQLException {
        //Crear la tabla dentro de la base de datos programa_java
        String query =
                "CREATE TABLE IF NOT EXISTS paciente(" +
                        "id int primary key," +
                        "nombre varchar(30) not null," +
                        "apellidos varchar(100) not null," +
                        "n_operaciones int)";

        Statement st = conn.createStatement();
        st.execute(query);
        System.out.println("Tabla creada correctamente.");

        String query2 =
                "CREATE TABLE IF NOT EXISTS doctor(" +
                        "id int primary key," +
                        "nombre varchar (30) not null," +
                        "apellidos varchar(100) not null," +
                        "especialidad varchar(20) not null)";

        st.execute(query2);
        System.out.println("Tabla creada correctamente.");
        System.out.println();
    }

    private static void eliminarTablas() throws SQLException {
        String query3 =
                "DROP TABLE IF EXISTS paciente;";

        String query4 =
                "DROP TABLE IF EXISTS doctor;";

        Statement st = conn.createStatement();

        st.execute(query3);
        System.out.println("Tabla eliminada correctamente");

        st.execute(query4);
        System.out.println("Tabla eliminada correctamente");
        System.out.println();
    }

    private static void insertarRegistros() throws SQLException {
        Scanner ska = new Scanner(System.in);
        PreparedStatement ps = conn.prepareStatement("insert into paciente values (?, ?, ?, ?)");
        System.out.print("Escriba el id del paciente: ");
        int id = ska.nextInt();
        System.out.print("Escriba el nombre del paciente: ");
        String nombre = ska.next();
        System.out.print("Escriba los apellidos del paciente: ");
        String apellidos = ska.next();
        System.out.print("Escriba el número de veces que el paciente ha sido operado: ");
        int operacion = ska.nextInt();


        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setString(3, apellidos);
        ps.setInt(4, operacion);
        ps.execute();
        System.out.println("Insertado correctamente.");
        System.out.println();


        System.out.println("Inserción 2");
        ps.setInt(1,2);
        ps.setString(2, "Celia");
        ps.setString(3,"Jaén");
        ps.setInt(4, 3);
        ps.execute();
        System.out.println("Insertado correctamente");
        System.out.println();

        System.out.println("Inserción 3");
        Statement st = conn.createStatement();
        String query3 = "insert into paciente values (3, \"Elena\", \"Bermejo\", 1);";
        st.execute(query3);
        System.out.println("Insertado correctamente");
        System.out.println();


    }

    private static void borrarPaciente() throws SQLException {

        System.out.println("Primer borrado");
        Scanner ska = new Scanner(System.in);
        System.out.print("Escriba el id del usuario que desea eliminar: ");
        int eleccion = ska.nextInt();
        PreparedStatement ps = conn.prepareStatement("delete from paciente where id = ?");
        ps.setInt(1, eleccion);
        ps.execute();
        System.out.println("Registro borrado correctamente");
        System.out.println();
    }

    private static void borrarTabla() throws SQLException {

        System.out.println("Borrado de tabla");
        Statement st = conn.createStatement();
        String query4 = "truncate paciente;";
        st.execute(query4);
        System.out.println("Borrado de tabla completo");
        System.out.println();
    }

    private static void actualizarNumeroOperaciones() throws SQLException {
        Scanner ska = new Scanner(System.in);
        System.out.print("Introduzca el ID del paciente al que desea actualizar el número de operaciones: ");
        int id= ska.nextInt();
        System.out.print("Introduzca el número correcto de operaciones: ");
        int numope= ska.nextInt();

        PreparedStatement ps = conn.prepareStatement("update paciente set n_operaciones = ? where id = ?;");
        ps.setInt(1, numope);
        ps.setInt(2, id);
        ps.execute();
        System.out.println("Paciente actualizado correctamente");
    }

    private static void consultarPaciente() throws SQLException {
        Scanner ska=new Scanner(System.in);
        System.out.print("Introduzca el ID del paciente que quiere consultar: ");
        int id = ska.nextInt();

        PreparedStatement ps = conn.prepareStatement("Select * from paciente where id = ?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            System.out.println("El ID del paciente es " + rs.getInt(1) + ", el nombre es " + rs.getString(2) + ", el apellido es " + rs.getString(3) + " y el número de operaciones que figuran en su historial es de " + rs.getInt(4) + ";");

        }
        System.out.println();
    }

    private static void consultarPorApellido() throws SQLException {

        Scanner ska=new Scanner(System.in);
        System.out.print("Introduzca el apellido del paciente que quiere consultar: ");
        String apellido = ska.next();

        PreparedStatement ps = conn.prepareStatement("Select * from paciente where apellidos = ?;");
        ps.setString(1, apellido);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            System.out.println("El ID del paciente con apellido " + apellido + " es " + rs.getInt(1) + ", el nombre es " + rs.getString(2) + " y el número de operaciones que figuran en su historial es de " + rs.getInt(4) + ";");

        }
        System.out.println();
    }

    private static void almacenarPacientes() throws SQLException {

        PreparedStatement ps = conn.prepareStatement("Select * from paciente where n_operaciones = 3;");

        ResultSet rs = ps.executeQuery();

        ArrayList<Paciente> arrayListPacientes = new ArrayList<>();

        while (rs.next()) {
            arrayListPacientes.add(new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
        }

        //imprime con iterator
        Iterator<Paciente> itr = arrayListPacientes.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }

        //imprime con bucle for each
        for (Paciente p : arrayListPacientes) {
            System.out.println(p);
        }

    }
}