import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = DB.getConnection();

            pstmt = conn.prepareStatement(
                    "INSERT INTO seller" +
                            "(Name, Email, BirthDate, BaseSalary, DepartmentId)" +
                            "VALUES" +
                            "(?, ?, ?, ?, ?)"
            );

            pstmt.setString(1, "Amanda Spader");
            pstmt.setString(2, "amandickz@gmail.com");
            pstmt.setDate(3, new java.sql.Date(sdf.parse("18/08/1995").getTime()));
            pstmt.setDouble(4, 3000.00);
            pstmt.setInt(5, 4);

            int rollsAffected = pstmt.executeUpdate();

            System.out.println("Done! Rolls Affected: " + rollsAffected);
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        } finally {
            DB.closeStatement(pstmt);
            DB.closeConnection();
        }

    }
}