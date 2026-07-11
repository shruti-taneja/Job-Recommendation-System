import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserDAO {

    public boolean registerUser(User user) {

       String sql =
"INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)";
        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            
            pstmt.setString(4, user.getRole());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println(" Registration Successful!");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

   public String loginUser(String email,String password){

    String sql =
    "SELECT role FROM users WHERE email=? AND password=?";


    try{

        Connection con = DBConnection.getConnection();

        PreparedStatement pst =
        con.prepareStatement(sql);


        pst.setString(1,email);
        pst.setString(2,password);


        ResultSet rs=pst.executeQuery();


        if(rs.next()){

            return rs.getString("role");

        }

    }
    catch(SQLException e){

        System.out.println(e.getMessage());

    }


    return null;
}
    // UPDATE USER EMAIL
    public boolean updateUserEmail(int id, String email) {

        String sql = "UPDATE users SET email=? WHERE id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, email);
            pst.setInt(2, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Email Updated Successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
        }

        return false;
    }

    // DELETE USER
    public boolean deleteUser(int id) {

        String sql = "DELETE FROM users WHERE id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("User Deleted Successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Delete Error: " + e.getMessage());
        }

        return false;
    }
public void getAllUsers(){


    String sql =
    "SELECT * FROM users";


    try {


        Connection con =
        DBConnection.getConnection();



        PreparedStatement pst =
        con.prepareStatement(sql);



        ResultSet rs =
        pst.executeQuery();



        System.out.println("\n===== ALL USERS =====");



        while(rs.next()) {


            System.out.println(
            "ID: " + rs.getInt("id"));


            System.out.println(
            "Name: " + rs.getString("name"));


            System.out.println(
            "Email: " + rs.getString("email"));


            System.out.println(
            "Role: " + rs.getString("role"));


            System.out.println("----------------");

        }


    }
    catch(SQLException e){


        System.out.println(
        "Error: " + e.getMessage());

    }

}
}