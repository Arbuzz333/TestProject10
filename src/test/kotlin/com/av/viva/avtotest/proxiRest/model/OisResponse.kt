package com.av.viva.avtotest.proxiRest.model

import com.fasterxml.jackson.annotation.JsonProperty

data class OisResponse(

	@field:JsonProperty("Response")
	val response: List<ResponseItem?>? = null
)

data class ResponseItem(

	@field:JsonProperty("region_key")
	val regionKey: String? = null,

	@field:JsonProperty("region_socr")
	val regionSocr: String? = null,

	@field:JsonProperty("region_name")
	val regionName: String? = null
)
