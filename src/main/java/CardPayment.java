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
        Card card1 = new Card(basicCardNameList[0], 10000, true);
        Card card2 = new Card(basicCardNameList[1], 0, true);
        Card card3 = new Card(basicCardNameList[2], 10000, false);
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

    public Boolean cardPayment(Card card_info, Drink drink_info){
        if (card_info.getCard_available()){
            //available 한 카드
            int balance = card_info.getBalance();
            int price = drink_info.getPrice();
            if (balance >= price){
                //잔액 결제 가능
                card_info.updateBalance(price);
                return true;
            }else{
                System.out.println("잔액 부족 결제 취소");
                return false;
            }
        }
        else{
            System.out.println("available하지 않은 카드");
            return false;
        }
    }

    public Code generateCode(Drink selected_drink){
        //5자리 코드 생성 후, 음료 객체랑 합쳐서 코드 객체 생성
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
}