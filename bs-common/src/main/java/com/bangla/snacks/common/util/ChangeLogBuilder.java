package com.bangla.snacks.common.util;

import com.bangla.snacks.common.change.log.model.ChangeLogDB;

public class ChangeLogUtil {
    private ChangeLogUtil() {

    }
    private ChangeLogDB changeLog;

    public static ChangeLogUtil newBuilder() {
        return new ChangeLogUtil();
    }
    public ChangeLogUtil start(String changeBy) {
        this.changeLog = new ChangeLogDB();
        changeLog.setChangeBy(changeBy);
        return this;
    }

}
