package helper;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import BUS.NguoiDungBUS;
import BUS.TKGiaoDichBUS;
import DTO.TKGiaoDichDTO;

public class PDFHelper {

	public static void createThongKe(int nam, int thang, int maNguoiDung) throws IOException, SQLException {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();

		doc.addPage(page);
		float yCordinate = page.getCropBox().getUpperRightY() - 70;
		float startX = page.getCropBox().getLowerLeftX() + 70;
		float endX = page.getCropBox().getUpperRightX() - 30;
		float leading = 20;

		PDFont fontBold = PDType0Font.load(doc, new File("./fonts/timesbd.ttf"));
		PDFont fontBoldItalic = PDType0Font.load(doc, new File("./fonts/timesbi.ttf"));
		PDFont fontItalic = PDType0Font.load(doc, new File("./fonts/timesi.ttf"));
		PDFont font = PDType0Font.load(doc, new File("./fonts/times.ttf"));
		
		String tenNguoiDung = NguoiDungBUS.getNguoiDungById(maNguoiDung).getTenNguoiDung();
		List<TKGiaoDichDTO> listThongKeThu = TKGiaoDichBUS.getDSTongThu(maNguoiDung, thang, nam);
		List<TKGiaoDichDTO> listThongKeChi = TKGiaoDichBUS.getDSTongChi(maNguoiDung, thang, nam);

		PDPageContentStream contents = new PDPageContentStream(doc, page);
		contents.beginText();
		contents.newLineAtOffset(
				(page.getCropBox().getUpperRightX() / 2) - ((fontBold.getStringWidth("MoneyGrower") / 1000 * 36) / 2),
				yCordinate);
		contents.setFont(fontBold, 36);
		contents.showText("MoneyGrower");
		contents.endText();
		yCordinate -= 40;

		contents.beginText();
		contents.newLineAtOffset((page.getCropBox().getUpperRightX() / 2)
				- ((fontBold.getStringWidth(String.format("THỐNG KÊ GIAO DỊCH THÁNG %d/%d", thang, nam)) / 1000 * 20)
						/ 2),
				yCordinate);
		contents.setFont(fontBold, 20);
		contents.showText(String.format("THỐNG KÊ GIAO DỊCH THÁNG %02d/%d", thang, nam));
		contents.endText();
		yCordinate -= 36;

		contents.beginText();
		contents.newLineAtOffset((page.getCropBox().getUpperRightX() / 2)
				- ((fontBold.getStringWidth("Người dùng: " + tenNguoiDung) / 1000 * 20) / 2), yCordinate);
		contents.setFont(fontItalic, 18);
		contents.showText("Người dùng: " + tenNguoiDung);
		contents.endText();
		yCordinate -= 20;

		// THỐNG KÊ GIAO DỊCH THU
		contents.beginText();
		contents.setFont(fontBoldItalic, 20);
		contents.newLineAtOffset(startX, yCordinate - leading);
		yCordinate -= leading;
		contents.showText("A. THỐNG KÊ GIAO DỊCH THU");
		contents.endText();
		yCordinate -= 6;
		contents.moveTo(startX, yCordinate);
		contents.lineTo(endX, yCordinate);

		// Tổng tiền thu
		if (listThongKeThu.isEmpty()) {
			contents.beginText();
			contents.setFont(fontItalic, 13);
			contents.newLineAtOffset(startX, yCordinate - leading);
			yCordinate -= leading;
			contents.showText("Không có khoản thu");
			contents.endText();
			yCordinate -= 6;
		} else {
			Long tongThu = listThongKeThu.stream().mapToLong(TKGiaoDichDTO::getTongGiaTri).sum();
			String tongThuString = MoneyFormatHelper.format(tongThu);
			contents.beginText();
			contents.setFont(fontItalic, 13);
			contents.newLineAtOffset(startX, yCordinate - leading);
			yCordinate -= leading;
			contents.showText("Tổng khoản thu: " + tongThuString + "đ");
			contents.endText();
			yCordinate -= 6;

			// Header
			contents.beginText();
			contents.setFont(fontBold, 13);
			contents.newLineAtOffset(startX, yCordinate - leading);
			contents.showText("Loại giao dịch");
			contents.newLineAtOffset(endX - 380, 0);
			contents.showText("Giá trị (VND)");
			contents.newLineAtOffset(endX - 360, 0);
			contents.showText("Tỉ lệ (%)");
			contents.endText();
			yCordinate -= leading;

			// Add chi tiết
			for (TKGiaoDichDTO tk : listThongKeThu) {
				contents.beginText();
				contents.setFont(font, 13);
				contents.newLineAtOffset(startX, yCordinate - leading);
				yCordinate -= leading;
				contents.showText(tk.getTenLoaiGiaoDich());
				contents.newLineAtOffset(endX - 380, 0);
				contents.showText(MoneyFormatHelper.format(tk.getTongGiaTri()));
				contents.newLineAtOffset(endX - 360, 0);
				contents.showText(String.format("%.2f", tk.getTongGiaTri().doubleValue() * 100 / tongThu));
				contents.endText();
			}
		}

		// THỐNG KÊ GIAO DỊCH CHI
		yCordinate -= 30;
		contents.beginText();
		contents.setFont(fontBoldItalic, 20);
		contents.newLineAtOffset(startX, yCordinate - leading);
		yCordinate -= leading;
		contents.showText("B. THỐNG KÊ GIAO DỊCH CHI");
		contents.endText();
		yCordinate -= 6;
		contents.moveTo(startX, yCordinate);
		contents.lineTo(endX, yCordinate);
		contents.stroke();
		
		// Tổng tiền chi
		if (listThongKeChi.isEmpty()) {
			contents.beginText();
			contents.setFont(fontItalic, 13);
			contents.newLineAtOffset(startX, yCordinate - leading);
			yCordinate -= leading;
			contents.showText("Không có khoản chi");
			contents.endText();
			yCordinate -= 6;
		} else {
			Long tongThu = listThongKeChi.stream().mapToLong(TKGiaoDichDTO::getTongGiaTri).sum();
			String tongThuString = MoneyFormatHelper.format(tongThu);
			contents.beginText();
			contents.setFont(fontItalic, 13);
			contents.newLineAtOffset(startX, yCordinate - leading);
			yCordinate -= leading;
			contents.showText("Tổng khoản chi: " + tongThuString + "đ");
			contents.endText();
			yCordinate -= 6;
			contents.moveTo(startX, yCordinate);
			contents.lineTo(endX, yCordinate);

			// Header
			contents.beginText();
			contents.setFont(fontBold, 13);
			contents.newLineAtOffset(startX, yCordinate - leading);
			contents.showText("Loại giao dịch");
			contents.newLineAtOffset(endX - 380, 0);
			contents.showText("Giá trị (VND)");
			contents.newLineAtOffset(endX - 360, 0);
			contents.showText("Tỉ lệ (%)");
			contents.endText();
			yCordinate -= leading;

			// Add chi tiết
			for (TKGiaoDichDTO tk : listThongKeChi) {
				contents.beginText();
				contents.setFont(font, 13);
				contents.newLineAtOffset(startX, yCordinate - leading);
				yCordinate -= leading;
				contents.showText(tk.getTenLoaiGiaoDich());
				contents.newLineAtOffset(endX - 380, 0);
				contents.showText(MoneyFormatHelper.format(tk.getTongGiaTri()));
				contents.newLineAtOffset(endX - 360, 0);
				contents.showText(String.format("%.2f", tk.getTongGiaTri().doubleValue() * 100 / tongThu));
				contents.endText();
			}
		}

		contents.close();
		String filePath = String.format("./bc/BC_%02d_%d_%d.pdf", thang, nam, maNguoiDung);
		doc.save(filePath);
		doc.close();
		Desktop.getDesktop().open(new File(filePath));
	}
}