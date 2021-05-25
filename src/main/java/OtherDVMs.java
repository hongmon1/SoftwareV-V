import java.util.ArrayList;
import java.util.Arrays;

<<<<<<< Updated upstream
public class OtherDVMs {
    ArrayList<DVM> dvmList;
=======
public class OtherDVMs extends Thread{
    ArrayList<DVM> dvmList =new ArrayList<DVM>();
>>>>>>> Stashed changes
    Network network;


    OtherDVMs(){
        init();
    }

    DVM getDVM(int index){
        DVM currentDVM = dvmList.get(index);
        for(Drink drink : currentDVM.getDrink_list()){
            Message message = new Message();
            message.createMessage(currentDVM.getDVMId(), currentDVM.getDVMId(), MsgType.REQUEST_STOCK, drink.getName());
            int stock = (int)network.handleRequestMessage(message);
            drink.setStock(stock);
        }
        return currentDVM;
    }

<<<<<<< Updated upstream
    ArrayList<DVM> getDVMList(){return dvmList;}
=======
    public ArrayList<DVM> getDVMList(){
        for(DVM dvm : dvmList){
            Message message = new Message();
            message.createMessage(dvm.getDVMId(), dvm.getDVMId(), MsgType.REQUEST_LOCATION);
            int address = (int)network.handleRequestMessage(message);
            dvm.setAddress(address);
        }
        return dvmList;
    }
>>>>>>> Stashed changes


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
<<<<<<< Updated upstream
        Message stockBroadCastMessage = new Message().createMessage(currentDVM.getId(), 0, 1, drink_info.getName());
        ArrayList<DVM> accessible_DVM_list = network.requestBroadcastMessage(stockBroadCastMessage);
=======
        //Message stockBroadCastMessage = currentDVM.makeStockRequestMessage(0, drink_info.getName());
        Message message = new Message();
        message.createMessage(currentDVM.getDVMId(), 0, MsgType.REQUEST_STOCK, drink_info.getName());
        ArrayList<DVM> accessible_DVM_list = (ArrayList<DVM>) network.handleRequestMessage(message);
        //ArrayList<DVM> accessible_DVM_list = (ArrayList<DVM>) currentDVM.requestStockMessage(network, stockBroadCastMessage);
>>>>>>> Stashed changes

