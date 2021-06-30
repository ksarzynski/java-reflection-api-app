package app.classloading;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {

    public Class loadClassData(String className, String dir) throws IOException {

        String classNamePath;
        StringBuilder sb = new StringBuilder(className);

        char actual;

        boolean stop = false;

        int i = 0;

        while(!stop) {
            actual = sb.charAt(i);
            i++;
            if(actual == '.'){
                stop=true;
            }}

        StringBuffer text = new StringBuffer(className);
        text.replace( 0 ,i ,"");
        classNamePath = String.valueOf(text);
        classNamePath = classNamePath + ".class";
        String path = dir + classNamePath;

        Path pathToClass = Paths.get(path);
        byte[] allBytes = Files.readAllBytes(pathToClass);
        System.out.println(className);
        return defineClass(className,allBytes,0, allBytes.length);
    }
}