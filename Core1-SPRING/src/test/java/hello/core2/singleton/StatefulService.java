package hello.core2.singleton;

// 문제 발생 가능성이 있는 싱글톤
public class StatefulService {

    // 상태를 유지하는(Stateful) 필드
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);

        // stateful 필드에 발생하는 문제
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
