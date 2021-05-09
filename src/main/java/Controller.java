import java.util.ArrayList;

public class Controller {
    ArrayList<Code> code_list = new ArrayList<Code>();
    int currentDVMIndex;
    ArrayList<DVM> accessible_DVM_list;
    Card card_info;
    OtherDVMs otherDVMs = new OtherDVMs();
    Drink selected_drink;
    CardPayment cardPayment = new CardPayment();
    CodePayment codePayment = new CodePayment();

    int selectDrink(int dialNum) {
        final int EMPTY_ALL_STOCK = 0; // ��� DVM�� ��� 0��
        final int CUR_IN_STOCK = 1;    // ���� DVM�� ��� ����
        final int OTHER_IN_STOCK = 2;  // �ٸ� DVM�� ��� ����
        if(dialNum >= 1 && dialNum <= 7) {
            DVM currentDVM = otherDVMs.getDVM(currentDVMIndex);
            selected_drink = currentDVM.getDrink_list().get(dialNum - 1);   //selected_drink ��� ���������� ����

            boolean current_stock = otherDVMs.checkCurrentDVMsStock(selected_drink, currentDVM);
            if (current_stock) {
                return CUR_IN_STOCK;
            } else {
                accessible_DVM_list = otherDVMs.checkOtherDVMsStock(selected_drink, currentDVM);
                if (accessible_DVM_list == null || accessible_DVM_list.size() == 0)
                    return EMPTY_ALL_STOCK;
                else
                    return OTHER_IN_STOCK;
            }
        }
        else{ // dialNum (8 ~ 20)
            DVM currentDVM = otherDVMs.getDVM(currentDVMIndex);
            selected_drink = currentDVM.getDrink_list().get(dialNum - 1);
            accessible_DVM_list = otherDVMs.checkOtherDVMsStock(selected_drink, currentDVM);
            if (accessible_DVM_list == null || accessible_DVM_list.size() == 0)
                return EMPTY_ALL_STOCK;
            else
                return OTHER_IN_STOCK;
        }
    }

    String insertCard(int card_num, boolean isPrepayment){
        Boolean card_available = cardPayment.getCard_available(card_num);
        if(!card_available){
            return "";
        }
        Card card = cardPayment.getCard(card_num);
        int balance = card.getBalance();
        int price = selected_drink.getPrice();
        if(balance < price){
            return "";
        }
        card.updateBalance(price);
        if(isPrepayment){
            Code code = cardPayment.generateCode(selected_drink); // �ڵ� ����
            addCode(code);
            DVM currentDVM = otherDVMs.getDVM(currentDVMIndex);
            String locationsListStr = otherDVMs.showAccessibleDVMsLocation(accessible_DVM_list, currentDVMIndex);
            String result = "������ ���� DVM: " + (currentDVM.getId() + 1)
                    + "\n�������� �����: " + selected_drink.getName()
                    + "\n���� ����: " + selected_drink.getPrice()
                    + "\n������ �� ī�� �ܰ�: " + card.getBalance() + "��"
                    + "\n�߱� �ڵ�: '" + code.getCode() + "'"
                    + "\n\n<�ش� ���� ���� ���� DVM �� DVM ��ġ>"
                    +"\n " + locationsListStr;
            return result;
        }
        else{
            String result = otherDVMs.requestDrink(selected_drink, currentDVMIndex);
            String result2 = result + "\n���� �� ī�� �ܰ�: " + card.getBalance() + "��";
            return result2;
        }
    }

    private ArrayList<Code> addCode(Code code) {
        code_list.add(code);
        return code_list;
    }

    String enterCode(int code_num) {
        Boolean codeAvailable = checkCodeAvailable(code_num);
        if(!codeAvailable){
            return "";
        }
        Code code_info = getCodeInfo(code_num);
        Drink drink = codePayment.codePayment(code_info);
        String result = otherDVMs.requestDrink(drink, currentDVMIndex);
        deleteCode(code_info);
        String result2 = result + "\n�ڵ� ����: " + code_info.getCode();
        return result2;
    }

    Code getCodeInfo(int code_num) {
        for(Code code : code_list){
            if(code.getCode() == code_num){
                return code;
            }
        }
        return null;
    }

    ArrayList<Code> deleteCode(Code code_info) {
        code_list.removeIf(code -> code.getCode() == code_info.getCode());
        return code_list;
    }

    public Boolean checkCodeAvailable(int code_num) {
        for (Code code : code_list) {
            if (code_num == code.getCode()) {
                return true;
            }
        }
        return false;
    }

    public DVM selectDVM(int num) {
        currentDVMIndex = num - 1;
        return otherDVMs.getDVM(num - 1);
    }

}
