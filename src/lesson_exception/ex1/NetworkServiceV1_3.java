package lesson_exception.ex1;

//기존 서비스층에서는 오류가 발생해도 데이터가 발송되는 문제
//오류 내용을 남기면 디버깅에 도움이 될 것.

public class NetworkServiceV1_3 {
    public void sendMessage(String data){
        String address = "https://example.com";
        NetworkClientV1 client = new NetworkClientV1(address);
        client.initError(data);

        String connectResult = client.connect();
        if(isError(connectResult)){
            System.out.println("네트워크에 오류 발생!! 오류 코드 : " + connectResult);
        }else{
            String sendResult = client.send(data);
            if(isError(sendResult)){
                System.out.println("데이터 전송에 오류 발생!! 오류 코드 : " + sendResult);
            }
        }
        client.disconnect();

    }
    private static boolean isError(String resultCode){
        return !resultCode.equals("success");
    }
}

// 사용 후에는 반드시 disconnect을 호출해야 함.
// 그러나 해당 클래스에서는 실패해도 연결이 계속 유지됨 -> disconnect 호출 안함.
// 실제 네트워크 상에서는 네트워크 연결 자원이 고갈 될 수 있음
// 여기서는 적절한 분기를 사용해 disconnect이 오류가 나던 안나던 간에 무조건 한번은 호출 되게 만들었음.
