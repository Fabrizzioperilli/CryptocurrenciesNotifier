package org.ull.dap.app.views;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.models.users.IObserver;
import org.ull.dap.app.models.users.User;

import javax.swing.*;
import java.awt.*;

import java.util.Objects;

public class MainView extends JFrame implements IView {

    public static final String ROUTE_IMAGE_LOGO = "/images/logo_app.png";
    public static final String ROUTE_IMAGE_BITCOIN = "/images/bitcoin.png";
    public static final String ROUTE_IMAGE_ETHEREUM = "/images/ethereum.png";
    public static final String ROUTE_IMAGE_CARDANO = "/images/cardano.png";
    public static final String ROUTE_IMAGE_LITECOIN = "/images/litecoin.png";
    private JLabel lblTitle, lblLogo, lblBitcoin, lblEthereum, lblCardano, lblLitecoin, lblBitcoinImagen;
    private JLabel lblEthereumImagen, lblCardanoImagen, lblLitecoinImagen, lblUser;
    private JButton btnStart, btnAddBitcoin, btnDeleteBitcoin, btnAddEthereum, btnDeleteEthereum;
    private JButton btnAddCardano, btnDeleteCardano, btnAddLitecoin, btnDeleteLitecoin, btnLogin;
    private JComboBox<String> comboBoxUsersSelected;
    private JList<String> usersList;
    private JPanel contentPane, panel, pnSelCrypto;
    private final AppController controller;
    private String[] usersSelected;

