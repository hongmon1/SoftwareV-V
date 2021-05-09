import java.util.ArrayList;

public class Network {
    ArrayList<DVM> dvmList;

    Network(ArrayList<DVM> dvmList){
        this.dvmList = dvmList;
    }

    public int requestNormalMessage(Message message) {
        int address = -1;
        int src_id = message.getSrc_id();
        int dst_id = message.getDst_id();
        int msg_type = message.getMsg_type();
        String msg = message.getMsg();
        if (msg_type == MsgType.REQUEST_LOCATION) {
            address = handleLocationRequest(src_id, dst_id);
        }
        return address;
    }

    // BroadCastMessage �� ����ϴ� ���̽��� ���������� �����û���ۿ� ����
    public ArrayList<DVM> requestBroadcastMessage(Message broadCastMessage) {
        int src_id = broadCastMessage.getSrc_id();
        int msg_type = broadCastMessage.getMsg_type();
        String msg = broadCastMessage.getMsg();
        if(msg_type == MsgType.REQUEST_STOCK){
            ArrayList<DVM> accessibleDVMList = handleStockRequest(src_id, msg);
            return accessibleDVMList;
        }
        return null;
    }

    private ArrayList<DVM> handleStockRequest(int src_id, String msg) {
        ArrayList<DVM> accessibleDVMList = new ArrayList<>();
        for(DVM dvm : dvmList){
            boolean isInStock = false;
            for(Drink drink: dvm.getDrink_list()){
                if(drink.getName().equals(msg)){
                    Message message = dvm.makeStockResponseMessage(src_id, drink.getStock()); // DVM�� ���� �޼����� ����
                    int stock = dvm.responseStockMessage(this, message); // DVM�� Network�� ���� ����޽����� �����ϰ� Network ���ο��� stock ���� ã�� ��������
                    if(stock != 0){
                        isInStock = true;
                        break;
                    }
                }
            }
            if(isInStock){
                accessibleDVMList.add(dvm);
            }
        }
        return accessibleDVMList;
    }

    public int responseBroadcastMessage(Message message) {
        int src_id = message.getSrc_id();
        String msg = message.getMsg();
        int stock = Integer.parseInt(msg);
        return stock;
    }

    public int responseNormalMessage(Message message) {
        int address = -1;
        int msg_type = message.getMsg_type();
        String msg = message.getMsg();
        if (msg_type == MsgType.RESPONSE_LOCATION) {
            address = Integer.parseInt(msg);
        }
        return address;
    }

    private int handleLocationRequest(int src_id, int dst_id) {
        int address = -1;
        for(DVM dvm : dvmList){
            if(dvm.getId() == dst_id) {
                Message message = dvm.makeLocationResponseMessage(src_id);
                address = dvm.responseLocationMessage(this, message);
                break;
            }
        }
        return address;
    }
}