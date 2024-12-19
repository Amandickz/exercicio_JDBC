import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = DB.getConnection();

            pstmt = conn.prepareStatement("UPDATE seller" +
                    " SET BaseSalary = BaseSalary + ?" +
                    " WHERE" +
                    " (DepartmentID = ?)");

            pstmt.setDouble(1, 200.0);
            pstmt.setInt(2, 2);

            int rowsAffected = pstmt.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.closeStatement(pstmt);
            DB.closeConnection();
        }

    }
}