    public MainView(CryptocurrencyNotifier model) {
        this.controller = new AppController(model, this);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("CryptoNotifier");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 308, 397);
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
        lblLogo.setBounds(40, -8, 284, 186);
        return lblLogo;
    }

    private JLabel getLblTitle() {
        if (lblTitle != null) {
            return lblTitle;
        }
        lblTitle = new JLabel("Users");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(30, 167, 233, 32);
        return lblTitle;
    }

    private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image image = icon.getImage(); // Obtiene la imagen del ImageIcon
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Redimensiona la imagen
        return new ImageIcon(newImage); // Crea un nuevo ImageIcon con la imagen redimensionada
    }

    public JButton getBtnLogin() {
        if (btnLogin != null) {
            return btnLogin;
        }
        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(0, 255, 0));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnLogin.setBounds(93, 300, 115, 38);
        btnLogin.setActionCommand("LOGIN");
        btnLogin.addActionListener(controller);
        return btnLogin;
    }

    public JList<String> getListUsers() {
        if (usersList != null) {
            return usersList;
        }
        String[] usersAvailable = new String[]{"María", "Luis", "Daniela", "Luis"};
        this.usersList = new JList<>(usersAvailable);
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
        scrollPane.setBounds(60, 225, 180, 60);
        panel.add(scrollPane);
        panel.add(getBtnLogin());
        return panel;
    }

    public void nextWindow() {
        controller.suscribeUsers(usersSelected);
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
        pnSelCrypto.add(getLblBitcoin());
        pnSelCrypto.add(getLblEthereum());
        pnSelCrypto.add(getLblCardano());
        pnSelCrypto.add(getLblLitecoin());
        pnSelCrypto.add(getLblBitcoinImage());
        pnSelCrypto.add(getLblEthereumImage());
        pnSelCrypto.add(getLblCardanoImage());
        pnSelCrypto.add(getLblLitecoinImage());
        pnSelCrypto.add(getBtnAddBitcoin());
        pnSelCrypto.add(getBtnDeleteBitcoin());
        pnSelCrypto.add(getBtnAddEthereum());
        pnSelCrypto.add(getBtnDeleteEthereum());
        pnSelCrypto.add(getBtnAddCardano());
        pnSelCrypto.add(getBtnDeleteCardano());
        pnSelCrypto.add(getBtnAddLitecoin());
        pnSelCrypto.add(getBtnDeleteLitecoin());
        pnSelCrypto.add(getBtnStart());
        return pnSelCrypto;
    }

    private JLabel getLblUser() {
        if (lblUser != null) {
            return lblUser;
        }
        lblUser = new JLabel("User");
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblUser.setBounds(10, 10, 161, 30);
        return lblUser;
    }

    public JComboBox<String> getComboBoxUsersSelected() {
        if (comboBoxUsersSelected != null) {
            return comboBoxUsersSelected;
        }
        comboBoxUsersSelected = new JComboBox<>();
        comboBoxUsersSelected.setBounds(10, 50, 161, 28);
        comboBoxUsersSelected.addActionListener(e -> eventButtons());
        return comboBoxUsersSelected;
    }
    private JLabel getLblBitcoin() {
        if (lblBitcoin != null) {
            return lblBitcoin;
        }
        lblBitcoin = new JLabel("Bitcoin");
        lblBitcoin.setBackground(Color.WHITE);
        lblBitcoin.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblBitcoin.setBounds(10, 102, 100, 22);
        return lblBitcoin;
    }

    private JLabel getLblEthereum() {
        if (lblEthereum != null) {
            return lblEthereum;
        }
        lblEthereum = new JLabel("Ethereum");
        lblEthereum.setBackground(Color.WHITE);
        lblEthereum.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblEthereum.setBounds(10, 162, 100, 22);
        return lblEthereum;
    }

    private JLabel getLblCardano() {
        if (lblCardano != null) {
            return lblCardano;
        }
        lblCardano = new JLabel("Cardano");
        lblCardano.setBackground(Color.WHITE);
        lblCardano.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblCardano.setBounds(10, 224, 100, 22);
        return lblCardano;
    }

    private JLabel getLblLitecoin() {
        if (lblLitecoin != null) {
            return lblLitecoin;
        }
        lblLitecoin = new JLabel("Litecoin");
        lblLitecoin.setBackground(Color.WHITE);
        lblLitecoin.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblLitecoin.setBounds(10, 282, 100, 22);
        return lblLitecoin;
    }

    private JLabel getLblBitcoinImage() {
        if (lblBitcoinImagen != null) {
            return lblBitcoinImagen;
        }
        lblBitcoinImagen = new JLabel("");
        lblBitcoinImagen.setIcon(resizeImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_BITCOIN))), 53, 48));
        lblBitcoinImagen.setBounds(118, 100, 53, 48);
        return lblBitcoinImagen;
    }

    private JLabel getLblEthereumImage() {
        if (lblEthereumImagen != null) {
            return lblEthereumImagen;
        }
        lblEthereumImagen = new JLabel("");
        lblEthereumImagen.setIcon(resizeImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_ETHEREUM))), 53, 48));
        lblEthereumImagen.setBounds(118, 164, 53, 48);
        return lblEthereumImagen;
    }

    private JLabel getLblCardanoImage() {
        if (lblCardanoImagen != null) {
            return lblCardanoImagen;
        }
        lblCardanoImagen = new JLabel("");
        lblCardanoImagen.setIcon(resizeImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_CARDANO))), 53, 48));
        lblCardanoImagen.setBounds(118, 223, 53, 48);
        return lblCardanoImagen;
    }

    private JLabel getLblLitecoinImage() {
        if (lblLitecoinImagen != null) {
            return lblLitecoinImagen;
        }
        lblLitecoinImagen = new JLabel("");
        lblLitecoinImagen.setIcon(resizeImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_LITECOIN))), 53, 48));
        lblLitecoinImagen.setBounds(118, 281, 53, 48);
        return lblLitecoinImagen;
    }

    private JButton getBtnAddBitcoin() {
        if (btnAddBitcoin != null) {
            return btnAddBitcoin;
        }
        btnAddBitcoin = new JButton("+");
        btnAddBitcoin.setBackground(new Color(0, 255, 0));
        btnAddBitcoin.setActionCommand("ADD_BITCOIN");
        btnAddBitcoin.addActionListener(controller);
        btnAddBitcoin.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAddBitcoin.setBounds(183, 112, 47, 33);
        return btnAddBitcoin;
    }

    private JButton getBtnDeleteBitcoin() {
        if (btnDeleteBitcoin != null) {
            return btnDeleteBitcoin;
        }
        btnDeleteBitcoin = new JButton("-");
        btnDeleteBitcoin.setEnabled(false);
        btnDeleteBitcoin.setBackground(new Color(255, 0, 0));
        btnDeleteBitcoin.setActionCommand("DELETE_BITCOIN");
        btnDeleteBitcoin.addActionListener(controller);
        btnDeleteBitcoin.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDeleteBitcoin.setBounds(235, 112, 40, 33);
        return btnDeleteBitcoin;
    }

    private JButton getBtnAddEthereum() {
        if (btnAddEthereum != null) {
            return btnAddEthereum;
        }
        btnAddEthereum = new JButton("+");
        btnAddEthereum.setBackground(new Color(0, 255, 0));
        btnAddEthereum.setActionCommand("ADD_ETHEREUM");
        btnAddEthereum.addActionListener(controller);
        btnAddEthereum.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAddEthereum.setBounds(183, 172, 47, 33);
        return btnAddEthereum;
    }

    private JButton getBtnDeleteEthereum() {
        if (btnDeleteEthereum != null) {
            return btnDeleteEthereum;
        }
        btnDeleteEthereum = new JButton("-");
        btnDeleteEthereum.setEnabled(false);
        btnDeleteEthereum.setBackground(new Color(255, 0, 0));
        btnDeleteEthereum.setActionCommand("DELETE_ETHEREUM");
        btnDeleteEthereum.addActionListener(controller);
        btnDeleteEthereum.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDeleteEthereum.setBounds(235, 172, 40, 33);
        return btnDeleteEthereum;
    }

    private JButton getBtnAddCardano() {
        if (btnAddCardano != null) {
            return btnAddCardano;
        }
        btnAddCardano = new JButton("+");
        btnAddCardano.setBackground(new Color(0, 255, 0));
        btnAddCardano.setActionCommand("ADD_CARDANO");
        btnAddCardano.addActionListener(controller);
        btnAddCardano.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAddCardano.setBounds(183, 234, 47, 33);
        return btnAddCardano;
    }

    private JButton getBtnDeleteCardano() {
        if (btnDeleteCardano != null) {
            return btnDeleteCardano;
        }
        btnDeleteCardano = new JButton("-");
        btnDeleteCardano.setEnabled(false);
        btnDeleteCardano.setBackground(new Color(255, 0, 0));
        btnDeleteCardano.setActionCommand("DELETE_CARDANO");
        btnDeleteCardano.addActionListener(controller);
        btnDeleteCardano.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDeleteCardano.setBounds(235, 234, 40, 33);
        return btnDeleteCardano;
    }

    private JButton getBtnAddLitecoin() {
        if (btnAddLitecoin != null) {
            return btnAddLitecoin;
        }
        btnAddLitecoin = new JButton("+");
        btnAddLitecoin.setBackground(new Color(0, 255, 0));
        btnAddLitecoin.setActionCommand("ADD_LITECOIN");
        btnAddLitecoin.addActionListener(controller);
        btnAddLitecoin.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAddLitecoin.setBounds(183, 292, 47, 33);
        return btnAddLitecoin;
    }

    private JButton getBtnDeleteLitecoin() {
        if (btnDeleteLitecoin != null) {
            return btnDeleteLitecoin;
        }
        btnDeleteLitecoin = new JButton("-");
        btnDeleteLitecoin.setEnabled(false);
        btnDeleteLitecoin.setBackground(new Color(255, 0, 0));
        btnDeleteLitecoin.setActionCommand("DELETE_LITECOIN");
        btnDeleteLitecoin.addActionListener(controller);
        btnDeleteLitecoin.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDeleteLitecoin.setBounds(235, 292, 40, 33);
        return btnDeleteLitecoin;
    }


    @Override
    public String[] getUsersSelected() {
        return this.usersSelected;
    }

    public void setUsersSelected(String[] usersSelected) {
        this.usersSelected = usersSelected;
    }

    private JButton getBtnStart() {
        if (btnStart != null) {
            return btnStart;
        }
        btnStart = new JButton("Start");
        btnStart.setBackground(Color.WHITE);
        btnStart.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnStart.setActionCommand("START");
        btnStart.addActionListener(controller);
        btnStart.setBounds(183, 25, 92, 54);
        return btnStart;
    }

    public void enableButtons(String nombre, boolean tipo) {
        switch (nombre) {
            case "litecoin" -> {
                btnAddLitecoin.setEnabled(tipo);
                btnDeleteLitecoin.setEnabled(!tipo);
            }
            case "bitcoin" -> {
                btnAddBitcoin.setEnabled(tipo);
                btnDeleteBitcoin.setEnabled(!tipo);
            }
            case "ethereum" -> {
                btnAddEthereum.setEnabled(tipo);
                btnDeleteEthereum.setEnabled(!tipo);
            }
            case "cardano" -> {
                btnAddCardano.setEnabled(tipo);
                btnDeleteCardano.setEnabled(!tipo);
            }
        }
    }

    private void eventButtons() {
        activateButtons("bitcoin", getBtnAddBitcoin(), getBtnDeleteBitcoin());
        activateButtons("litecoin", getBtnAddLitecoin(), getBtnDeleteLitecoin());
        activateButtons("ethereum", getBtnAddEthereum(), getBtnDeleteEthereum());
        activateButtons("cardano", getBtnAddCardano(), getBtnDeleteCardano());
    }

    private void activateButtons(String name, JButton addButton, JButton deleteButton) {
        for (IObserver o: this.controller.getNotifier().getObservers()) {
            if (((User) o).getName().equals(comboBoxUsersSelected.getSelectedItem())) {
                if (o.getNameCryptos().stream().anyMatch(e -> e.equals(name))) {
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

    @Override
    public void update() {

    }
}