package utils;

import Easis.Common.StringUtil;

public class KMP {
    public int[] getLocations(String originStr, String strT) {
        if (StringUtil.isNullOrEmpty(strT) || StringUtil.isNullOrEmpty(originStr)) {
            return null;
        }
        return null;
    }


    private int[] getNextArray(String strT) {
        if (StringUtil.isNullOrEmpty(strT)) {
            return null;
        }
        int[] _arr_next = new int[strT.length()];
        return null;
    }
}