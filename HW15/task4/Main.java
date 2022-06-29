package com.company;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{

    public static void main(String[] args)
    {
        String string = "   this & is,...1 a  1235 !? dog ??  ";
        string = string.trim();
        string = string.replaceAll("[^a-zA-z]"," ");
        String[] words = string.split("\\s+");
        for(String word: words)
        {
            System.out.println(word);
        }
    }
}
