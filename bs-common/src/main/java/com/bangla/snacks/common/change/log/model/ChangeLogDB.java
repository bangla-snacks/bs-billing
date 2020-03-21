package com.bangla.snacks.common.change.log;

import com.bangla.snacks.common.constants.DBConstants;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = DBConstants.TABLE_CHANGE_LOG_DB)
public class ChangeLogDB implements Serializable, DBConstants {
    @Id
    @Column(name = COL_CHANGE_LOG_DB_ID, length = 20)
    private String changeLogId;

    @Column(name = COL_CHANGE_LOG_DB_CHG_DT, length = 20, nullable = false)
    private String changeDate;

    @Column(name = COL_CHANGE_LOG_DB_CHG_BY, length = 20, nullable = false)
    private String changeBy;

    @Column(name = COL_CHANGE_LOG_DB_CHG_DESC, length = 1024, nullable = false)
    private String changeDescription;
}
