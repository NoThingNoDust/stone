package com.company.cifaanalysis;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CifaAnalysis {

    Set<String> symbolSet;

    public CifaAnalysis() {
        this.symbolSet = new HashSet<>();
        symbolSet.add("\"");
        this.queue = new ArrayList<>();
    }


    ArrayList<Token> queue;

    public int analysis(String filePath) throws ParseException {

        //文件阅读器
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new ParseException("文件读取失败");
        }
        Lexer lexer = new Lexer(fileReader);
        do {
            String read = lexer.read();
            //词法分析器
            queue.addAll(this.lexicalAnalysis(read));
        } while (lexer.hasNext());
        return 1;
    }


    private List<Token> lexicalAnalysis(String line) {
        if (line == null) {
            return new ArrayList<>();
        }
        char[] chars = line.toCharArray();
        String cache = "";
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (symbolSet.contains(String.valueOf(aChar))) {

            }
        }

        return new ArrayList<>();
    }


}
