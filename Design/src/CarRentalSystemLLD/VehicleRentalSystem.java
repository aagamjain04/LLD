package CarRentalSystemLLD;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VehicleRentalSystem {
    List<User> users;
    List<Store>stores;

    public VehicleRentalSystem() {
        this.users = new ArrayList<>();
        this.stores = new ArrayList<>();
    }

    public void addUser(User user){
        users.add(user);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public void addStore(Store store){
        stores.add(store);
    }

    public void removeStore(Store store){
        stores.remove(store);
    }

    public Store getStore(Location location){
        return (stores.stream().filter(store -> store!=null && Objects.equals(store.getLocation(),location)).findFirst().get());
    }
    public List<Store> getStores() {
        return stores;
    }

    public List<User> getUsers() {
        return users;
    }
}
