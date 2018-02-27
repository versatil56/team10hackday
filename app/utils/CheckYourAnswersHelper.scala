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

import controllers.routes
import models.CheckMode
import viewmodels.{AnswerRow, RepeaterAnswerRow, RepeaterAnswerSection}

class CheckYourAnswersHelper(userAnswers: UserAnswers) {

  def doYouWantToGiftAid: Option[AnswerRow] = userAnswers.doYouWantToGiftAid map {
    x => AnswerRow("doYouWantToGiftAid.checkYourAnswersLabel", if(x) "site.yes" else "site.no", true, routes.doYouWantToGiftAidController.onPageLoad(CheckMode).url)
  }

  def whatIsYourDonatorsNumber: Option[AnswerRow] = userAnswers.whatIsYourDonatorsNumber map {
    x => AnswerRow("whatIsYourDonatorsNumber.checkYourAnswersLabel", s"$x", false, routes.whatIsYourDonatorsNumberController.onPageLoad(CheckMode).url)
  }

  def doYouHaveADonatorsNumber: Option[AnswerRow] = userAnswers.doYouHaveADonatorsNumber map {
    x => AnswerRow("doYouHaveADonatorsNumber.checkYourAnswersLabel", if(x) "site.yes" else "site.no", true, routes.doYouHaveADonatorsNumberController.onPageLoad(CheckMode).url)
  }

  def donatorAgreement: Option[AnswerRow] = userAnswers.donatorAgreement map {
    x => AnswerRow("donatorAgreement.checkYourAnswersLabel", if(x) "site.yes" else "site.no", true, routes.donatorAgreementController.onPageLoad(CheckMode).url)
  }

  def donationDate: Option[AnswerRow] = userAnswers.donationDate map {
    x => AnswerRow("donationDate.checkYourAnswersLabel", s"$x", false, routes.donationDateController.onPageLoad(CheckMode).url)
  }

  def amountToDonate: Option[AnswerRow] = userAnswers.amountToDonate map {
    x => AnswerRow("amountToDonate.checkYourAnswersLabel", s"$x", false, routes.amountToDonateController.onPageLoad(CheckMode).url)
  }

  def donatorsPostcode: Option[AnswerRow] = userAnswers.donatorsPostcode map {
    x => AnswerRow("donatorsPostcode.checkYourAnswersLabel", s"$x", false, routes.donatorsPostcodeController.onPageLoad(CheckMode).url)
  }

  def donatorsHouseNumber: Option[AnswerRow] = userAnswers.donatorsHouseNumber map {
    x => AnswerRow("donatorsHouseNumber.checkYourAnswersLabel", s"$x", false, routes.donatorsHouseNumberController.onPageLoad(CheckMode).url)
  }

  def donatorsEmail: Option[AnswerRow] = userAnswers.donatorsEmail map {
    x => AnswerRow("donatorsEmail.checkYourAnswersLabel", s"$x", false, routes.donatorsEmailController.onPageLoad(CheckMode).url)
  }

  def donatorsSurname: Option[AnswerRow] = userAnswers.donatorsSurname map {
    x => AnswerRow("donatorsSurname.checkYourAnswersLabel", s"$x", false, routes.donatorsSurnameController.onPageLoad(CheckMode).url)
  }

  def donatorsName: Option[AnswerRow] = userAnswers.donatorsName map {
    x => AnswerRow("donatorsName.checkYourAnswersLabel", s"$x", false, routes.donatorsNameController.onPageLoad(CheckMode).url)
  }

  def whatIsDonatorsName: Option[AnswerRow] = userAnswers.whatIsDonatorsName map {
    x => AnswerRow("whatIsDonatorsName.checkYourAnswersLabel", s"$x", false, routes.whatIsDonatorsNameController.onPageLoad(CheckMode).url)
  }
}
