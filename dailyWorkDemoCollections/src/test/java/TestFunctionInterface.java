import java.util.HashMap;
import java.util.Map;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-11-16 10:30
 **/

public class TestFunctionInterface {

    private final Map<Integer, TestFunction> strategyMap = new HashMap<>();
     interface TestFunction {
        String dealTest();
    }




    public static void main(String[] args) {
        TestFunctionInterface testFunctionInterface = new TestFunctionInterface();
        testFunctionInterface.strategyMap.put(1, ()-> "1");
        testFunctionInterface.strategyMap.put(2, ()-> "2");
        testFunctionInterface.strategyMap.put(3, ()-> "3");
        testFunctionInterface.strategyMap.put(4, ()-> "4");
        testFunctionInterface.strategyMap.put(4, new TestFunction() {
            @Override
            public String dealTest() {
                return "soga";
            }
        });

        TestFunction testFunction = testFunctionInterface.strategyMap.get(4);
        System.out.println(testFunction.dealTest());
    }
}

