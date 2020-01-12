package com.example.summer.utilityTest.dateUtillityTest;

import com.example.summer.utility.dateUtility.DateUtility;
import org.junit.Assert;
import org.junit.Test;

public class DateUtilityTest {
    @Test
    public void testConvertIdToDate(){
        String id = "2018-04-28_19-13-39-959--0";
        String date = DateUtility.convertIdToDate(id);
        Assert.assertEquals("2018年04月28日 19:13", date);
    }
}