        return accessible_DVM_list;
    }

<<<<<<< Updated upstream
    public String requestDrink(Drink selected_drink, int currentDVMIndex) {
        dvmList.get(currentDVMIndex).updateStock(selected_drink);
        String result = "       <음료 구매 완료>" +
                "\n구매 진행한 DVM: DVM" + String.valueOf(currentDVMIndex + 1)
                + "\n구매한 음료: "+ selected_drink.getName()
                + "\n음료 가격: " + selected_drink.getPrice() + "원";
=======
    public String requestDrink(Drink selected_drink, DVM currentDVM) {
        Message message = new Message();

        message.createMessage(currentDVM.getDVMId(), currentDVM.getDVMId(), MsgType.DRINK_SALE_CHECK, selected_drink.getName());
        int remainedStock = (int)network.handleRequestMessage(message);
//        dvmList.get(currentDVMIndex).updateStock(selected_drink);
        String result = "       <�쓬猷� 援щℓ �셿猷�>" +
                "\n援щℓ 吏꾪뻾�븳 DVM: DVM" + (currentDVM.getDVMId() + 1)
                + "\n援щℓ�븳 �쓬猷�: "+ selected_drink.getName()
                + "\n�쓬猷� 媛�寃�: " + selected_drink.getPrice() + "�썝"
                + "\n�옍�뿬 �옱怨�: " + remainedStock + "媛�";
>>>>>>> Stashed changes
        return result;
    }

    String showAccessibleDVMsLocation(ArrayList<DVM> accessibleDVMList, DVM currentDVM){
        ArrayList<Integer> dvmLocationList = new ArrayList<>();
<<<<<<< Updated upstream
        for(int i = 0; i < accessibleDVMList.size(); i++){
            DVM currentDVM = getDVM(currentDVMIndex);
            Message locationRequestMessage = new Message().createMessage(currentDVM.getId(), accessibleDVMList.get(i).getId(), MsgType.REQUEST_LOCATION);
            int address = network.requestNormalMessage(locationRequestMessage);
=======
        for(int i = 0; i < accessibleDVMList.size(); i++) {
            Message message = new Message();
            message.createMessage(currentDVM.getDVMId(), accessibleDVMList.get(i).getDVMId(), MsgType.REQUEST_LOCATION);
            int address = (int)network.handleRequestMessage(message);
>>>>>>> Stashed changes
            dvmLocationList.add(address);
        }
        StringBuilder locationListStr = new StringBuilder();
        for(int i = 0; i < accessibleDVMList.size(); i++){
<<<<<<< Updated upstream
            locationListStr.append("DVM 명: DVM").append((accessibleDVMList.get(i).getId() + 1)).append(" / 위치: ").append(dvmLocationList.get(i)).append("\n");
=======
            locationListStr.append("DVM 紐�: DVM").append((accessibleDVMList.get(i).getDVMId() + 1)).append(" / �쐞移�: ").append(dvmLocationList.get(i)).append("\n");
>>>>>>> Stashed changes
        }
        return String.valueOf(locationListStr);
    }

    private void init() {
<<<<<<< Updated upstream
        ArrayList<Drink> drinkArrayList = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList.add(new Drink("펩시콜라", 1500, 11, "src/main/resources/image/2.jpg"));
        drinkArrayList.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList.add(new Drink("스프라이트", 1500, 10, "src/main/resources/image/4.jpg"));
        drinkArrayList.add(new Drink("환타오렌지", 1500, 8, "src/main/resources/image/5.jpg"));
        drinkArrayList.add(new Drink("환타포도", 1500, 1, "src/main/resources/image/6.jpg"));
        drinkArrayList.add(new Drink("핫식스", 1500, 10, "src/main/resources/image/7.jpg"));
        drinkArrayList.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList.add(new Drink("빡텐션", 1500, 0, "src/main/resources/image/10.jpg"));
        drinkArrayList.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList.add(new Drink("파워에이드", 1500, 0, "src/main/resources/image/13.jpg"));
        drinkArrayList.add(new Drink("밀키스", 1500, 0, "src/main/resources/image/14.jpg"));
        drinkArrayList.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList2 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList2.add(new Drink("빡텐션", 1500, 10, "src/main/resources/image/10.jpg"));
        drinkArrayList2.add(new Drink("파워에이드", 1500, 10, "src/main/resources/image/13.jpg"));
        drinkArrayList2.add(new Drink("밀키스", 1500, 10, "src/main/resources/image/14.jpg"));
        drinkArrayList2.add(new Drink("레쓰비", 1500, 10, "src/main/resources/image/15.jpg"));
        drinkArrayList2.add(new Drink("스파클링", 1500, 10, "src/main/resources/image/16.jpg"));
        drinkArrayList2.add(new Drink("데자와", 1500, 10, "src/main/resources/image/19.jpg"));
        drinkArrayList2.add(new Drink("코카콜라", 1500, 10, "src/main/resources/image/1.jpg"));
        drinkArrayList2.add(new Drink("펩시콜라", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList2.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList2.add(new Drink("스프라이트", 1500, 0, "src/main/resources/image/4.jpg"));
        drinkArrayList2.add(new Drink("환타오렌지", 1500, 0, "src/main/resources/image/5.jpg"));
        drinkArrayList2.add(new Drink("환타포도", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList2.add(new Drink("핫식스", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList2.add(new Drink("레드불", 1500, 0, "src/main/resources/image/8.jpg"));
        drinkArrayList2.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList2.add(new Drink("포카리스웨트", 1500, 0, "src/main/resources/image/11.jpg"));
        drinkArrayList2.add(new Drink("게토레이", 1500, 0, "src/main/resources/image/12.jpg"));
        drinkArrayList2.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList2.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList2.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList3 = new ArrayList<>(); // 전체 음료수 리스트
        drinkArrayList3.add(new Drink("환타오렌지", 1500, 10, "src/main/resources/image/5.jpg"));
        drinkArrayList3.add(new Drink("포카리스웨트", 1500, 10, "src/main/resources/image/11.jpg"));
        drinkArrayList3.add(new Drink("레드불", 1500, 10, "src/main/resources/image/8.jpg"));
        drinkArrayList3.add(new Drink("빡텐션", 1500, 10, "src/main/resources/image/10.jpg"));
        drinkArrayList3.add(new Drink("파워에이드", 1500, 10, "src/main/resources/image/13.jpg"));
        drinkArrayList3.add(new Drink("밀키스", 1500, 10, "src/main/resources/image/14.jpg"));
        drinkArrayList3.add(new Drink("게토레이", 1500, 20, "src/main/resources/image/12.jpg"));
        drinkArrayList3.add(new Drink("비락식혜", 1500, 0, "src/main/resources/image/17.jpg"));
        drinkArrayList3.add(new Drink("솔의눈", 1500, 0, "src/main/resources/image/18.jpg"));
        drinkArrayList3.add(new Drink("레쓰비", 1500, 0, "src/main/resources/image/15.jpg"));
        drinkArrayList3.add(new Drink("스파클링", 1500, 0, "src/main/resources/image/16.jpg"));
        drinkArrayList3.add(new Drink("데자와", 1500, 0, "src/main/resources/image/19.jpg"));
        drinkArrayList3.add(new Drink("코카콜라", 1500, 0, "src/main/resources/image/1.jpg"));
        drinkArrayList3.add(new Drink("펩시콜라", 1500, 0, "src/main/resources/image/2.jpg"));
        drinkArrayList3.add(new Drink("칠성사이다", 1500, 0, "src/main/resources/image/3.jpg"));
        drinkArrayList3.add(new Drink("스프라이트", 1500, 0, "src/main/resources/image/4.jpg"));
        drinkArrayList3.add(new Drink("환타포도", 1500, 0, "src/main/resources/image/6.jpg"));
        drinkArrayList3.add(new Drink("핫식스", 1500, 0, "src/main/resources/image/7.jpg"));
        drinkArrayList3.add(new Drink("몬스터드링크", 1500, 0, "src/main/resources/image/9.jpg"));
        drinkArrayList3.add(new Drink("마운틴듀", 1500, 0, "src/main/resources/image/20.jpg"));

        DVM dvm1 = new DVM(drinkArrayList, 0, 101);
        DVM dvm2 = new DVM(drinkArrayList2, 1, 202);
        DVM dvm3 = new DVM(drinkArrayList3, 2, 303);
        DVM dvm4 = new DVM(drinkArrayList3, 3, 404);
        DVM dvm5 = new DVM(drinkArrayList3, 4, 505);
        DVM dvm6 = new DVM(drinkArrayList3, 5, 606);
        DVM dvm7 = new DVM(drinkArrayList3, 6, 707);
        DVM dvm8 = new DVM( drinkArrayList3, 7, 808);
=======
        /*
        * �씠 怨녹뿉 �젙�쓽�맂 Drink�뱾怨� Drink由ъ뒪�듃�뱾��� �옱怨좎뿉 ����븳 �젙蹂대�� �떞吏� �븡怨� �엳�쓬
        * �옱怨� �젙蹂대뒗 諛섎뱶�떆 �꽕�듃�썙�궧�쓣 �넻�빐�꽌留� �쟾�떖諛쏆쓣 �닔 �엳�쓬
        * �뵲�씪�꽌 �솕硫댁뿉 異쒕젰�릺�뒗 �옱怨� �젙蹂대뱾��� �쟾遺� �냼耳볧넻�떊�쓣 �넻�븳 寃곌낵媛믪쓣 異쒕젰諛쏅뒗 寃껋엫
        * */
        ArrayList<Drink> drinkArrayList = new ArrayList<>(); // �쟾泥� �쓬猷뚯닔 由ъ뒪�듃
        drinkArrayList.add(new Drink("肄붿뭅肄쒕씪", 1500, "src/main/resources/image/1.jpg"));
        drinkArrayList.add(new Drink("�렔�떆肄쒕씪", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList.add(new Drink("移좎꽦�궗�씠�떎", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList.add(new Drink("�뒪�봽�씪�씠�듃", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList.add(new Drink("�솚����삤�젋吏�", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList.add(new Drink("�솚����룷�룄", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList.add(new Drink("�빂�떇�뒪", 1500,  "src/main/resources/image/7.jpg"));
        drinkArrayList.add(new Drink("�젅�뱶遺�", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList.add(new Drink("紐ъ뒪�꽣�뱶留곹겕", 1500, "src/main/resources/image/9.jpg"));
        drinkArrayList.add(new Drink("鍮≫뀗�뀡", 1500, "src/main/resources/image/10.jpg"));
        drinkArrayList.add(new Drink("�룷移대━�뒪�썾�듃", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList.add(new Drink("寃뚰넗�젅�씠", 1500, "src/main/resources/image/12.jpg"));
        drinkArrayList.add(new Drink("�뙆�썙�뿉�씠�뱶", 1500,  "src/main/resources/image/13.jpg"));
        drinkArrayList.add(new Drink("諛��궎�뒪", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList.add(new Drink("�젅�벐鍮�", 1500, "src/main/resources/image/15.jpg"));
        drinkArrayList.add(new Drink("�뒪�뙆�겢留�", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList.add(new Drink("鍮꾨씫�떇�삙", 1500, "src/main/resources/image/17.jpg"));
        drinkArrayList.add(new Drink("�넄�쓽�늿", 1500, "src/main/resources/image/18.jpg"));
        drinkArrayList.add(new Drink("�뜲�옄���", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList.add(new Drink("留덉슫�떞���", 1500,  "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList2 = new ArrayList<>(); // �쟾泥� �쓬猷뚯닔 由ъ뒪�듃
        drinkArrayList2.add(new Drink("鍮≫뀗�뀡", 1500,  "src/main/resources/image/10.jpg"));
        drinkArrayList2.add(new Drink("�뙆�썙�뿉�씠�뱶", 1500, "src/main/resources/image/13.jpg"));
        drinkArrayList2.add(new Drink("諛��궎�뒪", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList2.add(new Drink("�젅�벐鍮�", 1500,  "src/main/resources/image/15.jpg"));
        drinkArrayList2.add(new Drink("�뒪�뙆�겢留�", 1500, "src/main/resources/image/16.jpg"));
        drinkArrayList2.add(new Drink("�뜲�옄���", 1500, "src/main/resources/image/19.jpg"));
        drinkArrayList2.add(new Drink("肄붿뭅肄쒕씪", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList2.add(new Drink("�렔�떆肄쒕씪", 1500, "src/main/resources/image/2.jpg"));
        drinkArrayList2.add(new Drink("移좎꽦�궗�씠�떎", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList2.add(new Drink("�뒪�봽�씪�씠�듃", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList2.add(new Drink("�솚����삤�젋吏�", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList2.add(new Drink("�솚����룷�룄", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList2.add(new Drink("�빂�떇�뒪", 1500, "src/main/resources/image/7.jpg"));
        drinkArrayList2.add(new Drink("�젅�뱶遺�", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList2.add(new Drink("紐ъ뒪�꽣�뱶留곹겕", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList2.add(new Drink("�룷移대━�뒪�썾�듃", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList2.add(new Drink("寃뚰넗�젅�씠", 1500, "src/main/resources/image/12.jpg"));
        drinkArrayList2.add(new Drink("鍮꾨씫�떇�삙", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList2.add(new Drink("�넄�쓽�늿", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList2.add(new Drink("留덉슫�떞���", 1500, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList3 = new ArrayList<>(); // �쟾泥� �쓬猷뚯닔 由ъ뒪�듃
        drinkArrayList3.add(new Drink("�솚����삤�젋吏�", 1500, "src/main/resources/image/5.jpg"));
        drinkArrayList3.add(new Drink("�룷移대━�뒪�썾�듃", 1500, "src/main/resources/image/11.jpg"));
        drinkArrayList3.add(new Drink("�젅�뱶遺�", 1500, "src/main/resources/image/8.jpg"));
        drinkArrayList3.add(new Drink("鍮≫뀗�뀡", 1500, "src/main/resources/image/10.jpg"));
        drinkArrayList3.add(new Drink("�뙆�썙�뿉�씠�뱶", 1500, "src/main/resources/image/13.jpg"));
        drinkArrayList3.add(new Drink("諛��궎�뒪", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList3.add(new Drink("寃뚰넗�젅�씠", 1500, "src/main/resources/image/12.jpg"));
        drinkArrayList3.add(new Drink("鍮꾨씫�떇�삙", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList3.add(new Drink("�넄�쓽�늿", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList3.add(new Drink("�젅�벐鍮�", 1500, "src/main/resources/image/15.jpg"));
        drinkArrayList3.add(new Drink("�뒪�뙆�겢留�", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList3.add(new Drink("�뜲�옄���", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList3.add(new Drink("肄붿뭅肄쒕씪", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList3.add(new Drink("�렔�떆肄쒕씪", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList3.add(new Drink("移좎꽦�궗�씠�떎", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList3.add(new Drink("�뒪�봽�씪�씠�듃", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList3.add(new Drink("�솚����룷�룄", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList3.add(new Drink("�빂�떇�뒪", 1500,  "src/main/resources/image/7.jpg"));
        drinkArrayList3.add(new Drink("紐ъ뒪�꽣�뱶留곹겕", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList3.add(new Drink("留덉슫�떞���", 1500, "src/main/resources/image/20.jpg"));

        ArrayList<Drink> drinkArrayList4 = new ArrayList<>(); // �쟾泥� �쓬猷뚯닔 由ъ뒪�듃
        drinkArrayList4.add(new Drink("�렔�떆肄쒕씪", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList4.add(new Drink("移좎꽦�궗�씠�떎", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList4.add(new Drink("�뒪�봽�씪�씠�듃", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList4.add(new Drink("�솚����삤�젋吏�", 1500, "src/main/resources/image/5.jpg"));
        drinkArrayList4.add(new Drink("�룷移대━�뒪�썾�듃", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList4.add(new Drink("�뒪�뙆�겢留�", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList4.add(new Drink("寃뚰넗�젅�씠", 1500,  "src/main/resources/image/12.jpg"));
        drinkArrayList4.add(new Drink("鍮꾨씫�떇�삙", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList4.add(new Drink("�넄�쓽�늿", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList4.add(new Drink("�젅�벐鍮�", 1500, "src/main/resources/image/15.jpg"));
        drinkArrayList4.add(new Drink("�뜲�옄���", 1500, "src/main/resources/image/19.jpg"));
        drinkArrayList4.add(new Drink("肄붿뭅肄쒕씪", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList4.add(new Drink("�솚����룷�룄", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList4.add(new Drink("�빂�떇�뒪", 1500, "src/main/resources/image/7.jpg"));
        drinkArrayList4.add(new Drink("紐ъ뒪�꽣�뱶留곹겕", 1500, "src/main/resources/image/9.jpg"));
        drinkArrayList4.add(new Drink("留덉슫�떞���", 1500, "src/main/resources/image/20.jpg"));
        drinkArrayList4.add(new Drink("諛��궎�뒪", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList4.add(new Drink("�젅�뱶遺�", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList4.add(new Drink("鍮≫뀗�뀡", 1500,  "src/main/resources/image/10.jpg"));
        drinkArrayList4.add(new Drink("�뙆�썙�뿉�씠�뱶", 1500,  "src/main/resources/image/13.jpg"));

        ArrayList<Drink> drinkArrayList5 = new ArrayList<>(); // �쟾泥� �쓬猷뚯닔 由ъ뒪�듃
        drinkArrayList5.add(new Drink("�젅�벐鍮�", 1500,  "src/main/resources/image/15.jpg"));
        drinkArrayList5.add(new Drink("�렔�떆肄쒕씪", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList5.add(new Drink("移좎꽦�궗�씠�떎", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList5.add(new Drink("留덉슫�떞���", 1500,  "src/main/resources/image/20.jpg"));
        drinkArrayList5.add(new Drink("諛��궎�뒪", 1500, "src/main/resources/image/14.jpg"));
        drinkArrayList5.add(new Drink("�뒪�봽�씪�씠�듃", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList5.add(new Drink("�솚����삤�젋吏�", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList5.add(new Drink("�뜲�옄���", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList5.add(new Drink("肄붿뭅肄쒕씪", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList5.add(new Drink("�룷移대━�뒪�썾�듃", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList5.add(new Drink("�뒪�뙆�겢留�", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList5.add(new Drink("寃뚰넗�젅�씠", 1500,  "src/main/resources/image/12.jpg"));
        drinkArrayList5.add(new Drink("鍮꾨씫�떇�삙", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList5.add(new Drink("�넄�쓽�늿", 1500, "src/main/resources/image/18.jpg"));
        drinkArrayList5.add(new Drink("�솚����룷�룄", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList5.add(new Drink("�빂�떇�뒪", 1500,  "src/main/resources/image/7.jpg"));
        drinkArrayList5.add(new Drink("紐ъ뒪�꽣�뱶留곹겕", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList5.add(new Drink("�젅�뱶遺�", 1500, "src/main/resources/image/8.jpg"));
        drinkArrayList5.add(new Drink("鍮≫뀗�뀡", 1500, "src/main/resources/image/10.jpg"));
        drinkArrayList5.add(new Drink("�뙆�썙�뿉�씠�뱶", 1500,  "src/main/resources/image/13.jpg"));

        ArrayList<Drink> drinkArrayList6 = new ArrayList<>(); // �쟾泥� �쓬猷뚯닔 由ъ뒪�듃
        drinkArrayList6.add(new Drink("�뒪�뙆�겢留�", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList6.add(new Drink("�젅�뱶遺�", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList6.add(new Drink("鍮≫뀗�뀡", 1500,  "src/main/resources/image/10.jpg"));
        drinkArrayList6.add(new Drink("肄붿뭅肄쒕씪", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList6.add(new Drink("�뒪�봽�씪�씠�듃", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList6.add(new Drink("�솚����삤�젋吏�", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList6.add(new Drink("�룷移대━�뒪�썾�듃", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList6.add(new Drink("寃뚰넗�젅�씠", 1500,  "src/main/resources/image/12.jpg"));
        drinkArrayList6.add(new Drink("鍮꾨씫�떇�삙", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList6.add(new Drink("�넄�쓽�늿", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList6.add(new Drink("�렔�떆肄쒕씪", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList6.add(new Drink("移좎꽦�궗�씠�떎", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList6.add(new Drink("�젅�벐鍮�", 1500,  "src/main/resources/image/15.jpg"));
        drinkArrayList6.add(new Drink("�뜲�옄���", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList6.add(new Drink("�솚����룷�룄", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList6.add(new Drink("�빂�떇�뒪", 1500,  "src/main/resources/image/7.jpg"));
        drinkArrayList6.add(new Drink("紐ъ뒪�꽣�뱶留곹겕", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList6.add(new Drink("留덉슫�떞���", 1500,  "src/main/resources/image/20.jpg"));
        drinkArrayList6.add(new Drink("諛��궎�뒪", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList6.add(new Drink("�뙆�썙�뿉�씠�뱶", 1500,  "src/main/resources/image/13.jpg"));

        ArrayList<Drink> drinkArrayList7 = new ArrayList<>(); // �쟾泥� �쓬猷뚯닔 由ъ뒪�듃
        drinkArrayList7.add(new Drink("�빂�떇�뒪", 1500, "src/main/resources/image/7.jpg"));
        drinkArrayList7.add(new Drink("紐ъ뒪�꽣�뱶留곹겕", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList7.add(new Drink("�뙆�썙�뿉�씠�뱶", 1500,  "src/main/resources/image/13.jpg"));
        drinkArrayList7.add(new Drink("�렔�떆肄쒕씪", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList7.add(new Drink("寃뚰넗�젅�씠", 1500,  "src/main/resources/image/12.jpg"));
        drinkArrayList7.add(new Drink("鍮꾨씫�떇�삙", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList7.add(new Drink("�솚����삤�젋吏�", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList7.add(new Drink("�룷移대━�뒪�썾�듃", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList7.add(new Drink("�뒪�뙆�겢留�", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList7.add(new Drink("�뜲�옄���", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList7.add(new Drink("�넄�쓽�늿", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList7.add(new Drink("�젅�벐鍮�", 1500,  "src/main/resources/image/15.jpg"));
        drinkArrayList7.add(new Drink("肄붿뭅肄쒕씪", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList7.add(new Drink("�솚����룷�룄", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList7.add(new Drink("留덉슫�떞���", 1500,  "src/main/resources/image/20.jpg"));
        drinkArrayList7.add(new Drink("鍮≫뀗�뀡", 1500,  "src/main/resources/image/10.jpg"));
        drinkArrayList7.add(new Drink("諛��궎�뒪", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList7.add(new Drink("�젅�뱶遺�", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList7.add(new Drink("移좎꽦�궗�씠�떎", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList7.add(new Drink("�뒪�봽�씪�씠�듃", 1500,  "src/main/resources/image/4.jpg"));


        ArrayList<Drink> drinkArrayList8 = new ArrayList<>(); // �쟾泥� �쓬猷뚯닔 由ъ뒪�듃
        drinkArrayList8.add(new Drink("�젅�뱶遺�", 1500,  "src/main/resources/image/8.jpg"));
        drinkArrayList8.add(new Drink("鍮≫뀗�뀡", 1500,  "src/main/resources/image/10.jpg"));
        drinkArrayList8.add(new Drink("�넄�쓽�늿", 1500,  "src/main/resources/image/18.jpg"));
        drinkArrayList8.add(new Drink("�젅�벐鍮�", 1500,  "src/main/resources/image/15.jpg"));
        drinkArrayList8.add(new Drink("�뜲�옄���", 1500,  "src/main/resources/image/19.jpg"));
        drinkArrayList8.add(new Drink("肄붿뭅肄쒕씪", 1500,  "src/main/resources/image/1.jpg"));
        drinkArrayList8.add(new Drink("�솚����룷�룄", 1500,  "src/main/resources/image/6.jpg"));
        drinkArrayList8.add(new Drink("�룷移대━�뒪�썾�듃", 1500,  "src/main/resources/image/11.jpg"));
        drinkArrayList8.add(new Drink("�뒪�뙆�겢留�", 1500,  "src/main/resources/image/16.jpg"));
        drinkArrayList8.add(new Drink("寃뚰넗�젅�씠", 1500,  "src/main/resources/image/12.jpg"));
        drinkArrayList8.add(new Drink("鍮꾨씫�떇�삙", 1500,  "src/main/resources/image/17.jpg"));
        drinkArrayList8.add(new Drink("�뒪�봽�씪�씠�듃", 1500,  "src/main/resources/image/4.jpg"));
        drinkArrayList8.add(new Drink("�솚����삤�젋吏�", 1500,  "src/main/resources/image/5.jpg"));
        drinkArrayList8.add(new Drink("�빂�떇�뒪", 1500,  "src/main/resources/image/7.jpg"));
        drinkArrayList8.add(new Drink("紐ъ뒪�꽣�뱶留곹겕", 1500,  "src/main/resources/image/9.jpg"));
        drinkArrayList8.add(new Drink("留덉슫�떞���", 1500,  "src/main/resources/image/20.jpg"));
        drinkArrayList8.add(new Drink("�렔�떆肄쒕씪", 1500,  "src/main/resources/image/2.jpg"));
        drinkArrayList8.add(new Drink("移좎꽦�궗�씠�떎", 1500,  "src/main/resources/image/3.jpg"));
        drinkArrayList8.add(new Drink("諛��궎�뒪", 1500,  "src/main/resources/image/14.jpg"));
        drinkArrayList8.add(new Drink("�뙆�썙�뿉�씠�뱶", 1500,  "src/main/resources/image/13.jpg"));

        /*
        * �씠 怨녹뿉 �젙�쓽�맂 DVM�뱾��� 二쇱냼 �젙蹂닿�� �뾾�쓬
        * 二쇱냼 �젙蹂대�� �뼸湲� �쐞�빐�꽑 留덉갔媛�吏�濡� �꽕�듃�썙�궧�쓣 �넻�빐�꽌留� �븣 �닔 �엳�쓬
        * */
        DVM dvm1 = new DVM1(drinkArrayList, 1);
        DVM dvm2 = new DVM2(drinkArrayList2, 2);
        DVM dvm3 = new DVM3(drinkArrayList3, 3);
        DVM dvm4 = new DVM4(drinkArrayList4, 4);
        DVM dvm5 = new DVM5(drinkArrayList5, 5);
        DVM dvm6 = new DVM6(drinkArrayList6, 6);
        DVM dvm7 = new DVM7(drinkArrayList7, 7);
        DVM dvm8 = new DVM8(drinkArrayList8, 8);

>>>>>>> Stashed changes
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
