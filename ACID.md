To ensure the correctness of a transaction, it must be **Atomic**, **Consistent**, **Isolated** and **Durable** (ACID).

- The ***atomicity*** ensures that all or none of the steps of a transaction should complete.
  Here's how you might manage a transaction to ensure atomicity,
  where either all operations succeed, or none do:

  ```java
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;
    import java.sql.Statement;
    
    public class AtomicityExample {
    public static void main(String[] args) {
    String url = "jdbc:yourdb://hostname:port/dbname";
    String user = "username";
    String password = "password";
    
          try (Connection conn = DriverManager.getConnection(url, user, password)) {
              conn.setAutoCommit(false);  // Start transaction
    
              try (Statement stmt = conn.createStatement()) {
                  stmt.executeUpdate("INSERT INTO Accounts (AccountID, Balance) VALUES (1, 1000)");
                  stmt.executeUpdate("INSERT INTO Accounts (AccountID, Balance) VALUES (2, 1500)");
                  conn.commit();  // Commit transaction
              } catch (SQLException e) {
                  conn.rollback();  // Rollback transaction on error
                  e.printStackTrace();
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
    }
  ```

- ***Consistency*** takes data from one valid state to another valid state.
  This example ensures that any changes made to the database are consistent with certain conditions (e.g., balance must
  not be negative):
  ```java
  import java.sql.*;
  
  public class ConsistencyExample {
      public static void main(String[] args) {
          String url = "jdbc:yourdb://hostname:port/dbname";
          String user = "username";
          String password = "password";
  
          try (Connection conn = DriverManager.getConnection(url, user, password)) {
              conn.setAutoCommit(false);
  
              try (Statement stmt = conn.createStatement()) {
                  // This statement will fail if it violates a database constraint (e.g., balance cannot be negative)
                  stmt.executeUpdate("UPDATE Accounts SET Balance = Balance - 100 WHERE AccountID = 1 AND Balance >= 100");
                  conn.commit();
              } catch (SQLException e) {
                  conn.rollback();
                  e.printStackTrace();
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  }
  
  ```


- ***Isolation*** guarantees that concurrent transactions should produce the same result that sequentially transactions
  would have produced.

  This code snippet demonstrates setting the isolation level of a transaction to prevent interference from other
  transactions:

  ```java
  import java.sql.*;

  public class IsolationExample {
  public static void main(String[] args) {
  String url = "jdbc:yourdb://hostname:port/dbname";
  String user = "username";
  String password = "password";
  
          try (Connection conn = DriverManager.getConnection(url, user, password)) {
              conn.setAutoCommit(false);
              conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // Set the highest level of isolation
  
              try (Statement stmt = conn.createStatement()) {
                  ResultSet rs = stmt.executeQuery("SELECT Balance FROM Accounts WHERE AccountID = 1");
                  while (rs.next()) {
                      int balance = rs.getInt("Balance");
                      // Perform some operations
                  }
                  conn.commit();
              } catch (SQLException e) {
                  conn.rollback();
                  e.printStackTrace();
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  
  }
  
  ```

- Lastly, ***durability*** means that committed transactions remain committed irrespective of any type of system
  failure.
  Durability is usually handled by the database system itself; once a transaction is committed, the changes are written
  to non-volatile storage. This is intrinsic to the database and not something you control from JDBC, but ensuring you
  commit your transactions as shown above is key.

In a distributed transaction scenario, as the transaction spans several services, ensuring ACID always remains key.
