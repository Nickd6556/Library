/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java_Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import project.ConnectionProvider;

/**
 *
 * @author Nikit
 */
public class ReturnBook extends javax.swing.JFrame {

    /**
     * Creates new form ReturnBook
     */
    public ReturnBook() {
        initComponents();
        Date date = new Date();
        //lbl_date.setText(dFormat.format(date));
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, +0);
        Date d = c.getTime();
        date_returned_date.setDate(d);
        date_returned_date.getDateEditor().setEnabled(false);
        date_returned_date.getJCalendar().setMinSelectableDate(date);
        date_returned_date.getJCalendar().setMaxSelectableDate(d);
    }

    public boolean getIssueBookDetails() {
        boolean isIssued = false;
        String book_id = txt_book_id.getText();
        String student_id = txt_student_id.getText();
        try {
            Connection con = ConnectionProvider.getCon();
            String sql = "Select book_id,book_name,student_id,student_name,issue_date,due_date,TIMESTAMPDIFF(DAY,due_date,now()) as fine_days from issue_book_details where book_id =? and student_id = ? and status =?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, book_id);
            ps.setString(2, student_id);
            ps.setString(3, "Pending");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isIssued = true;
                txt_id_book.setText(rs.getString("book_id"));
                txt_book_name.setText(rs.getString("book_name"));
                txt_id_student.setText(rs.getString("student_id"));
                txt_student_name.setText(rs.getString("student_name"));
                txt_issue_date.setText(rs.getString("issue_date"));
                txt_due_date.setText(rs.getString("due_date"));

                txt_fine_days.setText(rs.getString("fine_days"));
                int fine_days = Integer.parseInt(txt_fine_days.getText());

                if (fine_days <= 0) {
                    txt_fine_days.setText("0");
                    txt_total_fine.setText("No Fine");
                } else {
                    int fine = Integer.parseInt(txt_fine_days.getText());
                    int TotalFine = fine * 10;
                    //System.out.println(TotalFine);
                    txt_total_fine.setText(String.valueOf(TotalFine + " Rs"));
                }
            } else {
                isIssued = false;
                txt_id_book.setText("");
                txt_book_name.setText("");
                txt_id_student.setText("");
                txt_student_name.setText("");
                txt_issue_date.setText("");
                txt_due_date.setText("");
                txt_fine_days.setText("");
                txt_total_fine.setText("");

                JFrame jf = new JFrame();
                jf.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(jf, "No Record Found !");
            }

        } catch (SQLException e) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, e);

        }
        return isIssued;
    }

    //Return the Book
    public boolean returnBook() {
        boolean isReturned = false;
        String book_id = txt_book_id.getText();
        String student_id = txt_student_id.getText();
        String fine_days = txt_fine_days.getText();
        String total_fine = txt_total_fine.getText();
        java.util.Date uissue_date = date_returned_date.getDate();
        Long l1 = uissue_date.getTime();
        java.sql.Date sreturned_date = new java.sql.Date(l1);
        try {
            Connection con = ConnectionProvider.getCon();
            String sql = "Update issue_book_details set status =?,fine_days=? ,total_fine = ?,returned_date=?  where student_id =? and book_id =? and status =?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "Returned");
            ps.setString(2, fine_days);
            ps.setString(3, total_fine);
            ps.setDate(4, sreturned_date);
            ps.setString(5, student_id);
            ps.setString(6, book_id);
            ps.setString(7, "Pending");
            int rowCount = ps.executeUpdate();
            if (rowCount > 0) {
                isReturned = true;
                con.close();
                txt_id_book.setText("");
                txt_book_name.setText("");
                txt_id_student.setText("");
                txt_student_name.setText("");
                txt_issue_date.setText("");
                txt_due_date.setText("");
                txt_student_id.setText("");
                txt_fine_days.setText("");
                txt_total_fine.setText("");

            } else {
                isReturned = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isReturned;
    }

    // Method to update book Count after Returning book
    public void updateBookCount() {
        String book_id = txt_book_id.getText();
        try {
            Connection con = ConnectionProvider.getCon();
            String sql = "Update book_details set quantity = quantity + 1 where book_id =?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, book_id);

            int rowCount = ps.executeUpdate();

            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Book Count Updated !");

            } else {
                JOptionPane.showMessageDialog(this, "Cant Update Book Count !");
            }

        } catch (SQLException e) {
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_book_name = new javax.swing.JLabel();
        txt_id_book = new javax.swing.JLabel();
        txt_id_student = new javax.swing.JLabel();
        txt_issue_date = new javax.swing.JLabel();
        txt_student_name = new javax.swing.JLabel();
        txt_fine_days = new javax.swing.JLabel();
        txt_due_date = new javax.swing.JLabel();
        txt_total_fine = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_find_details = new rojerusan.RSButtonHover();
        txt_student_id = new app.bolivia.swing.JCTextField();
        txt_book_id = new app.bolivia.swing.JCTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        btn_issue_book_return = new rojerusan.RSButtonHover();
        jLabel9 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date_returned_date = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/library-2 (Custom).png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 420, 660));

        kGradientPanel1.setkBorderRadius(2);
        kGradientPanel1.setkEndColor(new java.awt.Color(0, 102, 102));
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 102, 102));

        jLabel3.setFont(new java.awt.Font("Sitka Heading", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel3.setText(" Issue Details");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(380, 4));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Student Id         :");

        jLabel6.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Book Id              :");

        jLabel7.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Book Name        :");

        jLabel8.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Student Name  :");

        jLabel10.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Due Date           :");

        jLabel11.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Issue Date         :");

        jLabel12.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(" Fine Days         :");

        jLabel13.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText(" Total Fine        :");

        txt_book_name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_book_name.setForeground(new java.awt.Color(255, 255, 255));

        txt_id_book.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_id_book.setForeground(new java.awt.Color(255, 255, 255));

        txt_id_student.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_id_student.setForeground(new java.awt.Color(255, 255, 255));

        txt_issue_date.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_issue_date.setForeground(new java.awt.Color(255, 255, 255));

        txt_student_name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_student_name.setForeground(new java.awt.Color(255, 255, 255));

        txt_fine_days.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txt_fine_days.setForeground(new java.awt.Color(255, 51, 51));

        txt_due_date.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_due_date.setForeground(new java.awt.Color(255, 255, 255));

        txt_total_fine.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_total_fine.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_book_name, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_id_book, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_id_student, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_student_name, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10))
                                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txt_due_date, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(txt_issue_date, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_fine_days, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_total_fine, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_id_book, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_book_name, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_id_student, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_student_name, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_issue_date, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txt_due_date, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_fine_days, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_total_fine, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        jPanel1.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 510, 900));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        jLabel1.setText(" Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 0, 40, 50));

        btn_find_details.setText("FIND DETAILS");
        btn_find_details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_find_detailsActionPerformed(evt);
            }
        });
        jPanel1.add(btn_find_details, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 630, 330, -1));

        txt_student_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        txt_student_id.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_student_id.setPlaceholder("Enter Student Id");
        jPanel1.add(txt_student_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 430, 240, 30));

        txt_book_id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        txt_book_id.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_book_id.setPlaceholder("Enter Book Id");
        jPanel1.add(txt_book_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 350, 240, 30));

        jLabel26.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jLabel26.setText("Student Id  :");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 430, -1, 30));

        jLabel25.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jLabel25.setText("Book Id       :");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 350, -1, 30));

        jPanel5.setBackground(new java.awt.Color(102, 51, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(380, 4));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 180, -1, -1));

        jLabel23.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel23.setText("  Return Book");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 110, 300, -1));

        btn_issue_book_return.setText("RETURN BOOK");
        btn_issue_book_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_issue_book_returnActionPerformed(evt);
            }
        });
        jPanel1.add(btn_issue_book_return, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 740, 330, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minimize.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 0, 40, 50));

        jLabel27.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jLabel27.setText("Returned Date  :");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 500, -1, 30));

        date_returned_date.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 204)));
        date_returned_date.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        date_returned_date.setMaxSelectableDate(new java.util.Date(253370747770000L));
        date_returned_date.setMinSelectableDate(new java.util.Date(-62135786630000L));
        jPanel1.add(date_returned_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 500, 200, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1480, 900));

        setSize(new java.awt.Dimension(1482, 894));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        setVisible(false);
        new Dashboard().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to Exit ?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
            dispose();
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btn_find_detailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_find_detailsActionPerformed

        if (txt_book_id.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Book Id !");
        } else if ((txt_student_id.getText().equals(""))) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Id !");

        } else {
            getIssueBookDetails();

        }

    }//GEN-LAST:event_btn_find_detailsActionPerformed

    private void btn_issue_book_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_issue_book_returnActionPerformed
        // TODO add your handling code here:
        if (returnBook() == true) {
            JOptionPane.showMessageDialog(this, "Book Returned Sucessfully !");
            JOptionPane.showMessageDialog(this, "Fine Updated If Due Date Is Crossed !");
            updateBookCount();

        } else {
            JOptionPane.showMessageDialog(this, "Book Returned Failed !");

        }
    }//GEN-LAST:event_btn_issue_book_returnActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel9MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ReturnBook().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonHover btn_find_details;
    private rojerusan.RSButtonHover btn_issue_book_return;
    private com.toedter.calendar.JDateChooser date_returned_date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private app.bolivia.swing.JCTextField txt_book_id;
    private javax.swing.JLabel txt_book_name;
    private javax.swing.JLabel txt_due_date;
    private javax.swing.JLabel txt_fine_days;
    private javax.swing.JLabel txt_id_book;
    private javax.swing.JLabel txt_id_student;
    private javax.swing.JLabel txt_issue_date;
    private app.bolivia.swing.JCTextField txt_student_id;
    private javax.swing.JLabel txt_student_name;
    private javax.swing.JLabel txt_total_fine;
    // End of variables declaration//GEN-END:variables
}
