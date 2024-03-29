/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formViews;

import controller.LojaJpaController;
import model.Loja;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import mainViews.Lojas;
import mainViews.MainWindow;

/**
 *
 * @author aderito
 */
public class CadastrarLoja extends javax.swing.JFrame {
    Loja loja;
    LojaJpaController ljc;
    
    /**
     * Creates new form cadastrarLoja
     */
    public CadastrarLoja() {
        initComponents();
        
        loja = new Loja();
        ljc = new LojaJpaController();
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
        jLabel1 = new javax.swing.JLabel();
        txt_LocalLoja = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_DescricaoLoja = new javax.swing.JTextArea();
        txt_NomeLoja = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_CadastrarLoja = new javax.swing.JButton();
        title_cad_loja = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_contacto = new javax.swing.JTextField();
        btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel1.setText("Nome");

        txt_LocalLoja.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N

        txt_DescricaoLoja.setColumns(20);
        txt_DescricaoLoja.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        txt_DescricaoLoja.setRows(5);
        jScrollPane1.setViewportView(txt_DescricaoLoja);

        txt_NomeLoja.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        txt_NomeLoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NomeLojaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel2.setText("Localizacao");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel3.setText("Descricao");

        btn_CadastrarLoja.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_CadastrarLoja.setText("Cadastrar");
        btn_CadastrarLoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CadastrarLojaActionPerformed(evt);
            }
        });
        btn_CadastrarLoja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_CadastrarLojaKeyPressed(evt);
            }
        });

        title_cad_loja.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        title_cad_loja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_cad_loja.setText("Cadastrar Loja");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel4.setText("Contacto");

        txt_contacto.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N

        btn_back.setText("<--");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_LocalLoja, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_NomeLoja)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txt_contacto, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(259, 259, 259)
                                .addComponent(btn_CadastrarLoja))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btn_back)
                                .addGap(181, 181, 181)
                                .addComponent(title_cad_loja)))
                        .addGap(0, 227, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title_cad_loja)
                    .addComponent(btn_back))
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NomeLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_LocalLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_CadastrarLoja)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_NomeLojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NomeLojaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NomeLojaActionPerformed

    private void btn_CadastrarLojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CadastrarLojaActionPerformed
        loja.setNome(txt_NomeLoja.getText());
        loja.setContacto(txt_contacto.getText());
        loja.setLocalizacao(txt_LocalLoja.getText());
        loja.setDescricao(txt_DescricaoLoja.getText());
        
        try {
            
            ljc.create(loja);
            
            JOptionPane.showMessageDialog(null
                    , "Loja cadastrada com sucesso!"
                    , "Mensagem"
                    , JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
            new Lojas().setVisible(true);
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null
                    , e.getMessage()
                    , "Mensagem de Erro"
                    , JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_btn_CadastrarLojaActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        
        dispose();
        new MainWindow().setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_CadastrarLojaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_CadastrarLojaKeyPressed
        // TODO add your handling code here:   
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            loja.setNome(txt_NomeLoja.getText());
            loja.setContacto(txt_contacto.getText());
            loja.setLocalizacao(txt_LocalLoja.getText());
            loja.setDescricao(txt_DescricaoLoja.getText());

            try {

                ljc.create(loja);

                JOptionPane.showMessageDialog(null
                        , "Loja cadastrada com sucesso!"
                        , "Mensagem"
                        , JOptionPane.INFORMATION_MESSAGE);

                dispose();
                new Lojas().setVisible(true);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null
                        , e.getMessage()
                        , "Mensagem de Erro"
                        , JOptionPane.ERROR_MESSAGE);

            }
        }
    }//GEN-LAST:event_btn_CadastrarLojaKeyPressed

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
            java.util.logging.Logger.getLogger(CadastrarLoja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarLoja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarLoja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarLoja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarLoja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CadastrarLoja;
    private javax.swing.JButton btn_back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel title_cad_loja;
    private javax.swing.JTextArea txt_DescricaoLoja;
    private javax.swing.JTextField txt_LocalLoja;
    private javax.swing.JTextField txt_NomeLoja;
    private javax.swing.JTextField txt_contacto;
    // End of variables declaration//GEN-END:variables
}
