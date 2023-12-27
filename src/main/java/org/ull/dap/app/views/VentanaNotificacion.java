package org.ull.dap.app.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.JLabel;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class VentanaNotificacion extends JFrame {


    private int alternador = 1;
    private JPanel contentPane;
    private JPanel pnNotificacion;
    private JPanel pnTitulo;

    private JLabel lblTitulo;

    private JPanel pnPieDeVentana;
    private JLabel lblPie;
    private JScrollPane scpnCrypto;
    private JPanel pnCrypto;

    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public VentanaNotificacion() {
        setResizable(false);
        setTitle("Notificación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(getPnNotificacion(), BorderLayout.CENTER);
    }

    private JPanel getPnNotificacion() {
        if (pnNotificacion == null) {
            pnNotificacion = new JPanel();
            pnNotificacion.setLayout(new BorderLayout(0, 0));
            pnNotificacion.add(getPanel_1(), BorderLayout.NORTH);
            pnNotificacion.add(getPnPieDeVentana(), BorderLayout.SOUTH);
            pnNotificacion.add(getScpnCrypto(), BorderLayout.CENTER);
        }
        return pnNotificacion;
    }
    private JPanel getPanel_1() {
        if (pnTitulo == null) {
            pnTitulo = new JPanel();
            pnTitulo.setBackground(new Color(1, 1, 44));
            pnTitulo.setLayout(new BorderLayout(0, 0));
            pnTitulo.add(getLabel_2());
        }
        return pnTitulo;
    }

    private JLabel getLabel_2() {
        if (lblTitulo == null) {
            lblTitulo = new JLabel("New Prices");
            lblTitulo.setForeground(new Color(24, 243, 7));
            lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
            lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return lblTitulo;
    }

    public ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage(); // Obtiene la imagen del ImageIcon
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Redimensiona la imagen
        return new ImageIcon(newImage); // Crea un nuevo ImageIcon con la imagen redimensionada
    }
    private JPanel getPnPieDeVentana() {
        if (pnPieDeVentana == null) {
            pnPieDeVentana = new JPanel();
            pnPieDeVentana.setLayout(new BoxLayout(pnPieDeVentana, BoxLayout.X_AXIS));
            pnPieDeVentana.add(getLblPie());
        }
        return pnPieDeVentana;
    }
    private JLabel getLblPie() {
        if (lblPie == null) {
            lblPie = new JLabel("CryptoNotifier");
        }
        return lblPie;
    }
    private JScrollPane getScpnCrypto() {
        if (scpnCrypto == null) {
            scpnCrypto = new JScrollPane();
            scpnCrypto.setViewportView(getPnCrypto());
        }
        return scpnCrypto;
    }
    private JPanel getPnCrypto() {
        if (pnCrypto == null) {
            pnCrypto = new JPanel();
            pnCrypto.setLayout(new GridLayout(5, 1, 0, 0));
        }
        return pnCrypto;
    }

    public void crearNotificacion(String nombre, String crypto, double price) {
        JPanel p = new JPanel();
        if (alternador == 1) {
            p.setBackground(new Color(123, 149, 255));
            alternador++;
        }else{
            p.setBackground(new Color(141, 207, 236));
            alternador--;
        }
        p.setLayout(new BorderLayout(0, 0));
        JLabel l_texto = new JLabel();
        JLabel l_imagen = new JLabel();
        ImageIcon i = new ImageIcon("src/main/java/org/ull/dap/app/gui/img/"+crypto+".png");
        l_imagen.setIcon(resizeIcon(i,30,30));
        l_texto.setText(nombre+ ","+crypto+"'s price has changed to: "+price+ "USD");
        l_texto.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnCrypto.add(p);
        p.add(l_texto, BorderLayout.CENTER);
        p.add(l_imagen, BorderLayout.EAST);
    }
}