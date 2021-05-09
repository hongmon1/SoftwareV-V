import java.util.ArrayList;
import java.util.Arrays;

public class OtherDVMs {
    ArrayList<DVM> dvmList;
    Network network;


    OtherDVMs(){
        init();
    }

    DVM getDVM(int index){
        return dvmList.get(index);
    }

    ArrayList<DVM> getDVMList(){return dvmList;}


    public boolean checkCurrentDVMsStock(Drink selectedDrink, DVM currentDVM) {
        ArrayList<Drink> drink_list = currentDVM.getDrink_list();
        int dvmStock = 0;
        for(int i = 0; i < drink_list.size(); i++){
            if(drink_list.get(i).getName().equals(selectedDrink.getName())){
                dvmStock = drink_list.get(i).getStock();
            }
        }
        if(dvmStock > 0)
            return true;
        else
            return false;
    }

    ArrayList<DVM> checkOtherDVMsStock(Drink drink_info, DVM currentDVM){
        Message stockBroadCastMessage = new Message().createMessage(currentDVM.getId(), 0, 1, drink_info.getName());
        ArrayList<DVM> accessible_DVM_list = network.requestBroadcastMessage(stockBroadCastMessage);

        return accessible_DVM_list;
    }

    public String requestDrink(Drink selected_drink, int currentDVMIndex) {
        dvmList.get(currentDVMIndex).updateStock(selected_drink);
        String result = "       <���� ���� �Ϸ�>" +
                "\n���� ������ DVM: DVM" + String.valueOf(currentDVMIndex + 1)
                + "\n������ ����: "+ selected_drink.getName()
                + "\n���� ����: " + selected_drink.getPrice() + "��";
        return result;
    }

    String showAccessibleDVMsLocation(ArrayList<DVM> accessibleDVMList, int currentDVMIndex){
        ArrayList<Integer> dvmLocationList = new ArrayList<>();
        for(int i = 0; i < accessibleDVMList.size(); i++){
            DVM currentDVM = getDVM(currentDVMIndex);
            Message locationRequestMessage = new Message().createMessage(currentDVM.getId(), accessibleDVMList.get(i).getId(), MsgType.REQUEST_LOCATION);
            int address = network.requestNormalMessage(locationRequestMessage);
            dvmLocationList.add(address);
        }
        StringBuilder locationListStr = new StringBuilder();
        for(int i = 0; i < accessibleDVMList.size(); i++){
            locationListStr.append("DVM ��: DVM").append((accessibleDVMList.get(i).getId() + 1)).append(" / ��ġ: ").append(dvmLocationList.get(i)).append("\n");
        }
        return String.valueOf(locationListStr);
    }

