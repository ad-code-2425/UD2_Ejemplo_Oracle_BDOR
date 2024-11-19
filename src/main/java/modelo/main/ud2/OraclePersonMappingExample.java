package modelo.main.ud2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modelo.Person;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class OraclePersonMappingExample {
    public static void main(String[] args) {

        // <!-- https://www.oracle.com/database/technologies/faq-jdbc.html -->

        try {
            OracleDataSource ods = new OracleDataSource();

            // Update url por la ip que corresponda
            String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
            ods.setURL(url);
            ods.setUser("people_user");
            ods.setPassword("abc123.");

            String query = "SELECT value(p) from person_obj_table2 p ";
            try (
                    Connection conn = ods.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query);) {

                // Registrar el mapeo del tipo UDT (user defined type)
                ((OracleConnection) conn).setTypeMap(Map.of("PERSON_TYP", Person.class));

                List<Person> people = new ArrayList<>();
                // Ejecutar la consulta
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Person person = (Person) rs.getObject(1);
                    people.add(person); // Add to the list
                }

                System.out.println("Personas recuperadas:");
                for (Person person : people) {
                    person.displayDetails();
                    System.out.println("-------------");
                }
                if (people.size() == 0) {
                    System.out.println("No se ha recuperado ningún registro");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
