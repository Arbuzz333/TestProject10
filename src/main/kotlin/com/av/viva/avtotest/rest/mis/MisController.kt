package com.av.viva.avtotest.rest.mis

import com.av.viva.avtotest.config.AppTestProperties
import com.av.viva.avtotest.rest.dto.MisStartRq
import com.av.viva.avtotest.rest.dto.RequestData
import com.av.viva.avtotest.rest.mis.dto.BaseResponseDocs
import com.av.viva.avtotest.rest.mis.dto.BaseResponseRefin
import com.av.viva.avtotest.rest.mis.dto.BaseResponseRefinConfirm
import com.av.viva.avtotest.rest.mis.dto.GetRefinParamsResult
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate


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
            "loan-payment" -> "Process_LoanPayment_v1.3"
            "full-repayment" -> "Process_FullRepayment_v1.3"
            "upsale" -> "Process_Upsale_v1.4"
            "restructure" -> "Process_Restructure_v1.3"
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
        logger.info("get_registration_type $rq")

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
                "\"client\":{\"person\":{\"lastname\":\"??????????????????\",\"name\":\"????????????\",\"surname\":\"????????????????????????\"," +
                "\"birthdate\":\"1991-01-01\",\"passport\":\"4710 352764\",\"address\":\"?? ????????????, ?? ????????????, ????.????????????????, ??.123 ????.\"," +
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

    @PostMapping("/reg_fact_confirm_conditions")
    fun regFactConfirmConditions (
        rq: MisStartRq?
    ): String {
        logger.info("reg_fact_confirm_conditions $rq")

        return successResponse
    }

    @PostMapping("/pay_to_card")
    fun payToCard (
        rq: MisStartRq?
    ): String {
        logger.info("pay_to_card $rq")

        return "{\"hmac\":\"1f70b1792135aa892f89d47d31a66cd5\",\"response\":{\"status\":\"SUCCESS\",\"message\":\"\"," +
                "\"tx-id\":\"2845855\",\"tx-status\":\"SUCCEED\"}}"
    }

    @PostMapping("/check_assessment")
    fun checkAssessment (
        rq: MisStartRq?
    ): String {
        logger.info("check_assessment $rq")

        return "{\"hmac\":\"b17c97d2c9a6602b6ddde166ece117f2\",\"response\":{\"status\":\"SUCCESS\",\"message\":\"\",\"result\":\"assessment_required\"}}"
    }

    @PostMapping("/add_assessment")
    fun addAssessment (
        rq: MisStartRq?
    ): String {
        logger.info("add_assessment $rq")

        return successResponse
    }

    @PostMapping("/active_loans")
    fun activeLoans (
        rq: MisStartRq?
    ): String {
        logger.info("active_loans $rq")

        return "{\"response\":{\"status\":\"SUCCESS\",\"loans\":[{\"main\":{\"loan-key\":\"3346870\"}, \"mandatory-payment\":{\"sum\":\"1212.12\"}," +
                "\"recommend-payment\":{\"sum\":\"1200.00\"}," +
                "\"overdue\":{\"debt-overdue\":\"111.22\", \"accrued\":\"111.33\"}}]}}"
    }

    @PostMapping("/reg_fact_payment")
    fun regFactPayment (
        rq: MisStartRq?
    ): String {
        logger.info("reg_fact_payment $rq")

        return successResponse
    }

    @PostMapping("/get_full_repayment_status")
    fun getFullRepaymentStatus (
        rq: MisStartRq?
    ): String {
        logger.info("get_full_repayment_status $rq")

        return "{\"response\":{\"status\":\"SUCCESS\",\"result\":\"REQUEST_EXISTS\",\"sum\":\"555777\"," +
                "\"fullRepaymentSum\":\"6000.45\",\"date\":\"05.12.2025\"}}"
    }

    @PostMapping("/loanapp_review_upsale")
    fun loanAppReviewUpSale (
        rq: MisStartRq?
    ): String {
        logger.info("loanapp_review_upsale $rq")

        return successResponse
    }

    @PostMapping("/get_offer_upsale")
    fun getOfferUpsale (
        rq: MisStartRq?
    ): String {
        logger.info("get_offer_upsale $rq")
        val dateNow = LocalDate.now().toString()

        return "{\"response\":{\"status\":\"SUCCESS\",\"offer\":[{\"offer-id\":\"1110555\", \"date\":\"$dateNow\"," +
                "\"sum\":\"120077.00\"," +
                "\"period\":\"11M\",\"rate\":\"75\",\"text\":\"GET offer up sale\"}]}}"
    }

    @PostMapping("/upsale_docs")
    fun upSaleDocs (
        rq: MisStartRq?
    ): String {
        logger.info("upsale_docs $rq")

        return "{\"response\":{\"status\":\"SUCCESS\",\"individual-conditions\":\"individual conditions\"," +
                "\"payment-schedule\":\"week\",\"agreement-to-hold\":\"HOLD\",\"insurance-terms\":\"TERMS\"}}"
    }

    @PostMapping("/create_refin")
    fun createRefin (
        rq: MisStartRq?
    ): String {
        logger.info("create_refin $rq")
        val mapper = ObjectMapper()
        val loanRq = mapper.readValue(rq?.request?.process, MisStartRq.LoanRq::class.java)

        return "{\"response\":{\"status\":\"SUCCESS\",\"result\":\"CREATED\"," +
                "\"refin-id\":\"555333888\",\"business-key\":\"${loanRq.businessKey}\"}}"
    }

    @PostMapping("/get_refin_params")
    fun getRefinParams (
        rq: MisStartRq?
    ): BaseResponseRefin {
        logger.info("get_refin_params $rq")
        val result = GetRefinParamsResult(
            guaranteeSum = 957.08,
            validUntilDate = LocalDate.now(),
            loanAmount = 155.35,
            payment = 37.01,
            period = "1M",
            rate = 25.07,
            numberPayments = 12
        )
        val rs = BaseResponseRefin(response = result)

        return rs
    }

    @PostMapping("/refin_docs")
    fun refinDocs (
        rq: MisStartRq?
    ): BaseResponseDocs {
        logger.info("refin_docs $rq")
        val result = BaseResponseDocs.RefinDocsResult(
            individualConditions = "no longer",
            paymentSchedule = "1 M",
            statementNovation = "statement was closed"
        )
        val rs = BaseResponseDocs(response = result)

        return rs
    }

    @PostMapping("/refin_confirm")
    fun refinConfirm (
        rq: MisStartRq?
    ): BaseResponseRefinConfirm {
        logger.info("refin_confirm $rq")
        val rs = BaseResponseRefinConfirm(
            response = BaseResponseRefinConfirm.RefinConfirmResult()
        )

        return rs
    }

}