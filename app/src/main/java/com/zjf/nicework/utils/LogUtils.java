
package com.zjf.nicework.utils;

/**
 * ClassName:LogUtils <br/>
 * Function: Log工具类. <br/>
 * Date: 2016年6月7日 下午9:13:31 <br/>
 * 
 * @author Alpha
 * @version
 */
public final class LogUtils {
    /**
     * Log  tag.
     */
    private static String sTagDefault = "zjf";

    /**
     * 程序是否正式上线,info warn error 级别日志为true时,仍可打印
     */
    private static boolean sToggleRelease = false;

    /**
     * 是否打印Throwable 日志
     */
    private static boolean sToggleThrowable = true;

    /**
     * 是否打印线程名称
     */
    private static boolean sToggleThread = false;

    /**
     * 是否打印类名和方法名
     */
    private static boolean sToggleClassMethod = true;

    /**
     * 是否打印行数
     */
    private static boolean sToggleFileLineNumber = true;

    public static void e(String tag, String msg, Throwable e) {
        printLog(android.util.Log.ERROR, tag, msg, e);
    }

    public static void e(String msg, Throwable e) {
        printLog(android.util.Log.ERROR, null, msg, e);
    }

    public static void e(String msg) {
        printLog(android.util.Log.ERROR, null, msg, null);
    }

    public static void w(String tag, String msg, Throwable e) {
        printLog(android.util.Log.WARN, tag, msg, e);
    }

    public static void w(String msg, Throwable e) {
        printLog(android.util.Log.WARN, null, msg, e);
    }

    public static void w(String msg) {
        printLog(android.util.Log.WARN, null, msg, null);
    }

    public static void i(String tag, String msg, Throwable e) {
        printLog(android.util.Log.INFO, tag, msg, e);
    }

    public static void i(String msg) {
        printLog(android.util.Log.INFO, null, msg, null);
    }

    public static void d(String tag, String msg, Throwable e) {
        printLog(android.util.Log.DEBUG, tag, msg, e);
    }

    public static void d(String msg, Throwable e) {
        printLog(android.util.Log.DEBUG, null, msg, e);
    }

    public static void d(String tag, String msg) {
        printLog(android.util.Log.DEBUG, tag, msg, null);
    }

    public static void d(String msg) {
        printLog(android.util.Log.DEBUG, null, msg, null);
    }

    public static void v(String tag, String msg, Throwable e) {
        printLog(android.util.Log.VERBOSE, tag, msg, e);
    }

    public static void v(String tag, String msg) {
        printLog(android.util.Log.VERBOSE, tag, msg, null);
    }

    public static void v(String msg) {
        printLog(android.util.Log.VERBOSE, null, msg, null);
    }

    private static void printLog(int logType, String tag, String msg,
            Throwable e) {
        String tagStr = (tag == null) ? sTagDefault : tag;
        if (sToggleRelease) {
            if (logType < android.util.Log.INFO) {
                return;
            }
            String msgStr = (e == null) ? msg
                    : (msg + "\n" + android.util.Log.getStackTraceString(e));

            switch (logType) {
                case android.util.Log.ERROR:
                    android.util.Log.e(tagStr, msgStr);

                    break;
                case android.util.Log.WARN:
                    android.util.Log.w(tagStr, msgStr);

                    break;
                case android.util.Log.INFO:
                    android.util.Log.i(tagStr, msgStr);

                    break;
                default:
                    break;
            }

        } else {
            StringBuilder msgStr = new StringBuilder();

            if (sToggleThread || sToggleClassMethod || sToggleFileLineNumber) {
                Thread currentThread = Thread.currentThread();

                if (sToggleThread) {
                    msgStr.append("<");
                    msgStr.append(currentThread.getName());
                    msgStr.append("> ");
                }

                if (sToggleClassMethod) {
                    StackTraceElement ste = currentThread.getStackTrace()[4];
                    String className = ste.getClassName();
                    msgStr.append("[");
                    msgStr.append(className == null ? null
                            : className
                                    .substring(className.lastIndexOf('.') + 1));
                    msgStr.append("--");
                    msgStr.append(ste.getMethodName());
                    msgStr.append("] ");
                }

                if (sToggleFileLineNumber) {
                    StackTraceElement ste = currentThread.getStackTrace()[4];
                    msgStr.append("[");
                    msgStr.append(ste.getFileName());
                    msgStr.append("--");
                    msgStr.append(ste.getLineNumber());
                    msgStr.append("] ");
                }
            }

            msgStr.append(msg);
            if (e != null && sToggleThrowable) {
                msgStr.append('\n');
                msgStr.append(android.util.Log.getStackTraceString(e));
            }

            switch (logType) {
                case android.util.Log.ERROR:
                    android.util.Log.e(tagStr, msgStr.toString());

                    break;
                case android.util.Log.WARN:
                    android.util.Log.w(tagStr, msgStr.toString());

                    break;
                case android.util.Log.INFO:
                    android.util.Log.i(tagStr, msgStr.toString());

                    break;
                case android.util.Log.DEBUG:
                    android.util.Log.d(tagStr, msgStr.toString());

                    break;
                case android.util.Log.VERBOSE:
                    android.util.Log.v(tagStr, msgStr.toString());

                    break;
                default:
                    break;
            }
        }
    }

}