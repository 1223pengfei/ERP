package com.scd.erp.utils.common;

import com.scd.erp.utils.common.pinyinUtils.Type;
import lombok.SneakyThrows;

import java.util.Random;


public class itCodeUtils {
    private String itCode = "";
    private String itCodes = "";

    	public static void main(String[] args) {
		itCodeUtils i = new itCodeUtils();
		try {
			String code = i.SCDCode("张三八");
			System.out.println(code);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**
     * @see "兴科迪邮箱命名法"
     * @param str "姓名"
     * @return "邮箱前缀"
     *
     * */
    @SneakyThrows
    public String SCDCode(String str){
        if (null != str && !str.equals("")) {
            int len = str.length();
            switch (len){
                case 2:
                    itCode = pinyinUtils.toPinYin(str, "", Type.LOWERCASE);
                    break;
                case 3:
                    String[] split = str.split("");
                    String b = pinyinUtils.toPinYin(split[0], "", Type.LOWERCASE);
                    String s = pinyinUtils.toPinYin(split[1] + split[2], "", Type.LOWERCASE);
                    itCode = s+"."+b;
                    break;
                default:
                    break;
            }
        }
           return itCode;
    }

    //重名法
    public String EnglishCode(String str) throws Throwable {
        int len = str.length();
        if (str != null  & len > 1) {
            String[] s = str.split("");
            for (int i = 0; i < s.length; i++) {
                String pinYin = pinyinUtils.toPinYin(s[i], "", Type.LOWERCASE);
                if (len > 2 && i > 0) {
                    //String[] strs = itCode.replaceAll("[a-z]", " $0").split(" ");
                    pinYin = pinYin.substring(0, 1);
                }
                itCode += pinYin;
            }
        }

        return itCode;
    }

    /*加数字法*/
    public String RandomCode(String str) throws Throwable {

        itCodes = pinyinUtils.toPinYin(str, "", Type.FIRSTUPPER);//

        if (itCodes.length() > 8) {
            String[] strs = itCodes.replaceAll("[A-Z]", " $0").split(" ");
            itCode = strs[1];
            for (int i = 2; i < strs.length; i++) {
                itCode += strs[i].substring(0, 1);
            }
            int m = itCode.length();
            if (itCode.length() < 8) {
                for (int i = 0; i < 8 - m; i++) {
                    itCode += new Random().nextInt(10);
                    //System.out.println(itCode);
                }
            }
            return itCode;
        }

        if (itCodes.length() < 8) {
            itCode = itCodes;
            for (int i = 0; i < 8 - itCodes.length(); i++) {
                itCode += new Random().nextInt(10) + 1;
            }
            return itCode;
        }
        if (itCodes.length() == 8) {
            itCode = itCodes;
            return itCode;
        }
        return null;
    }
}
