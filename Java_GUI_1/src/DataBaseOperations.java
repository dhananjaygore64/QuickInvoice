
import java.sql.*;			// STEP 1- import required classes and interfaces
public class DataBaseOperations {
	static public ResultSet res=null;
	public static int addCustomer() {
		String query;
		int newID=0;
		int count=0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");			// STEP 2- Load and register JDBC driver
			Connection conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:xe", "system", "admin");	// STEP 3- Establish connection
			Statement st=conn.createStatement();						// STEP 4- Create Statement
			PreparedStatement pst=null;
			/* To Fetch the values from table execute following code  */
			query="Select ID from customer order by ID ASC ";
			res=st.executeQuery(query);						// STEP 5- Execute Query
			while(res.next()){
				newID=res.getInt(1);
			}
			/*		Up to this 		*/
			newID++;
			System.out.println(newID);
			System.out.println(Frame2.custName);
			/* To Update table or to insert Values in table execute following code  */
			pst=conn.prepareStatement("Insert into customer values(?,initcap(?),initcap(?),initcap(?),initcap(?),initcap(?),initcap(?))" ,
			         ResultSet.TYPE_SCROLL_INSENSITIVE , 
			         ResultSet.CONCUR_UPDATABLE ,
			         ResultSet.HOLD_CURSORS_OVER_COMMIT);
			pst.setInt(1, newID);
			pst.setString(2, Frame2.custName);
			pst.setString(3, Frame2.address1);
			pst.setString(4, Frame2.address2);
			pst.setString(5, Frame2.stateName);
			pst.setString(6, Frame2.contact);
			pst.setString(7, Frame2.PAN);
			count=pst.executeUpdate();				// STEP 5- Execute Query
			/*		Up to this 		*/
			/* To Fetch the values from table execute following code  */
			query="Select * from customer order by ID Asc ";
			res=st.executeQuery(query);						// STEP 5- Execute Query
			while(res.next()){
				System.out.println(res.getInt(1)+"  "+res.getString(2)+"  "+res.getString(3));	// STEP 6- Process result
			}
			/*		Up to this 		*/
			conn.close();												// Close Connection
		}catch(Exception ex){
			System.out.println(ex);
		}
		return count;
	}
	public static void getCustomerList() {
		String query;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");			// STEP 2- Load and register JDBC driver
			Connection conn=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:xe", "system", "admin");	// STEP 3- Establish connection
			Statement st=conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);						// STEP 4- Create Statement
			/* To Fetch the values from table execute following code  */
			query="Select * from customer order by ID Asc ";
			res=st.executeQuery(query);						// STEP 5- Execute Query
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
}
