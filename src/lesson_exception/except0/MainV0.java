package lesson_exception.except0;

import java.util.Scanner;

public class MainV0 {
    public static void main(String[] args) {
        NetWorkServiceV0 service = new NetWorkServiceV0();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("전송할 문자 : ");
            String inputData = sc.nextLine();
            if(inputData.equals("exit")){
                break;
            }
            service.sendMessage(inputData);
            System.out.println();
        }
        System.out.println("프로그램을 정상 종료 합니다.");
    }
}
