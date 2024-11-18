package modelo.main.ud2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import modelo.Person;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class OraclePersonMappingExample {
    public static void main(String[] args) {
        // String url = "jdbc:oracle:thin:@192.168.56.102:1521/xepdb1";
        // //String url = "jdbc:oracle:thin:@192.168.56.102:1521:xepdb1"; // Cambia por tu conexión
        // String user = "people_user"; // Usuario de la BD
        // String password = "abc123."; // Contraseña de la BD


        OracleDataSource ods= null;
		Connection conn =null;
		try {
			ods = new OracleDataSource();
			


        //Update url por la ip que corresponda
			String url = "jdbc:oracle:thin:@192.168.56.102:1521/xepdb1";
			ods.setURL(url);
			ods.setUser("people_user");
			ods.setPassword("abc123.");
			conn= ods.getConnection();

			// Create Oracle DatabaseMetaData object
			DatabaseMetaData meta = conn.getMetaData();

			// gets driver info:
			System.out.println("JDBC driver version is " + meta.getDriverVersion());

        
            System.out.println("Conexión exitosa a la base de datos.");

            // Registrar el mapeo del tipo UDT
            ((OracleConnection) conn).setTypeMap(Map.of("PERSON_TYP", Person.class));

            // Consulta para recuperar el objeto
            String query = "SELECT VALUE(p) FROM person_obj_table2 p ";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
               // stmt.setInt(1, 1); // Cambia el ID según lo necesites

                // Ejecutar la consulta
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Recuperar el objeto mapeado automáticamente a la clase Person
                        Person person = (Person) rs.getObject(1);

                        // Usar métodos de la clase Person
                        System.out.println("Información del objeto recuperado:");
                        person.displayDetails();
                    } else {
                        System.out.println("No se encontró ningún registro con el ID especificado.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
