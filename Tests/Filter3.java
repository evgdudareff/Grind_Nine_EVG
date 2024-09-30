package com.gridnine.Tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Filter3 implements FlightFilter {

    // 2 часа
    long maxTimeOnEarthMs = 7200000;
    List<Flight> resultFlights = new ArrayList<>();

    @Override
    public List<Flight> filter(List<Flight> flights) {

        for (Flight flight : flights) {
            long currSegmentsTotalTimeOnEarth = 0;
            List<Segment> segments = flight.getSegments();
            int segmentsSize = segments.size();

            if (segmentsSize == 1) {
                resultFlights.add(flight);
                continue;
            }

            for (int i = 0; i < segmentsSize - 1; i++) {
                Segment currSegment = segments.get(i);
                Segment nextSegment = segments.get(i + 1);
                currSegmentsTotalTimeOnEarth = currSegmentsTotalTimeOnEarth + Duration.between(currSegment.getArrivalDate(), nextSegment.getDepartureDate()).toMillis();
                if (currSegmentsTotalTimeOnEarth > maxTimeOnEarthMs) {
                    break;
                }
            }

            if (currSegmentsTotalTimeOnEarth <= maxTimeOnEarthMs) {
                resultFlights.add(flight);
            }
        }

        return resultFlights;
    }


}
