import algorithm.AppAlgorithm;
import collections.AppCollections;
import concurrency.AppConcurrency;
import java8.AppJava8;
import java_core.AppJavaCore;
import tasks.AppTask;
import tasks.dynamic_task.AppDynamicTask;
import tasks.leet_code.AppTasksLeetCode;

import java.util.Collections;

public class InterviewJavaApp {

    public static void main(String[] args) {
//        Java Core
        AppJavaCore.mainJavaCore();

//        Collections
//        AppCollections.mainCollections();

//        Java Algorithm
//        AppAlgorithm.mainAlgorithms();

//        Java 8
//        AppJava8.mainJava8();
//         jdk();

//        Concurrency
//        AppConcurrency.mainConcurrency();

//        Tasks from Leet Code
//        AppTasksLeetCode.mainTasksLeetCode();

//        Dynamic Tasks
//        AppDynamicTask.mainDynamicTasks();

//        Tasks
//        AppTask.mainTasks();
    }

    private static void jdk() {
        ClassLoader main = InterviewJavaApp.class.getClassLoader();
        System.out.println(main); //AppClassLoader

        ClassLoader classLoader1 = ClassLoader.class.getClassLoader();
        System.out.println(classLoader1); //null (BootClassLoader)

        ClassLoader classLoader2 = AppJava8.class.getClassLoader();
        System.out.println(classLoader2); //AppClassLoader

        String classpath = System.getProperty("java.class.path");
        System.out.println("CLASSPATH: " + classpath);
    }
}