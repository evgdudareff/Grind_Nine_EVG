package com.gridnine.Tests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Filter1 implements FlightFilter {

    LocalDateTime currentTime = LocalDateTime.now();
    List<Flight> resultFlights = new ArrayList<>();

    @Override
    public List<Flight> filter(List<Flight> flights) {
        resultFlights = flights
                .stream()
                .filter(flight -> flight
                                .getSegments()
                                .stream()
                                .noneMatch(segmentDate -> segmentDate.getDepartureDate().isBefore(currentTime)))
                .toList();

        return resultFlights;
    }

}
