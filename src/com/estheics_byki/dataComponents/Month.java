package com.estheics_byki.dataComponents;

public class Month {
    private Week[] weeks = new Week[4];
    private int currIdx = 0;

    public Month() {
        for (int i=0; i < 4; i++) {
            weeks[i] = new Week();
        }
    }

    public Week[] getWeeks() {
        return weeks;
    }

    public Week getCurrWeek() {
        return weeks[currIdx];
    }

    public int moveWeek(int dir) {
    //if dir is positive increase week else go back
        if (dir < 0 && currIdx < 3) {
            //animates down
            currIdx++;
            return 1;
        } else if(currIdx > 0 && dir > 0) {
            //animates up
            currIdx--;
            return -1;
        } else {
            //don't move
            return 0;
        }
    }
}
