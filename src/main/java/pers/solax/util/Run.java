package pers.solax.util;

import pers.solax.Base.Benchmark;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.logging.StreamHandler;

/**
 * Created by solax on 2017-4-7.
 */
public class Run {
    public  static Process exec (String command) {
        return exec(command);
    }

    public static Process exec (String command, boolean quiet) {
        Process proc = null;
        try {
            Runtime rt = Runtime.getRuntime();
            proc = rt.exec(command);
            InputStream fis=proc.getInputStream();
            //用一个读输出流类去读
            InputStreamReader isr=new InputStreamReader(fis);
            //用缓冲器读行
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            Date startDate = new Date();
            if (quiet) {
                int index = 0;
                while((line=br.readLine())!=null)
                {
                    index ++;
                    if (index % 100000 == 0) {
                        System.out.print("percent : " + index + ", ");
                    }
                }
            } else {
                //直到读完为止
                while((line=br.readLine())!=null)
                {
                    System.out.println(line);
/*                    if (line.indexOf("WARN") > 0 || line.indexOf("ERROR") > 0 ||  line.indexOf("FATAL") > 0) {
                        System.out.println(line);
                    }*/
                }
            }
            Date endDate = new Date();
            System.out.println("--");
            System.out.println("commond : " + command + " finished");
            System.out.println("finish after : " + (endDate.getTime() - startDate.getTime()) / 1000 + "s");
           // int exitVal = proc.exitValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  proc;
    }
}
