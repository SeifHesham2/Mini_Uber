import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseConnector
{
    public void  ConnectToDatabase(){
   try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MiniUber", "root", "123"))
   {
    System.out.println("Connected to the database");
   }
   catch (SQLException e)
   {
    System.out.println("Unable To connect To Database");
   }
                                      }
}
