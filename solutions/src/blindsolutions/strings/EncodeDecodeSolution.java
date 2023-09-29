package blindsolutions.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeDecodeSolution {

    public static void main(String[] args) {
        String[] strs = new String[]{"lint", "code", "love", "you"};
        EncodeDecodeSolution s1 = new EncodeDecodeSolution();
        String encode = s1.encode(Arrays.asList(strs));
        System.out.println(encode);
        List<String> decode = s1.decode(encode);
        System.out.println(Arrays.toString(decode.stream().toArray()));
    }

    public String encode(List<String> strs) {
        String encode = "";
        for (String st : strs) {
            encode += st.length() + "#" + st;
        }
        return encode;
    }

    public List<String> decode(String str) {
        List<String> list = new ArrayList<>();
        int start = 0;
        while (start < str.length()) {
            int right = start;
            while (str.charAt(right) != '#')
                right++;

            int len = Integer.parseInt(str.substring(start, right), 10);
            list.add(str.substring(right + 1, right + len + 1));
            start = right + len + 1;
        }
        return list;
    }
}
