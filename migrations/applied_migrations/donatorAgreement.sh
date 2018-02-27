#!/bin/bash

echo "Applying migration donatorAgreement"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /donatorAgreement                       controllers.donatorAgreementController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /donatorAgreement                       controllers.donatorAgreementController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changedonatorAgreement                       controllers.donatorAgreementController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changedonatorAgreement                       controllers.donatorAgreementController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "donatorAgreement.title = donatorAgreement" >> ../conf/messages.en
echo "donatorAgreement.heading = donatorAgreement" >> ../conf/messages.en
echo "donatorAgreement.checkYourAnswersLabel = donatorAgreement" >> ../conf/messages.en
echo "donatorAgreement.error.required = Please give an answer for donatorAgreement" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def donatorAgreement: Option[Boolean] = cacheMap.getEntry[Boolean](donatorAgreementId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def donatorAgreement: Option[AnswerRow] = userAnswers.donatorAgreement map {";\
     print "    x => AnswerRow(\"donatorAgreement.checkYourAnswersLabel\", if(x) \"site.yes\" else \"site.no\", true, routes.donatorAgreementController.onPageLoad(CheckMode).url)"; print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration donatorAgreement completed"
