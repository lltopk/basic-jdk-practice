package com.hm.functionalpractice.group.enums;

import com.hm.functionalpractice.group.entity.BillBo;
import lombok.Getter;

import java.util.function.Function;

/**
 * @Description:
 * @Author: hmly
 * @project: basic-jdk-practice
 * @Date: 2024/12/26 20:13
 */
@Getter
public enum MergeFieldEnum {

    BORN_CITY("BORN_CITY", "按照出生地合单", BillBo::getBornCity),
    UNIVERSITY("UNIVERSITY", "按照就读大学合单", BillBo::getUniversity),
    WORK_CITY("WORK_CITY", "按照工作地合单", (BillBo billBo) -> billBo.getWorkCity()),
    ;
//
//

    private String field;
    private String name;
    private Function<BillBo, String> function;

    MergeFieldEnum(String field, String name, Function<BillBo, String> function) {
        this.field = field;
        this.name = name;
        this.function = function;
    }

    /**
     *
     * @param field
     * @return
     */
    public static MergeFieldEnum getByField(String field) {
        for (MergeFieldEnum mergeFieldEnum : MergeFieldEnum.values()) {
            if (mergeFieldEnum.field.equals(field)) {
                return mergeFieldEnum;
            }
        }
        return null;
    }


    /**
     *
     * @param function
     * @return
     */
    public static MergeFieldEnum getByFunction(Function<BillBo, String> function) {
        for (MergeFieldEnum mergeFieldEnum : MergeFieldEnum.values()) {
            if (mergeFieldEnum.function.equals(function)) {
                return mergeFieldEnum;
            }
        }
        return null;
    }
}
