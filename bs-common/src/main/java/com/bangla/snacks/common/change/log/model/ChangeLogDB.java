package com.bangla.snacks.common.change.log.model;

import com.bangla.snacks.common.constants.DBConstants;
import com.bangla.snacks.common.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@Entity(name = DBConstants.TABLE_CHANGE_LOG_DB)
public class ChangeLogDB implements Serializable, DBConstants {
    private static final long serialVersionUID = -48923818919869239L;

    public ChangeLogDB() {
        this.changeLogId = String.format("CHG%s", CommonUtil.generateId());
    }

    @Id
    @Column(name = COL_CHANGE_LOG_DB_ID, length = 20)
    private String changeLogId;

    @Column(name = COL_CHANGE_LOG_DB_CHG_DT, length = 50, nullable = false)
    private String changeDate;

    @Column(name = COL_CHANGE_LOG_DB_CHG_BY, length = 20, nullable = false)
    private String changeBy;

    @Column(name = COL_CHANGE_LOG_DB_CHG_DESC, length = 1024, nullable = false)
    private String changeDescription;

    @Override
    public String toString() {
        return "ChangeLogDB{" +
                "changeLogId='" + changeLogId + '\'' +
                ", changeDate='" + changeDate + '\'' +
                ", changeBy='" + changeBy + '\'' +
                ", changeDescription='" + changeDescription + '\'' +
                '}';
    }
}
