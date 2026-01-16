package FlipRideSharingLLD.model;

public class User {
    String name;
    public int ridesTaken;
    public int ridesOffered;

    public User(String name){
        this.name = name;
        this.ridesTaken = 0;
        this.ridesOffered = 0;
    }


}
