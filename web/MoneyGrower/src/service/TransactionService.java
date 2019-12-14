package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helper.DBHelper;
import model.TransactionModel;

public class TransactionService {

	public Map<LocalDate, List<TransactionModel>> getTransactionsOfUser(int userID) throws ClassNotFoundException, SQLException {
		var conn = DBHelper.getConnection();
		try {
			var query = "SELECT * FROM GiaoDich WHERE MaNguoiDung = ? ORDER BY NgayGiaoDich DESC";
			var statement = conn.prepareStatement(query);
			statement.setInt(1, userID);

			var rs = statement.executeQuery();
			var output = new HashMap<LocalDate, List<TransactionModel>>();
			while (rs.next()) {
				var transactionID = rs.getInt("MaGiaoDich");
				var date = rs.getTimestamp("NgayGiaoDich").toLocalDateTime().toLocalDate();
				var price = rs.getLong("GiaTri");
				var note = rs.getString("GhiChu");				
				var typeID = rs.getInt("MaLoaiGiaoDich");
				var type = TypeService.getTypeByID(typeID);	
				var transaction = new TransactionModel(transactionID, userID, type, date, price, note);
				
				if (output.containsKey(date)) {
					output.get(date).add(transaction);
				}
				else {
					var transactions = new ArrayList<TransactionModel>();
					transactions.add(transaction);
					output.put(date, transactions);
				}
			}
			return output;
		} finally {
			conn.close();
		}
	}

}
