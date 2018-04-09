package cn.lzh.zbzd.service;

import java.util.List;

import cn.lzh.zbzd.model.Question;

public interface QuestionBelongTagService {
    public int insertQuestionBelongTag(long questionId, long tagId);

    public List<Question> listQuestionByTagId(long tagId);
}
