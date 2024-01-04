package org.ull.dap.app.views.desktop;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.models.users.IObserver;
import org.ull.dap.app.views.INotification;
import org.ull.dap.app.views.IView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class ViewDesktop extends JFrame implements IView {
    public static final String ROUTE_IMAGE_LOGO = "/images/logo_app.png";
    private JLabel lblTitle, lblLogo, lblUser;
    private JButton btnStart,btnLogin;
    private JComboBox<String> comboBoxUsersSelected;
    private JList<String> usersList;
    private JPanel contentPane, panel, pnSelCrypto;
    private final AppController controller;
    private List<String> usersSelected;
    private List<String> usersAvailable;
    private List <INotification> notifications;
    private Map<String, JButton> addButtonMap = new HashMap<>();
    private Map<String, JButton> deleteButtonMap = new HashMap<>();



    private DashboardDesktop dashboardDesktop;

    public ViewDesktop(CryptocurrencyNotifier model) {
        this.controller = new AppController(model, this);
        this.notifications = new ArrayList<>();
        this.usersAvailable = controller.getUsersAvailable();
        this.dashboardDesktop = new DashboardDesktop("Dashboard CryptoNotifier");
        initializeUI();
    }

    private void initializeUI() {
        setTitle("CryptoNotifier");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 340, 440);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_LOGO))).getImage());

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new CardLayout(0, 0));
        contentPane.add(getPanelLogin(), "LOGIN");
        contentPane.add(getPanelSelectCrypto(), "SELECT_CRYPTO");
    }

    private JLabel getLblLogo() {
        if (lblLogo != null) {
            return lblLogo;
        }
        lblLogo = new JLabel("");
        lblLogo.setIcon(resizeImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_LOGO))), 200, 150));
        lblLogo.setBounds(56, -8, 284, 186);
        return lblLogo;
    }

    private JLabel getLblTitle() {
        if (lblTitle != null) {
            return lblTitle;
        }
        lblTitle = new JLabel("CryptoNotifier");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(45, 167, 233, 32);
        return lblTitle;
    }

    private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Redimensiona la imagen
        return new ImageIcon(newImage);
    }

    public JButton getBtnLogin() {
        if (btnLogin != null) {
            return btnLogin;
        }
        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(0, 255, 0));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnLogin.setBounds(110, 300, 115, 38);
        btnLogin.setActionCommand("LOGIN");
        btnLogin.addActionListener(controller);
        return btnLogin;
    }

    public JList<String> getListUsers() {
        if (usersList != null) {
            return usersList;
        }
        this.usersList = new JList<>(usersAvailable.toArray(new String[0]));
        usersList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        return usersList;
    }

    public JPanel getPanelLogin() {
        if (panel != null) {
            return panel;
        }
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.add(getLblTitle());
        panel.add(getLblLogo());
        JScrollPane scrollPane = new JScrollPane(getListUsers());
        scrollPane.setBounds(70, 225, 180, 60);
        panel.add(scrollPane);
        panel.add(getBtnLogin());
        return panel;
    }

    @Override
    public void windowSelectCryptos() {
        CardLayout cl = (CardLayout) (contentPane.getLayout());
        cl.show(contentPane, "SELECT_CRYPTO");
        fillComboBoxUsers();
    }

    private void fillComboBoxUsers() {
        for (String u : usersSelected) {
            comboBoxUsersSelected.addItem(u);
        }
    }

    private JPanel getPanelSelectCrypto() {
        if (pnSelCrypto != null) {
            return pnSelCrypto;
        }
        pnSelCrypto = new JPanel();
        pnSelCrypto.setBackground(Color.WHITE);
        pnSelCrypto.setLayout(null);
        pnSelCrypto.add(getLblUser());
        pnSelCrypto.add(getComboBoxUsersSelected());
        pnSelCrypto.add(displayCryptos());
        pnSelCrypto.add(getBtnStart());
        return pnSelCrypto;
    }

    private JScrollPane displayCryptos() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(100, 0, 0, 0));
        for (Map.Entry<String, String> entry : controller.getNotifier().getCryptoNameImage().entrySet()) {
            String nameCrypto = entry.getKey();
            String imageCrypto = entry.getValue();
            panel.add(createItemCrypto(nameCrypto, imageCrypto));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(5, 115, 305, 250);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBorder(null);

        return scrollPane;
    }

    private JPanel createItemCrypto(String nameCrypto, String linkImage) {
        JButton btnAdd = new JButton("+");
        btnAdd.setBackground(new Color(0, 255, 0));
        btnAdd.setActionCommand("ADD_" + nameCrypto.toUpperCase());
        btnAdd.setAlignmentX(Component.RIGHT_ALIGNMENT);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));

        JButton btnDelete = new JButton("-");
        btnDelete.setEnabled(false);
        btnDelete.setBackground(new Color(255, 0, 0));
        btnDelete.setActionCommand("DELETE_" + nameCrypto.toUpperCase());
        btnDelete.setAlignmentX(Component.RIGHT_ALIGNMENT);
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));

        btnAdd.addActionListener(controller);
        btnDelete.addActionListener(controller);

        addButtonMap.put(nameCrypto.toLowerCase(), btnAdd);
        deleteButtonMap.put(nameCrypto.toLowerCase(), btnDelete);

        JLabel labelNameCrypto = new JLabel(nameCrypto + " ");
        labelNameCrypto.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelNameCrypto.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel imageCrypto = createImageCrypto(linkImage);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnDelete.setPreferredSize(new Dimension(50, 30));
        btnAdd.setPreferredSize(new Dimension(50, 30));

        panel.add(labelNameCrypto);
        panel.add(imageCrypto);
        panel.add(btnAdd);
        panel.add(btnDelete);

        return panel;
    }

    private JLabel createImageCrypto(String imageUrl) {
        JLabel imageLabel = new JLabel();
        try {
            BufferedImage image = ImageIO.read(new URL(imageUrl));
            Image scaledImage = image.getScaledInstance(30,30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            imageLabel.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageLabel;
    }

    private JLabel getLblUser() {
        if (lblUser != null) {
            return lblUser;
        }
        lblUser = new JLabel("User");
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblUser.setBounds(20, 10, 161, 30);
        return lblUser;
    }

    public JComboBox<String> getComboBoxUsersSelected() {
        if (comboBoxUsersSelected != null) {
            return comboBoxUsersSelected;
        }
        comboBoxUsersSelected = new JComboBox<>();
        comboBoxUsersSelected.setBounds(25, 50, 161, 28);
        comboBoxUsersSelected.addActionListener(e -> eventButtons());
        return comboBoxUsersSelected;
    }

    public String getUserComboBoxString() {
        return (String)comboBoxUsersSelected.getSelectedItem();
    }

    @Override
    public List<String> getUsersSelected() {
        this.usersSelected = getListUsers().getSelectedValuesList();
        return usersSelected;
    }

    @Override
    public List<INotification> getNotifications() {
        if (usersSelected != null) {
            for (int i = 0; i < usersSelected.size(); i++) {
                this.notifications.add(new NotificationDesktop());
            }
        }
        return notifications;
    }

    public void setUsersSelected(List<String> usersSelected) {
        this.usersSelected = usersSelected;
    }

    public void setDashboardDesktop(DashboardDesktop dashboardDesktop) {
        this.dashboardDesktop = dashboardDesktop;
    }

    public DashboardDesktop getDashboardDesktop() {
        return dashboardDesktop;
    }

    private JButton getBtnStart() {
        if (btnStart != null) {
            return btnStart;
        }
        btnStart = new JButton("Start");
        btnStart.setBackground(Color.CYAN);
        btnStart.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnStart.setActionCommand("START");
        btnStart.addActionListener(controller);
        btnStart.setBounds(205, 35, 92, 54);

        return btnStart;
    }

    public void enableButtons(String nombre, boolean tipo) {
        JButton addButton = addButtonMap.get(nombre);
        JButton deleteButton = deleteButtonMap.get(nombre);

        if (addButton != null && deleteButton != null) {
            addButton.setEnabled(tipo);
            deleteButton.setEnabled(!tipo);
        }
    }

    private void eventButtons() {
        for (String cryptoName : controller.getNotifier().getNamesCryptocurrencies()) {
            activateButtons(cryptoName);
        }
    }

    private void activateButtons(String name) {
        JButton addButton = addButtonMap.get(name);
        JButton deleteButton = deleteButtonMap.get(name);

        if (addButton != null && deleteButton != null) {
            for (IObserver o : this.controller.getNotifier().getObservers()) {
                if (o.getName().equals(comboBoxUsersSelected.getSelectedItem())) {
                    if (o.getNameCryptos().contains(name)) {
                        addButton.setEnabled(false);
                        deleteButton.setEnabled(true);
                    } else {
                        addButton.setEnabled(true);
                        deleteButton.setEnabled(false);
                    }
                    break;
                }
            }
        }
    }

}