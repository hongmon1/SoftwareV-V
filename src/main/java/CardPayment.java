import java.util.ArrayList;
import java.util.Arrays;

public class CardPayment {

    private Card card_info;
    private Drink drink_info;
    private boolean isPrePayment;
    ArrayList<Card> basicCardList;

    public CardPayment() {
        init();
    }

    private void init() {
        int[] basicCardNameList = {12341234,11111111, 10000000};
        Card card1 = new Card(basicCardNameList[0], 10000);
        Card card2 = new Card(basicCardNameList[1], 0);
        Card card3 = new Card(basicCardNameList[2], 10000);
        ArrayList<Card> tempList = new ArrayList<>();
        tempList.add(card1);
        tempList.add(card2);
        tempList.add(card3);
        basicCardList = tempList;
    }

    public boolean getIsPrePayment() {
        return isPrePayment;
    }

    public void setIsPrePayment(boolean tf){
        this.isPrePayment = tf;
    }

    public Code generateCode(Drink selected_drink){
        //5�ڸ� �ڵ� ���� ��, ���� ��ü�� ���ļ� �ڵ� ��ü ����
        drink_info = selected_drink;
        int generatedCodeNum = (int)(Math.random() * (99999 - 10000 + 1)) + 10000;
        Code generatedCode = new Code(generatedCodeNum, drink_info);
        return generatedCode;
    }

    public Card getCard(int card_num) {
        for (Card card : basicCardList) {
            if (card.getCard_num()== card_num) {
                return card;
            }
        }
        return null;
    }

    public Boolean getCard_available(int card_num) {
        for (Card card : basicCardList) {
            if(card.getCard_num() == card_num)
                return true;
        }
        return false;
    }
}