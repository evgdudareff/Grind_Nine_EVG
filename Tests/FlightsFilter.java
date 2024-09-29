package com.gridnine.Tests;

import java.util.HashMap;
import java.util.List;

import static com.gridnine.Tests.FlightBuilder.createFlights;
import static com.gridnine.Tests.FilterType.*;

public class FlightsFilter {

    public HashMap<FilterType, FlightFilter> filterNameToFilterImplMap = new HashMap<>() {{
        put(FILTER_FLIGHTS_WITH_DEPARTURE_IS_BEFORE_NOW, new Filter1());
        put(FILTER_FLIGHTS_WITH_ARRIVAL_IS_BEFORE_DEPARTURE, new Filter2());
        put(FILTER_FLIGHTS_WITH_TOTAL_ON_EARTH_TIME_IS_GREATER_THAN_2_HOURS, new Filter3());
    }};

    public List<Flight> filter(List<Flight> listFlights, FilterType filterName) {
        FlightFilter flightFilter = filterNameToFilterImplMap.get(filterName);
        return flightFilter.filter(listFlights);
    }

    public static void main(String[] args) {
        FlightsFilter flightsFilter = new FlightsFilter();

        List<Flight> filteredFlights1 = flightsFilter.filter(createFlights(), FILTER_FLIGHTS_WITH_DEPARTURE_IS_BEFORE_NOW);
        System.out.println(filteredFlights1);

        List<Flight> filteredFlights2 = flightsFilter.filter(createFlights(), FILTER_FLIGHTS_WITH_ARRIVAL_IS_BEFORE_DEPARTURE);
        System.out.println(filteredFlights2);

        List<Flight> filteredFlights3 = flightsFilter.filter(createFlights(), FILTER_FLIGHTS_WITH_TOTAL_ON_EARTH_TIME_IS_GREATER_THAN_2_HOURS);
        System.out.println(filteredFlights3);

    }
}
