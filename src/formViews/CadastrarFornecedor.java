/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formViews;

import controller.FornecedorJpaController;
import model.Fornecedor;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import mainViews.MainWindow;

/**
 *
 * @author aderito
 */
public class CadastrarFornecedor extends javax.swing.JFrame {
    Fornecedor fornecedor;
    FornecedorJpaController fjc;
    
    /**
     * Creates new form cadastrarFornecedor
     */
    public CadastrarFornecedor() {
        initComponents();
        
        fornecedor = new Fornecedor();
        fjc = new FornecedorJpaController();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title_cad_fornecedor = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_NomeFornecedor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_LocalFornecedor = new javax.swing.JTextField();
        txt_EmailFornecedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_ContactoFornecedor = new javax.swing.JTextField();
        btn_cadastrarFornecedor = new javax.swing.JButton();
        btn_goBackMain = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title_cad_fornecedor.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        title_cad_fornecedor.setText("Cadastrar fornecedor");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel2.setText("Nome");

        txt_NomeFornecedor.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        txt_NomeFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NomeFornecedorActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel3.setText("Localizacao");

        txt_LocalFornecedor.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N

        txt_EmailFornecedor.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel4.setText("Email");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel5.setText("Contacto");

        txt_ContactoFornecedor.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N

        btn_cadastrarFornecedor.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btn_cadastrarFornecedor.setText("Cadastrar");
        btn_cadastrarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarFornecedorActionPerformed(evt);
            }
        });
        btn_cadastrarFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_cadastrarFornecedorKeyPressed(evt);
            }
        });

        btn_goBackMain.setText("<--");
        btn_goBackMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_goBackMainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_ContactoFornecedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_EmailFornecedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_LocalFornecedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_NomeFornecedor)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_goBackMain)
                                .addGap(145, 145, 145)
                                .addComponent(title_cad_fornecedor)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(btn_cadastrarFornecedor)
                .addContainerGap(272, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title_cad_fornecedor)
                    .addComponent(btn_goBackMain))
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_LocalFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_EmailFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ContactoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_cadastrarFornecedor)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_NomeFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NomeFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NomeFornecedorActionPerformed

    private void btn_cadastrarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarFornecedorActionPerformed
        fornecedor.setNome(txt_NomeFornecedor.getText());
        fornecedor.setEmail(txt_EmailFornecedor.getText());
        fornecedor.setLocalizacao(txt_LocalFornecedor.getText());
        fornecedor.setContacto(txt_ContactoFornecedor.getText());
        
        try {
            
            fjc.create(fornecedor);
            
            JOptionPane.showMessageDialog(null
                    , "Fornecedor cadastrado com sucesso!"
                    , "Mensagem"
                    , JOptionPane.INFORMATION_MESSAGE); 
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null
                    , e.getMessage()
                    , "Mensagem de Erro"
                    , JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btn_cadastrarFornecedorActionPerformed

    private void btn_cadastrarFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_cadastrarFornecedorKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            fornecedor.setNome(txt_NomeFornecedor.getText());
            fornecedor.setEmail(txt_EmailFornecedor.getText());
            fornecedor.setLocalizacao(txt_LocalFornecedor.getText());
            fornecedor.setContacto(txt_ContactoFornecedor.getText());

            try {

                fjc.create(fornecedor);

                JOptionPane.showMessageDialog(null
                        , "Fornecedor cadastrado com sucesso!"
                        , "Mensagem"
                        , JOptionPane.INFORMATION_MESSAGE); 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null
                        , e.getMessage()
                        , "Mensagem de Erro"
                        , JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_cadastrarFornecedorKeyPressed

    private void btn_goBackMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_goBackMainActionPerformed
        // TODO add your handling code here:
        
        dispose();
        new MainWindow().setVisible(true);
        
    }//GEN-LAST:event_btn_goBackMainActionPerformed

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
            java.util.logging.Logger.getLogger(CadastrarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarFornecedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastrarFornecedor;
    private javax.swing.JButton btn_goBackMain;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel title_cad_fornecedor;
    private javax.swing.JTextField txt_ContactoFornecedor;
    private javax.swing.JTextField txt_EmailFornecedor;
    private javax.swing.JTextField txt_LocalFornecedor;
    private javax.swing.JTextField txt_NomeFornecedor;
    // End of variables declaration//GEN-END:variables
}
