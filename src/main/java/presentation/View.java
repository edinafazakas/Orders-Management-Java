package presentation;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import connection.ConnectionFactory;
import dao.ClientDAO;
import model.Client;
import model.Order;
import model.Product;
import net.proteanit.sql.DbUtils;
/**
 * clasa aceasta generează interfata grafica
 */
import static start.Start.LOGGER;

public class View implements ActionListener {

    JFrame f;
    JButton b1, b2, b3;
    JLabel l;

    public View() throws Exception {
        f = new JFrame("CLIENT ORDER MANAGING");
        f.setLayout(new FlowLayout(FlowLayout.CENTER, 2000, 200));
        l = new JLabel("CLIENT ORDER MANAGER");
        b1 = new JButton("Client operations");
        b2 = new JButton("Product operations");
        b3 = new JButton("Orders managing");

        l.setBounds(280, 20, 300, 50);
        b1.setBounds(300, 150, 200, 50);
        b2.setBounds(300, 250, 200, 50);
        b3.setBounds(300, 350, 200, 50);

        l.setFont(new Font("cooper", Font.BOLD, 20));

        f.add(l);
        f.add(b1);
        f.add(b2);
        f.add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);


        f.setLayout(null);
        f.setVisible(true);
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                new ClientView();
                f.setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        if (e.getSource() == b2) {
            try {
                new ProductView();
                f.setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == b3) {
            try {
                new OrderView();
                f.setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}


class ClientView implements ActionListener {

        JFrame f;
        JLabel l, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
        JTable table;
        JButton b1, b2, b3, b4, b5, b6;
        JTextField t1, t2, t3, t4, t5;


        public void refreshTable() {
            ClientDAO c = new ClientDAO();
            String s = "select * from client";
            try {
                Connection connection = ConnectionFactory.getConnection();
                Statement statement = connection.prepareStatement(s);
                ResultSet resultSet = statement.executeQuery(s);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
                //c.createObjects(resultSet).get(0);
            } catch (SQLException e1) {
                LOGGER.log(Level.WARNING, " show clients " + e1.getMessage());
            }
        }


        public ClientView() throws Exception {
            Client c;
            int nrOfCol = 5;
            f = new JFrame("CLIENT OPERATIONS");
            f.setLayout(new FlowLayout(FlowLayout.CENTER, 2000, /*20*/200));
            l = new JLabel("CLIENT OPERATIONS");
            table = new JTable(20, nrOfCol);
            l1 = new JLabel("Clients:");
            l2 = new JLabel("ID");
            l3 = new JLabel("NAME");
            l4 = new JLabel("ADDRESS");
            l5 = new JLabel("EMAIL");
            l6 = new JLabel("AGE");

            l7 = new JLabel("ID:");
            l8 = new JLabel("NAME:");
            l9 = new JLabel("ADDRESS:");
            l10 = new JLabel("EMAIL:");
            l11 = new JLabel("AGE:");

            t1 = new JTextField(10);
            t2 = new JTextField(10);
            t3 = new JTextField(10);
            t4 = new JTextField(10);
            t5 = new JTextField(10);

            b1 = new JButton("INSERT");
            b2 = new JButton("EDIT");
            b3 = new JButton("DELETE");
            b6 = new JButton("FIND");
            b4 = new JButton("SHOW CLIENTS");
            b5 = new JButton("BACK");


            l1.setBounds(100, 150, 300, 30);
            l2.setBounds(150, 170, 300, 30);
            l3.setBounds(260, 170, 300, 30);
            l4.setBounds(370, 170, 300, 30);
            l5.setBounds(500, 170, 300, 30);
            l6.setBounds(630, 170, 300, 30);

            l7.setBounds(250, 560, 300, 30);
            l8.setBounds(250, 600, 300, 30);
            l9.setBounds(250, 640, 300, 30);
            l10.setBounds(250, 680, 300, 30);
            l11.setBounds(250, 720, 300, 30);
            t1.setBounds(400, 560, 300, 30);
            t2.setBounds(400, 600, 300, 30);
            t3.setBounds(400, 640, 300, 30);
            t4.setBounds(400, 680, 300, 30);
            t5.setBounds(400, 720, 300, 30);


            l.setBounds(700, 20, 300, 50);
            table.setBounds(100, 200, 600, 320);
            b1.setBounds(800, 620, 200, 50);
            b2.setBounds(1000, 180, 200, 50);
            b3.setBounds(1000, 260, 200, 50);
            b6.setBounds(1000, 340, 200, 50);
            b4.setBounds(1000, 420, 200, 50);
            b5.setBounds(1000, 500, 200, 50);

            table.setGridColor(Color.black);
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(table);

            table.setValueAt("ID", 0, 0);
            table.setValueAt("NAME", 0, 1);
            table.setValueAt("ADDRESS", 0, 2);
            table.setValueAt("EMAIL", 0, 3);
            table.setValueAt("AGE", 0, 4);

            //put values to center in table
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < nrOfCol; i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            table.setShowGrid(true);
            l.setFont(new Font("cooper", Font.BOLD, 20));

            f.add(l1);
            f.add(l2);
            f.add(l3);
            f.add(l4);
            f.add(l5);
            f.add(l6);
            f.add(l7);
            f.add(l8);
            f.add(l9);
            f.add(l10);
            f.add(l11);

            f.add(t1);
            f.add(t2);
            f.add(t3);
            f.add(t4);
            f.add(t5);


            f.add(l);
            f.add(table);
            f.add(b1);
            f.add(b2);
            f.add(b3);
            f.add(b4);
            f.add(b5);
            f.add(b6);



            f.setLayout(null);
            f.setVisible(true);
            f.setSize(2000, 2000);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);

            b4.addActionListener(this);
            b5.addActionListener(this);
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            b6.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b4) {
                refreshTable();
            }


            if (e.getSource() == b1) {
                ClientBLL clientBLL = new ClientBLL();
                try {
                    clientBLL.insert(t1.getText(), t2.getText(), t3.getText(), t4.getText(), t5.getText());
                    //System.out.println("INSERT SUCCESSFULLY");
                    refreshTable();
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }


            if (e.getSource() == b2) {
                ClientBLL clientBLL = new ClientBLL();
                try {
                    clientBLL.update(t1.getText(), t2.getText(), t3.getText(), t4.getText(), t5.getText());
                    //System.out.println("INSERT SUCCESSFULLY");
                    refreshTable();
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }


            if (e.getSource() == b3) {
                ClientBLL clientBLL = new ClientBLL();
                try {
                    clientBLL.delete(t1.getText());
                    //System.out.println("INSERT SUCCESSFULLY");
                    refreshTable();
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }

            if (e.getSource() == b6) {
                ClientBLL clientBLL = new ClientBLL();
                Client c = new Client();
                try {
                    c = clientBLL.find(t1.getText());
                    t2.setText(c.getName());
                    t3.setText(c.getAddress());
                    t4.setText(c.getEmail());
                    t5.setText(String.valueOf((c.getAge())));
                    //System.out.println("INSERT SUCCESSFULLY");
                    refreshTable();
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }

            if (e.getSource() == b5) {
                try {
                    new View();
                    f.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    class ProductView implements ActionListener{

        JFrame f;
        JLabel l, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
        JTable table;
        JButton b1, b2, b3, b4, b5, b6;
        JTextField t1, t2, t3, t4, t5;


        public void refreshTable() {
            ClientDAO c = new ClientDAO();
            String s = "select * from product";
            try {
                Connection connection = ConnectionFactory.getConnection();
                Statement statement = connection.prepareStatement(s);
                ResultSet resultSet = statement.executeQuery(s);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
                //c.createObjects(resultSet).get(0);
            } catch (SQLException e1) {
                LOGGER.log(Level.WARNING, " show products " + e1.getMessage());
            }
        }


        public ProductView() throws Exception {
            Client c;
            int nrOfCol = 5;
            f = new JFrame("PRODUCT OPERATIONS");
            f.setLayout(new FlowLayout(FlowLayout.CENTER, 2000, /*20*/200));
            l = new JLabel("PRODUCT OPERATIONS");
            table = new JTable(20, nrOfCol);
            l1 = new JLabel("Products:");
            l2 = new JLabel("ID");
            l3 = new JLabel("NAME");
            l4 = new JLabel("COUNT");
            l5 = new JLabel("PRICE");
            l6 = new JLabel("QUALITY");

            l7 = new JLabel("ID:");
            l8 = new JLabel("NAME:");
            l9 = new JLabel("COUNT:");
            l10 = new JLabel("PRICE:");
            l11 = new JLabel("QUALITY:");

            t1 = new JTextField(10);
            t2 = new JTextField(10);
            t3 = new JTextField(10);
            t4 = new JTextField(10);
            t5 = new JTextField(10);

            b1 = new JButton("INSERT");
            b2 = new JButton("EDIT");
            b3 = new JButton("DELETE");
            b4 = new JButton("SHOW PRODUCTS");
            b5 = new JButton("BACK");
            b6 = new JButton("FIND");


            l1.setBounds(100, 150, 300, 30);
            l2.setBounds(150, 170, 300, 30);
            l3.setBounds(260, 170, 300, 30);
            l4.setBounds(370, 170, 300, 30);
            l5.setBounds(500, 170, 300, 30);
            l6.setBounds(630, 170, 300, 30);

            l7.setBounds(250, 560, 300, 30);
            l8.setBounds(250, 600, 300, 30);
            l9.setBounds(250, 640, 300, 30);
            l10.setBounds(250, 680, 300, 30);
            l11.setBounds(250, 720, 300, 30);
            t1.setBounds(400, 560, 300, 30);
            t2.setBounds(400, 600, 300, 30);
            t3.setBounds(400, 640, 300, 30);
            t4.setBounds(400, 680, 300, 30);
            t5.setBounds(400, 720, 300, 30);


            l.setBounds(700, 20, 300, 50);
            table.setBounds(100, 200, 600, 320);
            b1.setBounds(800, 620, 200, 50);
            b2.setBounds(1000, 180, 200, 50);
            b3.setBounds(1000, 260, 200, 50);
            b6.setBounds(1000, 340, 200, 50);
            b4.setBounds(1000, 420, 200, 50);
            b5.setBounds(1000, 500, 200, 50);


            table.setGridColor(Color.black);
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(table);

            table.setValueAt("ID", 0, 0);
            table.setValueAt("NAME", 0, 1);
            table.setValueAt("COUNT", 0, 2);
            table.setValueAt("PRICE", 0, 3);
            table.setValueAt("QUALITY", 0, 4);

            //put values to center in table
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < nrOfCol; i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            table.setShowGrid(true);
            l.setFont(new Font("cooper", Font.BOLD, 20));

            f.add(l1);
            f.add(l2);
            f.add(l3);
            f.add(l4);
            f.add(l5);
            f.add(l6);
            f.add(l7);
            f.add(l8);
            f.add(l9);
            f.add(l10);
            f.add(l11);

            f.add(t1);
            f.add(t2);
            f.add(t3);
            f.add(t4);
            f.add(t5);


            f.add(l);
            f.add(table);
            f.add(b1);
            f.add(b2);
            f.add(b3);
            f.add(b4);
            f.add(b5);
            f.add(b6);


            f.setLayout(null);
            f.setVisible(true);
            f.setSize(2000, 2000);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);

            b4.addActionListener(this);
            b5.addActionListener(this);
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            b6.addActionListener(this);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b4) {
                refreshTable();
            }


            if (e.getSource() == b1) {
                ProductBLL productBLL = new ProductBLL();
                try {
                    productBLL.insert(t1.getText(), t2.getText(), t3.getText(), t4.getText(), t5.getText());
                    //System.out.println("INSERT SUCCESSFULLY");
                    refreshTable();
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }


            if (e.getSource() == b2) {
                ProductBLL productBLL = new ProductBLL();
                try {
                    productBLL.update(t1.getText(), t2.getText(), t3.getText(), t4.getText(), t5.getText());
                    //System.out.println("INSERT SUCCESSFULLY");
                    refreshTable();
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }


            if (e.getSource() == b3) {
                ProductBLL productBLL = new ProductBLL();
                try {
                    productBLL.delete(t1.getText());
                    //System.out.println("INSERT SUCCESSFULLY");
                    refreshTable();
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }


            if (e.getSource() == b5) {
                try {
                    new View();
                    f.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (e.getSource() == b6) {
                ProductBLL productBLL = new ProductBLL();
                Product p= new Product();
                try {
                    p = productBLL.find(t1.getText());
                    t2.setText(p.getName());
                    t3.setText(String.valueOf(p.getCount()));
                    t4.setText(String.valueOf(p.getPrice()));
                    t5.setText(String.valueOf((p.getQuality())));
                    //System.out.println("INSERT SUCCESSFULLY");
                    refreshTable();
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }
        }
    }


    class OrderView implements ActionListener{
        JFrame f;
        JLabel l, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23;
        JTable table1, table2, table3;
        JButton b1, b2, b3, b4, b5;
        JTextField t1, t2, t3, t4, t5;
        public void refreshTable1() {
            ClientDAO c = new ClientDAO();
            String s = "select * from client";
            try {
                Connection connection = ConnectionFactory.getConnection();
                Statement statement = connection.prepareStatement(s);
                ResultSet resultSet = statement.executeQuery(s);
                table1.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (SQLException e1) {
                LOGGER.log(Level.WARNING, " show clients " + e1.getMessage());
            }
        }
        public void refreshTable2() {
            String s = "select * from product";
            try {
                Connection connection = ConnectionFactory.getConnection();
                Statement statement = connection.prepareStatement(s);
                ResultSet resultSet = statement.executeQuery(s);
                table2.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (SQLException e1) {
                LOGGER.log(Level.WARNING, " show clients " + e1.getMessage());
            }
        }
        public void refreshTable3() {
            String s = "select * from order1";
            try {
                Connection connection = ConnectionFactory.getConnection();
                Statement statement = connection.prepareStatement(s);
                ResultSet resultSet = statement.executeQuery(s);
                table3.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (SQLException e1) {
                LOGGER.log(Level.WARNING, " show orders " + e1.getMessage());
            }
        }
        public OrderView() throws Exception {
            Client c;
            int nrOfCol = 5;
            f = new JFrame("CLIENT ORDERS");
            f.setLayout(new FlowLayout(FlowLayout.CENTER, 2000, /*20*/200));
            l = new JLabel("CLIENT ORDERS");
            table1 = new JTable(20, nrOfCol);
            table2 = new JTable(20, nrOfCol);
            table3 = new JTable(20, nrOfCol);
            refreshTable1();
            refreshTable2();
            refreshTable3();
            l1 = new JLabel("Clients:");
            l2 = new JLabel("ID");
            l3 = new JLabel("NAME");
            l4 = new JLabel("ADDRESS");
            l5 = new JLabel("EMAIL");
            l6 = new JLabel("AGE");
            l10 = new JLabel("Products:");
            l7 = new JLabel("ID");
            l8 = new JLabel("NAME");
            l9 = new JLabel("COUNT");
            l13 = new JLabel("PRICE");
            l14 = new JLabel("QUALITY");
            l11 = new JLabel("Client id:");
            l12 = new JLabel("Product id:");
            l15 = new JLabel("Product count:");
            l16 = new JLabel("Product price:");
            l23 = new JLabel("Order id:");
            l17 = new JLabel("Orders:");
            l18 = new JLabel("ID");
            l19 = new JLabel("C_ID");
            l20 = new JLabel("P_ID");
            l21 = new JLabel("P_COUNT");
            l22 = new JLabel("O_PRICE");
            t1 = new JTextField(10);
            t2 = new JTextField(10);
            t3 = new JTextField(10);
            t4 = new JTextField(10);
            t5 = new JTextField(5);
            b1 = new JButton("MAKE ORDER");
            b2 = new JButton("BACK");
            b3 = new JButton("FIND ORDER");
            b4 = new JButton("EDIT ORDER");
            b5 = new JButton("DELETE ORDER");
            l1.setBounds(100, 50, 300, 30);
            l2.setBounds(150, 70, 300, 30);
            l3.setBounds(260, 70, 300, 30);
            l4.setBounds(370, 70, 300, 30);
            l5.setBounds(500, 70, 300, 30);
            l6.setBounds(630, 70, 300, 30);
            l7.setBounds(930, 70, 300, 30);
            l8.setBounds(1040, 70, 300, 30);
            l9.setBounds(1160, 70, 300, 30);
            l13.setBounds(1280, 70, 300, 30);
            l14.setBounds(1390, 70, 300, 30);
            l10.setBounds(880, 50, 300, 30);
            l17.setBounds(880, 420, 300, 30);
            l18.setBounds(930, 440, 300, 30);
            l19.setBounds(1040, 440, 300, 30);
            l20.setBounds(1160, 440, 300, 30);
            l21.setBounds(1280, 440, 300, 30);
            l22.setBounds(1390, 440, 300, 30);
            l23.setBounds(480, 450, 300, 30);
            l11.setBounds(70, 450, 300, 30);
            l12.setBounds(70, 500, 300, 30);
            l15.setBounds(70, 550, 300, 30);
            l16.setBounds(70, 600, 300, 30);
            t1.setBounds(160, 450, 300, 30);
            t2.setBounds(160, 500, 300, 30);
            t3.setBounds(160, 550, 300, 30);
            t4.setBounds(160, 600, 300, 30);
            t5.setBounds(550, 450, 300, 30);
            l.setBounds(720, 5, 300, 50);
            table1.setBounds(100, 100, 600, 320);
            table2.setBounds(880, 100, 600, 320);
            table3.setBounds(880, 470, 600, 320);
            b1.setBounds(200, 650, 200, 50);
            b2.setBounds(30, 730, 200, 50);
            b3.setBounds(580, 520, 200, 50);
            b4.setBounds(580, 600, 200, 50);
            b5.setBounds(580, 680, 200, 50);
            table1.setGridColor(Color.black);
            table2.setGridColor(Color.black);
            table3.setGridColor(Color.black);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < nrOfCol; i++) {
                table1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            for (int i = 0; i < nrOfCol; i++) {
                table2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            for (int i = 0; i < nrOfCol; i++) {
                table3.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            table1.setShowGrid(true);
            table2.setShowGrid(true);
            table3.setShowGrid(true);
            l.setFont(new Font("cooper", Font.BOLD, 20));
            f.add(l1);
            f.add(l2);
            f.add(l3);
            f.add(l4);
            f.add(l5);
            f.add(l6);
            f.add(l7);
            f.add(l8);
            f.add(l9);
            f.add(l10);
            f.add(l11);
            f.add(l12);
            f.add(l13);
            f.add(l14);
            f.add(l15);
            f.add(l16);
            f.add(l17);
            f.add(l18);
            f.add(l19);
            f.add(l20);
            f.add(l21);
            f.add(l22);
            f.add(l23);
            f.add(t1);
            f.add(t2);
            f.add(t3);
            f.add(t4);
            f.add(t5);
            f.add(l);
            f.add(table1);
            f.add(table2);
            f.add(table3);
            f.add(b1);
            f.add(b2);
            f.add(b3);
            f.add(b4);
            f.add(b5);
            f.setLayout(null);
            f.setVisible(true);
            f.setSize(2000, 2000);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);
            b2.addActionListener(this);
            b1.addActionListener(this);
            b5.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                String count1 = "";
                OrderBLL orderBLL = new OrderBLL();
                ProductBLL productBLL = new ProductBLL();
                Product p = new Product();
                Order o = new Order();
                String o_id = "";
                try {
                    p = productBLL.find(t2.getText());
                    count1 = String.valueOf(p.getCount());
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
                ClientBLL clientBLL = new ClientBLL();
                Client c = new Client();
                try {
                    c = clientBLL.find(t1.getText());
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
                if(Integer.parseInt(count1) >= Integer.parseInt(t3.getText())) {

                    JOptionPane.showMessageDialog(null, " Order made successfully! ");
                    int count = Integer.parseInt(count1) - Integer.parseInt(t3.getText());
                    try {
                        productBLL.update(t2.getText(), p.getName(), String.valueOf(count), String.valueOf(p.getPrice()), p.getQuality());
                        refreshTable2();
                    } catch (Exception ex) {
                        LOGGER.log(Level.INFO, ex.getMessage());
                    }
                    int price = Integer.parseInt(t3.getText()) * Integer.parseInt(t4.getText());
                    try {
                        orderBLL.insert(t5.getText(), t1.getText(), t2.getText(), t3.getText(), String.valueOf(price));
                        refreshTable3();
                    } catch (Exception ex) {
                        LOGGER.log(Level.INFO, ex.getMessage());
                    }

                    try {
                        p = productBLL.find(t2.getText());
                        count1 = String.valueOf(p.getCount());
                    } catch (Exception ex) {
                        LOGGER.log(Level.INFO, ex.getMessage());
                    }

                    if (count1.equals(String.valueOf(0))) {
                        try {
                            productBLL.delete(t2.getText());
                            refreshTable2();
                        } catch (Exception ex) {
                            LOGGER.log(Level.INFO, ex.getMessage());
                        }
                    }
                    try {
                        o = orderBLL.find(t5.getText());
                        o_id = String.valueOf(o.getId());

                    } catch (Exception ex) {
                        LOGGER.log(Level.INFO, ex.getMessage());
                    }
                    t5.setText(String.valueOf(o.getId()));

                    String billMessage = "Order #" + o_id + "\nClient name: " + c.getName() + "\nProduct name: " + p.getName() + "\nProduct count: " + o.getProductCount() + "\nOrder price: " + price;
                    orderBLL.createBill(String.valueOf(o.getId()), billMessage);
                }
                else JOptionPane.showMessageDialog(null," Desired quantity under-stock! ");
            }
            if (e.getSource() == b2) {
                try {
                    new View();
                    f.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == b3) {
                Order o;
                OrderBLL orderBLL = new OrderBLL();
                try {
                    o = orderBLL.find(t5.getText());
                    t1.setText(String.valueOf(o.getClientId()));
                    t2.setText(String.valueOf(o.getProductId()));
                    t3.setText(String.valueOf(o.getProductCount()));
                    t4.setText(String.valueOf(o.getProductPrice()));
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }
            if (e.getSource() == b4) {
               OrderBLL orderBLL = new OrderBLL();
                try {
                    orderBLL.updateOrder(t5.getText(), t1.getText(), t2.getText(), t3.getText(), t4.getText());
                    refreshTable3();
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }
            if (e.getSource() == b5) {
                OrderBLL orderBLL = new OrderBLL();
                try {
                    orderBLL.deleteOrder(t5.getText());
                    refreshTable3();
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }
    }
}



