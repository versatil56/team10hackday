#!/bin/bash

echo "Applying migration doYouWantToGiftAid"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /doYouWantToGiftAid                       controllers.doYouWantToGiftAidController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /doYouWantToGiftAid                       controllers.doYouWantToGiftAidController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changedoYouWantToGiftAid                       controllers.doYouWantToGiftAidController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changedoYouWantToGiftAid                       controllers.doYouWantToGiftAidController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "doYouWantToGiftAid.title = doYouWantToGiftAid" >> ../conf/messages.en
echo "doYouWantToGiftAid.heading = doYouWantToGiftAid" >> ../conf/messages.en
echo "doYouWantToGiftAid.checkYourAnswersLabel = doYouWantToGiftAid" >> ../conf/messages.en
echo "doYouWantToGiftAid.error.required = Please give an answer for doYouWantToGiftAid" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def doYouWantToGiftAid: Option[Boolean] = cacheMap.getEntry[Boolean](doYouWantToGiftAidId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def doYouWantToGiftAid: Option[AnswerRow] = userAnswers.doYouWantToGiftAid map {";\
     print "    x => AnswerRow(\"doYouWantToGiftAid.checkYourAnswersLabel\", if(x) \"site.yes\" else \"site.no\", true, routes.doYouWantToGiftAidController.onPageLoad(CheckMode).url)"; print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration doYouWantToGiftAid completed"
