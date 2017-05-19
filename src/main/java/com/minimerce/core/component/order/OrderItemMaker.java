package com.minimerce.core.component.order;

import com.minimerce.core.component.order.item.UsableOrderItemMaker;
import com.minimerce.core.domain.deal.option.DealOption;
import com.minimerce.core.domain.order.item.OrderItem;
import com.minimerce.core.object.type.DealType;
import com.minimerce.core.support.exception.UnsupportedItemTypeException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by gemini on 01/04/2017.
 */
@Component
public class OrderItemMaker {
    private final UsableOrderItemMaker usableOrderItemMaker;

    @Inject
    public OrderItemMaker(UsableOrderItemMaker usableOrderItemMaker) {
        this.usableOrderItemMaker = usableOrderItemMaker;
    }

    public List<OrderItem> make(DealOption option) throws UnsupportedItemTypeException {
        if(DealType.USABLE == option.getType()) return usableOrderItemMaker.make(option);
        throw new UnsupportedItemTypeException("지원하지 않는 상품 타입입니다.");
    }
}