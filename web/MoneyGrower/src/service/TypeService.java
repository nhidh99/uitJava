package service;

import java.sql.SQLException;
import helper.DBHelper;
import model.TypeModel;

public class TypeService {

	public static TypeModel getTypeByID(int typeID) throws SQLException, ClassNotFoundException {
		var conn = DBHelper.getConnection();
		try {
			var query = "SELECT * FROM LoaiGiaoDich WHERE MaLoaiGiaoDich = ?";
			var statement = conn.prepareStatement(query);
			statement.setInt(1, typeID);

			var rs = statement.executeQuery();
			rs.next();
			var name = rs.getString("TenLoaiGiaoDich");
			var icon = rs.getString("BieuTuong");
			var isIncome = rs.getBoolean("GiaoDichThu");
			var output = new TypeModel(typeID, name, icon, isIncome);
			return output;
		} finally {
			conn.close();
		}
	}
}
