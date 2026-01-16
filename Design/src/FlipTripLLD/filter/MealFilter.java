package FlipTripLLD.filter;

import FlipTripLLD.model.Flight;

public class MealFilter implements FlightFilter{


    @Override
    public boolean test(Flight flight) {
        return flight.isHasMeal();
    }
}