    private void init() {
        ArrayList<Drink> drinkArrayList = new ArrayList<>(); // ��ü ����� ����Ʈ
        drinkArrayList.add(new Drink("��ī�ݶ�", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList.add(new Drink("����ݶ�", 1500, 11, "src/main/resources/image/2.jpg"));
        drinkArrayList.add(new Drink("ĥ�����̴�", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList.add(new Drink("��������Ʈ", 1500, 10, "src/main/resources/image/4.jpg"));
        drinkArrayList.add(new Drink("ȯŸ������", 1500, 8, "src/main/resources/image/5.jpg"));
        drinkArrayList.add(new Drink("ȯŸ����", 1500, 1, "src/main/resources/image/6.jpg"));
        drinkArrayList.add(new Drink("�ֽĽ�", 1500, 10, "src/main/resources/image/7.jpg"));
        drinkArrayList.add(new Drink("�����", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList.add(new Drink("���͵帵ũ", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList.add(new Drink("���ټ�", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkArrayList.add(new Drink("��ī������Ʈ", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList.add(new Drink("���䷹��", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList.add(new Drink("�Ŀ����̵�", 1500, 0, "src/main/resources/image/13.jpg"));
        drinkArrayList.add(new Drink("��Ű��", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList.add(new Drink("������", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList.add(new Drink("����Ŭ��", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList.add(new Drink("�������", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList.add(new Drink("���Ǵ�", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList.add(new Drink("���ڿ�", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList.add(new Drink("����ƾ��", 1500, 0, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList2 = new ArrayList<>(); // ��ü ����� ����Ʈ
        drinkArrayList2.add(new Drink("���ټ�", 1500, 10, "src/main/resources/image/10.jpg"));
        drinkArrayList2.add(new Drink("�Ŀ����̵�", 1500, 10, "src/main/resources/image/13.jpg"));
        drinkArrayList2.add(new Drink("��Ű��", 1500, 10, "src/main/resources/image/14.jpg"));
        drinkArrayList2.add(new Drink("������", 1500, 10, "src/main/resources/image/15.jpg"));
        drinkArrayList2.add(new Drink("����Ŭ��", 1500, 10, "src/main/resources/image/16.jpg"));
        drinkArrayList2.add(new Drink("���ڿ�", 1500, 10, "src/main/resources/image/19.jpg"));
        drinkArrayList2.add(new Drink("��ī�ݶ�", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList2.add(new Drink("����ݶ�", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList2.add(new Drink("ĥ�����̴�", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList2.add(new Drink("��������Ʈ", 1500, 0, "src/main/resources/image/4.jpg"));
        drinkArrayList2.add(new Drink("ȯŸ������", 1500, 0, "src/main/resources/image/5.jpg"));
        drinkArrayList2.add(new Drink("ȯŸ����", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList2.add(new Drink("�ֽĽ�", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList2.add(new Drink("�����", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList2.add(new Drink("���͵帵ũ", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList2.add(new Drink("��ī������Ʈ", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList2.add(new Drink("���䷹��", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList2.add(new Drink("�������", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList2.add(new Drink("���Ǵ�", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList2.add(new Drink("����ƾ��", 1500, 0, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList3 = new ArrayList<>(); // ��ü ����� ����Ʈ
        drinkArrayList3.add(new Drink("ȯŸ������", 1500, 10, "src/main/resources/image/5.jpg"));
        drinkArrayList3.add(new Drink("��ī������Ʈ", 1500, 10, "src/main/resources/image/11.jpg"));
        drinkArrayList3.add(new Drink("�����", 1500, 10, "src/main/resources/image/8.jpg"));
        drinkArrayList3.add(new Drink("���ټ�", 1500, 10, "src/main/resources/image/10.jpg"));
        drinkArrayList3.add(new Drink("�Ŀ����̵�", 1500, 10, "src/main/resources/image/13.jpg"));
        drinkArrayList3.add(new Drink("��Ű��", 1500, 10, "src/main/resources/image/14.jpg"));
        drinkArrayList3.add(new Drink("���䷹��", 1500, 20, "src/main/resources/image/12.jpg"));
        drinkArrayList3.add(new Drink("�������", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList3.add(new Drink("���Ǵ�", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList3.add(new Drink("������", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList3.add(new Drink("����Ŭ��", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList3.add(new Drink("���ڿ�", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList3.add(new Drink("��ī�ݶ�", 1500, 0, "src/main/resources/image/1.jpg"));
        drinkArrayList3.add(new Drink("����ݶ�", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList3.add(new Drink("ĥ�����̴�", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList3.add(new Drink("��������Ʈ", 1500, 0, "src/main/resources/image/4.jpg"));
        drinkArrayList3.add(new Drink("ȯŸ����", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList3.add(new Drink("�ֽĽ�", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList3.add(new Drink("���͵帵ũ", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList3.add(new Drink("����ƾ��", 1500, 0, "src/main/resources/image/20.jpg"));

        DVM dvm1 = new DVM(true, drinkArrayList, 0, 101);
        DVM dvm2 = new DVM(true, drinkArrayList2, 1, 202);
        DVM dvm3 = new DVM(true, drinkArrayList3, 2, 303);
        DVM dvm4 = new DVM(true, drinkArrayList3, 3, 404);
        DVM dvm5 = new DVM(true, drinkArrayList3, 4, 505);
        DVM dvm6 = new DVM(true, drinkArrayList3, 5, 606);
        DVM dvm7 = new DVM(true, drinkArrayList3, 6, 707);
        DVM dvm8 = new DVM(true, drinkArrayList3, 7, 808);
        ArrayList<DVM> tempList = new ArrayList<DVM>();
        tempList.add(dvm1);
        tempList.add(dvm2);
        tempList.add(dvm3);
        tempList.add(dvm4);
        tempList.add(dvm5);
        tempList.add(dvm6);
        tempList.add(dvm7);
        tempList.add(dvm8);
        dvmList = tempList;
        network = new Network(dvmList);
    }
}
