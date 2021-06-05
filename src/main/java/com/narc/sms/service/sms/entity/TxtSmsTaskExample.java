package com.narc.sms.service.sms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TxtSmsTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TxtSmsTaskExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("TASK_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("TASK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("TASK_ID =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("TASK_ID <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("TASK_ID >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_ID >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("TASK_ID <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("TASK_ID <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("TASK_ID like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("TASK_ID not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("TASK_ID in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("TASK_ID not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("TASK_ID between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("TASK_ID not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNull() {
            addCriterion("TEMPLATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("TEMPLATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(String value) {
            addCriterion("TEMPLATE_ID =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(String value) {
            addCriterion("TEMPLATE_ID <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(String value) {
            addCriterion("TEMPLATE_ID >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_ID >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(String value) {
            addCriterion("TEMPLATE_ID <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_ID <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLike(String value) {
            addCriterion("TEMPLATE_ID like", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotLike(String value) {
            addCriterion("TEMPLATE_ID not like", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<String> values) {
            addCriterion("TEMPLATE_ID in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<String> values) {
            addCriterion("TEMPLATE_ID not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(String value1, String value2) {
            addCriterion("TEMPLATE_ID between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(String value1, String value2) {
            addCriterion("TEMPLATE_ID not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateContentIsNull() {
            addCriterion("TEMPLATE_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andTemplateContentIsNotNull() {
            addCriterion("TEMPLATE_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateContentEqualTo(String value) {
            addCriterion("TEMPLATE_CONTENT =", value, "templateContent");
            return (Criteria) this;
        }

        public Criteria andTemplateContentNotEqualTo(String value) {
            addCriterion("TEMPLATE_CONTENT <>", value, "templateContent");
            return (Criteria) this;
        }

        public Criteria andTemplateContentGreaterThan(String value) {
            addCriterion("TEMPLATE_CONTENT >", value, "templateContent");
            return (Criteria) this;
        }

        public Criteria andTemplateContentGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_CONTENT >=", value, "templateContent");
            return (Criteria) this;
        }

        public Criteria andTemplateContentLessThan(String value) {
            addCriterion("TEMPLATE_CONTENT <", value, "templateContent");
            return (Criteria) this;
        }

        public Criteria andTemplateContentLessThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_CONTENT <=", value, "templateContent");
            return (Criteria) this;
        }

        public Criteria andTemplateContentLike(String value) {
            addCriterion("TEMPLATE_CONTENT like", value, "templateContent");
            return (Criteria) this;
        }

        public Criteria andTemplateContentNotLike(String value) {
            addCriterion("TEMPLATE_CONTENT not like", value, "templateContent");
            return (Criteria) this;
        }

        public Criteria andTemplateContentIn(List<String> values) {
            addCriterion("TEMPLATE_CONTENT in", values, "templateContent");
            return (Criteria) this;
        }

        public Criteria andTemplateContentNotIn(List<String> values) {
            addCriterion("TEMPLATE_CONTENT not in", values, "templateContent");
            return (Criteria) this;
        }

        public Criteria andTemplateContentBetween(String value1, String value2) {
            addCriterion("TEMPLATE_CONTENT between", value1, value2, "templateContent");
            return (Criteria) this;
        }

        public Criteria andTemplateContentNotBetween(String value1, String value2) {
            addCriterion("TEMPLATE_CONTENT not between", value1, value2, "templateContent");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetIsNull() {
            addCriterion("PHONE_NUMBER_SET is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetIsNotNull() {
            addCriterion("PHONE_NUMBER_SET is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetEqualTo(String value) {
            addCriterion("PHONE_NUMBER_SET =", value, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetNotEqualTo(String value) {
            addCriterion("PHONE_NUMBER_SET <>", value, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetGreaterThan(String value) {
            addCriterion("PHONE_NUMBER_SET >", value, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE_NUMBER_SET >=", value, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetLessThan(String value) {
            addCriterion("PHONE_NUMBER_SET <", value, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetLessThanOrEqualTo(String value) {
            addCriterion("PHONE_NUMBER_SET <=", value, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetLike(String value) {
            addCriterion("PHONE_NUMBER_SET like", value, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetNotLike(String value) {
            addCriterion("PHONE_NUMBER_SET not like", value, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetIn(List<String> values) {
            addCriterion("PHONE_NUMBER_SET in", values, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetNotIn(List<String> values) {
            addCriterion("PHONE_NUMBER_SET not in", values, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetBetween(String value1, String value2) {
            addCriterion("PHONE_NUMBER_SET between", value1, value2, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberSetNotBetween(String value1, String value2) {
            addCriterion("PHONE_NUMBER_SET not between", value1, value2, "phoneNumberSet");
            return (Criteria) this;
        }

        public Criteria andTemplateParamIsNull() {
            addCriterion("TEMPLATE_PARAM is null");
            return (Criteria) this;
        }

        public Criteria andTemplateParamIsNotNull() {
            addCriterion("TEMPLATE_PARAM is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateParamEqualTo(String value) {
            addCriterion("TEMPLATE_PARAM =", value, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTemplateParamNotEqualTo(String value) {
            addCriterion("TEMPLATE_PARAM <>", value, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTemplateParamGreaterThan(String value) {
            addCriterion("TEMPLATE_PARAM >", value, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTemplateParamGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_PARAM >=", value, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTemplateParamLessThan(String value) {
            addCriterion("TEMPLATE_PARAM <", value, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTemplateParamLessThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_PARAM <=", value, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTemplateParamLike(String value) {
            addCriterion("TEMPLATE_PARAM like", value, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTemplateParamNotLike(String value) {
            addCriterion("TEMPLATE_PARAM not like", value, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTemplateParamIn(List<String> values) {
            addCriterion("TEMPLATE_PARAM in", values, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTemplateParamNotIn(List<String> values) {
            addCriterion("TEMPLATE_PARAM not in", values, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTemplateParamBetween(String value1, String value2) {
            addCriterion("TEMPLATE_PARAM between", value1, value2, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTemplateParamNotBetween(String value1, String value2) {
            addCriterion("TEMPLATE_PARAM not between", value1, value2, "templateParam");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNull() {
            addCriterion("TASK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("TASK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(String value) {
            addCriterion("TASK_TYPE =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(String value) {
            addCriterion("TASK_TYPE <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(String value) {
            addCriterion("TASK_TYPE >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(String value) {
            addCriterion("TASK_TYPE <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLike(String value) {
            addCriterion("TASK_TYPE like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotLike(String value) {
            addCriterion("TASK_TYPE not like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<String> values) {
            addCriterion("TASK_TYPE in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<String> values) {
            addCriterion("TASK_TYPE not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(String value1, String value2) {
            addCriterion("TASK_TYPE between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(String value1, String value2) {
            addCriterion("TASK_TYPE not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("SEND_TIME =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("SEND_TIME <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("SEND_TIME >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SEND_TIME >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("SEND_TIME <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("SEND_TIME <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("SEND_TIME in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("SEND_TIME not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("SEND_TIME between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("SEND_TIME not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIsNull() {
            addCriterion("CRON_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIsNotNull() {
            addCriterion("CRON_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andCronExpressionEqualTo(String value) {
            addCriterion("CRON_EXPRESSION =", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotEqualTo(String value) {
            addCriterion("CRON_EXPRESSION <>", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionGreaterThan(String value) {
            addCriterion("CRON_EXPRESSION >", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("CRON_EXPRESSION >=", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLessThan(String value) {
            addCriterion("CRON_EXPRESSION <", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLessThanOrEqualTo(String value) {
            addCriterion("CRON_EXPRESSION <=", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLike(String value) {
            addCriterion("CRON_EXPRESSION like", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotLike(String value) {
            addCriterion("CRON_EXPRESSION not like", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIn(List<String> values) {
            addCriterion("CRON_EXPRESSION in", values, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotIn(List<String> values) {
            addCriterion("CRON_EXPRESSION not in", values, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionBetween(String value1, String value2) {
            addCriterion("CRON_EXPRESSION between", value1, value2, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotBetween(String value1, String value2) {
            addCriterion("CRON_EXPRESSION not between", value1, value2, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andExtDataAIsNull() {
            addCriterion("EXT_DATA_A is null");
            return (Criteria) this;
        }

        public Criteria andExtDataAIsNotNull() {
            addCriterion("EXT_DATA_A is not null");
            return (Criteria) this;
        }

        public Criteria andExtDataAEqualTo(String value) {
            addCriterion("EXT_DATA_A =", value, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataANotEqualTo(String value) {
            addCriterion("EXT_DATA_A <>", value, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataAGreaterThan(String value) {
            addCriterion("EXT_DATA_A >", value, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataAGreaterThanOrEqualTo(String value) {
            addCriterion("EXT_DATA_A >=", value, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataALessThan(String value) {
            addCriterion("EXT_DATA_A <", value, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataALessThanOrEqualTo(String value) {
            addCriterion("EXT_DATA_A <=", value, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataALike(String value) {
            addCriterion("EXT_DATA_A like", value, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataANotLike(String value) {
            addCriterion("EXT_DATA_A not like", value, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataAIn(List<String> values) {
            addCriterion("EXT_DATA_A in", values, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataANotIn(List<String> values) {
            addCriterion("EXT_DATA_A not in", values, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataABetween(String value1, String value2) {
            addCriterion("EXT_DATA_A between", value1, value2, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataANotBetween(String value1, String value2) {
            addCriterion("EXT_DATA_A not between", value1, value2, "extDataA");
            return (Criteria) this;
        }

        public Criteria andExtDataBIsNull() {
            addCriterion("EXT_DATA_B is null");
            return (Criteria) this;
        }

        public Criteria andExtDataBIsNotNull() {
            addCriterion("EXT_DATA_B is not null");
            return (Criteria) this;
        }

        public Criteria andExtDataBEqualTo(String value) {
            addCriterion("EXT_DATA_B =", value, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataBNotEqualTo(String value) {
            addCriterion("EXT_DATA_B <>", value, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataBGreaterThan(String value) {
            addCriterion("EXT_DATA_B >", value, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataBGreaterThanOrEqualTo(String value) {
            addCriterion("EXT_DATA_B >=", value, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataBLessThan(String value) {
            addCriterion("EXT_DATA_B <", value, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataBLessThanOrEqualTo(String value) {
            addCriterion("EXT_DATA_B <=", value, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataBLike(String value) {
            addCriterion("EXT_DATA_B like", value, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataBNotLike(String value) {
            addCriterion("EXT_DATA_B not like", value, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataBIn(List<String> values) {
            addCriterion("EXT_DATA_B in", values, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataBNotIn(List<String> values) {
            addCriterion("EXT_DATA_B not in", values, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataBBetween(String value1, String value2) {
            addCriterion("EXT_DATA_B between", value1, value2, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataBNotBetween(String value1, String value2) {
            addCriterion("EXT_DATA_B not between", value1, value2, "extDataB");
            return (Criteria) this;
        }

        public Criteria andExtDataCIsNull() {
            addCriterion("EXT_DATA_C is null");
            return (Criteria) this;
        }

        public Criteria andExtDataCIsNotNull() {
            addCriterion("EXT_DATA_C is not null");
            return (Criteria) this;
        }

        public Criteria andExtDataCEqualTo(String value) {
            addCriterion("EXT_DATA_C =", value, "extDataC");
            return (Criteria) this;
        }

        public Criteria andExtDataCNotEqualTo(String value) {
            addCriterion("EXT_DATA_C <>", value, "extDataC");
            return (Criteria) this;
        }

        public Criteria andExtDataCGreaterThan(String value) {
            addCriterion("EXT_DATA_C >", value, "extDataC");
            return (Criteria) this;
        }

        public Criteria andExtDataCGreaterThanOrEqualTo(String value) {
            addCriterion("EXT_DATA_C >=", value, "extDataC");
            return (Criteria) this;
        }

        public Criteria andExtDataCLessThan(String value) {
            addCriterion("EXT_DATA_C <", value, "extDataC");
            return (Criteria) this;
        }

        public Criteria andExtDataCLessThanOrEqualTo(String value) {
            addCriterion("EXT_DATA_C <=", value, "extDataC");
            return (Criteria) this;
        }

        public Criteria andExtDataCLike(String value) {
            addCriterion("EXT_DATA_C like", value, "extDataC");
            return (Criteria) this;
        }

        public Criteria andExtDataCNotLike(String value) {
            addCriterion("EXT_DATA_C not like", value, "extDataC");
            return (Criteria) this;
        }

        public Criteria andExtDataCIn(List<String> values) {
            addCriterion("EXT_DATA_C in", values, "extDataC");
            return (Criteria) this;
        }

        public Criteria andExtDataCNotIn(List<String> values) {
            addCriterion("EXT_DATA_C not in", values, "extDataC");
            return (Criteria) this;
        }

        public Criteria andExtDataCBetween(String value1, String value2) {
            addCriterion("EXT_DATA_C between", value1, value2, "extDataC");
            return (Criteria) this;
        }

        public Criteria andExtDataCNotBetween(String value1, String value2) {
            addCriterion("EXT_DATA_C not between", value1, value2, "extDataC");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeIsNull() {
            addCriterion("CREATED_DATETIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeIsNotNull() {
            addCriterion("CREATED_DATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeEqualTo(Date value) {
            addCriterion("CREATED_DATETIME =", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeNotEqualTo(Date value) {
            addCriterion("CREATED_DATETIME <>", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeGreaterThan(Date value) {
            addCriterion("CREATED_DATETIME >", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATED_DATETIME >=", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeLessThan(Date value) {
            addCriterion("CREATED_DATETIME <", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATED_DATETIME <=", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeIn(List<Date> values) {
            addCriterion("CREATED_DATETIME in", values, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeNotIn(List<Date> values) {
            addCriterion("CREATED_DATETIME not in", values, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeBetween(Date value1, Date value2) {
            addCriterion("CREATED_DATETIME between", value1, value2, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATED_DATETIME not between", value1, value2, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedIdIsNull() {
            addCriterion("CREATED_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIdIsNotNull() {
            addCriterion("CREATED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedIdEqualTo(String value) {
            addCriterion("CREATED_ID =", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdNotEqualTo(String value) {
            addCriterion("CREATED_ID <>", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdGreaterThan(String value) {
            addCriterion("CREATED_ID >", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdGreaterThanOrEqualTo(String value) {
            addCriterion("CREATED_ID >=", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdLessThan(String value) {
            addCriterion("CREATED_ID <", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdLessThanOrEqualTo(String value) {
            addCriterion("CREATED_ID <=", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdLike(String value) {
            addCriterion("CREATED_ID like", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdNotLike(String value) {
            addCriterion("CREATED_ID not like", value, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdIn(List<String> values) {
            addCriterion("CREATED_ID in", values, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdNotIn(List<String> values) {
            addCriterion("CREATED_ID not in", values, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdBetween(String value1, String value2) {
            addCriterion("CREATED_ID between", value1, value2, "createdId");
            return (Criteria) this;
        }

        public Criteria andCreatedIdNotBetween(String value1, String value2) {
            addCriterion("CREATED_ID not between", value1, value2, "createdId");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeIsNull() {
            addCriterion("MODIFIED_DATETIME is null");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeIsNotNull() {
            addCriterion("MODIFIED_DATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeEqualTo(Date value) {
            addCriterion("MODIFIED_DATETIME =", value, "modifiedDatetime");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeNotEqualTo(Date value) {
            addCriterion("MODIFIED_DATETIME <>", value, "modifiedDatetime");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeGreaterThan(Date value) {
            addCriterion("MODIFIED_DATETIME >", value, "modifiedDatetime");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("MODIFIED_DATETIME >=", value, "modifiedDatetime");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeLessThan(Date value) {
            addCriterion("MODIFIED_DATETIME <", value, "modifiedDatetime");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("MODIFIED_DATETIME <=", value, "modifiedDatetime");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeIn(List<Date> values) {
            addCriterion("MODIFIED_DATETIME in", values, "modifiedDatetime");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeNotIn(List<Date> values) {
            addCriterion("MODIFIED_DATETIME not in", values, "modifiedDatetime");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeBetween(Date value1, Date value2) {
            addCriterion("MODIFIED_DATETIME between", value1, value2, "modifiedDatetime");
            return (Criteria) this;
        }

        public Criteria andModifiedDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("MODIFIED_DATETIME not between", value1, value2, "modifiedDatetime");
            return (Criteria) this;
        }

        public Criteria andModifiedIdIsNull() {
            addCriterion("MODIFIED_ID is null");
            return (Criteria) this;
        }

        public Criteria andModifiedIdIsNotNull() {
            addCriterion("MODIFIED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedIdEqualTo(String value) {
            addCriterion("MODIFIED_ID =", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdNotEqualTo(String value) {
            addCriterion("MODIFIED_ID <>", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdGreaterThan(String value) {
            addCriterion("MODIFIED_ID >", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdGreaterThanOrEqualTo(String value) {
            addCriterion("MODIFIED_ID >=", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdLessThan(String value) {
            addCriterion("MODIFIED_ID <", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdLessThanOrEqualTo(String value) {
            addCriterion("MODIFIED_ID <=", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdLike(String value) {
            addCriterion("MODIFIED_ID like", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdNotLike(String value) {
            addCriterion("MODIFIED_ID not like", value, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdIn(List<String> values) {
            addCriterion("MODIFIED_ID in", values, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdNotIn(List<String> values) {
            addCriterion("MODIFIED_ID not in", values, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdBetween(String value1, String value2) {
            addCriterion("MODIFIED_ID between", value1, value2, "modifiedId");
            return (Criteria) this;
        }

        public Criteria andModifiedIdNotBetween(String value1, String value2) {
            addCriterion("MODIFIED_ID not between", value1, value2, "modifiedId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}