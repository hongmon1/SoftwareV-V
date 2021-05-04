import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class MyFrame extends JFrame {
    int inputNum = 0;
    int inputTemp = 0;
    JTextField inputText = new JTextField("          ", SwingConstants.CENTER); // ����� �Է��� �޴� �ؽ�Ʈ �ʵ�;

    String[] num_list = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "��", "Ȯ��"};
    JButton[] dialButton_list = new JButton[12];
    ArrayList<JLabel> labelList = new ArrayList<JLabel>();
    List imageList = new List();
    String[] dvmList = {"dvm1", "dvm3", "dvm6", "", "", "", "", ""};

    JPanel pDial = new JPanel();
    JPanel panelDown = new JPanel();
    JPanel pTemp = new JPanel();
    JPanel pScreen = new JPanel();
    JPanel pInput = new JPanel();

    GridBagLayout grid = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();


    MyFrame() {
        init();
    }


    private void init() {
        setTitle("�ʱ�ȭ��");
        setLayout(new GridLayout(2, 1)); // ��ü ȭ���� �׸������·� ��(��ũ��) �Ʒ�(��ư) ����

        pDial.setLayout(grid);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        setDialButton(dialButton_list);

        // JPanel�� ��ư�� add
        for (int i = 0; i < num_list.length; i++) {
            pDial.add(dialButton_list[i]);
        }

        showAccessibleDVMList(pScreen, dvmList);
        // ��Ʈ �����ӿ� screen JPanel add
        panelDown.setLayout(grid);
        // ��Ʈ �����ӿ� button�� JPanel add

        pInput.setLayout(new BorderLayout());
        pInput.add(inputText,BorderLayout.CENTER);

        gbc(pTemp, 0, 0, 4, 1);
        gbc(pInput, 0, 1, 4, 1);
        gbc(pDial, 0, 2, 4, 4);

        pInput.setBackground(Color.GRAY);

        panelDown.add(pTemp);
        panelDown.add(pInput);
        panelDown.add(pDial);
        add(pScreen);
        add(panelDown);

        // ------------------------------------------------
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �������� x��ư�� Ȱ��ȭ�Ͽ� �ݱ��ư�� ���డ������

        setSize(600, 800); // ������ ������ ����

        setVisible(true); // �������� ���̰� ��

        setLocationRelativeTo(null); // ������ ����� ��ġ �߾�

    }

    private void setDialButton(JButton[] dialButton_list) {
        for (int i = 0; i < 3; i++) { // 1 ~ 9 ��ư
            for (int j = 0; j < 3; j++) {
                dialButton_list[i * 3 + j] = new JButton(num_list[i * 3 + j]);
                gbc(dialButton_list[i * 3 + j], j, i, 1, 1);

                MyFrame.PadInput handler = new MyFrame.PadInput(); // Ű�е� �̺�Ʈ�� �߻� ��Ű�� ���� handler ����
                dialButton_list[i * 3 + j].addActionListener(handler); // �̺�Ʈ ���
            }
        }
        dialButton_list[9] = new JButton(num_list[9]);     // 0 ��ư
        gbc(dialButton_list[9], 0, 3, 2, 1);
        PadInput handler9 = new PadInput(); // Ű�е� �̺�Ʈ�� �߻� ��Ű�� ���� handler ����
        dialButton_list[9].addActionListener(handler9); // �̺�Ʈ ���

        dialButton_list[10] = new JButton(num_list[10]);   // �� ��ư
        gbc(dialButton_list[10], 2, 3, 1, 1);
        PadInput handler10 = new PadInput(); // Ű�е� �̺�Ʈ�� �߻� ��Ű�� ���� handler ����
        dialButton_list[10].addActionListener(handler10); // �̺�Ʈ ���

        dialButton_list[11] = new JButton(num_list[11]);   // Ȯ�� ��ư
        gbc(dialButton_list[11], 3, 0, 1, 4);
        PadInput handler11 = new PadInput(); // Ű�е� �̺�Ʈ�� �߻� ��Ű�� ���� handler ����
        dialButton_list[11].addActionListener(handler11); // �̺�Ʈ ���
    }

    private class PadInput implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String eventText = event.getActionCommand();
//            System.out.println("input�� "+input);
//            System.out.println("input num ��" + inputNum);

            if(inputTemp == 0){
                if(eventText.equals("Ȯ��")){
                    inputNum = inputTemp;
                    inputText.setText("");
                    // ���ÿϷ� �޽���
                    JOptionPane aa=new JOptionPane();
                    if(inputNum>=1 && inputNum<=8 )
                        aa.showMessageDialog(null, inputNum+ "�� DVM�� �����ϼ̽��ϴ�.");
                    else
                        aa.showMessageDialog(null, "�ùٸ� ��ȣ�� ������ �ֽʽÿ� (1~8)");


                }else if(eventText.equals("��")) {
                    inputTemp = 0;
                    inputText.setText("");
                }else {                                           //0~9 ������ ���� input
                    inputTemp = Integer.parseInt(eventText);
                    inputText.setText(String.valueOf(inputTemp));
                }
            }

            // �����ڸ��� �̻��� input ��Ʈ��

            else{
                if(eventText.equals("Ȯ��")){
                    inputNum = inputTemp;
                    inputTemp = 0;
                    inputText.setText("");

                    JOptionPane aa=new JOptionPane();
                    if(inputNum>=1 && inputNum<=8)
                        aa.showMessageDialog(null, inputNum+ "�� DVM�� �����ϼ̽��ϴ�.");
                    else
                        aa.showMessageDialog(null, inputNum + "���� ���������ʽ��ϴ�. �ùٸ� ��ȣ�� ������ �ֽʽÿ� (1~8)");
                }else if(eventText.equals("��")) {
//                    int tmp= inputTemp % 10;
//                    inputTemp = (inputTemp - tmp)/10;   �̷����Ϸ��ٰ� �����غ��� �� 10���� ������ɵ�
                    inputTemp = inputTemp/10;
                    if(inputTemp == 0)
                        inputText.setText("");
                    else
                        inputText.setText(String.valueOf(inputTemp));
                }else{                                                              //0~9 ������ ���� input
                    inputTemp = inputTemp*10 + Integer.parseInt(eventText);
                    inputText.setText(String.valueOf(inputTemp));
                }
            }

        }

    }

    private void showAccessibleDVMList(JPanel p2, String[] dvmList) {
        p2.setLayout(grid);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                int num = i * 4 + j + 1;
                if (!dvmList[i * 4 + j].equals("")) {
                    labelList.add(new JLabel(num + ". " + dvmList[i * 4 + j]));
                } else
                    labelList.add(new JLabel(""));
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                gbc(labelList.get(i * 4 + j), j, i, 1, 1);
                labelList.get(i * 4 + j).setHorizontalAlignment(SwingConstants.CENTER);
            }
        }
        for (int i = 0; i < dvmList.length; i++) {
            p2.add(labelList.get(i));
        }
    }


    private void gbc(JComponent j, int x, int y, int width, int height) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        grid.setConstraints(j, gbc);
    }
}