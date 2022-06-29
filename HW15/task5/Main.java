package com.company;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{

    public static void main(String[] args)
    {
        // ClientName Address Balance და მათ შორის მძიმეები როგორც ყველა შემთხვევის მოცემულობის ლოგიკით მიწერია.
        String string = "ClientName: Mark Twain,  Address: London, Balance: 123.56 - ";
        String[] columns = string.split(",");
        // სახელი
        columns[0] = columns[0].trim();
        // 11 = 'ClientName:'.length();
        String name = columns[0].substring(11).trim();

        //მისამართი
        columns[1] = columns[1].trim();
        // 9 = 'Address'.length();
        String address = columns[1].substring(9).trim();

        columns[2] = columns[2].trim();
        String balance = columns[2].substring(8).trim();
        // თუ ტირეს გარეშე გვინდა
        balance = balance.replace("-"," ");
        balance = balance.replaceAll("\\s+","");

        System.out.println(name);
        System.out.println(address);
        System.out.println(balance);
    }
}
