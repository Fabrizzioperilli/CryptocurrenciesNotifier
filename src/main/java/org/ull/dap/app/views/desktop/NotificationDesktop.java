package org.ull.dap.app.views.desktop;

import org.ull.dap.app.views.INotification;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Notification desktop.
 */
public class NotificationDesktop extends JFrame implements INotification {

    private boolean changeColor;
    private JPanel notificationPanel;
    private JPanel titlePanel;
    private JPanel footerPanel;
    private JPanel cryptoPanel;
    private JLabel lblTitle, lblFooter;
    private JScrollPane scrollPaneCrypto;
    /**
     * The constant ROUTE_IMAGE_LOGO.
     */
    public static final String ROUTE_IMAGE_LOGO = "/images/logo_app.png";

    /**
     * Instantiates a new Notification desktop.
     */
    public NotificationDesktop() {
        initializeUI();
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPanel);
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.add(getNotificationPanel(), BorderLayout.CENTER);
    }

    private void initializeUI() {
        setTitle("Notification");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 570, 250);
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

    /**
     * Resize icon image icon.
     *
     * @param icon   the icon
     * @param width  the width
     * @param height the height
     * @return the image icon
     */
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
            cryptoPanel.setLayout(new GridLayout(10000, 0, 0, 0));
        }
        return cryptoPanel;
    }

    @Override
    public void showNotification(List<String> messages, Map<String, String> cryptoNameImage) {
        for (String message : messages) {
            JPanel p = new JPanel();
            setVisible(true);
            if (!changeColor) {
                p.setBackground(new Color(123, 149, 255));
                changeColor = true;
            } else {
                p.setBackground(new Color(141, 207, 236));
                changeColor = false;
            }
            p.setLayout(new BorderLayout(0, 0));
            JLabel l_text = new JLabel("<html>" + message.replace("\n", "<br>") + "</html>");
            JLabel imageLabel = new JLabel();
            String nameCrypto = extractNameCrypto(message);
            try {
                BufferedImage image = ImageIO.read(new URL(cryptoNameImage.get(nameCrypto)));
                Image scaledImage = image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImage);
                imageLabel.setIcon(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }

            l_text.setFont(new Font("Arial", Font.BOLD, 16));
            p.add(imageLabel, BorderLayout.EAST);
            p.add(l_text, BorderLayout.CENTER);

            cryptoPanel.add(p);
        }
        cryptoPanel.revalidate();
        cryptoPanel.repaint();
    }

    /**
     * Extract name crypto string.
     *
     * @param message the message
     * @return the string
     */
    public static String extractNameCrypto(String message) {
        String pattern = "\\[([^\\]]+)\\]";
        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(message);
        if (m.find()) {
            return m.group(1).substring(0, 1).toUpperCase() + m.group(1).substring(1).toLowerCase();
        }
        return null;
    }
}