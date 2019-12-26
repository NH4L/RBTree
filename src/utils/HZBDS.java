package utils;

import Easis.Common.StringUtil;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 后缀表达式类
 * @author lcy
 * @date 2019-12-26
 */
public class HZBDS {
    public static void main(String[] args) {
        HZBDS hz = new HZBDS();
        String myOpStr1 = "9+12/2+3-4*7";
//        myOpStr1 = "4*7+5-10*1.2+3.6+1.4-1.2*1.2+1*5";
//        myOpStr1 = "3*3*4/2+4/2/1*3-1*2*3+7-2+3";

        //求中缀表达式对应的后缀表达式
        System.out.println("该中缀表达式对应的后缀表达式为：");
        Stack<CaculateUnit> myStack = hz.simple_getHZBDS(myOpStr1);
        hz.debug(myStack);

        //计算结果
        float res = hz.caculateHZS(myStack);
        System.out.println("计算结果为：" + res);
    }

    /**
     * 将中缀表达式字符串转化为后缀字符串
     * @param originStr 原始中缀表达式字符串
     * @return  后缀最表达式 栈
     */
    public Stack<CaculateUnit> simple_getHZBDS(String originStr) {
        if (originStr == null || originStr.trim().length() <= 0) {
            return null;
        }
        ArrayList<CaculateUnit> _arr = new ArrayList<>();
        StringBuilder sb_res = new StringBuilder();
        int preLoc = -1;
        int currentLoc = 0;
        String res = originStr.trim();
        Stack<CaculateUnit> _opStack = new Stack<>();
        Stack<CaculateUnit> _ResultStack = new Stack<>();
        for (int i = 0; i < res.length(); i++) {
            char c1 = res.charAt(i);
            if (c1 == '+' || c1 == '-' || c1 == '*' || c1 == '/') {
                if (preLoc != -1) {
                    String str_op = res.substring(preLoc, i);
                    CaculateUnit cOP = new CaculateUnit();
                    cOP.operationNumber = StringUtil.toFloat(str_op);
                    cOP.type = CaculateType.Number;
                    _ResultStack.push(cOP);
                } else if (preLoc == -1 && i > 0) {
                    preLoc = 0;
                    String str_op = res.substring(preLoc, i);
                    CaculateUnit cOP = new CaculateUnit();
                    cOP.operationNumber = StringUtil.toFloat(str_op);
                    cOP.type = CaculateType.Number;
                    _ResultStack.push(cOP);
                }

                preLoc = i + 1;
                CaculateUnit cUnit = new CaculateUnit();
                cUnit.operationNumber = 0.0F;
                if (c1 == '+') {
                    cUnit.type = CaculateType.PLUS;
                } else if (c1 == '-') {
                    cUnit.type = CaculateType.SUB;
                } else if (c1 == '*') {
                    cUnit.type = CaculateType.MUL;
                } else if (c1 == '/') {
                    cUnit.type = CaculateType.DIV;
                }
                if (_opStack.size() <= 0) {

                    _opStack.push(cUnit);

                } else {
                    CaculateUnit _top1 = _opStack.pop();
                    if (isHighLevel(cUnit, _top1) == 1) {
                        _opStack.push(_top1);
                        _opStack.push(cUnit);
                    } else {

                        _ResultStack.push(_top1);
                        while (_opStack.size() > 0) {
                            CaculateUnit _topI = _opStack.pop();
                            if (isHighLevel(cUnit, _topI) >= 0) {
                                _ResultStack.push(_topI);
                                continue;
                            }
                            _opStack.push(_topI);
                            _opStack.push(cUnit);

                            break;
                        }
                        if (_opStack.size() <= 0) {
                            _opStack.push(cUnit);
                        }
                    }
                }
            }
        }
        if (preLoc <= res.length() - 1) {
            String str_op = res.substring(preLoc, res.length());
            CaculateUnit cOPUnit = new CaculateUnit();
            cOPUnit.operationNumber = StringUtil.toFloat(str_op);
            _ResultStack.push(cOPUnit);
        }


        int opTotal = _opStack.size();
        if (opTotal > 0) {
            for (int i = 1; i <= opTotal; i++) {
                CaculateUnit cTMP1 = _opStack.pop();
                _ResultStack.push(cTMP1);
            }
        }
        return _ResultStack;
    }

