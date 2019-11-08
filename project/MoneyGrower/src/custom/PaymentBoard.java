package custom;

import java.sql.SQLException;
import BUS.GiaoDichThangBUS;
import DTO.GiaoDichThangDTO;
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

	public PaymentBoard(int userID, int month) throws SQLException {
		GiaoDichThangBUS.getDSGiaoDichThang(userID, month).stream().forEach(gdt -> {
			HBox hbox = this.createTitleBox(gdt);
			HBox contentBox1 = this.createContentBox("🎁", "Quà tặng", (long) -2000000);
			HBox contentBox2 = this.createContentBox("🎁", "Tiền gia đình", (long) 2000000);
			this.getChildren().addAll(hbox, contentBox1, contentBox2);
		});
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
		valueLabel.setPadding(new Insets(0, 10, 0, 0));
		hbox.getChildren().addAll(dayLabel, monthYearLabel, valueLabel);
		return hbox;
	}

	private HBox createContentBox(String icon, String content, Long value) {
		HBox hbox = new HBox();
		hbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

		Label iconLabel = new Label(icon);
		iconLabel.setAlignment(Pos.CENTER);
		iconLabel.setFont(Font.font("System", FontWeight.BOLD, 30));
		iconLabel.setPrefSize(65, 65);

		Label contentLabel = new Label(content);
		contentLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
		contentLabel.setPrefSize(175, 65);

		Label valueLabel = new Label(Long.toString(Math.abs(value)));
		valueLabel.setAlignment(Pos.CENTER_RIGHT);
		valueLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		valueLabel.setPrefSize(200, 65);

		if (value < 0) {
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
