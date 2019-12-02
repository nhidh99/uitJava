package custom;

import java.sql.SQLException;

import BUS.LoaiGiaoDichBUS;
import DTO.LoaiGiaoDichDTO;
import DTO.NganSachDTO;
import helper.DateFormatHelper;
import helper.MoneyFormatHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BudgetBox extends HBox {
	public BudgetBox(NganSachDTO nganSach) throws SQLException {

		LoaiGiaoDichDTO loaiGiaoDich = LoaiGiaoDichBUS.getLoaiGiaoDichById(nganSach.getMaLoaiGiaoDich());

		Label iconLabel = new Label(loaiGiaoDich.getBieuTuong());
		iconLabel.setPrefSize(70, 70);
		iconLabel.setFont(Font.font("System", FontWeight.BOLD, 30));
		iconLabel.setAlignment(Pos.CENTER);

		VBox contentBox = new VBox(3);
		Label titleLabel = new Label(loaiGiaoDich.getTenLoaiGiaoDich());
		titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));

		Label expireLabel = new Label(String.format("%s - %s", DateFormatHelper.fromLocalDate(nganSach.getNgayBatDau()),
				DateFormatHelper.fromLocalDate(nganSach.getNgayKetThuc())));
		expireLabel.setFont(Font.font(14));

		ProgressBar progressBar = new ProgressBar(nganSach.getSuDung().doubleValue() / nganSach.getGiaTri());
		progressBar.setPrefSize(190, 10);
		progressBar.setDisable(true);

		contentBox.setPrefWidth(200);
		contentBox.getChildren().addAll(titleLabel, expireLabel, progressBar);
		HBox.setMargin(contentBox, new Insets(0, 0, 0, 10));

		VBox valueBox = new VBox();
		Label valueLabel = new Label(MoneyFormatHelper.format(nganSach.getGiaTri()));
		valueLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
		Label detailLabel = new Label();
		detailLabel.setFont(Font.font(14));

		Long tienConLai = nganSach.getGiaTri() - nganSach.getSuDung();
		if (tienConLai >= 0) {
			iconLabel.setTextFill(Color.FORESTGREEN);
			titleLabel.setTextFill(Color.FORESTGREEN);
			expireLabel.setTextFill(Color.FORESTGREEN);
			valueLabel.setTextFill(Color.FORESTGREEN);
			detailLabel.setTextFill(Color.FORESTGREEN);
			detailLabel.setText("Còn lại " + MoneyFormatHelper.format(tienConLai));
		} else {
			iconLabel.setTextFill(Color.RED);
			titleLabel.setTextFill(Color.RED);
			expireLabel.setTextFill(Color.RED);
			valueLabel.setTextFill(Color.RED);
			detailLabel.setTextFill(Color.RED);
			detailLabel.setText("Bội chi " + MoneyFormatHelper.format(-tienConLai));
		}

		valueBox.getChildren().addAll(valueLabel, detailLabel);
		valueBox.setAlignment(Pos.CENTER_RIGHT);
		valueBox.setPrefWidth(200);

		this.setPrefWidth(450);
		this.setPadding(new Insets(10));
		this.getChildren().addAll(iconLabel, contentBox, valueBox);

		this.setOnMouseEntered(e -> {
			this.setBackground(
					new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		});

		this.setOnMouseExited(e -> {
			this.setBackground(
					new Background(new BackgroundFill(Color.rgb(244, 244, 244), CornerRadii.EMPTY, Insets.EMPTY)));
		});
	}
}
