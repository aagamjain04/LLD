package OrderManagementSystemLLD.Controller;

import OrderManagementSystemLLD.Warehouse.Warehouse;
import OrderManagementSystemLLD.Warehouse.WarehouseSelectionStrategy;

import java.util.List;

public class WarehouseController {

    List<Warehouse> warehouseList;
    WarehouseSelectionStrategy warehouseSelectionStrategy = null;

    public WarehouseController(List<Warehouse> warehouseList, WarehouseSelectionStrategy warehouseSelectionStrategy){
        this.warehouseList = warehouseList;
        this.warehouseSelectionStrategy = warehouseSelectionStrategy;
    }

    // add new warehouse
    public void addWarehouse(Warehouse warehouse){
        warehouseList.add(warehouse);
    }

    //remove warehouse
    public void removeWarehouse(Warehouse warehouse){
        warehouseList.remove(warehouse);
    }

    // get warehouse based on strategy
    public Warehouse getWarehouse(WarehouseSelectionStrategy selectionStrategy){
        this.warehouseSelectionStrategy = selectionStrategy;
        return this.warehouseSelectionStrategy.selectWarehouse(warehouseList);
    }
}
