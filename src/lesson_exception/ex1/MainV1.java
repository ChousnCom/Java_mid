package lesson_exception.ex1;

import java.util.Scanner;

public class MainV1 {
    public static void main(String[] args) {
        //NetworkServiceV1 service = new NetworkServiceV1();
        //NetworkServiceV1_2 service = new NetworkServiceV1_2();
        NetworkServiceV1_3 service = new NetworkServiceV1_3();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("전솓할 메시지 : ");
            String inputData = sc.nextLine();
            if(inputData.equals("exit")){
                break;
            }
            service.sendMessage(inputData);
            System.out.println();
        }
        System.out.println("프로그램을 종료합니다.");
    }
}
