package org.example.section15.treesetchallenge;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Theatre {

    private final String name;
    private final int seatsPerRow;
    private final int seatsTotal;
    private final NavigableSet<Seat> seats = new TreeSet<>();

    public Theatre(String name, int seatsPerRow, int seatsTotal) {
        this.name = name;
        this.seatsPerRow = seatsPerRow;
        this.seatsTotal = seatsTotal;

        for (int i = 0; i < seatsTotal; i++) {
            char rowChar = (char) ((i / this.seatsPerRow) + (int) 'A');
            int rowNumber = (i % this.seatsPerRow) + 1;
            seats.add(new Seat(rowChar, rowNumber, false));
        }
    }

    public void printSeatMap() {
        System.out.println("Map of " + this.name);
        System.out.println("-".repeat(this.seatsPerRow * 5));
        int row = 0;
        for (Seat seat : seats) {
            System.out.print(seat + " ");
            if (++row % seatsPerRow == 0) System.out.println();
        }
        System.out.println("-".repeat(this.seatsPerRow * 5));
    }

    public boolean bookSeat(char row, int seatNumber) {
        Seat toBeBooked = new Seat(row, seatNumber, false);
        if (!seats.contains(toBeBooked)) return false;
        if (seats.floor(toBeBooked).reserved) return false;
        seats.floor(toBeBooked).setReserved(true);
        return true;
    }

    public boolean bookMultipleSeats(int numOfSeats, char startRowRange, char endRowRange, int startSeatRange, int endSeatRange) {
        //ranges are inclusive!!
        if ((startRowRange > endRowRange) ||
                (endRowRange > 65 + (seatsTotal / seatsPerRow)) ||
                (startSeatRange > endSeatRange) ||
                ((endSeatRange - startSeatRange + 1) < numOfSeats) ||
                (endSeatRange > seatsPerRow)) return false;

        for (char row = startRowRange; row <= endRowRange; row++) {
            for (int seatNum = startSeatRange; seatNum <= endSeatRange - numOfSeats + 1; seatNum++) {
                if (isSeatSelectionValid(numOfSeats, row, seatNum)) {
                    for (int i = 0; i < numOfSeats; i++) {
                        Seat toBeBooked = new Seat(row, seatNum + i, false);
                        seats.floor(toBeBooked).setReserved(true);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSeatSelectionValid(int numOfSeats, char row, int seatNum) {
        for (int i = 0; i < numOfSeats; i++) {
            Seat seat = new Seat(row, seatNum + i, false);
            if (seats.floor(seat).reserved) return false;
        }
        return true;
    }

    public boolean bookMultipleSeats(int numOfSeats, char startRowRange, char endRowRange) {
        return bookMultipleSeats(numOfSeats, startRowRange, endRowRange, 1, seatsPerRow);
    }

    private class Seat implements Comparable<Seat> {
        private final char row;
        private final int number;
        private boolean reserved;

        public Seat(char row, int number, boolean reserved) {
            this.row = row;
            this.number = number;
            this.reserved = reserved;
        }

        @Override
        public String toString() {
            if (reserved) {
                return "(%s%03d)".formatted(row, number);
            } else {
                return " %s%03d ".formatted(row, number);
            }
        }


        @Override
        public int compareTo(Seat o) {
            return this.row == o.row ?
                    Integer.compare(this.number, o.number) : Integer.compare(this.row, o.row);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Seat seat = (Seat) o;

            if (row != seat.row) return false;
            return number == seat.number;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + number;
            return result;
        }

        public void setReserved(boolean reserved) {
            this.reserved = reserved;
        }
    }
}
