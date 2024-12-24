package bsu.rfe.java.group7.lab3.Los.A10;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Создание главного окна
            JFrame frame = new JFrame("Пример приложения");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Добавление меню
            JMenuBar menuBar = new JMenuBar();
            JMenu helpMenu = new JMenu("Справка");
            JMenuItem aboutMenuItem = new JMenuItem("О программе");
            aboutMenuItem.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame,
                        "Автор: Иванов Иван, группа: ИВТ-123",
                        "О программе",
                        JOptionPane.INFORMATION_MESSAGE);
            });
            helpMenu.add(aboutMenuItem);
            menuBar.add(helpMenu);
            frame.setJMenuBar(menuBar);

            // Создание таблицы
            String[] columnNames = {"X", "Значение многочлена", "Целая часть является квадратом?"};
            Object[][] data = {
                    {1.5, calculatePolynomial(1.5), isSquare(calculatePolynomial(1.5))},
                    {2.0, calculatePolynomial(2.0), isSquare(calculatePolynomial(2.0))},
                    {3.7, calculatePolynomial(3.7), isSquare(calculatePolynomial(3.7))},
                    {4.1, calculatePolynomial(4.1), isSquare(calculatePolynomial(4.1))}
            };

            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    if (columnIndex == 2) {
                        return Boolean.class; // Указываем, что 3-й столбец — это Boolean
                    }
                    return super.getColumnClass(columnIndex);
                }
            };

            JTable table = new JTable(model);

            // Настройка таблицы
            TableColumn booleanColumn = table.getColumnModel().getColumn(2);
            booleanColumn.setCellRenderer(table.getDefaultRenderer(Boolean.class));
            booleanColumn.setCellEditor(table.getDefaultEditor(Boolean.class));

            // Добавляем таблицу в окно
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Отображение окна
            frame.setVisible(true);
        });
    }

    // Вычисление значения многочлена (например, x^2 + 2x + 1 = (x+1)^2)
    private static double calculatePolynomial(double x) {
        return x * x + 2 * x + 1;
    }

    // Проверка, является ли целая часть числа квадратом целого числа
    private static boolean isSquare(double value) {
        int intValue = (int) value; // Берём целую часть
        int sqrt = (int) Math.sqrt(intValue);
        return sqrt * sqrt == intValue;
    }
}