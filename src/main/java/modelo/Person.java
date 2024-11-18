package modelo;

import java.sql.SQLData;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.SQLException;

public class Person implements SQLData {
    private int idno;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    // Variable que identifica el tipo SQL
    private String sqlType = "PERSON_TYP";

    // Constructor vacío (necesario para deserialización)
    public Person() {}

    // Constructor completo
    public Person(int idno, String firstName, String lastName, String email, String phone) {
        this.idno = idno;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    // Métodos de la interfaz SQLData
    @Override
    public String getSQLTypeName() {
        return sqlType;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        this.sqlType = typeName; // Guardar el tipo SQL
        this.idno = stream.readInt();
        this.firstName = stream.readString();
        this.lastName = stream.readString();
        this.email = stream.readString();
        this.phone = stream.readString();
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeInt(this.idno);
        stream.writeString(this.firstName);
        stream.writeString(this.lastName);
        stream.writeString(this.email);
        stream.writeString(this.phone);
    }

    // Métodos adicionales
    public void displayDetails() {
        System.out.println(idno + " " + firstName + " " + lastName);
        System.out.println(email + " " + phone);
    }

    // Getters y Setters
    public int getIdno() {
        return idno;
    }

    public void setIdno(int idno) {
        this.idno = idno;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
