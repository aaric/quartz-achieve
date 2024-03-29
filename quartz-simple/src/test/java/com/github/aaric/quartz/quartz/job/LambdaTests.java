package com.github.aaric.quartz.quartz.job;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LambdaTests
 *
 * @author Aaric, created on 2022-04-27T14:19.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
public class LambdaTests {

    @Data
    @Accessors(chain = true)
    public static class UserVo {
        private Integer id;
        private String name;
        private Integer age;
        private String tags;
        private List<String> petList;

        private Integer parentId;
        private Integer level;
        private String remark;

        public static int tagsIdx(UserVo o) {
            int rtnIdx = 0;
            if (null != o && null != o.getTags() && !o.getTags().isEmpty()) {
                switch (o.getTags()) {
                    case "GOOD":
                        rtnIdx = 1;
                        break;
                    case "NICE":
                        rtnIdx = 2;
                        break;
                    case "BEST":
                        rtnIdx = 3;
                        break;
                    default:
                }
            }
            return rtnIdx;
        }

        @Deprecated
        public static int tagsSort(UserVo o1, UserVo o2) {
            return tagsIdx(o1) - tagsIdx(o2);
        }
    }

    private static List<UserVo> userVoList = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        userVoList.add(new UserVo().setId(1).setParentId(null).setLevel(0).setName("zhangsan").setAge(18).setTags("GOOD").setPetList(Arrays.asList("cat")));
        userVoList.add(new UserVo().setId(2).setParentId(null).setLevel(0).setName("lisi").setAge(24).setTags("BEST").setPetList(Arrays.asList("cat", "dog")));
        userVoList.add(new UserVo().setId(3).setParentId(1).setLevel(1).setName("wangwu").setAge(24).setTags("GOOD").setPetList(Arrays.asList("bird")));
        userVoList.add(new UserVo().setId(4).setParentId(2).setLevel(1).setName("zhaoliu").setAge(26).setTags("GOOD").setPetList(Arrays.asList("dog")));
        userVoList.add(new UserVo().setId(5).setParentId(2).setLevel(1).setName("tianqi").setAge(25).setTags("GOOD").setPetList(Arrays.asList("dog", "bird")));
        userVoList.add(new UserVo().setId(5).setParentId(3).setLevel(2).setName("tianqi2").setAge(24).setTags("NICE").setPetList(Arrays.asList("cat")));
        userVoList.add(new UserVo().setId(6).setParentId(5).setLevel(3).setName(null).setAge(22).setTags("GOOD").setPetList(Arrays.asList("dog")));
        userVoList.add(new UserVo().setId(null).setParentId(null).setLevel(0).setName("null").setAge(22).setTags("GOOD").setPetList(Arrays.asList("bird")));
    }

    @Test
    public void testList2Map() {
        Map<Integer, String> collect = userVoList.stream()
//                .filter(o -> StringUtils.isNotBlank(o.getName()))
//                .collect(Collectors.toMap(UserVo::getId, UserVo::getName));
                .collect(Collectors.toMap(o -> o.getId(), o -> o.getName(), (o1, o2) -> o2));
//                .collect(HashMap::new, (map, o) -> map.put(o.getId(), o.getName()), HashMap::putAll);
        log.info("collect -> {}", collect);
    }

    @Test
    public void testList2Set() {
        Set<String> collect = userVoList.stream()
//                .map(o -> o.getPetList())
                .flatMap(o -> o.getPetList().stream())
//                .collect(ArrayList::new, (list, o)-> list.add(o), ArrayList::addAll);
                .collect(Collectors.toSet());

        log.info("collect -> {}", collect);
    }

    @Test
    public void testList2Sort() {
        userVoList.stream()
                .filter(o -> null != o.getId() && null != o.getName())
//                .sorted(Comparator.comparing(UserVo::getAge).thenComparing(UserVo::getName))
                // age -> tags(GOOD, NICE, BEST)
//                .sorted(Comparator.comparing(UserVo::getAge).thenComparing((o1, o2) -> UserVo.tagsIdx(o1) - UserVo.tagsIdx(o2)))
                .sorted(Comparator.comparing(UserVo::getAge).thenComparing(UserVo::tagsIdx))
                .forEach(o -> log.info("{} - {}, {}, {}", o.getId(), o.getAge(), o.getTags(), o.getName()));
    }

    @Test
    public void testList2New() {
        List<UserVo> collect = userVoList.stream()
                .filter(o -> null != o.getId() && null != o.getName())
                .collect(Collectors.toList());

        List<UserVo> collect1 = collect.stream().filter(o -> 24 == o.getAge()).collect(Collectors.toList());
        List<UserVo> collect2 = collect.stream().filter(o -> 24 != o.getAge()).collect(Collectors.toList());

        List<UserVo> collect3 = new ArrayList<>();
        collect3.addAll(collect1);
        collect3.addAll(collect2);

        collect3.forEach(o -> log.info("{} - {}, {}, {}", o.getId(), o.getAge(), o.getTags(), o.getName()));
    }

    @Test
    public void testList2Tree() throws Exception {
        Map<Integer, UserVo> collectMap = userVoList.stream()
                .filter(o -> null != o.getId())
                .collect(Collectors.toMap(o -> o.getId(), o -> o, (o1, o2) -> o1));
        List<UserVo> collectList = userVoList.stream()
                .filter(o -> null != o.getId())
                .sorted(Comparator.comparing(UserVo::getLevel))
                .map(o -> {
                    if (null == o.getParentId()) {
                        o.setRemark("root --> " + o.getLevel());
                    } else {
                        o.setRemark(collectMap.get(o.getParentId()).getRemark() + " --> " + o.getLevel());
                    }
                    return o;
                }).collect(Collectors.toList());
        collectList.forEach(System.err::println);
    }
}
