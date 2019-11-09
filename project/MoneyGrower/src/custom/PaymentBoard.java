package custom;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import BUS.GiaoDichBUS;
import BUS.GiaoDichThangBUS;
import BUS.LoaiGiaoDichBUS;
import DTO.GiaoDichDTO;
import DTO.GiaoDichThangDTO;
import DTO.LoaiGiaoDichDTO;
import helper.MoneyFormatHelper;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PaymentBoard extends VBox {

	public PaymentBoard(int userID, int month, int year) throws SQLException {
		for (GiaoDichThangDTO gdt : GiaoDichThangBUS.getDSGiaoDichThang(userID, month, year)) {
			HBox title = this.createTitleBox(gdt);
			List<HBox> contents = new ArrayList<>();
			for (GiaoDichDTO gd : GiaoDichBUS.getDSGiaoDichOfMonth(userID, gdt.getNgayGiaoDich())) {
				HBox content = this.createContentBox(gd);				
				contents.add(content);
			}
			this.getChildren().add(title);
			this.getChildren().addAll(contents);
		}
		this.setPrefWidth(465);
	}

	public void setContentBoxMouseClick(EventHandler<MouseEvent> e) {
		this.getChildren().stream().skip(1).forEach(hbox -> {
			hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
		});
	}

	private String getNameOfMonth(int month) {
		String[] names = { "Một", "Hai", "Ba", "Bốn", "Năm", "Sáu", 
				"Bảy", "Tám", "Chín", "Mười", "Mười Một", "Mười Hai" };
		return "Tháng " + names[month - 1];
	}

	private HBox createTitleBox(GiaoDichThangDTO gdt) {
		HBox hbox = new HBox();
		hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
		Label dayLabel = new Label(String.format("%02d", gdt.getNgayGiaoDich().getDayOfMonth()));
		dayLabel.setAlignment(Pos.CENTER);
		dayLabel.setFont(Font.font("System", FontWeight.BOLD, 30));
		dayLabel.setPrefSize(65, 65);

		Label monthYearLabel = new Label(String.format("%s %d", getNameOfMonth(gdt.getNgayGiaoDich().getMonthValue()),
				gdt.getNgayGiaoDich().getYear()));
		monthYearLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
		monthYearLabel.setPrefSize(175, 65);

		Label valueLabel = new Label(MoneyFormatHelper.format(gdt.getTriGia(), true));
		valueLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		valueLabel.setTextFill(valueLabel.getText().charAt(0) == '-' ? Color.RED : Color.FORESTGREEN);
		valueLabel.setAlignment(Pos.CENTER_RIGHT);
		valueLabel.setPrefSize(200, 65);
		hbox.getChildren().addAll(dayLabel, monthYearLabel, valueLabel);
		return hbox;
	}

	private HBox createContentBox(GiaoDichDTO giaoDich) throws SQLException {
		HBox hbox = new HBox();
		hbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		hbox.setUserData(giaoDich.getMaGiaoDich());
		
		LoaiGiaoDichDTO loaiGiaoDich = LoaiGiaoDichBUS.getLoaiGiaoDichById(giaoDich.getMaLoaiGiaoDich());
		Label iconLabel = new Label(loaiGiaoDich.getBieuTuong());
		iconLabel.setAlignment(Pos.CENTER);
		iconLabel.setFont(Font.font("System", FontWeight.BOLD, 30));
		iconLabel.setPrefSize(65, 65);

		Label contentLabel = new Label(loaiGiaoDich.getTenLoaiGiaoDich());
		contentLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
		contentLabel.setPrefSize(175, 65);

		Label valueLabel = new Label(MoneyFormatHelper.format(giaoDich.getGiaTri(), true));
		valueLabel.setAlignment(Pos.CENTER_RIGHT);
		valueLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		valueLabel.setPrefSize(200, 65);

		if (giaoDich.getGiaTri() < 0) {
			iconLabel.setTextFill(Color.RED);
			valueLabel.setTextFill(Color.RED);
		} else {
			iconLabel.setTextFill(Color.FORESTGREEN);
			valueLabel.setTextFill(Color.FORESTGREEN);
		}

		hbox.setOnMouseEntered(e -> {
			hbox.setBackground(
					new Background(new BackgroundFill(Color.rgb(232, 232, 232), CornerRadii.EMPTY, Insets.EMPTY)));
		});
		hbox.setOnMouseExited(e -> {
			hbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		hbox.getChildren().addAll(iconLabel, contentLabel, valueLabel);
		return hbox;
	}
}
