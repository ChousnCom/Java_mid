package lesson_exception.except0;

// 사용자의 데이터의 처리 흐름을 담당

public class NetWorkServiceV0 {

    public void sendMessage(String data){
        String address = "https://example.com";
        NetWorkClientV0 client = new NetWorkClientV0(address);
        client.connect();
        client.send(data);
        client.disconnect();
    }
}
