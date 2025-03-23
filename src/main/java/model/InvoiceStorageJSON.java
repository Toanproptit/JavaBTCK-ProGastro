package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceStorageJSON {

    private static  String FILE_PATH = "invoices.json";
    private static List<Invoice> invoiceList;

    static {
        try {
            invoiceList = loadInvoices(); // Đọc file khi bắt đầu
        } catch (IOException e) {
            // Nếu không thể đọc, tạo danh sách rỗng
            invoiceList = new ArrayList<>();
        }
    }

    public static List<Invoice> loadInvoices() throws IOException {
        File file = new File(FILE_PATH);

        // Nếu file không tồn tại hoặc trống, trả về danh sách rỗng
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            // Đọc dữ liệu từ file JSON
            return new Gson().fromJson(reader, new TypeToken<List<Invoice>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Lỗi khi đọc file hóa đơn.");
        }
    }

    public static void saveInvoices() throws IOException {
        // Ghi dữ liệu vào file
        File file = new File(FILE_PATH);
        try (FileWriter writer = new FileWriter(file)) {
            // Sử dụng Gson để chuyển đổi danh sách thành JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(invoiceList, writer);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Lỗi khi ghi dữ liệu vào file.");
        }
    }

    public static void addInvoice(Invoice invoice) {
        // Thêm hóa đơn vào danh sách và lưu lại
        invoiceList.add(invoice);
        try {
            saveInvoices(); // Ghi lại toàn bộ danh sách vào file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
