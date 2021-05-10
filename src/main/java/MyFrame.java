import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class MyFrame extends JFrame {

    int stage = 0;
    int inputNum = 0;
    int inputTemp = 0;
    JTextField inputText = new JTextField("          ", SwingConstants.CENTER); // ����� �Է��� �޴� �ؽ�Ʈ �ʵ�;
    String[] num_list = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "��", "Ȯ��"};
    JButton[] dialButton_list = new JButton[12];
    ArrayList<JLabel> labelList = new ArrayList<JLabel>();

    /////////////// panel �� ///////////////
    JPanel pDial = new JPanel();
    JPanel panelDown = new JPanel();
    JPanel pTemp = new JPanel();
    JPanel pScreen = new JPanel();
    JPanel pInput = new JPanel();

    /////////////// gridBagLayout ���ϰ� ����Ϸ��� �������� ���� ///////////////
    GridBagLayout grid = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    // controller ��ü
    Controller controller = new Controller();

    int[] dvmAddresses = {101, 202, 303, 404, 505, 606, 707, 808};

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

        showAllDVMList(pScreen); // �ʱ� ��ü DVM ���
        //showAccessibleDVMList(pScreen, dvmList);
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

    private void showAllDVMList(JPanel pScreen) {
        String [] arr = controller.startService();
        pScreen.setLayout(grid);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/image/vm_image.png")
                        .getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
                int num = i * 4 + j;
                int id = num + 1;
                int address = dvmAddresses[num];
                labelList.add(new JLabel("<html>"+ (num + 1) + ". DVM" + id + "<br>�ּ�: " + address + "</html>", imageIcon, JLabel.CENTER));
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                gbc(labelList.get(i * 4 + j), j, i, 1, 1);
                //labelList.get(i * 4 + j).setHorizontalAlignment(SwingConstants.CENTER);
            }
        }
        for (int i = 0; i < 8; i++) {
            pScreen.add(labelList.get(i));
        }
    }


    //���Ǳ� ����� ���//

    private void showDVMDrinkList(JPanel pScreen, int num) {
        ArrayList<JLabel> label_drink = new ArrayList<>();
        pScreen.setLayout(grid);
        DVM currentDVM = controller.selectDVM(num);
        ArrayList<Drink> currentDrinkList = currentDVM.getDrink_list();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                int index = i * 5 + j;
                Drink drink = currentDrinkList.get(index);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(drink.getImgURL())
                        .getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));
                String name = drink.getName();
                int price = drink.getPrice();
                int stock = drink.getStock();
                label_drink.add(new JLabel("<html>"+ (index + 1) + "." + name + "<br>" + price + "�� (" + stock + "��)</html>", imageIcon, JLabel.LEFT));
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                gbc(label_drink.get(i * 5 + j), j, i, 1, 1);
            }
        }
        for (int i = 0; i < currentDrinkList.size(); i++) {
            pScreen.add(label_drink.get(i));
        }
    }

    private void setDialButton(JButton[] dialButton_list) {
        for (int i = 0; i < 3; i++) { // 1 ~ 9 ��ư
            for (int j = 0; j < 3; j++) {
                dialButton_list[i * 3 + j] = new  JButton(num_list[i * 3 + j]);
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

            if(inputTemp == 0){
                if(eventText.equals("Ȯ��")){
                    inputNum = inputTemp;
                    inputText.setText("");
                    // ���ÿϷ� �޽���
                    JOptionPane aa=new JOptionPane();
                    if(inputNum >=1 && inputNum <= 8) {
                        JOptionPane.showMessageDialog(null, inputNum + "�� DVM�� �����ϼ̽��ϴ�.");
                        pScreen.removeAll();
                        showDVMDrinkList(pScreen, inputNum);
                        pScreen.updateUI();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "�ùٸ� ��ȣ�� ������ �ֽʽÿ� (1~8)");
                }else if(eventText.equals("��")) {
                    inputTemp = 0;
                    inputText.setText("");
                }else {                                           //0~9 ������ ���� input
                    inputTemp = Integer.parseInt(eventText);
                    String temp = inputText.getText();
                    inputText.setText(temp + eventText);
                }
            }
            // �����ڸ��� �̻��� input ��Ʈ��
            else{
                if(eventText.equals("Ȯ��")){
                    inputNum = inputTemp;
                    inputTemp = 0;
                    inputText.setText("");
                    //JOptionPane aa=new JOptionPane();
                    switch(stage) {
                        case 0: // DVM ����
                            if(inputNum>=1 && inputNum<=8) {
                                JOptionPane.showMessageDialog(null, inputNum + "�� DVM�� �����ϼ̽��ϴ�.");
                                pScreen.removeAll();
                                showDVMDrinkList(pScreen, inputNum);
                                pScreen.updateUI();
                                stage=1;
                            }
                            else{
                                JOptionPane.showMessageDialog(null, inputNum + "���� ���������ʽ��ϴ�. �ùٸ� ��ȣ�� ������ �ֽʽÿ� (1~8)");
                            }
                            break;
                        case 1: // Drink ����
                            if(inputNum>=1 && inputNum<=7) {
                                JOptionPane.showMessageDialog(null,inputNum + "�� ���Ḧ �����ϼ̽��ϴ�.");
                                pScreen.removeAll();
                                proceedCurrentDrink(pScreen, inputNum);
                                pScreen.updateUI();

                            }
                            else if(inputNum >= 8 && inputNum <= 20){
                                // ���� DVM�� ���� ���Ḧ ������ ���, ������ ����
                                // �ٸ� DVM�� ��� Ȯ�� ��û �� ��� �ִ� DVM ���
                                //JOptionPane.showMessageDialog(null,inputNum + "�� ����� ���� ���Ǳ⿡ �������� �ʽ��ϴ�. �������� �����մϴ�.");
                                pScreen.removeAll();
                                proceedOtherDrink(pScreen, inputNum);
                                pScreen.updateUI();
                            }
                            else
                                JOptionPane.showMessageDialog(null, "��ȣ�� �߸� �Է��߽��ϴ�. 1 or 2�� �Է����ּ���.");
                            break;
                        case 2: // ������� ����
                            if(inputNum == 1) {
                                JOptionPane.showMessageDialog(null, "ī�� ������ �����ϼ̽��ϴ�.");
                                pScreen.removeAll();
                                showCardInput(pScreen);
                                pScreen.updateUI();
                                stage = 3;
                            }
                            else if(inputNum == 2){
                                JOptionPane.showMessageDialog(null, "�ڵ� ������ �����ϼ̽��ϴ�.");
                                pScreen.removeAll();
                                showInputCode();
                                pScreen.updateUI();
                                stage = 4;
                            }
                            else
                                JOptionPane.showMessageDialog(null, "��ȣ�� �߸� �Է��߽��ϴ�. 1 or 2�� �Է����ּ���.");
                            break;
                        case 3: // ī�� ����
//                            aa.showMessageDialog(null, "ī�带 �������ּ���.");
                            // ī�� ��� ���
                            String result = controller.insertCard(inputNum, false);
                            if(result.equals("")){
                                JOptionPane.showMessageDialog(null, "��ȿ���� ���� ī���Դϴ�. �ʱ�ȭ������ ���ư��ϴ�.");
                                // �ʱ� ȭ������ ���ư�
                                stage = 0;
                                pScreen.removeAll();
                                showAllDVMList(pScreen);
                                pScreen.updateUI();
                            }
                            else{
                                JOptionPane.showMessageDialog(null, result);
                                // ����� ����, ����� ��� ������Ʈ
                                // �ʱ� ȭ������ ���ư�
                                stage = 0;
                                pScreen.removeAll();
                                showAllDVMList(pScreen);
                                pScreen.updateUI();
                            }
                            break;
                        case 4: // �ڵ� �Է�
                            String prepaymentResult = controller.enterCode(inputNum);
                            if(prepaymentResult.equals("")){
                                JOptionPane.showMessageDialog(null, "��ȿ���� ���� �ڵ��Դϴ�. �ʱ�ȭ������ ���ư��ϴ�.");
                                // �ʱ� ȭ������ ���ư�
                                stage = 0;
                                pScreen.removeAll();
                                showAllDVMList(pScreen);
                                pScreen.updateUI();
                            }
                            else{
                                JOptionPane.showMessageDialog(null, prepaymentResult);
                                stage = 0;
                                pScreen.removeAll();
                                showAllDVMList(pScreen);
                                pScreen.updateUI();
                            }
                            break;
                        case 5: // ������ ����
                            String prePaymentResult = controller.insertCard(inputNum, true);
                            JOptionPane.showMessageDialog(null, prePaymentResult);
                            stage = 0;
                            pScreen.removeAll();
                            showAllDVMList(pScreen);
                            pScreen.updateUI();
                            break;
                        case 6: // ��� �ִ� DVM ��ġ ���
                            //showAccessibleDVMList(pScreen);
                            stage = 0;
                            pScreen.removeAll();
                            showAllDVMList(pScreen);
                            pScreen.updateUI();
                            break;

                    }
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

    private void showInputCode() {
        pScreen.setLayout(grid);
        pScreen.add(new JLabel("<html>"+ "�ڵ� ��ȣ�� �Է����ּ���."+"<br>" + "(�ڵ� ��ȣ�� 5�ڸ� �����Դϴ�.)</html>"));
    }

    private void showCardInput(JPanel pScreen) {
        pScreen.setLayout(grid);
        pScreen.add(new JLabel("<html>"+ "ī�� �Ϸù�ȣ�� �Է����ּ���."+"<br>" + "(���� ��ȣ: 1234 1234)</html>"));
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

    private void proceedCurrentDrink(JPanel pScreen, int inputNum) {
        int drink_status = controller.selectCurrentDrink(inputNum);
        if(drink_status == 0){ // EMPTY_ALL_STOCK : ��� DVM �� ��� 0��
            JOptionPane.showMessageDialog(null, "��� DVM�� �ش� ������ ��� �����ϴ�. �ʱ�ȭ������ ���ư��ϴ�.");
            // ���� �ڵ� �޽��� ���
            stage = 0;
            showAllDVMList(pScreen);
            pScreen.updateUI();
        }
        else if(drink_status == 1){ // CUR_IN_STOCK : ���� ���Ǳ⿡ ��� ����
            ArrayList<JLabel> pay_label = new ArrayList<>();
            pScreen.setLayout(grid);
            pay_label.add(new JLabel("1. ī�����",JLabel.CENTER));
            pay_label.add(new JLabel("2. �ڵ����",JLabel.CENTER));

            for (int i = 0; i < 2; i++) {
                for(int j = 0; j < 1; j++) {
                    gbc(pay_label.get(i + j), j, i, 1, 1);
                }
            }

            for (int i = 0; i < 2; i++) {
                pScreen.add(pay_label.get(i));
            }
            stage = 2;
        }
        else{ // OTHER_IN_STOCK : �ٸ� ���Ǳ⿡ ��� ����
            JOptionPane.showMessageDialog(null, "���� DVM�� �ش� ������ ��� ������ �ٸ� DVM�� ��� �����մϴ�. �������� �Ѿ�ϴ�.");
            showCardInput(pScreen);
            stage = 5;
        }
    }

    private void proceedOtherDrink(JPanel pScreen, int inputNum) {
        int drink_status = controller.selectOtherDrink(inputNum);
        if(drink_status == 0){ // ��� DVM�� ��� ����
            JOptionPane.showMessageDialog(null, "��� DVM�� �ش� ������ ��� �����ϴ�. �ʱ�ȭ������ ���ư��ϴ�.");
            // ���� �ڵ� �޽��� ���
            stage = 0;
            showAllDVMList(pScreen);
            pScreen.updateUI();
        }
        else{ // OHTER_IN_STOCK : �ٸ� ���Ǳ⿡ ��� ����
            JOptionPane.showMessageDialog(null, "���� DVM�� �ش� ������ ��� ������ �ٸ� DVM�� ��� �����մϴ�. �������� �Ѿ�ϴ�.");
            showCardInput(pScreen);
            stage = 5;
        }
    }
}