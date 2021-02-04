abstract public class ConsoleLog {
    public static String error(String message){
        return "\n{ERROR} => "+message;
    }

    public static String error(String message, String errorMethod){
        return "\n{ERROR} => "+message+" =>"+errorMethod;
    }

    public static String error(String message, String errorMethod, String error){
        return "\n{ERROR} => "+message +" => "+errorMethod+" => "+"\n       "+error;
    }

    public static String warn(String message){
        return "\n{WARNING} => "+message;
    }

    public static String warn(String message, String warnMethod){
        return "\n{WARNING} => "+message+" => "+warnMethod;
    }


}
