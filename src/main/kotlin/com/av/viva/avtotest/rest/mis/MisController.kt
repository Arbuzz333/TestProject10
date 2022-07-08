package com.av.viva.avtotest.rest.mis

import com.av.viva.avtotest.config.AppTestProperties
import com.av.viva.avtotest.rest.dto.MisStartRq
import com.av.viva.avtotest.rest.dto.RequestData
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/mis-api/v2",
    consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE])
class MisController(
    val properties: AppTestProperties
) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(MisController::class.java)
        const val successResponse = "{\"hmac\":\"6a3843bc2e671549a4febf70c75d0fde\",\"response\":{\"status\":\"SUCCESS\",\"message\":\"\"}}"
    }

    @PostMapping("/get_process_key")
    fun getMonetaCardData(
        rq: MisStartRq?
    ): String {
        logger.info("get_process_key $rq")
        val om = ObjectMapper()
        val requestData = om.readValue(rq?.request?.process, RequestData::class.java)

        val processKey = when (requestData.process) {
            "registration" -> properties.misProcessKey
            "loan-application" -> "Process_LoanApp_v1.3"
            "loan-transaction" -> "Process_LoanTx_v1.6"
            else -> "Bad request"
        }

        return "{\"hmac\":\"4140d51dc3f97327e93e3024a113563f\"," +
                "\"response\":{\"status\":\"SUCCESS\",\"message\":\"\",\"process-key\":\"$processKey\"}}"
    }

    @PostMapping("/activate_demo_account")
    fun activateDemoAccount(
        rq: MisStartRq?
    ): String {
        logger.info("activate_demo_account $rq")

        return successResponse
    }

    @PostMapping("/get_registration_type")
    fun getRegistrationType(
        rq: MisStartRq?
    ): String {
        logger.info("activate_demo_account $rq")

        return "{\"hmac\":\"4e95fec62fdd128e4b12bb61f8f25231\"," +
                "\"response\":{\"status\":\"SUCCESS\",\"message\":\"\",\"result\":\"FULL\"}}"
    }

    @PostMapping("/send_sms")
    fun sendSms(
        rq: MisStartRq?
    ): String {
        logger.info("send_sms $rq")

        return "{\"hmac\":\"2ddd00b5a8ef9da93243b1a7ed35c1cf\"," +
                "\"response\":{\"message\":\"\",\"status\":\"SUCCESS\",\"0\":\"10387\"}}"
    }

    @PostMapping("/register_lead")
    fun registerLead(
        rq: MisStartRq?
    ): String {
        logger.info("register_lead $rq")

        return successResponse
    }

    @PostMapping("/input_passport_main")
    fun inputPassportMain(
        rq: MisStartRq?
    ): String {
        logger.info("input_passport_main $rq")

        return successResponse
    }

    @PostMapping("/input_passport_old")
    fun inputPassportOld(
        rq: MisStartRq?
    ): String {
        logger.info("input_passport_old $rq")

        return successResponse
    }

    @PostMapping("/input_phone")
    fun inputPhone(
        rq: MisStartRq?
    ): String {
        logger.info("input_phone $rq")

        return successResponse
    }

    @PostMapping("/input_passport_address")
    fun inputPassportAddress(
        rq: MisStartRq?
    ): String {
        logger.info("input_passport_address $rq")

        return successResponse
    }

    @PostMapping("/input_video_selfie")
    fun inputVideoSelfie(
        rq: MisStartRq?
    ): String {
        logger.info("input_video_selfie $rq")

        return successResponse
    }

    @PostMapping("/activate_account")
    fun activateAccount(
        rq: MisStartRq?
    ): String {
        logger.info("activate_account $rq")

        return successResponse
    }

    @PostMapping("/profile")
    fun profile(
        rq: MisStartRq?
    ): String {
        logger.info("profile $rq")

        return "{\"hmac\":\"62a8bffc2c00a1177ba9f5301fa7e245\",\"response\":{\"status\":\"SUCCESS\",\"message\":\"\"," +
                "\"client\":{\"person\":{\"lastname\":\"КУДРЯВЦЕВ\",\"name\":\"ЛЕОНИД\",\"surname\":\"ВАЛЕРЬЯНОВИЧ\"," +
                "\"birthdate\":\"1991-01-01\",\"passport\":\"4710 352764\",\"address\":\"г Москва, г Троицк, ул.Северная, д.123 кв.\"," +
                "\"phone\":\"+79782482088\",\"email\":\"\"}}}}"
    }

    @PostMapping("/reg_fact_sms_bki")
    fun regFactSmsBki(
        rq: MisStartRq?
    ): String {
        logger.info("reg_fact_sms_bki $rq")

        return successResponse
    }

    @PostMapping("/loanapp_review")
    fun loanappReview(
        rq: MisStartRq?
    ): String {
        logger.info("loanapp_review $rq")

        return successResponse
    }

    @PostMapping("/check_offer")
    fun checkOffer(
        rq: MisStartRq?
    ): String {
        logger.info("check_offer $rq")

        return "{\"hmac\":\"c4e08b6ad176d98d9d793a96c2815ab8\",\"response\":{\"status\":\"SUCCESS\",\"message\":\"\"," +
                "\"offer-status\":\"ACTUAL\",\"offer-amount\":\"10000\",\"offer-period\":\"6M\",\"offer-rate\":\"0.71\"," +
                "\"offer-date\":\"2022-07-05\"}}"
    }

    @PostMapping("/loan_docs")
    fun loanDocs(
        rq: MisStartRq?
    ): String {
        logger.info("loan_docs $rq")

        return "{\"hmac\":\"3f65a62bba1e8ec8adbbe6a4d999a688\",\"response\":{\"status\":\"SUCCESS\",\"message\":\"\"," +
                "\"individual-conditions\":\"/asuz_documents/m_app/2022/7/5/31993_194_1.pdf\",\"payment-schedule\":" +
                "\"/asuz_documents/m_app/2022/7/5/31993_195_1.pdf\",\"insurance-terms\":\"/asuz_documents/m_app/2022/7/5/31993_202_1.pdf\"}}"
    }

}