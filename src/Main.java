import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.sql.Time;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        Employee seif = new Employee();
        seif.setPhone("01099688107");
        seif.setLastName("hesham");
        seif.setFirstName("seif");
        InsertToDatabase.insertEmployee(seif);


    }
}