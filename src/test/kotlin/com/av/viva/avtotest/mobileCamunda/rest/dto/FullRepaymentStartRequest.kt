package com.av.viva.avtotest.mobileCamunda.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class FullRepaymentStartRequest(
  val type: String,
  @JsonProperty(value = "business-key", access = JsonProperty.Access.READ_WRITE)
  val businessKey: String,
  val params: String,
  val processName: String = "full-repayment"
)