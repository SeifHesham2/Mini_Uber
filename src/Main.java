import javax.xml.crypto.Data;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        Employee taftaf= new Employee();
        taftaf.setFirstName("Mustafa");
        taftaf.setEmail("mustafaHosny2002@gmail.com");
        taftaf.setPassword("123");
        taftaf.setLastName("Hosny");
        taftaf.setPhone("01000512412");
        InsertToDatabase.insertEmployee(taftaf);

    }
}