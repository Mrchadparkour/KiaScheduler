package sample.dataComponents;

public class Month {
    private Week[] weeks = new Week[4];

    final static String[][] colorChoices = new String[][] {
            { "#3498db", "#2980b9", "#8e44ad", "#9b59b6" }, //blues & purples
            { "#1abc9c", "#2ecc71", "#16a085", "#27ae60" }, //greens
            { "#f1c40f", "#e67e22", "#e74c3c", "#f39c12", "#d35400", "#c0392b" } //warms
    };

    public Month() {
        for (int i=0; i < 4; i++) {
            weeks[i] = new Week(randomColor());
        }
    }

    private String randomColor() {
        String[] colorArr = colorChoices[(int)(Math.random() * 3)];
        return colorArr[(int)(Math.random() * colorArr.length)];
    }

    private int currIdx = 0;

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
