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

package controllers

import com.google.inject.Inject
import play.api.i18n.{I18nSupport, MessagesApi}
import controllers.actions.{AuthAction, DataRequiredAction, DataRetrievalAction}
import utils.{CheckYourAnswersHelper, Navigator, UserAnswers}
import viewmodels.AnswerSection
import views.html.{check_your_answers, donatorAgreement}
import config.FrontendAppConfig
import connectors.DataCacheConnector
import forms.donatorAgreementFormProvider
import identifiers.donatorAgreementId
import models.{Mode, NormalMode}
import uk.gov.hmrc.play.bootstrap.controller.FrontendController
import identifiers.donatorAgreementId
import models.Mode
import play.api.data.Form
import uk.gov.hmrc.play.views.html.helpers.form

import scala.concurrent.Future

class CheckYourAnswersController @Inject()(appConfig: FrontendAppConfig,
                                           override val messagesApi: MessagesApi,
                                           dataCacheConnector: DataCacheConnector,
                                           navigator: Navigator,
                                           authenticate: AuthAction,
                                           getData: DataRetrievalAction,
                                           requireData: DataRequiredAction,
                                           formProvider: donatorAgreementFormProvider) extends FrontendController with I18nSupport {

  val form: Form[Boolean] = formProvider()

  def onPageLoad() = (getData andThen requireData) {
    implicit request =>

      val preparedForm = request.userAnswers.donatorAgreement match {
        case None => form
        case Some(value) => form.fill(value)
      }

      val checkYourAnswersHelper = new CheckYourAnswersHelper(request.userAnswers)
      val sections = Seq(AnswerSection(None, Seq(
        checkYourAnswersHelper.amountToDonate,
        checkYourAnswersHelper.donatorsName,
        checkYourAnswersHelper.donatorsSurname,
        checkYourAnswersHelper.donatorsEmail,
        checkYourAnswersHelper.donatorsHouseNumber,
        checkYourAnswersHelper.donatorsPostcode
      ).flatten))

      Ok(check_your_answers(appConfig, preparedForm, NormalMode, sections))
  }

  def onSubmit(mode: Mode) = (getData andThen requireData).async {
    implicit request =>

      val checkYourAnswersHelper = new CheckYourAnswersHelper(request.userAnswers)
      val sections = Seq(AnswerSection(None, Seq(
        checkYourAnswersHelper.amountToDonate,
        checkYourAnswersHelper.donatorsName,
        checkYourAnswersHelper.donatorsSurname,
        checkYourAnswersHelper.donatorsEmail,
        checkYourAnswersHelper.donatorsHouseNumber,
        checkYourAnswersHelper.donatorsPostcode
      ).flatten))

      form.bindFromRequest().fold(
        (formWithErrors: Form[_]) =>
          Future.successful(BadRequest(check_your_answers(appConfig, formWithErrors, NormalMode, sections))),
        (value) =>
          dataCacheConnector.save[Boolean](request.externalId, donatorAgreementId.toString, value).map(cacheMap =>
            Redirect(navigator.nextPage(donatorAgreementId, mode)(new UserAnswers(cacheMap))))
      )
  }
}
