package org.resource;

import org.example.MyMath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MyMathTest {
    private MyMath math = new MyMath();

    // @Test
//    void test(){
//     int[] num = {1,2,3};
//    MyMath math = new MyMath();
//   int result =   math.calculateSum(num);
//   int expected = 7;
//    assertEquals(expected, result);
// }
//
//}
    @Test
    void calculateSum_ThreeMemberArray() {
        assertEquals(6, math.calculateSum(new int[]{1, 2, 3}));
    }

    @Test
    void calculateSum_ZeroLengthArray() {
        assertEquals(0, math.calculateSum(new int[]{}));
    }


//@Test
//void test1(){
//    int[] numbers = {};
//    MyMath math = null;
//    int ans = math.calculateSum(numbers);
//    int expected = 0;
//    assertEquals(expected, ans);
//}
}