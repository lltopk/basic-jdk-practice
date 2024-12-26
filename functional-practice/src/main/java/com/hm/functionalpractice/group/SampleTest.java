package com.hm.functionalpractice.group;

import com.hm.functionalpractice.group.entity.BillBo;
import com.hm.functionalpractice.group.enums.MergeFieldEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: hmly
 * @project: basic-jdk-practice
 * @Date: 2024/12/26 19:55
 */
@Slf4j
public class SampleTest {
    public static void main(String[] args) {
        List<BillBo> inputBills = buildInputBills();
        List<String> inputMergeKeys = buildInputMergeKeys();

        List<List<BillBo>> groupResultList = new ArrayList<>();
        List<List<BillBo>> groupInputList = new ArrayList<>();
        groupInputList.add(inputBills);

        groupProcess(groupResultList, groupInputList, inputMergeKeys);

        System.out.println("分组数：" + groupResultList.size());

        groupResultList.forEach(
                list -> log.info("组别：{}", list)
        );

    }

    /**
     * 为什么groupInputList是List<List<BillBo>>，多套了一层？
     * <p>
     * 因为递归逻辑中groupInputList必须是List<List<BillBo>>
     * @param groupResultList
     * @param groupInputList
     * @param mergeRuleKeywords
     */
    private static void groupProcess(List<List<BillBo>> groupResultList, List<List<BillBo>> groupInputList, List<String> mergeRuleKeywords) {


        groupInputList.forEach(groups -> {
            String mergeRuleKeyword = mergeRuleKeywords.get(0);
            List<List<BillBo>> splitGroupList = groupingBo(groups, mergeRuleKeyword);
            //1. 递归出口
            if (mergeRuleKeywords.size() == 1) {
                // 2. 分组路径结束，封装数据
                groupResultList.addAll(splitGroupList);
            } else {
                // 3. 继续下一步分组
                List<String> splitMergeRuleKeywords = mergeRuleKeywords.subList(1, mergeRuleKeywords.size());
                groupProcess(groupResultList, splitGroupList, splitMergeRuleKeywords);
            }
        });
    }

    /**
     * @param bills
     * @param mergeRuleKeyword
     * @return
     */
    private static List<List<BillBo>> groupingBo(List<BillBo> bills, String mergeRuleKeyword) {
        return groupingBo(bills, getFunction(mergeRuleKeyword));
    }

    /**
     * @param bills
     * @param function
     * @return
     */
    private static List<List<BillBo>> groupingBo(List<BillBo> bills, Function<BillBo, String> function) {
        ArrayList<List<BillBo>> groupOnce = new ArrayList<>();
        MergeFieldEnum mergeField = MergeFieldEnum.getByFunction(function);
        if (Objects.isNull(mergeField)) {
            throw new RuntimeException("不支持的合单规则,原因分析：不存在lambda表达式：" + mergeField.getName());
        }
        Map<String, List<BillBo>> collect = bills.stream().collect(Collectors.groupingBy(function));

        log.info("当前按照 {} 合单，分组结果：{}", mergeField.getName(), collect.keySet());
        collect.forEach((k, v) -> {
            groupOnce.add(v);
        });
        return groupOnce;
    }

    /**
     * @param mergeRuleKeyword
     * @return
     */
    private static Function<BillBo, String> getFunction(String mergeRuleKeyword) {
        MergeFieldEnum mergeField = MergeFieldEnum.getByField(mergeRuleKeyword);
        if (Objects.isNull(mergeField)) {
            throw new RuntimeException("不支持的合单规则：" + mergeRuleKeyword);
        }
        return mergeField.getFunction();
    }

    /**
     * @return
     */
    private static List<String> buildInputMergeKeys() {
        List<String> inputMergeKeys = new ArrayList<>();
        inputMergeKeys.add("BORN_CITY");
        inputMergeKeys.add("UNIVERSITY");
        inputMergeKeys.add("WORK_CITY");
        return inputMergeKeys;
    }

    /**
     * @return
     */
    private static List<BillBo> buildInputBills() {
        List<BillBo> inputBills = new ArrayList<>();
        BillBo billBo1 = new BillBo();
        billBo1.setId(1L);
        billBo1.setPrice(100D);
        billBo1.setDescription("description");
        billBo1.setAddress("address");
        billBo1.setPhone("18333333333");
        billBo1.setEmail("125xxxxxxx@qq.com");
        billBo1.setState("1");
        billBo1.setZip("1");
        billBo1.setSex(1);
        billBo1.setAge(18);
        billBo1.setName("张三");
        billBo1.setBornCity("南阳");
        billBo1.setUniversity("郑州大学");
        billBo1.setWorkCity("郑州");
        inputBills.add(billBo1);
        BillBo billBo2 = new BillBo();
        billBo2.setId(2L);
        billBo2.setPrice(100D);
        billBo2.setDescription("description");
        billBo2.setAddress("address");
        billBo2.setPhone("18333333333");
        billBo2.setEmail("125xxxxxxx@qq.com");
        billBo2.setState("1");
        billBo2.setZip("1");
        billBo2.setSex(1);
        billBo2.setAge(18);
        billBo2.setName("李四");
        billBo2.setBornCity("南阳");
        billBo2.setUniversity("郑州大学");
        billBo2.setWorkCity("郑州");
        inputBills.add(billBo2);

        BillBo billBo3 = new BillBo();
        billBo3.setId(3L);
        billBo3.setPrice(100D);
        billBo3.setDescription("description");
        billBo3.setAddress("address");
        billBo3.setPhone("18333333333");
        billBo3.setEmail("125xxxxxxx@qq.com");
        billBo3.setState("1");
        billBo3.setZip("1");
        billBo3.setSex(1);
        billBo3.setAge(18);
        billBo3.setName("王五");
        billBo3.setBornCity("南阳");
        billBo3.setUniversity("郑州大学");
        billBo3.setWorkCity("北京");
        inputBills.add(billBo3);

        BillBo billBo4 = new BillBo();
        billBo4.setId(4L);
        billBo4.setPrice(100D);
        billBo4.setDescription("description");
        billBo4.setAddress("address");
        billBo4.setPhone("18333333333");
        billBo4.setEmail("125xxxxxxx@qq.com");
        billBo4.setState("1");
        billBo4.setZip("1");
        billBo4.setSex(1);
        billBo4.setAge(18);
        billBo4.setName("张麻子");
        billBo4.setBornCity("南阳");
        billBo4.setUniversity("安徽大学");
        billBo4.setWorkCity("郑州");
        inputBills.add(billBo4);

        BillBo billBo5 = new BillBo();
        billBo5.setId(5L);
        billBo5.setPrice(100D);
        billBo5.setDescription("description");
        billBo5.setAddress("address");
        billBo5.setPhone("18333333333");
        billBo5.setEmail("125xxxxxxx@qq.com");
        billBo5.setState("1");
        billBo5.setZip("1");
        billBo5.setSex(1);
        billBo5.setAge(18);
        billBo5.setName("问占山");
        billBo5.setBornCity("杭州");
        billBo5.setUniversity("郑州大学");
        billBo5.setWorkCity("郑州");
        inputBills.add(billBo5);

        return inputBills;
    }
}
