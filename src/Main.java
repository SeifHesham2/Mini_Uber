import javax.xml.crypto.Data;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        Employee seif = new Employee();
        seif.setFirstName("mohamed");
        seif.setEmail("mohamedashraf@gmail.com");
        seif.setPassword("123");
        seif.setLastName("ashraf");
        seif.setPhone("01099688109");
        InsertToDatabase.insertEmployee(seif);

    }
}