package com.concat.projetointegrador.service.validator;

import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.InboundOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class SectorCategoryMatchValidate implements Validator{

    private InboundOrder order;

    @Override
    public void validate() {
        for (BatchStock batchStock : order.getBatchStock()) {
            if (!batchStock.getProduct().getCategory().equals(order.getSector().getCategory())) {
                throw new RuntimeException("A categoria de todos os produtos deve ser igual ao do setor!");
            }
        }
    }
}
