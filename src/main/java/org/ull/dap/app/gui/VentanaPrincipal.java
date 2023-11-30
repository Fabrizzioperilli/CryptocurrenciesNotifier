package org.ull.dap.app.gui;

import org.ull.dap.app.notifier.CryptocurrencyNotifier;
import org.ull.dap.app.notifier.Observable;
import org.ull.dap.app.user.IObserver;
import org.ull.dap.app.user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private List<IObserver> usuarios = new ArrayList<>();
    private JPanel contentPane;
    private JPanel panel;
    private JButton btnLogin;
    private JLabel lblNombre;
    private JLabel lblFondo;
    private JCheckBox chckbxUser1;
    private JCheckBox chckbxUser2;
    private JCheckBox chckbxUser3;
    private Observable cryptocurrencyNotifier;
    private JPanel pnSelCrypto;
    private JComboBox<IObserver> comboBox;
    private JLabel lblBitcoin;
    private JLabel lblEthereum;
    private JLabel lblCardano;
    private JLabel lblLitecoin;
    private JLabel lblBitcoinImagen;
    private JLabel lblEthereumImagen;
    private JLabel lblCardanoImagen;
    private JLabel lblLitecoinImagen;
    private JButton btnStart;
    private JButton btnAddBitcoin;
    private JButton btnDeleteBitcoin;
    private JButton btnAddEthereum;
    private JButton btnDeleteEthereum;
    private JButton btnAddCardano;
    private JButton btnDeleteCardano;
    private JButton btnAddLitecoin;
    private JButton btnDeleteLitecoin;
    private JLabel lblUser;
    public VentanaPrincipal(Observable cryptocurrencyNotifier) {
        this.cryptocurrencyNotifier = cryptocurrencyNotifier;
        setTitle("CryptoNotifier");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 308, 367);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new CardLayout(0, 0));
        contentPane.add(getPanel(), "LOGIN");
        contentPane.add(getPnSelCrypto(), "SELECT");
    }

    private JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.setBackground(new Color(0, 0, 128));
            panel.setLayout(null);
            panel.add(getBtnLogin());
            panel.add(getLblNombre());
            panel.add(getLblFondo());
            panel.add(getChckbxUser1());
            panel.add(getChckbxUser2());
            panel.add(getChckbxUser3());
        }
        return panel;
    }
    private JButton getBtnLogin() {
        if (btnLogin == null) {
            btnLogin = new JButton("Login");
            btnLogin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    pasarSiguientePestaña();
                }
            });
            btnLogin.setBackground(new Color(0, 255, 0));
            btnLogin.setForeground(new Color(255, 0, 0));
            btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
            btnLogin.setBounds(83, 260, 115, 38);
        }
        return btnLogin;
    }

    private void pasarSiguientePestaña(){
        suscribirUsuarios();
        CardLayout cl = (CardLayout) (contentPane.getLayout());
        cl.show(contentPane, "SELECT");
        rellenarComboBox();
    }

    private void rellenarComboBox(){
        for (IObserver o:usuarios) {
            comboBox.addItem(o);
        }
    }
    private void suscribirUsuarios(){
        if (chckbxUser1.isSelected()){
            IObserver user1 = new User("Javier", 3, new ArrayList<String>());
            cryptocurrencyNotifier.subscribe(user1);
            usuarios.add(user1);
        }
        if (chckbxUser2.isSelected()){
            IObserver user2 = new User("Joan", 1, new ArrayList<String>());
            cryptocurrencyNotifier.subscribe(user2);
            usuarios.add(user2);
        }
        if (chckbxUser3.isSelected()){
            IObserver user3 = new User("Fabrizzio", 4, new ArrayList<String>());
            cryptocurrencyNotifier.subscribe(user3);
            usuarios.add(user3);
        }
    }
    private JLabel getLblNombre() {
        if (lblNombre == null) {
            lblNombre = new JLabel("Log users");
            lblNombre.setForeground(new Color(255, 0, 0));
            lblNombre.setBackground(new Color(255, 0, 0));
            lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
            lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
            lblNombre.setBounds(20, 187, 233, 22);
        }
        return lblNombre;
    }
    private JLabel getLblFondo() {
        if (lblFondo == null) {
            lblFondo = new JLabel("");
            lblFondo.setIcon(resizeIcon(new ImageIcon("src/main/java/org/ull/dap/app/gui/img/grafico.png"),288,200));
            lblFondo.setBounds(0, -8, 284, 186);
        }
        return lblFondo;
    }
    public ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage(); // Obtiene la imagen del ImageIcon
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Redimensiona la imagen
        return new ImageIcon(newImage); // Crea un nuevo ImageIcon con la imagen redimensionada
    }
    private JCheckBox getChckbxUser1() {
        if (chckbxUser1 == null) {
            chckbxUser1 = new JCheckBox("Javier");
            chckbxUser1.setBackground(new Color(0, 255, 0));
            chckbxUser1.setForeground(new Color(255, 0, 0));
            chckbxUser1.setBounds(21, 225, 64, 21);
        }
        return chckbxUser1;
    }
    private JCheckBox getChckbxUser2() {
        if (chckbxUser2 == null) {
            chckbxUser2 = new JCheckBox("Joan");
            chckbxUser2.setBackground(new Color(0, 255, 0));
            chckbxUser2.setForeground(new Color(255, 0, 0));
            chckbxUser2.setBounds(102, 225, 64, 21);
        }
        return chckbxUser2;
    }
    private JCheckBox getChckbxUser3() {
        if (chckbxUser3 == null) {
            chckbxUser3 = new JCheckBox("Fabricio");
            chckbxUser3.setBackground(new Color(0, 255, 0));
            chckbxUser3.setForeground(new Color(255, 0, 0));
            chckbxUser3.setBounds(182, 225, 71, 21);
        }
        return chckbxUser3;
    }
    private JPanel getPnSelCrypto() {
        if (pnSelCrypto == null) {
            pnSelCrypto = new JPanel();
            pnSelCrypto.setBackground(new Color(0, 0, 128));
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
        if (lblBitcoin == null) {
            lblBitcoin = new JLabel("Bitcoin");
            lblBitcoin.setForeground(Color.WHITE);
            lblBitcoin.setBackground(Color.WHITE);
            lblBitcoin.setFont(new Font("Tahoma", Font.PLAIN, 17));
            lblBitcoin.setBounds(10, 92, 100, 22);
        }
        return lblBitcoin;
    }
    private JLabel getLblEthereum() {
        if (lblEthereum == null) {
            lblEthereum = new JLabel("Ethereum");
            lblEthereum.setForeground(Color.WHITE);
            lblEthereum.setBackground(Color.WHITE);
            lblEthereum.setFont(new Font("Tahoma", Font.PLAIN, 17));
            lblEthereum.setBounds(10, 152, 100, 22);
        }
        return lblEthereum;
    }
    private JLabel getLblCardano() {
        if (lblCardano == null) {
            lblCardano = new JLabel("Cardano");
            lblCardano.setForeground(Color.WHITE);
            lblCardano.setBackground(Color.WHITE);
            lblCardano.setFont(new Font("Tahoma", Font.PLAIN, 17));
            lblCardano.setBounds(10, 214, 100, 22);
        }
        return lblCardano;
    }
    private JLabel getLblLitecoin() {
        if (lblLitecoin == null) {
            lblLitecoin = new JLabel("Litecoin");
            lblLitecoin.setForeground(Color.WHITE);
            lblLitecoin.setBackground(Color.WHITE);
            lblLitecoin.setFont(new Font("Tahoma", Font.PLAIN, 17));
            lblLitecoin.setBounds(10, 272, 100, 22);
        }
        return lblLitecoin;
    }
    private JLabel getLblBitcoinImagen() {
        if (lblBitcoinImagen == null) {
            lblBitcoinImagen = new JLabel("");
            lblBitcoinImagen.setIcon(resizeIcon(new ImageIcon("src/main/java/org/ull/dap/app/gui/img/bitcoin.png"),53,48));
            lblBitcoinImagen.setBounds(118, 80, 53, 48);
        }
        return lblBitcoinImagen;
    }
    private JLabel getLblEthereumImagen() {
        if (lblEthereumImagen == null) {
            lblEthereumImagen = new JLabel("");
            lblEthereumImagen.setIcon(resizeIcon(new ImageIcon("src/main/java/org/ull/dap/app/gui/img/ethereum.png"),53,48));
            lblEthereumImagen.setBounds(118, 144, 53, 48);
        }
        return lblEthereumImagen;
    }
    private JLabel getLblCardanoImagen() {
        if (lblCardanoImagen == null) {
            lblCardanoImagen = new JLabel("");
            lblCardanoImagen.setIcon(resizeIcon(new ImageIcon("src/main/java/org/ull/dap/app/gui/img/cardano.png"),53,48));
            lblCardanoImagen.setBounds(118, 203, 53, 48);
        }
        return lblCardanoImagen;
    }
    private JLabel getLblLitecoinImagen() {
        if (lblLitecoinImagen == null) {
            lblLitecoinImagen = new JLabel("");
            lblLitecoinImagen.setIcon(resizeIcon(new ImageIcon("src/main/java/org/ull/dap/app/gui/img/litecoin.png"),53,48));
            lblLitecoinImagen.setBounds(118, 261, 53, 48);
        }
        return lblLitecoinImagen;
    }
    private JButton getBtnStart() {
        if (btnStart == null) {
            btnStart = new JButton("Start");
            btnStart.setBackground(Color.WHITE);
            btnStart.setFont(new Font("Tahoma", Font.BOLD, 20));
            btnStart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    realizarStartEnSegundoPlano();
                }
            });
            btnStart.setBounds(183, 25, 92, 54);
        }
        return btnStart;
    }

    private void realizarStartEnSegundoPlano() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                ((CryptocurrencyNotifier)cryptocurrencyNotifier).start();
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

    private void addCrypto(String nombre){
        for (IObserver o: ((CryptocurrencyNotifier)cryptocurrencyNotifier).getObservers()){
            if (o.equals(((IObserver)comboBox.getSelectedItem()))){
                if (!(o.getNameCryptos().stream().anyMatch(elemento -> elemento.equals(nombre)))){
                    o.addCrypto(nombre);
                    habilitarBotones(nombre, false);
                }
                break;
            }
        }
    }

    private void deleteCrypto(String nombre){
        for (IObserver o: ((CryptocurrencyNotifier)cryptocurrencyNotifier).getObservers()){
            if (o.equals(((IObserver)comboBox.getSelectedItem()))){
                if (o.getNameCryptos().stream().anyMatch(elemento -> elemento.equals(nombre))){
                    o.deleteCrypto(nombre);
                    habilitarBotones(nombre, true);
                }
                break;
            }
        }
    }

    private void habilitarBotones(String nombre, boolean tipo){
        if (nombre.equals("litecoin")){
            btnAddLitecoin.setEnabled(tipo);
            btnDeleteLitecoin.setEnabled(!tipo);
        }else if(nombre.equals("bitcoin")){
            btnAddBitcoin.setEnabled(tipo);
            btnDeleteBitcoin.setEnabled(!tipo);
        }else if(nombre.equals("ethereum")){
            btnAddEthereum.setEnabled(tipo);
            btnDeleteEthereum.setEnabled(!tipo);
        }else if(nombre.equals("cardano")){
            btnAddCardano.setEnabled(tipo);
            btnDeleteCardano.setEnabled(!tipo);
        }
    }

    private void recolocarBotones(){
        activarBotones("bitcoin",getBtnAddBitcoin(),getBtnDeleteBitcoin());
        activarBotones("litecoin",getBtnAddLitecoin(),getBtnDeleteLitecoin());
        activarBotones("ethereum",getBtnAddEthereum(),getBtnDeleteEthereum());
        activarBotones("cardano",getBtnAddCardano(),getBtnDeleteCardano());
    }

    private void activarBotones(String name, JButton add, JButton delete){
        if(((IObserver)comboBox.getSelectedItem()).getNameCryptos().stream().anyMatch(elemento -> elemento.equals(name))){
            add.setEnabled(false); delete.setEnabled(true);
        }else{
            add.setEnabled(true); delete.setEnabled(false);
        }
    }
}