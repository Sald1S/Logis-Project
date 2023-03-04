package kz.bitlab.logistic.constrants;

public enum RequestStatus {
    INPROCESS(0),
    SENT(1),
    DELIVERED(2);

    private final int intValue;

    RequestStatus(int intValue){
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }
}
