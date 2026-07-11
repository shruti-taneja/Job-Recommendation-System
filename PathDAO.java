import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PathDAO {

  
    // USER: Get Recommendation
    public void getRecommendation(String skill) {


        String sql = "SELECT * FROM path WHERE skill=?";


        try {


            Connection con = DBConnection.getConnection();


            PreparedStatement pst =
            con.prepareStatement(sql);


            pst.setString(1, skill);


            ResultSet rs =
            pst.executeQuery();



            if(rs.next()) {


                System.out.println("\n===== JOB RECOMMENDATION =====");

                System.out.println(
                "Skill : " + rs.getString("skill"));

                System.out.println(
                "Job Role : " + rs.getString("job_role"));

                System.out.println(
                "Next Skills : " + rs.getString("next_skills"));

            }
            else {


                System.out.println(
                "No Recommendation Found");

            }


        }
        catch(SQLException e) {

            System.out.println(
            "Error: " + e.getMessage());

        }

    }






    // ADMIN: Add Recommendation

    public boolean addRecommendation(
            String skill,
            String jobRole,
            String nextSkills) {



        String sql =
        "INSERT INTO path(skill,job_role,next_skills) VALUES(?,?,?)";



        try {


            Connection con =
            DBConnection.getConnection();



            PreparedStatement pst =
            con.prepareStatement(sql);



            pst.setString(1, skill);

            pst.setString(2, jobRole);

            pst.setString(3, nextSkills);



            int rows =
            pst.executeUpdate();



            if(rows > 0) {


                return true;

            }



        }
        catch(SQLException e) {


            System.out.println(
            "Add Error: " + e.getMessage());

        }



        return false;

    }







    // ADMIN: Delete Recommendation

    public boolean deleteRecommendation(int id) {



        String sql =
        "DELETE FROM path WHERE id=?";



        try {


            Connection con =
            DBConnection.getConnection();



            PreparedStatement pst =
            con.prepareStatement(sql);



            pst.setInt(1,id);



            int rows =
            pst.executeUpdate();



            if(rows > 0) {


                return true;

            }



        }
        catch(SQLException e) {


            System.out.println(
            "Delete Error: " + e.getMessage());

        }



        return false;


    }
}