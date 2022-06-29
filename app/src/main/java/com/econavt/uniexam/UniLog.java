package com.econavt.uniexam;

class UniLog {

    private static String PREFIX = "UniLog: ";

    public static void print(Object message){
        System.out.println(PREFIX + message);
    }


    public static void error(Object message){
        print(" X " + message);
    }

    public static void onReceived(Object message){
        print(" <- " + message);
    }

    public static void onSend(Object message){
        print(" -> " + message);
    }




    public static void Method(){

        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

        //System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
        // return ste[ste.length - depth].getMethodName();  //Wrong, fails for depth = 0
        String name =  ste[3].getMethodName(); //Thank you Tom Tresansky

        print(name+"()");
    }

    public static void Method(String arg){

        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

        //System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
        // return ste[ste.length - depth].getMethodName();  //Wrong, fails for depth = 0
        String name =  ste[3].getMethodName(); //Thank you Tom Tresansky

        print(name+"() " + arg);
    }



}
