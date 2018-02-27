package controllers

import javax.inject.Inject

import play.api.data.Form
import play.api.i18n.{I18nSupport, MessagesApi}
import uk.gov.hmrc.play.bootstrap.controller.FrontendController
import connectors.DataCacheConnector
import controllers.actions._
import config.FrontendAppConfig
import forms.whatIsDonatorsNameFormProvider
import identifiers.whatIsDonatorsNameId
import models.Mode
import utils.{Navigator, UserAnswers}
import views.html.whatIsDonatorsName

import scala.concurrent.Future

class whatIsDonatorsNameController @Inject()(
                                        appConfig: FrontendAppConfig,
                                        override val messagesApi: MessagesApi,
                                        dataCacheConnector: DataCacheConnector,
                                        navigator: Navigator,
                                        authenticate: AuthAction,
                                        getData: DataRetrievalAction,
                                        requireData: DataRequiredAction,
                                        formProvider: whatIsDonatorsNameFormProvider) extends FrontendController with I18nSupport {

  val form = formProvider()

  def onPageLoad(mode: Mode) = (authenticate andThen getData andThen requireData) {
    implicit request =>
      val preparedForm = request.userAnswers.whatIsDonatorsName match {
        case None => form
        case Some(value) => form.fill(value)
      }
      Ok(whatIsDonatorsName(appConfig, preparedForm, mode))
  }

  def onSubmit(mode: Mode) = (authenticate andThen getData andThen requireData).async {
    implicit request =>
      form.bindFromRequest().fold(
        (formWithErrors: Form[_]) =>
          Future.successful(BadRequest(whatIsDonatorsName(appConfig, formWithErrors, mode))),
        (value) =>
          dataCacheConnector.save[Int](request.externalId, whatIsDonatorsNameId.toString, value).map(cacheMap =>
            Redirect(navigator.nextPage(whatIsDonatorsNameId, mode)(new UserAnswers(cacheMap))))
      )
  }
}
