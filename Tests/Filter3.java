package com.gridnine.Tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Filter3 implements FlightFilter {

    long maxTimeOnEarth = 2;
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
                currSegmentsTotalTimeOnEarth = currSegmentsTotalTimeOnEarth + Duration.between(currSegment.getArrivalDate(), nextSegment.getDepartureDate()).toHours();
                if (currSegmentsTotalTimeOnEarth > maxTimeOnEarth) {
                    break;
                }
            }

            if (currSegmentsTotalTimeOnEarth <= maxTimeOnEarth) {
                resultFlights.add(flight);
            }
        }

        return resultFlights;
    }


}
