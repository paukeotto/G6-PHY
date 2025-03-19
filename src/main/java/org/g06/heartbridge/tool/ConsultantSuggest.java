package org.g06.heartbridge.tool;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.*;


/**
 * @author 璃樘鼎臻
 * @since 2025/3/19 上午9:07
 * 标签-评分->推荐参数计算器
 * initSuggest是必须的，然后才能调用getSuggestParam
 **/
@Component
@Scope("prototype")
public class ConsultantSuggest {
    /**
     * 咨询者偏好标签组
     */
    private HashSet<String> tags;
    /**
     * 咨询者历史记录<标签，访问次数>
     */
    private Map<String,Integer> histories;
    /**
     * 评分对总分的影响因子
     */
    private Double evaluateImpParam=1.0;
    /**
     * 标签对总分的影响因子
     */
    private Double tagImpParam=1.0;
    /**
     * 用户选择的偏好对标签分的影响因子
     */
    private Double userChoose=1.0;
    private Double userHistory=1.0;
    public ConsultantSuggest() {}

    /**
     *
     * @param tags
     * @param histories
     */
    public void initSuggest(HashSet<String> tags, Map<String,Integer> histories) {
        this.tags = tags;
        this.histories = histories;
    }
    public void initParam(Double evaluateImpParam, Double tagImpParam) {
        this.evaluateImpParam = evaluateImpParam;
        this.tagImpParam = tagImpParam;
    }
    public void initTagPrefer(Double userChoose, Double userHistory) {
        this.userChoose = userChoose;
        this.userHistory = userHistory;
    }
    public Double getSuggestParam(List<String>consultantTags,Double consultantAvrEvaluate){
        if(tags==null||histories==null){
            System.out.println("必要参数:咨询者偏好tag或历史记录未提供");
            return 0.0;
        }
        int choose =0;
        int history=0;
        for(String tag:consultantTags){
            if(tags.contains(tag)){
                choose++;
            }
            if(histories.containsKey(tag)){
                history+=histories.get(tag);
            }
        }
        return (choose*userChoose+history*userHistory)*tagImpParam+consultantAvrEvaluate*evaluateImpParam;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }

    public Map<String, Integer> getHistories() {
        return histories;
    }

    public void setHistories(TreeMap<String, Integer> histories) {
        this.histories = histories;
    }
}
