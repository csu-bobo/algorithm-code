import java.util.HashMap;
import java.util.Map;

/*
 * 13. 罗马数字转整数
 * https://leetcode.cn/problems/roman-to-integer/
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(romanToInt2("III")); //3
        System.out.println(romanToInt2("IV")); //4
        System.out.println(romanToInt2("IX")); //9
        System.out.println(romanToInt2("LVIII")); //58
        System.out.println(romanToInt2("MCMXCIV")); //1994
    }

    private static Map<String, Integer> oneCharMap = new HashMap<>(){{
        //单字母
        put("I", 1);
        put("V", 5);
        put("X", 10);
        put("L", 50);
        put("C", 100);
        put("D", 500);
        put("M", 1000); 
    }};

    private static  Map<String, Integer> twoCharMap = new HashMap<>(){{
        //双字母
        put("IV", 4);
        put("IX", 9);
        put("XL", 40);
        put("XC", 90);
        put("CD", 400);
        put("CM", 900);
    }};
    
    /*
     * 解法1： 先判断2字母是合法数字的情况，再判断1字母.
     */
    public static int romanToInt(String s) {
        int ans = 0;
        int len = s.length();
        int remainLen = len;
        for(int i=0; i<len;) {
            String sub = "";
            remainLen = len - i;
            if (remainLen >= 2) {
                sub = s.substring(i, i+2);
                if (twoCharMap.containsKey(sub)) {
                    ans += twoCharMap.get(sub);
                    i += 2;
                    continue;
                }
            } 
            sub = s.substring(i, i+1);
            ans += oneCharMap.get(sub);
            i++; 
        }

        return ans;
    }

    /*
     * 解法2：  数字 < 右边，则是做减法
     *         数字 >= 右边，则是做加法
     */
    public static int romanToInt2(String s) {
        int ans = 0;
        for (int i=0; i<s.length(); i++) {
            int n = oneCharMap.get(s.substring(i, i+1));
            if (i < s.length() - 1 && n < oneCharMap.get(s.substring(i+1, i+2))) {
                ans -= n;
            } else {
                ans += n;
            }
        }
        return ans;
    }
}