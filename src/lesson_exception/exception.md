# -예외 처리가 필요한 이유?-

```java
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
```
#### 실제 네트워크 통신은 아니자만 임의로 구성한 코드이다. 다음과 같은 코드를 볼때, 한눈에 정상 흐름과, 예외의 흐름이 어느 부분인지 이해가 되는가?
#### 오직 반환값으로만 예외를 처리한다면 코드는 더 복잡해지고 가독성이 떨이질 것이 분명.

#### 정상 흐름과 예외흐름이 한 코드에 전부 섞여 있기 때문에 결국 코드를 한 눈에 이해하기 어렵다.
#### 심지어 실무에서는 예외 흐름이 더 많은 분량의 코드를 차지할텐데, 어떻게 하면 예외처리와 정상 흐름을 분리할 수 있을까?
#### 우리는 예외처리를 사용하여 정상 흐름과 예외 흐름을 명확하게 분리 할 수 있다.

# -예외 계층-

#### 자바는 프로그램 중에 발생할 수 있는 예외를 처리하기 위한 메커니즘을 제공.

#### 자바의 예외 처리 키워드
#### -> try, catch, finally, throw, throws.

### Throwable ==> 최상위 예외. {하위에 : Exception, Error}
#### 해당 계층 하위에 Exception과 Error가 존재.

### Error
#### -> 메모리 부족이나 심각한 시스템 오류와 같이 어플리케이션 복구가 불가능한 시스템 예외. 
#### 개발자는 이 예외를 잡으려고 해서는 안됨. -> 어차피 해결 불가.

### Exception(체크 예외)->{하위에 : SQLException, IOException}
#### 어플리케이션 로직에서 사용할 수 있는 실질적인 최상위 예외.
#### Exception과 그 하위 예외는 모두 컴파일러가 체크하는 예외. 단, RuntimeException은 제외.

### RuntimeException(런타임 예외, 언체크 예외)
#### 컴파일러가 체크하지 않는 언체크 예외.
#### RuntimeException과 그 자식 예외(NPE,IllegalArgumentException)들은 모두 언체크 예외임.
#### RuntimeException과 그 하위 언체크 예외를 런타임 예외라고 많이 부름.

### -언체크 예외?(런타임 예외) vs 체크 예외-
#### 체크예외(Exception) 은 개발자가 명시적으로 처리해야 함. -> 그렇지 않으면 컴파일 오류가 발생.
#### 언체크예외(RuntimeException) 은 개발자가 예외를 명시적으로 처리하지 않아도 됨.

### -주의-
#### 상속 관계에서 보았듯 부모는 자식을 담을 수 있음, 예외도 마찬가지로 적용.
#### 상위 예외를 catch로 잡으면 그 하위 예외들은 자동으로 잡히게 됨. 실제 어플리케이션 로직에서 Throwable은 잡으면 안되는데.
#### Throwable을 잡으면 그 하위 예외인 Exception은 물론 잡히면 안되는 Error예외도 함께 잡을 수 있기 때문임.
#### 따라서 우리는 실제 어플리케이션에서 예외를 처리할때 Exception 부터 처리 가능한 예외라고 생각하고 예외 처리를 하면 됨.


