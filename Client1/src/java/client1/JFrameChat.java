package client1;

import java.awt.event.KeyEvent;
import javax.jms.*;
import javax.naming.*;
import javax.swing.JOptionPane;

public class JFrameChat extends javax.swing.JFrame {
    
    private QueueSender qs = null;
    private QueueConnection qc = null;
    private QueueSession qss = null;

  
    public JFrameChat() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldContent = new javax.swing.JTextField();
        jButtonConnect = new javax.swing.JButton();
        jButtonSend = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat 1");

        jTextFieldContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldContentActionPerformed(evt);
            }
        });
        jTextFieldContent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldContentKeyReleased(evt);
            }
        });

        jButtonConnect.setText("Connect");
        jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnectActionPerformed(evt);
            }
        });

        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldContent, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonConnect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSend)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonClose)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConnect)
                    .addComponent(jButtonSend)
                    .addComponent(jButtonClose))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnectActionPerformed
        // Connect
        try {
        Context ctx = new InitialContext();
        QueueConnectionFactory qcf = (QueueConnectionFactory) ctx.lookup("myconP2P");
        Queue q = (Queue) ctx.lookup("mydes");
        qc = qcf.createQueueConnection();
        qss = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        qs = qss.createSender(q);
        qc.start();
        this.jButtonConnect.setVisible(false);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No puedo Conectarme");
        }
    }//GEN-LAST:event_jButtonConnectActionPerformed

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        // Send 
        SendMessage();        
        }
    
        private void SendMessage() {
        try {
        TextMessage tx = qss.createTextMessage(this.jTextFieldContent.getText());
        qs.send(tx);
        this.jTextFieldContent.setText("");
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error enviando Mensaje");
        }
        
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        // Close
        try {
        qc.close();
        this.dispose();
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No puedo Cerrar la aplicacion");
        }
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jTextFieldContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldContentActionPerformed
        // Evento o barra de texto  
       
    }//GEN-LAST:event_jTextFieldContentActionPerformed

    private void jTextFieldContentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldContentKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
            SendMessage();
    }//GEN-LAST:event_jTextFieldContentKeyReleased

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
            java.util.logging.Logger.getLogger(JFrameChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameChat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JTextField jTextFieldContent;
    // End of variables declaration//GEN-END:variables
}
