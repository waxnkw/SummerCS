package com.example.summer.convertorUtilityTest;


import com.example.summer.SummerApplicationTests;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class DozerTest extends SummerApplicationTests {
    List<String> maplist = Arrays.asList("amap.xml");
    DozerBeanMapper mapper = new DozerBeanMapper(maplist);

    @Test
    public void test(){
         A aO = new A();
         aO.setA("123456");
         B b = mapper.map(aO, B.class);
         Assert.assertEquals("123456",b.getC().getC());
         A a = mapper.map(b, A.class);
         Assert.assertEquals("123456",a.getA());
    }
}
