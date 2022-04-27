package com.concat.projetointegrador.service.validator;

import com.concat.projetointegrador.model.BatchStock;
import com.concat.projetointegrador.model.InboundOrder;
import com.concat.projetointegrador.repository.BatchStockRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ValidateSectorCapacity implements Validator {

    private InboundOrder order;
    private BatchStockRepository repository;

    private int calculateBatchVolume(List<BatchStock> batchStockList, boolean initial) {
        if (initial) {
            return order.getBatchStock().stream().reduce(0, (acc, e) -> acc + (e.getProduct().getVolume() * e.getInitialQuantity()), Integer::sum);

        }

        return order.getBatchStock().stream().reduce(0, (acc, e) -> acc + (e.getProduct().getVolume() * e.getCurrentQuantity()), Integer::sum);

    }

    @Override
    public void validate() {
        int volume;
        int orderVolume;
        boolean volumeSmallerThanNewBatch;
        boolean newVolumeSmallerThanInitialCapacity;

        List<BatchStock> batchStocksBySector = repository.findAllByInboundOrderSectorId(order.getSector().getId());

        volume = calculateBatchVolume(batchStocksBySector, false);
        orderVolume = calculateBatchVolume(order.getBatchStock(), true);

        volumeSmallerThanNewBatch = orderVolume > order.getSector().getCapacity() - volume;
        newVolumeSmallerThanInitialCapacity = volume > order.getSector().getCapacity();

        if (volumeSmallerThanNewBatch || newVolumeSmallerThanInitialCapacity) {
            throw new RuntimeException("Capacidade total já atingida!");
        }
    }
}
