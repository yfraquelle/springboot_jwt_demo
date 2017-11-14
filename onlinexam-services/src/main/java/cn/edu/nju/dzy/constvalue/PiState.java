package cn.edu.nju.dzy.constvalue;

/**
 * Created by YU Fan on 2017/5/23.
 */
public enum PiState {
    ALARMING(1),//having an alarm
    NOTALARM(0),//not having an alarm
    NOALARM(-1),//can't have an alarm
    INVALID(-2);
    private final int value;

    PiState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static PiState getEnum(int value)
    {
        switch(value)
        {
            case -1:
                return ALARMING;
            case 0:
                return NOTALARM;
            case 1:
                return NOALARM;
            default:
                return INVALID;
        }
    }
}

