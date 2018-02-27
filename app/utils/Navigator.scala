/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package utils

import javax.inject.{Inject, Singleton}

import play.api.mvc.Call
import controllers.routes
import identifiers.{donatorsHouseNumberId, _}
import models.{CheckMode, Mode, NormalMode}

@Singleton
class Navigator @Inject()() {

  private val routeMap: Map[Identifier, UserAnswers => Call] = Map(
    amountToDonateId -> routeToDoYouWanToGiftAid,
    doYouWantToGiftAidId -> routeAnswerGiftAid,
    donatorsNameId -> routeToSurname,
    donatorsSurnameId -> routeToDoYouHaveDonatorsNumber,
    doYouHaveADonatorsNumberId -> routeToYourDonatorNumber,
    whatIsYourDonatorsNumberId -> routeToEmail,
    donatorsHouseNumberId -> routeToPostcode,
    donatorsPostcodeId -> routeToEmail,
    donatorsEmailId -> routeToAgreement,
    donatorAgreementId -> routeToUserAnswersPage
  )

  private val editRouteMap: Map[Identifier, UserAnswers => Call] = Map(

  )

  def routeToDoYouWanToGiftAid(answers: UserAnswers) = routes.doYouWantToGiftAidController.onPageLoad(NormalMode)

  def routeAnswerGiftAid(answers: UserAnswers) = {
    if (answers.doYouWantToGiftAid.fold(false)(c=>c)) {
      routes.giftAidAmountController.onPageLoad()
    }
    else {
      routes.donatorsNameController.onPageLoad(NormalMode)
    }
  }

  def routeToDoYouHaveDonatorsNumber(answers: UserAnswers): Call = routes.doYouHaveADonatorsNumberController.onPageLoad(NormalMode)

  def routeToYourDonatorNumber(answers: UserAnswers): Call = {
    if (answers.doYouHaveADonatorsNumber.fold(false)(c=>c)) {
      routes.whatIsYourDonatorsNumberController.onPageLoad(NormalMode)
    }
    else {
      routes.donatorsHouseNumberController.onPageLoad(NormalMode)
    }
  }

  def routeToAgreement(answers: UserAnswers) = routes.donatorAgreementController.onPageLoad(NormalMode)

  def routeToDate(answers: UserAnswers) = routes.donationDateController.onPageLoad(NormalMode)

  def routeToName(answers:UserAnswers) = routes.donatorsNameController.onPageLoad(NormalMode)

  def routeToSurname(answers: UserAnswers) = routes.donatorsSurnameController.onPageLoad(NormalMode)

  def routeToHouseName(answers: UserAnswers) = routes.donatorsHouseNumberController.onPageLoad(NormalMode)

  def routeToPostcode(answers: UserAnswers) = routes.donatorsPostcodeController.onPageLoad(NormalMode)

  def routeToEmail(answers: UserAnswers) = routes.donatorsEmailController.onPageLoad(NormalMode)

  def routeToUserAnswersPage(answers: UserAnswers) = routes.CheckYourAnswersController.onPageLoad

  def nextPage(id: Identifier, mode: Mode): UserAnswers => Call = mode match {
    case NormalMode =>
      routeMap.getOrElse(id, _ => routes.StartPageController.onPageLoad())
    case CheckMode =>
      editRouteMap.getOrElse(id, _ => routes.CheckYourAnswersController.onPageLoad())
  }
}
