package FlipTripLLD.model;

public class Flight {
    String airline;
    String src;
    String dest;
    int price;
    boolean hasMeal;

    public Flight(String airline, String src, String dest, int price, boolean hasMeal) {
        this.airline = airline;
        this.src = src;
        this.dest = dest;
        this.price = price;
        this.hasMeal = hasMeal;
    }

    public String getAirline() {
        return airline;
    }

    public String getSrc() {
        return src;
    }

    public String getDest() {
        return dest;
    }

    public int getPrice() {
        return price;
    }

    public boolean isHasMeal() {
        return hasMeal;
    }
}
