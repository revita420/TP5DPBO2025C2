import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu extends JFrame {
    // Database connection
    private Database database;

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JLabel fakultasLabel;
    private JComboBox fakultasComboBox;

    private int selectedRowId = -1;

    public static void main(String[] args) {
        // buat object window
        SwingUtilities.invokeLater(() -> {
            Menu window = new Menu();
            window.setSize(600, 500);
            window.setLocationRelativeTo(null);
            window.setContentPane(window.mainPanel);
            window.getContentPane().setBackground(new Color(255, 209, 220));
            window.setVisible(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }

    // constructor
    public Menu() {
        listMahasiswa = new ArrayList<>();
        database = new Database();

        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // styling
        Color pinkPastel = new Color(255, 209, 220);
        Color darkPinkPastel = new Color(255, 182, 193);

        mainPanel.setBackground(pinkPastel);
        addUpdateButton.setBackground(darkPinkPastel);
        deleteButton.setBackground(darkPinkPastel);
        cancelButton.setBackground(darkPinkPastel);

        String[] jenisKelaminData = {"", "Laki-Laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        String[] fakultasData = {"", "FPMIPA", "FPIPS", "FPBS", "FPEB", "FPTK", "FPOK", "FPSD"};
        fakultasComboBox.setModel(new DefaultComboBoxModel(fakultasData));

        deleteButton.setVisible(false);

        // Populate table
        mahasiswaTable.setModel(setTable());

        // Add Update/Insert Listener
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nimField.getText().isEmpty() || namaField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Harap isi semua field!");
                } else {
                    if (selectedIndex == -1) {
                        insertData();
                    } else {
                        updateData();
                    }
                }
            }
        });

        // Cancel Button Listener
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // Delete Button Listener
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex != -1) {
                    int konfirmasi = JOptionPane.showConfirmDialog(null,
                            "Apakah Anda yakin ingin menghapus data?",
                            "Konfirmasi",
                            JOptionPane.YES_NO_OPTION);

                    if (konfirmasi == JOptionPane.YES_OPTION) {
                        deleteData();
                    }
                }
            }
        });

        // Table Row Selection Listener
        mahasiswaTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && mahasiswaTable.getSelectedRow() != -1) {
                selectedIndex = mahasiswaTable.getSelectedRow();

                nimField.setText(mahasiswaTable.getValueAt(selectedIndex, 1).toString());
                namaField.setText(mahasiswaTable.getValueAt(selectedIndex, 2).toString());
                jenisKelaminComboBox.setSelectedItem(mahasiswaTable.getValueAt(selectedIndex, 3).toString());
                fakultasComboBox.setSelectedItem(mahasiswaTable.getValueAt(selectedIndex, 4).toString());

                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Fakultas"};
        DefaultTableModel temp = new DefaultTableModel(null, column);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");
            int autoIncrementIndex = 1;
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = autoIncrementIndex++;
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("fakultas");

                temp.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return temp;
    }

    public void insertData() {
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String fakultas = fakultasComboBox.getSelectedItem().toString();

        boolean hasError = false;
        StringBuilder errorMessage = new StringBuilder("Harap lengkapi input berikut:\n");

        if (nim.isEmpty()) {
            errorMessage.append("- NIM\n");
            hasError = true;
        }
        if (nama.isEmpty()) {
            errorMessage.append("- Nama\n");
            hasError = true;
        }
        if (jenisKelamin.isEmpty()) {
            errorMessage.append("- Jenis Kelamin\n");
            hasError = true;
        }
        if (fakultas.isEmpty()) {
            errorMessage.append("- Fakultas\n");
            hasError = true;
        }

        // Check duplikat IM
        try {
            ResultSet checkNim = database.selectQuery("SELECT * FROM mahasiswa WHERE nim = '" + nim + "'");
            if (checkNim.next()) {
                JOptionPane.showMessageDialog(null,
                        "Error: NIM " + nim + " sudah terdaftar!",
                        "Duplicate NIM",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal memeriksa NIM: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (hasError) {
            JOptionPane.showMessageDialog(null,
                    errorMessage.toString(),
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO mahasiswa VALUES(null, '" + nim + "', '" + nama + "', '" + jenisKelamin + "', '" + fakultas + "');";
        database.insertUpdateDeleteQuery(sql);
        mahasiswaTable.setModel(setTable());
        clearForm();
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
    }

    public void updateData() {
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String fakultas = fakultasComboBox.getSelectedItem().toString();

        boolean hasError = false;
        StringBuilder errorMessage = new StringBuilder("Harap lengkapi input berikut:\n");

        if (nim.isEmpty()) {
            errorMessage.append("- NIM\n");
            hasError = true;
        }
        if (nama.isEmpty()) {
            errorMessage.append("- Nama\n");
            hasError = true;
        }
        if (jenisKelamin.isEmpty()) {
            errorMessage.append("- Jenis Kelamin\n");
            hasError = true;
        }
        if (fakultas.isEmpty()) {
            errorMessage.append("- Fakultas\n");
            hasError = true;
        }

        if (hasError) {
            JOptionPane.showMessageDialog(null,
                    errorMessage.toString(),
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check duplikat NIM
        try {
            String sqlGetId = "SELECT id FROM mahasiswa LIMIT " + (selectedIndex + 1);
            int currentId = -1;
            ResultSet rsId = database.selectQuery(sqlGetId);
            for (int i = 0; i <= selectedIndex; i++) {
                if (rsId.next()) {
                    currentId = rsId.getInt("id");
                }
            }

            ResultSet checkNim = database.selectQuery(
                    "SELECT * FROM mahasiswa WHERE nim = '" + nim + "' AND id != " + currentId
            );
            if (checkNim.next()) {
                JOptionPane.showMessageDialog(null,
                        "Error: NIM " + nim + " sudah terdaftar!",
                        "Duplicate NIM",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal memeriksa NIM: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sqlGetId = "SELECT id FROM mahasiswa LIMIT " + (selectedIndex + 1);
        int id = -1;
        try {
            ResultSet rs = database.selectQuery(sqlGetId);
            for (int i = 0; i <= selectedIndex; i++) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil ID: " + e.getMessage());
            return;
        }

        if (id != -1) {
            String sql = "UPDATE mahasiswa SET nim='" + nim + "', nama='" + nama +
                    "', jenis_kelamin='" + jenisKelamin + "', fakultas='" + fakultas +
                    "' WHERE id=" + id;
            database.insertUpdateDeleteQuery(sql);
            mahasiswaTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
        }
    }

    public void deleteData() {
        String sqlGetId = "SELECT id FROM mahasiswa LIMIT " + (selectedIndex + 1);
        int id = -1;
        try {
            ResultSet rs = database.selectQuery(sqlGetId);
            for (int i = 0; i <= selectedIndex; i++) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil ID: " + e.getMessage());
            return;
        }

        if (id != -1) {
            String sql = "DELETE FROM mahasiswa WHERE id=" + id;
            database.insertUpdateDeleteQuery(sql);
            mahasiswaTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        }
    }

    public void clearForm() {
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedIndex(0);
        fakultasComboBox.setSelectedIndex(0);
        selectedIndex = -1;
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
    }
}