package com.scd.erp.utils.extraUtil;

public class ConsoleMsg{

    public static void err(Object msg){
        System.out.println("\033[1;91m"+msg.toString()+ "\033[0m");
    }

    public static void log(Object msg){
        System.out.println("\033[1;94m"+msg.toString()+ "\033[0m");
    }

    public static void debug(Object msg){
        System.out.println( "\033[1;90m"+msg.toString()+ "\033[0m");
    }

}
