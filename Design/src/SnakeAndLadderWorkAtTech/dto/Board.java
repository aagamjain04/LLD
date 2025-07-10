package dto;

public class Board {
    int start;
    int end;
    int size;
    public Board(int start, int end, int size) {
        this.start = start;
        this.end = end;
        this.size = size;
    }



    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
