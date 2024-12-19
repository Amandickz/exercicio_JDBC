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
                            "(?, ?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS
            );

            pstmt.setString(1, "Diego Spader");
            pstmt.setString(2, "spader.diego@gmail.com");
            pstmt.setDate(3, new java.sql.Date(sdf.parse("19/09/1988").getTime()));
            pstmt.setDouble(4, 3000.00);
            pstmt.setInt(5, 4);

            int rollsAffected = pstmt.executeUpdate();

            if(rollsAffected > 0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! ID = " + id);
                }
            } else {
                System.out.println("No rown affected!");
            }

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