package com.jvm.demo.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class toFileUtil {
    public static void StoreInFile(String content, String address) throws IOException {
        File file = new File(address);
        if (!file.exists()){
            file.createNewFile();
        }

        FileWriter writer;
        try {
            writer = new FileWriter(address);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Map<Object, Object> getHashMapFromTextFile(){

        Map<Object, Object> mapFileContents = new HashMap<>();
        BufferedReader br = null;

        try{

            //create file object
            File file = new File("/Users/zhangqizhou/Desktop/testResult");

            //create BufferedReader object from the File
            br = new BufferedReader( new FileReader(file) );

            String line = null;

            //read file line by line
            while ( (line = br.readLine()) != null ){

                //split the line by :
                Object[] parts = line.split(":");

                //first part is name, second is age
                Object LineNumber = parts[0];
//                Integer age = Integer.parseInt( parts[1].trim() );
                Object chain= parts[1];

                //put name, age in HashMap if they are not empty
//                if( !LineNumber.equals("") && !age.equals("") )
                    mapFileContents.put(LineNumber, chain);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{

            //Always close the BufferedReader
            if(br != null){
                try {
                    br.close();
                }catch(Exception e){};
            }
        }

        return mapFileContents;

    }

//    public static void main(String[] args) {
//        Map<Object, Object> map = new HashMap<>();
//        map = getHashMapFromTextFile();
//        for (Map.Entry<Object,Object> m:
//             map.entrySet()) {
//            System.out.println(m.getKey() + ":" + m.getValue());
//        }
//    }
}
