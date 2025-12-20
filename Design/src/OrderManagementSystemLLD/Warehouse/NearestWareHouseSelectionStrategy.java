package OrderManagementSystemLLD.Warehouse;

import java.util.List;

public class NearestWareHouseSelectionStrategy extends WarehouseSelectionStrategy{
    @Override
    public Warehouse selectWarehouse(List<Warehouse> warehouseList) {
        return warehouseList.getFirst();
    }
}
