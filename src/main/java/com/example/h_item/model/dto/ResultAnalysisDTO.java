package com.example.h_item.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResultAnalysisDTO {

    /**
     * 成果类型按类型分析
     */
    private List<ResultTypeAnalysis> resultTypeAnalyses;

    /**
     * 成果类型按季度分析
     */
    private List<ResultQuarterAnalysis> resultQuarterAnalyses;

    /**
     * 成果类型按学院分析
     */
    private List<ResultAcademyAnalysis> resultAcademyAnalyses;

    @Data
    public static class ResultTypeAnalysis{
        /**
         * 成果数量
         */
        private Integer cnt;
        /**
         * 成果类型
         */
        private String resultType;

        private String resultTypeName;
    }

    @Data
    public static class ResultQuarterAnalysis{
        /**
         * 成果数量
         */
        private Integer cnt;
        /**
         * 季度名称
         */
        private String quarter;
    }

    @Data
    public static class ResultAcademyAnalysis{
        /**
         * 学院id
         */
        private Long academyId;
        /**
         * 长度12的数组 每个月一个数字
         */
        private List<Integer> monthCntList;
    }
}
