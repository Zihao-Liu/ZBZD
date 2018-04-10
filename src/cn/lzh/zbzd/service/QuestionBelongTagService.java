package cn.lzh.zbzd.service;

import java.util.List;

import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.Tag;

public interface QuestionBelongTagService {
    public int insertQuestionBelongTag(long questionId, long tagId);

    public List<Question> listQuestionByTagId(long tagId);

    public List<Question> listQuestionByTagIdOrderByModifiedTime(long tagId);

    public List<Question> listQuestionByTagIdOrderByAnswerCount(long tagId);
    
    public Tag getTagIdByQuestionId(long questionId);
}
