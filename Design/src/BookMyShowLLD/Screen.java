package BookMyShowLLD;

import java.util.List;
import java.util.Objects;

public class Screen {
    int id;
    List<Seat> seatList;

    public Screen(int id, List<Seat> seatList) {
        this.id = id;
        this.seatList = seatList;
    }

    // getter and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public Seat getSeatById(int id){
        return seatList.stream().filter(seat-> Objects.equals(id,seat.getId())).findFirst().orElse(null);
    }
}
