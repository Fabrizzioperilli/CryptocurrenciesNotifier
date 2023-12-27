package org.ull.dap.app.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;
import javax.swing.JLabel;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class Notification extends JFrame {


    private boolean changeColor;
    private JPanel notificationPanel;
    private JPanel titlePanel;
    private JPanel footerPanel;
    private JPanel cryptoPanel;
    private JLabel lblTitle, lblFooter;
    private JScrollPane scrollPaneCrypto;

    public static final String ROUTE_IMAGE_LOGO = "/images/logo_app.png";

    public Notification() {
        initializeUI();
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPanel);
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.add(getNotificationPanel(), BorderLayout.CENTER);
    }

    private void initializeUI() {
        setTitle("Notify");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 550, 250);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_LOGO))).getImage());
    }

    private JPanel getNotificationPanel() {
        if (notificationPanel != null) {
            return notificationPanel;
        }
        notificationPanel = new JPanel();
        notificationPanel.setLayout(new BorderLayout(0, 0));
        notificationPanel.add(getTitlePanel(), BorderLayout.NORTH);
        notificationPanel.add(getFooterPanel(), BorderLayout.SOUTH);
        notificationPanel.add(getScrollPaneCrypto(), BorderLayout.CENTER);
        return notificationPanel;
    }
    private JPanel getTitlePanel() {
        if (titlePanel != null) {
            return titlePanel;
        }
        titlePanel = new JPanel();
        titlePanel.setBackground(new Color(1, 1, 44));
        titlePanel.setLayout(new BorderLayout(0, 0));
        titlePanel.add(getLblTitle());
        return titlePanel;
    }

    private JLabel getLblTitle() {
        if (lblTitle != null) {
            return lblTitle;
        }
        lblTitle = new JLabel("New Prices");
        lblTitle.setForeground(new Color(24, 243, 7));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        return lblTitle;
    }

    public ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
    private JPanel getFooterPanel() {
        if (footerPanel != null) {
            return footerPanel;
        }
        footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
        footerPanel.add(getLblFooter());
        return footerPanel;
    }
    private JLabel getLblFooter() {
        if (lblFooter == null) {
            lblFooter = new JLabel("CryptoNotifier");
        }
        return lblFooter;
    }
    private JScrollPane getScrollPaneCrypto() {
        if (scrollPaneCrypto != null) {
            return scrollPaneCrypto;
        }
        scrollPaneCrypto = new JScrollPane();
        scrollPaneCrypto.setViewportView(getCryptoPanel());
        return scrollPaneCrypto;
    }
    private JPanel getCryptoPanel() {
        if (cryptoPanel == null) {
            cryptoPanel = new JPanel();
            cryptoPanel.setLayout(new GridLayout(5, 1, 0, 0));
        }
        return cryptoPanel;
    }

    public void createNotify(String nombre, String crypto, double price) {
        JPanel p = new JPanel();
        if (!changeColor) {
            p.setBackground(new Color(123, 149, 255));
            changeColor = true;
        }else{
            p.setBackground(new Color(141, 207, 236));
            changeColor = false;
        }
        p.setLayout(new BorderLayout(0, 0));
        JLabel l_texto = new JLabel();
        JLabel l_imagen = new JLabel();
        ImageIcon i = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/" + crypto + ".png")));
        l_imagen.setIcon(resizeIcon(i,30,30));
        l_texto.setText(nombre+ ","+crypto+"'s price has changed to: "+ price + "USD");
        l_texto.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cryptoPanel.add(p);
        p.add(l_texto, BorderLayout.CENTER);
        p.add(l_imagen, BorderLayout.EAST);
    }
}