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

  Isolation helps to prevent various types of concurrency problems, such as dirty reads, non-repeatable reads, and
  phantom reads.
  Here’s a breakdown of what each of these terms means:

    - **Dirty Read**: This occurs when a transaction reads data that has been modified by another transaction but not
      yet
      committed. If the other transaction is rolled back, the first transaction will have read data that was never
      officially in the database.
    - **Non-repeatable Read**: This happens when a transaction reads the same row twice and finds different data each
      time because another transaction updated the row between the two reads.
    - **Phantom Read**: This occurs when a transaction re-executes a query returning a set of rows that satisfy a
      search condition and finds that the set of rows satisfying the condition has changed because of another
      recently-committed transaction.

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

  ### Levels of Isolation
  SQL databases define several isolation levels which control the degree to which one transaction must be isolated from
  resource or data modifications made by other transactions. Isolation levels are defined by the SQL standard and are
  implemented using locks or other mechanisms:

    - Read Uncommitted: This is the lowest level of isolation. Transactions may read changes made by other transactions
      even
      before they are committed. This level allows dirty reads.

    - Read Committed: This prevents dirty reads. A transaction can only see changes committed by other transactions.
      However,
      it can still experience non-repeatable reads.

    - Repeatable Read: This level prevents non-repeatable reads by ensuring that if a transaction reads a row, other
      transactions cannot modify or delete that row until the first transaction completes.

    - Serializable: This is the highest level of isolation. Transactions are fully isolated from one another,
      essentially
      queuing them to operate one after the other. This prevents dirty reads, non-repeatable reads, and phantom reads.

- Lastly, ***durability*** means that committed transactions remain committed irrespective of any type of system
  failure.
  Durability is usually handled by the database system itself; once a transaction is committed, the changes are written
  to non-volatile storage. This is intrinsic to the database and not something you control from JDBC, but ensuring you
  commit your transactions as shown above is key.

In a distributed transaction scenario, as the transaction spans several services, ensuring ACID always remains key.
