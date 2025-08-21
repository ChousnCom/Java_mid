package lesson_exception.ex1;

//오류 상황 시뮬레이션
//사용자가 입력한 데이터에 error1 -> connectError!
//사용자가 입력한 데이터에 error2 -> sendError
public class NetworkClientV1 {
    private final String address;
    public boolean connectError;
    public boolean sendError;

    public NetworkClientV1(String address) {
        this.address = address;
    }

    public String connect(){
        if(connectError){
            System.out.println(address + " 서버 연결 실패!");
            return "connectError!";
        }

        System.out.println(address + " 서버 연결 성공!");
        return "success";
    }

    public String send(String data){
        if(sendError){
            System.out.println(address + " 데이터 전송 실패!! " + data);
            return "sendError!";
        }
        System.out.println(address + " 데이터 전송 성공! " + data);
        return "success";
    }

    public void disconnect(){
        System.out.println(address + " 서버 연결 해제");
    }

    public void initError(String data){
        if(data.contains("error1")){
            connectError = true;
        }
        if(data.contains("error2")){
            sendError = true;
        }
    }

}
