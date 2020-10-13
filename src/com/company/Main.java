package com.company;

import com.company.cifaanalysis.CifaAnalysis;

public class Main {

    public static void main(String[] args) {
        //词法分析器
        CifaAnalysis cifaAnalysis = new CifaAnalysis();
        int analysis = cifaAnalysis.analysis(args[0]);
        if (analysis != 0) {
            System.exit(analysis);
        }
        //语法分析器
        //语意分析器
    }

}
