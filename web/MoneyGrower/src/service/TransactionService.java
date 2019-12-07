package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helper.DBHelper;
import model.TransactionModel;

public class TransactionService {

	public List<TransactionModel> getTransactionsOfUser(int userID) throws ClassNotFoundException, SQLException {
		var conn = DBHelper.getConnection();
		try {
			var query = "SELECT * FROM GiaoDich WHERE MaNguoiDung = ?";
			var statement = conn.prepareStatement(query);
			statement.setInt(1, userID);

			var rs = statement.executeQuery();
			var output = new ArrayList<TransactionModel>();
			while (rs.next()) {
				var transactionID = rs.getInt("MaGiaoDich");
				var nameID = rs.getInt("MaLoaiGiaoDich");
				var date = rs.getTimestamp("NgayGiaoDich").toLocalDateTime().toLocalDate();
				var price = rs.getLong("GiaTri");
				var note = rs.getString("GhiChu");
				var transaction = new TransactionModel(transactionID, nameID, userID, date, price, note);
				output.add(transaction);
			}
			return output;
		} finally {
			conn.close();
		}
	}

}
