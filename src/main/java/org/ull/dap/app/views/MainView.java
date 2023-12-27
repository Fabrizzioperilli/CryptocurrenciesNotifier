package org.ull.dap.app.views;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;
import org.ull.dap.app.models.notifiers.Observable;
import org.ull.dap.app.models.users.IObserver;
import org.ull.dap.app.models.users.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainView extends JFrame implements IView {

    public static final String ROUTE_IMAGE_BACKGROUND = "/images/logo_app.png";
    public static final String ROUTE_IMAGE_BITCOIN = "/images/bitcoin.png";
    public static final String ROUTE_IMAGE_ETHEREUM = "/images/ethereum.png";
    public static final String ROUTE_IMAGE_CARDANO = "/images/cardano.png";
    public static final String ROUTE_IMAGE_LITECOIN = "/images/litecoin.png";
    private final List<IObserver> usuarios = new ArrayList<>();
    private final AppController controller;
    private JComboBox<IObserver> comboBox;

    private JList<String> usersList;

    private String[] usersAvailable, usersSelected;
    private JPanel contentPane, panel, pnSelCrypto;
    private JButton btnLogin;
    private JLabel lblNombre, lblFondo, lblBitcoin, lblEthereum, lblCardano, lblLitecoin, lblBitcoinImagen;
    private JLabel lblEthereumImagen, lblCardanoImagen, lblLitecoinImagen, lblUser;

    private JButton btnStart, btnAddBitcoin, btnDeleteBitcoin, btnAddEthereum, btnDeleteEthereum;
    private JButton btnAddCardano, btnDeleteCardano, btnAddLitecoin, btnDeleteLitecoin;

    public MainView(Observable model) {
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
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_BACKGROUND))).getImage());
        getContentPane().setBackground(Color.WHITE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new CardLayout(0, 0));
        contentPane.add(getPanelLogin(), "LOGIN");
        contentPane.add(getPanelSelectCrypto(), "SELECT");
    }

    private JPanel getPanelLogin() {
        if (panel == null) {
            panel = new JPanel();
            panel.setBackground(Color.WHITE);
            panel.setLayout(null);
            panel.add(getLblNombre());
            panel.add(getLblFondo());
            JScrollPane scrollPane = new JScrollPane(getListUsers());
            scrollPane.setBounds(21, 225, 233, 61);
            panel.add(scrollPane);
            panel.add(getBtnLogin());
        }
        return panel;
    }

    public JButton getBtnLogin() {
        if (btnLogin != null) {
            return btnLogin;
        }
        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(0, 255, 0));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnLogin.setBounds(83, 300, 115, 38);
        btnLogin.setActionCommand("LOGIN");
        btnLogin.addActionListener(controller);
        return btnLogin;
    }

    public JList<String> getListUsers() {
        if (usersList != null) {
            return usersList;
        }
        this.usersAvailable = new String[]{"María", "Luis", "Daniela", "Luis"};
        this.usersList = new JList<>(this.usersAvailable);
        usersList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        return usersList;
    }

    @Override
    public String[] getUsersSelected() {
        return this.usersSelected;
    }

    public void setUsersSelected(String[] usersSelected) {
        this.usersSelected = usersSelected;
    }


    public void nextWindow() {
        suscribirUsuarios();
        CardLayout cl = (CardLayout) (contentPane.getLayout());
        cl.show(contentPane, "SELECT");
        rellenarComboBox();
    }

    private void rellenarComboBox() {
        for (IObserver o : usuarios) {
            comboBox.addItem(o);
        }
    }

    private void suscribirUsuarios() {
        for (String user : usersSelected) {
            IObserver usuario = new User(user, 0, new ArrayList<String>());
            this.controller.getNotifier().subscribe(usuario);
            usuarios.add(usuario);
        }
    }

    private JLabel getLblNombre() {
        if (lblNombre == null) {
            lblNombre = new JLabel("Users");
            lblNombre.setFont(new Font("Tahoma", Font.BOLD, 25));
            lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
            lblNombre.setBounds(24, 167, 233, 32);
        }
        return lblNombre;
    }

    private JLabel getLblFondo() {
        if (lblFondo == null) {
            lblFondo = new JLabel("");
            lblFondo.setIcon(resizeIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_BACKGROUND))), 200, 150));
            lblFondo.setBounds(40, -8, 284, 186);
        }
        return lblFondo;
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage(); // Obtiene la imagen del ImageIcon
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Redimensiona la imagen
        return new ImageIcon(newImage); // Crea un nuevo ImageIcon con la imagen redimensionada
    }

    private JPanel getPanelSelectCrypto() {
        if (pnSelCrypto == null) {
            pnSelCrypto = new JPanel();
            pnSelCrypto.setBackground(Color.WHITE);
            pnSelCrypto.setLayout(null);
            pnSelCrypto.add(getComboBox());
            pnSelCrypto.add(getLblBitcoin());
            pnSelCrypto.add(getLblEthereum());
            pnSelCrypto.add(getLblCardano());
            pnSelCrypto.add(getLblLitecoin());
            pnSelCrypto.add(getLblBitcoinImagen());
            pnSelCrypto.add(getLblEthereumImagen());
            pnSelCrypto.add(getLblCardanoImagen());
            pnSelCrypto.add(getLblLitecoinImagen());
            pnSelCrypto.add(getBtnStart());
            pnSelCrypto.add(getBtnAddBitcoin());
            pnSelCrypto.add(getBtnDeleteBitcoin());
            pnSelCrypto.add(getBtnAddEthereum());
            pnSelCrypto.add(getBtnDeleteEthereum());
            pnSelCrypto.add(getBtnAddCardano());
            pnSelCrypto.add(getBtnDeleteCardano());
            pnSelCrypto.add(getBtnAddLitecoin());
            pnSelCrypto.add(getBtnDeleteLitecoin());
            pnSelCrypto.add(getLblUser());
        }
        return pnSelCrypto;
    }

    private JComboBox<IObserver> getComboBox() {
        if (comboBox == null) {
            comboBox = new JComboBox<IObserver>();
            comboBox.setBounds(10, 50, 161, 28);
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    recolocarBotones();
                }
            });
        }
        return comboBox;
    }

    private JLabel getLblBitcoin() {
        if (lblBitcoin != null) {
            return lblBitcoin;
        }
        lblBitcoin = new JLabel("Bitcoin");
        lblBitcoin.setBackground(Color.WHITE);
        lblBitcoin.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblBitcoin.setBounds(10, 92, 100, 22);
        return lblBitcoin;
    }

    private JLabel getLblEthereum() {
        if (lblEthereum != null) {
            return lblEthereum;
        }
        lblEthereum = new JLabel("Ethereum");
        lblEthereum.setBackground(Color.WHITE);
        lblEthereum.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblEthereum.setBounds(10, 152, 100, 22);
        return lblEthereum;
    }

    private JLabel getLblCardano() {
        if (lblCardano != null) {
            return lblCardano;
        }
        lblCardano = new JLabel("Cardano");
        lblCardano.setBackground(Color.WHITE);
        lblCardano.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblCardano.setBounds(10, 214, 100, 22);
        return lblCardano;
    }

    private JLabel getLblLitecoin() {
        if (lblLitecoin != null) {
            return lblLitecoin;
        }
        lblLitecoin = new JLabel("Litecoin");
        lblLitecoin.setBackground(Color.WHITE);
        lblLitecoin.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblLitecoin.setBounds(10, 272, 100, 22);
        return lblLitecoin;
    }

    private JLabel getLblBitcoinImagen() {
        if (lblBitcoinImagen != null) {
            return lblBitcoinImagen;
        }
        lblBitcoinImagen = new JLabel("");
        lblBitcoinImagen.setIcon(resizeIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_BITCOIN))), 53, 48));
        lblBitcoinImagen.setBounds(118, 80, 53, 48);
        return lblBitcoinImagen;
    }

    private JLabel getLblEthereumImagen() {
        if (lblEthereumImagen != null) {
            return lblEthereumImagen;
        }
        lblEthereumImagen = new JLabel("");
        lblEthereumImagen.setIcon(resizeIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_ETHEREUM))), 53, 48));
        lblEthereumImagen.setBounds(118, 144, 53, 48);
        return lblEthereumImagen;
    }

    private JLabel getLblCardanoImagen() {
        if (lblCardanoImagen != null) {
            return lblCardanoImagen;
        }
        lblCardanoImagen = new JLabel("");
        lblCardanoImagen.setIcon(resizeIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_CARDANO))), 53, 48));
        lblCardanoImagen.setBounds(118, 203, 53, 48);
        return lblCardanoImagen;
    }

    private JLabel getLblLitecoinImagen() {
        if (lblLitecoinImagen != null) {
            return lblLitecoinImagen;
        }
        lblLitecoinImagen = new JLabel("");
        lblLitecoinImagen.setIcon(resizeIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_LITECOIN))), 53, 48));
        lblLitecoinImagen.setBounds(118, 261, 53, 48);
        return lblLitecoinImagen;
    }

    private JButton getBtnStart() {
        if (btnStart == null) {
            btnStart = new JButton("Start");
            btnStart.setBackground(Color.WHITE);
            btnStart.setFont(new Font("Tahoma", Font.BOLD, 20));
            btnStart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!comprobarTodosTienenCryptos()) {
                        JOptionPane.showMessageDialog(null, "Debes elegir alguna crypto para cada uno");
                    } else {
                        realizarStartEnSegundoPlano();
                    }
                }
            });
            btnStart.setBounds(183, 25, 92, 54);
        }
        return btnStart;
    }

    private boolean comprobarTodosTienenCryptos() {
        for (IObserver u : usuarios) {
            if (u.getNameCryptos().size() == 0) {
                return false;
            }
        }
        return true;
    }

    private void realizarStartEnSegundoPlano() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                ((CryptocurrencyNotifier) controller.getNotifier()).start();
                return null;
            }

            @Override
            protected void done() {
            }
        };
        worker.execute(); // Inicia el SwingWorker
    }

    private JButton getBtnAddBitcoin() {
        if (btnAddBitcoin == null) {
            btnAddBitcoin = new JButton("+");
            btnAddBitcoin.setBackground(new Color(0, 255, 0));
            btnAddBitcoin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addCrypto("bitcoin");
                }
            });
            btnAddBitcoin.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnAddBitcoin.setBounds(183, 92, 47, 33);
        }
        return btnAddBitcoin;
    }

    private JButton getBtnDeleteBitcoin() {
        if (btnDeleteBitcoin == null) {
            btnDeleteBitcoin = new JButton("-");
            btnDeleteBitcoin.setEnabled(false);
            btnDeleteBitcoin.setBackground(new Color(255, 0, 0));
            btnDeleteBitcoin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    deleteCrypto("bitcoin");
                }
            });
            btnDeleteBitcoin.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnDeleteBitcoin.setBounds(235, 92, 40, 33);
        }
        return btnDeleteBitcoin;
    }

    private JButton getBtnAddEthereum() {
        if (btnAddEthereum == null) {
            btnAddEthereum = new JButton("+");
            btnAddEthereum.setBackground(new Color(0, 255, 0));
            btnAddEthereum.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addCrypto("ethereum");
                }
            });
            btnAddEthereum.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnAddEthereum.setBounds(183, 152, 47, 33);
        }
        return btnAddEthereum;
    }

    private JButton getBtnDeleteEthereum() {
        if (btnDeleteEthereum == null) {
            btnDeleteEthereum = new JButton("-");
            btnDeleteEthereum.setEnabled(false);
            btnDeleteEthereum.setBackground(new Color(255, 0, 0));
            btnDeleteEthereum.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    deleteCrypto("ethereum");
                }
            });
            btnDeleteEthereum.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnDeleteEthereum.setBounds(235, 152, 40, 33);
        }
        return btnDeleteEthereum;
    }

    private JButton getBtnAddCardano() {
        if (btnAddCardano == null) {
            btnAddCardano = new JButton("+");
            btnAddCardano.setBackground(new Color(0, 255, 0));
            btnAddCardano.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addCrypto("cardano");
                }
            });
            btnAddCardano.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnAddCardano.setBounds(183, 214, 47, 33);
        }
        return btnAddCardano;
    }

    private JButton getBtnDeleteCardano() {
        if (btnDeleteCardano == null) {
            btnDeleteCardano = new JButton("-");
            btnDeleteCardano.setEnabled(false);
            btnDeleteCardano.setBackground(new Color(255, 0, 0));
            btnDeleteCardano.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    deleteCrypto("cardano");
                }
            });
            btnDeleteCardano.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnDeleteCardano.setBounds(235, 214, 40, 33);
        }
        return btnDeleteCardano;
    }

    private JButton getBtnAddLitecoin() {
        if (btnAddLitecoin == null) {
            btnAddLitecoin = new JButton("+");
            btnAddLitecoin.setBackground(new Color(0, 255, 0));
            btnAddLitecoin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addCrypto("litecoin");
                }
            });
            btnAddLitecoin.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnAddLitecoin.setBounds(183, 272, 47, 33);
        }
        return btnAddLitecoin;
    }

    private JButton getBtnDeleteLitecoin() {
        if (btnDeleteLitecoin == null) {
            btnDeleteLitecoin = new JButton("-");
            btnDeleteLitecoin.setEnabled(false);
            btnDeleteLitecoin.setBackground(new Color(255, 0, 0));
            btnDeleteLitecoin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    deleteCrypto("litecoin");
                }
            });
            btnDeleteLitecoin.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnDeleteLitecoin.setBounds(235, 272, 40, 33);
        }
        return btnDeleteLitecoin;
    }

    private JLabel getLblUser() {
        if (lblUser == null) {
            lblUser = new JLabel("User");
            lblUser.setForeground(Color.WHITE);
            lblUser.setFont(new Font("Tahoma", Font.BOLD, 17));
            lblUser.setBounds(10, 10, 161, 30);
        }
        return lblUser;
    }

    private void addCrypto(String nombre) {
        for (IObserver o : ((CryptocurrencyNotifier) this.controller.getNotifier()).getObservers()) {
            if (o.equals(comboBox.getSelectedItem())) {
                if (!(o.getNameCryptos().stream().anyMatch(elemento -> elemento.equals(nombre)))) {
                    o.addCrypto(nombre);
                    habilitarBotones(nombre, false);
                }
                break;
            }
        }
    }

    private void deleteCrypto(String nombre) {
        for (IObserver o : ((CryptocurrencyNotifier) this.controller.getNotifier()).getObservers()) {
            if (o.equals(comboBox.getSelectedItem())) {
                if (o.getNameCryptos().stream().anyMatch(elemento -> elemento.equals(nombre))) {
                    o.deleteCrypto(nombre);
                    habilitarBotones(nombre, true);
                }
                break;
            }
        }
    }

    private void habilitarBotones(String nombre, boolean tipo) {
        if (nombre.equals("litecoin")) {
            btnAddLitecoin.setEnabled(tipo);
            btnDeleteLitecoin.setEnabled(!tipo);
        } else if (nombre.equals("bitcoin")) {
            btnAddBitcoin.setEnabled(tipo);
            btnDeleteBitcoin.setEnabled(!tipo);
        } else if (nombre.equals("ethereum")) {
            btnAddEthereum.setEnabled(tipo);
            btnDeleteEthereum.setEnabled(!tipo);
        } else if (nombre.equals("cardano")) {
            btnAddCardano.setEnabled(tipo);
            btnDeleteCardano.setEnabled(!tipo);
        }
    }

    private void recolocarBotones() {
        activarBotones("bitcoin", getBtnAddBitcoin(), getBtnDeleteBitcoin());
        activarBotones("litecoin", getBtnAddLitecoin(), getBtnDeleteLitecoin());
        activarBotones("ethereum", getBtnAddEthereum(), getBtnDeleteEthereum());
        activarBotones("cardano", getBtnAddCardano(), getBtnDeleteCardano());
    }

    private void activarBotones(String name, JButton add, JButton delete) {
        if (((IObserver) comboBox.getSelectedItem()).getNameCryptos().stream().anyMatch(elemento -> elemento.equals(name))) {
            add.setEnabled(false);
            delete.setEnabled(true);
        } else {
            add.setEnabled(true);
            delete.setEnabled(false);
        }
    }

    @Override
    public void update() {

    }
}