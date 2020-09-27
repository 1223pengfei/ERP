package com.scd.erp.Vo.Alarm;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_trace_alarm_question")
public class Question  implements Serializable {
    @Id
    @Column(name = "questionID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionid;

    @Column(name = "queType")
    private Integer quetype;

    private String question;

    private String cause;

    private String solution;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Question{");
        sb.append("questionid=").append(questionid);
        sb.append(", quetype=").append(quetype);
        sb.append(", question='").append(question).append('\'');
        sb.append(", cause='").append(cause).append('\'');
        sb.append(", solution='").append(solution).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return questionID
     */
    public Integer getQuestionid() {
        return questionid;
    }

    /**
     * @param questionid
     */
    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    /**
     * @return queType
     */
    public Integer getQuetype() {
        return quetype;
    }

    /**
     * @param quetype
     */
    public void setQuetype(Integer quetype) {
        this.quetype = quetype;
    }

    /**
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    /**
     * @return cause
     */
    public String getCause() {
        return cause;
    }

    /**
     * @param cause
     */
    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    /**
     * @return solution
     */
    public String getSolution() {
        return solution;
    }

    /**
     * @param solution
     */
    public void setSolution(String solution) {
        this.solution = solution == null ? null : solution.trim();
    }
}