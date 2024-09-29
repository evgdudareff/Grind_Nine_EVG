package com.gridnine.Tests;

import java.util.ArrayList;
import java.util.List;

import static com.gridnine.Tests.FlightBuilder.createFlights;

public class Filter2 implements FlightFilter {
    List<Flight> resultFlights = new ArrayList<>();

    @Override
    public List<Flight> filter(List<Flight> flights) {
        resultFlights = flights
                .stream()
                .filter(flight -> flight
                        .getSegments()
                        .stream()
                        .allMatch(segmentDate -> segmentDate.getDepartureDate().isBefore(segmentDate.getArrivalDate())))
                .toList();

        return resultFlights;
    }
}
