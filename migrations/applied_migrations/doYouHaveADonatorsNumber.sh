#!/bin/bash

echo "Applying migration doYouHaveADonatorsNumber"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /doYouHaveADonatorsNumber                       controllers.doYouHaveADonatorsNumberController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /doYouHaveADonatorsNumber                       controllers.doYouHaveADonatorsNumberController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changedoYouHaveADonatorsNumber                       controllers.doYouHaveADonatorsNumberController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changedoYouHaveADonatorsNumber                       controllers.doYouHaveADonatorsNumberController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "doYouHaveADonatorsNumber.title = doYouHaveADonatorsNumber" >> ../conf/messages.en
echo "doYouHaveADonatorsNumber.heading = doYouHaveADonatorsNumber" >> ../conf/messages.en
echo "doYouHaveADonatorsNumber.checkYourAnswersLabel = doYouHaveADonatorsNumber" >> ../conf/messages.en
echo "doYouHaveADonatorsNumber.error.required = Please give an answer for doYouHaveADonatorsNumber" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def doYouHaveADonatorsNumber: Option[Boolean] = cacheMap.getEntry[Boolean](doYouHaveADonatorsNumberId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def doYouHaveADonatorsNumber: Option[AnswerRow] = userAnswers.doYouHaveADonatorsNumber map {";\
     print "    x => AnswerRow(\"doYouHaveADonatorsNumber.checkYourAnswersLabel\", if(x) \"site.yes\" else \"site.no\", true, routes.doYouHaveADonatorsNumberController.onPageLoad(CheckMode).url)"; print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration doYouHaveADonatorsNumber completed"
