package BookMyShowLLD;

public class Seat {
    private int id;
    private int row;
    private SeatCatergory seatCatergory;

    //constructor

    public Seat(int id, int row, SeatCatergory seatCatergory) {
        this.id = id;
        this.row = row;
        this.seatCatergory = seatCatergory;
    }

    //getter and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public SeatCatergory getSeatCatergory() {
        return seatCatergory;
    }

    public void setSeatCatergory(SeatCatergory seatCatergory) {
        this.seatCatergory = seatCatergory;
    }
}
