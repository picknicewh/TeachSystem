package com.mayi.yun.teachsystem.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/4/23
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DataUtil {

    public static List<String> getCauseList(){
        List<String> causeList = new ArrayList<>();
        causeList.add("生病了");
        causeList.add("有事外出");
        causeList.add("其他");
        return  causeList;
    }
    public static List<String> getPositionList(){
        List<String> positionList= new ArrayList<>();
        positionList.add("班长");
        positionList.add("学习委员");
        positionList.add("体育委员");
        positionList.add("音乐委员");
        positionList.add("纪律委员");
        return positionList;
    }
}
