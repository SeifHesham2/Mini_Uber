import javax.xml.crypto.Data;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        Employee seif = new Employee();
        seif.setFirstName("mohamed");
        seif.setEmail("mohamedehab@gmail.com");
        seif.setPassword("123");
        seif.setLastName("zahran");
        seif.setPhone("01099688108");
        Employee.insertEmployee(seif);

    }
}