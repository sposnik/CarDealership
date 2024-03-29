package pl.zajavka.business.services.utils;

public interface Keys {

    enum Domains implements Keys {
        SALESMAN,
        MECHANIC,
        PART,
        CUSTOMER,
        CAR,
        SERVICE
    }

    enum Simulation implements Keys {
        BUY_FIRST_TIME,
        BUY_AGAIN,
        SERVICE_REQUEST,
        DO_THE_SERVICE
    }
    enum Properties implements Keys{
        WHAT,
        FINISHED,
        NOT_FINISHED
    }

}
