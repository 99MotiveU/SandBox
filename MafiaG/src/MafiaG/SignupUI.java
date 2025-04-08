package MafiaG;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Pattern;

public class SignupUI extends JFrame {

    public SignupUI(Runnable onSignupComplete) {
        setTitle("MafiaG");
        ImageIcon icon = new ImageIcon("src/img/logo.png"); // �ΰ� ���
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        JPanel centerBox = new JPanel();
        centerBox.setPreferredSize(new Dimension(560, 600));
        centerBox.setOpaque(false);
        centerBox.setLayout(new BorderLayout());

        JPanel logoPanel = new JPanel();
        logoPanel.setPreferredSize(new Dimension(560, 180));
        logoPanel.setOpaque(false);
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("../../MafiaG_logo.jpg");
        logoLabel.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(230, 160, Image.SCALE_SMOOTH)));
        logoPanel.add(logoLabel);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 1, 0, 10));
        formPanel.setOpaque(false);

        formPanel.add(createInputGroup("���̵�", JTextField.class));
        formPanel.add(createInputGroup("��й�ȣ", JPasswordField.class));
        formPanel.add(createInputGroup("��й�ȣ Ȯ��", JPasswordField.class));
        formPanel.add(createInputGroup("�г���", JTextField.class));
        formPanel.add(createInputGroup("�̸���", JTextField.class));

        // ȸ������ ��ư
        JButton signupButton = new JButton("ȸ������ �Ϸ�");
        signupButton.setPreferredSize(new Dimension(260, 45));
        signupButton.setBackground(new Color(204, 230, 255));
        signupButton.setForeground(new Color(68, 68, 68));
        signupButton.setFont(new Font("���� ���", Font.BOLD, 16));
        signupButton.setFocusPainted(false);
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(179, 218, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(204, 230, 255));
            }
        });

        // �ڷΰ��� ��ư
        JButton backButton = new JButton("�ڷΰ���");
        backButton.setPreferredSize(new Dimension(260, 45));
        backButton.setBackground(new Color(224, 224, 224));
        backButton.setForeground(new Color(68, 68, 68));
        backButton.setFont(new Font("���� ���", Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(200, 200, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(224, 224, 224));
            }
        });

        backButton.addActionListener(e -> {
        	setVisible(false); // â�� ����
        	LoginUI loginUI = new LoginUI();
        	loginUI.showLoginUI();
        	dispose();
        });

        // ȸ������ ��ư ���� ����
        signupButton.addActionListener(e -> {
            String id = ((JTextField)((JPanel) formPanel.getComponent(0)).getComponent(1)).getText();
            String pw = new String(((JPasswordField)((JPanel) formPanel.getComponent(1)).getComponent(1)).getPassword());
            String pwConfirm = new String(((JPasswordField)((JPanel) formPanel.getComponent(2)).getComponent(1)).getPassword());
            String nickname = ((JTextField)((JPanel) formPanel.getComponent(3)).getComponent(1)).getText();
            String email = ((JTextField)((JPanel) formPanel.getComponent(4)).getComponent(1)).getText();

            if (id.length() < 4) {
                JOptionPane.showMessageDialog(SignupUI.this, "���̵�� 4�� �̻��̾�� �մϴ�.");
                return;
            }

            if (pw.length() < 8) {
                JOptionPane.showMessageDialog(SignupUI.this, "��й�ȣ�� 8�� �̻��̾�� �մϴ�.");
                return;
            }

            if (!pw.equals(pwConfirm)) {
                JOptionPane.showMessageDialog(SignupUI.this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                return;
            }

            if (Pattern.compile("[^a-zA-Z0-9��-�R]").matcher(nickname).find()) {
                JOptionPane.showMessageDialog(SignupUI.this, "�г��ӿ��� Ư�����ڸ� ����� �� �����ϴ�.");
                return;
            }

            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(SignupUI.this, "�̸��� ������ �ùٸ��� �ʽ��ϴ�.");
                return;
            }

            boolean success = DB.DatabaseManager.insertNewMember(id, pw, nickname, email);

            if (success) {
                JOptionPane.showMessageDialog(SignupUI.this, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
                dispose();
                onSignupComplete.run();
            } else {
                JOptionPane.showMessageDialog(SignupUI.this, "ȸ�����Կ� �����߽��ϴ�. �ٽ� �õ����ּ���.");
            }
        });

        // ��ư �� �� ���� ����
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        buttonPanel.add(signupButton);

        JPanel formContainer = new JPanel();
        formContainer.setOpaque(false);
        formContainer.setLayout(new BorderLayout(0, 20));
        formContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
        formContainer.add(formPanel, BorderLayout.CENTER);
        formContainer.add(buttonPanel, BorderLayout.SOUTH);

        centerBox.add(logoPanel, BorderLayout.NORTH);
        centerBox.add(formContainer, BorderLayout.CENTER);
        contentPane.add(centerBox);

        setVisible(true);
    }

    private JPanel createInputGroup(String labelText, Class<? extends JComponent> inputType) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, 40));
        label.setFont(new Font("���� ���", Font.BOLD, 14));
        label.setForeground(new Color(51, 51, 51));
        label.setHorizontalAlignment(SwingConstants.LEFT);

        JComponent input;
        if (inputType == JPasswordField.class) {
            input = new JPasswordField();
        } else {
            input = new JTextField();
        }
        input.setPreferredSize(new Dimension(240, 40));
        input.setFont(new Font("���� ���", Font.PLAIN, 16));
        input.setBackground(new Color(227, 232, 236));
        input.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        panel.add(label, BorderLayout.WEST);
        panel.add(input, BorderLayout.CENTER);

        return panel;
    }
}
