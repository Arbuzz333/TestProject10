package com.av.viva.avtotest.scoring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public record ScoringRequest(
    @JsonProperty("req_id")
        String reqId,
    @JsonProperty("endpoint_url")
        String endpointUrl,
    @JsonProperty("sum")
        Double reqSum,
    @JsonProperty("period")
        String period,
    @JsonProperty("lastname")
        String lastname,
    @JsonProperty("firstname")
        String firstname,
    @JsonProperty("patronymic")
        String patronymic,
    @JsonProperty("birthday")
        String birthday,
    @JsonProperty("pass_serial")
        String passSerial,
    @JsonProperty("pass_number")
        String passNumber,
    @JsonProperty("pass_issuer")
        String passIssuer,
    @JsonProperty("pass_issuer_code")
        String passIssuerCode,
    @JsonProperty("pass_issue_date")
        String passIssueDate,
    @JsonProperty("birthplace")
        String birthplace,
    @JsonProperty("reg_address")
        RegAddress regAddress,
    @JsonProperty("fact_address")
        FactAddress factAddress,
    @JsonProperty("pass_old_serial")
        String passOldSerial,
    @JsonProperty("pass_old_number")
        String passOldNumber,
    @JsonProperty("gender")
        String gender,
    @JsonProperty("income_source")
        Integer incomeSource,
    @JsonProperty("organization_type")
        Integer organizationType,
    @JsonProperty("company_occupation")
        Integer companyOccupation,
    @JsonProperty("last_work_term")
        Integer lastWorkTerm,
    @JsonProperty("position_key")
        Integer positionKey,
    @JsonProperty("regular_profit")
        Double regularProfit,
    @JsonProperty("snils")
        String snils,
    @JsonProperty("inn")
        String inn,
    @JsonProperty("email")
        String email
){
}