    /**
     * 判断是否为高位
     * @param c1 第一个计算单元
     * @param c2 第二个计算单元
     * @return 0，1，-1
     */
    public int isHighLevel(CaculateUnit c1, CaculateUnit c2) {
        if (c1.type == CaculateType.Number || c2.type == CaculateType.Number) {
            return 0;
        }
        if (c1.type == CaculateType.DIV || c1.type == CaculateType.MUL) {

            if (c2.type == CaculateType.DIV || c2.type == CaculateType.MUL) {
                return 0;
            }
            return 1;
        }
        if (c2.type == CaculateType.DIV || c2.type == CaculateType.MUL) {
            return -1;
        }
        return 0;
    }

    /**
     * 计算后缀表达式结果
     * @param stack 栈
     * @return 计算结果
     */
    public float caculateHZS(Stack<CaculateUnit> stack) {
        float res = 0.0F;
        ArrayList<CaculateUnit> _stackCopy = new ArrayList<>(stack);
        int opLoc = 0;
        while (_stackCopy.size() > 1) {
            int size = _stackCopy.size();
            for (int i = 0; i < size; i++) {
                CaculateUnit cUnit1 = _stackCopy.get(i);
                if (cUnit1.type == CaculateType.DIV || cUnit1.type == CaculateType.SUB || cUnit1.type == CaculateType.PLUS || cUnit1.type == CaculateType.MUL) {
                    CaculateUnit cUnit_res = new CaculateUnit();
                    if (cUnit1.type == CaculateType.SUB) {
                        ((CaculateUnit) _stackCopy.get(i - 2)).operationNumber -= ((CaculateUnit) _stackCopy.get(i - 1)).operationNumber;
                        cUnit_res = ((CaculateUnit) _stackCopy.get(i - 2));
                    } else if (cUnit1.type == CaculateType.PLUS) {
                        ((CaculateUnit) _stackCopy.get(i - 2)).operationNumber += ((CaculateUnit) _stackCopy.get(i - 1)).operationNumber;
                        cUnit_res = ((CaculateUnit) _stackCopy.get(i - 2));
                    } else if (cUnit1.type == CaculateType.MUL) {
                        ((CaculateUnit) _stackCopy.get(i - 2)).operationNumber *= ((CaculateUnit) _stackCopy.get(i - 1)).operationNumber;
                        cUnit_res = ((CaculateUnit) _stackCopy.get(i - 2));
                    } else if (cUnit1.type == CaculateType.DIV) {
                        ((CaculateUnit) _stackCopy.get(i - 2)).operationNumber /= ((CaculateUnit) _stackCopy.get(i - 1)).operationNumber;
                        cUnit_res = ((CaculateUnit) _stackCopy.get(i - 2));
                    }
                    _stackCopy.remove(i);
                    _stackCopy.remove(i - 1);
                    _stackCopy.remove(i - 2);
                    _stackCopy.add(i - 2, cUnit_res);
                    break;
                }
            }
        }
        res = ((CaculateUnit) _stackCopy.get(0)).operationNumber;
        return res;
    }

    /**
     * 输出后缀表达式
     * @param stack 栈
     */
    public void debug(Stack<CaculateUnit> stack) {
        for (int i = 0; i < stack.size(); i++) {
            CaculateUnit iUnit = stack.get(i);
            switch (iUnit.type) {
                case DIV:
                    System.out.print(" / ");
                    break;
                case MUL:
                    System.out.print(" * ");
                    break;
                case PLUS:
                    System.out.print(" + ");
                    break;
                case SUB:
                    System.out.print(" - ");
                    break;
                default:
                    System.out.print(" " + iUnit.operationNumber + " ");
                    break;
            }
        }
        System.out.println('\n');
    }
}
