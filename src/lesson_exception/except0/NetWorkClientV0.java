package lesson_exception.except0;

//네트워크 클라이언트 클래스 -> 세부적인 모듈이 존재
//connect, send, disconnect
public class NetWorkClientV0 {
    private final String address; //주소는 한번 설정되면 바뀌면 안됨

    public NetWorkClientV0(String address) {
        this.address = address;
    }

    // 외부 서버와 연결
    public String connect(){
        //연결 성공
        System.out.println(address + " 연결성공");
        return "success";
    }

    //연결한 외부 서버에 데이터 보냄
    public String send(String data){
        //데이터 전송 성공!
        System.out.println(address + " 데이터 전송 성공");

        return "success";
    }

    //연결 해제
    public void disconnect(){
        System.out.println(address + " 연결 종료");
    }

}
