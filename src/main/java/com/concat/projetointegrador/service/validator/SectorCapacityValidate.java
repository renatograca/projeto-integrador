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
public class SectorCapacityValidate implements Validator {

    private InboundOrder order;
    private BatchStockRepository repository;

    private Integer calculateBatchVolume(List<BatchStock> batchStockList, boolean initial) {
        if (initial) {
            return batchStockList.stream().reduce(0, (acc, e) -> acc + (e.getProduct().getVolume() * e.getInitialQuantity()), Integer::sum);
        }

        return batchStockList.stream().reduce(0, (acc, e) -> acc + (e.getProduct().getVolume() * e.getCurrentQuantity()), Integer::sum);

    }

    @Override
    public void validate() {
        Integer volume;
        Integer orderVolume;
        boolean volumeSmallerThanNewBatch;
        boolean newVolumeSmallerThanInitialCapacity;

        List<BatchStock> batchStocksBySector = repository.findAllByInboundOrderSectorId(order.getSector().getId());

        volume = calculateBatchVolume(batchStocksBySector, false);
        orderVolume = calculateBatchVolume(order.getBatchStock(), true);

        volumeSmallerThanNewBatch = orderVolume > order.getSector().getCapacity() - volume;
        newVolumeSmallerThanInitialCapacity = volume >= order.getSector().getCapacity();

        if (volumeSmallerThanNewBatch || newVolumeSmallerThanInitialCapacity) {
            throw new RuntimeException("Capacidade total já atingida!");
        }
    }
}
