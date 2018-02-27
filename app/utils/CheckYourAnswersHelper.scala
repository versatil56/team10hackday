package utils

import controllers.routes
import models.CheckMode
import viewmodels.{AnswerRow, RepeaterAnswerRow, RepeaterAnswerSection}

class CheckYourAnswersHelper(userAnswers: UserAnswers) {

  def whatIsDonatorsName: Option[AnswerRow] = userAnswers.whatIsDonatorsName map {
    x => AnswerRow("whatIsDonatorsName.checkYourAnswersLabel", s"$x", false, routes.whatIsDonatorsNameController.onPageLoad(CheckMode).url)
  }
}
