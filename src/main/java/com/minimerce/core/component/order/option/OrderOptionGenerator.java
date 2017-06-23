package com.minimerce.core.component.order.option;

import com.minimerce.core.component.order.option.usable.UsableOrderOptionGenerator;
import com.minimerce.core.domain.deal.option.Option;
import com.minimerce.core.domain.deal.option.usable.UsableOption;
import com.minimerce.core.domain.order.option.OrderOption;
import com.minimerce.core.object.deal.type.ProductType;
import com.minimerce.core.support.exception.MinimerceException;
import com.minimerce.core.support.response.ErrorCode;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by gemini on 14/06/2017.
 */
@Component
public class OrderOptionGenerator {
    private final UsableOrderOptionGenerator usableOrderOptionGenerator;

    @Inject
    public OrderOptionGenerator(UsableOrderOptionGenerator usableOrderOptionGenerator) {
        this.usableOrderOptionGenerator = usableOrderOptionGenerator;
    }

    public OrderOption generate(Long clientId, Option option) {
        if(ProductType.USABLE == option.getType()) return usableOrderOptionGenerator.generate(clientId, (UsableOption) option);
        throw new MinimerceException(ErrorCode.NOT_SUPPORTED_PRODUCT_TYPE);
    }
}
