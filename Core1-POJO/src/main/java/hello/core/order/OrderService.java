package hello.core.order;

public interface OrderService {
    // 역할: 주문 생성
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
