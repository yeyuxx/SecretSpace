package head.secretspace.utils;

import java.util.Random;

/**
 * Created by HEAD on 2017/5/31.
 */

public class GPW {
    /**
     * 随机生成密码
     * @param combination 密码得到组合
     * @param length 密码长度
     * @return
     */
    public static String generatePassword(String combination, int length){
        StringBuffer sb=new StringBuffer();
        Random random=new Random();
        for (int i=0;i<length;i++){
            sb.append(combination.charAt(random.nextInt(combination.length())));
        }
        return sb.toString();
    }


}
